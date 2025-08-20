/**
 * Resumen.
 * Objeto               :   ReporteVerificacionProrrateo.java.
 * Descripción          :   Clase que define los datos de verificación del proceso de prorrateo.
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
import java.util.List;

/**
 * Clase con los datos generales de la verificación del proceso a prorratear.
 *
 */
public class ReporteVerificacionProrrateoPredio implements Serializable{

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
    private String idPredio;
    private String predio;
    private String afectoP1;
    private String afectoP2;
    private String anio;
    private String mes;
    private String descMes;

    public String getIdInmueble() {
        return idInmueble ==null?"":idInmueble;
    }
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }
    public String getInmueble() {
        return inmueble  ==null?"":inmueble;
    }
    public void setInmueble(String inmueble) {
        this.inmueble = inmueble;
    }
    public String getIdBloque() {
        return idBloque  ==null?"":idBloque;
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
        return servicio ==null?"":servicio;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public String getIdPredio() {
        return idPredio ==null?"":idPredio;
    }
    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }
    public String getPredio() {
        return predio ==null?"":predio;
    }
    public void setPredio(String predio) {
        this.predio = predio;
    }
    public String getAfectoP1() {
        return afectoP1  ==null?"":afectoP1;
    }
    public void setAfectoP1(String afectoP1) {
        this.afectoP1 = afectoP1;
    }
    public String getAfectoP2() {
        return afectoP2 ==null?"":afectoP2;
    }
    public void setAfectoP2(String afectoP2) {
        this.afectoP2 = afectoP2;
    }

    public String getAnio() {
        return anio ==null?"":anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }
    public String getMes() {
        return mes  ==null?"":mes;
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

}
