/**
 * Resumen.
 * Objeto               :   ICartaReparacionServicio.java.
 * Descripción          :   Interface utilizado para la creación de los métodos para la obtencion de carta reparacion.
 * Fecha de Creación    :   13/08/2021
 * PE de Creación       :   INI-900
 * Autor                :   Mario Salinas.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services;

import java.util.List;
import java.util.Map;

import pe.gob.onp.nsai.dto.CartaAviso;
import pe.gob.onp.nsai.dto.CartaReparacion;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;

/**
 * Interface que define métodos para la obtencion de carta reparacion
 */
public interface CartaReparacionService {

    public void guardarCartaReparacion(CartaReparacion cartaReparacion) ;

    public Integer obtenerCorrelativoCartaReparacion() ;




    public ResultadoBusquedaMantenimiento consultarCartaAviso(
            Map<String,Object> parametros
    ) ;

    public List<CartaAviso> obtenerListaCartaAviso(
            Map<String,Object> parametros
    ) ;


    public int obtenerCantidadCartaAviso(
            Map<String,Object> parametros
    ) ;
}
