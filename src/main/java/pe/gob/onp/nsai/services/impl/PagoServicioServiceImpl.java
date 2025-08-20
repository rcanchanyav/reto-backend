package pe.gob.onp.nsai.services.impl;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.PagoServicioLocalDao;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.infrastructure.client.ServicioSparmag;
import pe.gob.onp.nsai.services.PagoServicioService;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PagoServicioServiceImpl implements PagoServicioService {

    /**
     * Clase EJB para mantenimiento del inmueble ESInmuebleLocal
     */

    private final PagoServicioLocalDao ejbPagoServicio;

    /**
     * Instancia a la interfaz ILocalizadorServicio.
     */
    private final ServicioSparmag servicioSparmag;

    public PagoServicioServiceImpl(PagoServicioLocalDao ejbPagoServicio, ServicioSparmag servicioSparmag) {
        this.ejbPagoServicio = ejbPagoServicio;
        this.servicioSparmag = servicioSparmag;
    }

    public ResultadoBusquedaMantenimiento consultaPagoServicio(PagoServicio pagoServicio, int pagina, int nroRegistros) throws Exception {

        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);
            parametrosConsulta.put("codigo", pagoServicio.getCodigo());
            parametrosConsulta.put("idInmueble", pagoServicio.getIdInmueble());
            parametrosConsulta.put("idInmuebleBloque", pagoServicio.getIdInmuebleBloque());
            parametrosConsulta.put("idInmueblePredio", pagoServicio.getIdInmueblePredio());
            parametrosConsulta.put("anio", pagoServicio.getAnio());
            parametrosConsulta.put("mes", pagoServicio.getMes());

            int cantidadRegistros = obtenerCantidadPagoServicio(pagoServicio);
            List<PagoServicio> listarPagoServicio = obtenerListaPagoServicio(parametrosConsulta);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPagoServicio(listarPagoServicio);
            return resultado;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de registros de pagos servicios.
     *
     * @param pagoServicio bean con los datos de búsqueda del pagosServicios, tipo pagosServicios.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadPagoServicio(PagoServicio pagoServicio) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPagoServicio.obtenerCantidadPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite consultar pago servicio
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PagoServicio> obtenerListaPagoServicio(Map<String, Object> parametros) throws Exception {
        List<PagoServicio> listPagoServicio;
        try {
            listPagoServicio = ejbPagoServicio.obtenerListaPagoServicio(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listPagoServicio;
    }

    /**
     * Método que permite guardar pago servicio
     *
     * @param pagoServicio Datos del pago servicio a guardar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void guardarPagoServicio(PagoServicio pagoServicio) throws Exception {
        try {
            ejbPagoServicio.registrarPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar un pago servicio
     *
     * @param pagoServicio Datos del pago servicio a modificar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarPagoServicio(PagoServicio pagoServicio) throws Exception {
        try {
            ejbPagoServicio.modificarPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite eliminar pago servicio según el id
     *
     * @param pagoServicio datos del pago servicio a eliminar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarPagoServicio(PagoServicio pagoServicio) throws Exception {
        try {
            ejbPagoServicio.eliminarPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar pago servicio.
     *
     * @param id identificador del contrato, tipo String.
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PagoServicio obtenerPagoServicio(Integer id) throws Exception {
        PagoServicio pagoServicio;
        try {
            pagoServicio = ejbPagoServicio.obtenerPagoServicio(id);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return pagoServicio;
    }

    /**
     * Método que permite consultar los suministros del predio por id.
     *
     * @param nuSuministro identificador del suministro del predio, tipo String.
     * @return suministroPredio datos del suministro del predio, tipo PredioSuministro.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PredioSuministro obtenerSuministroPredio(String nuSuministro, Integer tipo) throws Exception {
        PredioSuministro suministroPredio;
        try {

            suministroPredio = ejbPagoServicio.obtenerSuministroPredio(nuSuministro, tipo);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return suministroPredio;
    }

    /**
     * Método que permite obtener parametros del servico publico
     *
     * @return Lista de parametros, tipo List<CategoriaParametro>.
     */
    @Override
    public List<CategoriaParametro> listarServicioPublico() {
        try {
            return ejbPagoServicio.listarServicioPublico();
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return null;
    }


    @Override
    public List<CategoriaParametro> listarServicioEmpresa(String id) {
        try {
            System.out.println("Paso 2");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            return ejbPagoServicio.listarServicioEmpresa(parametros);
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return null;
    }


    public ResultadoBusquedaMantenimiento consultaPagoServicioPublico(PagoServicio pagoServicio, int pagina, int nroRegistros) throws Exception {

        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);
            parametrosConsulta.put("tipoServicio", pagoServicio.getTipoServicio());
            parametrosConsulta.put("proveedor", pagoServicio.getProveedor());

            int cantidadRegistros = obtenerCantidadPagoServicioPublico(pagoServicio);
            List<PagoServicio> listarPagoServicio = obtenerListaPagoServicioPublico(parametrosConsulta);
            Paginacion paginacion = new Paginacion();
            System.out.println("total =======> " + listarPagoServicio.size());
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPagoServicio(listarPagoServicio);
            return resultado;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de registros de pagos servicios publico.
     *
     * @param pagoServicio bean con los datos de búsqueda del pagosServicios, tipo pagosServicios.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadPagoServicioPublico(PagoServicio pagoServicio) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPagoServicio.obtenerCantidadPagoServicioPublico(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite consultar pago servicio publico
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PagoServicio> obtenerListaPagoServicioPublico(Map<String, Object> parametros) throws Exception {
        List<PagoServicio> listPagoServicio;
        try {
            listPagoServicio = ejbPagoServicio.obtenerListaPagoServicioPublico(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listPagoServicio;
    }

    /**
     * Método para obtener la lista de numero de suministro
     *
     * @param valor identificador del contrato, tipo String.
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PagoServicio obtenerInformaionBarra(String valor) throws Exception {
        PagoServicio pagoServicio;
        try {
            pagoServicio = ejbPagoServicio.obtenerInformaionBarra(valor);
            return pagoServicio;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite guardar tmp pago servicio
     *
     * @param pagoServicio Datos del pago servicio a guardar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void tmpPagoServicio(PagoServicio pagoServicio) throws Exception {
        try {
            ejbPagoServicio.tmpPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite validar si existe el suministro registrado
     *
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int validarProcesoRepetidos(PagoServicio pagoServicio) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPagoServicio.validarProcesoRepetidos(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    public ResultadoBusquedaMantenimiento tmpConsultaPagoServicio(int pagina, int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("pagina", pagina);
            parametros.put("paginacion", nroRegistros);
            int cantidadRegistros = tmpOtenerCantidadPagoServicio();
            List<PagoServicio> listarPagoServicio = tmpObtenerListaPagoServicio(parametros);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);
            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPagoServicio(listarPagoServicio);
            return resultado;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite obtener la cantidad de registros de tabla temporal pagos servicios.
     *
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int tmpOtenerCantidadPagoServicio() throws Exception {
        int cantidad;
        try {
            cantidad = ejbPagoServicio.tmpObtenerCantidadPagoServicio();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    /**
     * Método que permite consultar tabla temporal pago servicio
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PagoServicio> tmpObtenerListaPagoServicio(Map<String, Object> parametros) throws Exception {
        List<PagoServicio> listPagoServicio;
        try {
            listPagoServicio = ejbPagoServicio.tmpObtenerListaPagoServicio(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listPagoServicio;
    }


    /**
     * Método que permite eliminar temporal pago servicio según el id
     *
     * @param pagoServicio datos del pago servicio a eliminar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void retirarPagoServicio(PagoServicio pagoServicio) throws Exception {
        try {
            ejbPagoServicio.retirarPagoServicio(pagoServicio);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite validar si existe el suministro
     *
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int validarBusqueda(String sumistro) throws Exception {
        int cantidad;
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("numeroSuministro", sumistro);
            cantidad = ejbPagoServicio.validarBusqueda(parametro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;

    }

    @Override
    public ResultadoBusquedaMantenimiento consultaPagoServicioPredioDesocupado(PagoServicio pagoServicio, int pagina,
                                                                               int nroRegistros) throws Exception {

        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idServicio", pagoServicio.getCodigoTipoServ());
            parametrosConsulta.put("idInmueble", pagoServicio.getIdInmueble());
            parametrosConsulta.put("mes", pagoServicio.getMes());
            parametrosConsulta.put("anio", pagoServicio.getAnio());

            // int cantidadRegistros = obtenerCantidadPagoServicio(pagoServicio);
            List<GastoServicioPredio> listarPagoServicio = obtenerListaPagoServicioPredioDesocupado(parametrosConsulta);
            // Paginacion paginacion = new Paginacion();
            // paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            // resultado.setPaginacion(paginacion);
            resultado.setListaPagoServicioPredios(listarPagoServicio);
            return resultado;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar pago servicio de predio desocupado
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del
     * GastoServicioPredio, tipo List<GastoServicioPredio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<GastoServicioPredio> obtenerListaPagoServicioPredioDesocupado(Map<String, Object> parametros)
            throws Exception {
        List<GastoServicioPredio> listPagoServicio;
        try {
            listPagoServicio = ejbPagoServicio.obtenerListaPagoServicioPredioDesocupado(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listPagoServicio;
    }

    @Override
    public byte[] generarReporteExcelGastosServicios(ReporteGastoServicioPredio reporteGastos, ServletContext context)
            throws Exception {
        try {
            List<GastoServicioPredio> gastosAuxList = reporteGastos.getListaPagoServicioPrediosDesocupados().stream().filter(pago -> pago.getImprimir().equals("1")).collect(Collectors.toList());
            JRBeanCollectionDataSource jds = new JRBeanCollectionDataSource(obtenerListadoGastosServicioReporte(gastosAuxList));

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_OBSERVACION", reporteGastos.getObservacion());
            parametros.put("P_GASTOS", jds);

            String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosPdf.jasper");
            InputStream reportStream = new FileInputStream(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, new JREmptyDataSource());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setOnePagePerSheet(false);
            config.setDetectCellType(true);
            config.setIgnoreCellBorder(false);
            config.setCollapseRowSpan(false);
            exporter.setConfiguration(config);

            exporter.exportReport();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    @Override
    public byte[] generarReportePdfGastosServicios(ReporteGastoServicioPredio reporteGastos, ServletContext context)
            throws Exception {
        try {
            List<GastoServicioPredio> gastosAuxList = reporteGastos.getListaPagoServicioPrediosDesocupados().stream().filter(pago -> pago.getImprimir().equals("1")).collect(Collectors.toList());
            List<ReporteGastoServicioPredioDesocupadoTabla> newLista = obtenerListadoGastosServicioReporte(gastosAuxList);
            JRBeanCollectionDataSource jds = new JRBeanCollectionDataSource(newLista);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_OBSERVACION", reporteGastos.getObservacion());
            parametros.put("P_GASTOS", jds);

            String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosPdf.jasper");

            InputStream reportStream = new FileInputStream(ruta);
            return JasperRunManager.runReportToPdf(reportStream, parametros, new JREmptyDataSource());
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    public List<ReporteGastoServicioPredioDesocupadoTabla> obtenerListadoGastosServicioReporte(List<GastoServicioPredio> lstGastos) {
        List<ReporteGastoServicioPredioDesocupadoTabla> listado = new ArrayList<>();
        int cont = 0;

        for (GastoServicioPredio gasto : lstGastos) {
            ReporteGastoServicioPredioDesocupadoTabla pago = new ReporteGastoServicioPredioDesocupadoTabla();
            cont++;
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            String fechaEmision = DateFor.format(gasto.getFechaEmision());
            String fechaVencimiento = DateFor.format(gasto.getFechaVencimiento());

            pago.setItem("" + cont);
            pago.setPeriodo(gasto.getPeriodo());
            pago.setInmueble(gasto.getInmueble());
            pago.setBloque(gasto.getBloque());
            pago.setPredio(gasto.getPredio());
            pago.setEstadoPredio(gasto.getEstadoPredio());
            pago.setTipoServicio(gasto.getTipoServicio());
            pago.setNroSuministro(gasto.getSuministro());
            pago.setFechaEmision(fechaEmision);
            pago.setFechaVencimiento(fechaVencimiento);
            pago.setMonto(gasto.getMonto());

            listado.add(pago);
        }
        System.out.println("LISTA lstGastos  " + lstGastos.size());
        return listado;
    }

    @Override
    public ResultadoBusquedaMantenimiento consultaPagoServicioPredioOcupado(PagoServicio pagoServicio, int pagina,
                                                                            int nroRegistros) throws Exception {

        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idServicio", pagoServicio.getCodigoTipoServ());
            parametrosConsulta.put("idInmueble", pagoServicio.getIdInmueble());
            parametrosConsulta.put("mes", pagoServicio.getMes());
            parametrosConsulta.put("anio", pagoServicio.getAnio());

            // int cantidadRegistros = obtenerCantidadPagoServicio(pagoServicio);
            List<GastoServicioPredio> listarPagoServicio = obtenerListaPagoServicioPredioOcupado(parametrosConsulta);
            // Paginacion paginacion = new Paginacion();
            // paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            // resultado.setPaginacion(paginacion);
            resultado.setListaPagoServicioPredios(listarPagoServicio);
            return resultado;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar pago servicio de predio ocupado
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del GastoServicioPredio, tipo List<GastoServicioPredio>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<GastoServicioPredio> obtenerListaPagoServicioPredioOcupado(Map<String, Object> parametros)
            throws Exception {
        List<GastoServicioPredio> listPagoServicio;
        try {
            listPagoServicio = ejbPagoServicio.obtenerListaPagoServicioPredioOcupado(parametros);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listPagoServicio;
    }


    public byte[] generarReporteExcelGastosServiciosOcupados(ReporteGastoServicioPredio reporteGastos, ServletContext context)
            throws Exception {
        try {
            List<GastoServicioPredio> gastosAuxList = reporteGastos.getListaPagoServicioPrediosDesocupados().stream().filter(pago -> pago.getImprimir().equals("1")).collect(Collectors.toList());

            JRBeanCollectionDataSource jds = new JRBeanCollectionDataSource(obtenerListadoGastosServicioReporteOcupados(gastosAuxList));

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_OBSERVACION", reporteGastos.getObservacion());
            parametros.put("P_GASTOS", jds);

            String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosOcupadosPdf.jasper");
            //String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosPdf.jasper");

            InputStream reportStream = new FileInputStream(ruta);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, new JREmptyDataSource());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setOnePagePerSheet(false);
            config.setDetectCellType(true);
            config.setIgnoreCellBorder(false);
            config.setCollapseRowSpan(false);
            exporter.setConfiguration(config);

            exporter.exportReport();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    @Override
    public byte[] generarReportePdfGastosServiciosOcupados(ReporteGastoServicioPredio reporteGastos, ServletContext context)
            throws Exception {
        try {
            List<GastoServicioPredio> gastosAuxList = reporteGastos.getListaPagoServicioPrediosDesocupados().stream().filter(pago -> pago.getImprimir().equals("1")).collect(Collectors.toList());
            JRBeanCollectionDataSource jds = new JRBeanCollectionDataSource(obtenerListadoGastosServicioReporteOcupados(gastosAuxList));

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("P_OBSERVACION", reporteGastos.getObservacion());
            parametros.put("P_GASTOS", jds);

            String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosOcupadosPdf.jasper");
            //String ruta = context.getRealPath("/WEB-INF/reportes/reportesGastosServiciosPdf.jasper");
            InputStream reportStream = new FileInputStream(ruta);
            return JasperRunManager.runReportToPdf(reportStream, parametros, new JREmptyDataSource());
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    @Override
    public List<ReporteGastoServicioPredioOcupadoTabla> obtenerListadoGastosServicioReporteOcupados(List<GastoServicioPredio> lstGastos) throws Exception {

        List<ReporteGastoServicioPredioOcupadoTabla> listado = new ArrayList<>();
        try {

            final int[] count = {0};

            Map<String, List<GastoServicioPredio>> gastosMap = lstGastos.stream().collect(Collectors.groupingBy(GastoServicioPredio::getSuministro));

            gastosMap.forEach((suministro, listaPorSuministro) -> {
                count[0]++;

                ReporteGastoServicioPredioOcupadoTabla pago = new ReporteGastoServicioPredioOcupadoTabla();
                pago.setItem("" + count[0]);
                pago.setInmueble(listaPorSuministro.getFirst().getInmueble());
                pago.setPeriodo(listaPorSuministro.getFirst().getPeriodo());
                pago.setNroSuministro(listaPorSuministro.getFirst().getSuministro());
                pago.setMoneda("S/.");

                BigDecimal montoPorSuministro = listaPorSuministro.stream().map(GastoServicioPredio::getMonto).reduce(BigDecimal.ZERO, BigDecimal::add);
                pago.setMonto(montoPorSuministro);

                listado.add(pago);
            });


        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listado;
    }


    /**
     * Método que permite obtener el reporte porrateo servicio publico
     *
     * @param mes  codigo de inmueble, tipo String.
     * @param anio codigo de bloque, tipo String.
     * @return arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream reportePorrateoServicioPublicoExcel(String mes, String anio, List<PagoServicio> ListarPagoServicio, String descripcionMes) throws Exception {
        try {
            System.out.println("Entro Anio ---> " + anio);
            System.out.println("Entro Mes ---> " + mes);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("ServicioPublico");

            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight((short) (9 * 20));

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

            CellStyle estiloIzquierda = workbook.createCellStyle();
            estiloIzquierda.setFont(font);
            estiloIzquierda.setBorderBottom(BorderStyle.MEDIUM);
            estiloIzquierda.setBorderLeft(BorderStyle.MEDIUM);
            estiloIzquierda.setBorderRight(BorderStyle.MEDIUM);
            estiloIzquierda.setBorderTop(BorderStyle.MEDIUM);
            estiloIzquierda.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloIzquierda.setAlignment(HorizontalAlignment.LEFT);

            CellStyle estiloMonto = workbook.createCellStyle();
            estiloMonto.setFont(font);
            estiloMonto.setBorderBottom(BorderStyle.MEDIUM);
            estiloMonto.setBorderLeft(BorderStyle.MEDIUM);
            estiloMonto.setBorderRight(BorderStyle.MEDIUM);
            estiloMonto.setBorderTop(BorderStyle.MEDIUM);
            estiloMonto.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloMonto.setAlignment(HorizontalAlignment.RIGHT);


            Row fila1 = sheet.createRow(1);

            Cell celda2 = fila1.createCell(1);
            celda2.setCellValue("PRORRATEO GASTOS DE MANTENIMIENTO");

            celda2.setCellStyle(estiloCabe);
            Cell celda3 = fila1.createCell(2);
            celda3.setCellStyle(estiloCabe);
            Cell celda4 = fila1.createCell(3);


            Row filaCabeceraTabla = sheet.createRow(3);

            Cell celdaCabInmueble = filaCabeceraTabla.createCell(1);
            celdaCabInmueble.setCellValue("Mes de:");
            celdaCabInmueble.setCellStyle(estiloCabe);
            Cell celdaCabBloque = filaCabeceraTabla.createCell(2);
            celdaCabBloque.setCellValue(descripcionMes + " " + anio);
            celdaCabBloque.setCellStyle(estiloCabe);

            int cont = 0;
            int fila = 6;
            double montoGeneral = 0;
            for (PagoServicio listaDatos : ListarPagoServicio) {
                cont++;
                Row filaPresupuestoOrden = sheet.createRow(fila);


                Cell celdaInmueble = filaPresupuestoOrden.createCell(1);
                celdaInmueble.setCellValue(cont + ") " + listaDatos.getDescripcionInmueble());
                celdaInmueble.setCellStyle(estiloIzquierda);

                Cell celdaBloque = filaPresupuestoOrden.createCell(2);
                celdaBloque.setCellValue("Mes junio");
                celdaBloque.setCellStyle(estiloCelda);
                fila++;

                Map<String, Object> parametro = new HashMap<>();
                parametro.put("idInmueble", listaDatos.getIdInmueble());
                parametro.put("mes", mes);
                parametro.put("anio", anio);
                List<PagoServicio> ListarDetallePagoServicio = listarDetallePagoServicioReporte(parametro);

                double montoTotal = 0;
                for (PagoServicio listaDetalleDatos : ListarDetallePagoServicio) {
                    Row filaDetalle = sheet.createRow(fila);

                    Cell celdaProveedor = filaDetalle.createCell(1);
                    celdaProveedor.setCellValue(listaDetalleDatos.getProveedor());
                    celdaProveedor.setCellStyle(estiloIzquierda);

                    Cell celdaMonto = filaDetalle.createCell(2);
                    celdaMonto.setCellValue(listaDetalleDatos.getMontoTotal());
                    celdaMonto.setCellStyle(estiloMonto);

                    montoTotal = montoTotal + listaDetalleDatos.getMontoTotal();

                    fila++;
                }
                Row filaTotal = sheet.createRow(fila);

                Cell celdaTotal = filaTotal.createCell(1);
                celdaTotal.setCellValue("Total S/.");
                celdaTotal.setCellStyle(estiloMonto);

                Cell celdaMontoTotal = filaTotal.createCell(2);
                celdaMontoTotal.setCellValue(montoTotal);
                celdaMontoTotal.setCellStyle(estiloMonto);
                montoGeneral = montoGeneral + montoTotal;
                fila++;
                fila++;

            }
            fila++;
            fila++;
            Row filaPie = sheet.createRow(fila);

            Cell celdaProveedor = filaPie.createCell(1);
            celdaProveedor.setCellValue("Las facturas correponden al mes de " + descripcionMes + " - " + anio);
            celdaProveedor.setCellStyle(estiloIzquierda);

            Cell celdaMontoTotal = filaPie.createCell(2);
            celdaMontoTotal.setCellValue(montoGeneral);
            celdaMontoTotal.setCellStyle(estiloMonto);

            ByteArrayOutputStream arregloBytes = new ByteArrayOutputStream();
            workbook.write(arregloBytes);
            workbook.close();

            return arregloBytes;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    /**
     * Método que permite obtener parametros del servico publico
     *
     * @param pagoServicio codigo que identifica el tipo de parametro, tipo Parametro.
     * @return Lista de parametros, tipo List<PagoServicio>.
     */
    @Override
    public List<PagoServicio> listarPagoServicioReporte(PagoServicio pagoServicio) {
        List<PagoServicio> listPagoServicio = new ArrayList<>();
        try {
            listPagoServicio = ejbPagoServicio.listarPagoServicioReporte(pagoServicio);
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return listPagoServicio;
    }


    /**
     * Método que permite consultar el detalle pago servicio reporte
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     */
    @Override
    public List<PagoServicio> listarDetallePagoServicioReporte(Map<String, Object> parametros) {
        List<PagoServicio> listPagoServicio = new ArrayList<>();
        try {
            listPagoServicio = ejbPagoServicio.listarDetallePagoServicioReporte(parametros);
        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
        return listPagoServicio;
    }


}