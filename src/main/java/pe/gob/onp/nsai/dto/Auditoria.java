/**
 * Resumen.
 * Objeto               :   Auditoria.java.
 * Descripción          :   DTO utilizado para encapsular los campos de auditoria.
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
import java.util.Date;

/**
 * Clase utilizado para encapsular los campos de auditoria.
 */
public class Auditoria implements Serializable {
	

	/** Número de versión de la clase Serializable*/
	private static final long serialVersionUID = 8365670504393699804L;
	
	/** Identificador de usuario de creación. */
    private String usuarioCreacion;
    
    /** Fecha de creación. */
    private Date fechaCreacion;
    
    /** IP de creación. */
    private String terminalCreacion;
    
    /** Identificador de usuario de modificación.*/
    private String usuarioModificacion;
    
    /** Fecha de modificación. */
    private Date fechaModificacion;
    
    /** IP de modificación. */
    private String terminalModificacion;
    
	/**
	 * Permite obtener el identificador del usuario de creación.
	 * @return usuarioCreacion Atributo que guarda el identificador del usuario de creación, tipo String.
	 */
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }
    
    /**
     * Permite actualizar el identificador del usuario de creación.
     * @param usuarioCreacion Atributo que guarda el identificador del usuario de creación, tipo String.
     */
    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
    
    /**
     * Permite obtener la fecha de creación.
     * @return fechaCreacion Atributo que guarda la fecha de creacion, tipo Date.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Permite obtener el terminal de creación.
     * @return terminalCreacion Atributo que guarda el terminal de creación, tipo String.
     */
    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    /**
     * Permite actualizar el terminal de creación.
     * @param terminalCreacion Atributo que guarda el terminal de creación, tipo String.
     */
    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    /**
     * Permite obtener el identificador del usuario de modificación.
     * @return usuarioModificacion Atributo que guarda el identificador del usuario de modificación, tipo String.
     */
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    /**
     * Permite actualizar el identificador del usuario de modificación.
     * @param usuarioModificacion Atributo que guarda el identificador del  usuario de modificación, tipo String.
     */
    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    /**
     * Permite obtener la fecha de modificación.
     * @return fechaModificacion Atributo que guarda la fecha de modificación, tipo Date.
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * Permite obtener el terminal de modificación.
     * @return terminalModificacion Atributo que guarda el terminal de modificación, tipo String.
     */
    public String getTerminalModificacion() {
        return terminalModificacion;
    }

    /**
     * Permite actualizar el terminal de modificación.
     * @param terminalModificacion Atributo que guarda el terminal de modificación, tipo String.
     */
    public void setTerminalModificacion(String terminalModificacion) {
        this.terminalModificacion = terminalModificacion;
    }

	@Override
	public String toString() {
		return "Auditoria [usuarioCreacion=" + usuarioCreacion + ", fechaCreacion=" + fechaCreacion
				+ ", terminalCreacion=" + terminalCreacion + ", usuarioModificacion=" + usuarioModificacion
				+ ", fechaModificacion=" + fechaModificacion + ", terminalModificacion=" + terminalModificacion + "]";
	}
    
    
}
