/**
 * Resumen.
 * Objeto               :   IPresupuestoOrdenServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para los presupuestos de orden de servicio.
 * Fecha de Creación    :   15/05/2022
 * PE de Creación       :   INI-900
 * Autor                :   Juan Carlos Verde 
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.services;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import pe.gob.onp.nsai.dto.Archivo;
import pe.gob.onp.nsai.dto.PresupuestoOrden;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;

public interface PresupuestoOrdenService extends Serializable {
	/**
	 * Método que permite realizar consulta a los Presupuestos Orden
	 * 
	 * @param presupuestoOrden
	 *            datos del presupuesto de orden a buscar.
	 * @param pagina
	 *            pagina de busqueda, tipo int.
	 * @param nroRegistros
	 *            numero de registros, tipo int
	 * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
	 * @throws Exception
	 *             excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento consultarPresupuestoOrden(PresupuestoOrden presupuestoOrden, int pagina,
			int nroRegistros) throws Exception;

	/**
	 * Método que permite consultar ambientes.
	 * 
	 * @param parametros
	 *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return lista registros con los datos del ambiente, tipo
	 *         List<PresupuestoOrden>.
	 * @throws Exception
	 *             excepción generada en caso de error.
	 */
	public List<PresupuestoOrden> obtenerListaPresupuestoOrden(Map<String, Object> parametros) throws Exception;

	/**
	 * Método que permite obtener la cantidad de la consulta del presupuesto orden
	 * por los filtros ingresados.
	 * 
	 * @param presupuestoOrden
	 *            filtros para realizar la búsqueda del PresupuestoOrden, tipo
	 *            Optional<PresupuestoOrden>.
	 * @return cantidad de la consulta de inmuebles, tipo int.
	 * @throws Exception
	 *             excepción generada en caso de error.
	 */
	public int obtenerCantidadPresupuestoOrden(PresupuestoOrden presupuestoOrden) throws Exception;

	/**
	 * Método que permite consultar los presupuestos de orden de servicio
	 * 
	 * @param presupuestoOrden
	 *            de los Presupuesto de orden de servicio
	 * @return datos del PresupuestoOrden.
	 * @throws Exception
	 *             excepción generada en caso de error.
	 */
	public ByteArrayOutputStream generarReportePresupuestoOrdenExcel(PresupuestoOrden presupuestoOrden, int pagina,
			int nroRegistros) throws Exception;

	
	public void guardarPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception;
	
	
	public ResultadoBusquedaMantenimiento consultarPresReg(PresupuestoOrden presupuestoOrden, int pagina,
			int nroRegistros) throws Exception;

	public ResultadoBusquedaMantenimiento obtenerListaPresupuestoDet(PresupuestoOrden presupuestoOrden, int pagina,
			int nroRegistros) throws Exception;

	public void modificarPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception;

	
	public ResultadoBusquedaMantenimiento consultaRealizarPre(PresupuestoOrden presupuestoOrden, int pagina,
			int nroRegistros) throws Exception;
	
	public List<PresupuestoOrden> obtenerListaRealizarPre(Map<String, Object> parametros) throws Exception;

	public void guardarGastoPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public void modificarPresupuestoDet(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public void actualizarTotalPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public Integer obtenerCantidadNroSol(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public ResultadoBusquedaMantenimiento consultaTotalPresuOrde(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public List<PresupuestoOrden> consultaPresupuestoOrdenVigente(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public void guardarDocumentoOrden(PresupuestoOrden registroPresOrdenDoc,Archivo archivo)throws Exception;
	
	public ResultadoBusquedaMantenimiento consultaTotalPresuOrdeDet(PresupuestoOrden presupuestoOrden) throws Exception;
	
	public Archivo obtenerDocumentoAdjunto(PresupuestoOrden presupuestoOrden) throws Exception;
	

}