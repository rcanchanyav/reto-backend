/**
 * Resumen.
 * Objeto               :   ArrendatarioConyuge.java.
 * Descripción          :   DTO utilizado para encapsular los campos del conyuge del arrendatario.
 * Fecha de Creación    :   10/05/2018
 * PE de Creación       :   2018-0059
 * Autor                :   Pedro Aguilar
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0060					  06/06/2018		Joseph Mena		 GeneradorID se refactoriza a una libreria general.
 * PE2018-0117					  12/09/2018		James Torres	 Se modifican los comentarios.
 * 								  16/10/2018		Pedro Aguilar    Se agregan atributos a la clase.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

import pe.gob.onp.nsai.util.GeneradorID;

/**
 *Clase que contiene los atributos del conyuge del arrendatario.
 */
public class ArrendatarioConyuge implements Serializable {

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 3373258472488250101L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId=new IdHash();

    /** Identificador del conyuge del arrendatario. */
    private Integer idConyugeArendatario;

    /** Identificador del arrendatario. */
    private String idArrendatario;

    /** Tipo de documento de identidad del conyuge. */
    private String documentoIdentidadConyuge;

    /** Número de documento de indetidad del conyuge. */
    private String numeroDocumentoIdConyuge;

    /** Apellido paterno del conyuge. */
    private String apellidoPaternoConyuge;

    /** Apellido materno del conyuge. */
    private String apellidoMaternoConyuge;

    /** Nombre del conyuge. */
    private String nombreConyuge;

    /** Género del conyuge. */
    private String generoConyuge;

    /** Fecha nacimiento del conyuge. */
    private Date fechaNacimientoConyuge;

    /** Edad del conyuge. */
    private Integer edadConyuge;

    /** RUC del conyuge. */
    private String rucConyuge;

    /** Telefono del conyuge. */
    private String telefonoConyuge;

    /** Celular del conyuge. */
    private String celularConyuge;

    /** Correo electronico del conyuge. */
    private String correoElectronicoConyuge;

    /** Telefono del centro laboral del conyuge. */
    private String telefonoConyugeCentroLaboral;

    /** Anexo del conyuge. */
    private String anexoConyuge;

    /** Indicador de estado del registro. */
    private String indicadorEstadoRegistro;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /** Número de documento de tipo DNI. */
    private String numeroDocumentoIdConyugeDni;

    /** Número de documento de tipo extranjeria. */
    private String numeroDocumentoIdConyugeExt;

    /** Fecha de Fallecimiento del Conyuge */
    private Date regFechaFallecimientoConyuge;

    /**  Fecha de registro de Fallecimiento del Conyuge */
    private Date fechaFallecimientoConyuge;

    /**
     * Constructor de la clase.
     */
    public ArrendatarioConyuge() {
        super();
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
     * Método que obtiene el identificador del conyuge del arrendatario.
     * @return idConyugeArendatario identificador del conyuge del arrendatario, tipo Integer.
     */
    public Integer getIdConyugeArendatario() {
        return idConyugeArendatario;
    }

    /**
     * Método que establece el identificador del conyuge del arrendatario.
     * @param idConyugeArendatario identificador del conyuge del arrendatario, tipo Integer.
     */
    public void setIdConyugeArendatario(Integer idConyugeArendatario) {
        this.idConyugeArendatario = idConyugeArendatario;


    }

    /**
     * Método que obtiene el identificador del arrendatario.
     * @return idArrendatario identificador del arrendatario, tipo String.
     */
    public String getIdArrendatario() {
        return idArrendatario;
    }

    /**
     * Método que establece el identificador del arrendatario.
     * @param idArrendatario identificador del arrendatario, tipo String.
     */
    public void setIdArrendatario(String idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    /**
     * Método que obtiene el documento de identidad del arrendatario.
     * @return documentoIdentidadConyuge documento de identidad del arrendatario, tipo String.
     */
    public String getDocumentoIdentidadConyuge() {
        return documentoIdentidadConyuge;
    }

    /**
     * Método que establece el documento de identidad del arrendatario.
     * @param documentoIdentidadConyuge documento de identidad del arrendatario, tipo String.
     */
    public void setDocumentoIdentidadConyuge(String documentoIdentidadConyuge) {
        this.documentoIdentidadConyuge = documentoIdentidadConyuge;
    }

    /**
     * Método que obtiene el número del documento de identidad del arrendatario.
     * @return numeroDocumentoIdConyuge número del documento de identidad del arrendatario, tipo String.
     */
    public String getNumeroDocumentoIdConyuge() {
        return numeroDocumentoIdConyuge;
    }

    /**
     * Método que establece el número del documento de identidad del arrendatario.
     * @param numeroDocumentoIdConyuge número del documento de identidad del arrendatario, tipo String.
     */
    public void setNumeroDocumentoIdConyuge(String numeroDocumentoIdConyuge) {
        this.numeroDocumentoIdConyuge = numeroDocumentoIdConyuge;
    }

    /**
     * Método que obtiene el apellido paterno del conyuge del arrendatario.
     * @return apellidoPaternoConyuge apellido paterno del conyuge del arrendatario, tipo String.
     */
    public String getApellidoPaternoConyuge() {
        return apellidoPaternoConyuge;
    }

    /**
     * Método que establece el apellido paterno del conyuge del arrendatario.
     * @param apellidoPaternoConyuge apellido paterno del conyuge del arrendatario, tipo String.
     */
    public void setApellidoPaternoConyuge(String apellidoPaternoConyuge) {
        this.apellidoPaternoConyuge = apellidoPaternoConyuge;
    }

    /**
     * Método que obtiene el apellido materno del conyuge del arrendatario.
     * @return apellidoMaternoConyuge apellido materno del conyuge del arrendatario, tipo String.
     */
    public String getApellidoMaternoConyuge() {
        return apellidoMaternoConyuge;
    }

    /**
     * Método que establece el apellido materno del conyuge del arrendatario.
     * @param apellidoMaternoConyuge apellido materno del conyuge del arrendatario, tipo String.
     */
    public void setApellidoMaternoConyuge(String apellidoMaternoConyuge) {
        this.apellidoMaternoConyuge = apellidoMaternoConyuge;
    }

    /**
     * Método que obtiene el nombre del conyuge del arrendatario.
     * @return nombreConyuge nombre del conyuge del arrendatario, tipo String.
     */
    public String getNombreConyuge() {
        return nombreConyuge;
    }

    /**
     * Método que establece el nombre del conyuge del arrendatario.
     * @param nombreConyuge nombre del conyuge del arrendatario, tipo String.
     */
    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }

    /**
     * Método que obtiene el género del conyuge del arrendatario.
     * @return generoConyuge género del conyuge del arrendatario, tipo String.
     */
    public String getGeneroConyuge() {
        return generoConyuge;
    }

    /**
     * Método que establece el género del conyuge del arrendatario.
     * @param generoConyuge género del conyuge del arrendatario, tipo String.
     */
    public void setGeneroConyuge(String generoConyuge) {
        this.generoConyuge = generoConyuge;
    }

    /**
     * Método que obtiene la fecha de nacimiento del conyuge del arrendatario.
     * @return fechaNacimientoConyuge fecha de nacimiento del conyuge del arrendatario, tipo Date.
     */
    public Date getFechaNacimientoConyuge() {
        return fechaNacimientoConyuge;
    }

    /**
     * Método que establece la fecha de nacimiento del conyuge del arrendatario.
     * @param fechaNacimientoConyuge fecha de nacimiento del conyuge del arrendatario, tipo Date.
     */
    public void setFechaNacimientoConyuge(Date fechaNacimientoConyuge) {
        this.fechaNacimientoConyuge = fechaNacimientoConyuge;
    }

    /**
     * Método que obtiene la edad del conyuge del arrendatario.
     * @return edadConyuge edad del conyuge del arrendatario, tipo Integer.
     */
    public Integer getEdadConyuge() {
        return edadConyuge;
    }

    /**
     * Método que establece la edad del conyuge del arrendatario.
     * @param edadConyuge edad del conyuge del arrendatario, tipo Integer.
     */
    public void setEdadConyuge(Integer edadConyuge) {
        this.edadConyuge = edadConyuge;
    }

    /**
     * Método que obtiene el RUC del conyuge del arrendatario.
     * @return rucConyuge RUC del conyuge del arrendatario, tipo String.
     */
    public String getRucConyuge() {
        return rucConyuge;
    }

    /**
     * Método que establece el RUC del conyuge del arrendatario.
     * @param rucConyuge RUC del conyuge del arrendatario, tipo String.
     */
    public void setRucConyuge(String rucConyuge) {
        this.rucConyuge = rucConyuge;
    }

    /**
     * Método que obtiene el teléfono del conyuge del arrendatario.
     * @return telefonoConyuge teléfono del conyuge del arrendatario, tipo String.
     */
    public String getTelefonoConyuge() {
        return telefonoConyuge;
    }

    /**
     * Método que establece el teléfono del conyuge del arrendatario.
     * @param telefonoConyuge teléfono del conyuge del arrendatario, tipo String.
     */
    public void setTelefonoConyuge(String telefonoConyuge) {
        this.telefonoConyuge = telefonoConyuge;
    }

    /**
     * Método que obtiene el celular del conyuge del arrendatario.
     * @return celularConyuge celular del conyuge del arrendatario, tipo String.
     */
    public String getCelularConyuge() {
        return celularConyuge;
    }

    /**
     * Método que establece el celular del conyuge del arrendatario.
     * @param celularConyuge celular del conyuge del arrendatario, tipo String.
     */
    public void setCelularConyuge(String celularConyuge) {
        this.celularConyuge = celularConyuge;
    }

    /**
     * Método que obtiene el correo electronico del conyuge del arrendatario.
     * @return correoElectronicoConyuge correo electronico del conyuge del arrendatario, tipo String.
     */
    public String getCorreoElectronicoConyuge() {
        return correoElectronicoConyuge;
    }

    /**
     * Método que establece el correo electronico del conyuge del arrendatario.
     * @param correoElectronicoConyuge correo electronico del conyuge del arrendatario, tipo String.
     */
    public void setCorreoElectronicoConyuge(String correoElectronicoConyuge) {
        this.correoElectronicoConyuge = correoElectronicoConyuge;
    }

    /**
     * Método que obtiene el anexo del conyuge del arrendatario.
     * @return anexoConyuge anexo del conyuge del arrendatario, tipo String.
     */
    public String getAnexoConyuge() {
        return anexoConyuge;
    }

    /**
     * Método que establece el anexo del conyuge del arrendatario.
     * @param anexoConyuge anexo del conyuge del arrendatario, tipo String.
     */
    public void setAnexoConyuge(String anexoConyuge) {
        this.anexoConyuge = anexoConyuge;
    }

    /**
     * Método que obtiene el teléfono del centro laboral del conyuge del arrendatario.
     * @return telefonoConyugeCentroLaboral teléfono del centro laboral del conyuge del arrendatario, tipo String.
     */
    public String getTelefonoConyugeCentroLaboral() {
        return telefonoConyugeCentroLaboral;
    }

    /**
     * Método que establece el teléfono del centro laboral del conyuge del arrendatario.
     * @param telefonoConyugeCentroLaboral teléfono del centro laboral del conyuge del arrendatario, tipo String.
     */
    public void setTelefonoConyugeCentroLaboral(String telefonoConyugeCentroLaboral) {
        this.telefonoConyugeCentroLaboral = telefonoConyugeCentroLaboral;
    }

    /**
     * Método que obtiene el indicador de estado del registro del conyuge del arrendatario.
     * @return indicadorEstadoRegistro indicador de estado del registro del conyuge del arrendatario, tipo String.
     */
    public String getIndicadorEstadoRegistro() {
        return indicadorEstadoRegistro;
    }

    /**
     * Método que establece el indicador de estado del registro del conyuge del arrendatario.
     * @param indicadorEstadoRegistro indicador de estado del registro del conyuge del arrendatario, tipo String.
     */
    public void setIndicadorEstadoRegistro(String indicadorEstadoRegistro) {
        this.indicadorEstadoRegistro = indicadorEstadoRegistro;
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
     * @param auditoria objeto auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Método que obtiene el número de documento de tipo DNI.
     * @return numeroDocumentoIdConyugeDni número de documento de tipo DNI, tipo String.
     */

    public String getNumeroDocumentoIdConyugeDni() {
        return numeroDocumentoIdConyugeDni;
    }

    /**
     * Método que establece el número de documento de tipo DNI.
     * @param numeroDocumentoIdConyugeDni número de documento de tipo DNI, tipo String.
     */

    public void setNumeroDocumentoIdConyugeDni(String numeroDocumentoIdConyugeDni) {
        this.numeroDocumentoIdConyugeDni = numeroDocumentoIdConyugeDni;
    }

    /**
     * Método que obtiene el número de documento de tipo extranjeria.
     * @return numeroDocumentoIdConyugeExt número de documento de tipo extranjeria, tipo String.
     */

    public String getNumeroDocumentoIdConyugeExt() {
        return numeroDocumentoIdConyugeExt;
    }

    /**
     * Método que establece el número de documento de tipo extranjeria.
     * @param numeroDocumentoIdConyugeExt número de documento de tipo extranjeria, tipo String.
     */

    public void setNumeroDocumentoIdConyugeExt(String numeroDocumentoIdConyugeExt) {
        this.numeroDocumentoIdConyugeExt = numeroDocumentoIdConyugeExt;
    }


    /**
     * @return the fechaFallecimientoConyuge
     */
    public Date getFechaFallecimientoConyuge() {
        return fechaFallecimientoConyuge;
    }

    /**
     * @param fechaFallecimientoConyuge the fechaFallecimientoConyuge to set
     */
    public void setFechaFallecimientoConyuge(Date fechaFallecimientoConyuge) {
        this.fechaFallecimientoConyuge = fechaFallecimientoConyuge;
    }

    /**
     * @return the regFechaFallecimientoConyuge
     */
    public Date getRegFechaFallecimientoConyuge() {
        return regFechaFallecimientoConyuge;
    }

    /**
     * @param regFechaFallecimientoConyuge the regFechaFallecimientoConyuge to set
     */
    public void setRegFechaFallecimientoConyuge(Date regFechaFallecimientoConyuge) {
        this.regFechaFallecimientoConyuge = regFechaFallecimientoConyuge;
    }


}