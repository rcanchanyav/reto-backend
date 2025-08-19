/**
 * Resumen.
 * Objeto               :   IProgramacionServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la programacion del mantenimiento.
 * Fecha de Creación    :   16/12/2020
 * PE de Creación       :   INI-900
 * Autor                :   Igor A. Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;
import pe.gob.onp.nsai.dto.ProgramacionMantenimiento;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDetalle;
import pe.gob.onp.nsai.dto.ResultadoBusquedaInmueble;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;

/**
 * Interface que define métodos para el mantenimiento de la programacion
 */
public interface ProgramacionMantenimientoService {
	/**
	 * Método que permite realizar una consulta de la programacion.
	 * @param programacionBusqueda datos de la programacion a buscar, tipo ProgramacionMantenimiento.	 
	 * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento consultarProgramacion(
			Map<String, Object> parametros			
	);
	
	/**
	 * Método que permite obtener la lista de programacions.
	 * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return lista de registros con los datos de la programacion, tipo List<ProgramacionMantenimiento>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<ProgramacionMantenimiento> obtenerListaProgramaciones(
			Map<String,Object> parametrosConsulta
	);
	
	/**
	 * Método que permite obtener la cantidad de registros de las programaciones según la búsqueda.
	 * @param programacionBusqueda objeto con los datos de la búsqueda de la programacion, código de servicio.
	 * @return número de registros de programaciones, tipo int.
	 * @throws Exception excepción generada en caso de error.
	 */
	public int obtenerCantidadProgramaciones(
			Map<String,Object> parametrosConsulta
	);
	
	/**
	 * Método que consultar los inmuebles registrados en una proyeccion de costo, para luego ser programados.
	 * @param busquedaProgramacion datos de la programacion a buscar, tipo ProgramacionMantenimiento.
	 * @return datos de los inmuebles, tipo ResultadoBusquedaInmueble.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaInmueble consultarInmueble(
			ProgramacionMantenimiento busquedaProgramacion
	);
	
	/**
	 * Método que permite consultar las etapas de una programacion
	 * @param programacionMantenimientoDetalle datos del detalle de la programacion, tipo ProgramacionMantenimientoDetalle
	 * @return ResultadoBusquedaMantenimiento datos de las etapas de la programacion.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento consultarProgramacionMantenimientoDetalle(
			ProgramacionMantenimientoDetalle programacionMantenimientoDetalle
	);
	
	/**
	 * Método que permite obtener la lista de las etapas de una programacion
	 * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return datos de las etapas de una programacion, tipo List<ProgramacionMantenimientoDetalle>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<ProgramacionMantenimientoDetalle> obtenerListaProgramacionesMantenimientoDetalle(
			Map<String,Object> parametrosConsulta
	);
	
	/**
	 * Método que permite registrar datos de la programacion.
	 * @param programacionMatenimiento datos de la programacion a registrar, tipo ProgramacionMatenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void registrarProgramacion(
			ProgramacionMantenimiento programacionMatenimiento
	);
	
	/**
	 * Método que permite eliminar los datos de la programacion del mantenimiento.
	 * @param programacionMantenimiento datos de la programacion del mantenimiento a eliminar, tipo ProgramacionMantenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void eliminarProgramacionMantenimiento(
			ProgramacionMantenimiento programacionMantenimiento
	);
	
	/**
	 * Método que permite consultar la programacion del mantenimiento según el código
	 * @param codigoProgramacion código de la programacion de mantenimiento a consultar, tipo String.
	 * @return datos de la programacion de mantenimiento, tipo ProgramacionMatenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ProgramacionMantenimiento obtenerProgramacionMantenimiento(
			int codigoProgramacion
	);
	
	/**
	 * Método que permite modificar datos de la programacion de mantenimiento.
	 * @param programacionMantenimiento datos de la programacion de mantenimiento a modificar, tipo ProgramacionMantenimiento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void modificarProgramacionMantenimiento(
			ProgramacionMantenimiento programacionMantenimiento
	);
	
	//eliminarProgramacionMantenimientoDetalle
	/**
	 * Método que permite eliminar los datos de la etapa de la programacion del mantenimiento.
	 * @param programacionMantenimientoDetalle datos de la etapa de la programacion del mantenimiento a eliminar, tipo ProgramacionMantenimientoDetalle.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void eliminarProgramacionMantenimientoDetalle(
			ProgramacionMantenimientoDetalle programacionMantenimientoDetalle
	);
	
	
	/**
	 * Método que permite obtener la programacion la etapa de la programacion
	 * @param pmd datos de la etapa de la programacion de mantenimiento a consultar, tipo ProgramacionMantenimientoDetalle.
	 * @return datos de la etapa de la programacion, tipo ProgramacionMantenimientoDetalle.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ProgramacionMantenimientoDetalle obtenerEtapaProgramacionMatenimientoDetalle(
			ProgramacionMantenimientoDetalle pmd
	);
	
	/**
	 * Método que permite modificar datos de la etapa de programacion de mantenimiento.
	 * @param pmd datos de la etapa de programacion de mantenimiento a modificar, tipo ProgramacionMantenimientoDetalle.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void modificarProgramacionMantenimientoDetalle(
			ProgramacionMantenimientoDetalle pmd
	);
	
	
	/**
	 * Método que permite obtener el detalle de la lista de programaciones.
	 * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return listado de registros con los datos de los detalles de la programacion, tipo List<ProgramacionMantenimientoDetalle>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ResultadoBusquedaMantenimiento obtenerProgramacionMantenimientoDetalle(
			ProgramacionMantenimiento programacionMantenimiento
	);
	
	/**
	 * Método que permite consultar el monto total del servicio que se envia como parámetro
	 * @param parametros datos de la búsqueda para el monto total de la programacion, tipo Map<String, Object>.
	 * @return datos con el monto total del servicio proyectado, tipo BigDecimal.
	 * @throws Exception excepción generada en caso de error.
	 */
	public Map<String, Object> obtenerMontoTotalServicioProgramacion (
			Map<String, Object> parametros
	);
}
