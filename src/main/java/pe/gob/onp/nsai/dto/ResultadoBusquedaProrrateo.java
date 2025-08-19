/**
 * Resumen.
 * Objeto               :   ResultadoBusquedaProrrateo.java.
 * Descripción          :   Clase utilizada para encapsular los resultados de la búsqueda de prorrateos.
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
import java.util.List;

/** Clase utilizada para encapsular los resultados de la búsqueda de prorrateos */
public class ResultadoBusquedaProrrateo implements Serializable{

    /** Código autogenerado */
    private static final long serialVersionUID = -845060169504298981L;

    /** Datos de la paginación */
    private Paginacion paginacion;

    /** Lista de servicios para el prorrateo */
    private List<ServicioProrrateoInmueble> listaServiciosProrrateo;

    /** Lista de bloques para el prorrateo */
    private List<BloqueServicioProrrateo> listaBloqueProrrateo;

    /** Lista de predios para el prorrateo */
    private List<PredioServicioProrrateo> listaPredioProrrateo;

    /** Lista de prorrateo predios general para el prorrateo */
    private List<ProrrateoPredioGeneral> listaProrrateoPredioGeneral;

    /**
     * Método que obtiene la paginación
     * @return paginacion datos de paginación, tipo Paginacion
     */
    public Paginacion getPaginacion() {
        return paginacion;
    }

    /**
     * Método que establece la paginación
     * @param paginacion datos de paginación, tipo Paginacion
     */
    public void setPaginacion(Paginacion paginacion) {
        this.paginacion = paginacion;
    }

    /**
     * Método que obtiene la lista de servicios del prorrateo
     * @return listaServiciosProrrateo lista de servicios del prorrateo, tipo List<ServicioProrrateoInmueble>
     */
    public List<ServicioProrrateoInmueble> getListaServiciosProrrateo() {
        return listaServiciosProrrateo;
    }

    /**
     * Método que establece la lista de servicios del prorrateo
     * @param listaServiciosProrrateo lista de servicios del prorrateo, tipo List<ServicioProrrateoInmueble>
     */
    public void setListaServiciosProrrateo(List<ServicioProrrateoInmueble> listaServiciosProrrateo) {
        this.listaServiciosProrrateo = listaServiciosProrrateo;
    }

    /**
     * Método que obtiene la lista de bloques para el prorrateo
     * @return listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>
     */
    public List<BloqueServicioProrrateo> getListaBloqueProrrateo() {
        return listaBloqueProrrateo;
    }

    /**
     * Método que establece la lista de bloques para el prorrateo
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>
     */
    public void setListaBloqueProrrateo(List<BloqueServicioProrrateo> listaBloqueProrrateo) {
        this.listaBloqueProrrateo = listaBloqueProrrateo;
    }

    /**
     * Método que obtiene la lista de predios para el prorrateo
     * @return listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>
     */
    public List<PredioServicioProrrateo> getListaPredioProrrateo() {
        return listaPredioProrrateo;
    }

    /**
     * Método que establece la lista de predios para el prorrateo
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>
     */
    public void setListaPredioProrrateo(List<PredioServicioProrrateo> listaPredioProrrateo) {
        this.listaPredioProrrateo = listaPredioProrrateo;
    }

    /**
     * Método que obtiene la lista de prorrateo predios general para el prorrateo
     * @return listaProrrateoPredioGeneral lista de predios para el prorrateo, tipo List<ProrrateoPredioGeneral>
     */
    public List<ProrrateoPredioGeneral> getListaProrrateoPredioGeneral() {
        return listaProrrateoPredioGeneral;
    }

    /**
     * Método que establece la lista de prorrateo predios general para el prorrateo
     * @param listaProrrateoPredioGeneral lista de predios para el prorrateo, tipo List<ProrrateoPredioGeneral>
     */
    public void setListaProrrateoPredioGeneral(List<ProrrateoPredioGeneral> listaProrrateoPredioGeneral) {
        this.listaProrrateoPredioGeneral = listaProrrateoPredioGeneral;
    }








}
