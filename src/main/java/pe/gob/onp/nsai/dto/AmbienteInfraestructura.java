package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

public class AmbienteInfraestructura implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 6684413035379492171L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId = new IdHash();

    /** Identificador del detalle Ambiente Predio. */
    private long idDetalleAmbientePredio;

    /** Identificador del detalle Ambiente Predio. */
    private long idDevolucionPredioInf;


    /** Identificador del Entrega Predio. */
    private long idEntregaPredio;

    /** Identificador del Devolucion Predio. */
    private long idDevolucionPredio;

    /** Identificador del detalle Entrega Predio Infraestructura */
    private long idDetEntregaPredioInfr;


    /** Identificador del detalle devolucion Predio Infraestructura */
    private long idDetDevolucionPredioInfr;


    /** Identificador de infraestructura. */
    private long idInfraestructura;

    /** Identificador del Ambiente. */
    private String codigo;

    /** Caracteristica del Ambiente. */
    private String caracteristica;


    /** Nombre Ambiente. */
    private String ambiente;

    /** Nombre Infraestructura. */
    private String infraestructura;

    /** Cantidad de la Infraestructura. */
    private int cantidad;

    /** Fecha estado conservación. */
    private Date fecha;

    /** Observacion del Infraestructura. */
    private String observacion;

    /** Codigo Estado Conservacion. */
    private String codigoEstado;

    /** Estado del Infraestructura. */
    private String estado;

    /** Item. */
    private Integer item;

    /** Objeto de Auditoria. */
    private Auditoria auditoria;

    /**
     * @return the idInfraestructura
     */
    public long getIdInfraestructura() {
        return idInfraestructura;
    }

    /**
     * @param idInfraestructura the idInfraestructura to set
     */
    public void setIdInfraestructura(long idInfraestructura) {
        this.idInfraestructura = idInfraestructura;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the ambiente
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * @param ambiente the ambiente to set
     */
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * @return the infraestructura
     */
    public String getInfraestructura() {
        return infraestructura;
    }

    /**
     * @param infraestructura the infraestructura to set
     */
    public void setInfraestructura(String infraestructura) {
        this.infraestructura = infraestructura;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
     * @return the idDetalleAmbientePredio
     */
    public long getIdDetalleAmbientePredio() {
        return idDetalleAmbientePredio;
    }

    /**
     * @param idDetalleAmbientePredio the idDetalleAmbientePredio to set
     */
    public void setIdDetalleAmbientePredio(long idDetalleAmbientePredio) {
        this.idDetalleAmbientePredio = idDetalleAmbientePredio;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the idDetEntregaPredioInfr
     */
    public long getIdDetEntregaPredioInfr() {
        return idDetEntregaPredioInfr;
    }

    /**
     * @param idDetEntregaPredioInfr the idDetEntregaPredioInfr to set
     */
    public void setIdDetEntregaPredioInfr(long idDetEntregaPredioInfr) {
        this.idDetEntregaPredioInfr = idDetEntregaPredioInfr;
    }

    /**
     * @return the idEntregaPredio
     */
    public long getIdEntregaPredio() {
        return idEntregaPredio;
    }

    /**
     * @param idEntregaPredio the idEntregaPredio to set
     */
    public void setIdEntregaPredio(long idEntregaPredio) {
        this.idEntregaPredio = idEntregaPredio;
    }

    /**
     * @return the codigoEstado
     */
    public String getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param codigoEstado the codigoEstado to set
     */
    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     * @return the idDevolucionPredioInf
     */
    public long getIdDevolucionPredioInf() {
        return idDevolucionPredioInf;
    }

    /**
     * @param idDevolucionPredioInf the idDevolucionPredioInf to set
     */
    public void setIdDevolucionPredioInf(long idDevolucionPredioInf) {
        this.idDevolucionPredioInf = idDevolucionPredioInf;
    }

    /**
     * @return the caracteristica
     */
    public String getCaracteristica() {
        return caracteristica;
    }

    /**
     * @param caracteristica the caracteristica to set
     */
    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    /**
     * @return the idDetDevolucionPredioInfr
     */
    public long getIdDetDevolucionPredioInfr() {
        return idDetDevolucionPredioInfr;
    }

    /**
     * @param idDetDevolucionPredioInfr the idDetDevolucionPredioInfr to set
     */
    public void setIdDetDevolucionPredioInfr(long idDetDevolucionPredioInfr) {
        this.idDetDevolucionPredioInfr = idDetDevolucionPredioInfr;
    }

    /**
     * @return the item
     */
    public Integer getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Integer item) {
        this.item = item;
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

}
