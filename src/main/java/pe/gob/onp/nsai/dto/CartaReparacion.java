/**
 * Resumen.
 * Objeto               :   CartaReparacion.java.
 * Descripción          :   DTO utilizado para encapsular los atributos del CartaReparacion.
 * Fecha de Creación    :   13/08/2021
 * PE de Creación       :   INI-900
 * Autor                :   Mario Salinas
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que contiene los atributos del personal.
 */
public class CartaReparacion implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 1529548754199674943L;

    /** Identificador de la carta de reparacion. */
    private Integer idCartaReparacion;
    private Integer nroCorrelativo;
    private Date fechaInicio;
    private Date fechaFin;
    private String horaInicio;
    private String horaFin;
    private String observacion;
    private String asunto;
    private String codigoTipoGeneracion;
    private String codigoTipoPlantilla;
    /** Objeto de auditoria. */
    private Auditoria auditoria;
    private String idInmueble;
    private String nombreImueble;
    private Integer codigoBloque;
    private String nombreBloque;
    private Integer codigoPredio;
    private String nombrePredio;

    public Integer getIdCartaReparacion() {
        return idCartaReparacion;
    }
    public void setIdCartaReparacion(Integer idCartaReparacion) {
        this.idCartaReparacion = idCartaReparacion;
    }
    public Integer getNroCorrelativo() {
        return nroCorrelativo;
    }
    public void setNroCorrelativo(Integer nroCorrelativo) {
        this.nroCorrelativo = nroCorrelativo;
    }
    public Date getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getCodigoTipoGeneracion() {
        return codigoTipoGeneracion;
    }
    public void setCodigoTipoGeneracion(String codigoTipoGeneracion) {
        this.codigoTipoGeneracion = codigoTipoGeneracion;
    }
    public String getCodigoTipoPlantilla() {
        return codigoTipoPlantilla;
    }
    public void setCodigoTipoPlantilla(String codigoTipoPlantilla) {
        this.codigoTipoPlantilla = codigoTipoPlantilla;
    }
    public Auditoria getAuditoria() {
        return auditoria;
    }
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }
    public String getIdInmueble() {
        return idInmueble;
    }
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }
    public String getNombreImueble() {
        return nombreImueble;
    }
    public void setNombreImueble(String nombreImueble) {
        this.nombreImueble = nombreImueble;
    }
    public Integer getCodigoBloque() {
        return codigoBloque;
    }
    public void setCodigoBloque(Integer codigoBloque) {
        this.codigoBloque = codigoBloque;
    }
    public String getNombreBloque() {
        return nombreBloque;
    }
    public void setNombreBloque(String nombreBloque) {
        this.nombreBloque = nombreBloque;
    }
    public Integer getCodigoPredio() {
        return codigoPredio;
    }
    public void setCodigoPredio(Integer codigoPredio) {
        this.codigoPredio = codigoPredio;
    }
    public String getNombrePredio() {
        return nombrePredio;
    }
    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

}
