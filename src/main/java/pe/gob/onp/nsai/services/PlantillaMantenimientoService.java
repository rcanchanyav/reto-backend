/**
 * Resumen.
 * Objeto               :   IPlantillaMantenimientoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la plantilla del modulo de mantenimiento.
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;
import pe.gob.onp.nsai.dto.PlantillaMantenimientoDocumentoCorreo;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;

/**
 * Interface utilizado para la creación de los métodos para la plantilla de mantenimiento
 */
public interface PlantillaMantenimientoService {

    /**
     * Método que permite realizar una consulta de las plantillas del mantenimeinto.
     * @param plantillaBusqueda datos de la plantilla a buscar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @param pagina pagina de busqueda, tipo int.
     * @param nroRegistros numero de registros, tipo int
     * @return resultado de la busqueda, tipo ResultadoBusquedaPlantillaDocumento.
     * @ excepción generada en caso de error.
     */
    public ResultadoBusquedaPlantillaMantenimiento consultarPlantillasDocumentoCorreo(PlantillaMantenimientoDocumentoCorreo plantillaBusqueda,int pagina,int nroRegistros);

    /**
     * Método que permite consultar plantillas.
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la plantilla, tipo List<PlantillaMantenimientoDocumentoCorreo>.
     * @ excepción generada en caso de error.
     */
    public List<PlantillaMantenimientoDocumentoCorreo> obtenerPlantillas(Map<String, Object> parametros);

    /**
     * Método que permite consultar plantillas contrato o adenda.
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la plantilla, tipo List<PlantillaMantenimientoDocumentoCorreo>.
     * @ excepción generada en caso de error.
     */
    public List<PlantillaMantenimientoDocumentoCorreo> obtenerPlantillasDocumentoCorreo(Map<String, Object> parametros);

    /**
     * Método que permite obtener la cantidad de registros de la plantilla.
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return número de registros de la plantilla, tipo int.
     * @ excepción generada en caso de error.
     */
    public int obtenerCantidadRegistrosDocumentoCorreo(PlantillaMantenimientoDocumentoCorreo busquedaPlantilla);

    /**
     * Método que permite verificar si la plantilla tiene detalle.
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return número de registros de la plantilla, tipo int.
     * @ excepción generada en caso de error.
     */
    public int obtenerCantidadRegistrosDetallePlantilla(PlantillaMantenimientoDocumentoCorreo busquedaPlantilla);

    /**
     * Método que permite obtener los datos de la plantilla.
     * @param obtenerPlantilla bean con los datos de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return bean con los datos de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @ excepción generada en caso de error.
     */
    public PlantillaMantenimientoDocumentoCorreo obtenerDatosPlantilla(PlantillaMantenimientoDocumentoCorreo obtenerPlantilla);

    /**
     * Método que permite guardar una plantilla.
     * @param registrarPlantilla datos de la plantilla a guardar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @ excepción generada en caso de error.
     */
    public void registrarPlantilla(PlantillaMantenimientoDocumentoCorreo registrarPlantilla);

    /**
     * Método que permite modificar los datos de la plantilla.
     * @param  modificarPlantilla bean con los datos de la plantilla a modificar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @ excepción generada en caso de error.
     */
    public void modificarPlantilla(PlantillaMantenimientoDocumentoCorreo modificarPlantilla);

    /**
     * Método que permite eliminar los datos de la plantilla.
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @ excepción generada en caso de error.
     */
    public void eliminarPlantilla(PlantillaMantenimientoDocumentoCorreo eliminarPlantilla);

    /**
     * Método que permite eliminar detalle de la plantilla.
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @ excepción generada en caso de error.
     */
    public void eliminarDetallePlantilla(PlantillaMantenimientoDocumentoCorreo eliminarPlantilla);
}
