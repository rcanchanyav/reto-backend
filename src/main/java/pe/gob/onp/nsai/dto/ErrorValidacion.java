/**
 * Resumen.
 * Objeto               :   ErrorValidacion.java.
 * Descripción          :   Clase utilizado para la validar la lista de errores.
 * Fecha de Creación    :   16/04/2018
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
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizado para la validar la lista de errores.
 */
public class ErrorValidacion implements Serializable{

	/**  Número de serie generado de la clase	 */
	private static final long serialVersionUID = -1488977625726556055L;
	
	/** Lista de errores */
	private List<String> errores=new ArrayList<>();
	
	/**
	 * Método que permite agregar los errores a la lista.
	 * @param error mensaje de error, tipo String.
	 */
	public void agregarError(String error){
		this.errores.add(error);
	}

	/**
	 * Método que permite obtener los errores de la lista.
	 * @return errores lista de errores, tipo List<String>.
	 */
	public List<String> getErrores() {
		return errores;
	}
	
}
