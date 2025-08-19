/**
 * Resumen.
 * Objeto               :   PredioServicioProrrateo.java.
 * Descripción          :   Clase utilizada para encapsular los campos del predio del servicio de prorrateo
 * Fecha de Creación    :   06/09/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Christian Wong
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Clase que encapsula los campos del predio del servicio de Prorrateo
 */
public class PredioServicioProrrateo  implements Serializable{

    /** Código autogenerado */
    private static final long serialVersionUID = -479767422418425016L;

    /** Identificador del predio del servicio para prorrateo */
    private Long idPredioServicioProrrateo;

    /** Identificador del bloque del servicio para prorrateo */
    private Long idBloqueServicioProrrateo;

    /** Identificador del servicio del inmueble para prorrateo */
    private Long idServicioProrrateoInmueble;

    /** Identificador del inmueble */
    private String idInmueble;

    /** Identificador del predio */
    private Long idPredio;

    /** Porcentaje de incidencia */
    private BigInteger porcentajeIncidencia;

    /** Descripción del predio */
    private String descripcionPredio;

    /** Indicador de prorrateo */
    private String indicadorProrrateo;

    /** Datos del servicio para el prorrateo */
    private ServicioProrrateoInmueble servicioProrrateo;

    /** Datos del bloque del servicio para prorrateo */
    private BloqueServicioProrrateo bloqueProrrateo;

    /** Datos de auditoria */
    private Auditoria auditoria;

    /** Numero de fila */
    private int item;

    /**
     * Método que obtiene el identificador del predio del servicio para prorrateo
     * @return idPredioServicioProrrateo identificador del predio del servicio para prorrateo, tipo Long.
     */
    public Long getIdPredioServicioProrrateo() {
        return idPredioServicioProrrateo;
    }

    /**
     * Método que establece el identificador del predio del servicio para prorrateo
     * @param idPredioServicioProrrateo identificador del predio del servicio para prorrateo, tipo Long.
     */
    public void setIdPredioServicioProrrateo(Long idPredioServicioProrrateo) {
        this.idPredioServicioProrrateo = idPredioServicioProrrateo;
    }

    /**
     * Método que obtiene el identificador del bloque del servicio para prorrateo
     * @return idBloqueServicioProrrrateo identificador del bloque del servicio para prorrateo, tipo Long.
     */
    public Long getIdBloqueServicioProrrateo() {
        return idBloqueServicioProrrateo;
    }

    /**
     * Método que establece el identificador del bloque del servicio para prorrateo
     * @param idBloqueServicioProrrrateo identificador del bloque del servicio para prorrateo, tipo Long.
     */
    public void setIdBloqueServicioProrrrateo(Long idBloqueServicioProrrateo) {
        this.idBloqueServicioProrrateo = idBloqueServicioProrrateo;
    }

    /**
     * Método que obtiene el identificador del servicio para prorrateo
     * @return idServicioProrrateoInmueble identificador del servicio para prorrateo, tipo Long.
     */
    public Long getIdServicioProrrateoInmueble() {
        return idServicioProrrateoInmueble;
    }

    /**
     * Método que establece el identificador del servicio para prorrateo
     * @param idServicioProrrateoInmueble identificador del servicio para prorrateo, tipo Long.
     */
    public void setIdServicioProrrateoInmueble(Long idServicioProrrateoInmueble) {
        this.idServicioProrrateoInmueble = idServicioProrrateoInmueble;
    }

    /**
     * Método que obtiene el identificador del inmueble
     * @return idInmueble identificador del inmueble, tipo String.
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**
     * Método que establece el identificador del inmueble
     * @param idInmueble identificador del inmueble, tipo String.
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    /**
     * Método que obtiene el identificador del predio
     * @return idPredio identificador del predio, tipo Long.
     */
    public Long getIdPredio() {
        return idPredio;
    }

    /**
     * Método que establece el identificador del predio
     * @param idPredio identificador del predio, tipo Long.
     */
    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    /**
     * Método que obtiene el porcentaje de incidencia
     * @return porcentajeIncidencia porcentaje de incidencia, tipo BigInteger.
     */
    public BigInteger getPorcentajeIncidencia() {
        return porcentajeIncidencia;
    }

    /**
     * Método que establece el porcentaje de incidencia
     * @param porcentajeIncidencia porcentaje de incidencia, tipo BigInteger.
     */
    public void setPorcentajeIncidencia(BigInteger porcentajeIncidencia) {
        this.porcentajeIncidencia = porcentajeIncidencia;
    }

    /**
     * Método que obtiene la descripción del predio
     * @return descripcionPredio descripción del predio, tipo String.
     */
    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    /**
     * Método que establece la descripción del predio
     * @param descripcionPredio descripción del predio, tipo String.
     */
    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }

    /**
     * Método que obtiene los datos del servicio de prorrateo
     * @return servicioProrrateo servicio de prorrateo, tipo ServicioProrrateoInmueble.
     */
    public ServicioProrrateoInmueble getServicioProrrateo() {
        return servicioProrrateo;
    }

    /**
     * Método que establece los datos del servicio de prorrateo
     * @param servicioProrrateo servicio de prorrateo, tipo ServicioProrrateoInmueble.
     */
    public void setServicioProrrateo(ServicioProrrateoInmueble servicioProrrateo) {
        this.servicioProrrateo = servicioProrrateo;
    }

    /**
     * Método que obtiene los datos del bloque de servicio de prorrateo
     * @return bloqueProrrateo bloque de servicio de prorrateo, tipo BloqueServicioProrrateo.
     */
    public BloqueServicioProrrateo getBloqueProrrateo() {
        return bloqueProrrateo;
    }

    /**
     * Método que establece los datos del bloque de servicio de prorrateo
     * @param bloqueProrrateo bloque de servicio de prorrateo, tipo BloqueServicioProrrateo.
     */
    public void setBloqueProrrateo(BloqueServicioProrrateo bloqueProrrateo) {
        this.bloqueProrrateo = bloqueProrrateo;
    }

    /**
     * Método que obtiene el indicador de prorrateo.
     * @return indicadorProrrateo indicador de prorrateo, tipo String.
     */
    public String getIndicadorProrrateo() {
        return indicadorProrrateo;
    }

    /**
     * Método que establece el indicador de prorrateo.
     * @param indicadorProrrateo indicador de prorrateo, tipo String.
     */
    public void setIndicadorProrrateo(String indicadorProrrateo) {
        this.indicadorProrrateo = indicadorProrrateo;
    }

    /**
     * Método que obtiene los datos de auditoria.
     * @return auditoria datos de auditoria, tipo Auditoria.
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que establece los datos de auditoria.
     * @param auditoria datos de auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
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
