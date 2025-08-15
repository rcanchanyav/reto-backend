/**
 * Resumen.
 * Objeto               :   ErrorSistema.java.
 * Descripción          :   Clase utilizada para mostrar los errores del sistema.
 * Fecha de Creación    :   04/04/2018
 * PE de Creación       :   4181
 * Autor                :   Pedro Peña
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * Clase utilizada para mostrar los errores del sistema.
 */
public class ErrorSistema implements Serializable{
	
	private static final long serialVersionUID = 2841886970507903233L;
	
	/** Variable que contiene el identificador del error. */
	private Long idError;
	private String id;
	private String mensaje;
	private String detalle;
	private Instant timestamp;
	
	/**
	 * Variable que contiene el identificador del error.
	 * @param idError identificador del error, tipo Long.
	 */
	public ErrorSistema(Long idError) {
		super();
		this.idError = idError;
	}

	public ErrorSistema(String id, String mensaje) {
		this.id = id; this.mensaje = mensaje;
	}


	/**
	 * Obtiene la variable del error
	 * @return idError identificador del error, tipo Long.
	 */
	public Long getIdError() {
		return idError;
	}
}
