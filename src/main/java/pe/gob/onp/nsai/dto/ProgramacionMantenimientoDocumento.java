/**
 * Resumen.
 * Objeto               :   ProgramacionMantenimientoDocumento.java.
 * Descripción          :   DTO utilizado para encapsular los campos de los documentos de la programación de
 * 							mantenimiento.
 * Fecha de Creación    :   05/02/2021
 * PE de Creación       :   INI-900
 * Autor                :   Igor Alexander Quispe Vasquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.dto;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import pe.gob.onp.nsai.util.GeneradorID;

/**
 *  Clase utilizada para encapsular los campos de los documentos del inmueble. 
 **/
public class ProgramacionMantenimientoDocumento implements Serializable {

	/** Número de versión de la clase Serializable */
	private static final long serialVersionUID = 1334469955071739167L;
	
	/** Código del documento de la programación de mantenimiento */
	private Long codigoProgramacionDocumento;
	
	/** Código de la programación del servicio */
	private Integer codigoProgramacion;
	
	/** Codigo Tipo de documento*/
	private String codigoTipoDocumento;
	
	/** Tipo del documento*/
	private String tipoDocumento;
	
	/** Nombre del archivo */	
	private String nombreArchivo;
	
	/** Nombre del documento */
	@Size(max = 100,message="{programacionMantenimientoDocumento.nombreDocumento.size}")
	private String nombreDocumento;
	
	/** Archivo */
	private String fileNetDocumento;
	
	/** Observación del documento */
	@Size(max = 500,message="{programacionMantenimientoDocumento.observacionDocumento.size}")
	private String observacionDocumento;
	
	/** Objeto de auditoria. */
    private Auditoria auditoria;
	
	/** Datos de ids generados */
	private IdHash hashId=new IdHash();

	/**
     * Método que obtiene el identificador del código del documento de la programacion.
     * @return codigoProgramacionDocumento identificador del documento de la programacion, tipo Integer.
     */
	public Long getCodigoProgramacionDocumento() {
		return codigoProgramacionDocumento;
	}

	/**
	 * Método que establece el identificador del código del documento de programación.
	 * @param codigoProgramacionDocumento identificador del documento de la programación, tipo Integer.
	 */
	public void setCodigoProgramacionDocumento(Long codigoProgramacionDocumento) {
		//this.codigoProgramacion = codigoProgramacion;
		
		if(this.hashId.getIdPrincipal() == null && codigoProgramacionDocumento != null){
			this.hashId.setIdPrincipal(GeneradorID.codificarID(codigoProgramacionDocumento));
			this.codigoProgramacionDocumento = null;
		}else{
			this.codigoProgramacionDocumento = codigoProgramacionDocumento;
		}
	}
	
	/**
     * Método que obtiene el identificador de la programación.
     * @return codigoProgramacion identificador de la programacion, tipo Integer.
     */
	public Integer getCodigoProgramacion() {
		return codigoProgramacion;
	}

	/**
	 * Método que establece el identificador del código de programación.
	 * @param codigoProgramacion identificador de la programación, tipo Integer.
	 */
	public void setCodigoProgramacion(Integer codigoProgramacion) {		
		this.codigoProgramacion = codigoProgramacion;		
	}

	//codigoTipoDocumento
	/**
     * Método que obtiene el código del tipo de documento.
     * @return codigoTipoDocumento código del tipo de documento, tipo String.
     */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	/**
	 * Método que establece el código del tipo de documento.
	 * @param codigoTipoDocumento código del tipo de documento, tipo String.
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	
	/**
     * Método que obtiene el tipo de documento.
     * @return tipoDocumento tipo de documento, tipo String.
     */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Método que establece el tipo de documento.
	 * @param tipoDocumento tipo de documento, tipo String.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
     * Método que obtiene el nombre del archivo.
     * @return nombreArchivo nombre del archivo, tipo String.
     */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Método que establece el nombre del archivo.
	 * @param nombreArchivo nombre del archivo, tipo String.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	/**
     * Método que obtiene el nombre del documento.
     * @return nombreDocumento nombre del documento, tipo String.
     */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Método que establece el nombre del documento.
	 * @param nombreDocumento nombre del documento, tipo String.
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
     * Método que obtiene la referencia de FileNet del Documento.
     * @return fileNetDocumento referencia de FileNet, tipo String.
     */
	public String getFileNetDocumento() {
		return fileNetDocumento;
	}

	/**
	 * Método que establece la referencia de FileNet del Documento.
	 * @param fileNetDocumento referencia de FileNet, tipo String.
	 */
	public void setFileNetDocumento(String fileNetDocumento) {
		this.fileNetDocumento = fileNetDocumento;
	}

	/**
     * Método que obtiene la observación del documento.
     * @return observacionDocumento observación del documento, tipo String.
     */
	public String getObservacionDocumento() {
		return observacionDocumento;
	}

	/**
	 * Método que establece la observación del documento.
	 * @param observacionDocumento observación del documento, tipo String.
	 */
	public void setObservacionDocumento(String observacionDocumento) {
		this.observacionDocumento = observacionDocumento;
	}
	
	/**
	 * Método que obtiene el objeto de auditoria .
	 * @return auditoria objeto auditoria, tipo Auditoria.
	 */
	public Auditoria getAuditoria() {
		return auditoria;
	}
	
	/**
	 * Método que establece el objeto de auditoria.
	 * @param auditoria objeto auditoria, , tipo Auditoria.
	 */
	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
	
	/**
	 * Método que permite obtener el hash id
	 * @return hashId objeto hash, tipo IdHash
	 */
	public IdHash getHashId() {
		return hashId;
	}
	
	/**
	 * Método que permite establecer el hash id
	 * @param hashId objeto hash, tipo IdHash
	 */
	public void setHashId(IdHash hashId) {
		this.hashId = hashId;
	}
	
}
