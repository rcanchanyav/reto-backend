package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DevolucionPredio implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 6684413035379492171L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId = new IdHash();

    /** Identificador del detalle del Contrato Persona. */
    private long idDetalleContPers;


    /** Identificador del detalle Acta Documento. */
    private int idDetalleActaDocumento;
    /** Identificador del detalle Acta Documento. */
    private int idDetalleAmbientePredio;



    /** Identificador del Inmueble Predio Detalle. */
    private int idDetalleInmuPredio;


    /** Identificador de Acta Base. */
    private String idActaBase;

    /** Hora Recepcion. */
    private String horaRecepcion;

    /** Fecha de Devol */
    private Date fecha;



    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    private int item;


    public int getIdDetalleActaDocumento() {
        return idDetalleActaDocumento;
    }

    public void setIdDetalleActaDocumento(int idDetalleActaDocumento) {
        this.idDetalleActaDocumento = idDetalleActaDocumento;
    }

    /** Identificador del detalle Predio Contrato Documento. */
    private long idDetallePredioContrato;

    /** Identificador del predio del contrato. */
    private Integer idContratoPredio;

    /** Identificador del contrato. */
    private String idContrato;

    /** Identificador del contrato. */
    private long idDevolucionPredio;

    /** Identificador del inmueble. */
    private String idInmueble;

    /** Identificador del bloque del inmueble. */
    private String idInmuebleBloque;

    /** Identificador del predio del inmueble. */
    private String idInmueblePredio;

    /** Identificador del predio del inmueble. */
    private String setIdInmuebleBloque;

    /** Fecha de Aasignacion */
    private Date fechaInicio;

    /** Hora Aasignacion*/
    private String hoAsignacion;


    /** Nombre Arrendatario */
    private String nombreArrendatario;

    /** Datos Arrendatario */
    private String datosArrendatario;

    /** Documento Arrendatario */
    private String numeroDocumentoIdentidadRes;

    /** Objeto de Auditoria. */
    private Auditoria auditoria;

    /** Estado del registro. */
    private String estadoBusquedad;

    /** Estado de asignacion. */
    private String estado;

    /** Descripción del inmueble. */
    private String descripcionInmueble;

    /** Descripción del bloque. */
    private String descripcionBloque;

    /** Descripción del predio. */
    private String descripcionPredio;

    /** Identificador del persona. */
    private Integer idPersona;

    /** Fecha de Asignacion. */
    private Date feAsignacion;

    /** Descripción de Responsable. */
    private String responsable;

    /** Correo Arrendatario. */
    private String correo;

    /** Telefono Arrendatario. */
    private String telefono;

    /** Celular Arrendatario. */
    private String celular;

    /** Codigo de Ubigeo  */
    private String codigoAreaGeografica;

    /** Direccion Inmueblre */
    private String direccionInmueble;

    /** Nombre Archivo Documento */
    private String nombreArchivoDocumento;

    /** Codigo Archivo Documento */
    private String codigoArchivoDocumento;


    /** Provincia del Inmueble */
    @NotNull(message="{entregaPredio.provinciaInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.provinciaInmueble.size}")
    private String provinciaInmueble;

    /** Departamento del Inmueble  */
    @NotNull(message="{entregaPredio.departamentoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.departamentoInmueble.size}")
    private String departamentoInmueble;

    /** Distrito del Inmueble */
    @NotNull(message="{entregaPredio.distritoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.distritoInmueble.size}")
    private String distritoInmueble;

    /** Responsable */
    private String nombreResponsable;

    /** Observacion Devolucion Predio */
    private String observacionPredio;

    /** Codigo Devolucion Predio */
    private String codigoDevolucionPredio;


    /** Codigo Devolucion Predio */
    private String codigoContrato;
    /** Codigo Devolucion Predio */
    private String estadoContrato;
    /** Codigo Devolucion Predio */
    private String tipoPersona;
    /** Codigo Devolucion Predio */
    private String documentoIdentidad;
    /** Codigo Devolucion Predio */
    private String numeroDocumentoIdentidad;
    /** Codigo Devolucion Predio */
    private String apellidoPaternoArrendatario;
    /** Codigo Devolucion Predio */
    private String apellidoMaternoArrendatario;
    /** Codigo Devolucion Predio */
    private String numeroRucArrendatario;
    /** Codigo Devolucion Predio */
    private String razonSocialArrendatario;
    /** Codigo Devolucion Predio */



    /**
     * Método que obtiene el identificador del contrato del predio.
     * @return idContratoPredio identificador del contrato del predio, tipo Integer.
     */
    public Integer getIdContratoPredio() {
        return idContratoPredio;
    }

    /**
     * Método que establece el identificador del contrato del predio.
     * @param idContratoPredio identificador del contrato del predio, tipo Integer.
     */
    public void setIdContratoPredio(Integer idContratoPredio) {
        this.idContratoPredio = idContratoPredio;
    }

    /**
     * Método que obtiene el identificador del contrato.
     * @return idContrato identificador del contrato, tipo Integer.
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Método que establece el identificador del contrato.
     * @param idContrato identificador del contrato, tipo String.
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * Método que obtiene el identificador del inmueble.
     * @return idInmueble identificador del inmueble, tipo String.
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**
     * Método que establece el identificador del inmueble.
     * @param idInmueble identificador del inmueble, tipo String.
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    /**
     * Método que obtiene el identificador del bloque.
     * @return idInmuebleBloque identificador del bloque, tipo Integer.
     */
    public String getIdInmuebleBloque() {
        return idInmuebleBloque;
    }

    /**
     * Método que establece el identificador del bloque.
     * @param idInmuebleBloque identificador del bloque, tipo Integer.
     */
    public void setIdInmuebleBloque(String idInmuebleBloque) {
        this.idInmuebleBloque = idInmuebleBloque;
    }

    /**
     * Método que obtiene el identificador del predio del inmueble.
     * @return idInmueblePredio identificador del predio del inmueble, tipo Integer.
     */
    public String getIdInmueblePredio() {
        return idInmueblePredio;
    }

    /**
     * Método que establece el identificador del predio del inmueble.
     * @param idInmueblePredio identificador del predio del inmueble, tipo Integer.
     */
    public void setIdInmueblePredio(String idInmueblePredio) {
        this.idInmueblePredio = idInmueblePredio;
    }

    /**
     * Método que obtiene el objeto de auditoria.
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
     * Método que obtiene el estado del registro que sirve como flag para la validación.
     * @return estado estado del registro que sirve como flag para la validación, tipo String.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que establece el estado del registro que sirve como flag para la validación.
     * @param estado estado del registro que sirve como flag para la validación, tipo String.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método que obtiene la descripción del inmueble.
     * @return descripcionInmueble descripción del inmueble, tipo String.
     */

    public String getDescripcionInmueble() {
        return descripcionInmueble;
    }

    /**
     * Método que establece la descripción del inmueble.
     * @param descripcionInmueble descripción del inmueble, tipo String.
     */

    public void setDescripcionInmueble(String descripcionInmueble) {
        this.descripcionInmueble = descripcionInmueble;
    }

    /**
     * Método que obtiene la descripción del bloque.
     * @return descripcionBloque descripción del bloque, tipo String.
     */

    public String getDescripcionBloque() {
        return descripcionBloque;
    }

    /**
     * Método que establece la descripción del bloque.
     * @param descripcionBloque descripción del bloque, tipo String.
     */

    public void setDescripcionBloque(String descripcionBloque) {
        this.descripcionBloque = descripcionBloque;
    }

    /**
     * Método que obtiene la descripción del predio.
     * @return descripcionPredio descripción del predio, tipo String.
     */

    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    /**
     * Método que establece la descripción del predio.
     * @param descripcionPredio descripción del predio, tipo String.
     */

    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
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
     * Método que obtiene la fecha de Inicio.
     * @return fechaInicio fecha de Inicio, tipo Date.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Método que establece la fecha de Inicio.
     * @param fechaInicio fecha de Inicio, tipo Date.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * Método que obtiene el estado del registro.
     * @return  estado del registro, tipo String.
     */
    public String getEstadoBusquedad() {
        return estadoBusquedad;
    }

    /**
     * Método que establece el estado del registro.
     * @param estadoRegistro estado del registro, tipo String.
     */
    public void setEstadoBusquedad(String estadoBusquedad) {
        this.estadoBusquedad = estadoBusquedad;
    }

    /**
     * @return the nombreArrendatario
     */
    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    /**
     * @param nombreArrendatario the nombreArrendatario to set
     */
    public void setNombreArrendatario(String nombreArrendatario) {
        this.nombreArrendatario = nombreArrendatario;
    }


    /**
     * Método que obtiene el identificador del contrato del predio.
     * @return idContratoPredio identificador del contrato del predio, tipo Integer.
     */
    public Integer getIdPersona() {
        return idPersona;
    }

    /**
     * Método que establece el identificador del contrato del predio.
     * @param idContratoPredio identificador del contrato del predio, tipo Integer.
     */
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Método que obtiene el identificador del contrato del predio.
     * @return idContratoPredio identificador del contrato del predio, tipo Integer.
     */
    public long getIdDetalleContPers() {
        return idDetalleContPers;
    }

    /**
     * Método que establece el identificador del contrato del predio.
     * @param idContratoPredio identificador del contrato del predio, tipo long.
     */
    public void setIdDetalleContPers(long idDetalleContPers) {
        this.idDetalleContPers = idDetalleContPers;
    }


    /**
     * Método que obtiene la fecha de asignacion.
     * @return feAsignacion fecha de asignacion, tipo Date.
     */
    public Date getFeAsignacion() {
        return feAsignacion;
    }

    /**
     * Método que establece la fecha de asignacion.
     * @param feAsignacion fecha de asignacion, tipo Date.
     */
    public void setFeAsignacion(Date feAsignacion) {
        this.feAsignacion = feAsignacion;
    }

    /**
     * @return the responsable
     */
    public String getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the direccionInmueble
     */
    public String getDireccionInmueble() {
        return direccionInmueble;
    }

    /**
     * @param direccionInmueble the direccionInmueble to set
     */
    public void setDireccionInmueble(String direccionInmueble) {
        this.direccionInmueble = direccionInmueble;
    }

    /**
     * Método que obtiene el departamento.
     * @return departamento departamento, tipo String.
     */
    public String getDepartamentoInmueble() {
        return departamentoInmueble;
    }

    /**
     * Método que establece el departamento.
     * @param departamento departamento, tipo String.
     */
    public void setDepartamentoInmueble(String departamentoInmueble) {
        this.departamentoInmueble = departamentoInmueble;
    }

    /**
     * Método que obtiene la provincia.
     * @return provincia provincia, tipo String.
     */
    public String getProvinciaInmueble() {
        return provinciaInmueble;
    }

    /**
     * Método que establece la provincia.
     * @param provincia provincia, tipo String.
     */
    public void setProvinciaInmueble(String provinciaInmueble) {
        this.provinciaInmueble = provinciaInmueble;
    }

    /**
     * Método que obtiene el distrito.
     * @return distrito distrito, tipo String.
     */
    public String getDistritoInmueble() {
        return distritoInmueble;
    }

    /**
     * Método que establece el distrito.
     * @param distrito distrito, tipo String.
     */
    public void setDistritoInmueble(String distritoInmueble) {
        this.distritoInmueble = distritoInmueble;
    }

    /**
     * @return the nombreResponsable
     */
    public String getNombreResponsable() {
        return nombreResponsable;
    }

    /**
     * @param nombreResponsable the nombreResponsable to set
     */
    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    /**
     * @return the observacionPredio
     */
    public String getObservacionPredio() {
        return observacionPredio;
    }

    /**
     * @param observacionPredio the observacionPredio to set
     */
    public void setObservacionPredio(String observacionPredio) {
        this.observacionPredio = observacionPredio;
    }

    /**
     * Método que permite obtener el código del área geografica.
     * @return codigoAreaGeografica código del área geografica, tipo String.
     */
    public String getCodigoAreaGeografica() {
        return codigoAreaGeografica;
    }

    /**
     * Método que permite establecer el código del área geografica.
     * @param codigoAreaGeografica código del área geografica, tipo String.
     */
    public void setCodigoAreaGeografica(String codigoAreaGeografica) {
        this.codigoAreaGeografica = codigoAreaGeografica;
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
     * @return the setIdInmuebleBloque
     */
    public String getSetIdInmuebleBloque() {
        return setIdInmuebleBloque;
    }

    /**
     * @param setIdInmuebleBloque the setIdInmuebleBloque to set
     */
    public void setSetIdInmuebleBloque(String setIdInmuebleBloque) {
        this.setIdInmuebleBloque = setIdInmuebleBloque;
    }

    /**
     * @return the idDetallePredioContrato
     */
    public long getIdDetallePredioContrato() {
        return idDetallePredioContrato;
    }

    /**
     * @param idDetallePredioContrato the idDetallePredioContrato to set
     */
    public void setIdDetallePredioContrato(long idDetallePredioContrato) {
        this.idDetallePredioContrato = idDetallePredioContrato;
    }

    /**
     * @return the idDevolucionPredio
     */
    public long getIdDevolucionPredio() {
        return idDevolucionPredio;
    }

    /**
     * @param idDevolucionPredio the idDevolucionPredio to set
     */
    public void setIdDevolucionPredio(long idDevolucionPredio) {
        this.idDevolucionPredio = idDevolucionPredio;
    }

    /**
     * @return the hoAsignacion
     */
    public String getHoAsignacion() {
        return hoAsignacion;
    }

    /**
     * @param hoAsignacion the hoAsignacion to set
     */
    public void setHoAsignacion(String hoAsignacion) {
        this.hoAsignacion = hoAsignacion;
    }

    /**
     * @return the codigoDevolucionPredio
     */
    public String getCodigoDevolucionPredio() {
        return codigoDevolucionPredio;
    }

    /**
     * @param codigoDevolucionPredio the codigoDevolucionPredio to set
     */
    public void setCodigoDevolucionPredio(String codigoDevolucionPredio) {
        this.codigoDevolucionPredio = codigoDevolucionPredio;
    }

    /**
     * @return the codigoContrato
     */
    public String getCodigoContrato() {
        return codigoContrato;
    }

    /**
     * @param codigoContrato the codigoContrato to set
     */
    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    /**
     * @return the estadoContrato
     */
    public String getEstadoContrato() {
        return estadoContrato;
    }

    /**
     * @param estadoContrato the estadoContrato to set
     */
    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    /**
     * @return the tipoPersona
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the documentoIdentidad
     */
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    /**
     * @param documentoIdentidad the documentoIdentidad to set
     */
    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    /**
     * @return the numeroDocumentoIdentidad
     */
    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    /**
     * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
     */
    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    /**
     * @return the apellidoPaternoArrendatario
     */
    public String getApellidoPaternoArrendatario() {
        return apellidoPaternoArrendatario;
    }

    /**
     * @param apellidoPaternoArrendatario the apellidoPaternoArrendatario to set
     */
    public void setApellidoPaternoArrendatario(String apellidoPaternoArrendatario) {
        this.apellidoPaternoArrendatario = apellidoPaternoArrendatario;
    }

    /**
     * @return the apellidoMaternoArrendatario
     */
    public String getApellidoMaternoArrendatario() {
        return apellidoMaternoArrendatario;
    }

    /**
     * @param apellidoMaternoArrendatario the apellidoMaternoArrendatario to set
     */
    public void setApellidoMaternoArrendatario(String apellidoMaternoArrendatario) {
        this.apellidoMaternoArrendatario = apellidoMaternoArrendatario;
    }

    /**
     * @return the numeroRucArrendatario
     */
    public String getNumeroRucArrendatario() {
        return numeroRucArrendatario;
    }

    /**
     * @param numeroRucArrendatario the numeroRucArrendatario to set
     */
    public void setNumeroRucArrendatario(String numeroRucArrendatario) {
        this.numeroRucArrendatario = numeroRucArrendatario;
    }

    /**
     * @return the razonSocialArrendatario
     */
    public String getRazonSocialArrendatario() {
        return razonSocialArrendatario;
    }

    /**
     * @param razonSocialArrendatario the razonSocialArrendatario to set
     */
    public void setRazonSocialArrendatario(String razonSocialArrendatario) {
        this.razonSocialArrendatario = razonSocialArrendatario;
    }

    /**
     * @return the codigoArchivoDocumento
     */
    public String getCodigoArchivoDocumento() {
        return codigoArchivoDocumento;
    }

    /**
     * @param codigoArchivoDocumento the codigoArchivoDocumento to set
     */
    public void setCodigoArchivoDocumento(String codigoArchivoDocumento) {
        this.codigoArchivoDocumento = codigoArchivoDocumento;
    }

    /**
     * @return the idDetalleInmuPredio
     */
    public int getIdDetalleInmuPredio() {
        return idDetalleInmuPredio;
    }

    /**
     * @param idDetalleInmuPredio the idDetalleInmuPredio to set
     */
    public void setIdDetalleInmuPredio(int idDetalleInmuPredio) {
        this.idDetalleInmuPredio = idDetalleInmuPredio;
    }

    /**
     * @return the numeroDocumentoIdentidadRes
     */
    public String getNumeroDocumentoIdentidadRes() {
        return numeroDocumentoIdentidadRes;
    }

    /**
     * @param numeroDocumentoIdentidadRes the numeroDocumentoIdentidadRes to set
     */
    public void setNumeroDocumentoIdentidadRes(String numeroDocumentoIdentidadRes) {
        this.numeroDocumentoIdentidadRes = numeroDocumentoIdentidadRes;
    }

    /**
     * @return the datosArrendatario
     */
    public String getDatosArrendatario() {
        return datosArrendatario;
    }

    /**
     * @param datosArrendatario the datosArrendatario to set
     */
    public void setDatosArrendatario(String datosArrendatario) {
        this.datosArrendatario = datosArrendatario;
    }

    /**
     * @return the idActaBase
     */
    public String getIdActaBase() {
        return idActaBase;
    }

    /**
     * @param idActaBase the idActaBase to set
     */
    public void setIdActaBase(String idActaBase) {
        this.idActaBase = idActaBase;
    }

    /**
     * @return the horaRecepcion
     */
    public String getHoraRecepcion() {
        return horaRecepcion;
    }

    /**
     * @param horaRecepcion the horaRecepcion to set
     */
    public void setHoraRecepcion(String horaRecepcion) {
        this.horaRecepcion = horaRecepcion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idDetalleAmbientePredio
     */
    public int getIdDetalleAmbientePredio() {
        return idDetalleAmbientePredio;
    }

    /**
     * @param idDetalleAmbientePredio the idDetalleAmbientePredio to set
     */
    public void setIdDetalleAmbientePredio(int idDetalleAmbientePredio) {
        this.idDetalleAmbientePredio = idDetalleAmbientePredio;
    }




}
