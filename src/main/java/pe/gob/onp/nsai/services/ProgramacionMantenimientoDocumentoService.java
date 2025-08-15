/**
 * Resumen.
 * Objeto               :   IProgramacionMantenimientoDocumentoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para los documentos de la
 * 							programcion de mantenimientos
 * Fecha de Creación    :   08/02/2021
 * PE de Creación       :   INI-900
 * Autor                :   Igor Alexander Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;
import pe.gob.onp.nsai.dto.Archivo;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDocumento;

/**
 *Interface utilizado para la creación de los métodos para los documentos de la programacion de mantenimientos.
 */
public interface ProgramacionMantenimientoDocumentoService {
	/**
	 * Método que permite consultar los documentos de la programacion de mantenimientos.
	 * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
	 * @return registros con los documentos de la programacion de mantenimientos, tipo List<ProgramacionMantenimientoDocumento>.
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<ProgramacionMantenimientoDocumento> obtenerDocumentosProgramacionMantenimiento(Map<String,Object> parametros)throws Exception;
	
	/**
	 * Metodo que permite guardar los documentos de la programacion de mantenimientos.
	 * @param programacionMantenimientoDocumento documentos de la programacion de mantenimientos, tipo ProgramacionMantenimientoDocumento.
	 * @param archivo archivo adjunto, tipo Archivo.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void guardarDocumentoProgramacionMantenimiento(ProgramacionMantenimientoDocumento programacionMantenimientoDocumento, Archivo archivo) throws Exception;
	
	/**
	 * Metodo que permite modificar los documentos de la programacion de mantenimientos.
	 * @param programacionMantenimientoDocumento documentos de la programacion de mantenimientos, tipo ProgramacionMantenimientoDocumento.
	 * @param archivo archivo adjunto, tipo Archivo.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void modificarDocumentoProgramacionMantenimiento(ProgramacionMantenimientoDocumento programacionMantenimientoDocumento, Archivo archivo)throws Exception;
	
	/**
	 * Metodo que permite eliminar el documento de la programacion de mantenimientos.
	 * @param programacionMantenimientoDocumento documentos de la programacion de mantenimientos, tipo ProgramacionMantenimientoDocumento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public void eliminarDocumentoProgramacionMantenimiento(ProgramacionMantenimientoDocumento programacionMantenimientoDocumento) throws Exception;
		
	/**
	 * Método que permite obtener los datos del documento de la programacion de mantenimiento.
	 * @param codigoProgramacionMantenimiento identificador del documento de la programacion de mantenimiento, tipo long.
	 * @return bean con los datos del documento, tipo ProgramacionMantenimientoDocumento.
	 * @throws Exception excepción generada en caso de error.
	 */
	public ProgramacionMantenimientoDocumento obtenerDatosDocumentoProgramacionMantenimiento(Long codigoProgramacionMantenimiento)throws Exception;

	/**
	 * Método que permite obtener la cantidad de registros de los documentos de la programacion de mantenimiento.
	 * @param programacionMantenimientoDocumento bean con los datos de búsqueda de la programacion de mantenimiento, tipo ProgramacionMantenimientoDocumento.
	 * @return número de registros de los documentos de la programacion de mantenimiento, tipo int.
	 * @throws Exception excepción generada en caso de error.
	 */
	public int obtenerCantidadRegistrosBusquedaProgramacionMantenimiento(ProgramacionMantenimientoDocumento programacionMantenimientoDocumento)throws Exception;

	/**
	 * Método que permite obtener el documento adjunto.
	 * @param documento bean con los datos de búsqueda del documento, tipo ProgramacionMantenimientoDocumento.
	 * @return objeto con los datos del documento, tipo Archivo.
	 * @throws Exception excepción generada en caso de error.
	 */
	public Archivo obtenerDocumentoAdjunto(ProgramacionMantenimientoDocumento documento) throws Exception;
	
	/**
	 * Método que permite obtener los documentos para el reporte de ficha de programacion de mantenimiento.
	 * @param codigoProgramacion identificador de la programacion de mantenimiento, tipo String.
	 * @return lista de documentos de la programacion de mantenimiento, tipo List<ProgramacionMantenimientoDocumento>
	 * @throws Exception excepción generada en caso de error.
	 */
	public List<ProgramacionMantenimientoDocumento> obtenerDocumentosFichaProgramacionMantenimientoDocumento(int codigoProgramacion) throws Exception;
}
