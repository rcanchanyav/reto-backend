/**
 * Resumen.
 * Objeto               :   PlantillaMantenimientoDocumentoCorreoDelegateEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para las plantillas de documento y correo.
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.PlantillaMantenimientoDocumentoCorreoLocalDao;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.PlantillaMantenimientoDocumentoCorreo;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;
import pe.gob.onp.nsai.services.PlantillaMantenimientoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase delegate utilizada para la creación de los métodos para las plantillas de documento y correo.
 */
@Service
public class PlantillaMantenimientoDocumentoCorreoServiceImpl implements PlantillaMantenimientoService {

    /**
     * Clase EJB para el mantenimiento de la plantilla.
     */
    private final PlantillaMantenimientoDocumentoCorreoLocalDao ejbPlantillaDocumentoCorreo;

    public PlantillaMantenimientoDocumentoCorreoServiceImpl(PlantillaMantenimientoDocumentoCorreoLocalDao ejbPlantillaDocumentoCorreo) {
        this.ejbPlantillaDocumentoCorreo = ejbPlantillaDocumentoCorreo;
    }

    /**
     * Método que permite realizar una consulta de las plantillas.
     *
     * @param plantillaBusqueda datos de la plantilla a buscar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @param pagina            pagina de busqueda, tipo int.
     * @param nroRegistros      numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo ResultadoBusquedaPlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaPlantillaMantenimiento consultarPlantillasDocumentoCorreo(PlantillaMantenimientoDocumentoCorreo plantillaBusqueda, int pagina, int nroRegistros) throws Exception {
        Map<String, Object> parametrosConsulta = new HashMap<>();
        parametrosConsulta.put("tipoPlantilla", plantillaBusqueda.getTipoPlantilla());
        parametrosConsulta.put("codigoTipoPlantillaDocumento", plantillaBusqueda.getCodigoTipoPlantillaDocumento());
        parametrosConsulta.put("pagina", pagina);
        parametrosConsulta.put("paginacion", nroRegistros);

        int cantidadRegistrosBusquedaInmueble = obtenerCantidadRegistrosDocumentoCorreo(plantillaBusqueda);
        List<PlantillaMantenimientoDocumentoCorreo> listaPlantillas = obtenerPlantillasDocumentoCorreo(parametrosConsulta);

        Paginacion paginacion = new Paginacion();
        paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaInmueble);

        ResultadoBusquedaPlantillaMantenimiento resultado = new ResultadoBusquedaPlantillaMantenimiento();
        resultado.setPaginacion(paginacion);
        resultado.setListaPlantillaDocumentoCorreo(listaPlantillas);
        return resultado;
    }

    /**
     * Método que permite consultar plantillas de documento.
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaPlantillas lista registros con los datos de las plantillas, tipo List<PlantillaMantenimientoDocumentoCorreo>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PlantillaMantenimientoDocumentoCorreo> obtenerPlantillas(Map<String, Object> parametros) throws Exception {
        List<PlantillaMantenimientoDocumentoCorreo> listaPlantillas;
        try {
            listaPlantillas = ejbPlantillaDocumentoCorreo.consultarPlantillasDocumento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaPlantillas;
    }


    /**
     * Método que permite consultar plantillas de documento contrato o adenda.
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaPlantillas lista registros con los datos de las plantillas, tipo List<PlantillaMantenimientoDocumentoCorreo>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PlantillaMantenimientoDocumentoCorreo> obtenerPlantillasDocumentoCorreo(Map<String, Object> parametros) throws Exception {
        List<PlantillaMantenimientoDocumentoCorreo> listaPlantillas;
        try {
            listaPlantillas = ejbPlantillaDocumentoCorreo.consultarPlantillasDocumentoCorreo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaPlantillas;
    }

    /**
     * Método que permite obtener la cantidad de registros de la plantilla.
     *
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return cantidad número de registros de la plantilla, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadRegistrosDocumentoCorreo(PlantillaMantenimientoDocumentoCorreo busquedaPlantilla) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPlantillaDocumentoCorreo.obtenerCantidadRegistrosDocumentoCorreo(busquedaPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite verificar si la plantilla tiene detalle.
     *
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return cantidad número de registros de la plantilla, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadRegistrosDetallePlantilla(PlantillaMantenimientoDocumentoCorreo busquedaPlantilla) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPlantillaDocumentoCorreo.obtenerCantidadRegistrosDetallePlantilla(busquedaPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    /**
     * Método que permite obtener los datos de la plantilla.
     *
     * @param obtenerPlantilla datos de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @return obtenerPlantillaDocu bean con los datos de la plantilla, tipo PlantillaMantenimientoDocumentoCorreo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PlantillaMantenimientoDocumentoCorreo obtenerDatosPlantilla(PlantillaMantenimientoDocumentoCorreo obtenerPlantilla) throws Exception {
        PlantillaMantenimientoDocumentoCorreo obtenerPlantillaDocu;
        try {
            obtenerPlantillaDocu = ejbPlantillaDocumentoCorreo.obtenerDatosPlantilla(obtenerPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return obtenerPlantillaDocu;
    }

    /**
     * Método que permite guardar la plantilla.
     *
     * @param registrarPlantilla datos de la plantilla a guardar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void registrarPlantilla(PlantillaMantenimientoDocumentoCorreo registrarPlantilla) throws Exception {
        try {
            ejbPlantillaDocumentoCorreo.registrarPlantilla(registrarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar la plantilla.
     *
     * @param modificarPlantilla bean con los datos de la plantilla a modificar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarPlantilla(PlantillaMantenimientoDocumentoCorreo modificarPlantilla) throws Exception {
        try {
            ejbPlantillaDocumentoCorreo.modificarPlantilla(modificarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite eliminar la plantilla.
     *
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarPlantilla(PlantillaMantenimientoDocumentoCorreo eliminarPlantilla) throws Exception {
        try {
            ejbPlantillaDocumentoCorreo.eliminarPlantilla(eliminarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }


    /**
     * Método que permite eliminar detalle de la plantilla..
     *
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimientoDocumentoCorreo.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarDetallePlantilla(PlantillaMantenimientoDocumentoCorreo eliminarPlantilla) throws Exception {
        try {
            ejbPlantillaDocumentoCorreo.eliminarDetallePlantilla(eliminarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }
}
