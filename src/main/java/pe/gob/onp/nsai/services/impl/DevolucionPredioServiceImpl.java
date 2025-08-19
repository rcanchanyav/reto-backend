/**
 * Resumen.
 * Objeto               :   DevolucionPredioDelegateEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para el mantenimiento de devolucion predio.
 * Fecha de Creación    :   23/03/2021
 * PE de Creación       :   INI-900
 * Autor                :   Omar Meza.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */

package pe.gob.onp.nsai.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.DevolucionPredioLocalDao;
import pe.gob.onp.nsai.dto.*;

import pe.gob.onp.nsai.services.DevolucionPredioService;
import pe.gob.onp.nsai.util.DirectorioTaxonomia;
import pe.gob.onp.nsai.util.UConvierteFecha;
import pe.gob.onp.nsai.util.UServicios;
@Service
public class DevolucionPredioServiceImpl implements DevolucionPredioService {

    /** Clase EJB para el mantenimiento de devolucion predio */

    private final DevolucionPredioLocalDao ejbDevolucionPredio;

    public DevolucionPredioServiceImpl(DevolucionPredioLocalDao ejbDevolucionPredio) {
        this.ejbDevolucionPredio = ejbDevolucionPredio;
    }

    /**
     * Método que permite realizar una consulta de entrega predio.
     *
     * @param idInmueble
     *            datos de la entrega de predio a buscar, tipo DevolucionPredio.
     * @param pagina
     *            pagina de busqueda, tipo int.
     * @param nroRegistros
     *            numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo
     *         ResultadoBusquedaMantenimiento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarDevolucionPredio(String idInmueble, String idInmuebleBloque,
                                                                    String idInmueblePredio, int pagina, int nroRegistros) throws Exception {
        try {

            int numeroTotalRegistros;
            List<DevolucionPredio> listaDevolucion;
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("idInmueble", idInmueble);
            parametros.put("idInmuebleBloque", idInmuebleBloque);
            parametros.put("idInmueblePredio", idInmueblePredio);
            parametros.put("pagina", pagina);
            parametros.put("paginacion", nroRegistros);
            numeroTotalRegistros = obtenerCantidadDevolucionPredio(parametros);
            listaDevolucion = obtenerListaDevolucionPredio(parametros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setListaDevolucionPredio(listaDevolucion);

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(numeroTotalRegistros);

            resultado.setPaginacion(paginacion);

            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar proveedores.
     *
     * @param parametrosConsulta
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaProveedores lista de registros con los datos del proveedor, tipo
     *         List<Proveedor>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<DevolucionPredio> obtenerListaDevolucionPredio(Map<String, Object> parametrosConsulta)
            throws Exception {
        try {
            return ejbDevolucionPredio
                    .obtenerListaDevolucionPredio(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de registros de proveedores según la
     * busqueda.
     *
     * @param parametros
     *            objeto con los datos de búsqueda del proveedor, tipo Proveedor.
     * @return cantidadListaProveedores número de registros de proveedores, tipo
     *         int.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadDevolucionPredio(Map<String, Object> parametros) throws Exception {
        try {
            return ejbDevolucionPredio.obtenerCantidadDevolucionPredio(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad total de registros del contrato
     * enviando como filtro los datos del arrendatario.
     *
     * @param parametros
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return cantidad de registros del contrato, tipo int.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    public int obtenerNumeroRegistrosFiltroArrendatario(Map<String, Object> parametros) throws Exception {
        try {
            return ejbDevolucionPredio.obtenerNumeroRegistrosFiltroArrendatario(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar el contrato teniendo como filtro los datos del
     * arrendatario.
     *
     * @param parametros
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaContrato lista de registros con los datos del contrato, tipo
     *         List<Contrato>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    public List<DevolucionPredio> consultarContratoFiltroArrendatario(Map<String, Object> parametros) throws Exception {
        List<DevolucionPredio> listaDevolucionPredio;
        try {
            listaDevolucionPredio = ejbDevolucionPredio.consultarContratoFiltroArrendatario(parametros);
            return listaDevolucionPredio;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite realizar una consulta de acta devolucion.
     *
     * @param devolucionPredioBusqueda
     *            datos de la acta devolucion a buscar, tipo DevolucionPredio.
     * @param pagina
     *            pagina de busqueda, tipo int.
     * @param nroRegistros
     *            numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo
     *         ResultadoBusquedaMantenimiento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarActaDevolucion(DevolucionPredio devolucionPredioBusqueda, int pagina,
                                                                  int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idInmueble", devolucionPredioBusqueda.getIdInmueble());
            parametrosConsulta.put("idInmueblePredio", devolucionPredioBusqueda.getIdInmueblePredio());
            parametrosConsulta.put("idInmuebleBloque", devolucionPredioBusqueda.getIdInmuebleBloque());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            int cantidadlistaDevolucionPredio = obtenerCantidadActaDevolucion(devolucionPredioBusqueda);
            List<DevolucionPredio> listaActaDevolucion = obtenerListaActaDevolucion(parametrosConsulta);
            // int cantidadlistaDevolucionPredio = listaDevolucionPredio.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadlistaDevolucionPredio);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaActaDevolucion(listaActaDevolucion);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite realizar una consulta de acta devolucion.
     *
     * @param devolucionPredioBusqueda
     *            datos de la acta devolucion a buscar, tipo DevolucionPredio.
     * @return resultado de la busqueda, tipo
     *         ResultadoBusquedaMantenimiento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarActaBase(DevolucionPredio devolucionPredioBusqueda)
            throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idContrato", devolucionPredioBusqueda.getIdContrato());
            parametrosConsulta.put("idDetalleInmuPredio", devolucionPredioBusqueda.getIdDetalleInmuPredio());

            List<ActaBase> listaActaBase = obtenerListaActaBase(parametrosConsulta);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setListaActaBAse(listaActaBase);

            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar proveedores.
     *
     * @param parametrosConsulta
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaProveedores lista de registros con los datos del proveedor, tipo
     *         List<Proveedor>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<ActaBase> obtenerListaActaBase(Map<String, Object> parametrosConsulta) throws Exception {
        try {
            return ejbDevolucionPredio.obtenerListaActaBase(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar acta base.
     *
     * @param parametrosConsulta
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaProveedores lista de registros con los datos del proveedor, tipo
     *         List<Proveedor>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<DevolucionPredio> obtenerListaActaDevolucion(Map<String, Object> parametrosConsulta) throws Exception {
        try {
            return ejbDevolucionPredio
                    .obtenerListaActaDevolucion(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de registros de proveedores según la
     * busqueda.
     *
     * @param devolucionPredioBusqueda
     *            objeto con los datos de búsqueda del proveedor, tipo Proveedor.
     * @return cantidadListaProveedores número de registros de proveedores, tipo
     *         int.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadActaDevolucion(DevolucionPredio devolucionPredioBusqueda) throws Exception {
        try {
            return ejbDevolucionPredio
                    .obtenerCantidadActaDevolucion(devolucionPredioBusqueda);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar datos del proveedor.
     *
     * @param devolucionPredio
     *            datos del proveedor a modificar, tipo Proveedor.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void asignarResponsable(DevolucionPredio devolucionPredio) throws Exception {
        try {
            ejbDevolucionPredio.asignarResponsable(devolucionPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite agregar observacion del predio
     *
     * @param DevolucionPredio
     *            Datos del observacion del predio, tipo DevolucionPredio.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionPredio(DevolucionPredio DevolucionPredio) throws Exception {
        try {
            ejbDevolucionPredio.agregarObservacionPredio(DevolucionPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite agregar observacion por infraestructura.
     *
     * @param ambienteInfraestructura
     *            datos de la infraestructura, tipo AmbienteInfraestructura.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionInfraestructura(AmbienteInfraestructura ambienteInfraestructura, int idAmbiente)
            throws Exception {
        try {
            ejbDevolucionPredio.agregarObservacionInfraestructura(ambienteInfraestructura, idAmbiente);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite agregar observacion por infraestructura.
     *
     * @param ambienteInfraestructura
     *            datos de la infraestructura, tipo AmbienteInfraestructura.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void actualizarInfraestructura(AmbienteInfraestructura ambienteInfraestructura, int idAmbiente)
            throws Exception {
        try {
            ejbDevolucionPredio.actualizarInfraestructura(ambienteInfraestructura, idAmbiente);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite agregar observacion por ambiente.
     *
     * @param listAmbienteContrato
     *            datos de la ambiente, tipo AmbienteContrato.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionAmbiente(List<AmbienteContrato> listAmbienteContrato,
                                           AmbienteContrato ambienteContrato) throws Exception {
        try {
            for (AmbienteContrato value : listAmbienteContrato) {
                value.setAuditoria(ambienteContrato.getAuditoria());
                value.setIdDevolucionPredio(ambienteContrato.getIdDevolucionPredio());
                if (value.getObservacionAmbiente() != null) {
                    if (value.getIdDetalleDevoPreAmbiente() != 0) {
                        ejbDevolucionPredio.actualizarObservacionAmbiente(value);
                    } else {
                        ejbDevolucionPredio.agregarObservacionAmbiente(value);
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
     * @return lista registros con los datos de responsable, tipo
     *         List<TipoResponsable>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<TipoResponsable> obtenerResponsables() throws Exception {
        try {
            return ejbDevolucionPredio.obtenerResponsables();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar la entrega de predio segun el código
     *
     * @param codigoDevolucionPredio
     *            código de la entrega de predio a consultar, tipo String.
     * @return datos del DevolucionPredio, tipo DevolucionPredio.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    public DevolucionPredio obtenerDevolucionPredio(String codigoDevolucionPredio) throws Exception {
        try {
            DevolucionPredio DevolucionPredio = ejbDevolucionPredio.obtenerDevolucionPredio(codigoDevolucionPredio);
            DevolucionPredio
                    .setDepartamentoInmueble(DevolucionPredio.getCodigoAreaGeografica().substring(0, 2) + "0000");
            DevolucionPredio.setProvinciaInmueble(DevolucionPredio.getCodigoAreaGeografica().substring(0, 4) + "00");
            DevolucionPredio.setDistritoInmueble(DevolucionPredio.getCodigoAreaGeografica());
            return DevolucionPredio;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar los datos del reporte acta entrega.
     *
     * @return lista registros con los datos de reporte acta entrega, tipo
     *         List<ReporteActaEntrega>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<ReporteActaEntrega> obtenerDatosReporteActaEntrega() throws Exception {
        try {
            return ejbDevolucionPredio.obtenerDatosReporteActaEntrega();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite realizar una consulta de entrega predio.
     *
     * @param DevolucionPredioBusqueda
     *            datos de la entrega de predio a buscar, tipo DevolucionPredio.
     * @param pagina
     *            pagina de busqueda, tipo int.
     * @param nroRegistros
     *            numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo
     *         ResultadoBusquedaMantenimiento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento obtenerAmbienteContrato(DevolucionPredio DevolucionPredioBusqueda, int pagina,
                                                                  int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idDevolucionPredio", DevolucionPredioBusqueda.getIdDevolucionPredio());
            parametrosConsulta.put("idDetalleInmuPredio", DevolucionPredioBusqueda.getIdDetalleInmuPredio());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            List<AmbienteContrato> listaAmbienteContrato = obtenerListaAmbienteContrato(parametrosConsulta);

            int cantidadlistaDevolucionPredio = listaAmbienteContrato.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadlistaDevolucionPredio);

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
     * @param parametrosConsulta
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de los ambientes por contrato, tipo
     *         List<AmbienteContrato>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<AmbienteContrato> obtenerListaAmbienteContrato(Map<String, Object> parametrosConsulta)
            throws Exception {
        try {
            return ejbDevolucionPredio
                    .obtenerAmbienteContrato(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener los datos de la infraestructura por ambiente.
     *
     * @param idAmbiente
     *            identificador del ambiente idAmbiente, tipo long.
     * @throws Exception
     *             excepción generada en caso de error.
     * @return bean con los datos de la infraestructura, tipo
     *         List<AmbienteInfraestructura>.
     */
    @Override
    public List<AmbienteInfraestructura> obtenerInfraestruturaPorAmbiente(long idAmbiente, String idDevolucionPredio)
            throws Exception {
        List<AmbienteInfraestructura> ambienteInfraestructura;
        try {

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idAmbiente", idAmbiente);
            parametrosConsulta.put("idDevolucionPredio", idDevolucionPredio);
            ambienteInfraestructura = ejbDevolucionPredio.obtenerInfraestruturaPorAmbiente(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return ambienteInfraestructura;
    }

    /**
     * Método que permite generar los datos del reporte de acta de entrega en pdf.
     *
     * @param listaReporteActaEntrega
     *            lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param filtroCodigoContrato
     *            filtro por código del contrato, tipo String.
     * @param context
     *            contexto del sistema, tipo ServletContext.
     * @param auditoria
     *            objeto de auditoria, tipo Auditoria.
     * @return bytes arreglo de bytes del archivo pdf generado, tipo byte[].
     * @throws Exception
     *             excepción generada en caso de error.
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
     * @param listaReporteActaEntrega
     *            lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param auditoria
     *            objeto de auditoria, tipo Auditoria.
     * @return arregloBytes arreglo de bytes del archivo excel generado, tipo
     *         ByteArrayOutputStream.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarReporteActaEntregaExcel(List<ReporteActaEntrega> listaReporteActaEntrega,
                                                                DevolucionPredio DevolucionPredio, Auditoria auditoria) throws Exception {
        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Devolucion");

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
            celda4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN:");
            celda4.setCellStyle(estiloCabe);
            Cell celda5 = fila1.createCell(4);
            celda5.setCellStyle(estiloCabe);
            Cell celda6 = fila1.createCell(5);
            celda6.setCellStyle(estiloCabe);
            Cell celda7 = fila1.createCell(6);
            celda7.setCellStyle(estiloCabe);
            Cell celda8 = fila1.createCell(7);
            celda8.setCellStyle(estiloCabe);
            Cell celda9 = fila1.createCell(8);
            celda9.setCellStyle(estiloCabe);
            Cell celda10 = fila1.createCell(9);
            celda10.setCellStyle(estiloCabe);
            Cell celda11 = fila1.createCell(10);
            celda11.setCellStyle(estiloCabe);
            Cell celda12 = fila1.createCell(11);
            celda12.setCellStyle(estiloCabe);

            Cell celda13 = fila1.createCell(12);
            celda13.setCellValue("FECHA:");
            celda13.setCellStyle(estiloCabe);
            Cell celda14 = fila1.createCell(13);
            celda14.setCellValue(UConvierteFecha.formatearFecha(new Date()));
            celda14.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 11));

            Row fila2 = sheet.createRow(1);

            Cell celda01 = fila2.createCell(0);
            celda01.setCellStyle(estiloCabe);
            Cell celda02 = fila2.createCell(1);
            celda02.setCellStyle(estiloCabe);
            Cell celda03 = fila2.createCell(2);
            celda03.setCellStyle(estiloCabe);
            Cell celda04 = fila2.createCell(3);
            celda04.setCellValue("ACTA DEVOLUCION");
            celda04.setCellStyle(estiloCabeFont);
            Cell celda05 = fila2.createCell(4);
            celda05.setCellStyle(estiloCabe);
            Cell celda06 = fila2.createCell(5);
            celda06.setCellStyle(estiloCabe);
            Cell celda07 = fila2.createCell(6);
            celda07.setCellStyle(estiloCabe);
            Cell celda08 = fila2.createCell(7);
            celda08.setCellStyle(estiloCabe);
            Cell celda09 = fila2.createCell(8);
            celda09.setCellStyle(estiloCabe);
            Cell celda010 = fila2.createCell(9);
            celda010.setCellStyle(estiloCabe);
            Cell celda011 = fila2.createCell(10);
            celda011.setCellStyle(estiloCabe);
            Cell celda012 = fila2.createCell(11);
            celda012.setCellStyle(estiloCabe);
            Cell celda013 = fila2.createCell(12);
            celda013.setCellValue("PAGINA:");
            celda013.setCellStyle(estiloCabe);
            Cell celda014 = fila2.createCell(13);
            celda014.setCellValue("1");
            celda014.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 11));

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));

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
            celda26.setCellStyle(estiloCabe);
            Cell celda27 = fila3.createCell(6);
            celda27.setCellStyle(estiloCabe);
            Cell celda28 = fila3.createCell(7);
            celda28.setCellStyle(estiloCabe);
            Cell celda29 = fila3.createCell(8);
            celda29.setCellStyle(estiloCabe);
            Cell celda30 = fila3.createCell(9);
            celda30.setCellValue(DevolucionPredio.getIdContrato());
            celda30.setCellStyle(estiloCabe);
            Cell celda31 = fila3.createCell(10);
            celda31.setCellStyle(estiloCabe);
            Cell celda32 = fila3.createCell(11);
            celda32.setCellStyle(estiloCabe);
            Cell celda33 = fila3.createCell(12);
            celda33.setCellStyle(estiloCabe);
            Cell celda34 = fila3.createCell(13);
            celda34.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 8));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 9, 11));

            Row fila4 = sheet.createRow(4);
            fila4.createCell(0).setCellValue("Edificio");
            fila4.createCell(1).setCellValue(":");
            fila4.createCell(2).setCellValue(DevolucionPredio.getDescripcionInmueble());
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 13));

            Row fila5 = sheet.createRow(5);
            fila5.createCell(0).setCellValue("Bloque");
            fila5.createCell(1).setCellValue(":");
            fila5.createCell(2).setCellValue(DevolucionPredio.getDescripcionBloque());
            sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 13));

            Row fila6 = sheet.createRow(6);
            fila6.createCell(0).setCellValue("Predio");
            fila6.createCell(1).setCellValue(":");
            fila6.createCell(2).setCellValue(DevolucionPredio.getDescripcionPredio());
            sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 13));

            Row fila7 = sheet.createRow(7);
            fila7.createCell(0).setCellValue("De");
            fila7.createCell(1).setCellValue(":");
            // fila7.createCell(2).setCellValue(DevolucionPredio.getDireccionInmueble());
            fila7.createCell(2).setCellValue(DevolucionPredio.getNombreArrendatario());
            sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 13));

            Row fila8 = sheet.createRow(8);
            fila8.createCell(0).setCellValue("A");
            fila8.createCell(1).setCellValue(":");
            fila8.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila9 = sheet.createRow(9);
            fila9.createCell(0).setCellValue("Fecha");
            fila9.createCell(1).setCellValue(":");
            fila9.createCell(2).setCellValue(UConvierteFecha.formatearFecha(DevolucionPredio.getFeAsignacion()));
            sheet.addMergedRegion(new CellRangeAddress(9, 9, 2, 13));

            Row fila11 = sheet.createRow(11);
            fila11.createCell(0).setCellValue("Condiciones Fisicas de las instalaciones");
            sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 13));

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idDevolucionPredio", DevolucionPredio.getIdDevolucionPredio());
            parametrosConsulta.put("idDetalleInmuPredio", DevolucionPredio.getIdDetalleInmuPredio());
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
                celdaCabecera8.setCellValue("Devolucion");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 8, 11));
                celdaCabecera8.setCellStyle(estiloCelda);

                Cell celdaCabecera9 = filaCabeceraTabla.createCell(9);
                celdaCabecera9.setCellStyle(estiloCelda);
                Cell celdaCabecera10 = filaCabeceraTabla.createCell(10);
                celdaCabecera10.setCellStyle(estiloCelda);
                Cell celdaCabecera11 = filaCabeceraTabla.createCell(11);
                celdaCabecera11.setCellStyle(estiloCelda);

                Cell celdaCabecera12 = filaCabeceraTabla.createCell(12);
                celdaCabecera12.setCellValue("Observaciones");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 2, 12, 13));
                celdaCabecera12.setCellStyle(estiloCelda);

                Cell celdaCabecera13 = filaCabeceraTabla.createCell(13);
                celdaCabecera13.setCellStyle(estiloCelda);

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
                celdaCabecera09.setCellValue("Cantidad");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 1, 8, 8));
                celdaCabecera09.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera010 = filaCabeceraTabla2.createCell(9);
                celdaCabecera010.setCellValue("Estado");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 9, 11));
                celdaCabecera010.setCellStyle(estiloCelda);

                Cell celdaCabecera011 = filaCabeceraTabla2.createCell(10);
                celdaCabecera011.setCellStyle(estiloCelda);
                Cell celdaCabecera012 = filaCabeceraTabla2.createCell(11);
                celdaCabecera012.setCellStyle(estiloCelda);

                Cell celdaCabecera013 = filaCabeceraTabla2.createCell(12);
                celdaCabecera013.setCellStyle(estiloCelda);
                Cell celdaCabecera014 = filaCabeceraTabla2.createCell(13);
                celdaCabecera014.setCellStyle(estiloCelda);

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

                Cell celdaCabecera0010 = filaCabeceraTabla3.createCell(9);
                celdaCabecera0010.setCellValue("Bueno");
                celdaCabecera0010.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera0011 = filaCabeceraTabla3.createCell(10);
                celdaCabecera0011.setCellValue("Regular");
                celdaCabecera0011.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera0012 = filaCabeceraTabla3.createCell(11);
                celdaCabecera0012.setCellValue("Malo");
                celdaCabecera0012.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera0013 = filaCabeceraTabla3.createCell(12);
                celdaCabecera0013.setCellStyle(estiloCelda);
                Cell celdaCabecera0014 = filaCabeceraTabla3.createCell(13);
                celdaCabecera0014.setCellStyle(estiloCelda);

                sheet.setColumnWidth(0, 2800);
                sheet.setColumnWidth(1, 500);
                sheet.setColumnWidth(2, 8000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 900);
                sheet.setColumnWidth(5, 900);
                sheet.setColumnWidth(6, 900);
                sheet.setColumnWidth(7, 900);
                sheet.setColumnWidth(8, 900);
                sheet.setColumnWidth(9, 900);
                sheet.setColumnWidth(10, 900);
                sheet.setColumnWidth(11, 900);
                sheet.setColumnWidth(12, 3000);
                sheet.setColumnWidth(13, 3000);

                fila++;
                Map<String, Object> parametrosConsultaAmbiente = new HashMap<>();
                parametrosConsultaAmbiente.put("idAmbiente", datosAmbienteContrato.getIdDetalleAmbientePredio());
                parametrosConsultaAmbiente.put("idDevolucionPredio", datosAmbienteContrato.getIdDevolucionPredio());

                List<AmbienteInfraestructura> listaAmbienteInfraestructura = ejbDevolucionPredio
                        .obtenerInfraestruturaPorAmbiente(parametrosConsultaAmbiente);

                for (AmbienteInfraestructura datosAmbienteInfraestructura : listaAmbienteInfraestructura) {

                    Row filaCabeceraDetalle = sheet.createRow(fila);

                    Cell celdaCabeceraDet0 = filaCabeceraDetalle.createCell(2);
                    celdaCabeceraDet0.setCellValue(datosAmbienteInfraestructura.getInfraestructura());
                    celdaCabeceraDet0.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 2, 3));
                    celdaCabeceraDet0.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet1 = filaCabeceraDetalle.createCell(3);
                    celdaCabeceraDet1.setCellStyle(estiloCeldaDe);

                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("idInfraestructura", datosAmbienteInfraestructura.getIdInfraestructura());

                    AmbienteInfraestructura ambiente = ejbDevolucionPredio.obtenerInfraestruturaPorTipo(parametros,
                            DevolucionPredio.getIdActaBase());

                    Cell celdaCabeceraDet2 = filaCabeceraDetalle.createCell(4);
                    celdaCabeceraDet2.setCellValue(ambiente.getCantidad());
                    celdaCabeceraDet2.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet3 = filaCabeceraDetalle.createCell(5);
                    if (ambiente.getEstado().equals("BUENO")) {
                        celdaCabeceraDet3.setCellValue("X");
                    } else {
                        celdaCabeceraDet3.setCellValue("");
                    }
                    celdaCabeceraDet3.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet4 = filaCabeceraDetalle.createCell(6);
                    if (ambiente.getEstado().equals("REGULAR")) {
                        celdaCabeceraDet4.setCellValue("X");
                    } else {
                        celdaCabeceraDet4.setCellValue("");
                    }
                    celdaCabeceraDet4.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet5 = filaCabeceraDetalle.createCell(7);
                    if (ambiente.getEstado().equals("MALO")) {
                        celdaCabeceraDet5.setCellValue("X");
                    } else {
                        celdaCabeceraDet5.setCellValue("");
                    }
                    celdaCabeceraDet5.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet6 = filaCabeceraDetalle.createCell(8);
                    celdaCabeceraDet6.setCellValue(datosAmbienteInfraestructura.getCantidad());
                    celdaCabeceraDet6.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet7 = filaCabeceraDetalle.createCell(9);
                    if (datosAmbienteInfraestructura.getEstado().equals("BUENO")) {
                        celdaCabeceraDet7.setCellValue("X");
                    } else {
                        celdaCabeceraDet7.setCellValue("");
                    }
                    celdaCabeceraDet7.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet8 = filaCabeceraDetalle.createCell(10);
                    if (datosAmbienteInfraestructura.getEstado().equals("REGULAR")) {
                        celdaCabeceraDet8.setCellValue("X");
                    } else {
                        celdaCabeceraDet8.setCellValue("");
                    }
                    celdaCabeceraDet8.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet9 = filaCabeceraDetalle.createCell(11);
                    if (datosAmbienteInfraestructura.getEstado().equals("MALO")) {
                        celdaCabeceraDet9.setCellValue("X");
                    } else {
                        celdaCabeceraDet9.setCellValue("");
                    }
                    celdaCabeceraDet9.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet10 = filaCabeceraDetalle.createCell(12);
                    celdaCabeceraDet10.setCellValue(datosAmbienteInfraestructura.getObservacion());
                    celdaCabeceraDet1.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 12, 13));
                    celdaCabeceraDet10.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet11 = filaCabeceraDetalle.createCell(13);
                    celdaCabeceraDet11.setCellStyle(estiloCeldaDe);

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
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 2, 13));
                celdaCabeceras0012.setCellStyle(estiloCeldaDe);

                Cell celdaCabeceras11 = filaCabeceraTabla5.createCell(3);
                celdaCabeceras11.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras12 = filaCabeceraTabla5.createCell(4);
                celdaCabeceras12.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras13 = filaCabeceraTabla5.createCell(5);
                celdaCabeceras13.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras14 = filaCabeceraTabla5.createCell(6);
                celdaCabeceras14.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras15 = filaCabeceraTabla5.createCell(7);
                celdaCabeceras15.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras16 = filaCabeceraTabla5.createCell(8);
                celdaCabeceras16.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras17 = filaCabeceraTabla5.createCell(9);
                celdaCabeceras17.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras18 = filaCabeceraTabla5.createCell(10);
                celdaCabeceras18.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras19 = filaCabeceraTabla5.createCell(11);
                celdaCabeceras19.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras20 = filaCabeceraTabla5.createCell(12);
                celdaCabeceras20.setCellStyle(estiloCeldaDe);
                Cell celdaCabeceras21 = filaCabeceraTabla5.createCell(13);
                celdaCabeceras21.setCellStyle(estiloCeldaDe);

                fila++;
                fila++;
            }

            // CABECERA
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

            Cell celdaFinReporte3 = filaFinReporte1.createCell(7);
            celdaFinReporte3.setCellValue("ARRENDATARIO");

            fila++;
            Row filaFinReporte2 = sheet.createRow(fila);
            Cell celdaFinReporte4 = filaFinReporte2.createCell(0);
            celdaFinReporte4.setCellValue("Oficina de Normalizacion Previsional - ONP");

            Cell celdaFinReporte5 = filaFinReporte2.createCell(5);
            celdaFinReporte5.setCellValue("Nombre : ");

            Cell celdaFinReporte661 = filaFinReporte2.createCell(8);
            celdaFinReporte661.setCellValue(DevolucionPredio.getNombreArrendatario());

            fila++;
            Row filaFinReporte3 = sheet.createRow(fila);
            Cell celdaFinReporte6 = filaFinReporte3.createCell(0);
            celdaFinReporte6.setCellValue("         Secretaría Técnica del FCR");

            Cell celdaFinReporte7 = filaFinReporte3.createCell(5);
            celdaFinReporte7.setCellValue("DNI : ");

            Cell celdaFinReporte771 = filaFinReporte3.createCell(7);
            celdaFinReporte771.setCellValue(DevolucionPredio.getNumeroDocumentoIdentidad());

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
            celdas14.setCellValue("ACTA DE DEVOLUCIÓN");
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
            celdas26.setCellValue(DevolucionPredio.getIdContrato());
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
            fila44.createCell(2).setCellValue(DevolucionPredio.getDescripcionInmueble());
            sheet2.addMergedRegion(new CellRangeAddress(4, 4, 2, 9));

            Row fila55 = sheet2.createRow(5);
            fila55.createCell(0).setCellValue("Bloque");
            fila55.createCell(1).setCellValue(":");
            fila55.createCell(2).setCellValue(DevolucionPredio.getDescripcionBloque());
            sheet2.addMergedRegion(new CellRangeAddress(5, 5, 2, 9));

            Row fila66 = sheet2.createRow(6);
            fila66.createCell(0).setCellValue("Predio");
            fila66.createCell(1).setCellValue(":");
            fila66.createCell(2).setCellValue(DevolucionPredio.getDescripcionPredio());
            sheet2.addMergedRegion(new CellRangeAddress(6, 6, 2, 9));

            Row fila77 = sheet2.createRow(7);
            fila77.createCell(0).setCellValue("De");
            fila77.createCell(1).setCellValue(":");
            fila77.createCell(2).setCellValue(DevolucionPredio.getNombreArrendatario());
            sheet2.addMergedRegion(new CellRangeAddress(7, 7, 2, 9));

            Row fila88 = sheet2.createRow(8);
            fila88.createCell(0).setCellValue("A");
            fila88.createCell(1).setCellValue(":");
            fila88.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet2.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila99 = sheet2.createRow(9);
            fila99.createCell(0).setCellValue("Fecha");
            fila99.createCell(1).setCellValue(":");
            fila99.createCell(2).setCellValue(UConvierteFecha.formatearFecha(DevolucionPredio.getFeAsignacion()));
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
     * @param codigoContrato
     *            código de la entrega de predio a consultar, tipo String.
     * @return datos del DevolucionPredio, tipo DevolucionPredio.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    public DevolucionPredio obtenerResponsableAsignado(String codigoContrato) throws Exception {
        try {
            return ejbDevolucionPredio.obtenerResponsableAsignado(codigoContrato);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite modificar datos del proveedor.
     *
     * @param devolucionPredio
     *            datos del proveedor a modificar, tipo Proveedor.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void actualizarResponsable(DevolucionPredio devolucionPredio) throws Exception {
        try {
            ejbDevolucionPredio.actualizarResponsable(devolucionPredio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public int obtenerCorrelativoDevolucion() throws Exception {
        try {
            return ejbDevolucionPredio.obtenerCorrelativoDevolucion();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Metodo que permite guardar los documentos de la adenda.
     *
     * @param registroDocumentoActa
     *            documentos de la adenda, tipo AdendaDocumento.
     * @param archivo
     *            documento adjunto, tipo Archivo.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void guardarDocumentoActa(DevolucionPredio registroDocumentoActa, Archivo archivo) throws Exception {
        try {

            int correlativo = obtenerCorrelativoDevolucion();
            registroDocumentoActa.setIdDetalleActaDocumento(correlativo);

            // CarpetaFilenet[] carpetasFilenet=new CarpetaFilenet[4];
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
            PropertiesFilenet propiedades_3A = new PropertiesFilenet();
            PropertiesFilenet propiedades_4A = new PropertiesFilenet();

			/*
			// Carpeta /NSAI/CONTRATO
			carpetasFilenet_0.setOrden("0");
			carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
			carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_CONTRATO);
			carpetasFilenet_0.setCarpetaDocumental(null);
			carpetasFilenet_0.setCantidadPropiedades("0");
			carpetasFilenet_0.setProperties(null);

			// Carpeta /NSAI/CONTRATO/{ID_CONTRATO}
			carpetasFilenet_1.setOrden("1");
			carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_CONTRATO);
			carpetasFilenet_1.setCarpetaHija(registroDocumentoActa.getIdContrato());
			carpetasFilenet_1.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_CONTRATO);
			carpetasFilenet_1.setCantidadPropiedades("1");

			propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
			propiedades_1A.setValor(registroDocumentoActa.getIdContrato());
			propiedades_1A.setProperties(null);

			carpetasFilenet_1.setProperties(propiedades_1A);

			// Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA
			carpetasFilenet_2.setOrden("2");
			carpetasFilenet_2.setCarpetaPadre(registroDocumentoActa.getIdContrato());
			carpetasFilenet_2.setCarpetaHija(DirectorioTaxonomia.CARPETA_ACTA);
			carpetasFilenet_2.setCarpetaDocumental(null);
			carpetasFilenet_2.setCantidadPropiedades("0");
			carpetasFilenet_2.setProperties(null);

			// Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/DEVOLUCION
			carpetasFilenet_3.setOrden("3");
			carpetasFilenet_3.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ACTA);
			carpetasFilenet_3.setCarpetaHija(DirectorioTaxonomia.CARPETA_DEVOLUCION);
			carpetasFilenet_3.setCarpetaDocumental(null);
			carpetasFilenet_3.setCantidadPropiedades("0");
			carpetasFilenet_3.setProperties(null);

			// Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/DEVOLUCION/{ID_DEVOLUCION}
			carpetasFilenet_4.setOrden("4");
			carpetasFilenet_4.setCarpetaPadre(DirectorioTaxonomia.CARPETA_DEVOLUCION);
			carpetasFilenet_4.setCarpetaHija(String.valueOf(registroDocumentoActa.getIdDetalleActaDocumento()));
			carpetasFilenet_4.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_DEVOLUCION);
			carpetasFilenet_4.setCantidadPropiedades("1");

			propiedades_4A.setCodigo(TaxonomiaDocumentos.PROP_ID_ACTA_DEVOLUCION);
			propiedades_4A.setValor(String.valueOf(registroDocumentoActa.getIdDetalleActaDocumento()));
			propiedades_4A.setProperties(null);

			carpetasFilenet_4.setProperties(propiedades_4A);

			// Crear Carpeta
			// /NSAI/CONTRATO/{ID_CONTRATO}/DEVOLUCION/{ID_DEVOLUCION}/DOCUMENTOS
			carpetasFilenet_5.setOrden("5");
			carpetasFilenet_5.setCarpetaPadre(String.valueOf(registroDocumentoActa.getIdDetalleActaDocumento()));
			carpetasFilenet_5.setCarpetaHija(DirectorioTaxonomia.CARPETA_DOCUMENTOS);
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

			archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_DOCUMENTO_ACTA);
			archivoFilenet.setCantidadPropiedades("2");

			propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
			propiedades_0.setValor(registroDocumentoActa.getIdContrato());

			propiedades_1.setCodigo(TaxonomiaDocumentos.PROP_ID_ACTA_DEVOLUCION);
			propiedades_1.setValor(String.valueOf(registroDocumentoActa.getIdDetalleActaDocumento()));
			propiedades_1.setProperties(null);

			propiedades_0.setProperties(propiedades_1);

			archivoFilenet.setProperties(propiedades_0);
			archivoFilenet.setTitle(archivo.getNombre());
			archivoFilenet.setContentType(archivo.getTipoMime());

			archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

			documentoFilenet.setArchivo(archivoFilenet);
			documentoFilenet.setAuditoria(auditoriaFilenet);
			documentoFilenet.setCantidadCarpetas("6");
			documentoFilenet.setCarpeta(carpetasFilenet_0);*/


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


            registroDocumentoActa.setNombreArchivoDocumento(archivo.getNombre());

            ejbDevolucionPredio.registrarDocumentoActa(registroDocumentoActa, documentoFilenet);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public Archivo obtenerArchivoAdjunto(DevolucionPredio registroDocumentoActa) throws Exception {
        try {
            String uuid = ejbDevolucionPredio.consultarIdArchivoAdjunto(registroDocumentoActa);
            if (uuid == null) {
                return null;
            } else {
                ResultadoBusquedaDocumento resultadoBusquedaDocumento = UServicios.obtenerDocumento(uuid,
                        DirectorioTaxonomia.CLASE_DOCUMENTO_FILENET_DOCUMENTO);

                if (resultadoBusquedaDocumento != null) {
                    if (resultadoBusquedaDocumento.getResultado() != null) {
                        if (resultadoBusquedaDocumento.getResultado().getCodigo().equals("0000")) {
                            return new Archivo(resultadoBusquedaDocumento.getArchivo().getTitle(),
                                    Base64.decode(resultadoBusquedaDocumento.getArchivo().getBinaryBase64()));
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
     * Método que permite consultar los documentos del contrato
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaDocumentosContrato registros con los documentos del contrato, tipo List<ContratoDocumento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<EntregaPredioDocumento> obtenerDocumentosActa(Map <String,Object> parametros)throws Exception {
        List<EntregaPredioDocumento> listaDocumentosActa;
        try {
            listaDocumentosActa = ejbDevolucionPredio.obtenerDocumentosActa(parametros);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
        return listaDocumentosActa;
    }

    /**
     * Metodo que permite eliminar los documentos del inmueble.
     * @param eliminarDocumento documentos del inmueble, tipo InmuebleDocumento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarDocumento(EntregaPredioDocumento eliminarDocumento)throws Exception {
        try {
            if(eliminarDocumento.getIdActaDocumento() != 0){
                ejbDevolucionPredio.eliminarDocumento(eliminarDocumento);
            }
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

}
