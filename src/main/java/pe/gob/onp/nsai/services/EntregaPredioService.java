/**
 * Resumen.
 * Objeto               :   IEntregaPredioServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la entrega predio.
 * Fecha de Creación    :   11/12/2020
 * PE de Creación       :   INI-900
 * Autor                :   Omar Meza
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services;


import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import pe.gob.onp.nsai.dto.*;

/**
 * Interface que define los métodos para el mantenimiento del entrega predio.
 */

public interface EntregaPredioService {

    /**
     * Método que permite consultar los contratos.
     * @param entregaPredioBusqueda datos de la entrega de predio a buscar, tipo EntregaPredio.	
     * @param pagina página de busqueda, tipo int.
     * @param nroRegistros número de registros, tipo int
     * @return lista registros con los datos del contrato, tipo ResultadoBusquedaContrato.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento consultarEntregaPredio(EntregaPredio entregaPredioBusqueda, int pagina, int nroRegistros);

    /**
     * Método que permite consultar predios a entregar.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la entregade predios, tipo List<EntregaPredio>.
     * @ excepción generada en caso de error.
     */
    List<EntregaPredio> obtenerListaEntregaPredio(Map<String,Object> parametrosConsulta);

    /**
     * Método que permite obtener la cantidad de registros de prdios a entregar según la busqueda.
     * @param entregaPredioBusqueda objeto con los datos de búsqueda de la entrega de predio, tipo EntregaPredio.
     * @return número de registros de entregaPredioBusqueda, tipo int.
     * @ excepción generada en caso de error.
     */
    int obtenerCantidadEntregaPredio(EntregaPredio entregaPredioBusqueda);

    /**
     * Método que permite modificar datos del entregaPredio.
     * @param entregaPredio datos del responsable a asignar, tipo EntregaPredio.
     * @ excepción generada en caso de error.
     */
    void asignarResponsable(EntregaPredio entregaPredio);

    /**
     * Método que permite modificar datos del entregaPredio.
     * @param entregaPredio datos del responsable a asignar, tipo EntregaPredio.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionPredio(EntregaPredio entregaPredio);


    /**
     * Método que permite agregar observacion por infraestructura.
     * @param ambienteInfraestructura datos de la infraestructura, tipo AmbienteInfraestructura.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionInfraestructura(List<AmbienteInfraestructura> listAmbienteInfraestructura ,
                                           AmbienteInfraestructura ambienteInfraestructura);

    /**
     * Método que permite agregar observacion por ambiente.
     * @param listAmbienteContrato datos de la ambiente, tipo AmbienteContrato.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionAmbiente(List<AmbienteContrato> listAmbienteContrato,
                                    AmbienteContrato ambienteContrato);


    /**
     * Método que permite consultar el tipo de responsable.
     * @return lista registros con los datos de responsable, tipo List<TipoResponsable>.
     * @ excepción generada en caso de error.
     */
    List<TipoResponsable> obtenerResponsables();

    /**
     * Método que permite consultar los datos del reporte acta entrega.
     * @return lista registros con los datos de reporte acta entrega, tipo List<ReporteActaEntrega>.
     * @ excepción generada en caso de error.
     */
    List<ReporteActaEntrega> obtenerDatosReporteActaEntrega();

    /**
     * Método que permite consultar los contratos.
     * @param entregaPredioBusqueda datos de la entrega de predio a buscar, tipo EntregaPredio.	
     * @param pagina mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de ambiente contrato, tipo List<AmbienteContrato>.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento obtenerAmbienteContrato(EntregaPredio entregaPredioBusqueda,
                                                           int pagina, int nroRegistros);

    /**
     * Método que permite consultar predios a entregar.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de los ambientes por contrato, tipo List<AmbienteContrato>.
     * @ excepción generada en caso de error.
     */
    List<AmbienteContrato> obtenerListaAmbienteContrato(Map<String,Object> parametrosConsulta);


    /**
     * Método que permite generar los datos del reporte de acta de entrega en pdf.
     * @param listaReporteActaEntrega lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param filtroCodigoContrato filtro por código del contrato, tipo String.
     * @param context contexto del sistema, tipo ServletContext.
     * @param auditoria objeto de auditoria, tipo Auditoria.
     * @return bytes arreglo de bytes del archivo pdf generado, tipo byte[].
     * @ excepción generada en caso de error.
     */
    byte[] generarReporteActaEntregaPdf(List<ReporteActaEntrega> listaReporteActaEntrega,
                                        String filtroCodigoContrato, Auditoria auditoria);

    /**
     * Método que permite generar los datos del reporte de acta de entrega en excel.
     * @param listaReporteActaEntrega lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param entregaPredio filtro por código del contrato, tipo String.
     * @param auditoria objeto de auditoria, tipo Auditoria.
     * @return arregloBytes arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @ excepción generada en caso de error.
     */
    byte[] generarReporteActaEntregaExcel(List<ReporteActaEntrega> listaReporteActaEntrega,
                                          EntregaPredio entregaPredio, Auditoria auditoria);

    /**
     * Método que permite consultar la entrega de predio segun el código
     * @param codigoEntregaPredio código de la entrega de predio a consultar, tipo String.
     * @return datos del entregaPredio, tipo Entreg	aPredio.
     * @ excepción generada en caso de error.
     */
    EntregaPredio obtenerEntregaPredio(String codigoEntregaPredio, Integer codigoPredio);

    /**
     * Método que permite obtener los datos de la infraestructura por ambiente.
     * @param idAmbiente identificador del ambiente idAmbiente, tipo long.
     * @ excepción generada en caso de error.
     * @return bean con los datos de la infraestructura, tipo List<AmbienteInfraestructura>.
     */
    List<AmbienteInfraestructura> obtenerInfraestruturaPorAmbiente(long idAmbiente);

    /**
     * Método que permite consultar la entrega de predio asignado segun el código
     * @param codigoContrato código de la entrega de predio a consultar, tipo String.
     * @return datos del entregaPredio, tipo EntregaPredio.
     * @ excepción generada en caso de error.
     */
    EntregaPredio obtenerResponsableAsignado(String codigoContrato);

    /**
     * Método que permite modificar datos del entregaPredio.
     * @param entregaPredio datos del responsable a asignar, tipo EntregaPredio.
     * @ excepción generada en caso de error.
     */
    void actualizarResponsable(EntregaPredio entregaPredio);

    /**
     * Metodo que permite guardar los documentos del acta de entrega.
     * @param registroDocumentoActa documentos del acta de entrega., tipo AdendaDocumento.
     * @param archivo archivo adjunto, tipo Archivo.
     * @ excepción generada en caso de error.
     */
    void guardarDocumentoActa(EntregaPredio registroDocumentoActa, Archivo archivo);


    /**
     * Método que permite obtener el documento adjunto.
     * @param documento bean con los datos de búsqueda del documento, tipo EntregaPredio.
     * @return objeto con los datos del documento, tipo Archivo.
     * @ excepción generada en caso de error.
     */
    Archivo obtenerDocumentoAdjunto(EntregaPredio documento);

    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @ excepción generada en caso de error.
     */
    List<EntregaPredio> obtenerReporteSeguimiento(Map<String, Object> parametros);


    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @ excepción generada en caso de error.
     */
    List<EntregaPredioDocumento> obtenerDocumentosActa(Map<String, Object> parametros);

    /**
     * Metodo que permite eliminar el documento del inmueble.
     * @param eliminarDocumento documentos del inmueble, tipo InmuebleDocumento.
     * @ excepción generada en caso de error.
     */
    void eliminarDocumento(EntregaPredioDocumento eliminarDocumento);






}
