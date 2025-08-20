/**
 * Resumen.
 * Objeto               :   CartaReparacionDelegateEJB.java.
 * Descripción          :   Clase utilizada para la creación de los métodos para el cartareparacion.
 * Fecha de Creación    :   03/08/2021
 * PE de Creación       :   INI-900
 * Autor                :   Mario Salinas
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.CartaReparacionLocalDao;
import pe.gob.onp.nsai.dto.CartaAviso;
import pe.gob.onp.nsai.dto.CartaReparacion;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.CartaReparacionService;

import java.util.List;
import java.util.Map;

/**
 * Clase utilizada para la creación de los métodos para el carta reparacion.
 */
@Service
public class CartaReparacionServiceImpl implements CartaReparacionService {

    /**
     * Instancia a la clase EJB ESPersonalLocal.
     */

    private final CartaReparacionLocalDao ejbCartaReparacion;

    public CartaReparacionServiceImpl(CartaReparacionLocalDao ejbCartaReparacion) {
        this.ejbCartaReparacion = ejbCartaReparacion;
    }

    @Override
    public void guardarCartaReparacion(CartaReparacion cartaReparacion) throws Exception {
        try {
            ejbCartaReparacion.guardarCartaReparacion(cartaReparacion);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public Integer obtenerCorrelativoCartaReparacion() throws Exception {
        // TODO Auto-generated method stub
        return ejbCartaReparacion.obtenerCorrelativoCartaReparacion();
    }


    @Override
    public ResultadoBusquedaMantenimiento consultarCartaAviso(Map<String, Object> parametros) throws Exception {
        try {
            int cantidad = obtenerCantidadCartaAviso(parametros);
            List<CartaAviso> lista = obtenerListaCartaAviso(parametros);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidad);
            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaCartaAviso(lista);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public List<CartaAviso> obtenerListaCartaAviso(Map<String, Object> parametros) throws Exception {
        // TODO Auto-generated method stub
        try {
            return ejbCartaReparacion.obtenerListaCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public int obtenerCantidadCartaAviso(Map<String, Object> parametros) throws Exception {
        try {
            return ejbCartaReparacion.obtenerCantidadCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }
}
