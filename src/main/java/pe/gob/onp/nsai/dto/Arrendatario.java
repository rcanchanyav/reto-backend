/**
 * Resumen.
 * Objeto               :   Arrendatario.java.
 * Descripción          :   DTO utilizado para encapsular los campos de arrendatario.
 * Fecha de Creación    :   04/05/2018
 * PE de Creación       :   2018-0059
 * Autor                :   Pedro Aguilar
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0060					  26/06/2018		Pedro Peña 		Se agrega el atributo indicadorPublico con sus respectivos métodos get y set.
 * PE2018-0117					  16/08/2018		Pedro Aguilar			Se agregan los atributos fechaFallecimiento, registroFechaFallecimiento, indicadorFallecimiento y se modifican los comentarios.
 * 2021-INI900					  22/01/2021	    Jherson Huayhua		 	Se agrega el atributo idContrato con sus respectivos métodos get y set.
 *                                27/02/2023        Josue Odar              Se agregaron nuevos parametros.
 */
package pe.gob.onp.nsai.dto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *  DTO con los atributos del arrendatario.
 */
/**
 * @author PJODAR
 *
 */
public class Arrendatario implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = -967917836489242076L;

    /** Identificador del arrendatario. */
    private String idArrendatario;

    /** Tipo de arrendatario. */
    @NotNull(message="{arrendatario.tipoArrendatario.notNull}")
    @Size(min = 2, max = 2,message="{arrendatario.tipoArrendatario.size}")
    private String tipoArrendatario;

    /** Tipo de persona. */
    @NotNull(message="arrendatario.tipoPersona.notNull")
    @Size(min = 2, max = 2,message="{arrendatario.tipoPersona.size}")
    private String tipoPersona;

    /** Nombre del arrendatario. */
    @Size(max = 100,message="{arrendatario.nombre.size}")
    private String nombreArrendatario;

    /** Apellido paterno del arrendatario. */
    @Size(max = 50,message="{arrendatario.apellidoPaterno.size}")
    private String apellidoPaternoArrendatario;

    /** Apellido Materno del arrendatario. */
    @Size(max = 50,message="{arrendatario.apellidoMaterno.size}")
    private String apellidoMaternoArrendatario;

    /** Documento de Identidad del arrendatario. */
    @Size(max = 2,message="{arrendatario.documentoIdentidad.size}")
    private String documentoIdentidad;

    /** Número del documento de identidad del arrendatario. */
    @Size(max = 20,message="{arrendatario.numeroDocumentoIdentidad.size}")
    private String numeroDocumentoIdentidad;

    /** Número RUC del arrendatario. */
    @Size(max = 11,message="{arrendatario.numeroRuc.size}")
    private String numeroRucArrendatario;

    /** Estado Civil del arrendatario. */
    @Size(max = 1,message="{arrendatario.estadoCivil.size}")
    private String estadoCivil;

    /** Género del arrendatario. */
    @Size(max = 1,message="{arrendatario.genero.size}")
    private String generoArrendatario;

    /** Fecha de nacimiento del arrendatario. */
    private Date fechaNacimientoArrendatario;

    /** Edad del arrendatario. */
    @Digits(integer=3, fraction = 0 , message="{arrendatario.edad.digits}")
    private Integer edadArrendatario;

    /** Telefono fijo del arrendatario. */
    @Size(max = 20,message="{arrendatario.telefonoFijo.size}")
    private String telefonoFijo;

    /** Telefono celular del arrendatario. */
    @Size(max = 20,message="{arrendatario.telefonoCelular.size}")
    private String telefonoCelular;

    /** Fax del arrendatario. */
    private String fax;

    /** Correo electronico del arrendatario. */
    @Size(max = 100,message="{arrendatario.correoElectronico.size}")
    private String correoElectronico;

    /** Tipo de via. */
    @Size(max = 2,message="{arrendatario.tipoVia.size}")
    private String tipoVia;

    /** Nombre de via. */
    @Size(max = 100,message="{arrendatario.nombreVia.size}")
    private String nombreVia;

    /** Número de puerta. */
    @Size(max = 10,message="{arrendatario.numeroPuerta.size}")
    private String numeroPuerta;

    /** Número de interior de la dirección. */
    @Size(max = 10,message="{arrendatario.numeroInterior.size}")
    private String numeroInteriorDireccion;

    /** Número de bloque de la dirección. */
    @Size(max = 10,message="{arrendatario.numeroBloque.size}")
    private String numeroBloqueDireccion;



    /** Tipo de zona. */
    @Size(max = 2,message="{arrendatario.tipoZona.size}")
    private String tipoZona;

    /** Número de zona. */
    @Size(max = 100,message="{arrendatario.nombreZona.size}")
    private String nombreZona;

    /** Número de manzana. */
    @Size(max = 18,message="{arrendatario.numeroManzana.size}")
    private String numeroManzana;

    /** Número de lote. */
    @Size(max = 10,message="{arrendatario.numeroLote.size}")
    private String numeroLote;

    /** Dirección del arrendatario. */
    @NotNull(message="{arrendatario.direccion.notNull}")
    @Size(min = 1, max = 500,message="{arrendatario.direccion.size}")
    private String direccionArrendatario;

    /** Dirección de Referencia del arrendatario. */
    @Size(max = 500,message="{arrendatario.direccionReferencia.size}")
    private String direccionReferencia;

    /** Departamento del arrendatario. */
    @Size(min=6, max = 6,message="{arrendatario.departamentoArrendatario.size}")
    private String departamentoArrendatario;

    /** Provincia del arrendatario. */
    @Size(min=6, max = 6,message="{arrendatario.provinciaArrendatario.size}")
    private String provinciaArrendatario;

    /** Distrito del arrendatario. */
    @Size(min=6, max = 6,message="{arrendatario.distritoArrendatario.size}")
    private String distritoArrendatario;

    /** área geográfica del arrendatario. */
    private String codigoAreaGeografica;

    /** Observación del arrendatario. */
    private String observacionArrendatario;

    /** Nombre del contacto del arrendatario. */
    @Size(max = 200,message="{arrendatario.nombreContacto.size}")
    private String nombreContacto;

    /** Telefono del contacto del arrendatario. */
    @Size(max = 40,message="{arrendatario.telefonoContacto.size}")
    private String telefonoContacto;

    /** Celular del contacto del arrendatario. */
    private String celularContacto;

    /** Razón social del arrendatario. */
    @Size(max = 100,message="{arrendatario.razonSocialArrendatario.size}")
    private String razonSocialArrendatario;

    /** Vigencia del arrendatario. */
    @NotNull(message="{arrendatario.estadoVigencia.notNull}")
    @Size(min = 1, max = 1,message="{arrendatario.estadoVigencia.size}")
    private String estadoVigenciaArrendatario;

    /** Estado del registro. */
    private String estadoRegistro;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /** Razón social del centro laboral. */
    @Size(max = 100,message="{arrendatario.razonSocialCentroLaboral.size}")
    private String razonSocialCentroLaboral;

    /** Tipo de via del centro laboral. */
    @Size(max = 2,message="{arrendatario.tipoViaCentroLaboral.size}")
    private String tipoViaCentroLaboral;

    /** Nombre de via del centro laboral. */
    @Size(max = 100,message="{arrendatario.nombreViaCentroLaboral.size}")
    private String nombreViaCentroLaboral;

    /** Número de via del centro laboral. */
    @Size(max = 10,message="{arrendatario.numeroViaCentroLaboral.size}")
    private String numeroViaCentroLaboral;

    /** Número interior del centro laboral. */
    @Size(max = 10,message="{arrendatario.numeroInteriorCentroLaboral.size}")
    private String numeroInteriorCentroLaboral;

    /** Tipo de zona del centro laboal. */
    @Size(max = 2,message="{arrendatario.tipoZonaCentroLaboral.size}")
    private String tipoZonaCentroLaboral;

    /** Nombre de zona del centro laboral. */
    @Size(max = 100,message="{arrendatario.tipoZonaCentroLaboral.size}")
    private String nombreZonaCentroLaboral;

    /** Manzana del centro laboral. */
    @Size(max = 10,message="{arrendatario.manzanaCentroLaboral.size}")
    private String manzanaCentroLaboral;

    /** Lote del centro laboral. */
    @Size(max = 10,message="{arrendatario.loteCentroLaboral.size}")
    private String loteCentroLaboral;

    /** Dirección del centro laboral. */
    @Size(max = 500,message="{arrendatario.direccionCentroLaboral.size}")
    private String direccionCentroLaboral;

    /** Referencia del centro laboral. */
    @Size(max = 500,message="{arrendatario.referenciaCentroLaboral.size}")
    private String referenciaCentroLaboral;

    /** Departamento del centro laboral. */
    @Size(max = 6,message="{arrendatario.departamentoCentroLaboral.size}")
    private String departamentoCentroLaboral;

    /** Provincia del centro laboral. */
    @Size(max = 6,message="{arrendatario.provinciaCentroLaboral.size}")
    private String provinciaCentroLaboral;

    /** Distrito del centro laboral. */
    @Size(max = 6,message="{arrendatario.distritoCentroLaboral.size}")
    private String distritoCentroLaboral;

    /** área geográfica del centro laboral. */
    private String areaGeograficaCentroLaboral;

    /** Celular del centro laboral. */
    @Size(max = 20,message="{arrendatario.telefonoCentroLaboral.size}")
    private String telefonoCentroLaboral;

    /** Anexo del centro laboral. */
    @Size(max = 20,message="{arrendatario.anexoCentroLaboral.size}")
    private String anexoCentroLaboral;

    /** Fax del centro laboral. */
    private String faxCentroLaboral;

    /** Conyuge del arrendatario. */
    private ArrendatarioConyuge conyuge;

    /** Indicador del arrendatario. */
    private String indicadorPublico;

    /** Número del DNI del Arrendatario. */
    @Size(max = 20,message="{arrendatario.numeroDocumentoIdentidad.size}")
    private String numeroDocumentoIdentidadDni;

    /** Número del DNI del Arrendatario. */
    @Size(max = 20,message="{arrendatario.numeroDocumento.size}")
    private String numeroDocumento;

    /** Número del carnet de extranjeria del Arrendatario. */
    @Size(max = 20,message="{arrendatario.numeroDocumentoIdentidad.size}")
    private String numeroDocumentoIdentidadExt;

    /** Fecha de fallecimiento. */
    private Date fechaFallecimiento;

    /** Registro de la fecha de fallecimiento.*/
    private Date registroFechaFallecimiento;

    /** Indicador de fallecimiento. */
    @Size(max = 1,message="{arrendatario.indicadorFallecimiento.size}")
    private String indicadorFallecimiento;

    /** Descripción del estado de fallecimiento */
    private String descripcionFallecimiento;

    /** Contrato del comprador/arrendatario */
    private String idContrato;

    /** Indicador del arrendatario con negocio. */
    private String indicadorConNegocio;

    private String representante;

    private String nombreConyuge;

    private List<ArrendatarioRepresentanteLegal> listaRepresentanteLegal;

    private Long item;

    private boolean checked;

    private boolean consulta;

    /** Codigo Tipo de persona. */
    private String codigoTipoPersona;

    private String fechaEmision;

    private String fechaVencimiento;

    private String tipoComprobante;

    private int nuImprComprobantes;

    private double montoTotal;

    private String numeroOperacion;

    private String tipoMoneda;

    private String anioComprobante;

    private String tipoConcepto;

    private int cantidadRegistro;

    private String ocupacionPredio;

    private String rowDeudores;

    /**
     * Método que permite obtener el identificador del arrendatario.
     * @return idArrendatario identificador del arrendatario, tipo String.
     */
    public String getIdArrendatario() {
        return idArrendatario;
    }

    /**
     * Método que permite establecer el identificador del arrendatario.
     * @param idArrendatario identificador del arrendatario, tipo String.
     */
    public void setIdArrendatario(String idArrendatario) {
        this.idArrendatario = idArrendatario;
    }

    /**
     * Método que permite obtener el tipo de arrendatario.
     * @return tipoArrendatario tipo de arrendatario, tipo String.
     */
    public String getTipoArrendatario() {
        return tipoArrendatario;
    }

    /**
     * Método que permite establecer el tipo de arrendatario.
     * @param tipoArrendatario tipo de arrendatario, tipo String.
     */
    public void setTipoArrendatario(String tipoArrendatario) {
        this.tipoArrendatario = tipoArrendatario;
    }

    /**
     * Método que permite obtener el tipo de persona.
     * @return tipoPersona tipo de persona, tipo String.
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Método que permite establecer el tipo de persona.
     * @param tipoPersona tipo de persona, tipo String.
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Método que permite obtener el nombre del arrendatario.
     * @return nombreArrendatario nombre del arrendatario, tipo String.
     */
    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    /**
     * Método que permite establecer el nombre del arrendatario.
     * @param nombreArrendatario nombre del arrendatario, tipo String.
     */
    public void setNombreArrendatario(String nombreArrendatario) {
        this.nombreArrendatario = nombreArrendatario;
    }

    /**
     * Método que permite obtener el apellido paterno del arrendatario.
     * @return apellidoPaternoArrendatario apellido paterno del arrendatario, tipo String.
     */
    public String getApellidoPaternoArrendatario() {
        return apellidoPaternoArrendatario;
    }

    /**
     * Método que permite establecer el apellido paterno del arrendatario.
     * @param apellidoPaternoArrendatario apellido paterno del arrendatario, tipo String.
     */
    public void setApellidoPaternoArrendatario(String apellidoPaternoArrendatario) {
        this.apellidoPaternoArrendatario = apellidoPaternoArrendatario;
    }

    /**
     * Método que permite obtener el apellido materno del arrendatario.
     * @return apellidoMaternoArrendatario apellido materno del arrendatario, tipo String.
     */
    public String getApellidoMaternoArrendatario() {
        return apellidoMaternoArrendatario;
    }

    /**
     * Método que permite establecer el apellido materno del arrendatario.
     * @param apellidoMaternoArrendatario apellido materno del arrendatario, tipo String.
     */
    public void setApellidoMaternoArrendatario(String apellidoMaternoArrendatario) {
        this.apellidoMaternoArrendatario = apellidoMaternoArrendatario;
    }

    /**
     * Método que permite obtener el tipo del documento de identidad del arrendatario.
     * @return documentoIdentidad tipo del documento de identidad del arrendatario, tipo String.
     */
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    /**
     * Método que permite establecer el tipo del documento de identidad del arrendatario.
     * @param documentoIdentidad tipo del documento de identidad del arrendatario, tipo String.
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    /**
     * Método que permite obtener el número del documento de identidad del arrendatario.
     * @return numeroDocumentoIdentidad número del documento de identidad del arrendatario, tipo String.
     */
    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    /**
     * Método que permite establecer el número del documento de identidad del arrendatario.
     * @param numeroDocumentoIdentidad número del documento de identidad del arrendatario, tipo String.
     */
    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    /**
     * Método que permite obtener el número de RUC del arrendatario.
     * @return numeroRucArrendatario número de RUC del arrendatario, tipo String.
     */
    public String getNumeroRucArrendatario() {
        return numeroRucArrendatario;
    }

    /**
     * Método que permite establecer el número de RUC del arrendatario.
     * @param numeroRucArrendatario número de RUC del arrendatario, tipo String.
     */
    public void setNumeroRucArrendatario(String numeroRucArrendatario) {
        this.numeroRucArrendatario = numeroRucArrendatario;
    }

    /**
     * Método que permite obtener el estado civil del arrendatario.
     * @return estadoCivil estado civil del arrendatario, tipo String.
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Método que permite establecer el estado civil del arrendatario.
     * @param estadoCivil estado civil del arrendatario, tipo String.
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Método que permite obtener el género del arrendatario.
     * @return generoArrendatario género del arrendatario, tipo String.
     */
    public String getGeneroArrendatario() {
        return generoArrendatario;
    }

    /**
     * Método que permite establecer el género del arrendatario.
     * @param generoArrendatario género del arrendatario, tipo String.
     */
    public void setGeneroArrendatario(String generoArrendatario) {
        this.generoArrendatario = generoArrendatario;
    }

    /**
     * Método que permite obtener la fecha de nacimiento del arrendatario.
     * @return fechaNacimientoArrendatario fecha de nacimiento del arrendatario, tipo Date.
     */
    public Date getFechaNacimientoArrendatario() {
        return fechaNacimientoArrendatario;
    }

    /**
     * Método que permite establecer la fecha de nacimiento del arrendatario.
     * @param fechaNacimientoArrendatario fecha de nacimiento del arrendatario, tipo Date.
     */
    public void setFechaNacimientoArrendatario(Date fechaNacimientoArrendatario) {
        this.fechaNacimientoArrendatario = fechaNacimientoArrendatario;
    }

    /**
     * Método que permite obtener la edad del arrendatario.
     * @return edadArrendatario edad del arrendatario, tipo Integer.
     */
    public Integer getEdadArrendatario() {
        return edadArrendatario;
    }

    /**
     * Método que permite establecer la edad del arrendatario.
     * @param edadArrendatario edad del arrendatario, tipo Integer.
     */
    public void setEdadArrendatario(Integer edadArrendatario) {
        this.edadArrendatario = edadArrendatario;
    }

    /**
     * Método que permite obtener el teléfono fijo del arrendatario.
     * @return telefonoFijo teléfono fijo del arrendatario, tipo String.
     */
    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    /**
     * Método que permite establecer el teléfono fijo del arrendatario.
     * @param telefonoFijo teléfono fijo del arrendatario, tipo String.
     */
    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    /**
     * Método que permite obtener el teléfono celular del arrendatario.
     * @return telefonoCelular teléfono celular del arrendatario, tipo String.
     */
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * Método que permite establecer el teléfono celular del arrendatario.
     * @param telefonoCelular teléfono celular del arrendatario, tipo String.
     */
    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    /**
     * Método que permite obtener el fax del arrendatario.
     * @return fax fax del arrendatario, tipo String.
     */
    public String getFax() {
        return fax;
    }

    /**
     * Método que permite establecer el fax del arrendatario.
     * @param fax fax del arrendatario, tipo String.
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Método que permite obtener el correo electronico del arrendatario.
     * @return correoElectronico correo electronico, tipo String.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Método que permite establecer el correo electronico del arrendatario.
     * @param correoElectronico correo electronico, tipo String.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Método que permite obtener el tipo de via del arrendatario.
     * @return tipoVia tipo de via, tipo String.
     */
    public String getTipoVia() {
        return tipoVia;
    }

    /**
     * Método que permite establecer el tipo de via del arrendatario.
     * @param tipoVia tipo de via, tipo String.
     */
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    /**
     * Método que permite obtener el nombre de via del arrendatario.
     * @return nombreVia nombre de via, tipo String.
     */
    public String getNombreVia() {
        return nombreVia;
    }

    /**
     * Método que permite establecer el nombre de via del arrendatario.
     * @param nombreVia nombre de via, tipo String.
     */
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    /**
     * Método que permite obtener el número de puerta del arrendatario.
     * @return numeroPuerta número de puerta, tipo String.
     */
    public String getNumeroPuerta() {
        return numeroPuerta;
    }

    /**
     * Método que permite establecer el número de puerta del arrendatario.
     * @param numeroPuerta número de puerta, tipo String.
     */
    public void setNumeroPuerta(String numeroPuerta) {
        this.numeroPuerta = numeroPuerta;
    }

    /**
     * Método que permite obtener el número de interior de la dirección del arrendatario.
     * @return numeroInteriorDireccion número de interior de la dirección, tipo String.
     */
    public String getNumeroInteriorDireccion() {
        return numeroInteriorDireccion;
    }

    /**
     * Método que permite establecer el número de interior de la dirección del arrendatario.
     * @param numeroInteriorDireccion número de interior de la dirección, tipo String.
     */
    public void setNumeroInteriorDireccion(String numeroInteriorDireccion) {
        this.numeroInteriorDireccion = numeroInteriorDireccion;
    }

    /**
     * Método que permite obtener el tipo de zona del arrendatario.
     * @return tipoZona tipo de zona, tipo String.
     */
    public String getTipoZona() {
        return tipoZona;
    }

    /**
     * Método que permite establecer el tipo de zona del arrendatario.
     * @param tipoZona tipo de zona, tipo String.
     */
    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }

    /**
     * Método que permite obtener el nombre de zona del arrendatario.
     * @return nombreZona nombre de zona, tipo String.
     */
    public String getNombreZona() {
        return nombreZona;
    }

    /**
     * Método que permite establecer el nombre de zona del arrendatario.
     * @param nombreZona nombre de zona, tipo String.
     */
    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    /**
     * Método que permite obtener el número de manzana del arrendatario.
     * @return numeroManzana número de manzana, tipo String.
     */
    public String getNumeroManzana() {
        return numeroManzana;
    }

    /**
     * Método que permite establecer el número de manzana del arrendatario.
     * @param numeroManzana número de manzana, tipo String.
     */
    public void setNumeroManzana(String numeroManzana) {
        this.numeroManzana = numeroManzana;
    }

    /**
     * Método que permite obtener el número de lote del arrendatario.
     * @return numeroLote número de lote, tipo String.
     */
    public String getNumeroLote() {
        return numeroLote;
    }

    /**
     * Método que permite establecer el número de lote del arrendatario.
     * @param numeroLote número de lote, tipo String.
     */
    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    /**
     * Método que permite obtener la dirección del arrendatario.
     * @return direccionArrendatario dirección del arrendatario, tipo String.
     */
    public String getDireccionArrendatario() {
        return direccionArrendatario;
    }

    /**
     * Método que permite establecer la dirección del arrendatario.
     * @param direccionArrendatario dirección del arrendatario, tipo String.
     */
    public void setDireccionArrendatario(String direccionArrendatario) {
        this.direccionArrendatario = direccionArrendatario;
    }

    /**
     * Método que permite obtener la dirección de referencia del arrendatario.
     * @return direccionReferencia dirección de referencia del arrendatario, tipo String.
     */
    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    /**
     * Método que permite establecer la dirección de referencia del arrendatario.
     * @param direccionReferencia dirección de referencia del arrendatario, tipo String.
     */
    public void setDireccionReferencia(String direccionReferencia) {
        this.direccionReferencia = direccionReferencia;
    }

    /**
     * Método que permite obtener el departamento del arrendatario.
     * @return departamentoArrendatario departamento del arrendatario, tipo String.
     */
    public String getDepartamentoArrendatario() {
        return departamentoArrendatario;
    }

    /**
     * Método que permite establecer el departamento del arrendatario.
     * @param departamentoArrendatario departamento del arrendatario, tipo String.
     */
    public void setDepartamentoArrendatario(String departamentoArrendatario) {
        this.departamentoArrendatario = departamentoArrendatario;
    }

    /**
     * Método que permite obtener la provincia del arrendatario.
     * @return provinciaArrendatario provincia del arrendatario, tipo String.
     */
    public String getProvinciaArrendatario() {
        return provinciaArrendatario;
    }

    /**
     * Método que permite establecer la provincia del arrendatario.
     * @param provinciaArrendatario provincia del arrendatario, tipo String.
     */
    public void setProvinciaArrendatario(String provinciaArrendatario) {
        this.provinciaArrendatario = provinciaArrendatario;
    }

    /**
     * Método que permite obtener el distrito del arrendatario.
     * @return distritoArrendatario distrito del arrendatario, tipo String.
     */
    public String getDistritoArrendatario() {
        return distritoArrendatario;
    }

    /**
     * Método que permite establecer el distrito del arrendatario.
     * @param distritoArrendatario distrito del arrendatario, tipo String.
     */
    public void setDistritoArrendatario(String distritoArrendatario) {
        this.distritoArrendatario = distritoArrendatario;
    }

    /**
     * Método que permite obtener el área geográfica del arrendatario.
     * @return codigoAreaGeografica área geográfica del arrendatario, tipo String.
     */
    public String getCodigoAreaGeografica() {
        return codigoAreaGeografica;
    }

    /**
     * Método que permite establecer el área geográfica del arrendatario.
     * @param codigoAreaGeografica área geográfica del arrendatario, tipo String.
     */
    public void setCodigoAreaGeografica(String codigoAreaGeografica) {
        this.codigoAreaGeografica = codigoAreaGeografica;
    }

    /**
     * Método que permite obtener la observación del arrendatario.
     * @return observacionArrendatario observación del arrendatario, tipo String.
     */
    public String getObservacionArrendatario() {
        return observacionArrendatario;
    }

    /**
     * Método que permite establecer la observación del arrendatario.
     * @param observacionArrendatario observación del arrendatario, tipo String.
     */
    public void setObservacionArrendatario(String observacionArrendatario) {
        this.observacionArrendatario = observacionArrendatario;
    }

    /**
     * Método que permite obtener el nombre del contacto del arrendatario.
     * @return nombreContacto nombre del contacto del arrendatario, tipo String.
     */
    public String getNombreContacto() {
        return nombreContacto;
    }

    /**
     * Método que permite establecer el nombre del contacto del arrendatario.
     * @param nombreContacto nombre del contacto del arrendatario, tipo String.
     */
    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    /**
     * Método que permite obtener el teléfono del contacto del arrendatario.
     * @return telefonoContacto teléfono del contacto del arrendatario, tipo String.
     */
    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    /**
     * Método que permite establecer el teléfono del contacto del arrendatario.
     * @param telefonoContacto teléfono del contacto del arrendatario, tipo String.
     */
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    /**
     * Método que permite obtener el teléfono del contacto del arrendatario.
     * @return celularContacto teléfono del contacto del arrendatario, tipo String.
     */
    public String getCelularContacto() {
        return celularContacto;
    }

    /**
     * Método que permite establecer el teléfono del contacto del arrendatario.
     * @param celularContacto teléfono del contacto del arrendatario, tipo String.
     */
    public void setCelularContacto(String celularContacto) {
        this.celularContacto = celularContacto;
    }

    /**
     * Método que permite obtener el estado de vigencia del arrendatario.
     * @return estadoVigenciaArrendatario estado de vigencia del arrendatario, tipo String.
     */
    public String getEstadoVigenciaArrendatario() {
        return estadoVigenciaArrendatario;
    }

    /**
     * Método que permite establecer el estado de vigencia del arrendatario.
     * @param estadoVigenciaArrendatario estado de vigencia del arrendatario, tipo String.
     */
    public void setEstadoVigenciaArrendatario(String estadoVigenciaArrendatario) {
        this.estadoVigenciaArrendatario = estadoVigenciaArrendatario;
    }

    /**
     * Método que permite obtener el estado del registro del arrendatario.
     * @return estadoRegistro estado del registro del arrendatario, tipo String.
     */
    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * Método que permite establecer el estado del registro del arrendatario.
     * @param estadoRegistro estado del registro del arrendatario, tipo String.
     */
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * Método que permite obtener el objeto de auditoria.
     * @return auditoria objeto de auditoria, tipo Auditoria.
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que permite establecer el objeto de auditoria.
     * @param auditoria objeto de auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Método que permite obtener la razón social del centro laboral.
     * @return razonSocialCentroLaboral razón social, tipo String.
     */
    public String getRazonSocialCentroLaboral() {
        return razonSocialCentroLaboral;
    }

    /**
     * Método que permite establecer la razón social del centro laboral.
     * @param razonSocialCentroLaboral razón social, tipo String.
     */
    public void setRazonSocialCentroLaboral(String razonSocialCentroLaboral) {
        this.razonSocialCentroLaboral = razonSocialCentroLaboral;
    }

    /**
     * Método que permite obtener el tipo de via del centro laboral.
     * @return tipoViaCentroLaboral tipo de via, tipo String.
     */
    public String getTipoViaCentroLaboral() {
        return tipoViaCentroLaboral;
    }

    /**
     * Método que permite establecer el tipo de via del centro laboral.
     * @param tipoViaCentroLaboral tipo de via, tipo String.
     */
    public void setTipoViaCentroLaboral(String tipoViaCentroLaboral) {
        this.tipoViaCentroLaboral = tipoViaCentroLaboral;
    }

    /**
     * Método que permite obtener el nombre de via del centro laboral.
     * @return nombreViaCentroLaboral nombre de via, tipo String.
     */
    public String getNombreViaCentroLaboral() {
        return nombreViaCentroLaboral;
    }

    /**
     * Método que permite establecer el nombre de via del centro laboral.
     * @param nombreViaCentroLaboral nombre de via, tipo String.
     */
    public void setNombreViaCentroLaboral(String nombreViaCentroLaboral) {
        this.nombreViaCentroLaboral = nombreViaCentroLaboral;
    }

    /**
     * Método que permite obtener el número de via del centro laboral.
     * @return numeroViaCentroLaboral número de via, tipo String.
     */
    public String getNumeroViaCentroLaboral() {
        return numeroViaCentroLaboral;
    }

    /**
     * Método que permite establecer el número de via del centro laboral.
     * @param numeroViaCentroLaboral número de via, tipo String.
     */
    public void setNumeroViaCentroLaboral(String numeroViaCentroLaboral) {
        this.numeroViaCentroLaboral = numeroViaCentroLaboral;
    }

    /**
     * Método que permite obtener el número de interior del centro laboral.
     * @return numeroInteriorCentroLaboral número de interior, tipo String.
     */
    public String getNumeroInteriorCentroLaboral() {
        return numeroInteriorCentroLaboral;
    }

    /**
     * Método que permite establecer el número de interior del centro laboral.
     * @param numeroInteriorCentroLaboral número de interior, tipo String.
     */
    public void setNumeroInteriorCentroLaboral(String numeroInteriorCentroLaboral) {
        this.numeroInteriorCentroLaboral = numeroInteriorCentroLaboral;
    }

    /**
     * Método que permite obtener el tipo de zona del centro laboral.
     * @return tipoZonaCentroLaboral tipo de zona, tipo String.
     */
    public String getTipoZonaCentroLaboral() {
        return tipoZonaCentroLaboral;
    }

    /**
     * Método que permite establecer el tipo de zona del centro laboral.
     * @param tipoZonaCentroLaboral tipo de zona, tipo String.
     */
    public void setTipoZonaCentroLaboral(String tipoZonaCentroLaboral) {
        this.tipoZonaCentroLaboral = tipoZonaCentroLaboral;
    }

    /**
     * Método que permite obtener el nombre de zona del centro laboral.
     * @return nombreZonaCentroLaboral nombre de zona, tipo String.
     */
    public String getNombreZonaCentroLaboral() {
        return nombreZonaCentroLaboral;
    }

    /**
     * Método que permite establecer el nombre de zona del centro laboral.
     * @param nombreZonaCentroLaboral nombre de zona, tipo String.
     */
    public void setNombreZonaCentroLaboral(String nombreZonaCentroLaboral) {
        this.nombreZonaCentroLaboral = nombreZonaCentroLaboral;
    }

    /**
     * Método que permite obtener la manzana del centro laboral.
     * @return manzanaCentroLaboral manzana del centro laboral, tipo String.
     */
    public String getManzanaCentroLaboral() {
        return manzanaCentroLaboral;
    }

    /**
     * Método que permite establecer la manzana del centro laboral.
     * @param manzanaCentroLaboral manzana del centro laboral, tipo String.
     */
    public void setManzanaCentroLaboral(String manzanaCentroLaboral) {
        this.manzanaCentroLaboral = manzanaCentroLaboral;
    }

    /**
     * Método que permite obtener el lote del centro laboral.
     * @return loteCentroLaboral lote del centro laboral, tipo String.
     */
    public String getLoteCentroLaboral() {
        return loteCentroLaboral;
    }

    /**
     * Método que permite establecer el lote del centro laboral.
     * @param loteCentroLaboral lote del centro laboral, tipo String.
     */
    public void setLoteCentroLaboral(String loteCentroLaboral) {
        this.loteCentroLaboral = loteCentroLaboral;
    }

    /**
     * Método que permite obtener la dirección del centro laboral.
     * @return direccionCentroLaboral dirección del centro laboral, tipo String.
     */
    public String getDireccionCentroLaboral() {
        return direccionCentroLaboral;
    }

    /**
     * Método que permite establecer la dirección del centro laboral.
     * @param direccionCentroLaboral dirección del centro laboral, tipo String.
     */
    public void setDireccionCentroLaboral(String dirreccionCentroLaboral) {
        this.direccionCentroLaboral = dirreccionCentroLaboral;
    }

    /**
     * Método que permite obtener la referencia del centro laboral.
     * @return referenciaCentroLaboral referencia del centro laboral, tipo String.
     */
    public String getReferenciaCentroLaboral() {
        return referenciaCentroLaboral;
    }

    /**
     * Método que permite establecer la referencia del centro laboral.
     * @param referenciaCentroLaboral referencia del centro laboral, tipo String.
     */
    public void setReferenciaCentroLaboral(String referenciaCentroLaboral) {
        this.referenciaCentroLaboral = referenciaCentroLaboral;
    }

    /**
     * Método que permite obtener el área geográfica del centro laboral.
     * @return areaGeograficaCentroLaboral área geográfica del centro laboral, tipo String.
     */
    public String getAreaGeograficaCentroLaboral() {
        return areaGeograficaCentroLaboral;
    }

    /**
     * Método que permite establecer el área geográfica del centro laboral.
     * @param areaGeograficaCentroLaboral área geográfica del centro laboral, tipo String.
     */
    public void setAreaGeograficaCentroLaboral(String areaGeograficaCentroLaboral) {
        this.areaGeograficaCentroLaboral = areaGeograficaCentroLaboral;
    }

    /**
     * Método que permite obtener el celular del centro laboral.
     * @return telefonoCentroLaboral celular del centro laboral, tipo String.
     */
    public String getTelefonoCentroLaboral() {
        return telefonoCentroLaboral;
    }

    /**
     * Método que permite establecer el celular del centro laboral.
     * @param telefonoCentroLaboral celular del centro laboral, tipo String.
     */
    public void setTelefonoCentroLaboral(String telefonoCentroLaboral) {
        this.telefonoCentroLaboral = telefonoCentroLaboral;
    }

    /**
     * Método que permite obtener el anexo del centro laboral.
     * @return anexoCentroLaboral anexo del centro laboral, tipo String.
     */
    public String getAnexoCentroLaboral() {
        return anexoCentroLaboral;
    }

    /**
     * Método que permite establecer el anexo del centro laboral.
     * @param anexoCentroLaboral anexo del centro laboral, tipo String.
     */
    public void setAnexoCentroLaboral(String anexoCentroLaboral) {
        this.anexoCentroLaboral = anexoCentroLaboral;
    }

    /**
     * Método que permite obtener el fax del centro laboral.
     * @return faxCentroLaboral fax del centro laboral, tipo String.
     */
    public String getFaxCentroLaboral() {
        return faxCentroLaboral;
    }

    /**
     * Método que permite establecer el fax del centro laboral.
     * @param faxCentroLaboral fax del centro laboral, tipo String.
     */
    public void setFaxCentroLaboral(String faxCentroLaboral) {
        this.faxCentroLaboral = faxCentroLaboral;
    }

    /**
     * Método que permite obtener el departamento del centro laboral.
     * @return departamentoCentroLaboral departamento del centro laboral, tipo String.
     */
    public String getDepartamentoCentroLaboral() {
        return departamentoCentroLaboral;
    }

    /**
     * Método que permite establece el departamento del centro laboral.
     * @param departamentoCentroLaboral departamento del centro laboral, tipo String.
     */
    public void setDepartamentoCentroLaboral(String departamentoCentroLaboral) {
        this.departamentoCentroLaboral = departamentoCentroLaboral;
    }

    /**
     * Método que permite obtener la provincia del centro laboral.
     * @return provinciaCentroLaboral provincia del centro laboral, tipo String.
     */
    public String getProvinciaCentroLaboral() {
        return provinciaCentroLaboral;
    }

    /**
     * Método que permite establecer la provincia del centro laboral.
     * @param provinciaCentroLaboral provincia del centro laboral, tipo String.
     */
    public void setProvinciaCentroLaboral(String provinciaCentroLaboral) {
        this.provinciaCentroLaboral = provinciaCentroLaboral;
    }

    /**
     * Método que permite obtener el distrito del centro laboral.
     * @return distritoCentroLaboral distrito del centro laboral, tipo String.
     */
    public String getDistritoCentroLaboral() {
        return distritoCentroLaboral;
    }

    /**
     * Método que permite establecer el distrito del centro laboral.
     * @param distritoCentroLaboral distrito del centro laboral, tipo String.
     */
    public void setDistritoCentroLaboral(String distritoCentroLaboral) {
        this.distritoCentroLaboral = distritoCentroLaboral;
    }

    /**
     * Método que permite obtener la razón social del arrendatario.
     * @return razonSocialArrendatario razón social del arrendatario, tipo String.
     */
    public String getRazonSocialArrendatario() {
        return razonSocialArrendatario;
    }

    /**
     * Método que permite establecer la razón social del arrendatario.
     * @param razonSocialArrendatario razón social del arrendatario, tipo String.
     */
    public void setRazonSocialArrendatario(String razonSocialArrendatario) {
        this.razonSocialArrendatario = razonSocialArrendatario;
    }

    /**
     * Método que permite obtener el conyuge del arrendatario.
     * @return conyuge conyuge del arrendatario, tipo ArrendatarioConyuge.
     */
    public ArrendatarioConyuge getConyuge() {
        return conyuge;
    }

    /**
     * Método que permite establecer el conyuge del arrendatario.
     * @param conyuge conyuge del arrendatario, tipo ArrendatarioConyuge.
     */
    public void setConyuge(ArrendatarioConyuge conyuge) {
        this.conyuge = conyuge;
    }

    /**
     * Método que permite obtener el indicador del arrendatario.
     * @return indicadorPublico indicador del arrendatario, tipo String.
     */
    public String getIndicadorPublico() {
        return indicadorPublico;
    }

    /**
     *  Método que permite establecer el indicador del arrendatario.
     * @param indicadorPublico indicador del arrendatario, tipo String.
     */
    public void setIndicadorPublico(String indicadorPublico) {
        this.indicadorPublico = indicadorPublico;
    }

    /**
     * Método que obtiene el número de documento de identidad.
     * @return numeroDocumentoIdentidadDni número de documento de identidad, tipo String.
     */

    public String getNumeroDocumentoIdentidadDni() {
        return numeroDocumentoIdentidadDni;
    }

    /**
     * Método que establece el número de documento de identidad.
     * @param numeroDocumentoIdentidadDni número de documento de identidad, tipo String.
     */

    public void setNumeroDocumentoIdentidadDni(String numeroDocumentoIdentidadDni) {
        this.numeroDocumentoIdentidadDni = numeroDocumentoIdentidadDni;
    }


    /**
     * Método que obtiene el número de carnet de extranjeria.
     * @return numeroDocumentoIdentidadExt número de carnet de extranjeria, tipo String.
     */

    public String getNumeroDocumentoIdentidadExt() {
        return numeroDocumentoIdentidadExt;
    }

    /**
     * Método que establece el número de carnet de extranjeria.
     * @param numeroDocumentoIdentidadExt número de carnet de extranjeria, tipo String.
     */

    public void setNumeroDocumentoIdentidadExt(String numeroDocumentoIdentidadExt) {
        this.numeroDocumentoIdentidadExt = numeroDocumentoIdentidadExt;
    }

    /**
     * Método que obtiene la fecha de fallecimiento.
     * @return fechaFallecimiento fecha de fallecimiento, tipo Date.
     */

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    /**
     * Método que establece la fecha de fallecimiento.
     * @param fechaFallecimiento fecha de fallecimiento, tipo Date.
     */

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    /**
     * Método que obtiene la fecha del registro de fallecimiento.
     * @return registroFechaFallecimiento fecha del registro de fallecimiento, tipo Date.
     */

    public Date getRegistroFechaFallecimiento() {
        return registroFechaFallecimiento;
    }

    /**
     * Método que establece la fecha del registro de fallecimiento.
     * @param registroFechaFallecimiento fecha del registro de fallecimiento, tipo Date.
     */

    public void setRegistroFechaFallecimiento(Date registroFechaFallecimiento) {
        this.registroFechaFallecimiento = registroFechaFallecimiento;
    }

    /**
     * Método que obtiene el indicador de fallecimiento.
     * @return indicadorFallecimiento indicador de fallecimiento, tipo String.
     */

    public String getIndicadorFallecimiento() {
        return indicadorFallecimiento;
    }

    /**
     * Método que establece el indicador de fallecimiento.
     * @param indicadorFallecimiento indicador de fallecimiento, tipo String.
     */

    public void setIndicadorFallecimiento(String indicadorFallecimiento) {
        this.indicadorFallecimiento = indicadorFallecimiento;
    }

    /**
     * Método que obtiene la descripcion del fallecimiento.
     * @return descripcionFallecimiento ,tipo String.
     */
    public String getDescripcionFallecimiento() {
        return descripcionFallecimiento;
    }

    /**
     * Método que establece la descripcion del fallecimiento.
     * @param descripcionFallecimiento ,tipo String.
     */
    public void setDescripcionFallecimiento(String descripcionFallecimiento) {
        this.descripcionFallecimiento = descripcionFallecimiento;
    }

    /**
     * Método que permite establecer el contrato del comprador/arrendatario.
     * @return idContrato ,tipo String.
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Método que establece el contrato del comprador/arrendatario.
     * @param idContrato ,tipo String.
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the indicadorConNegocio
     */
    public String getIndicadorConNegocio() {
        return indicadorConNegocio;
    }

    /**
     * @param indicadorConNegocio the indicadorConNegocio to set
     */
    public void setIndicadorConNegocio(String indicadorConNegocio) {
        this.indicadorConNegocio = indicadorConNegocio;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the codigoTipoPersona
     */
    public String getCodigoTipoPersona() {
        return codigoTipoPersona;
    }

    /**
     * @param codigoTipoPersona the codigoTipoPersona to set
     */
    public void setCodigoTipoPersona(String codigoTipoPersona) {
        this.codigoTipoPersona = codigoTipoPersona;
    }

    /**
     * @return the representante
     */
    public String getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    /**
     * @return the listaRepresentanteLegal
     */
    public List<ArrendatarioRepresentanteLegal> getListaRepresentanteLegal() {
        return listaRepresentanteLegal;
    }

    /**
     * @param listaRepresentanteLegal the listaRepresentanteLegal to set
     */
    public void setListaRepresentanteLegal(List<ArrendatarioRepresentanteLegal> listaRepresentanteLegal) {
        this.listaRepresentanteLegal = listaRepresentanteLegal;
    }

    /**
     * @return the nombreConyuge
     */
    public String getNombreConyuge() {
        return nombreConyuge;
    }

    /**
     * @param nombreConyuge the nombreConyuge to set
     */
    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
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
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the consulta
     */
    public boolean isConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(boolean consulta) {
        this.consulta = consulta;
    }

    /**
     * @return the numeroBloqueDireccion
     */
    public String getNumeroBloqueDireccion() {
        return numeroBloqueDireccion;
    }

    /**
     * @param numeroBloqueDireccion the numeroBloqueDireccion to set
     */
    public void setNumeroBloqueDireccion(String numeroBloqueDireccion) {
        this.numeroBloqueDireccion = numeroBloqueDireccion;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getNuImprComprobantes() {
        return nuImprComprobantes;
    }

    public void setNuImprComprobantes(int nuImprComprobantes) {
        this.nuImprComprobantes = nuImprComprobantes;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }



    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getAnioComprobante() {
        return anioComprobante;
    }

    public void setAnioComprobante(String anioComprobante) {
        this.anioComprobante = anioComprobante;
    }

    public String getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(String tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
    }


    public int getCantidadRegistro() {
        return cantidadRegistro;
    }

    public void setCantidadRegistro(int cantidadRegistro) {
        this.cantidadRegistro = cantidadRegistro;
    }

    public String getOcupacionPredio() {
        return ocupacionPredio;
    }

    public void setOcupacionPredio(String ocupacionPredio) {
        this.ocupacionPredio = ocupacionPredio;
    }

    public void setRowDeudores(String rowDeudores) {
        this.rowDeudores = rowDeudores;
    }

    public String getRowDeudores() {
        return this.idContrato + "|" + this.fechaEmision + "|" + this.fechaVencimiento + "|" + this.documentoIdentidad + "|" + this.idArrendatario + "|" +
                this.nombreArrendatario + "|" + this.numeroOperacion + "|" + this.montoTotal + "|" + this.tipoComprobante + "|" +
                this.tipoMoneda + "|" + this.anioComprobante + "|" + this.tipoConcepto;
    }

}
