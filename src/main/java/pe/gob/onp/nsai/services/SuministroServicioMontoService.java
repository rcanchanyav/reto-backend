/**
 * Resumen.
 * Objeto               :   ISuministroServicioMontoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para los montos del suministro.
 * Fecha de Creación    :   04/09/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Cipriano Méndez Gálvez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.dto.SuministroMonto;

/**
 * Interface utilizado para la creación de los métodos para los montos del suministro.
 */
public interface SuministroServicioMontoService {

	/**
	 * Método que permite consultar suministros
	 * @param suministroBusqueda objeto con los datos del monto del suministro, tipo SuministroMonto.
	 * @param pagina pagina actual de consulta, tipo int. 
	 * @param nroRegistros cantidad de registros a recuperar, tipo int.
	 * @return objeto con los datos de los suministros, tipo ResultadoBusquedaMantenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento obtenerSuministros(SuministroMonto suministroBusqueda, int pagina, int nroRegistros) throws Exception;
	
	/**
	 * Método que permite consultar suministros con sus montos.
	 * @param idSistema identificador del sistema, tipo Integer.
	 * @param suministroBusqueda objeto con los datos del monto del suministro, tipo SuministroMonto.
	 * @param pagina pagina actual de consulta, tipo int. 
	 * @param nroRegistros cantidad de registros a recuperar, tipo int.  
	 * @return objeto con los datos de los suministros, tipo ResultadoBusquedaMantenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento consultarSuministrosMontos(Integer idSistema, SuministroMonto suministroBusqueda, int pagina, int nroRegistros) throws Exception;
	
	/**
	 * Método que permite consultar suministros.
	 * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return lista de registros con los datos de los suministros, tipo List<SuministroMonto>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<SuministroMonto> obtenerSuministros(Map<String,Object> parametros) throws Exception;
	
	/**
	 * Método que permite consultar suministros con sus montos.
	 * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return lista de registros con los datos de los suministros, tipo List<SuministroMonto>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<SuministroMonto> consultarSuministrosMontos(Map<String,Object> parametros) throws Exception;
	
	/**
	 * Método que permite obtener la cantidad de registros de suministros.
	 * @param suministro bean con los datos de búsqueda, tipo Suministro.
	 * @return cantidad de registros de la búsqueda de suministros, tipo int.
	 * @throws Exception excepción generada en caso de error.
	 */
	public int obtenerCantidadBusquedaSuministro(SuministroMonto suministro) throws Exception;
	
	/**
	 * Método que permite guardar los suministros.
	 * @param registroSuministro registra los datos del suministro, tipo SuministroMonto.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void guardarSuministro(SuministroMonto registroSuministro) throws Exception;
	
	/**
	 * Método que permite modificar los datos del suministro.
	 * @param modificarSuministro datos del suministro, tipo SuministroMonto.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void modificarSuministro(SuministroMonto modificarSuministro)throws Exception;
	
	/**
	 * Método que permite eliminar el suministro.
	 * @param eliminarSuministro datos del suministro a eliminar, tipo SuministroMonto.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void eliminarSuministro(SuministroMonto eliminarSuministro) throws Exception;

	/**
	 * Método que permite verificar el suministro existente.
	 * @param registroSuministro bean con los datos de búsqueda de los suministros, tipo SuministroMonto.
	 * @return cantidad de suministros existentes, tipo int.
	 * @throws Exception excepción generada en caso de error.
	 */
	public int verificarSuministroExistente(SuministroMonto registroSuministro) throws Exception;
	
	/**
	 * Método que permite consultar suministros por id.
	 * @param idSuministro identificador del suministro, tipo String.
	 * @return objeto con los datos del suministro, tipo SuministroMonto.
	 * @throws Exception excepción generada en caso de error.
	 */
	public SuministroMonto consultarSuministroPorId(String idSuministro) throws Exception;
     
	/**
	 * Método que permite obtener la cantidad de registros de suministros con sus montos.
	 * @param suministro bean con los datos de búsqueda de los suministros, tipo SuministroMonto.
	 * @return cantidad de registros de la búsqueda de suministros, tipo int.
	 * @throws Exception excepción generada en caso de error.
	 */
	public int obtenerCantidadSuministroMonto(SuministroMonto suministro) throws Exception;
	
}
