package pe.gob.onp.nsai.dto;

import jakarta.validation.constraints.Size;

public class EntregaPredioDocumento {
    /** Número de versión de la clase Serializable */
    private static final long serialVersionUID = 5137875889481046904L;

    /** Identificador del Documento del acta */
    private Long idActaDocumento;

    /** Identificador del Contrato */
    private String idContrato;

    /** Descripción del Documento asociado al contrato */
    private String descDocumento;

    /** Observacion del documento del acta */
    private String observacionDocumento;

    /** Codigo de la imagen del documento */
    private String codigoImagenDocumentoAsociado;

    /** Nombre del archivo */
    private String nombreArchivoDocumento;

    /** Descripción del documento */
    private String descripcionDocumento;

    /** Estado del registro. 1: Activo, 0: Inactivo */
    private String indicadorEstadoDocumento;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /** Datos de ids generados */
    private IdHash hashId=new IdHash();

    /**
     * @return the idActaDocumento
     */
    public Long getIdActaDocumento() {
        return idActaDocumento;
    }

    /**
     * @param idActaDocumento the idActaDocumento to set
     */
    public void setIdActaDocumento(Long idActaDocumento) {
        this.idActaDocumento = idActaDocumento;
    }

    /**
     * @return the idContrato
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato the idContrato to set
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the descDocumento
     */
    public String getDescDocumento() {
        return descDocumento;
    }

    /**
     * @param descDocumento the descDocumento to set
     */
    public void setDescDocumento(String descDocumento) {
        this.descDocumento = descDocumento;
    }

    /**
     * @return the observacionDocumento
     */
    public String getObservacionDocumento() {
        return observacionDocumento;
    }

    /**
     * @param observacionDocumento the observacionDocumento to set
     */
    public void setObservacionDocumento(String observacionDocumento) {
        this.observacionDocumento = observacionDocumento;
    }

    /**
     * @return the codigoImagenDocumentoAsociado
     */
    public String getCodigoImagenDocumentoAsociado() {
        return codigoImagenDocumentoAsociado;
    }

    /**
     * @param codigoImagenDocumentoAsociado the codigoImagenDocumentoAsociado to set
     */
    public void setCodigoImagenDocumentoAsociado(String codigoImagenDocumentoAsociado) {
        this.codigoImagenDocumentoAsociado = codigoImagenDocumentoAsociado;
    }

    /**
     * @return the nombreArchivoDocumento
     */
    public String getNombreArchivoDocumento() {
        return nombreArchivoDocumento;
    }

    /**
     * @param nombreArchivoDocumento the nombreArchivoDocumento to set
     */
    public void setNombreArchivoDocumento(String nombreArchivoDocumento) {
        this.nombreArchivoDocumento = nombreArchivoDocumento;
    }

    /**
     * @return the descripcionDocumento
     */
    public String getDescripcionDocumento() {
        return descripcionDocumento;
    }

    /**
     * @param descripcionDocumento the descripcionDocumento to set
     */
    public void setDescripcionDocumento(String descripcionDocumento) {
        this.descripcionDocumento = descripcionDocumento;
    }

    /**
     * @return the indicadorEstadoDocumento
     */
    public String getIndicadorEstadoDocumento() {
        return indicadorEstadoDocumento;
    }

    /**
     * @param indicadorEstadoDocumento the indicadorEstadoDocumento to set
     */
    public void setIndicadorEstadoDocumento(String indicadorEstadoDocumento) {
        this.indicadorEstadoDocumento = indicadorEstadoDocumento;
    }

    /**
     * @return the auditoria
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * @return the hashId
     */
    public IdHash getHashId() {
        return hashId;
    }

    /**
     * @param hashId the hashId to set
     */
    public void setHashId(IdHash hashId) {
        this.hashId = hashId;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
