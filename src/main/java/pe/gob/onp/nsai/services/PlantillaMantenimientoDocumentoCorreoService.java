/**
 * Resumen.
 * Objeto               :   IPlantillaMantenimientoDocumentoCorreoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la plantilla del documento o correo.
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services;

import jakarta.servlet.ServletContext;
import pe.gob.onp.nsai.dto.PlantillaMantenimiento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

/**
 * Interface que define los métodos para el mantenimiento de la plantilla del documento o correo.
 */
public interface PlantillaMantenimientoDocumentoCorreoService {

    /**
     * Método que permite realizar una consulta de las plantillas del documento.
     *
     * @param plantillaBusqueda datos de la plantilla a buscar, tipo PlantillaMantenimiento.
     * @param pagina            pagina de busqueda, tipo int.
     * @param nroRegistros      numero de registros, tipo int
     * @return resultado de la busqueda, tipo ResultadoBusquedaPlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaPlantillaMantenimiento consultarPlantillasDocumento(PlantillaMantenimiento plantillaBusqueda, int pagina, int nroRegistros);

    /**
     * Método que permite consultar plantillas.
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de registros con los datos de la plantilla, tipo List<PlantillaMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<PlantillaMantenimiento> obtenerPlantillas(Map<String, Object> parametros);

    /**
     * Método que permite obtener la cantidad de registros de la plantilla.
     *
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimiento.
     * @return número de registros de la plantilla, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCantidadRegistrosBusquedaPlantilla(PlantillaMantenimiento busquedaPlantilla);

    /**
     * Método que permite obtener los datos de la plantilla.
     *
     * @param obtenerPlantilla bean con los datos de la plantilla, tipo PlantillaMantenimiento.
     * @return bean con los datos de la plantilla, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public PlantillaMantenimiento obtenerDatosPlantilla(PlantillaMantenimiento obtenerPlantilla);

    /**
     * Método que permite guardar una plantilla.
     *
     * @param registrarPlantilla datos de la plantilla a guardar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void registrarPlantilla(PlantillaMantenimiento registrarPlantilla);

    /**
     * Método que permite modificar los datos de la plantilla.
     *
     * @param modificarPlantilla bean con los datos de la plantilla a modificar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void modificarPlantilla(PlantillaMantenimiento modificarPlantilla);

    /**
     * Método que permite eliminar los datos de la plantilla.
     *
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public void eliminarPlantilla(PlantillaMantenimiento eliminarPlantilla);

    /**
     * Método que permite obtener la cantidad de secciones.
     *
     * @param codigoPlantilla código de la plantilla, tipo Integer.
     * @param codigoSeccion   código de la seccion de la plantilla, tipo String.
     * @return cantidad de secciones según los filtros de busqueda, tipo int
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCantidadPlantillaSeccion(Integer codigoPlantilla, String codigoSeccion);

    /**
     * Método que permite generar la plantilla del correo.
     *
     * @param idPlantillaMantenimiento identificador de la plantilla del documento, tipo String.
     * @return arreglo de bytes del archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    public ByteArrayOutputStream generarPlantillaCorreo(Integer idPlantillaMantenimiento);

    /**
     * Método que obtiene las clausulas según el tipo de plantilla indicada
     *
     * @param idPlantillaMantenimiento identificador de la plantilla del documento, tipo Integer.
     * @return lista de plantillas de las clausulas, tipo List<PlantillaMantenimiento>
     * @throws Exception excepcion generada en caso de error.
     */
    public List<PlantillaMantenimiento> obtenerListaClausulasPlantilla(Integer idPlantillaMantenimiento);

    /**
     * Método que obtiene los números de orden de las clausulas según el tipo de plantilla indicada
     *
     * @param idPlantillaMantenimiento identificador de la plantilla del documento, tipo Integer.
     * @return lista de numeros de orden de las clausulas, tipo List<String>
     * @throws Exception excepcion generada en caso de error.
     */
    public List<String> obtenerOrdenClausulasPlantilla(Integer idPlantillaMantenimiento);

    /**
     * Método que obtiene los números de orden de las clausulas según el tipo de plantilla indicada para su modificacion
     *
     * @param idPlantillaMantenimiento identificador de la plantilla del documento, tipo Integer.
     * @param identificador            identificador de la plantilla a modificar, tipo Integer.
     * @return lista de numeros de orden de las clausulas, tipo List<String>
     * @throws Exception excepcion generada en caso de error.
     */
    public List<String> obtenerOrdenClausulasPlantillaModificacion(Integer idPlantillaMantenimiento, Integer identificador);

    /**
     * Método que obtiene el id de la plantilla según el identificador.
     *
     * @param datosPlantilla bean con los datos de la plantilla, tipo PlantillaMantenimiento.
     * @return identificador de la plantilla, tipo Integer.
     * @throws Exception excepción generada en caso de error.
     */
    public Integer obtenerIdPlantilla(PlantillaMantenimiento datosPlantilla);

    /**
     * Método que obtiene los datos del tipo de la plantilla según el identificador
     *
     * @param idPlantillaMantenimiento identificador del tipo de la plantilla, tipo Integer.
     * @return datos del tipo de plantilla del documento, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public PlantillaMantenimiento obtenerTipoPlantilla(Integer idPlantillaMantenimiento);

    /**
     * Método que permite generar una plantilla de documento
     *
     * @param idPlantillaMantenimiento identificador de la plantilla a descargar, tipo Integer.
     * @return documento word generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    public ByteArrayOutputStream generarPlantillaDocumento(Integer idPlantillaMantenimiento, ServletContext context);

    /**
     * Método que permite generar una plantilla de convivencia
     *
     * @param idPlantillaMantenimiento identificador de la plantilla a descargar, tipo Integer.
     * @return documento word generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    public ByteArrayOutputStream generarPlantillaCorreoNotificacion(Integer idPlantillaMantenimiento);

}
