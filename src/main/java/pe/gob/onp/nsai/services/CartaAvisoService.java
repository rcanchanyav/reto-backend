/**
 * Resumen.
 * Objeto               :   ICartaAvisoServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para las cartas de aviso.
 * Fecha de Creación    :   17/02/2021
 * PE de Creación       :   INI-900
 * Autor                :   Igor A. Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;



import pe.gob.onp.nsai.dto.CartaAviso;
import pe.gob.onp.nsai.dto.PlantillaCartaAvisoProgramacion;
import pe.gob.onp.nsai.dto.ProgramacionMantenimiento;
import pe.gob.onp.nsai.dto.PlantillaCartaAvisoDetalle;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;

/**
 * Interface que define métodos para la consulta de la carta de aviso
 */
public interface CartaAvisoService {
    /**
     * Método que permite realizar una consulta de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaMantenimiento consultarServicioInmuebleCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener la lista de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo List<CartaAviso>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<CartaAviso> obtenerListaServicioInmuebleCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener la cantidad de registros de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, Tipo Map<String,Object>.
     * @return número de registros de los inmuebles con cartas de aviso, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCantidadServicioInmuebleCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite realizar una consulta de las cartas de aviso generadas.
     * @param parametros objeto con los datos para las cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaMantenimiento consultarCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener la lista de las cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de las cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo List<CartaAviso>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<CartaAviso> obtenerListaCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener la cantidad de las cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de las cartas de aviso, Tipo Map<String,Object>.
     * @return número de registros de las cartas de aviso, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    public int obtenerCantidadCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener la plantilla de la carta de aviso de programacion
     * @param parametros objeto con los datos para la búsqueda de la plantilla de carta de aviso, Tipo Map<String,Object>.
     * @return datos de la plantilla de carta de aviso, tipo PlantillaCartaAvisoProgramacion.
     * @throws Exception excepción generada en caso de error.
     */
    public PlantillaCartaAvisoProgramacion obtenerPlantillaCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener el documento en formato word con las cartas de aviso
     * @param parametros objeto con los datos para la creación del documento de cartas de aviso, Tipo Map<String,Object>.
     * @return documento en formato word de las cartas de aviso, tipo byte[].
     * @throws Exception excepción generada en caso de error.
     */
    public byte[] getDocumentoCartaAviso(
            Map<String,Object> parametros
    );

    /**
     * Método que permite obtener de manera dinámica el resultado de los datos para las cartas de aviso
     * @param parametros objeto con los datos para la búsqueda de la plantilla de carta de aviso, Tipo Map<String,Object>.
     * @return datos de las marcas de cartas de aviso, tipo List<Map<String,Object>>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<PlantillaCartaAvisoDetalle> listarDatosCartaAvisoGenerada(
            Map<String,Object> parametros
    );

    /**
     * Método que permite modificar el correlativo de la carta de aviso.
     * @param cartaAviso datos carta de aviso a modificar, tipo CartaAviso.
     * @throws Exception excepción generada en caso de error.
     */
    public void modificarCorrelativo(CartaAviso cartaAviso);
}
