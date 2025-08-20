/**
 * Resumen.
 * Objeto               :   BloqueServicioProrrateo.java.
 * Descripción          :   Clase utilizada para encapsular los campos del bloque del servicio de prorrateo.
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
import java.math.BigDecimal;

/**
 * Clase que encapsula los datos del bloque del servicio para el prorrateo.
 */
public class BloqueServicioProrrateo implements Serializable{

    /** Código autogenerado */
    private static final long serialVersionUID = -5798904316930146634L;

    /** Identificador de la tabla del bloque del servicio para el prorrateo */
    private Long idBloqueServicioProrrateo;

    /** Identificador de la tabla del servicio del inmueble para el prorrateo */
    private Long idServicioProrrateoInmueble;

    /** Identificador del bloque */
    private Long idBloque;

    /** Porcentaje de incidencia del bloque */
    private BigDecimal porcentajeIncidencia;

    /** Tipo de prorrateo */
    private String tipoProrrateo;

    /** Indicador de prorrateo */
    private String indicadorProrrateo;

    /** Descripción del bloque */
    private String descripcionBloque;

    /** Servicio de prorrateo */
    private ServicioProrrateoInmueble servicioProrrateo;

    /** Datos de auditoria */
    private Auditoria auditoria;

    /** Numero de fila */
    private int item;

    /**
     * Método que permite obtener el identificador de la tabla del bloque del servicio para el prorrateo
     * @return idBloqueServicioProrrateo identificador de la tabla del bloque del servicio para el prorrateo, tipo Long
     */
    public Long getIdBloqueServicioProrrateo() {
        return idBloqueServicioProrrateo;
    }

    /**
     * Método que permite establecer el identificador de la tabla del bloque del servicio para el prorrateo
     * @param idBloqueServicioProrrateo identificador de la tabla del bloque del servicio para el prorrateo, tipo Long
     */
    public void setIdBloqueServicioProrrateo(Long idBloqueServicioProrrrateo) {
        this.idBloqueServicioProrrateo = idBloqueServicioProrrrateo;
    }

    /**
     * Método que permite obtener el identificador de la tabla del servicio del inmueble para el prorrateo
     * @return idServicioProrrateoInmueble identificador de la tabla de servicio del inmueble para el prorrateo, tipo Long
     */
    public Long getIdServicioProrrateoInmueble() {
        return idServicioProrrateoInmueble;
    }

    /**
     * Método que permite establecer el identificador de la tabla del servicio del inmueble para el prorrateo
     * @param idServicioProrrateoInmueble identificador de la tabla de servicio del inmueble para el prorrateo, tipo Long
     */
    public void setIdServicioProrrateoInmueble(Long idServicioProrrateoInmueble) {
        this.idServicioProrrateoInmueble = idServicioProrrateoInmueble;
    }

    /**
     * Método que permite obtener el identificador del bloque.
     * @return idBloque identificador del bloque, tipo Long.
     */
    public Long getIdBloque() {
        return idBloque;
    }

    /**
     * Método que permite establecer el identificador del bloque.
     * @param idBloque identificador del bloque, tipo Long.
     */
    public void setIdBloque(Long idBloque) {
        this.idBloque = idBloque;
    }

    /**
     * Método que permite obtener el porcentaje de incidencia
     * @return porcentajeIncidencia porcentaje de incidencia, tipo BigDecimal
     */
    public BigDecimal getPorcentajeIncidencia() {
        return porcentajeIncidencia;
    }

    /**
     * Método que permite establecer el porcentaje de incidencia
     * @param porcentajeIncidencia porcentaje de incidencia, tipo BigDecimal
     */
    public void setPorcentajeIncidencia(BigDecimal porcentajeIncidencia) {
        this.porcentajeIncidencia = porcentajeIncidencia;
    }

    /**
     * Método que permite obtener el tipo de prorrateo
     * @return tipoProrrateo tipo de prorrateo, tipo String.
     */
    public String getTipoProrrateo() {
        return tipoProrrateo;
    }

    /**
     * Método que permite establecer el tipo de prorrateo
     * @param tipoProrrateo tipo de prorrateo, tipo String.
     */
    public void setTipoProrrateo(String tipoProrrateo) {
        this.tipoProrrateo = tipoProrrateo;
    }

    /**
     * Método que permite obtener la descripción del bloque
     * @return descripcionBloque descripción del bloque, tipo String.
     */
    public String getDescripcionBloque() {
        return descripcionBloque;
    }

    /**
     * Método que permite establecer la descripción del bloque
     * @param descripcionBloque descripción del bloque, tipo String.
     */
    public void setDescripcionBloque(String descripcionBloque) {
        this.descripcionBloque = descripcionBloque;
    }

    /**
     * Método que permite obtener los datos del servicio del prorrateo
     * @return servicioProrrateo datos del servicio del prorrateo, tipo ServicioProrrateoInmueble.
     */
    public ServicioProrrateoInmueble getServicioProrrateo() {
        return servicioProrrateo;
    }

    /**
     * Método que permite establecer los datos del servicio del prorrateo
     * @param servicioProrrateo datos del servicio del prorrateo, tipo ServicioProrrateoInmueble.
     */
    public void setServicioProrrateo(ServicioProrrateoInmueble servicioProrrateo) {
        this.servicioProrrateo = servicioProrrateo;
    }

    /**
     * Método que obtiene los datos de auditoria.
     * @return auditoria datos de auditoria, tipo Auditoria.
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que establece los datos de auditoria..
     * @param auditoria datos de auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
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
