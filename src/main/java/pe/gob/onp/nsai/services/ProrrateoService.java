/**
 * Resumen.
 * Objeto               :   IProrrateoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para el prorrateo
 * Fecha de Creación    :   27/08/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Christian Wong
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services;

import jakarta.servlet.ServletContext;
import pe.gob.onp.nsai.dto.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Interface que define los métodos para el prorrateo
 */
public interface ProrrateoService {

    /**
     * Método que permite consultar la lista de inmuebles para el prorrateo
     * @param inmuebleBusqueda datos del inmueble a buscar, tipo Inmueble
     * @param pagina página a buscar, tipo int
     * @param nroRegistros número de registros a buscar, tipo int
     * @return datos del inmueble consultado, tipo ResultadoBusquedaInmueble
     * @throws Exception excepción generada en caso de error.
     */
    ResultadoBusquedaInmueble consultarInmueblesProrrateo(Inmueble inmuebleBusqueda, int pagina, int nroRegistros);

    /**
     * Método que permite consultar la cantidad de inmuebles según parametros de busqueda
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>
     * @return cantidad de inmuebles obtenidos, tipo Integer.
     * @throws Exception excepción generada en caso de error.
     */
    Integer obtenerCantidadInmuebleParaProrrateo(Map<String, Object> parametros);

    /**
     * Método que obtiene la lista de inmuebles según parametros de busqueda
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>
     * @return lista de inmuebles obtenida, tipo List<Inmueble>
     * @throws Exception excepción generada en caso de error.
     */
    List<Inmueble> consultarInmueblesParaProrrateo(Map<String, Object> parametros);

    /**
     * Método que permite consultar los servicios del inmueble segun el periodo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @param pagina pagina a buscar, tipo int.
     * @param nroRegistros numero de registros, tipo int.
     * @return resultado de la busqueda, tipo ResultadoBusquedaProrrateo.
     * @throws Exception excepción generada en caso de error.
     */
    ResultadoBusquedaProrrateo consultarServiciosPeriodoInmueble(PeriodoProrrateoInmueble periodoProrrateo, int pagina, int nroRegistros);

    /**
     * Método que permite obtener la cantidad de servicios para el prorrateo
     * @param parametrosConsulta parametros de consulta, tipo Map<String,Object>.
     * @return cantidad de servicios para el prorrateo, tipo int.
     * @throws Exception excepción generada en caso de error
     */
    int obtenerCantidadServiciosParaProrrateo(Map<String, Object> parametrosConsulta);

    /**
     * Método que permite consultar los servicios para el prorrateo
     * @param parametros parametros de busqueda, tipo Map<String,Object>.
     * @return lista de servicios para el prorrateo, tipo List<ServicioProrrateoInmueble>.
     * @throws Exception excepción generada en caso de error.
     */
    List<ServicioProrrateoInmueble> consultarServiciosParaProrrateo(Map<String, Object> parametros);

    /**
     * Método que permite registrar los servicios del prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    Long registrarServiciosProrrateo(ServicioProrrateoInmueble prorrateoInmueble, List<BloqueServicioProrrateo> listaBloqueProrrateo, List<PredioServicioProrrateo> listaPredioProrrateo);

    /**
     * Método que permite registrar los servicios del prorrateo de los bloques.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    Long registrarServicioProrrateoBloque(ServicioProrrateoInmueble prorrateoInmueble, List<BloqueServicioProrrateo> listaBloqueProrrateo);

    /**
     * Método que permite registrar los servicios del prorrateo de los predios.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaPredioProrrateo lista de predio para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    Long registrarServicioProrrateoPredio(ServicioProrrateoInmueble prorrateoInmueble, List<PredioServicioProrrateo> listaPredioProrrateo);

    /**
     * Método que permite obtener los servicios del prorrateo.
     * @param idServicioProrrateoInmueble identificador de la tabla de servicios del prorrateo, tipo Long.
     * @return datos del servicio para el prorrateo, tipo ServicioProrrateoInmueble.
     * @throws Exception excepción generada en caso de error.
     */
    ServicioProrrateoInmueble obtenerServiciosDeProrrateo(Long idServicioProrrateoInmueble);

    /**
     * Método que permite modificar los servicios del prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    void modificarServiciosProrrateo(ServicioProrrateoInmueble prorrateoInmueble, List<BloqueServicioProrrateo> listaBloqueProrrateo, List<PredioServicioProrrateo> listaPredioProrrateo);

    /**
     * Método que permite modificar los servicios del prorrateo de los bloques.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    void modificarServiciosProrrateoBloque(ServicioProrrateoInmueble prorrateoInmueble, List<BloqueServicioProrrateo> listaBloqueProrrateo);

    /**
     * Método que permite modificar los servicios del prorrateo de los predios.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaPredioProrrateo lista de predio para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    void modificarServiciosProrrateoPredio(ServicioProrrateoInmueble prorrateoInmueble, List<PredioServicioProrrateo> listaPredioProrrateo);

    /**
     * Método que permite validar si la configuracion del prorrateo del periodo es correcta.
     * @param mes mes del prorrateo, tipo String.
     * @param anio año del prorrateo, tipo String,
     * @param idInmueble identificador del inmueble, tipo String.
     * @return errores para validacion, tipo ErrorValidacion.
     * @throws Exception excepción generada en caso de error.
     */
    ErrorValidacion validarConfiguracionProrrateo(String mes, String anio, String idInmueble);

    /**
     * Método que permite obtener los datos del periodo del prorrateo.
     * @param periodoProrrateo datos del periodo de prorrateo a buscar, tipo PeriodoProrrateoInmueble.
     * @return datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @throws Exception excepción generada en caso de error.
     */
    PeriodoProrrateoInmueble obtenerPeriodoProrrateo(PeriodoProrrateoInmueble periodoProrrateo);

    /**
     * Método que obtiene la lista de inmueble en estado de prorrateo.
     * @param idInmueble identificador del inmueble, tipo String.
     * @param mes mes del prorrateo, tipo String.
     * @param anio año del prorrateo, tipo String.
     * @param idServicio identificador del servicio, tipo String.
     * @return lista de inmueble en estado de prorrateo, tipo List<Inmueble>
     * @throws Exception, excepción generada en caso de error.
     */
    List<PeriodoProrrateoInmueble> obtenerPeriodoProrrateoInmueble(String idInmueble, String mes, String anio, String idServicio);

    /**
     * Método que permite actualizar los servicios para prorrateo
     * @param listaServicios lista de servicios para prorrateo, tipo List<ServicioProrrateoInmueble>.
     * @param periodoInmueble datos del periodo del inmueble, tipo PeriodoProrrateoInmueble
     * @param auditoria datos de auditoria, tipo Auditoria.
     * @throws Exception excepción generada en caso de error.
     */
    void actualizarServiciosProrrateo(List<ServicioProrrateoInmueble> listaServicios, PeriodoProrrateoInmueble periodoInmueble, Auditoria auditoria);

    /**
     * Método que permite generar el prorrateo simulado
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream generarProrrateoSimulado(PeriodoProrrateoInmueble periodoProrrateo);

    /**
     * Método que permite generar el prorrateo definitivo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream generarProrrateoDefinitivo(PeriodoProrrateoInmueble periodoProrrateo);

    /**
     * Método que permite ver el prorrateo definitivo generado
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream verProrrateoPeriodo(PeriodoProrrateoInmueble periodoProrrateo);


    /**
     * Método que permite generar el excel para prorrateo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream generarExcelProrrateo(PeriodoProrrateoInmueble periodoProrrateo);

    /**
     * Método que permite consultar los servicios de prorrateos definitivos
     * @param parametros parametros de busqueda, tipo Map<String,Object>.
     * @return lista de servicios del inmueble, tipo List<ServicioProrrateoInmueble>.
     * @throws Exception excepción generada en caso de error.
     */
    List<ServicioProrrateoInmueble> consultarServiciosProrrateoDefinitivo(Map<String, Object> parametros);

    /**
     * Método que permite consultar la cantidad de servicios de prorrateo definitivos
     * @param parametros parametros de busqueda, tipo Map<String,Object>.
     * @return cantidad de servicios del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    int consultarCantidadServiciosProrrateoDefinitivo(Map<String, Object> parametros);

    /**
     * Método que permite ver el prorrateo de los inmuebles en el periodo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    ByteArrayOutputStream verProrrateosPeriodo(PeriodoProrrateoInmueble periodoProrrateo);

    /**
     * Método que permite consultar la lista de prorrateoPredioInmueble para el prorrateo
     * @param prorrateoBusqueda Busqueda datos del ProrrateoPredioGeneral a buscar, tipo ProrrateoPredioGeneral
     * @param pagina página a buscar, tipo int
     * @param nroRegistros número de registros a buscar, tipo int
     * @return datos del inmueble consultado, tipo ResultadoBusquedaInmueble
     * @throws Exception excepción generada en caso de error.
     */
    ResultadoBusquedaProrrateo consultarInmueblesProrrateoPredio(ProrrateoPredioGeneral prorrateoBusqueda, int pagina, int nroRegistros);

    List<ProrrateoPredioGeneral> consultarInmueblesPredioParaProrrateo(Map<String, Object> parametros);

    /**
     * Método que permite obtener la cantidad de registros del prorrateo Predio.
     * @param prorrateoPredio bean con los datos de búsqueda del prorrateo predio del inmueble, tipo ProrrateoPredioGeneral.
     * @return cantidad número de registros del predio del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    int obtenerCantidadInmueblesPredioParaProrrateo(ProrrateoPredioGeneral prorrateoPredio);


    Map<String, Object> generarProrrateoInmuebleBloquePredio(ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio);

    byte[] generarProrrateoInmuebleBloquePredioPdf(List<ReporteProrrateoInmuebleBloquePredio> listaReporteProrrateoInmuebleBloquePredio,
                                                   ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio, String ruta, ServletContext context,
                                                   Auditoria auditoria);

    ByteArrayOutputStream generarProrrateoInmuebleBloquePredioExcel(List<ReporteProrrateoInmuebleBloquePredio> listaReporteProrrateoInmuebleBloquePredio,
                                                                    ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio, String ruta, ServletContext context,
                                                                    Auditoria auditoria);


    Map<String, Object> verificacionProrrateo(ReporteVerificacionProrrateoPredio filtroReporteVerificacionProrrateo);

    byte[] verificacionProrrateoPdf(List<ReporteVerificacionProrrateoInmueble> listaVerificacionPorInmueble,
                                    List<ReporteVerificacionProrrateoBloque> listaVerificacionPorBloque,
                                    List<ReporteVerificacionProrrateoPredio> listaVerificacionPorPredio,
                                    ReporteVerificacionProrrateoPredio filtroReporteVerificacionProrrateo, String ruta, ServletContext context,
                                    Auditoria auditoria);

}
