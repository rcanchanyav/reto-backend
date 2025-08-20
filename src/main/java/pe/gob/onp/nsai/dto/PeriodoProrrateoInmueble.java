/**
 * Resumen.
 * Objeto               :   PeriodoProrrateoInmueble.java.
 * Descripción          :   Clase utilizada para encapsular los campos de periodos del inmueble para el prorrateo
 * Fecha de Creación    :   04/09/2018
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

/**
 * Clase del Periodo del inmueble para el prorrateo
 */
public class PeriodoProrrateoInmueble implements Serializable{


    /** Código autogenerado */
    private static final long serialVersionUID = -4908757928098354209L;

    /** Identificador de la tabla de periodo del inmueble */
    private Long idPeriodoProrrateo;

    /** Mes del prorrateo */
    private String mesProrrateo;

    /** año del prorrateo */
    private String anioProrrateo;

    /** Identificador del inmueble */
    private String idInmueble;

    /** Estado del prorrateo */
    private String estadoProrrateo;

    /** Datos de auditoria */
    private Auditoria auditoria;



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
     * Método que obtiene el mes de prorrateo
     * @return mesProrrateo mes de prorrateo, tipo String
     */
    public String getMesProrrateo() {
        return mesProrrateo;
    }

    /**
     * Método que establece el mes de prorrateo
     * @param mesProrrateo mes de prorrateo, tipo String
     */
    public void setMesProrrateo(String mesProrrateo) {
        this.mesProrrateo = mesProrrateo;
    }

    /**
     * Método que obtiene el año de prorrateo
     * @return anioProrrateo año de prorrateo, tipo String
     */
    public String getAnioProrrateo() {
        return anioProrrateo;
    }

    /**
     * Método que establece el año de prorrateo
     * @param anioProrrateo año de prorrateo, tipo String
     */
    public void setAnioProrrateo(String anioProrrateo) {
        this.anioProrrateo = anioProrrateo;
    }

    /**
     * Método que obtiene el identificador del inmueble
     * @return idInmueble identificador del inmueble, tipo String
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**
     * Método que establece el identificador del inmueble
     * @param idInmueble identificador del inmueble, tipo String
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }



    /**
     * Método que obtiene el estado del prorrateo.
     * @return estadoProrrateo estado del prorrateo, tipo String.
     */
    public String getEstadoProrrateo() {
        return estadoProrrateo;
    }

    /**
     * Método que establece el estado del prorrateo.
     * @param estadoProrrateo estado del prorrateo, tipo String.
     */
    public void setEstadoProrrateo(String estadoProrrateo) {
        this.estadoProrrateo = estadoProrrateo;
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





}
