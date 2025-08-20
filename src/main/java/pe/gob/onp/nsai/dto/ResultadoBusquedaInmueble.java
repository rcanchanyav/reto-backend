/**
 * Resumen.
 * Objeto               :   ResultadoBusquedaInmueble.java.
 * Descripción          :   DTO utilizado para almacenar los resultados de la búsqueda.
 * Fecha de Creación    :   05/04/2018
 * PE de Creación       :   4181
 * Autor                :   Pedro Peña
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0059                   22/05/2018       Pedro Aguilar      Se agregó la listas para generar el reprote histórico de inmueble, bloques y predios.
 * PE2018-0060                   07/06/2018       Pedro Peña     Se agregó la lista de tarifas del inmueble.
 * PE2018-0117					 04/09/2018		  Pedro Aguilar		 Se agregó las listas para los servicios de inmueble, bloque y predios.
 */
package pe.gob.onp.nsai.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Clase utilizada para almacenar los resultados de la búsqueda.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoBusquedaInmueble implements Serializable{

    /**
     * Número de serie generado
     */
    private static final long serialVersionUID = -8065052212627980347L;

    /** Lista de los inmuebles */
    private List<Inmueble> listaInmueble;

    /** Lista de los inmuebles */
    private Page<Inmueble> pageInmueble;

    /** Lista de los inmuebles */
    private List<InmuebleGasto> listaInmuebleGasto;

    /** Lista de los inmuebles */
    private List<InmuebleDocumento> listaInmuebleDocumentos;

    /** Lista de los Suministros de Inmuebles */
    private List<InmuebleSuministro> listaInmuebleSuministro;

    /** Lista de los Suministros de Inmuebles */
    private List<InmueblePredio> listaInmueblePredio;

    /** Lista de las Tasaciones de Inmuebles */
    private List<InmuebleTasacion> listaInmueblesTasacion;

    /** Lista de los bloques del Inmuebles */
    private List<InmuebleBloque> listaBloqueInmuebles;

    /** Lista de inmuebles de tasacion de predio */
    private List<InmuebleTasacionPredio> listaInmuebleTasacionPredio;

    /** Lista de los Suministros de Predio */
    private List<PredioSuministro> listaSuministroPredio;

    /** Lista de los Suministros de bloque */
    private List<SuministroBloque> listaSuministroBloque;

    /** Lista de los ambientes */
    private List<Ambiente> listaAmbiente;

    /** Lista de la infraestructura */
    private List<Infraestructura> listaInfraestructura;

    /** Lista de Parámetros */
    private List<Parametros> listaParametro;

    /** Lista del detalle de Parámetros */
    private List<Parametros> listaParametroDetalle;

    /** Lista del detalle para generar el reporte histórico con los datos del inmueble. */
    private List<ReporteHistoricoInmueble> listaReporteHistoricoInmueble;
    private List<ReporteHistoricoNsaiInmueble> listaReporteHistoricoNsaiInmueble;

    /** Lista del detalle para generar el reporte histórico con los datos del bloque. */
    private List<ReporteHistoricoBloque> listaReporteHistoricoBloque;
    private List<ReporteHistoricoNsaiBloque> listaReporteHistoricoNsaiBloque;

    /** Lista del detalle para generar el reporte histórico con los datos del predio. */
    private List<ReporteHistoricoPredio> listaReporteHistoricoPredio;

    /** Listado con las imágenes del ambiente. */
    private List<AmbienteImagen> listaImagenAmbiente;

    /** Listado con las tarifas del inmueble. */
    private List<Tarifario> listaTarifasInmueble;

    /** Listado con los servicios del inmueble. */
    private List<InmuebleServicio> listaInmuebleServicio;

    /** Listado con los servicios de bloques del inmueble. */
    private List<InmuebleBloqueServicio> listaInmuebleBloqueServicio;

    /** Listado con los servicios de predios del inmueble. */
    private List<InmueblePredioServicio> listaInmueblePredioServicio;

    /** Listado con los servicios de predios del inmueble. */
    private List<SeparacionPredio> listaSeparacionPredio;

    /** Objeto para la almacenar la paginación en general */
    private Paginacion paginacion;

    /** Objeto para la almacenar la paginación de bloques */
    private Paginacion paginacionBloque;

    /** Objeto para la almacenar la paginación de predios */
    private Paginacion paginacionPredio;




}
