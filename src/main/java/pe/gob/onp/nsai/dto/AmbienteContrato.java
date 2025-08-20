package pe.gob.onp.nsai.dto;

import java.io.Serializable;

public class AmbienteContrato implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 6684413035379492171L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId = new IdHash();

    /** Identificador del detalle Ambiente Predio. */
    private long idDetalleAmbientePredio;

    /** Identificador de Entrega Predio. */
    private long idEntregaPredio;

    /** Identificador de Devolucion Predio. */
    private long idDevolucionPredio;

    /** Identificador del detalle Ambiente Predio. */
    private long idDetalleEntrPreAmbiente;

    /** Identificador del detalle Ambiente Predio. */
    private long idDetalleDevoPreAmbiente;

    /** Item. */
    private Integer item;

    /** Identificador del Ambiente. */
    private String codigo;

    /** Nombre Ambiente. */
    private String nombreAmbiente;

    /** Observacion del Ambiente. */
    private String observacionAmbiente;

    /** Objeto de Auditoria. */
    private Auditoria auditoria;

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
     * @return the nombreAmbiente
     */
    public String getNombreAmbiente() {
        return nombreAmbiente;
    }

    /**
     * @param nombreAmbiente the nombreAmbiente to set
     */
    public void setNombreAmbiente(String nombreAmbiente) {
        this.nombreAmbiente = nombreAmbiente;
    }

    /**
     * @return the observacionAmbiente
     */
    public String getObservacionAmbiente() {
        return observacionAmbiente;
    }

    /**
     * @param observacionAmbiente the observacionAmbiente to set
     */
    public void setObservacionAmbiente(String observacionAmbiente) {
        this.observacionAmbiente = observacionAmbiente;
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
     * @return the idDetalleEntrPreAmbiente
     */
    public long getIdDetalleEntrPreAmbiente() {
        return idDetalleEntrPreAmbiente;
    }

    /**
     * @param idDetalleEntrPreAmbiente the idDetalleEntrPreAmbiente to set
     */
    public void setIdDetalleEntrPreAmbiente(long idDetalleEntrPreAmbiente) {
        this.idDetalleEntrPreAmbiente = idDetalleEntrPreAmbiente;
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
     * @return the idDetalleDevoPreAmbiente
     */
    public long getIdDetalleDevoPreAmbiente() {
        return idDetalleDevoPreAmbiente;
    }

    /**
     * @param idDetalleDevoPreAmbiente the idDetalleDevoPreAmbiente to set
     */
    public void setIdDetalleDevoPreAmbiente(long idDetalleDevoPreAmbiente) {
        this.idDetalleDevoPreAmbiente = idDetalleDevoPreAmbiente;
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

}
