package pe.gob.onp.nsai.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import pe.gob.onp.nsai.dto.Auditoria;
import pe.gob.onp.nsai.util.UConstantes;

@Component
@RequestScope
@Slf4j
public class ServicioREST {

    private final SistemaNSAIProperties sistemaNSAI;

    private final MessageSource messageSource;

    private final DataSource dataSource;


    @Value("${reportes.path}")
    private String reportPath;

    public ServicioREST(SistemaNSAIProperties sistemaNSAI, MessageSource messageSource, DataSource dataSource) {
        this.sistemaNSAI = sistemaNSAI;
        this.messageSource = messageSource;
        this.dataSource = dataSource;
    }

    /**
     * Constante de nombre del sistema NSAI
     */
    private static final String NSAI_FRONTEND="nsai_frontend";
    public static final String ID_SISTEMA_FRONTEND="1";

    /**
     * Instancia a ResourceBundle
     */
    //Locale locale = new Locale("es", "PE");
    //ResourceBundle mensajes = ResourceBundle.getBundle("pe.gob.onp.nsai.util.mensajes.mensajes", locale);

    /**
     * Método que permite obtener el identificador del error
     * @return tiempo en milisegundos, tipo Long.
     */
    protected Long idError(){
        return System.currentTimeMillis();
    }

    public String getMessage(String message) {
       return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }

    /**
     * Método que permite obtener los datos de auditoria del usuario de creación.
     * @return auditoria datos de auditoria, tipo Auditoria.
     */
    protected Auditoria obtenerAuditoriaCreacion(HttpHeaders headers, HttpServletRequest request)  {
        log.info("NK: obtenerAuditoriaCreacion : inicio");
        Auditoria auditoria=new Auditoria();
        String user = obtenerCuerpoJWT(headers);
        auditoria.setUsuarioCreacion(user);
        auditoria.setTerminalCreacion(request.getRemoteAddr());
        return auditoria;
    }
    /**
     * Método que permite obtener los datos de auditoria del usuario de modificación.
     * @return auditoria datos de auditoria, tipo Auditoria.
     */
    protected Auditoria obtenerAuditoriaModificacion(HttpHeaders headers, HttpServletRequest request)  {
        log.info("NK: obtenerAuditoriaModificacion : inicio");
        Auditoria auditoria=new Auditoria();
        String user = obtenerCuerpoJWT(headers);
        auditoria.setUsuarioModificacion(user);
        auditoria.setTerminalModificacion(request.getRemoteAddr());
        return auditoria;
    }

    private String obtenerCuerpoJWT(HttpHeaders headers) {
        return headers.getFirst("x-user");
    }

    public byte[] exportarJasper(String jasper, Map<String, Object> parameters, String tipoArchivo, Auditoria auditoria) throws Exception {
        log.debug("Generando reporte - Jasper: {}, Tipo archivo: {}", jasper, tipoArchivo);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Connection conn = dataSource.getConnection()) {


            String pathReport = reportPath + "/" + jasper;
            // Configurar parámetros comunes
            InputStream logoStream = new FileInputStream(reportPath.endsWith("/") ? reportPath : reportPath + "/" + "onp-logo.png");
            Map<String, Object> params = new HashMap<>();
            parameters.put("logoParam", logoStream);  //
            parameters.put("usuario", auditoria.getUsuarioCreacion());  //

            parameters.put("SUBREPORT_DIR", reportPath.endsWith("/") ? reportPath : reportPath + "/");

            parameters.put("contexto", pathReport);
            parameters.put("P_DIRECTORIO_REPORTE", reportPath.endsWith("/") ? reportPath : reportPath + "/");
            parameters.put(JRParameter.REPORT_LOCALE, UConstantes.LOCALE_REPORTE);

            if (tipoArchivo.equals(UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL)) {
                parameters.put("IS_IGNORE_PAGINATION", true);
            }

            // Cargar y llenar el reporte
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(pathReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            // Exportar según el tipo de archivo
            return switch (tipoArchivo.trim().toUpperCase()) {
                case UConstantes.TIPO_ARCHIVO_REPORTE_PDF -> exportToPdf(jasperPrint, outputStream);
                case UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL -> exportToExcel(jasperPrint, outputStream);
                default -> throw new IllegalArgumentException("Tipo de archivo no soportado: " + tipoArchivo);
            };

        } catch (Exception ex) {
            log.error("Error al generar el reporte", ex);
            throw new Exception("Error al generar el reporte: " + ex.getMessage(), ex);
        }
    }

    private byte[] exportToPdf(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
        if (jasperPrint.getPages().isEmpty()) {
            log.info("El reporte no tiene páginas");
            return null;
        } else {
            for (int i = 0; i < jasperPrint.getPages().size(); i++) {
                JRPrintPage page = jasperPrint.getPages().get(i);
                if (page.getElements().isEmpty()) {
                    log.info("La página " + (i+1) + " está en blanco");
                }
            }
        }


        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();


        return outputStream.toByteArray();
    }

    private byte[] exportToExcel(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) throws JRException {
        if (jasperPrint.getPages().isEmpty()) {
            log.info("El reporte en excel no tiene páginas");
            return null;
        } else {
            for (int i = 0; i < jasperPrint.getPages().size(); i++) {
                JRPrintPage page = jasperPrint.getPages().get(i);
                if (page.getElements().isEmpty()) {
                    log.info("La página  " + (i+1) + " está en blanco");
                }
            }
        }
        JRXlsxExporter exporter = getJrXlsxExporter(jasperPrint, outputStream);
        exporter.exportReport();

        return outputStream.toByteArray();
    }

    private static JRXlsxExporter getJrXlsxExporter(JasperPrint jasperPrint, ByteArrayOutputStream outputStream) {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
        reportConfig.setOnePagePerSheet(false);
        reportConfig.setDetectCellType(true);
        reportConfig.setCollapseRowSpan(false);
        reportConfig.setWhitePageBackground(false);
        reportConfig.setRemoveEmptySpaceBetweenRows(true);

        exporter.setConfiguration(reportConfig);
        return exporter;
    }

    protected String recuperarNombreArchivo(String cabecera){
        Pattern patron=Pattern.compile("filename=\"(.+)\"");
        Matcher buscador = patron.matcher(cabecera);
        String nombreArchivo=null;
        if(buscador.find()){
            nombreArchivo=buscador.group(1);
        }
        return nombreArchivo;
    }

}
