/**
 * Resumen.
 * Objeto               :   EntregaPredioDelegateEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para el mantenimiento de entrega de predio.
 * Fecha de Creación    :   23/03/2021
 * PE de Creación       :   INI-900
 * Autor                :   Omar Meza.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.EntregaPredioLocalDao;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.EntregaPredioService;
import pe.gob.onp.nsai.util.DirectorioTaxonomia;
import pe.gob.onp.nsai.util.UConvierteFecha;
import pe.gob.onp.nsai.util.UServicios;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * Clase delegate para obtener las equivalencias entre el appens y el nsai.
 */
@Service
public class EntregaPredioServiceImpl implements EntregaPredioService {

    /**
     * Clase EJB para el mantenimiento de proveedores
     */

    private final EntregaPredioLocalDao ejbEntregaPredio;

    public EntregaPredioServiceImpl(EntregaPredioLocalDao ejbEntregaPredio) {
        this.ejbEntregaPredio = ejbEntregaPredio;
    }

    /**
     * Método que permite realizar una consulta de entrega predio.
     *
     * @param entregaPredioBusqueda datos de la entrega de predio a buscar, tipo EntregaPredio.
     * @param pagina                pagina de busqueda, tipo int.
     * @param nroRegistros          numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarEntregaPredio(EntregaPredio entregaPredioBusqueda, int pagina, int nroRegistros)
            throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("estado", entregaPredioBusqueda.getEstadoBusqueda());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            int cantidadlistaEntregaPredio = obtenerCantidadEntregaPredio(entregaPredioBusqueda);
            List<EntregaPredio> listaEntregaPredio = obtenerListaEntregaPredio(parametrosConsulta);
            //int cantidadlistaEntregaPredio =  listaEntregaPredio.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadlistaEntregaPredio);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaEntregaPredio(listaEntregaPredio);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar proveedores.
     *
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaProveedores lista de registros con los datos del proveedor, tipo List<Proveedor>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<EntregaPredio> obtenerListaEntregaPredio(Map<String, Object> parametrosConsulta) throws Exception {
        try {
            return ejbEntregaPredio.obtenerListaEntregaPredio(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de registros de proveedores según la busqueda.
     *
     * @param entregaPredioBusqueda objeto con los datos de búsqueda del proveedor, tipo Proveedor.
     * @return cantidadListaProveedores número de registros de proveedores, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadEntregaPredio(EntregaPredio entregaPredioBusqueda) throws Exception {
        try {
            return ejbEntregaPredio.obtenerCantidadEntregaPredio(entregaPredioBusqueda);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar datos del proveedor.
     *
     * @param entregaPredio datos del proveedor a modificar, tipo Proveedor.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void asignarResponsable(EntregaPredio entregaPredio) throws Exception {
        try {
            ejbEntregaPredio.asignarResponsable(entregaPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite agregar observacion del predio
     *
     * @param entregaPredio Datos del observacion del predio, tipo EntregaPredio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionPredio(EntregaPredio entregaPredio) throws Exception {
        try {
            ejbEntregaPredio.agregarObservacionPredio(entregaPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite agregar observacion por infraestructura.
     *
     * @param listAmbienteInfraestructura datos de la infraestructura, tipo AmbienteInfraestructura.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionInfraestructura(List<AmbienteInfraestructura> listAmbienteInfraestructura, AmbienteInfraestructura ambienteInfraestructura) throws Exception {
        try {
            for (AmbienteInfraestructura value : listAmbienteInfraestructura) {
                value.setAuditoria(ambienteInfraestructura.getAuditoria());
                value.setIdEntregaPredio(ambienteInfraestructura.getIdEntregaPredio());
                if (value.getObservacion() != null) {
                    if (value.getIdDetEntregaPredioInfr() != 0) {
                        ejbEntregaPredio.actualizarObservacionInfraestructura(value);
                    } else {
                        ejbEntregaPredio.agregarObservacionInfraestructura(value);
                    }

                }

            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite agregar observacion por ambiente.
     *
     * @param listAmbienteContrato datos de la ambiente, tipo AmbienteContrato.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionAmbiente(List<AmbienteContrato> listAmbienteContrato, AmbienteContrato ambienteContrato) throws Exception {
        try {
            for (AmbienteContrato value : listAmbienteContrato) {
                value.setAuditoria(ambienteContrato.getAuditoria());
                value.setIdEntregaPredio(ambienteContrato.getIdEntregaPredio());
                if (value.getObservacionAmbiente() != null) {
                    if (value.getIdDetalleEntrPreAmbiente() != 0) {
                        ejbEntregaPredio.actualizarObservacionAmbiente(value);
                    } else {
                        ejbEntregaPredio.agregarObservacionAmbiente(value);
                    }

                }
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite consultar el tipo de responsable.
     *
     * @return lista registros con los datos de responsable, tipo List<TipoResponsable>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<TipoResponsable> obtenerResponsables() throws Exception {
        try {
            return ejbEntregaPredio.obtenerResponsables();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar la entrega de predio segun el código
     *
     * @param codigoEntregaPredio código de la entrega de predio a consultar, tipo String.
     * @return datos del entregaPredio, tipo EntregaPredio.
     * @throws Exception excepción generada en caso de error.
     */
    public EntregaPredio obtenerEntregaPredio(String codigoEntregaPredio, Integer codigoPredio) throws Exception {
        try {
            EntregaPredio entregaPredio = ejbEntregaPredio.obtenerEntregaPredio(codigoEntregaPredio, codigoPredio);
            entregaPredio.setDepartamentoInmueble(entregaPredio.getCodigoAreaGeografica().substring(0, 2) + "0000");
            entregaPredio.setProvinciaInmueble(entregaPredio.getCodigoAreaGeografica().substring(0, 4) + "00");
            entregaPredio.setDistritoInmueble(entregaPredio.getCodigoAreaGeografica());
            return entregaPredio;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite consultar los datos del reporte acta entrega.
     *
     * @return lista registros con los datos de reporte acta entrega, tipo List<ReporteActaEntrega>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ReporteActaEntrega> obtenerDatosReporteActaEntrega() throws Exception {
        try {
            return ejbEntregaPredio.obtenerDatosReporteActaEntrega();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite realizar una consulta de entrega predio.
     *
     * @param entregaPredioBusqueda datos de la entrega de predio a buscar, tipo EntregaPredio.
     * @param pagina                pagina de busqueda, tipo int.
     * @param nroRegistros          numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento obtenerAmbienteContrato(EntregaPredio entregaPredioBusqueda, int pagina, int nroRegistros)
            throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idContrato", entregaPredioBusqueda.getIdContrato());
            parametrosConsulta.put("idDetalleInmuPredio", entregaPredioBusqueda.getIdDetalleInmuPredio());
            parametrosConsulta.put("idEntregaPredio", entregaPredioBusqueda.getIdEntregaPredio());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);


            List<AmbienteContrato> listaAmbienteContrato = obtenerListaAmbienteContrato(parametrosConsulta);

            int cantidadlistaEntregaPredio = listaAmbienteContrato.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadlistaEntregaPredio);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaAmbienteContrato(listaAmbienteContrato);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite consultar los ambientes del contrato.
     *
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de los ambientes por contrato, tipo List<AmbienteContrato>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<AmbienteContrato> obtenerListaAmbienteContrato(Map<String, Object> parametrosConsulta) throws Exception {
        try {
            return ejbEntregaPredio.obtenerAmbienteContrato(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener los datos de la infraestructura por ambiente.
     *
     * @param idAmbiente identificador del ambiente idAmbiente, tipo long.
     * @return bean con los datos de la infraestructura, tipo List<AmbienteInfraestructura>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<AmbienteInfraestructura> obtenerInfraestruturaPorAmbiente(long idAmbiente) throws Exception {
        List<AmbienteInfraestructura> ambienteInfraestructura;
        try {

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idAmbiente", idAmbiente);
            ambienteInfraestructura = ejbEntregaPredio.obtenerInfraestruturaPorAmbiente(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return ambienteInfraestructura;
    }


    /**
     * Método que permite generar los datos del reporte de acta de entrega en pdf.
     *
     * @param listaReporteActaEntrega lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param filtroCodigoContrato    filtro por código del contrato, tipo String.
     * @param context                 contexto del sistema, tipo ServletContext.
     * @param auditoria               objeto de auditoria, tipo Auditoria.
     * @return bytes arreglo de bytes del archivo pdf generado, tipo byte[].
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public byte[] generarReporteActaEntregaPdf(List<ReporteActaEntrega> listaReporteActaEntrega,
                                               String filtroCodigoContrato, ServletContext context, Auditoria auditoria) throws Exception {

        try {

            Map<String, Object> parametrosPdf = new HashMap<>();


            String usuarioNSAI = auditoria.getUsuarioCreacion();
            parametrosPdf.put("usuario", usuarioNSAI);

            DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            simbolo.setGroupingSeparator(',');
            DecimalFormat formatoNumero = new DecimalFormat("#,##0", simbolo);

            parametrosPdf.put("formatoNumero", formatoNumero);


            if (listaReporteActaEntrega.isEmpty()) {
                ReporteActaEntrega ayuda = new ReporteActaEntrega();
                listaReporteActaEntrega.add(ayuda);
            }

            JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listaReporteActaEntrega);
            String ruta = context.getRealPath("/WEB-INF/reportes/reportePrediosAmbientes.jasper");
            InputStream reportStream = new FileInputStream(ruta);
            return JasperRunManager.runReportToPdf(reportStream, parametrosPdf, jrb);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }


    }

    /**
     * Método que permite generar los datos del reporte de acta de entrega en excel.
     *
     * @param listaReporteActaEntrega lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param auditoria               objeto de auditoria, tipo Auditoria.
     * @return arregloBytes arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarReporteActaEntregaExcel(List<ReporteActaEntrega> listaReporteActaEntrega,
                                                                EntregaPredio entregaPredio, Auditoria auditoria) throws Exception {
        try {


            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Entrega");

            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight((short) (9 * 20));

            XSSFFont font2 = workbook.createFont();
            font2.setFontName("Arial");
            font2.setFontHeight((short) (9 * 20));

            XSSFFont font3 = workbook.createFont();
            font3.setFontName("Arial");
            font3.setFontHeight((short) (14 * 20));

            CellStyle estiloCelda = workbook.createCellStyle();
            estiloCelda.setFont(font);
            estiloCelda.setBorderBottom(BorderStyle.MEDIUM);
            estiloCelda.setBorderLeft(BorderStyle.MEDIUM);
            estiloCelda.setBorderRight(BorderStyle.MEDIUM);
            estiloCelda.setBorderTop(BorderStyle.MEDIUM);
            estiloCelda.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCelda.setAlignment(HorizontalAlignment.CENTER);

            CellStyle estiloCabe = workbook.createCellStyle();
            estiloCabe.setFont(font);
            estiloCabe.setBorderBottom(BorderStyle.DOUBLE);
            estiloCabe.setBorderLeft(BorderStyle.DOUBLE);
            estiloCabe.setBorderRight(BorderStyle.DOUBLE);
            estiloCabe.setBorderTop(BorderStyle.DOUBLE);
            estiloCabe.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCabe.setAlignment(HorizontalAlignment.CENTER);

            CellStyle estiloCabeFont = workbook.createCellStyle();
            estiloCabeFont.setFont(font3);
            estiloCabeFont.setBorderBottom(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderLeft(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderRight(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderTop(BorderStyle.DOUBLE);
            estiloCabeFont.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCabeFont.setAlignment(HorizontalAlignment.CENTER);

            CellStyle estiloCeldaDe = workbook.createCellStyle();
            estiloCeldaDe.setFont(font2);
            estiloCeldaDe.setBorderBottom(BorderStyle.THIN);
            estiloCeldaDe.setBorderLeft(BorderStyle.THIN);
            estiloCeldaDe.setBorderRight(BorderStyle.THIN);
            estiloCeldaDe.setBorderTop(BorderStyle.THIN);


            CellStyle estiloCeldaRota = workbook.createCellStyle();
            estiloCeldaRota.setFont(font);
            estiloCeldaRota.setBorderBottom(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderLeft(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderRight(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderTop(BorderStyle.MEDIUM);
            estiloCeldaRota.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCeldaRota.setAlignment(HorizontalAlignment.CENTER);
            estiloCeldaRota.setRotation((short) 0xff);

            CellStyle estiloCeldaNegrita = workbook.createCellStyle();
            estiloCeldaNegrita.setFont(font);


            Row fila1 = sheet.createRow(0);

            Cell celda1 = fila1.createCell(0);
            celda1.setCellValue("OFICINA DE NORMALIZACIÓN PREVISIONAL - ONP");
            celda1.setCellStyle(estiloCabe);
            Cell celda2 = fila1.createCell(1);
            celda2.setCellStyle(estiloCabe);
            Cell celda3 = fila1.createCell(2);
            celda3.setCellStyle(estiloCabe);
            Cell celda4 = fila1.createCell(3);
            //celda4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN: \n del Predio: DPTO. N° " +entregaPredio.getDescripcionBloque() + " - " + entregaPredio.getDescripcionPredio() );
            celda4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN");
            // Crear un estilo para centrar el texto
            CellStyle estilo = workbook.createCellStyle();
            estilo.setAlignment(HorizontalAlignment.CENTER);
            estilo.setFont(font);
            estilo.setBorderBottom(BorderStyle.DOUBLE);
            estilo.setBorderLeft(BorderStyle.DOUBLE);
            estilo.setBorderRight(BorderStyle.DOUBLE);
            estilo.setBorderTop(BorderStyle.DOUBLE);
            estilo.setVerticalAlignment(VerticalAlignment.CENTER);
            estilo.setAlignment(HorizontalAlignment.CENTER);
            estilo.setWrapText(true);

            // Aplicar el estilo a la celda
            celda4.setCellStyle(estilo);

            // Auto ajustar el ancho de la columna para que se ajuste al contenido
            fila1.getSheet().autoSizeColumn(3);
            int anchoCelda = celda4.getSheet().getColumnWidth(30);
            celda4.getSheet().setColumnWidth(3, (int) (anchoCelda * 1.2));

            //celda4.setCellStyle(estiloCabe);
            Cell celda5 = fila1.createCell(4);
            celda5.setCellStyle(estiloCabe);
            Cell celda6 = fila1.createCell(5);
            celda6.setCellStyle(estiloCabe);
            Cell celda7 = fila1.createCell(6);
            celda7.setCellStyle(estiloCabe);
            Cell celda8 = fila1.createCell(7);
            celda8.setCellStyle(estiloCabe);
            Cell celda9 = fila1.createCell(8);
            celda9.setCellValue("FECHA:");
            celda9.setCellStyle(estiloCabe);
            Cell celda10 = fila1.createCell(9);
            celda10.setCellValue(UConvierteFecha.formatearFecha(new Date()));
            celda10.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 7));


            Row fila2 = sheet.createRow(1);

            Cell celda11 = fila2.createCell(0);
            celda11.setCellStyle(estiloCabe);
            Cell celda12 = fila2.createCell(1);
            celda12.setCellStyle(estiloCabe);
            Cell celda13 = fila2.createCell(2);
            celda13.setCellStyle(estiloCabe);
            Cell celda14 = fila2.createCell(3);
            celda14.setCellValue("ACTA ENTREGA");
            celda14.setCellStyle(estiloCabeFont);
            Cell celda15 = fila2.createCell(4);
            celda15.setCellStyle(estiloCabe);
            Cell celda16 = fila2.createCell(5);
            celda16.setCellStyle(estiloCabe);
            Cell celda17 = fila2.createCell(6);
            celda17.setCellStyle(estiloCabe);
            Cell celda18 = fila2.createCell(7);
            celda18.setCellStyle(estiloCabe);
            Cell celda19 = fila2.createCell(8);
            celda19.setCellValue("PAGINA:");
            celda19.setCellStyle(estiloCabe);
            Cell celda20 = fila2.createCell(9);
            celda20.setCellValue("1");
            celda20.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));


            Row fila3 = sheet.createRow(2);

            Cell celda21 = fila3.createCell(0);
            celda21.setCellStyle(estiloCabe);
            Cell celda22 = fila3.createCell(1);
            celda22.setCellStyle(estiloCabe);
            Cell celda23 = fila3.createCell(2);
            celda23.setCellStyle(estiloCabe);
            Cell celda24 = fila3.createCell(3);
            celda24.setCellValue("Contrato de Arrendamiento N°");
            celda24.setCellStyle(estiloCabe);
            Cell celda25 = fila3.createCell(4);
            celda25.setCellStyle(estiloCabe);
            Cell celda26 = fila3.createCell(5);
            celda26.setCellValue(entregaPredio.getIdContrato());
            celda26.setCellStyle(estiloCabe);
            Cell celda27 = fila3.createCell(6);
            celda27.setCellStyle(estiloCabe);
            Cell celda28 = fila3.createCell(7);
            celda28.setCellStyle(estiloCabe);
            Cell celda29 = fila3.createCell(8);
            celda29.setCellStyle(estiloCabe);
            Cell celda30 = fila3.createCell(9);
            celda30.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 7));

            Row fila4 = sheet.createRow(4);
            fila4.createCell(0).setCellValue("Edificio");
            fila4.createCell(1).setCellValue(":");
            fila4.createCell(2).setCellValue(entregaPredio.getDescripcionInmueble());
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 9));

            Row fila5 = sheet.createRow(5);
            fila5.createCell(0).setCellValue("Bloque");
            fila5.createCell(1).setCellValue(":");
            fila5.createCell(2).setCellValue(entregaPredio.getDescripcionBloque());
            sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 9));

            Row fila6 = sheet.createRow(6);
            fila6.createCell(0).setCellValue("Predio");
            fila6.createCell(1).setCellValue(":");
            fila6.createCell(2).setCellValue(entregaPredio.getDescripcionPredio());
            sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 9));

            Row fila7 = sheet.createRow(7);
            fila7.createCell(0).setCellValue("De");
            fila7.createCell(1).setCellValue(":");
            fila7.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 9));

            Row fila8 = sheet.createRow(8);
            fila8.createCell(0).setCellValue("A");
            fila8.createCell(1).setCellValue(":");
            fila8.createCell(2).setCellValue(entregaPredio.getNombreArrendatario());
            sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila9 = sheet.createRow(9);
            fila9.createCell(0).setCellValue("Fecha");
            fila9.createCell(1).setCellValue(":");
            fila9.createCell(2).setCellValue(UConvierteFecha.formatearFecha(entregaPredio.getFechaInicio()));
            sheet.addMergedRegion(new CellRangeAddress(9, 9, 2, 9));

            Row fila11 = sheet.createRow(11);
            fila11.createCell(0).setCellValue("Condiciones Fisicas de las instalaciones");
            sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 9));


            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idContrato", entregaPredio.getIdContrato());
            parametrosConsulta.put("idDetalleInmuPredio", entregaPredio.getIdDetalleInmuPredio());
            parametrosConsulta.put("idEntregaPredio", entregaPredio.getIdEntregaPredio());
            parametrosConsulta.put("pagina", 1);
            parametrosConsulta.put("paginacion", 10);


            List<AmbienteContrato> listaAmbienteContrato = obtenerListaAmbienteContrato(parametrosConsulta);

            int fila = 13;

            for (AmbienteContrato datosAmbienteContrato : listaAmbienteContrato) {

                Row filaAmbiente = sheet.createRow(fila);

                Cell celdaBloque = filaAmbiente.createCell(2);
                if (datosAmbienteContrato.getNombreAmbiente() != null) {
                    celdaBloque.setCellValue(datosAmbienteContrato.getNombreAmbiente());
                } else {
                    celdaBloque.setCellValue("");
                }

                fila++;

                Row filaCabeceraTabla = sheet.createRow(fila);

                Cell celdaCabecera0 = filaCabeceraTabla.createCell(0);
                celdaCabecera0.setCellStyle(estiloCelda);

                Cell celdaCabecera1 = filaCabeceraTabla.createCell(1);
                celdaCabecera1.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 2, 0, 1));
                celdaCabecera1.setCellStyle(estiloCelda);

                Cell celdaCabecera2 = filaCabeceraTabla.createCell(2);
                celdaCabecera2.setCellValue("Descripción");
                celdaCabecera2.getSheet().addMergedRegion(new CellRangeAddress(fila, fila + 2, 2, 3));
                celdaCabecera2.setCellStyle(estiloCelda);


                Cell celdaCabecera3 = filaCabeceraTabla.createCell(3);
                celdaCabecera3.setCellStyle(estiloCelda);

                Cell celdaCabecera4 = filaCabeceraTabla.createCell(4);
                celdaCabecera4.setCellValue("Entrega");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 4, 7));
                celdaCabecera4.setCellStyle(estiloCelda);

                Cell celdaCabecera5 = filaCabeceraTabla.createCell(5);
                celdaCabecera5.setCellStyle(estiloCelda);
                Cell celdaCabecera6 = filaCabeceraTabla.createCell(6);
                celdaCabecera6.setCellStyle(estiloCelda);
                Cell celdaCabecera7 = filaCabeceraTabla.createCell(7);
                celdaCabecera7.setCellStyle(estiloCelda);

                Cell celdaCabecera8 = filaCabeceraTabla.createCell(8);
                celdaCabecera8.setCellValue("Observaciones");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 2, 8, 9));
                celdaCabecera8.setCellStyle(estiloCelda);

                Cell celdaCabecera9 = filaCabeceraTabla.createCell(9);
                celdaCabecera9.setCellStyle(estiloCelda);

                fila++;
                Row filaCabeceraTabla2 = sheet.createRow(fila);

                Cell celdaCabecera01 = filaCabeceraTabla.createCell(0);
                celdaCabecera01.setCellStyle(estiloCelda);

                Cell celdaCabecera02 = filaCabeceraTabla.createCell(1);
                celdaCabecera02.setCellStyle(estiloCelda);

                Cell celdaCabecera03 = filaCabeceraTabla2.createCell(2);
                celdaCabecera03.setCellStyle(estiloCelda);
                Cell celdaCabecera04 = filaCabeceraTabla2.createCell(3);
                celdaCabecera04.setCellStyle(estiloCelda);

                Cell celdaCabecera05 = filaCabeceraTabla2.createCell(4);
                celdaCabecera05.setCellValue("Cantidad");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 1, 4, 4));
                celdaCabecera05.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera06 = filaCabeceraTabla2.createCell(5);
                celdaCabecera06.setCellValue("Estado");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 5, 7));
                celdaCabecera06.setCellStyle(estiloCelda);

                Cell celdaCabecera07 = filaCabeceraTabla2.createCell(6);
                celdaCabecera07.setCellStyle(estiloCelda);
                Cell celdaCabecera08 = filaCabeceraTabla2.createCell(7);
                celdaCabecera08.setCellStyle(estiloCelda);

                Cell celdaCabecera09 = filaCabeceraTabla2.createCell(8);
                celdaCabecera09.setCellStyle(estiloCelda);
                Cell celdaCabecera10 = filaCabeceraTabla2.createCell(9);
                celdaCabecera10.setCellStyle(estiloCelda);

                fila++;
                Row filaCabeceraTabla3 = sheet.createRow(fila);

                Cell celdaCabecera001 = filaCabeceraTabla3.createCell(0);
                celdaCabecera001.setCellStyle(estiloCelda);
                Cell celdaCabecera002 = filaCabeceraTabla3.createCell(1);
                celdaCabecera002.setCellStyle(estiloCelda);

                Cell celdaCabecera003 = filaCabeceraTabla3.createCell(2);
                celdaCabecera003.setCellStyle(estiloCelda);
                Cell celdaCabecera004 = filaCabeceraTabla3.createCell(3);
                celdaCabecera004.setCellStyle(estiloCelda);

                Cell celdaCabecera005 = filaCabeceraTabla3.createCell(4);
                celdaCabecera005.setCellStyle(estiloCelda);

                Cell celdaCabecera006 = filaCabeceraTabla3.createCell(5);
                celdaCabecera006.setCellValue("Bueno");
                celdaCabecera006.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera007 = filaCabeceraTabla3.createCell(6);
                celdaCabecera007.setCellValue("Regular");
                celdaCabecera007.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera008 = filaCabeceraTabla3.createCell(7);
                celdaCabecera008.setCellValue("Malo");
                celdaCabecera008.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera009 = filaCabeceraTabla3.createCell(8);
                celdaCabecera009.setCellStyle(estiloCelda);
                Cell celdaCabecera010 = filaCabeceraTabla3.createCell(9);
                celdaCabecera010.setCellStyle(estiloCelda);


                sheet.setColumnWidth(0, 2800);
                sheet.setColumnWidth(1, 500);
                sheet.setColumnWidth(2, 8000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 900);
                sheet.setColumnWidth(5, 900);
                sheet.setColumnWidth(6, 900);
                sheet.setColumnWidth(7, 900);
                sheet.setColumnWidth(8, 3000);
                sheet.setColumnWidth(9, 3000);

                fila++;
                Map<String, Object> parametrosConsultaAmbiente = new HashMap<>();
                parametrosConsultaAmbiente.put("idAmbiente", datosAmbienteContrato.getIdDetalleAmbientePredio());
                List<AmbienteInfraestructura> listaAmbienteInfraestructura = ejbEntregaPredio.obtenerInfraestruturaPorAmbiente(parametrosConsultaAmbiente);

                for (AmbienteInfraestructura datosAmbienteInfraestructura : listaAmbienteInfraestructura) {

                    Row filaCabeceraDetalle = sheet.createRow(fila);

                    Cell celdaCabeceraDet0 = filaCabeceraDetalle.createCell(2);
                    celdaCabeceraDet0.setCellValue(datosAmbienteInfraestructura.getInfraestructura());
                    celdaCabeceraDet0.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 2, 3));
                    celdaCabeceraDet0.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet1 = filaCabeceraDetalle.createCell(3);
                    celdaCabeceraDet1.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet2 = filaCabeceraDetalle.createCell(4);
                    celdaCabeceraDet2.setCellValue(datosAmbienteInfraestructura.getCantidad());
                    celdaCabeceraDet2.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet3 = filaCabeceraDetalle.createCell(5);
                    if (datosAmbienteInfraestructura.getEstado().equals("BUENO")) {
                        celdaCabeceraDet3.setCellValue("X");
                    } else {
                        celdaCabeceraDet3.setCellValue("");
                    }
                    celdaCabeceraDet3.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet4 = filaCabeceraDetalle.createCell(6);
                    if (datosAmbienteInfraestructura.getEstado().equals("REGULAR")) {
                        celdaCabeceraDet4.setCellValue("X");
                    } else {
                        celdaCabeceraDet4.setCellValue("");
                    }
                    celdaCabeceraDet4.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet5 = filaCabeceraDetalle.createCell(7);
                    if (datosAmbienteInfraestructura.getEstado().equals("MALO")) {
                        celdaCabeceraDet5.setCellValue("X");
                    } else {
                        celdaCabeceraDet5.setCellValue("");
                    }
                    celdaCabeceraDet5.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet6 = filaCabeceraDetalle.createCell(8);
                    celdaCabeceraDet6.setCellValue(datosAmbienteInfraestructura.getObservacion());
                    celdaCabeceraDet1.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 8, 9));
                    celdaCabeceraDet6.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet7 = filaCabeceraDetalle.createCell(9);
                    celdaCabeceraDet7.setCellStyle(estiloCeldaDe);

                    fila++;
                }

                fila++;
                fila++;

                Row filaCabeceraTabla4 = sheet.createRow(fila);
                Cell celdaCabecera110 = filaCabeceraTabla4.createCell(2);
                celdaCabecera110.setCellValue("Observaciones:");
                celdaCabecera110.setCellStyle(estiloCeldaNegrita);

                fila++;
                Row filaCabeceraTabla5 = sheet.createRow(fila);
                Cell celdaCabeceras0012 = filaCabeceraTabla5.createCell(2);
                celdaCabeceras0012.setCellValue(datosAmbienteContrato.getObservacionAmbiente());
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 2, 9));
                celdaCabeceras0012.setCellStyle(estiloCelda);

                Cell celdaCabecera11 = filaCabeceraTabla5.createCell(3);
                celdaCabecera11.setCellStyle(estiloCelda);
                Cell celdaCabecera12 = filaCabeceraTabla5.createCell(4);
                celdaCabecera12.setCellStyle(estiloCelda);
                Cell celdaCabecera13 = filaCabeceraTabla5.createCell(5);
                celdaCabecera13.setCellStyle(estiloCelda);
                Cell celdaCabecera14 = filaCabeceraTabla5.createCell(6);
                celdaCabecera14.setCellStyle(estiloCelda);
                Cell celdaCabecera15 = filaCabeceraTabla5.createCell(7);
                celdaCabecera15.setCellStyle(estiloCelda);
                Cell celdaCabecera16 = filaCabeceraTabla5.createCell(8);
                celdaCabecera16.setCellStyle(estiloCelda);
                Cell celdaCabecera17 = filaCabeceraTabla5.createCell(9);
                celdaCabecera17.setCellStyle(estiloCelda);


                fila++;
                fila++;
            }

            //CABECERA
            fila++;


            Row filaFinReporte = sheet.createRow(fila);
            Cell celdaFinReporte = filaFinReporte.createCell(0);
            celdaFinReporte.setCellValue("___________________________________");


            Cell celdaFinReporte1 = filaFinReporte.createCell(4);
            celdaFinReporte1.setCellValue("______________________________________");

            fila++;
            Row filaFinReporte1 = sheet.createRow(fila);
            Cell celdaFinReporte2 = filaFinReporte1.createCell(2);
            celdaFinReporte2.setCellValue("ARRENDADOR");


            Cell celdaFinReporte3 = filaFinReporte1.createCell(6);
            celdaFinReporte3.setCellValue("ARRENDATARIO");

            fila++;
            Row filaFinReporte2 = sheet.createRow(fila);
            Cell celdaFinReporte4 = filaFinReporte2.createCell(0);
            celdaFinReporte4.setCellValue("Oficina de Normalizacion Previsional - ONP");

            Cell celdaFinReporte5 = filaFinReporte2.createCell(5);
            celdaFinReporte5.setCellValue("Nombre : ");

            Cell celdaFinReporte661 = filaFinReporte2.createCell(8);
            celdaFinReporte661.setCellValue(entregaPredio.getNombreArrendatario());

            fila++;
            Row filaFinReporte3 = sheet.createRow(fila);
            Cell celdaFinReporte6 = filaFinReporte3.createCell(0);
            celdaFinReporte6.setCellValue("         Secretaría Técnica del FCR");

            Cell celdaFinReporte7 = filaFinReporte3.createCell(5);
            celdaFinReporte7.setCellValue("DNI : ");

            Cell celdaFinReporte771 = filaFinReporte3.createCell(7);
            celdaFinReporte771.setCellValue(entregaPredio.getNumeroDocumento());

            CellStyle estiloFilaFinReporte = workbook.createCellStyle();
            estiloFilaFinReporte.setFont(font);
            estiloFilaFinReporte.setAlignment(HorizontalAlignment.CENTER_SELECTION);


            XSSFSheet sheet2 = workbook.createSheet("Fotos");

            Row fila21 = sheet2.createRow(0);

            Cell celdas1 = fila21.createCell(0);
            celdas1.setCellValue("OFICINA DE NORMALIZACIÓN PREVISIONAL - ONP");
            celdas1.setCellStyle(estiloCabe);
            Cell celdas2 = fila21.createCell(1);
            celdas2.setCellStyle(estiloCabe);
            Cell celdas3 = fila21.createCell(2);
            celdas3.setCellStyle(estiloCabe);
            Cell celdas4 = fila21.createCell(3);
            celdas4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN:");
            celdas4.setCellStyle(estiloCabe);
            Cell celdas5 = fila21.createCell(4);
            celdas5.setCellStyle(estiloCabe);
            Cell celdas6 = fila21.createCell(5);
            celdas6.setCellStyle(estiloCabe);
            Cell celdas7 = fila21.createCell(6);
            celdas7.setCellStyle(estiloCabe);
            Cell celdas8 = fila21.createCell(7);
            celdas8.setCellStyle(estiloCabe);
            Cell celdas9 = fila21.createCell(8);
            celdas9.setCellValue("FECHA:");
            celdas9.setCellStyle(estiloCabe);
            Cell celdas10 = fila21.createCell(9);
            celdas10.setCellValue(UConvierteFecha.formatearFecha(new Date()));
            celdas10.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            sheet2.addMergedRegion(new CellRangeAddress(0, 0, 3, 7));


            Row fila22 = sheet2.createRow(1);

            Cell celdas11 = fila22.createCell(0);
            celdas11.setCellStyle(estiloCabe);
            Cell celdas12 = fila22.createCell(1);
            celdas12.setCellStyle(estiloCabe);
            Cell celdas13 = fila22.createCell(2);
            celdas13.setCellStyle(estiloCabe);
            Cell celdas14 = fila22.createCell(3);
            celdas14.setCellValue("ACTA ENTREGA");
            celdas14.setCellStyle(estiloCabeFont);
            Cell celdas15 = fila22.createCell(4);
            celdas15.setCellStyle(estiloCabe);
            Cell celdas16 = fila22.createCell(5);
            celdas16.setCellStyle(estiloCabe);
            Cell celdas17 = fila22.createCell(6);
            celdas17.setCellStyle(estiloCabe);
            Cell celdas18 = fila22.createCell(7);
            celdas18.setCellStyle(estiloCabe);
            Cell celdas19 = fila22.createCell(8);
            celdas19.setCellValue("PAGINA:");
            celdas19.setCellStyle(estiloCabe);
            Cell celdas20 = fila22.createCell(9);
            celdas20.setCellValue("1");
            celdas20.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet2.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            sheet2.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));


            Row fila33 = sheet2.createRow(2);

            Cell celdas21 = fila33.createCell(0);
            celdas21.setCellStyle(estiloCabe);
            Cell celdas22 = fila33.createCell(1);
            celdas22.setCellStyle(estiloCabe);
            Cell celdas23 = fila33.createCell(2);
            celdas23.setCellStyle(estiloCabe);
            Cell celdas24 = fila33.createCell(3);
            celdas24.setCellValue("Contrato de Arrendamiento N°");
            celdas24.setCellStyle(estiloCabe);
            Cell celdas25 = fila33.createCell(4);
            celdas25.setCellStyle(estiloCabe);
            Cell celdas26 = fila33.createCell(5);
            celdas26.setCellValue(entregaPredio.getIdContrato());
            celdas26.setCellStyle(estiloCabe);
            Cell celdas27 = fila33.createCell(6);
            celdas27.setCellStyle(estiloCabe);
            Cell celdas28 = fila33.createCell(7);
            celdas28.setCellStyle(estiloCabe);
            Cell celdas29 = fila33.createCell(8);
            celdas29.setCellStyle(estiloCabe);
            Cell celdas30 = fila33.createCell(9);
            celdas30.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
            sheet2.addMergedRegion(new CellRangeAddress(2, 2, 5, 7));

            Row fila44 = sheet2.createRow(4);
            fila44.createCell(0).setCellValue("Edificio");
            fila44.createCell(1).setCellValue(":");
            fila44.createCell(2).setCellValue(entregaPredio.getDescripcionInmueble());
            sheet2.addMergedRegion(new CellRangeAddress(4, 4, 2, 9));

            Row fila55 = sheet2.createRow(5);
            fila55.createCell(0).setCellValue("Bloque");
            fila55.createCell(1).setCellValue(":");
            fila55.createCell(2).setCellValue(entregaPredio.getDescripcionBloque());
            sheet2.addMergedRegion(new CellRangeAddress(5, 5, 2, 9));

            Row fila66 = sheet2.createRow(6);
            fila66.createCell(0).setCellValue("Predio");
            fila66.createCell(1).setCellValue(":");
            fila66.createCell(2).setCellValue(entregaPredio.getDescripcionPredio());
            sheet2.addMergedRegion(new CellRangeAddress(6, 6, 2, 9));

            Row fila77 = sheet2.createRow(7);
            fila77.createCell(0).setCellValue("De");
            fila77.createCell(1).setCellValue(":");
            fila77.createCell(2).setCellValue(entregaPredio.getNombreArrendatario());
            sheet2.addMergedRegion(new CellRangeAddress(7, 7, 2, 9));

            Row fila88 = sheet2.createRow(8);
            fila88.createCell(0).setCellValue("A");
            fila88.createCell(1).setCellValue(":");
            fila88.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet2.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila99 = sheet2.createRow(9);
            fila99.createCell(0).setCellValue("Fecha");
            fila99.createCell(1).setCellValue(":");
            fila99.createCell(2).setCellValue(UConvierteFecha.formatearFecha(entregaPredio.getFechaInicio()));
            sheet2.addMergedRegion(new CellRangeAddress(9, 9, 2, 9));

            Row fila111 = sheet2.createRow(11);
            fila111.createCell(0).setCellValue("REGISTRO FOTOGRAFICO");
            sheet2.addMergedRegion(new CellRangeAddress(11, 11, 0, 9));


            sheet2.setColumnWidth(0, 2800);
            sheet2.setColumnWidth(1, 500);
            sheet2.setColumnWidth(2, 8000);
            sheet2.setColumnWidth(3, 8000);
            sheet2.setColumnWidth(4, 900);
            sheet2.setColumnWidth(5, 900);
            sheet2.setColumnWidth(6, 900);
            sheet2.setColumnWidth(7, 900);
            sheet2.setColumnWidth(8, 3000);
            sheet2.setColumnWidth(9, 3000);

            ByteArrayOutputStream arregloBytes = new ByteArrayOutputStream();
            workbook.write(arregloBytes);
            workbook.close();

            return arregloBytes;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar la entrega de predio asignado segun el código
     *
     * @param codigoContrato código de la entrega de predio a consultar, tipo String.
     * @return datos del entregaPredio, tipo EntregaPredio.
     * @throws Exception excepción generada en caso de error.
     */
    public EntregaPredio obtenerResponsableAsignado(String codigoContrato) throws Exception {
        try {
            return ejbEntregaPredio.obtenerResponsableAsignado(codigoContrato);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite modificar datos del proveedor.
     *
     * @param entregaPredio datos del proveedor a modificar, tipo Proveedor.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void actualizarResponsable(EntregaPredio entregaPredio) throws Exception {
        try {
            ejbEntregaPredio.actualizarResponsable(entregaPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Metodo que permite guardar los documentos de la adenda.
     *
     * @param registroDocumentoActa documentos de la adenda, tipo AdendaDocumento.
     * @param archivo               documento adjunto, tipo Archivo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void guardarDocumentoActa(EntregaPredio registroDocumentoActa, Archivo archivo) throws Exception {
        try {
            /**
             {//Crear Carpeta /NSAI/ACTA/{ID_CONTRATO}
             CarpetaFilenet carpetaInmuebleFileNet=new CarpetaFilenet();
             carpetaInmuebleFileNet.setClaseCarpeta(DirectorioTaxonomia.CLASE_CARPETA_ACTA);
             carpetaInmuebleFileNet.setNombreNuevaCarpeta(registroDocumentoActa.getIdContrato());
             carpetaInmuebleFileNet.setRutaCarpetaPadre(DirectorioTaxonomia.RUTA_CONTRATO);
             Map<String,Object> metadata=new HashMap<>();
             metadata.put(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO, registroDocumentoActa.getIdContrato());
             carpetaInmuebleFileNet.setMetadata(metadata);
             carpetasFilenet_0=carpetaInmuebleFileNet;
             }

             {//Crear Carpeta /NSAI/ACTA/{ID_CONTRATO}/CONTRATO
             CarpetaFilenet carpetaDocumentosGeneralesFileNet=new CarpetaFilenet();
             carpetaDocumentosGeneralesFileNet.setClaseCarpeta(null);
             carpetaDocumentosGeneralesFileNet.setNombreNuevaCarpeta(DirectorioTaxonomia.CLASE_CARPETA_DOCUMENTO);
             carpetaDocumentosGeneralesFileNet.setRutaCarpetaPadre(DirectorioTaxonomia.transformarRuta(DirectorioTaxonomia.RUTA_ID_CONTRATO, DirectorioTaxonomia.PATRON_ID_CONTRATO, registroDocumentoActa.getIdContrato()));
             carpetasFilenet_1=carpetaDocumentosGeneralesFileNet;

             }*/

            // /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}/DOCUMENTOS_GENERALES/

            DocumentoFilenet documentoFilenet = new DocumentoFilenet();
            AuditoriaFilenet auditoriaFilenet = new AuditoriaFilenet();
            ArchivoFileNet archivoFilenet = new ArchivoFileNet();
            PropertiesFilenet propiedades_0 = new PropertiesFilenet();
            PropertiesFilenet propiedades_1 = new PropertiesFilenet();
            CarpetaFilenet carpetasFilenet_0 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_1 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_2 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_3 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_4 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_5 = new CarpetaFilenet();
            PropertiesFilenet propiedades_1A = new PropertiesFilenet();
            PropertiesFilenet propiedades_4A = new PropertiesFilenet();

			/*
			//Carpeta /NSAI/CONTRATO
			carpetasFilenet_0.setOrden("0");
			carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
			carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_CONTRATO);
			carpetasFilenet_0.setCarpetaDocumental(null);
			carpetasFilenet_0.setCantidadPropiedades("0");
			carpetasFilenet_0.setProperties(null);

			//Carpeta /NSAI/CONTRATO/{ID_CONTRATO}
			carpetasFilenet_1.setOrden("1");
			carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_CONTRATO);
			carpetasFilenet_1.setCarpetaHija(registroDocumentoActa.getIdContrato());
			carpetasFilenet_1.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_CONTRATO);
			carpetasFilenet_1.setCantidadPropiedades("1");

			propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
			propiedades_1A.setValor(registroDocumentoActa.getIdContrato());
			propiedades_1A.setProperties(null);

			carpetasFilenet_1.setProperties(propiedades_1A);

			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA
			carpetasFilenet_2.setOrden("2");
			carpetasFilenet_2.setCarpetaPadre(registroDocumentoActa.getIdContrato());
			carpetasFilenet_2.setCarpetaHija(DirectorioTaxonomia.CARPETA_ACTA);
			carpetasFilenet_2.setCarpetaDocumental(null);
			carpetasFilenet_2.setCantidadPropiedades("0");
			carpetasFilenet_2.setProperties(null);

			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA
			carpetasFilenet_3.setOrden("3");
			carpetasFilenet_3.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ACTA);
			carpetasFilenet_3.setCarpetaHija(DirectorioTaxonomia.CARPETA_ENTREGA);
			carpetasFilenet_3.setCarpetaDocumental(null);
			carpetasFilenet_3.setCantidadPropiedades("0");
			carpetasFilenet_3.setProperties(null);

			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}
			carpetasFilenet_4.setOrden("4");
			carpetasFilenet_4.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ENTREGA);
			carpetasFilenet_4.setCarpetaHija(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));//?
			carpetasFilenet_4.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_ACTA_ENTREGA);
			carpetasFilenet_4.setCantidadPropiedades("1");

			propiedades_4A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_ACTA_ENTREGA);
			propiedades_4A.setValor(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));
			propiedades_4A.setProperties(null);

			carpetasFilenet_4.setProperties(propiedades_4A);

			// /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}/DOCUMENTOS_GENERALES/
			carpetasFilenet_5.setOrden("5");
			carpetasFilenet_5.setCarpetaPadre(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));
			carpetasFilenet_5.setCarpetaHija(DirectorioTaxonomia.CARPETA_DOCUMENTOS_ACTA_GENERALES);
			carpetasFilenet_5.setCarpetaDocumental(null);
			carpetasFilenet_5.setCantidadPropiedades("0");
			carpetasFilenet_5.setProperties(null);
			carpetasFilenet_5.setCarpeta(null);

			carpetasFilenet_4.setCarpeta(carpetasFilenet_5);
			carpetasFilenet_3.setCarpeta(carpetasFilenet_4);
			carpetasFilenet_2.setCarpeta(carpetasFilenet_3);
			carpetasFilenet_1.setCarpeta(carpetasFilenet_2);
			carpetasFilenet_0.setCarpeta(carpetasFilenet_1);

			auditoriaFilenet.setIdUsuario(registroDocumentoActa.getAuditoria().getUsuarioCreacion());
			auditoriaFilenet.setIpTerminal(registroDocumentoActa.getAuditoria().getTerminalCreacion());

			//archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_CONTRATO_ACTA);
			archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_DOCUMENTO_ACTA);
			archivoFilenet.setCantidadPropiedades("2");

			propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
			propiedades_0.setValor(registroDocumentoActa.getIdContrato());

			propiedades_1.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_ACTA_ENTREGA);
			propiedades_1.setValor(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));
			propiedades_1.setProperties(null);

			propiedades_0.setProperties(propiedades_1);

			archivoFilenet.setProperties(propiedades_0);
			archivoFilenet.setTitle(archivo.getNombre());
			archivoFilenet.setContentType(archivo.getTipoMime());

			archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

			documentoFilenet.setArchivo(archivoFilenet);
			documentoFilenet.setAuditoria(auditoriaFilenet);
			documentoFilenet.setCantidadCarpetas("6");
			documentoFilenet.setCarpeta(carpetasFilenet_0);
			*/

            /**
             ArchivoFileNet archivoFilenet=new ArchivoFileNet();
             archivoFilenet.setRutaCarpeta(DirectorioTaxonomia.transformarRuta(DirectorioTaxonomia.RUTA_ID_CONTRATO, DirectorioTaxonomia.PATRON_ID_CONTRATO, registroDocumentoActa.getIdContrato())
             +DirectorioTaxonomia.SEPARADOR_DIRECTORIO
             +DirectorioTaxonomia.transformarRuta(DirectorioTaxonomia.RUTA_ID_ADENDA,DirectorioTaxonomia.PATRON_ID_ADENDA, registroDocumentoActa.getIdContrato())
             +DirectorioTaxonomia.SEPARADOR_DIRECTORIO
             +DirectorioTaxonomia.CARPETA_DOCUMENTOS_ACTA_GENERALES);
             archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_DOCUMENTO_ACTA);
             archivoFilenet.setNombreArchivo(archivo.getNombre());

             Map<String,Object> metadata=new HashMap<>();
             metadata.put(TaxonomiaDocumentos.PROP_CODIGO_ADENDA,  registroDocumentoActa.getIdContrato());
             metadata.put(TaxonomiaDocumentos.PROP_NOMBRE_ARCHIVO_ADENDA, archivo.getNombre());
             metadata.put(TaxonomiaDocumentos.PROP_USUARIO_CREACION, registroDocumentoActa.getAuditoria().getUsuarioCreacion());
             metadata.put(TaxonomiaDocumentos.PROP_FECHA_CREACION, new Date());
             metadata.put(TaxonomiaDocumentos.PROP_TERMINAL_CREACION, registroDocumentoActa.getAuditoria().getTerminalCreacion());

             archivoFilenet.setMetadata(metadata);
             archivoFilenet.setArchivo(archivo.getArchivo());*/


            {//Crear carpeta /NSAI/FILENET_DOCUMENTOS
                carpetasFilenet_0.setOrden("0");
                carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
                carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_FILENET_DOCUMENTOS);
                carpetasFilenet_0.setCarpetaDocumental(null);
                carpetasFilenet_0.setCantidadPropiedades("0");
                carpetasFilenet_0.setProperties(null);

            }
            auditoriaFilenet.setIdUsuario(registroDocumentoActa.getAuditoria().getUsuarioCreacion());
            auditoriaFilenet.setIpTerminal(registroDocumentoActa.getAuditoria().getTerminalCreacion());

            archivoFilenet.setClaseDocumental(DirectorioTaxonomia.CLASE_DOCUMENTO_FILENET_DOCUMENTO);
            archivoFilenet.setCantidadPropiedades("0");

            archivoFilenet.setTitle(archivo.getNombre());
            archivoFilenet.setContentType(archivo.getTipoMime());

            archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

            documentoFilenet.setArchivo(archivoFilenet);
            documentoFilenet.setAuditoria(auditoriaFilenet);
            documentoFilenet.setCantidadCarpetas("1");
            documentoFilenet.setCarpeta(carpetasFilenet_0);

            //ejbEntregaPredio.registrarDocumentoActa(registroDocumentoActa,carpetasFilenet,archivoFilenet);
            registroDocumentoActa.setNombreArchivoDocumento(archivo.getNombre());
            ejbEntregaPredio.registrarDocumentoActaFilenet(registroDocumentoActa, documentoFilenet);


        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener el documento adjunto.
     *
     * @param documento bean con los datos de búsqueda del documento, tipo InmuebleDocumento.
     * @return objeto con los datos del documento, tipo Archivo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Archivo obtenerDocumentoAdjunto(EntregaPredio documento) throws Exception {
        /**try {
         String uuid=ejbEntregaPredio.consultarIdDocumentoAdjunto(documento);
         ESFilenet filenet=(ESFilenet)localizadorServicio.obtenerServicioFilenet(ServicioRemotoFilenet.FILENET);
         filenet.conectarFilenet();
         ArchivoFileNet archivoFnet=filenet.obtenerArchivo(uuid);
         return new Archivo(archivoFnet.getNombreArchivo(),archivoFnet.getArchivo());
         }catch(Exception excepcion){
         throw new Exception(excepcion);
         }*/
        try {
            String uuid = ejbEntregaPredio.consultarIdDocumentoAdjunto(documento);
            if (uuid == null) {
                return null;
            } else {
                ResultadoBusquedaDocumento resultadoBusquedaDocumento = UServicios.obtenerDocumento(uuid, DirectorioTaxonomia.CLASE_DOCUMENTO_FILENET_DOCUMENTO);

                if (resultadoBusquedaDocumento != null) {
                    if (resultadoBusquedaDocumento.getResultado() != null) {
                        if (resultadoBusquedaDocumento.getResultado().getCodigo().equals("0000")) {
                            return new Archivo(resultadoBusquedaDocumento.getArchivo().getTitle(), Base64.decode(resultadoBusquedaDocumento.getArchivo().getBinaryBase64()));
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<EntregaPredio> obtenerReporteSeguimiento(Map<String, Object> parametros) throws Exception {
        List<EntregaPredio> listaEntregaPredio;
        try {
            listaEntregaPredio = ejbEntregaPredio.obtenerReporteSeguimiento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaEntregaPredio;
    }


    /**
     * Método que permite consultar los documentos del contrato
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaDocumentosContrato registros con los documentos del contrato, tipo List<ContratoDocumento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<EntregaPredioDocumento> obtenerDocumentosActa(Map<String, Object> parametros) throws Exception {
        List<EntregaPredioDocumento> listaDocumentosActa;
        try {
            listaDocumentosActa = ejbEntregaPredio.obtenerDocumentosActa(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaDocumentosActa;
    }

    /**
     * Metodo que permite eliminar los documentos del inmueble.
     *
     * @param eliminarDocumento documentos del inmueble, tipo InmuebleDocumento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarDocumento(EntregaPredioDocumento eliminarDocumento) throws Exception {
        try {
            if (eliminarDocumento.getIdActaDocumento() != 0) {
                ejbEntregaPredio.eliminarDocumento(eliminarDocumento);
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


}
