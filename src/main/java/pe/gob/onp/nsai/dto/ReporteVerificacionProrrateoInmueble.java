
/**
 * Resumen.
 * Objeto               :   ReporteVerificacionProrrateoInmueble.java.
 * Descripción          :   Clase que define los datos de verificación del proceso de prorrateo por Inmueble.
 * Fecha de Creación    :   25/10/2022
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


/**
 * Clase con los datos generales de la verificación del  Inmueble a prorratear.
 *
 */
public class ReporteVerificacionProrrateoInmueble implements Serializable {
    /**
     * Clave serial autogenerada
     */
    private static final long serialVersionUID = 9071731949607255072L;
    private String idInmueble;
    private String inmueble;
    private String idServicio;
    private String servicio;
    private String prorrateoP1;
    private String prorrateoP2;

    public String getIdInmueble() {
        return idInmueble==null?"":idInmueble;
    }
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }
    public String getInmueble() {
        return inmueble==null?"":inmueble;
    }
    public void setInmueble(String inmueble) {
        this.inmueble = inmueble;
    }
    public String getIdServicio() {
        return idServicio==null?"":idServicio;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    public String getServicio() {
        return servicio==null?"":servicio;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public String getProrrateoP1() {
        return prorrateoP1==null?"":prorrateoP1;
    }
    public void setProrrateoP1(String prorrateoP1) {
        this.prorrateoP1 = prorrateoP1;
    }
    public String getProrrateoP2() {
        return prorrateoP2 ==null?"":prorrateoP2;
    }
    public void setProrrateoP2(String prorrateoP2) {
        this.prorrateoP2 = prorrateoP2;
    }


}
