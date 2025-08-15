/**
 * Resumen.
 * Objeto               :   IdHash.java.
 * Descripción          :   Clase utilizado para encapsular los identificadores.
 * Fecha de Creación    :   16/04/2018
 * PE de Creación       :   4181
 * Autor                :   Joseph Mena
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0117					   04/10/2018		 James Torres			 Se agregan los atributos idCuarto y idQuinto.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;

/** Clase utilizado para encapsular los identificadores */
public class IdHash implements Serializable {

	private static final long serialVersionUID = 8276994803941472815L;

	/** Constante para el identificador auxiliar */
	public static final String HASH_AUXILIAR="AAA";
	
	/** Identificador principal */
	private String idPrincipal;
	
	/** Identificador secundario  */
	private String idSecundario;
	
	/** Identificador terceario */
	private String idTerciario;
	
	/** Identificador cuarto */
	private String idCuarto;
	
	/** Identificador quinto */
	private String idQuinto;
	
	/**
	 * Método que obtiene el identificador principal.
	 * @return idPrincipal identificador principal, tipo String.
	 */
	public String getIdPrincipal() {
		return idPrincipal;
	}
	
	/**
	 *  Método que obtiene el  identificador principal.
	 * @param idPrincipal identificador principal, tipo String.
	 */
	public void setIdPrincipal(String idPrincipal) {
		this.idPrincipal = idPrincipal;
	}
	
	/**
	 * Método que obtiene el identificador secundario.
	 * @return idSecundario identificador secundario, tipo String.
	 */
	public String getIdSecundario() {
		return idSecundario;
	}
	
	/**
	 * Método que obtiene el identificador secundario.
	 * @param idSecundario identificador secundario, tipo String.
	 */
	public void setIdSecundario(String idSecundario) {
		this.idSecundario = idSecundario;
	}
	
	/**
	 *  Método que obtiene el identificador terceario.
	 * @return idTerciario identificador terceario, tipo String.
	 */
	public String getIdTerciario() {
		return idTerciario;
	}
	
	/**
	 *  Método que obtiene el identificador terceario.
	 * @param idTerciario identificador terceario, tipo String.
	 */
	public void setIdTerciario(String idTerciario) {
		this.idTerciario = idTerciario;
	}

	/**
	 * Método que obtiene el identificador cuarto.
	 * @return idCuarto identificador cuarto, tipo String.
	 */
	public String getIdCuarto() {
		return idCuarto;
	}

	/**
	 *  Método que establece el identificador cuarto.
	 * @param idCuarto identificador cuarto, tipo String.
	 */
	public void setIdCuarto(String idCuarto) {
		this.idCuarto = idCuarto;
	}

	/**
	 * Método que obtiene el identificador quinto.
	 * @return idQuinto identificador quinto, tipo String.
	 */
	public String getIdQuinto() {
		return idQuinto;
	}

	/**
	 * Método que establece el identificador quinto.
	 * @param idQuinto identificador quinto, tipo String.
	 */
	public void setIdQuinto(String idQuinto) {
		this.idQuinto = idQuinto;
	}
	
	
}
