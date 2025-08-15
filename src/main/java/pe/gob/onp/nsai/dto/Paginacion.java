/**
 * Resumen.
 * Objeto               :   Paginacion.java.
 * Descripción          :   DTO utilizado para encapsular los campos de la páginación.
 * Fecha de Creación    :   09/04/2018
 * PE de Creación       :   4181
 * Autor                :   Joseph Mena
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;

/**
 * Clase utilizado para encapsular los campos de la páginación. */
public class Paginacion implements Serializable{

	/** Número de serie generadp */
	private static final long serialVersionUID = -9134314839779518110L;
	
	/**
	 * Atributo para guardar el número total de registros recuperados.
	 */
	private int numeroTotalRegistros;
	
	/**
	 * Método que permite obtener el número total de registros recuperados.
	 * @return numeroTotalRegistros número total de registros recuperados, tipo int.
	 */
	public int getNumeroTotalRegistros() {
		return numeroTotalRegistros;
	}

	/**
	 * Método que permite establecer el número total de registros recuperados.
	 * @param numeroTotalRegistros número total de registros recuperados, tipo int.
	 */
	public void setNumeroTotalRegistros(int numeroTotalRegistros) {
		this.numeroTotalRegistros = numeroTotalRegistros;
	}
	
}
