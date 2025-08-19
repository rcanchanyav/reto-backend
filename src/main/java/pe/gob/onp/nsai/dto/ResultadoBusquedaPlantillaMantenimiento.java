/**
 * Resumen.
 * Objeto               :   ResultadoBusquedaPlantillaMantenimiento.java.
 * Descripción          :   DTO utilizado para almacenar los resultados de la búsqueda de la plantilla de mantenimientos.
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
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
 * Clase utilizado para almacenar los resultados de la búsqueda.
 */
public class ResultadoBusquedaPlantillaMantenimiento implements Serializable{

    /**
     * Número de serie generado
     */
    private static final long serialVersionUID = -189487284838104742L;

    /** Lista de Parámetros */
    private List<Parametros> listaParametro;

    /** Lista del detalle de Parámetros */
    private List<Parametros> listaParametroDetalle;

    /** Listado con las plantillas. */
    private List<PlantillaMantenimiento> listaPlantilla;

    /** Listado con las plantillas de documento y correo. */
    private List<PlantillaMantenimientoDocumentoCorreo> listaPlantillaDocumentoCorreo;


    /** Objeto para almacenar la paginación */
    private Paginacion paginacion;

    /**
     * Método que permite obtener la lista de parametros.
     * @return listaParametro lista de parametros, tipo List<Parametros>.
     */
    public List<Parametros> getListaParametro() {
        return listaParametro;
    }

    /**
     * Método que permite establecer la lista de parametros.
     * @param listaParametro lista de parametros, tipo List<Parametros>.
     */
    public void setListaParametro(List<Parametros> listaParametro) {
        this.listaParametro = listaParametro;
    }

    /**
     * Método que permite obtener la lista del detalle de parametros.
     * @return listaParametroDetalle lista del detalle de parametros, tipo List<Parametros>.
     */
    public List<Parametros> getListaParametroDetalle() {
        return listaParametroDetalle;
    }

    /**
     * Método que permite establecer la lista del detalle de parametros.
     * @param listaParametroDetalle lista del detalle de parametros, tipo List<Parametros>.
     */
    public void setListaParametroDetalle(List<Parametros> listaParametroDetalle) {
        this.listaParametroDetalle = listaParametroDetalle;
    }

    /**
     * Método que permite obtener la lista de plantillas.
     * @return listaPlantilla lista de plantillas, tipo List<PlantillaDocumento>.
     */
    public List<PlantillaMantenimiento> getListaPlantilla() {
        return listaPlantilla;
    }

    /**
     * Método que permite establecer la lista de plantillas.
     * @param listaPlantilla lista de plantillas, tipo List<PlantillaDocumento>.
     */
    public void setListaPlantilla(List<PlantillaMantenimiento> listaPlantilla) {
        this.listaPlantilla = listaPlantilla;
    }

    /**
     * Método que permite obtener la paginación actual.
     * @return paginacion paginación actual, tipo Paginacion.
     */
    public Paginacion getPaginacion() {
        return paginacion;
    }

    /**
     * Método que permite establecer la paginación actual.
     * @param paginacion paginación actual, tipo Paginacion.
     */
    public void setPaginacion(Paginacion paginacion) {
        this.paginacion = paginacion;
    }

    /**
     * @return the listaPlantillaDocumentoCorreo
     */
    public List<PlantillaMantenimientoDocumentoCorreo> getListaPlantillaDocumentoCorreo() {
        return listaPlantillaDocumentoCorreo;
    }

    /**
     * @param listaPlantillaDocumentoCorreo the listaPlantillaDocumentoCorreo to set
     */
    public void setListaPlantillaDocumentoCorreo(List<PlantillaMantenimientoDocumentoCorreo> listaPlantillaDocumentoCorreo) {
        this.listaPlantillaDocumentoCorreo = listaPlantillaDocumentoCorreo;
    }

}
