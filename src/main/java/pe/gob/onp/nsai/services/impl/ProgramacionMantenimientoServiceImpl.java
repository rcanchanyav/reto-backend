/**
 * Resumen.
 * Objeto               :   ProgramacionMantenimientoEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para el mantenimiento de las programaciones.
 * Fecha de Creación    :   16/12/2020
 * PE de Creación       :   INI-900
 * Autor                :   Igor A. Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.ProgramacionMantenimientoLocalDao;
import pe.gob.onp.nsai.dto.ProgramacionMantenimiento;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDetalle;
import pe.gob.onp.nsai.dto.ResultadoBusquedaInmueble;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.ProgramacionMantenimientoService;

/**
 * Clase que implementa métodos para la programacion de mantenimientos
 */
@Service
public class ProgramacionMantenimientoServiceImpl implements ProgramacionMantenimientoService {

    /** Clase EJB para el mantenimiento de programaciones de mantenimientos */

    private final ProgramacionMantenimientoLocalDao ejbProgramacion;

    public ProgramacionMantenimientoServiceImpl(ProgramacionMantenimientoLocalDao ejbProgramacion) {
        this.ejbProgramacion = ejbProgramacion;
    }

    /**
     * Método que permite realizar una consulta de programacion.
     * @param parametros datos de la programacion a buscar, tipo ProgramacionMantenimiento.
     * @return resultado de datos de la búsqueda de programaciones, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarProgramacion(
            Map<String, Object> parametros
    ) throws Exception {
        try {
            int cantidadListaProgramaciones = obtenerCantidadProgramaciones(parametros);
            List<ProgramacionMantenimiento> listaProgramaciones = obtenerListaProgramaciones(parametros);

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadListaProgramaciones);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaProgramaciones(listaProgramaciones);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la lista de programaciones.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return lista de los mantenimientos programados, tipo List<ProgramacionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ProgramacionMantenimiento> obtenerListaProgramaciones(
            Map<String, Object> parametrosConsulta
    ) throws Exception {
        try {
            return ejbProgramacion.obtenerListaProgramaciones(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de programaciones.
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return cantidad de mantenimientos programados, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadProgramaciones(Map<String, Object> parametros) throws Exception {
        try {
            return ejbProgramacion.obtenerCantidadProgramaciones(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar los inmuebles registrados en una proyeccion de costo.
     * @param busquedaProgramacion datos de la programacion a buscar, tipo ProgramacionMantenimiento.
     * @return datos de los inmuebles registrados en una proyeccion de costo, tipo ResultadoBusquedaInmueble.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaInmueble consultarInmueble(
            ProgramacionMantenimiento busquedaProgramacion
    ) throws Exception {
        List<Inmueble> listaInmuebles;
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigoServicio", busquedaProgramacion.getCodigoServicio());
            listaInmuebles = ejbProgramacion.consultarInmuebles(parametros);

            ResultadoBusquedaInmueble resultado = new ResultadoBusquedaInmueble();
            resultado.setListaInmueble(listaInmuebles);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar los datos de las etapas de una programacion.
     * @param programacionMantenimientoDetalle datos de la programacion a buscar, tipo ProgramacionMantenimientoDetalle.
     * @return datos de las etapas registradas en un mantenimiento de servicio, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarProgramacionMantenimientoDetalle(
            ProgramacionMantenimientoDetalle programacionMantenimientoDetalle
    ) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("codigoProgramacion", programacionMantenimientoDetalle.getCodigoProgramacion());

            List<ProgramacionMantenimientoDetalle> listaProgramacionesMantenimientoDetalle = obtenerListaProgramacionesMantenimientoDetalle(parametrosConsulta);
            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();

            resultado.setListaProgramacionesMantenimientoDetalle(listaProgramacionesMantenimientoDetalle);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar la lista de etapas de una programacion.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return datos de las etapas registradas en una programacion, tipo List<ProgramacionMantenimientoDetalle>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ProgramacionMantenimientoDetalle> obtenerListaProgramacionesMantenimientoDetalle(
            Map<String, Object> parametrosConsulta
    ) throws Exception {
        try {
            return ejbProgramacion.obtenerListaProgramacionesDetalle(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite registrar datos de la programacion.
     * @param programacionMatenimiento datos de la programacion de mantenimiento a registrar, tipo ProgramacionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void registrarProgramacion(
            ProgramacionMantenimiento programacionMatenimiento
    ) throws Exception {
        try {
            ejbProgramacion.registrarProgramacion(programacionMatenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite eliminar datos de la programacion del mantenimiento.
     * @param programacionMantenimiento datos de la programacion a eliminar, tipo ProgramacionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarProgramacionMantenimiento(
            ProgramacionMantenimiento programacionMantenimiento
    ) throws Exception {
        try {
            ejbProgramacion.eliminarProgramacionMantenimiento(programacionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar la programacion del mantenimiento según el código
     * @param codigoProgramacion código de la programacion del mantenimiento a consultar, tipo int.
     * @return programacionMantenimientoObtenida datos de la programacion de mantemiento, tipo ProgramacionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ProgramacionMantenimiento obtenerProgramacionMantenimiento(int codigoProgramacion) throws Exception {
        try {
            return ejbProgramacion.obtenerProgramacionMantenimiento(codigoProgramacion);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar datos de la programacion de mantenimiento.
     * @param programacionMantenimiento datos de la programacion de mantenimeinto a modificar, tipo ProgramacionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarProgramacionMantenimiento(ProgramacionMantenimiento programacionMantenimiento) throws Exception {
        try {
            ejbProgramacion.modificarProgramacionMantenimiento(programacionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite eliminar datos de la etapa de la programacion del mantenimiento.
     * @param programacionMantenimientoDetalle datos del programacion de mantenimiento a eliminar, tipo ProgramacionMantenimientoDetalle.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarProgramacionMantenimientoDetalle(
            ProgramacionMantenimientoDetalle programacionMantenimientoDetalle
    ) throws Exception {
        try {
            ejbProgramacion.eliminarProgramacionMantenimientoDetalle(programacionMantenimientoDetalle);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar la etapa de la programacion del mantenimiento según los datos de búqueda
     * @param pmd datos de la etapa de la programacion del mantenimiento a consultar, tipo ProgramacionMantenimientoDetalle.
     * @return ProgramacionMantenimientoDetalle datos de la etapa de la programacion, tipo ProgramacionMantenimientoDetalle.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ProgramacionMantenimientoDetalle obtenerEtapaProgramacionMatenimientoDetalle(
            ProgramacionMantenimientoDetalle pmd
    ) throws Exception {
        try {
            return ejbProgramacion.obtenerEtapaProgramacionMatenimientoDetalle(pmd);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite registrar datos del detalle de la programacion.
     * @param pmd datos de la etapa de programacion a registrar, tipo ProgramacionMantenimientoDetalle.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarProgramacionMantenimientoDetalle(
            ProgramacionMantenimientoDetalle pmd
    ) throws Exception {
        try {
            ejbProgramacion.modificarProgramacionMantenimientoDetalle(pmd);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener el detalle de la lista de programaciones.
     * @param programacionMantenimiento mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listado de registros con los datos de los detalles de la programacion, tipo List<ProgramacionMantenimientoDetalle>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento obtenerProgramacionMantenimientoDetalle(
            ProgramacionMantenimiento programacionMantenimiento
    ) throws Exception {
        try {
            List<ProgramacionMantenimientoDetalle> listaProgramaciones = ejbProgramacion.obtenerProgramacionMantenimientoDetalle(programacionMantenimiento);
            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setListaProgramacionesDetalle(listaProgramaciones);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar el monto total del servicio que se envia como parámetro
     * @param parametros datos de la búsqueda para el monto total de la programacion, tipo Map<String, Object>.
     * @return datos con el monto total del servicio proyectado, tipo BigDecimal.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Map<String, Object> obtenerMontoTotalServicioProgramacion(Map<String, Object> parametros) throws Exception {
        try {
            BigDecimal montoTotal = ejbProgramacion.obtenerMontoTotalServicioProgramacion(parametros);
            Map<String, Object> resultado = new HashMap<>();
            resultado.put("montoTotal", montoTotal);

            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

}


