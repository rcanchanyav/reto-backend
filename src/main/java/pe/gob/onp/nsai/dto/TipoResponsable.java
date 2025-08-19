/**
 * Resumen.
 * Objeto               :   TipoResponsable.java.
 * Descripción          :   Clase que encapsula los campos del tipo de responsable
 * Fecha de Creación    :   29/12/2020
 * PE de Creación       :   INI-900
 * Autor                :   Omar Meza
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */

package pe.gob.onp.nsai.dto;

import java.io.Serializable;

/**
 * Clase que encapsula los campos del tipo de prorrateo
 */
public class TipoResponsable implements Serializable{

    /** Número de versión de la clase Serializable */
    private static final long serialVersionUID = 1245142052232885526L;

    /** Identificador del tipo de prorrateo */
    private Long idTipoResponsable;

    /** Código del tipo de prorrateo */
    private String codigoResponsable;

    /** Descripción del tipo de prorrateo */
    private String descripcionResponsable;

    /** Tipo documento*/
    private String tipoDocumento;
    /** Numero documento*/
    private String numeroDocumento;

    /**
     * Método que obtiene el identificador del tipo de prorrateo.
     * @return idTipoProrrateo identificador del tipo de prorrateo ,tipo Long.
     */

    public Long getIdTipoResponsable() {
        return idTipoResponsable;
    }

    /**
     * Método que establece el identificador del tipo de prorrateo.
     * @param idTipoProrrateo identificador del tipo de prorrateo ,tipo Long.
     */

    public void setIdTipoResponsable(Long idTipoResponsable) {
        this.idTipoResponsable = idTipoResponsable;
    }

    /**
     * Método que obtiene el código de tipo de prorrateo.
     * @return codigoTipoProrrateo código de tipo de prorrateo, tipo String.
     */

    public String getCodigoTipoProrrateo() {
        return codigoResponsable;
    }

    /**
     * Método que establece el código de tipo de prorrateo.
     * @param codigoTipoProrrateo código de tipo de prorrateo, tipo String.
     */

    public void setCodigoResponsable(String codigoResponsable) {
        this.codigoResponsable = codigoResponsable;
    }

    /**
     * Método que obtiene la descripción del tipo de prorrateo.
     * @return descripcionTipoProrrateo descripción del tipo de prorrateo, tipo String.
     */
    public String getDescripcionResponsable() {
        return descripcionResponsable;
    }

    /**
     * Método que establece la descripción del tipo de prorrateo.
     * @param descripcionTipoProrrateo descripción del tipo de prorrateo, tipo String.
     */

    public void setDescripcionResponsable(String descripcionResponsable) {
        this.descripcionResponsable = descripcionResponsable;
    }

    /**
     * Método que obtiene la tipo documento del tipo de prorrateo.
     * @return tipoDocumento identificador del tipo de prorrateo, tipo String.
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Método que establece la tipoDocumento del tipo de prorrateo.
     * @param tipoDocumento descripción del tipo de prorrateo, tipo String.
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Método que obtiene la numero documento del tipo de prorrateo.
     * @return numeroDocumento identificador del tipo de prorrateo, tipo String.
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Método que establece la numeroDocumento del tipo de prorrateo.
     * @param numeroDocumento descripción del tipo de prorrateo, tipo String.
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }





}
