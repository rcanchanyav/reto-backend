/**
 * Resumen.
 * Objeto               :   IDevolucionPredioServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la devolucion predio.
 * Fecha de Creación    :   23/03/2021
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

public interface DevolucionPredioService {

    /**
     * Método que permite consultar los contratos.
     * @param idInmueble datos de la entrega de predio a buscar, tipo DevolucionPredio.
     * @param pagina página de busqueda, tipo int.
     * @param nroRegistros número de registros, tipo int
     * @return lista registros con los datos del contrato, tipo ResultadoBusquedaContrato.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento consultarDevolucionPredio(String idInmueble,String idInmuebleBloque,String idInmueblePredio,int pagina, int nroRegistros);

    /**
     * Método que permite consultar predios a entregar.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la entregade predios, tipo List<DevolucionPredio>.
     * @ excepción generada en caso de error.
     */
    List<DevolucionPredio> obtenerListaDevolucionPredio(Map<String,Object> parametrosConsulta) ;

    /**
     * Método que permite obtener la cantidad de registros de prdios a entregar según la busqueda.
     * @param parametros objeto con los datos de búsqueda de la entrega de predio, tipo DevolucionPredio.
     * @return número de registros de DevolucionPredioBusqueda, tipo int.
     * @ excepción generada en caso de error.
     */
    int obtenerCantidadDevolucionPredio(Map<String,Object> parametros) ;



    /**
     * Método que permite consultar los contratos.
     * @param DevolucionPredioBusqueda datos de la entrega de predio a buscar, tipo DevolucionPredio.	
     * @param pagina página de busqueda, tipo int.
     * @param nroRegistros número de registros, tipo int
     * @return lista registros con los datos del contrato, tipo ResultadoBusquedaContrato.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento consultarActaDevolucion(DevolucionPredio DevolucionPredioBusqueda, int pagina, int nroRegistros);

    /**
     * Método que permite consultar los contratos.
     * @param DevolucionPredioBusqueda datos de la entrega de predio a buscar, tipo DevolucionPredio.	
     * @return lista registros con los datos del contrato, tipo ResultadoBusquedaContrato.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento consultarActaBase(DevolucionPredio DevolucionPredioBusqueda);

    /**
     * Método que permite consultar predios a entregar.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la entregade predios, tipo List<DevolucionPredio>.
     * @ excepción generada en caso de error.
     */
    List<DevolucionPredio> obtenerListaActaDevolucion(Map<String,Object> parametrosConsulta) ;

    /**
     * Método que permite consultar acta base de predios entregados e inspeccion.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la entregade predios, tipo List<ActaBase>.
     * @ excepción generada en caso de error.
     */
    List<ActaBase> obtenerListaActaBase(Map<String,Object> parametrosConsulta) ;

    /**
     * Método que permite obtener la cantidad de registros de prdios a entregar según la busqueda.
     * @param DevolucionPredioBusqueda objeto con los datos de búsqueda de la entrega de predio, tipo DevolucionPredio.
     * @return número de registros de DevolucionPredioBusqueda, tipo int.
     * @ excepción generada en caso de error.
     */
    int obtenerCantidadActaDevolucion(DevolucionPredio DevolucionPredioBusqueda) ;
    /**
     * Método que permite modificar datos del DevolucionPredio.
     * @param DevolucionPredio datos del responsable a asignar, tipo DevolucionPredio.
     * @ excepción generada en caso de error.
     */
    void asignarResponsable(DevolucionPredio DevolucionPredio) ;

    /**
     * Método que permite modificar datos del DevolucionPredio.
     * @param DevolucionPredio datos del responsable a asignar, tipo DevolucionPredio.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionPredio(DevolucionPredio DevolucionPredio) ;


    /**
     * Método que permite agregar observacion por infraestructura.
     * @param ambienteInfraestructura datos de la infraestructura, tipo AmbienteInfraestructura.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionInfraestructura(AmbienteInfraestructura ambienteInfraestructura, int idAmbiente) ;

    /**
     * Método que permite agregar observacion por infraestructura.
     * @param ambienteInfraestructura datos de la infraestructura, tipo AmbienteInfraestructura.
     * @ excepción generada en caso de error.
     */
    void actualizarInfraestructura(AmbienteInfraestructura ambienteInfraestructura, int idAmbiente) ;

    /**
     * Método que permite agregar observacion por ambiente.
     * @param listAmbienteContrato datos de la ambiente, tipo AmbienteContrato.
     * @ excepción generada en caso de error.
     */
    void agregarObservacionAmbiente(List<AmbienteContrato> listAmbienteContrato ,AmbienteContrato ambienteContrato) ;


    /**
     * Método que permite consultar el tipo de responsable.
     * @return lista registros con los datos de responsable, tipo List<TipoResponsable>.
     * @ excepción generada en caso de error.
     */
    List<TipoResponsable> obtenerResponsables() ;

    /**
     * Método que permite consultar los datos del reporte acta entrega.
     * @return lista registros con los datos de reporte acta entrega, tipo List<ReporteActaEntrega>.
     * @ excepción generada en caso de error.
     */
    List<ReporteActaEntrega> obtenerDatosReporteActaEntrega() ;

    /**
     * Método que permite consultar los contratos.
     * @param devolucionPredioBusqueda datos de la entrega de predio a buscar, tipo DevolucionPredio.
     * @param pagina mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de ambiente contrato, tipo List<AmbienteContrato>.
     * @ excepción generada en caso de error.
     */
    ResultadoBusquedaMantenimiento obtenerAmbienteContrato(DevolucionPredio devolucionPredioBusqueda, int pagina, int nroRegistros);

    /**
     * Método que permite consultar predios a entregar.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de los ambientes por contrato, tipo List<AmbienteContrato>.
     * @ excepción generada en caso de error.
     */
    List<AmbienteContrato> obtenerListaAmbienteContrato(Map<String,Object> parametrosConsulta) ;


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
                                        String filtroCodigoContrato, jakarta.servlet.ServletContext context, Auditoria auditoria)
             ;

    /**
     * Método que permite generar los datos del reporte de acta de entrega en excel.
     * @param listaReporteActaEntrega lista de datos del reporte, tipo List<ReporteActaEntrega>.
     * @param devolucionPredio filtro por código del contrato, tipo String.
     * @param auditoria objeto de auditoria, tipo Auditoria.
     * @return arregloBytes arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @ excepción generada en caso de error.
     */
    ByteArrayOutputStream generarReporteActaEntregaExcel(List<ReporteActaEntrega> listaReporteActaEntrega,
                                                         DevolucionPredio devolucionPredio,Auditoria auditoria) ;


    /**
     * Método que permite consultar la entrega de predio segun el código
     * @param codigoDevolucionPredio código de la entrega de predio a consultar, tipo String.
     * @return datos del DevolucionPredio, tipo Entreg	aPredio.
     * @ excepción generada en caso de error.
     */
    DevolucionPredio obtenerDevolucionPredio(String codigoDevolucionPredio) ;

    /**
     * Método que permite obtener los datos de la infraestructura por ambiente.
     * @param id identificador del ambiente idAmbiente, tipo long.
     * @ excepción generada en caso de error.
     * @return bean con los datos de la infraestructura, tipo List<AmbienteInfraestructura>.
     */
    List<AmbienteInfraestructura> obtenerInfraestruturaPorAmbiente(long id, String idDevolucionPredio);

    /**
     * Método que permite consultar la entrega de predio asignado segun el código
     * @param codigoContrato código de la entrega de predio a consultar, tipo String.
     * @return datos del DevolucionPredio, tipo DevolucionPredio.
     * @ excepción generada en caso de error.
     */
    DevolucionPredio obtenerResponsableAsignado(String codigoContrato) ;

    /**
     * Método que permite modificar datos del DevolucionPredio.
     * @param DevolucionPredio datos del responsable a asignar, tipo DevolucionPredio.
     * @ excepción generada en caso de error.
     */
    void actualizarResponsable(DevolucionPredio DevolucionPredio) ;

    /**
     * Metodo que permite guardar los documentos del acta de entrega.
     * @param registroDocumentoActa documentos del acta de entrega., tipo AdendaDocumento.
     * @param archivo archivo adjunto, tipo Archivo.
     * @ excepción generada en caso de error.
     */
    void guardarDocumentoActa(DevolucionPredio registroDocumentoActa, Archivo archivo);

    /**
     * Método que obtiene el número correlativo devolucion.
     * @return número correlativo de devolucion , tipo int.
     * @ excepción generada en caso de error.
     */
    int obtenerCorrelativoDevolucion() ;

    /**
     * Método que permite obtener el archivo adjunto.
     * @param DevolucionPredio datos del responsable a asignar, tipo DevolucionPredio.
     * @return archivo adjunto obtenido del filenet, tipo Archivo.
     * @ excepción generada en caso de error.
     */
    Archivo obtenerArchivoAdjunto(DevolucionPredio DevolucionPredio) ;


    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @ excepción generada en caso de error.
     */
    List<EntregaPredioDocumento> obtenerDocumentosActa(Map<String, Object> parametros) ;

    /**
     * Metodo que permite eliminar el documento del inmueble.
     * @param eliminarDocumento documentos del inmueble, tipo InmuebleDocumento.
     * @ excepción generada en caso de error.
     */
    void eliminarDocumento(EntregaPredioDocumento eliminarDocumento);




}
