package pe.gob.onp.nsai.services;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import pe.gob.onp.nsai.dto.*;

/**
 * Interface que define métodos para la inspeccion mantenimiento
 */
public interface InspeccionMantenimientoService {

    /**
     * Método que permite guardar inspeccion mantenimiento
     * @param inspeccionMantenimiento Datos del inspeccion mantenimientoa guardar, tipo Inspeccion mantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void guardarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento);

    /**
     * Método que permite realizar consulta a los inspeccion mantenimiento
     * @param inspeccionMantenimiento datos del inspeccion mantenimiento a buscar, tipo inspeccion mantenimiento.
     * @param pagina pagina de busqueda, tipo int.
     * @param nroRegistros numero de registros, tipo int
     * @return resultado de la busqueda, tipo ResultadoBusquedaInspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaInspeccionMantenimiento consultarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento, int pagina, int nroRegistros);

    /**
     * Método que permite obtener la cantidad de la consulta del inspeccion mantenimiento por los filtros ingresados.
     * @param inspeccionMantenimiento filtros para realizar la búsqueda del InspeccionMantenimiento, tipo Optional<InspeccionMantenimiento>.
     * @return cantidad de la consulta de inmuebles, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCantidadInspeccionInmueble(InspeccionMantenimiento inspeccionMantenimiento);


    /**
     * Método que permite eliminar insversion mantenimiento.
     * @param inspeccionMantenimiento datos del gasto operacion insversionMantenimiento a eliminar, tipo insversionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void eliminarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento);

    /**
     * Método que permite consultar el insversionMantenimiento segun el código
     * @param codigo código del proveedor a consultar, tipo String.
     * @return datos del insversionMantenimiento, tipo insversionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public InspeccionMantenimiento obtenerInspeccionMantenimiento(String codigo);

    /**
     * Método que permite modificar inversión Mantenimiento
     * @param inspeccionMantenimiento Datos del gasto operativo insversionMantenimiento a modificar, tipo insversionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void modificarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento);

    public ResultadoBusquedaInspeccionMantenimiento cargarContacto(TipoResponsable tipoResponsable);

    public List<TipoResponsable> obtenerContacto(TipoResponsable tipoResponsable);


    public ResultadoBusquedaInspeccionMantenimiento cargarArrendamiento(String codigo);

    public List<Arrendatario> obtenerArrendatario(Map<String, Object> parametro);

    public ResultadoBusquedaInspeccionMantenimiento cargarAmbiente(String idInmueble, String idDetalleAmbientePredio,String idInspecccionMantenimiento);

    public List<InspeccionMantenimiento> cargarAmbiente(Map<String, Object> parametro);

    /**
     * Método que permite agregar observacion por acta inspeccion.
     * @param listarObservacionesAmbiente datos de la ambiente, tipo InspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void agregarObservacionAmbiente(List<InspeccionMantenimiento> listarObservacionesAmbiente,InspeccionMantenimiento inspeccionMantenimiento);

    public List<InspeccionMantenimiento> obtenerInfraestruturaPorAmbiente(long id, String idInspecccionMantenimiento);

    public void agregarObservacionInfraestructura(InspeccionMantenimiento inspeccionMantenimiento);

    public void actualizarObservacionInfraestructura(InspeccionMantenimiento inspeccionMantenimiento);

    public void actualizarActaInspeccion(InspeccionMantenimiento inspeccionMantenimiento);

    // public ByteArrayOutputStream generarReporteActaInspeccionExcel(Auditoria auditoria, InspeccionMantenimiento inspeccion);

    public InspeccionMantenimiento obtenerInspeccion(String id);

    public ByteArrayOutputStream generarReporteActaInspeccionExcel(Auditoria auditoria, InspeccionMantenimiento inspeccion,
                                                                   String id);

    /**
     * Método que permite validar si existe N° solicitud
     * @param numeroSolicitud bean con los datos de búsqueda del inspeccionMantenimiento, tipo NumeroSolicitud.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int validarSolicitud(String numeroSolicitud);

    /**
     * Método que permite realizar consulta a los inspeccion mantenimiento
     * @param inspeccionMantenimiento datos del inspeccion mantenimiento a buscar, tipo inspeccion mantenimiento.
     * @param pagina pagina de busqueda, tipo int.
     * @param nroRegistros numero de registros, tipo int
     * @return resultado de la busqueda, tipo ResultadoBusquedaInspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaInspeccionMantenimiento consultarReporteInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento, int pagina, int nroRegistros);

    public List<InspeccionMantenimiento> consultarPrediosReparados(InspeccionMantenimiento inspeccionMantenimiento);

    /**
     * Método que permite obtener la cantidad de reporte de inspeccion mantenimiento conforme.
     * @param inspeccionMantenimiento bean con los datos de búsqueda del inspeccionMantenimiento, tipo inspeccionMantenimiento.
     * @return cantidad número de registros del inspeccionMantenimiento, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerReporteCantidadInspeccion(InspeccionMantenimiento inspeccionMantenimiento);

    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<InspeccionMantenimiento> obtenerReporteInspeccionMantenimiento(Map<String, Object> parametros);


    /**
     * Método que permite registrar un representante legal al arrendatario.
     * @param inspeccionMantenimiento bean con los datos de búsqueda del inspeccionMantenimiento, tipo inspeccionMantenimiento.
     * @param archivo Archivo del representante a ser adjuntado, tipo Archivo.
     * @throws Exception excepción generada en caso de error.
     */
    public void guardarActaInspeccion(InspeccionMantenimiento inspeccionMantenimiento, Archivo archivo);

    /**
     * Método que obtiene el número correlativo de acta inspeccion.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCorrelativoActaInspeccion();

    public int obtenerCantidadInspeccionMantenimiento(Map<String, Object> parametros);

    public int obtenerCantidadPrediosReparados(Map<String, Object> parametros);

}
