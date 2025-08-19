
/**
 * Resumen.
 * Objeto               :   ReporteProrrateoInmuebleBloquePredio.java.
 * Descripción          :   Clase que define los datos de detalle para generación de prorrateo por inmueble, bloque y predios
 * Fecha de Creación    :   13/10/2022
 * PE de Creación       :   .
 * Autor                :  Kevin Cabrera
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Clase con los datos generales del  inmueble, bloque y predio a prorratear
 *
 */
public class ReporteProrrateoInmuebleBloquePredio implements Serializable{

    /**
     * Clave serial autogenerada
     */
    private static final long serialVersionUID = 9071731949607255072L;

    private String predio;

    private String precario;

    private String contrato;

    private String codArrendatario;

    private String arrendatario;

    private BigDecimal areaConstruida;

    private BigDecimal areaComun;

    private String diasOcupados;

    private String generoComprobante;

    private BigDecimal costoOcupado;

    private BigDecimal costoTodos;

    private BigDecimal numeroPredios;

    private BigDecimal costoDesocupado;

    private BigDecimal total;

    private BigDecimal totalOcupado;

    private BigDecimal totalDesocupado;

    private BigDecimal totalGeneral;

    private String periodo;

    private String inmueble;

    private String idInmueble;

    private String bloque;

    private String idBloque;

    private String moneda;

    private String servicio;

    private String codServicio;

    private String anio;

    private String mes;

    private String descMes;

    private String tipoServicio;

    private int nuflgCab;

    private BigDecimal totalColumna;

    private Integer ultBloque;

    private Integer ultInmueble;

    private Date fechaEmision;
    private Date fechaVencimiento;
    private Date fechaCalcInteres;
    private Date fechaGeneracion;
    private String tipoProrrateo;

    private Auditoria auditoria;

    private List<ReporteProrrateoInmuebleBloquePredioDeta> detalle;

    public String getPredio() {
        return predio ==null?"":predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getPrecario() {
        return precario ==null?"":precario;
    }

    public void setPrecario(String precario) {
        this.precario = precario;
    }

    public String getContrato() {
        return contrato  ==null?"":contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getCodArrendatario() {
        return codArrendatario  ==null?"":codArrendatario;
    }

    public void setCodArrendatario(String codArrendatario) {
        this.codArrendatario = codArrendatario;
    }

    public String getArrendatario() {
        return arrendatario  ==null?"":arrendatario;
    }

    public void setArrendatario(String arrendatario) {
        this.arrendatario = arrendatario;
    }

    public BigDecimal getAreaConstruida() {
        return areaConstruida ==null?new BigDecimal("0.00"):areaConstruida;
    }

    public void setAreaConstruida(BigDecimal areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public BigDecimal getAreaComun() {
        return areaComun ==null?new BigDecimal("0.00"):areaComun;
    }

    public void setAreaComun(BigDecimal areaComun) {
        this.areaComun = areaComun;
    }

    public String getDiasOcupados() {
        return diasOcupados ==null?"":diasOcupados;
    }

    public void setDiasOcupados(String diasOcupados) {
        this.diasOcupados = diasOcupados;
    }

    public String getGeneroComprobante() {
        return generoComprobante ==null?"":generoComprobante;
    }

    public void setGeneroComprobante(String generoComprobante) {
        this.generoComprobante = generoComprobante;
    }

    public BigDecimal getCostoOcupado() {
        return costoOcupado==null?new BigDecimal("0.00"):costoOcupado;
    }

    public void setCostoOcupado(BigDecimal costoOcupado) {
        this.costoOcupado = costoOcupado;
    }

    public BigDecimal getCostoDesocupado() {
        return costoDesocupado ==null?new BigDecimal("0.00"):costoDesocupado;
    }

    public void setCostoDesocupado(BigDecimal costoDesocupado) {
        this.costoDesocupado = costoDesocupado;
    }

    public BigDecimal getTotal() {
        return total ==null?new BigDecimal("0.00"):total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalOcupado() {
        return totalOcupado ==null?new BigDecimal("0.00"):totalOcupado;
    }

    public void setTotalOcupado(BigDecimal totalOcupado) {
        this.totalOcupado = totalOcupado;
    }

    public BigDecimal getTotalDesocupado() {
        return totalDesocupado ==null?new BigDecimal("0.00"):totalDesocupado;
    }

    public void setTotalDesocupado(BigDecimal totalDesocupado) {
        this.totalDesocupado = totalDesocupado;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral  ==null?new BigDecimal("0.00"):totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String getPeriodo() {
        return periodo ==null?"":periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getInmueble() {
        return inmueble ==null?"":inmueble;
    }

    public void setInmueble(String inmueble) {
        this.inmueble = inmueble;
    }

    public String getIdInmueble() {
        return idInmueble  ==null?"":idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getBloque() {
        return bloque ==null?"":bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getIdBloque() {
        return idBloque ==null?"":idBloque;
    }

    public void setIdBloque(String idBloque) {
        this.idBloque = idBloque;
    }

    public String getMoneda() {
        return moneda  ==null?"":moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getServicio() {
        return servicio ==null?"":servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCodServicio() {
        return codServicio ==null?"":codServicio;
    }

    public void setCodServicio(String codServicio) {
        this.codServicio = codServicio;
    }

    public String getAnio() {
        return anio  ==null?"":anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes ==null?"":mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDescMes() {
        return descMes ==null?"":descMes;
    }

    public void setDescMes(String descMes) {
        this.descMes = descMes;
    }

    public BigDecimal getCostoTodos() {
        return costoTodos ==null?new BigDecimal("0.00"):costoTodos;
    }

    public void setCostoTodos(BigDecimal costoTodos) {
        this.costoTodos = costoTodos;
    }

    public BigDecimal getNumeroPredios() {
        return numeroPredios ==null?new BigDecimal("0.00"):numeroPredios;
    }

    public void setNumeroPredios(BigDecimal numeroPredios) {
        this.numeroPredios = numeroPredios;
    }

    /**
     * @return the tipoServicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * @param tipoServicio the tipoServicio to set
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * @return the nuflgCab
     */
    public int getNuflgCab() {
        return nuflgCab;
    }

    /**
     * @param nuflgCab the nuflgCab to set
     */
    public void setNuflgCab(int nuflgCab) {
        this.nuflgCab = nuflgCab;
    }

    /**
     * @return the detalle
     */
    public List<ReporteProrrateoInmuebleBloquePredioDeta> getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(List<ReporteProrrateoInmuebleBloquePredioDeta> detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the totalColumna
     */
    public BigDecimal getTotalColumna() {
        return totalColumna;
    }

    /**
     * @param totalColumna the totalColumna to set
     */
    public void setTotalColumna(BigDecimal totalColumna) {
        this.totalColumna = totalColumna;
    }

    /**
     * @return the ultBloque
     */
    public Integer getUltBloque() {
        return ultBloque;
    }

    /**
     * @param ultBloque the ultBloque to set
     */
    public void setUltBloque(Integer ultBloque) {
        this.ultBloque = ultBloque;
    }

    /**
     * @return the ultInmueble
     */
    public Integer getUltInmueble() {
        return ultInmueble;
    }

    /**
     * @param ultInmueble the ultInmueble to set
     */
    public void setUltInmueble(Integer ultInmueble) {
        this.ultInmueble = ultInmueble;
    }

    /**
     * @return the fechaEmision
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the fechaCalcInteres
     */
    public Date getFechaCalcInteres() {
        return fechaCalcInteres;
    }

    /**
     * @param fechaCalcInteres the fechaCalcInteres to set
     */
    public void setFechaCalcInteres(Date fechaCalcInteres) {
        this.fechaCalcInteres = fechaCalcInteres;
    }

    /**
     * @return the fechaGeneracion
     */
    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    /**
     * @param fechaGeneracion the fechaGeneracion to set
     */
    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    /**
     * @return the tipoProrrateo
     */
    public String getTipoProrrateo() {
        return tipoProrrateo;
    }

    /**
     * @param tipoProrrateo the tipoProrrateo to set
     */
    public void setTipoProrrateo(String tipoProrrateo) {
        this.tipoProrrateo = tipoProrrateo;
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


}
