
/**
 * Resumen.
 * Objeto               :   ReporteVerificacionProrrateoBloque.java.
 * Descripción          :   Clase que define los datos de verificación del proceso de prorrateo por Bloque.
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
 * Clase con los datos generales de la verificación del  Bloque a prorratear.
 *
 */
public class ReporteVerificacionProrrateoBloque implements Serializable{

    /**
     * Clave serial autogenerada
     */
    private static final long serialVersionUID = 9071731949607255072L;
    private String idInmueble;
    private String inmueble;
    private String idBloque;
    private String bloque;
    private String idServicio;
    private String servicio;
    private String prorrateoBloqueP1;
    private String prorrateoPredioP1;
    private String prorrateoBloqueP2;
    private String prorrateoPredioP2;

    public String getIdInmueble() {
        return idInmueble;
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
    public String getIdBloque() {
        return idBloque ==null?"":idBloque;
    }
    public void setIdBloque(String idBloque) {
        this.idBloque = idBloque;
    }
    public String getBloque() {
        return bloque  ==null?"":bloque;
    }
    public void setBloque(String bloque) {
        this.bloque = bloque;
    }
    public String getIdServicio() {
        return idServicio  ==null?"":idServicio;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    public String getServicio() {
        return servicio  ==null?"":servicio;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public String getProrrateoBloqueP1() {
        return prorrateoBloqueP1 ==null?"":prorrateoBloqueP1;
    }
    public void setProrrateoBloqueP1(String prorrateoBloqueP1) {
        this.prorrateoBloqueP1 = prorrateoBloqueP1;
    }
    public String getProrrateoPredioP1() {
        return prorrateoPredioP1  ==null?"":prorrateoPredioP1;
    }
    public void setProrrateoPredioP1(String prorrateoPredioP1) {
        this.prorrateoPredioP1 = prorrateoPredioP1;
    }
    public String getProrrateoBloqueP2() {
        return prorrateoBloqueP2;
    }
    public void setProrrateoBloqueP2(String prorrateoBloqueP2) {
        this.prorrateoBloqueP2 = prorrateoBloqueP2;
    }
    public String getProrrateoPredioP2() {
        return prorrateoPredioP2;
    }
    public void setProrrateoPredioP2(String prorrateoPredioP2) {
        this.prorrateoPredioP2 = prorrateoPredioP2;
    }




}
