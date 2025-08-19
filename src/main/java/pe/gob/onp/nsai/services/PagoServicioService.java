package pe.gob.onp.nsai.services;

import jakarta.servlet.ServletContext;
import pe.gob.onp.nsai.dto.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface PagoServicioService {

    /**
     * Método que permite guardar pago servicio
     *
     * @param pagoServicio Datos del pago servicio a guardar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    void guardarPagoServicio(PagoServicio pagoServicio);

    /**
     * Método que permite modificar un pago servicio
     *
     * @param pagoServicio Datos del pago servicio a modificar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    void modificarPagoServicio(PagoServicio pagoServicio);

    /**
     * Método que permite eliminar pago servicio según el id
     *
     * @param pagoServicio datos del pago servicio a eliminar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    void eliminarPagoServicio(PagoServicio pagoServicio);

    /**
     * Método que permite consultar pago servicio.
     *
     * @param id identificador del contrato, tipo String.
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    PagoServicio obtenerPagoServicio(Integer id);


    /**
     * Método que permite obtener la lista del suministro del predio por id.
     *
     * @param nuSuministro identificador con los datos de búsqueda de los suministros del predio, tipo String.
     * @param tipo         identificador con los datos de búsqueda de los suministros del predio, tipo Integer.
     * @throws Exception excepción generada en caso de error.
     */
    PredioSuministro obtenerSuministroPredio(String nuSuministro, Integer tipo);


    ResultadoBusquedaMantenimiento consultaPagoServicio(PagoServicio pagoServicio, int pagina, int nroRegistros);

    /**
     * Método que permite obtener la cantidad de registros de pagos servicios.
     *
     * @param pagoServicio bean con los datos de búsqueda del pagosServicios, tipo pagosServicios.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    int obtenerCantidadPagoServicio(PagoServicio pagoServicio);

    /**
     * Método que permite consultar pago servicio
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    List<PagoServicio> obtenerListaPagoServicio(Map<String, Object> parametros);

    /**
     * Método que permite obtener parametros del servico publico
     *
     * @return Lista de parametros, tipo List<CategoriaParametro>.
     */
    List<CategoriaParametro> listarServicioPublico();

    List<CategoriaParametro> listarServicioEmpresa(String id);

    ResultadoBusquedaMantenimiento consultaPagoServicioPublico(PagoServicio pagoServicio, int pagina, int nroRegistros);

    int obtenerCantidadPagoServicioPublico(PagoServicio pagoServicio);

    /**
     * Método que permite consultar pago servicio publico
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    List<PagoServicio> obtenerListaPagoServicioPublico(Map<String, Object> parametros);

    /**
     * Método para obtener la lista de numero de suministro
     *
     * @param valor identificador del contrato, tipo String.
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    PagoServicio obtenerInformaionBarra(String valor);

    /**
     * Método que permite guardar tmp pago servicio
     *
     * @param pagoServicio Datos del pago servicio a guardar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    void tmpPagoServicio(PagoServicio pagoServicio);

    ResultadoBusquedaMantenimiento tmpConsultaPagoServicio(int pagina, int nroRegistros);

    /**
     * Método que permite obtener la cantidad de registros de tabla temporal pagos servicios.
     *
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    int tmpOtenerCantidadPagoServicio();

    /**
     * Método que permite consultar tabla temporal pago servicio
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    List<PagoServicio> tmpObtenerListaPagoServicio(Map<String, Object> parametros);

    /**
     * Método que permite eliminar temporal pago servicio según el id
     *
     * @param pagoServicio datos del pago servicio a eliminar, tipo PagoServicio.
     * @throws Exception excepción generada en caso de error.
     */
    void retirarPagoServicio(PagoServicio pagoServicio);

    /**
     * Método que permite validar si existe el suministro
     *
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    int validarBusqueda(String sumistro);

    /**
     * Método que permite validar si existe el suministro registrado
     *
     * @return lista de pago servicio vigentes, tipo List<PagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    int validarProcesoRepetidos(PagoServicio pagoServicio);

    ResultadoBusquedaMantenimiento consultaPagoServicioPredioDesocupado(PagoServicio pagoServicio, int pagina, int nroRegistros);

    List<GastoServicioPredio> obtenerListaPagoServicioPredioDesocupado(Map<String, Object> parametros);

    byte[] generarReporteExcelGastosServicios(ReporteGastoServicioPredio reporteGastos, ServletContext context);

    byte[] generarReportePdfGastosServicios(ReporteGastoServicioPredio reporteGastos, ServletContext context);

    ResultadoBusquedaMantenimiento consultaPagoServicioPredioOcupado(PagoServicio pagoServicio, int pagina, int nroRegistros);

    List<GastoServicioPredio> obtenerListaPagoServicioPredioOcupado(Map<String, Object> parametros);

    byte[] generarReporteExcelGastosServiciosOcupados(ReporteGastoServicioPredio reporteGastos, ServletContext context);

    byte[] generarReportePdfGastosServiciosOcupados(ReporteGastoServicioPredio reporteGastos, ServletContext context);

    /**
     * Método que permite obtener el reporte porrateo servicio publico
     *
     * @param mes  codigo de inmueble, tipo String.
     * @param anio codigo de bloque, tipo String.
     * @return arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream reportePorrateoServicioPublicoExcel(String mes, String anio, List<PagoServicio> ListarPagoServicio, String descripcionMes);

    List<PagoServicio> listarPagoServicioReporte(PagoServicio pagoServicio);

    /**
     * Método que permite consultar el detalle pago servicio reporte
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return obtenerListaPagoServicio registros con los datos del pagoServicio, tipo List<pagoServicio>.
     * @throws Exception excepción generada en caso de error.
     */
    List<PagoServicio> listarDetallePagoServicioReporte(Map<String, Object> parametros);

    List<ReporteGastoServicioPredioDesocupadoTabla> obtenerListadoGastosServicioReporte(List<GastoServicioPredio> lstGastos);

    List<ReporteGastoServicioPredioOcupadoTabla> obtenerListadoGastosServicioReporteOcupados(
            List<GastoServicioPredio> lstGastos);


}