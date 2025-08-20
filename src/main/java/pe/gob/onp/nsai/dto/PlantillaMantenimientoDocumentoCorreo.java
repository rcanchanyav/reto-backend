/**
 * Resumen.
 * Objeto               :   PlantillaMantenimientoDocumentoCorreo.java.
 * Descripción          :   DTO utilizado para encapsular los campos de la plantillas de mantenimiento de documento y correo.
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
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
 *Clase que contiene los campos de la plantillas de los mantenimiento de documento y correo
 */
public class PlantillaMantenimientoDocumentoCorreo implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = -4428592464181175854L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId=new IdHash();

    /** Identificador de la plantilla del documento */
    private Integer idPlantillaDocumentoPrincipal;

    /** Plantilla del documento */
    private String plantilla;

    /** Código de la plantilla del documento */
    private String codPlantilla;

    /** Tipo de plantilla del documento */
    private String tipoPlantilla;

    /** Descripción de la plantilla del documento */
    private String descTipoPlantilla;

    /** Código del tipo de plantilla del documento */
    private String codigoTipoPlantillaDocumento;

    /** Estado de Registro */
    private String indicadorEstadoRegistro;

    /** Objeto de auditoria. */
    private Auditoria auditoria;



    private String codigoConceptos;

    private String tipoDocumento;

    private Date fechaCreacion;

    private String usuarioCreacion;



    /**
     * Método que permite obtener el identificador de la plantilla del documento.
     * @return idPlantillaDocumentoPrincipal identificador de la plantilla del documento, tipo Integer.
     */
    public Integer getIdPlantillaDocumentoPrincipal() {
        return idPlantillaDocumentoPrincipal;
    }

    /**
     * Método que permite establecer el identificador de la plantilla del documento.
     * @param idPlantillaDocumentoPrincipal identificador de la plantilla del documento, tipo Integer.
     */
    public void setIdPlantillaDocumentoPrincipal(Integer idPlantillaDocumentoPrincipal) {
		/*if(this.hashId.getIdSecundario() == null){
			this.hashId.setIdSecundario(GeneradorID.codificarID(idPlantillaDocumentoPrincipal));
			this.idPlantillaDocumentoPrincipal=null;
		}else{*/
        this.idPlantillaDocumentoPrincipal = idPlantillaDocumentoPrincipal;
        //}
    }

    /**
     * Método que permite obtener el tipo de plantilla.
     * @return tipoPlantilla tipo de plantilla, tipo String.
     */
    public String getTipoPlantilla() {
        return tipoPlantilla;
    }

    /**
     * Método que permite establecer el tipo de plantilla.
     * @param tipoPlantilla tipo de plantilla, tipo String.
     */
    public void setTipoPlantilla(String tipoPlantilla) {
        this.tipoPlantilla = tipoPlantilla;
    }

    /**
     * Método que obtiene el objeto instanciado para generar el id cifrado.
     * @return hashId objeto instanciado para generar el id cifrado, tipo IdHash.
     */
    public IdHash getHashId() {
        return hashId;
    }

    /**
     * Método que establece el objeto instanciado para generar el id cifrado.
     * @param hashId objeto instanciado para generar el id cifrado, tipo IdHash.
     */
    public void setHashId(IdHash hashId) {
        this.hashId = hashId;
    }

    /**
     * Método que permite obtener el indicador de estado del registro.
     * @return indicadorEstadoRegistro indicador de estado del registro, tipo String.
     */
    public String getIndicadorEstadoRegistro() {
        return indicadorEstadoRegistro;
    }

    /**
     * Método que permite establecer el indicador de estado del registro.
     * @param indicadorEstadoRegistro indicador de estado del registro, tipo String.
     */
    public void setIndicadorEstadoRegistro(String indicadorEstadoRegistro) {
        this.indicadorEstadoRegistro = indicadorEstadoRegistro;
    }

    /**
     * Método que obtiene el objeto de auditoria .
     * @return auditoria objeto de auditoria, tipo Auditoria.
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que establece el objeto de auditoria.
     * @param auditoria objeto de auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }


    /**
     * Método que obtiene el código de la plantilla contrato o adenda.
     * @return codigoTipoPlantillaDocumento código de la plantilla contrato o adenda, tipo String.
     */
    public String getCodigoTipoPlantillaDocumento() {
        return codigoTipoPlantillaDocumento;
    }

    /**
     * Método que establece el código de la plantilla contrato o adenda.
     * @param codigoTipoPlantillaDocumento código de la plantilla contrato o adenda, tipo String.
     */
    public void setCodigoTipoPlantillaDocumento(String codigoTipoPlantillaDocumento) {
        this.codigoTipoPlantillaDocumento = codigoTipoPlantillaDocumento;
    }


    /**
     * Método que obtiene la descripción de la plantilla contrato o adenda.
     * @return plantilla  descripción de la plantilla contrato o adenda, tipo String.
     */
    public String getPlantilla() {
        return plantilla;
    }

    /**
     * Método que establece la descripción de la plantilla contrato o adenda.
     * @param plantilla  descripción de la plantilla contrato o adenda, tipo String.
     */
    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    /**
     * Método que obtiene el código de la plantilla contrato o adenda.
     * @return codPlantilla código de la plantilla contrato o adenda, tipo String.
     */
    public String getCodPlantilla() {
        return codPlantilla;
    }

    /**
     * Método que establece el código de la plantilla contrato o adenda.
     * @param codPlantilla código de la plantilla contrato o adenda, tipo String.
     */
    public void setCodPlantilla(String codPlantilla) {
        this.codPlantilla = codPlantilla;
    }

    /**
     * Método que obtiene la descripción de la plantilla contrato o adenda.
     * @return descTipoPlantilla descripción de la plantilla contrato o adenda, tipo String.
     */
    public String getDescTipoPlantilla() {
        return descTipoPlantilla;
    }

    /**
     * Método que establece la descripción de la plantilla contrato o adenda.
     * @param descTipoPlantilla descripción de la plantilla contrato o adenda, tipo String.
     */
    public void setDescTipoPlantilla(String descTipoPlantilla) {
        this.descTipoPlantilla = descTipoPlantilla;
    }




    public String getCodigoConceptos() {
        return codigoConceptos;
    }

    public void setCodigoConceptos(String codigoConceptos) {
        this.codigoConceptos = codigoConceptos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

}
