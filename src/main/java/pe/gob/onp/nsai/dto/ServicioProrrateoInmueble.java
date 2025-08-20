/**
 * Resumen.
 * Objeto               :   ServicioProrrateoInmueble.java.
 * Descripción          :   Clase utilizada para encapsular los datos del servicio del inmueble para el prorrateo.
 * Fecha de Creación    :   11/09/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Christian Wong
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que encapsula los datos de servicios del prorrateo del inmueble para el prorrateo.
 */
public class ServicioProrrateoInmueble implements Serializable{

    /** Código autogenerado */
    private static final long serialVersionUID = 3373861056449402099L;

    /** Identificador del servicio del prorrateo del inmueble */
    private Long idServicioProrrateoInmueble;

    /** Identificador del periodo del prorrateo */
    private Long idPeriodoProrrateo;

    /** Identificador del servicio */
    private Long idServicio;

    /** Identificador del suministro */
    private Integer idSuministroInmueble;

    /** Itentificador del suministro del bloque */
    private Integer idSuministroBloque;

    /** Tipo de prorrateo */
    private String tipoProrrateo;

    /** Monto de prorrateo del servicio */
    private BigDecimal montoProrrateoServicio;

    /** Monto del prorrateo anterior */
    private BigDecimal montoProrrateoAnterior;

    /** Descripción del servicio */
    private String descripcionServicio;

    /** Descripción del proveedor */
    private String descripcionProveedor;

    /** Fecha de inicio del proveedor */
    private Date fechaInicioProveedor;

    /** Fecha de fin del proveedor */
    private Date fechaFinProveedor;

    /** Datos de auditoria */
    private Auditoria auditoria;

    /** Periodo del inmueble */
    private PeriodoProrrateoInmueble periodoInmueble;

    /** Estado del registro */
    private String estadoRegistro;

    /** Numero de fila */
    private int item;

    /**
     * Método que obtiene el identificador del servicio de prorrateo del inmueble
     * @return idServicioProrrateoInmueble identificador del servicio de prorrateo del inmueble, tipo Long
     */
    public Long getIdServicioProrrateoInmueble() {
        return idServicioProrrateoInmueble;
    }

    /**
     * Método que establece el identificador del servicio de prorrateo del inmueble
     * @param idServicioProrrateoInmueble identificador del servicio de prorrateo del inmueble, tipo Long
     */
    public void setIdServicioProrrateoInmueble(Long idServicioProrrateoInmueble) {
        this.idServicioProrrateoInmueble = idServicioProrrateoInmueble;
    }

    /**
     * Método que obtiene el identificador del periodo de prorrateo del inmueble
     * @return idPeriodoProrrateo identificador del periodo de prorrateo del inmueble, tipo Long
     */
    public Long getIdPeriodoProrrateo() {
        return idPeriodoProrrateo;
    }

    /**
     * Método que establece el identificador del periodo de prorrateo del inmueble
     * @param idPeriodoProrrateo identificador del periodo de prorrateo del inmueble, tipo Long
     */
    public void setIdPeriodoProrrateo(Long idPeriodoProrrateo) {
        this.idPeriodoProrrateo = idPeriodoProrrateo;
    }

    /**
     * Método que obtiene el identificador del servicio
     * @return idServicio identificador del servicio, tipo Long
     */
    public Long getIdServicio() {
        return idServicio;
    }

    /**
     * Método que establece el identificador del servicio
     * @param idServicio identificador del servicio, tipo Long
     */
    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * Método que obtiene el identificador del suministro del inmueble
     * @return idSuministroInmueble identificador del suministro del inmueble, tipo Integer
     */
    public Integer getIdSuministroInmueble() {
        return idSuministroInmueble;
    }

    /**
     * Método que establece el identificador del suministro del inmueble
     * @param idSuministroInmueble identificador del suministro del inmueble, tipo Integer
     */
    public void setIdSuministroInmueble(Integer idSuministroInmueble) {
        this.idSuministroInmueble = idSuministroInmueble;
    }

    /**
     * Método que obtiene el identificador del suministro del bloque.
     * @return idSuministroBloque identificador del suministro del bloque,tipo Integer.
     */
    public Integer getIdSuministroBloque() {
        return idSuministroBloque;
    }

    /**
     * Método que establece el identificador del suministro del bloque.
     * @param idSuministroBloque identificador del suministro del bloque, tipo Integer.
     */
    public void setIdSuministroBloque(Integer idSuministroBloque) {
        this.idSuministroBloque = idSuministroBloque;
    }

    /**
     * Método que obtiene el tipo de prorrateo
     * @return tipoProrrateo tipo de prorrateo, tipo String
     */
    public String getTipoProrrateo() {
        return tipoProrrateo;
    }

    /**
     * Método que establece el tipo de prorrateo
     * @param tipoProrrateo tipo de prorrateo, tipo String
     */
    public void setTipoProrrateo(String tipoProrrateo) {
        this.tipoProrrateo = tipoProrrateo;
    }

    /**
     * Método que obtiene el monto del prorrateo del servicio
     * @return montoProrrateoServicio monto del prorrateo del servicio, tipo BigDecimal
     */
    public BigDecimal getMontoProrrateoServicio() {
        return montoProrrateoServicio;
    }

    /**
     * Método que establece el monto del prorrateo del servicio
     * @param montoProrrateoServicio monto del prorrateo del servicio, tipo BigDecimal
     */
    public void setMontoProrrateoServicio(BigDecimal montoProrrateoServicio) {
        this.montoProrrateoServicio = montoProrrateoServicio;
    }

    /**
     * Método que obtiene el monto del prorrateo anterior
     * @return montoProrrateoAnterior monto del prorrateo anterior, tipo BigDecimal
     */
    public BigDecimal getMontoProrrateoAnterior() {
        return montoProrrateoAnterior;
    }

    /**
     * Método que establece el monto del prorrateo anterior
     * @param montoProrrateoAnterior monto del prorrateo anterior, tipo BigDecimal
     */
    public void setMontoProrrateoAnterior(BigDecimal montoProrrateoAnterior) {
        this.montoProrrateoAnterior = montoProrrateoAnterior;
    }

    /**
     * Método que obtiene la descripcion del servicio.
     * @return descripcionServicio descripcion del servicio, tipo String
     */
    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    /**
     * Método que establece la descripcion del servicio.
     * @param descripcionServicio descripcion del servicio, tipo String
     */
    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    /**
     * Método que obtiene la descripcion del proveedor
     * @return descripcionProveedor descripcion del proveedor, tipo String
     */
    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    /**
     * Método que establece la descripcion del proveedor
     * @param descripcionProveedor descripcion del proveedor, tipo String
     */
    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    /**
     * Método que obtiene la fecha inicio del proveedor con el servicio
     * @return fechaInicioProveedor fecha inicio del proveedor con el servicio, tipo Date
     */
    public Date getFechaInicioProveedor() {
        return fechaInicioProveedor;
    }

    /**
     * Método que establece la fecha inicio del proveedor con el servicio
     * @param fechaInicioProveedor fecha inicio del proveedor con el servicio, tipo Date
     */
    public void setFechaInicioProveedor(Date fechaInicioProveedor) {
        this.fechaInicioProveedor = fechaInicioProveedor;
    }

    /**
     * Método que obtiene la fecha fin del proveedor con el servicio
     * @return fechaFinProveedor fecha fin del proveedor con el servicio, tipo Date
     */
    public Date getFechaFinProveedor() {
        return fechaFinProveedor;
    }

    /**
     * Método que establece la fecha fin del proveedor con el servicio
     * @param fechaFinProveedor fecha fin del proveedor con el servicio, tipo Date
     */
    public void setFechaFinProveedor(Date fechaFinProveedor) {
        this.fechaFinProveedor = fechaFinProveedor;
    }

    /**
     * Método que obtiene los datos de auditoria
     * @return auditoria datos de auditoria, tipo Auditoria
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que establece los datos de auditoria
     * @param auditoria datos de auditoria, tipo Auditoria
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Método que obtiene los datos del periodo del prorrateo del inmueble
     * @return periodoInmueble datos del periodo del prorrateo del inmueble, tipo PeriodoProrrateoInmueble
     */
    public PeriodoProrrateoInmueble getPeriodoInmueble() {
        return periodoInmueble;
    }

    /**
     * Método que establece los datos del periodo del prorrateo del inmueble
     * @param periodoInmueble datos del periodo del prorrateo del inmueble, tipo PeriodoProrrateoInmueble
     */
    public void setPeriodoInmueble(PeriodoProrrateoInmueble periodoInmueble) {
        this.periodoInmueble = periodoInmueble;
    }

    /**
     * Método que obtiene el estado de registro.
     * @return estadoRegistro estado de registro,tipo String.
     */
    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    /**
     * Método que establece el estado de registro.
     * @param estadoRegistro estado de registro,tipo String.
     */
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    /**
     * Método que obtiene el número de item.
     * @return item número de item, tipo int.
     */
    public int getItem() {
        return item;
    }

    /**
     * Método que establece el número de item.
     * @param item número de item, tipo int.
     */
    public void setItem(int item) {
        this.item = item;
    }





}
