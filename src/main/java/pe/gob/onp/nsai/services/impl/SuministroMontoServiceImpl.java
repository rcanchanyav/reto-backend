/**
 * Resumen.
 * Objeto               :   SuministroMontoDelegateEJB.java.
 * Descripción          :   Clase utilizada para la creación de los métodos para el registro de montos del suministro.
 * Fecha de Creación    :   04/09/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Cipriano Méndez Gálvez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.SuministroMontoLocalDao;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.dto.SuministroMonto;
import pe.gob.onp.nsai.infrastructure.client.ServicioSparmag;
import pe.gob.onp.nsai.infrastructure.dto.ParametroDetalle;
import pe.gob.onp.nsai.services.SuministroServicioMontoService;
import pe.gob.onp.nsai.util.UConvierteFecha;


/**
 * Clase delegate para la creación de los métodos para el registro de montos del suministro.
 */
@Service
public class SuministroMontoServiceImpl implements SuministroServicioMontoService {

    /** Clase EJB para el mantenimiento del monto del suministro. */

    private final SuministroMontoLocalDao ejbSuministro;

    /** Objeto que permite localizar servicios de otros sistemas */
    private final ServicioSparmag servicioSparmag;

    public SuministroMontoServiceImpl(SuministroMontoLocalDao ejbSuministro, ServicioSparmag servicioSparmag) {
        this.ejbSuministro = ejbSuministro;
        this.servicioSparmag = servicioSparmag;
    }

    /**
     * Método que permite consultar los suministros.
     * @param suministroBusqueda objeto con los datos del suministro, tipo SuministroMonto.
     * @param pagina pagina actual de consulta, tipo int.
     * @param nroRegistros cantidad de registros a recuperar, tipo int.
     * @return resultado objeto con los datos de los suministros, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     *
     */
    @Override
    public ResultadoBusquedaMantenimiento obtenerSuministros(SuministroMonto suministroBusqueda, int pagina, int nroRegistros) throws Exception {
        Map<String, Object> parametrosBusqueda = new HashMap<>();
        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        List<SuministroMonto> listadoSuministros;
        try {
            parametrosBusqueda.put("idInmueble", suministroBusqueda.getIdInmueble());
            parametrosBusqueda.put("codigoTipoServicio", suministroBusqueda.getCodigoTipoServicio());
            parametrosBusqueda.put("descripcionInmueble", suministroBusqueda.getDescripcionInmueble());
            parametrosBusqueda.put("pagina", pagina);
            parametrosBusqueda.put("paginacion", nroRegistros);
            Paginacion paginas = new Paginacion();
            int cantidadRegistrosBusqueda;
            cantidadRegistrosBusqueda = obtenerCantidadBusquedaSuministro(suministroBusqueda);
            listadoSuministros = obtenerSuministros(parametrosBusqueda);
            paginas.setNumeroTotalRegistros(cantidadRegistrosBusqueda);
            resultado.setPaginacion(paginas);
            resultado.setListaSuministros(listadoSuministros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return resultado;
    }

    /**
     * Método que permite consultar los suministros.
     * @param parametrosBusqueda mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaSuministros lista registros con los datos del suministro, tipo List<SuministroMonto>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<SuministroMonto> obtenerSuministros(Map<String, Object> parametrosBusqueda) throws Exception {
        List<SuministroMonto> listaSuministros;
        try {
            listaSuministros = ejbSuministro.consultarSuministros(parametrosBusqueda);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaSuministros;
    }

    /**
     * Método que permite consultar los suministros con sus montos.
     * @param idSistema identificador del sistema, tipo Integer.
     * @param suministroBusqueda objeto con los datos del suministro con sus montos, tipo SuministroMonto.
     * @param pagina pagina actual de consulta, tipo int.
     * @param nroRegistros cantidad de registros a recuperar, tipo int.
     * @return resultado objeto con los datos de los suministros, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaMantenimiento consultarSuministrosMontos(Integer idSistema, SuministroMonto suministroBusqueda, int pagina, int nroRegistros) throws Exception {
        Map<String, Object> parametrosConsulta = new HashMap<>();
        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();

        try {
            parametrosConsulta.put("idInmueble", suministroBusqueda.getIdInmueble());
            parametrosConsulta.put("codigoTipoServicio", suministroBusqueda.getCodigoTipoServicio());
            parametrosConsulta.put("numeroSuministro", suministroBusqueda.getNumeroSuministro());
            parametrosConsulta.put("codigoEmpresaServicioPublico", suministroBusqueda.getCodigoEmpresaServicioPublico());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            int cantidadRegistrosBusquedaSuministro = obtenerCantidadSuministroMonto(suministroBusqueda);
            List<SuministroMonto> listarSuministrosMontos = consultarSuministrosMontos(parametrosConsulta);

            List<ParametroDetalle> lst = servicioSparmag.consultarParametroSistema(String.valueOf(idSistema), "TIPO_MONEDA", "");
            List<SuministroMonto> listaSuministroMonto = new ArrayList<>();
            for (SuministroMonto monstoSuministro : listarSuministrosMontos) {
                monstoSuministro.setMesSuministro(UConvierteFecha.obtenerDescripcionMesMayuscula(monstoSuministro.getMesSuministro()));
                for (ParametroDetalle parametroDetalle : lst) {
                    if (monstoSuministro.getCodigoMoneda().equals(parametroDetalle.getTxtValor())) {
                        monstoSuministro.setCodigoMoneda(parametroDetalle.getTxtDescripcion());
                        listaSuministroMonto.add(monstoSuministro);
                    }
                }
            }

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaSuministro);

            resultado.setPaginacion(paginacion);
            resultado.setListaSuministrosMontos(listaSuministroMonto);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

        return resultado;
    }

    /**
     * Método que permite guardar el suministro.
     * @param registroSuministro datos del suministro a guardar, tipo SuministroMonto.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void guardarSuministro(SuministroMonto registroSuministro) throws Exception {
        try {
            ejbSuministro.guardarSuministro(registroSuministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de registros de suministros.
     * @param suministro bean con los datos de búsqueda de los suministros, tipo SuministroMonto.
     * @return cantidad número de registros de suministros, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadBusquedaSuministro(SuministroMonto suministro) throws Exception {
        int cantidad;
        try {
            cantidad = ejbSuministro.obtenerCantidadBusquedaSuministro(suministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite obtener la cantidad de registros de suministros con sus montos.
     * @param suministro bean con los datos de búsqueda de los suministros, tipo SuministroMonto.
     * @return cantidad número de registros de suministros, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadSuministroMonto(SuministroMonto suministro) throws Exception {
        int cantidad;
        try {
            cantidad = ejbSuministro.obtenerCantidadSuministroMonto(suministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite consultar los suministros con sus montos.
     * @param parametrosConsulta mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaSuministros lista registros con los datos del suministro, tipo List<SuministroMonto>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<SuministroMonto> consultarSuministrosMontos(Map<String, Object> parametrosConsulta) throws Exception {
        List<SuministroMonto> listaSuministros;
        try {
            listaSuministros = ejbSuministro.consultarSuministrosMonto(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaSuministros;
    }

    /**
     * Método que permite modificar los datos del suministro.
     * @param modificarSuministro datos del suministro a modificar, tipo SuministroMonto.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarSuministro(SuministroMonto modificarSuministro) throws Exception {
        try {
            ejbSuministro.modificarSuministro(modificarSuministro);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite eliminar el suministro.
     * @param eliminarSuministro datos del suministro a eliminar, tipo SuministroMonto.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarSuministro(SuministroMonto eliminarSuministro) throws Exception {
        try {
            ejbSuministro.eliminarSuministro(eliminarSuministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite verificar el suministro existente.
     * @param suministro bean con los datos de búsqueda de los suministros, tipo SuministroMonto.
     * @return cantidad número de registros del suministro del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int verificarSuministroExistente(SuministroMonto suministro) throws Exception {
        int cantidad;
        try {
            cantidad = ejbSuministro.verificarSuministroExistente(suministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite consultar los suministros por id.
     * @param idSuministro identificador del suministro, tipo String.
     * @return suministro objeto con los datos del suministro, tipo SuministroMonto.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public SuministroMonto consultarSuministroPorId(String idSuministro) throws Exception {
        SuministroMonto suministro;
        try {
            suministro = ejbSuministro.consultarSuministroPorId(idSuministro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return suministro;
    }


}
