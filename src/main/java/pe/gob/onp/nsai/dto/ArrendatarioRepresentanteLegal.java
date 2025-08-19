/**
 * Resumen.
 * Objeto               :   ArrendatarioRepresentanteLegal.java.
 * Descripción          :   DTO utilizado para encapsular los campos del representante legal del arrendatario.
 * Fecha de Creación    :   10/05/2018
 * PE de Creación       :   2018-0059
 * Autor                :   Pedro Aguilar
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0060					   06/06/2018		Joseph Mena		 GeneradorID se refactoriza a una libreria general.
 *								   28/06/2018		James Torres	 Se agrega el atributo estadoCivil con sus respectivos métodos get y set.
 * PE2018-0117					   12/09/2018		James Torres	 Se modifican los comentarios.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import pe.gob.onp.nsai.util.GeneradorID;

/** DTO utilizado para encapsular los campos del representante legal del arrendatario */
public class ArrendatarioRepresentanteLegal implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = -7783841867583539376L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId= new IdHash();

    /** Identificador del representante legal. */
    private Integer idRepresentanteLegal;

    /** Nombre del arrendatario. */
    private String nombreArrendatario;

    /** Identificador del arrendatario. */
    private String idArrendatario;

    /** Tipo de representante legal. */
    @NotNull(message="{arrendatario.representante.tipoRepresentanteLegal.notNull}")
    @Size(min = 2, max = 2,message="{arrendatario.representante.tipoRepresentanteLegal.size}")
    private String tipoRepresentanteLegal;

    /** Tipo de persona. */
    @NotNull(message="{arrendatario.representante.tipoPersona.notNull}")
    @Size(min = 2, max = 2,message="{arrendatario.representante.tipoPersona.size}")
    private String tipoPersona;

    /** código del Tipo de Persona.*/
    private String codigoTipoPersona;

    /** Descripción del cargo del representante legal. */
    @Size(max = 30,message="{arrendatario.representante.descripcionCargo.size}")
    private String descripcionCargo;

    /** Documento de identidad. */
    @Size(max = 2,message="{arrendatario.representante.documentoIdentidad.size}")
    private String documentoIdentidad;

    /** Número del documento de identidad. */
    @Size(max = 20,message="{arrendatario.representante.numDocumentoIdentidad.size}")
    private String numDocumentoIdentidad;

    /** Apellido paterno. */
    @Size(max = 50,message="{arrendatario.representante.apellidoPaterno.size}")
    private String apellidoPaterno;

    /** Apellido materno. */
    @Size(max = 50,message="{arrendatario.representante.apellidoMaterno.size}")
    private String apellidoMaterno;

    /** Nombre del representante legal. */
    @Size(max = 50,message="{arrendatario.representante.nombreRepresentante.size}")
    private String nombreRepresentante;

    /** Razón social del representante legal. */
    @Size(max = 100,message="{arrendatario.representante.razonSocialRepresentante.size}")
    private String razonSocialRepresentante;

    /** RUC del representante legal. */
    @Size(max = 11,message="{arrendatario.representante.rucRepresentante.size}")
    private String rucRepresentante;

    /** Tipo de via. */
    @Size(max = 2,message="{arrendatario.representante.tipoViaRepresentante.size}")
    private String tipoViaRepresentante;

    /** Nombre de via. */
    @Size(max = 100,message="{arrendatario.representante.nombreViaRepresentante.size}")
    private String nombreViaRepresentante;

    /** Número de ingreso. */
    @Size(max = 10,message="{arrendatario.representante.numIngresoRepresentante.size}")
    private String numIngresoRepresentante;

    /** Número de interior. */
    @Size(max = 10,message="{arrendatario.representante.numInteriorRepresentante.size}")
    private String numInteriorRepresentante;

    /** Tipo de zona. */
    @Size(max = 2,message="{arrendatario.representante.tipoZonaRepresentante.size}")
    private String tipoZonaRepresentante;

    /** Nombre de la zona. */
    @Size(max = 100,message="{arrendatario.representante.nombreZonaRepresentante.size}")
    private String nombreZonaRepresentante;

    /** Manzana de la dirección. */
    @Size(max = 18,message="{arrendatario.representante.manzanaRepresentante.size}")
    private String manzanaRepresentante;

    /** Lote de la dirección. */
    @Size(max = 10,message="{arrendatario.representante.loteRepresentante.size}")
    private String loteRepresentante;

    /** Dirección del representante. */
    @Size(max = 500,message="{arrendatario.representante.direccionRepresentante.size}")
    private String direccionRepresentante;

    /** Referencia de la dirección. */
    @Size(max = 500,message="{arrendatario.representante.referenciaRepresentante.size}")
    private String referenciaRepresentante;

    /** Departamento del representante. */
    @Size(max = 6,message="{arrendatario.representante.departamentoRepresentante.size}")
    private String departamentoRepresentante;

    /** Provincia del representante. */
    @Size(max = 6,message="{arrendatario.representante.provinciaRepresentante.size}")
    private String provinciaRepresentante;

    /** Distrito del representante. */
    @Size(max = 6,message="{arrendatario.representante.distritoRepresentante.size}")
    private String distritoRepresentante;

    /** área geográfica del arrendatario. */
    private String codigoAreaGeografica;

    /** Género del representante legal. */
    //@Size(max = 1,message="{arrendatario.representante.generoRepresentante.size}")
    private String generoRepresentante;

    /** Fecha de nacimiento del representante legal. */
    private Date fechaNacimiento;

    /** Correo electronico del representante legal. */
    @Size(max = 100,message="{arrendatario.representante.correoElectronico.size}")
    private String correoElectronico;

    /** Telefono del domicilio. */
    @Size(max = 20,message="{arrendatario.representante.telefonoDomicilio.size}")
    private String telefonoDomicilio;

    /** Telefono del centro laboral. */
    @Size(max = 20,message="{arrendatario.representante.telefonoCentroLaboral.size}")
    private String telefonoCentroLaboral;

    /** Anexo del centro laboral */
    @Size(max = 20,message="{arrendatario.representante.anexoCentroLaboral.size}")
    private String anexoCentroLaboral;

    /** Partida registral del representante legal. */
    @Size(max = 100,message="{arrendatario.representante.partidaRegistral.size}")
    private String partidaRegistral;

    /** Archivo de la partida registral. */
    @Size(max = 50,message="{arrendatario.representante.archivoPartidaRegistral.size}")
    private String archivoPartidaRegistral;

    /** Nombre del archivo de la partida registral. */
    @Size(max = 100,message="{arrendatario.representante.nombreArchivoPartida.size}")
    private String nombreArchivoPartida;

    /** Fecha de nombramiento. */
    private Date fechaNombramiento;

    /** Tipo registral. */
    @Size(max = 2,message="{arrendatario.representante.nombreArchivoPartida.size}")
    private String tipoRegistral;

    /** Indicador de vigencia. */
    @NotNull(message="{arrendatario.representante.indicadorVigencia.notNull}")
    @Size(min = 1, max = 1,message="{arrendatario.representante.indicadorVigencia.size}")
    private String indicadorVigencia;

    /** Estado del registro. */
    private String estadoRegistro;

    /** Estado civil del representante. */
    private String estadoCivil;

    private String idContrato;

    private Long idContratoArrendatario;

    private Long item;

    private String idFilenet;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /**
     * Método que obtiene el identificador del representante legal.
     * @return idRepresentanteLegal Identificador del representante legal, tipo Integer.
     */
    public Integer getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    /**
     * Método que establece el identificador del representante legal.
     * @param idRepresentanteLegal Identificador del representante legal, tipo Integer.
     */
    public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
		/*if(this.hashId.getIdSecundario() == null){
			this.hashId.setIdSecundario(GeneradorID.codificarID(idRepresentanteLegal));
			this.idRepresentanteLegal = idRepresentanteLegal;
		}else{*/
        this.idRepresentanteLegal = idRepresentanteLegal;
        //}
    }

    /**
     * Método que obtiene el identificador del arrendatario.
     * @return idArrendatario Identificador del arrendatario, tipo String.
     */
    public String getIdArrendatario() {
        return idArrendatario;
    }

    /**
     * Método que establece el identificador del arrendatario.
     * @param idArrendatario Identificador del arrendatario, tipo String.
     */
    public void setIdArrendatario(String idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    /**
     * Método que obtiene el tipo de representante legal.
     * @return tipoRepresentanteLegal Tipo de representante legal, tipo String.
     */
    public String getTipoRepresentanteLegal() {
        return tipoRepresentanteLegal;
    }

    /**
     * Método que establece el tipo de representante legal.
     * @param tipoRepresentanteLegal Tipo de representante legal, tipo String.
     */
    public void setTipoRepresentanteLegal(String tipoRepresentanteLegal) {
        this.tipoRepresentanteLegal = tipoRepresentanteLegal;
    }

    /**
     * Método que obtiene el tipo de persona.
     * @return tipoPersona Tipo de persona, tipo String.
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Método que establece el tipo de persona.
     * @param tipoPersona Tipo de persona, tipo String.
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Método que obtiene la descripción del cargo del representante legal.
     * @return descripcionCargo Descripción del cargo del representante legal, tipo String.
     */
    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    /**
     * Método que establece la descripción del cargo del representante legal.
     * @param descripcionCargo Descripción del cargo del representante legal, tipo String.
     */
    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    /**
     * Método que obtiene el documento de identidad del representante legal.
     * @return documentoIdentidad Documento de identidad del representante legal, tipo String.
     */
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    /**
     * Método que establece el documento de identidad del representante legal.
     * @param documentoIdentidad Documento de identidad del representante legal, tipo String.
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    /**
     * Método que obtiene el número del documento de identidad del representante legal.
     * @return numDocumentoIdentidad Número del documento de identidad del representante legal, tipo String.
     */
    public String getNumDocumentoIdentidad() {
        return numDocumentoIdentidad;
    }

    /**
     * Método que establece el número del documento de identidad del representante legal.
     * @param numDocumentoIdentidad Número del documento de identidad del representante legal, tipo String.
     */
    public void setNumDocumentoIdentidad(String numDocumentoIdentidad) {
        this.numDocumentoIdentidad = numDocumentoIdentidad;
    }

    /**
     * Método que obtiene el apellido paterno del representante legal.
     * @return apellidoPaterno Apellido paterno del representante legal, tipo String.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que establece el apellido paterno del representante legal.
     * @param apellidoPaterno Apellido paterno del representante legal, tipo String.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Método que obtiene el apellido materno del representante legal.
     * @return apellidoMaterno Apellido materno del representante legal, tipo String.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que establece el apellido materno del representante legal.
     * @param apellidoMaterno Apellido materno del representante legal, tipo String.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que obtiene el nombre del representante legal.
     * @return nombreRepresentante Nombre del representante legal, tipo String.
     */
    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    /**
     * Método que establece el nombre del representante legal.
     * @param nombreRepresentante Nombre del representante legal, tipo String.
     */
    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    /**
     * Método que obtiene la razón social del representante legal.
     * @return razonSocialRepresentante Razón social del representante legal, tipo String.
     */
    public String getRazonSocialRepresentante() {
        return razonSocialRepresentante;
    }

    /**
     * Método que establece la razón social del representante legal.
     * @param razonSocialRepresentante Razón social del representante legal, tipo String.
     */
    public void setRazonSocialRepresentante(String razonSocialRepresentante) {
        this.razonSocialRepresentante = razonSocialRepresentante;
    }

    /**
     * Método que obtiene el RUC del representante legal.
     * @return rucRepresentante RUC del representante legal, tipo String.
     */
    public String getRucRepresentante() {
        return rucRepresentante;
    }

    /**
     * Método que establece el RUC del representante legal.
     * @param rucRepresentante RUC del representante legal, tipo String.
     */
    public void setRucRepresentante(String rucRepresentante) {
        this.rucRepresentante = rucRepresentante;
    }

    /**
     * Método que obtiene el tipo de via del representante legal.
     * @return tipoViaRepresentante Tipo de via del representante legal, tipo String.
     */
    public String getTipoViaRepresentante() {
        return tipoViaRepresentante;
    }

    /**
     * Método que establece el tipo de via del representante legal.
     * @param tipoViaRepresentante Tipo de via del representante legal, tipo String.
     */
    public void setTipoViaRepresentante(String tipoViaRepresentante) {
        this.tipoViaRepresentante = tipoViaRepresentante;
    }

    /**
     * Método que obtiene el nombre de via del representante legal.
     * @return nombreViaRepresentante Nombre de via del representante legal, tipo String.
     */
    public String getNombreViaRepresentante() {
        return nombreViaRepresentante;
    }

    /**
     * Método que establece el nombre de via del representante legal.
     * @param nombreViaRepresentante Nombre de via del representante legal, tipo String.
     */
    public void setNombreViaRepresentante(String nombreViaRepresentante) {
        this.nombreViaRepresentante = nombreViaRepresentante;
    }

    /**
     * Método que obtiene el número de ingreso del representante legal.
     * @return numIngresoRepresentante Número de ingreso del representante legal, tipo String.
     */
    public String getNumIngresoRepresentante() {
        return numIngresoRepresentante;
    }

    /**
     * Método que establece el número de ingreso del representante legal.
     * @param numIngresoRepresentante Número de ingreso del representante legal, tipo String.
     */
    public void setNumIngresoRepresentante(String numIngresoRepresentante) {
        this.numIngresoRepresentante = numIngresoRepresentante;
    }

    /**
     * Método que obtiene el número de interior del representante legal.
     * @return numInteriorRepresentante Número de interior del representante legal, tipo String.
     */
    public String getNumInteriorRepresentante() {
        return numInteriorRepresentante;
    }

    /**
     * Método que establece el número de interior del representante legal.
     * @param numInteriorRepresentante Número de interior del representante legal, tipo String.
     */
    public void setNumInteriorRepresentante(String numInteriorRepresentante) {
        this.numInteriorRepresentante = numInteriorRepresentante;
    }

    /**
     * Método que obtiene el tipo de zona del representante legal.
     * @return tipoZonaRepresentante Tipo de zona del representante legal, tipo String.
     */
    public String getTipoZonaRepresentante() {
        return tipoZonaRepresentante;
    }

    /**
     * Método que establece el tipo de zona del representante legal.
     * @param tipoZonaRepresentante Tipo de zona del representante legal, tipo String.
     */
    public void setTipoZonaRepresentante(String tipoZonaRepresentante) {
        this.tipoZonaRepresentante = tipoZonaRepresentante;
    }

    /**
     * Método que obtiene el nombre de la zona del representante legal.
     * @return nombreZonaRepresentante Nombre de la zona del representante legal, tipo String.
     */
    public String getNombreZonaRepresentante() {
        return nombreZonaRepresentante;
    }

    /**
     * Método que establece el nombre de la zona del representante legal.
     * @param nombreZonaRepresentante Nombre de la zona del representante legal, tipo String.
     */
    public void setNombreZonaRepresentante(String nombreZonaRepresentante) {
        this.nombreZonaRepresentante = nombreZonaRepresentante;
    }

    /**
     * Método que obtiene la manzana de la dirección del representante legal.
     * @return manzanaRepresentante Manzana de la dirección del representante legal, tipo String.
     */
    public String getManzanaRepresentante() {
        return manzanaRepresentante;
    }

    /**
     * Método que establece la manzana de la dirección del representante legal.
     * @param manzanaRepresentante Manzana de la dirección del representante legal, tipo String.
     */
    public void setManzanaRepresentante(String manzanaRepresentante) {
        this.manzanaRepresentante = manzanaRepresentante;
    }

    /**
     * Método que obtiene el lote de la dirección del representante legal.
     * @return loteRepresentante Lote de la dirección del representante legal, tipo String.
     */
    public String getLoteRepresentante() {
        return loteRepresentante;
    }

    /**
     * Método que establece el lote de la dirección del representante legal.
     * @param loteRepresentante Lote de la dirección del representante legal, tipo String.
     */
    public void setLoteRepresentante(String loteRepresentante) {
        this.loteRepresentante = loteRepresentante;
    }

    /**
     * Método que obtiene la dirección del representante legal.
     * @return direccionRepresentante Dirección del representante legal, tipo String.
     */
    public String getDireccionRepresentante() {
        return direccionRepresentante;
    }

    /**
     * Método que establece la dirección del representante legal.
     * @param direccionRepresentante Dirección del representante legal, tipo String.
     */
    public void setDireccionRepresentante(String direccionRepresentante) {
        this.direccionRepresentante = direccionRepresentante;
    }

    /**
     * Método que obtiene la referencia de la dirección del representante legal.
     * @return referenciaRepresentante Referencia de la dirección del representante legal, tipo String.
     */
    public String getReferenciaRepresentante() {
        return referenciaRepresentante;
    }

    /**
     * Método que establece la referencia de la dirección del representante legal.
     * @param referenciaRepresentante Referencia de la dirección del representante legal, tipo String.
     */
    public void setReferenciaRepresentante(String referenciaRepresentante) {
        this.referenciaRepresentante = referenciaRepresentante;
    }

    /**
     * Método que obtiene el departamento del representante legal.
     * @return departamentoRepresentante Departamento del representante legal, tipo String.
     */
    public String getDepartamentoRepresentante() {
        return departamentoRepresentante;
    }

    /**
     * Método que establece el departamento del representante legal.
     * @param departamentoRepresentante Departamento del representante legal, tipo String.
     */
    public void setDepartamentoRepresentante(String departamentoRepresentante) {
        this.departamentoRepresentante = departamentoRepresentante;
    }

    /**
     * Método que obtiene la provincia del representante legal.
     * @return provinciaRepresentante Provincia del representante legal, tipo String.
     */
    public String getProvinciaRepresentante() {
        return provinciaRepresentante;
    }

    /**
     * Método que establece la provincia del representante legal.
     * @param provinciaRepresentante Provincia del representante legal, tipo String.
     */
    public void setProvinciaRepresentante(String provinciaRepresentante) {
        this.provinciaRepresentante = provinciaRepresentante;
    }

    /**
     * Método que obtiene el distrito del representante legal.
     * @return distritoRepresentante Distrito del representante legal, tipo String.
     */
    public String getDistritoRepresentante() {
        return distritoRepresentante;
    }

    /**
     * Método que establece el distrito del representante legal.
     * @param distritoRepresentante Distrito del representante legal, tipo String.
     */
    public void setDistritoRepresentante(String distritoRepresentante) {
        this.distritoRepresentante = distritoRepresentante;
    }

    /**
     * Método que obtiene el código de área geográfica del representante legal.
     * @return codigoAreaGeografica Código del área geográfica del representante legal, tipo String.
     */
    public String getCodigoAreaGeografica() {
        return codigoAreaGeografica;
    }

    /**
     * Método que establece el código de área geográfica del representante legal.
     * @param codigoAreaGeografica Código del área geográfica del representante legal, tipo String.
     */
    public void setCodigoAreaGeografica(String codigoAreaGeografica) {
        this.codigoAreaGeografica = codigoAreaGeografica;
    }

    /**
     * Método que obtiene el género del representante legal.
     * @return generoRepresentante Género del representante legal, tipo String.
     */
    public String getGeneroRepresentante() {
        return generoRepresentante;
    }

    /**
     * Método que establece el género del representante legal.
     * @param generoRepresentante Género del representante legal, tipo String.
     */
    public void setGeneroRepresentante(String generoRepresentante) {
        this.generoRepresentante = generoRepresentante;
    }

    /**
     * Método que obtiene la fecha de nacimiento del representante legal.
     * @return fechaNacimiento Fecha de nacimiento del representante legal, tipo Date.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que establece la fecha de nacimiento del representante legal.
     * @param fechaNacimiento Fecha de nacimiento del representante legal, tipo Date.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Método que obtiene el correo electronico del representante legal.
     * @return correoElectronico Correo electronico del representante legal, tipo String.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Método que establece el correo electronico del representante legal.
     * @param correoElectronico Correo electronico del representante legal, tipo String.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Método que obtiene el teléfono del domicilio del representante legal.
     * @return telefonoDomicilio Telefono del domicilio del representante legal, tipo String.
     */
    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    /**
     * Método que establece el teléfono del domicilio del representante legal.
     * @param telefonoDomicilio Telefono del domicilio del representante legal, tipo String.
     */
    public void setTelefonoDomicilio(String telefonoDomicilio) {
        this.telefonoDomicilio = telefonoDomicilio;
    }

    /**
     * Método que obtiene el teléfono del centro laboral del representante legal.
     * @return telefonoCentroLaboral Telefono del centro laboral del representante legal, tipo String.
     */
    public String getTelefonoCentroLaboral() {
        return telefonoCentroLaboral;
    }

    /**
     * Método que establece el teléfono del centro laboral del representante legal.
     * @param telefonoCentroLaboral Telefono del centro laboral del representante legal, tipo String.
     */
    public void setTelefonoCentroLaboral(String telefonoCentroLaboral) {
        this.telefonoCentroLaboral = telefonoCentroLaboral;
    }

    /**
     * Método que obtiene el anexo del centro laboral del representante legal.
     * @return anexoCentroLaboral Anexo del centro laboral del representante legal, tipo String.
     */
    public String getAnexoCentroLaboral() {
        return anexoCentroLaboral;
    }

    /**
     * Método que establece el anexo del centro laboral del representante legal.
     * @param anexoCentroLaboral Anexo del centro laboral del representante legal, tipo String.
     */
    public void setAnexoCentroLaboral(String anexoCentroLaboral) {
        this.anexoCentroLaboral = anexoCentroLaboral;
    }

    /**
     * Método que obtiene la partida registral del representante legal.
     * @return partidaRegistral Partida registral del representante legal, tipo String.
     */
    public String getPartidaRegistral() {
        return partidaRegistral;
    }

    /**
     * Método que establece la partida registral del representante legal.
     * @param partidaRegistral Partida registral del representante legal, tipo String.
     */
    public void setPartidaRegistral(String partidaRegistral) {
        this.partidaRegistral = partidaRegistral;
    }

    /**
     * Método que obtiene el archivo de la partida registral del representante legal.
     * @return archivoPartidaRegistral Archivo de la partida registral del representante legal, tipo String.
     */
    public String getArchivoPartidaRegistral() {
        return archivoPartidaRegistral;
    }

    /**
     * Método que establece el archivo de la partida registral del representante legal.
     * @param archivoPartidaRegistral Archivo de la partida registral del representante legal, tipo String.
     */
    public void setArchivoPartidaRegistral(String archivoPartidaRegistral) {
        this.archivoPartidaRegistral = archivoPartidaRegistral;
    }

    /**
     * Método que obtiene el nombre del archivo de la partida registral del representante legal.
     * @return nombreArchivoPartida Nombre del archivo de la partida registral del representante legal, tipo String.
     */
    public String getNombreArchivoPartida() {
        return nombreArchivoPartida;
    }

    /**
     * Método que establece el nombre del archivo de la partida registral del representante legal.
     * @param nombreArchivoPartida Nombre del archivo de la partida registral del representante legal, tipo String.
     */
    public void setNombreArchivoPartida(String nombreArchivoPartida) {
        this.nombreArchivoPartida = nombreArchivoPartida;
    }

    /**
     * Método que obtiene la fecha de nombramiento del representante legal.
     * @return fechaNombramiento Fecha de nombramiento del representante legal, tipo Date.
     */
    public Date getFechaNombramiento() {
        return fechaNombramiento;
    }

    /**
     * Método que establece la fecha de nombramiento del representante legal.
     * @param fechaNombramiento Fecha de nombramiento del representante legal, tipo Date.
     */
    public void setFechaNombramiento(Date fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    /**
     * Método que obtiene el tipo registral del representante legal.
     * @return tipoRegistral Tipo registral del representante legal, tipo String.
     */
    public String getTipoRegistral() {
        return tipoRegistral;
    }

    /**
     * Método que establece el tipo registral del representante legal.
     * @param tipoRegistral Tipo registral del representante legal, tipo String.
     */
    public void setTipoRegistral(String tipoRegistral) {
        this.tipoRegistral = tipoRegistral;
    }

    /**
     * Método que obtiene el indicador de vigencia del representante legal.
     * @return indicadorVigencia Indicador de vigencia del representante legal, tipo String.
     */
    public String getIndicadorVigencia() {
        return indicadorVigencia;
    }

    /**
     * Método que establece el indicador de vigencia del representante legal.
     * @param indicadorVigencia Indicador de vigencia del representante legal, tipo String.
     */
    public void setIndicadorVigencia(String indicadorVigencia) {
        this.indicadorVigencia = indicadorVigencia;
    }

    /**
     * Método que obtiene el estado del registro.
     * @return estadoRegistro Estado del registro, tipo String.
     */
    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * Método que establece el estado del registro.
     * @param estadoRegistro Estado del registro, tipo String.
     */
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
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
     * Método que obtiene el nombre del arrendatario.
     * @return nombreArrendatario Nombre del arrendatario, tipo String.
     */
    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    /**
     * Método que establece el nombre del arrendatario.
     * @param nombreArrendatario Nombre del arrendatario, tipo String.
     */
    public void setNombreArrendatario(String nombreArrendatario) {
        this.nombreArrendatario = nombreArrendatario;
    }

    /**
     * Método que obtiene el tipo de persona.
     * @return codigoTipoPersona tipo de persona, tipo String.
     */
    public String getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    /**
     * Método que establece el tipo de persona.
     * @param codigoTipoPersona tipo de persona, tipo String.
     */
    public void setCodigoTipoPersona(String codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    /**
     * Método que obtiene el estado civil del representante.
     * @return estadoCivil estado civil del representante, tipo String.
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Método que establece el estado civil del representante.
     * @param estadoCivil estado civil del representante, tipo String.
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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
     * @return the item
     */
    public Long getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Long item) {
        this.item = item;
    }

    /**
     * @return the idContratoArrendatario
     */
    public Long getIdContratoArrendatario() {
        return idContratoArrendatario;
    }

    /**
     * @param idContratoArrendatario the idContratoArrendatario to set
     */
    public void setIdContratoArrendatario(Long idContratoArrendatario) {
        this.idContratoArrendatario = idContratoArrendatario;
    }

    /**
     * @return the idFilenet
     */
    public String getIdFilenet() {
        return idFilenet;
    }

    /**
     * @param idFilenet the idFilenet to set
     */
    public void setIdFilenet(String idFilenet) {
        this.idFilenet = idFilenet;
    }

}