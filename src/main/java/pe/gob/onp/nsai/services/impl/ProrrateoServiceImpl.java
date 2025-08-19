/**
 * Resumen.
 * Objeto               :   ProrrateoDelegateEJB.java.
 * Descripción          :   Clase utilizada para la creación de los métodos para el prorrateo de inmuebles.
 * Fecha de Creación    :   27/08/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Christian Wong
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dao.*;
import pe.gob.onp.nsai.dto.Auditoria;
import pe.gob.onp.nsai.dto.BloqueServicioProrrateo;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.Inmueble;
import pe.gob.onp.nsai.dto.InmuebleBloque;
import pe.gob.onp.nsai.dto.InmueblePredio;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.PeriodoProrrateoInmueble;
import pe.gob.onp.nsai.dto.PredioProrrateo;
import pe.gob.onp.nsai.dto.PredioServicioProrrateo;
import pe.gob.onp.nsai.dto.ProrrateoPredioDetalle;
import pe.gob.onp.nsai.dto.ProrrateoPredioGeneral;
import pe.gob.onp.nsai.dto.ReporteOperacionInmueble;
import pe.gob.onp.nsai.dto.ReporteProrrateoInmuebleBloquePredio;
import pe.gob.onp.nsai.dto.ReporteProrrateoInmuebleBloquePredioDeta;
import pe.gob.onp.nsai.dto.ReporteVerificacionProrrateoPredio;
import pe.gob.onp.nsai.dto.ReporteVerificacionProrrateoBloque;
import pe.gob.onp.nsai.dto.ReporteVerificacionProrrateoInmueble;
import pe.gob.onp.nsai.dto.ResultadoBusquedaInmueble;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaProrrateo;
import pe.gob.onp.nsai.dto.Servicio;
import pe.gob.onp.nsai.dto.ServicioProrrateoInmueble;
import pe.gob.onp.nsai.services.CategoriaParametroService;
import pe.gob.onp.nsai.services.ProrrateoService;
import pe.gob.onp.nsai.services.ServicioService;
import pe.gob.onp.nsai.util.GeneradorID;
import pe.gob.onp.nsai.util.UConvierteFecha;

/**
 * Clase que implementa el prorrateo de inmuebles.
 */
@Service
public class ProrrateoServiceImpl implements ProrrateoService {

    /** Objeto EJB con los métodos del prorrateo de inmuebles */

    private final ProrrateoLocalDao ejbProrrateo;

    /** Objeto EJB con los métodos del mantenimiento de bloques de inmuebles */

    private final InmuebleBloqueLocalDao ejbInmuebleBloque;

    /** Objeto EJB con los métodos del mantenimiento del prorrateo de bloques */

    private final BloqueProrrateoLocalDao ejbBloqueProrrateo;

    /** Objeto EJB con los métodos del mantenimiento de predios de inmuebles */

    private final InmueblePredioLocalDao ejbInmueblePredio;

    /** Objeto EJB con los métodos del prorrateo de predios */

    private final PredioProrrateoLocalDao ejbPredioProrrateo;

    /** Objeto EJB con los métodos de inmueble **/

    private final InmuebleLocalDao ejbInmueble;


    private final CategoriaParametroService categoriaParametroServicio;

    /** Objeto inyectado con los métodos para el servicio */
    private final ServicioService servicioServicio;

    private final ServicioREST servicioREST;

    public ProrrateoServiceImpl(ProrrateoLocalDao ejbProrrateo, InmuebleBloqueLocalDao ejbInmuebleBloque, BloqueProrrateoLocalDao ejbBloqueProrrateo, InmueblePredioLocalDao ejbInmueblePredio, PredioProrrateoLocalDao ejbPredioProrrateo, InmuebleLocalDao ejbInmueble, CategoriaParametroService categoriaParametroServicio, ServicioService servicioServicio, ServicioREST servicioREST) {
        this.ejbProrrateo = ejbProrrateo;
        this.ejbInmuebleBloque = ejbInmuebleBloque;
        this.ejbBloqueProrrateo = ejbBloqueProrrateo;
        this.ejbInmueblePredio = ejbInmueblePredio;
        this.ejbPredioProrrateo = ejbPredioProrrateo;
        this.ejbInmueble = ejbInmueble;
        this.categoriaParametroServicio = categoriaParametroServicio;
        this.servicioServicio = servicioServicio;
        this.servicioREST = servicioREST;
    }

    /**
     * Instancia a ResourceBundle
     */
    //protected ResourceBundle mensajes=ResourceBundle.getBundle("pe.gob.onp.nsai.util.mensajes.mensajes");

    /**
     * Método que permite consultar la cantidad de inmuebles según parametros de busqueda
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>.
     * @return cantidad cantidad de parametros obtenidos, tipo Integer.
     * @throws Exception, excepción generada en caso de error.
     */
    @Override
    public Integer obtenerCantidadInmuebleParaProrrateo(Map<String, Object> parametros) throws Exception {
        int cantidad;
        try {
            cantidad = ejbProrrateo.obtenerCantidadInmuebleParaProrrateo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que obtiene la lista de inmuebles según parametros de busqueda
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>
     * @return listaInmuebles lista de inmuebles obtenida, tipo List<Inmueble>
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<Inmueble> consultarInmueblesParaProrrateo(Map<String, Object> parametros) throws Exception {
        List<Inmueble> listaInmuebles;
        try {
            listaInmuebles = ejbProrrateo.consultarInmueblesParaProrrateo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaInmuebles;
    }

    /**
     * Método que permite consultar la lista de inmuebles para el prorrateo
     * @param inmuebleBusqueda datos del inmueble a buscar, tipo Inmueble
     * @param pagina página a buscar, tipo int
     * @param nroRegistros número de registros a buscar, tipo int
     * @return resultado datos del inmueble consultado, tipo ResultadoBusquedaInmueble
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaInmueble consultarInmueblesProrrateo(Inmueble inmuebleBusqueda, int pagina,
                                                                 int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idInmueble", inmuebleBusqueda.getIdInmueble());
            parametrosConsulta.put("descripcionInmueble", inmuebleBusqueda.getDescripcionInmueble());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            int cantidadRegistrosBusquedaInmueble = obtenerCantidadInmuebleParaProrrateo(parametrosConsulta);
            List<Inmueble> listaInmuebles = consultarInmueblesParaProrrateo(parametrosConsulta);

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaInmueble);

            ResultadoBusquedaInmueble resultado = new ResultadoBusquedaInmueble();
            resultado.setPaginacion(paginacion);
            resultado.setListaInmueble(listaInmuebles);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar los servicios de los inmuebles en un determinado periodo.
     * @param periodoProrrateo periodo a consultar para su prorrateo, tipo PeriodoProrrateoInmueble.
     * @param pagina página a buscar, tipo int.
     * @param nroRegistros cantidad de registros, tipo int.
     * @return resultado datos de los servicios de los inmuebles consultados, tipo ResultadoBusquedaProrrateo.
     * @throws Exception, excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaProrrateo consultarServiciosPeriodoInmueble(PeriodoProrrateoInmueble periodoProrrateo,
                                                                        int pagina, int nroRegistros) throws Exception {
        try {
            if(periodoProrrateo.getEstadoProrrateo() != null && periodoProrrateo.getEstadoProrrateo().equals("3")){
                Map<String,Object> parametrosConsulta = new HashMap<>();
                parametrosConsulta.put("idInmueble", periodoProrrateo.getIdInmueble());
                parametrosConsulta.put("mes", periodoProrrateo.getMesProrrateo());
                parametrosConsulta.put("anio", periodoProrrateo.getAnioProrrateo());
                parametrosConsulta.put("pagina", pagina);
                parametrosConsulta.put("paginacion", nroRegistros);

                int cantidadRegistrosBusquedaServicios = consultarCantidadServiciosProrrateoDefinitivo(parametrosConsulta);
                List<ServicioProrrateoInmueble> listaServicios = consultarServiciosProrrateoDefinitivo(parametrosConsulta);

                Paginacion paginacion = new Paginacion();
                paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaServicios);


                ResultadoBusquedaProrrateo resultado = new ResultadoBusquedaProrrateo();
                resultado.setPaginacion(paginacion);
                resultado.setListaServiciosProrrateo(listaServicios);

                return resultado;
            }else{

                Map<String,Object> parametrosConsulta = new HashMap<>();
                parametrosConsulta.put("idInmueble", periodoProrrateo.getIdInmueble());
                parametrosConsulta.put("mes", periodoProrrateo.getMesProrrateo());
                parametrosConsulta.put("anio", periodoProrrateo.getAnioProrrateo());
                parametrosConsulta.put("pagina", pagina);
                parametrosConsulta.put("paginacion", nroRegistros);
                ResultSet rs = null;
                parametrosConsulta.put("C_LISTA_SERVICIOS", rs);

                List<ServicioProrrateoInmueble> listaServicios = consultarServiciosParaProrrateo(parametrosConsulta);

                parametrosConsulta.put("pagina", 0);
                parametrosConsulta.put("paginacion", 0);
                List<ServicioProrrateoInmueble> listaServiciosCantidad = consultarServiciosParaProrrateo(parametrosConsulta);

                int cantidadRegistrosBusquedaServicios = listaServiciosCantidad.size();

                Paginacion paginacion = new Paginacion();
                paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaServicios);


                ResultadoBusquedaProrrateo resultado = new ResultadoBusquedaProrrateo();
                resultado.setPaginacion(paginacion);
                resultado.setListaServiciosProrrateo(listaServicios);

                return resultado;
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de servicios para su prorrateo.
     * @param parametrosConsulta mapa de parametros de busqueda, tipo Map<String,Object>.
     * @return cantidad cantidad de servicios para su prorrateo, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadServiciosParaProrrateo(Map<String,Object> parametrosConsulta) throws Exception{
        int cantidad;
        try {
            cantidad = ejbProrrateo.obtenerCantidadServiciosParaProrrateo(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }



    /**
     * Método que permite consultar los servicios para su prorrateo.
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>.
     * @return listaServicios lista de servicios obtenida para su prorrateo, tipo List<ServicioProrrateoInmueble>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ServicioProrrateoInmueble> consultarServiciosParaProrrateo(Map<String, Object> parametros)
            throws Exception {
        List<ServicioProrrateoInmueble> listaServicios;
        try {
            listaServicios = ejbProrrateo.consultarServiciosParaProrrateo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaServicios;
    }

    /**
     * Método que permite registrar los servicios del prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Long registrarServiciosProrrateo(ServicioProrrateoInmueble prorrateoInmueble,List<BloqueServicioProrrateo> listaBloqueProrrateo, List<PredioServicioProrrateo> listaPredioProrrateo) throws Exception {
        try{
            if(!listaBloqueProrrateo.isEmpty()){
                return registrarServicioProrrateoBloque(prorrateoInmueble,listaBloqueProrrateo);
            }else{
                return registrarServicioProrrateoPredio(prorrateoInmueble,listaPredioProrrateo);
            }
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite registrar el prorrateo de los servicios del bloque.
     * @param prorrateoInmueble datos del prorrateo del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Long registrarServicioProrrateoBloque(ServicioProrrateoInmueble prorrateoInmueble,List<BloqueServicioProrrateo> listaBloqueProrrateo) throws Exception {
        try{
            PeriodoProrrateoInmueble periodoInmueble = prorrateoInmueble.getPeriodoInmueble();
            if(periodoInmueble != null){
                return ejbProrrateo.registrarServicioProrrateoBloque(periodoInmueble, prorrateoInmueble, listaBloqueProrrateo);
            }else{
                return null;
            }
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite registrar el prorrateo de los servicios del predio.
     * @param prorrateoInmueble datos del prorrateo del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @return identificador del servicio para el prorrateo, tipo Long.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Long registrarServicioProrrateoPredio(ServicioProrrateoInmueble prorrateoInmueble,
                                                 List<PredioServicioProrrateo> listaPredioProrrateo) throws Exception {
        try{
            PeriodoProrrateoInmueble periodoInmueble = prorrateoInmueble.getPeriodoInmueble();
            if(periodoInmueble != null){
                return ejbProrrateo.registrarServicioProrrateoPredio(periodoInmueble, prorrateoInmueble,listaPredioProrrateo);
            }else{
                return null;
            }
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener los servicios para el prorrateo.
     * @param idServicioProrrateoInmueble identificador de la tabla de servicios de prorrateo, tipo Long.
     * @return datos del servicio para el prorrateo, tipo ServicioProrrateoInmueble.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ServicioProrrateoInmueble obtenerServiciosDeProrrateo(Long idServicioProrrateoInmueble) throws Exception {
        try{
            return ejbProrrateo.obtenerServiciosDeProrrateo(idServicioProrrateoInmueble);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar los servicios para el prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarServiciosProrrateo(ServicioProrrateoInmueble prorrateoInmueble,
                                            List<BloqueServicioProrrateo> listaBloqueProrrateo, List<PredioServicioProrrateo> listaPredioProrrateo)
            throws Exception {
        try{
            if(!listaBloqueProrrateo.isEmpty()){
                modificarServiciosProrrateoBloque(prorrateoInmueble,listaBloqueProrrateo);
            }else{
                modificarServiciosProrrateoPredio(prorrateoInmueble,listaPredioProrrateo);
            }
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite modificar los servicios de los bloques para el prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaBloqueProrrateo lista de bloques para el prorrateo, tipo List<BloqueServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarServiciosProrrateoBloque(ServicioProrrateoInmueble prorrateoInmueble,
                                                  List<BloqueServicioProrrateo> listaBloqueProrrateo) throws Exception {
        try{
            ejbProrrateo.modificarServiciosProrrateoBloque(prorrateoInmueble, listaBloqueProrrateo);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite modificar los servicios del predio para su prorrateo.
     * @param prorrateoInmueble datos del servicio del inmueble, tipo ServicioProrrateoInmueble.
     * @param listaPredioProrrateo lista de predios para el prorrateo, tipo List<PredioServicioProrrateo>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarServiciosProrrateoPredio(ServicioProrrateoInmueble prorrateoInmueble,
                                                  List<PredioServicioProrrateo> listaPredioProrrateo) throws Exception {
        try{
            ejbProrrateo.modificarServiciosProrrateoPredio(prorrateoInmueble, listaPredioProrrateo);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite validar si la configuracion del prorrateo del periodo es correcta
     * @param mes mes del prorrateo, tipo String.
     * @param anio año del prorrateo, tipo String,
     * @param idInmueble identificador del inmueble, tipo String.
     * @return errores errores para su validacion, tipo ErrorValidacion.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ErrorValidacion validarConfiguracionProrrateo(String mes, String anio, String idInmueble) throws Exception {
        try{
            ErrorValidacion errores = new ErrorValidacion();
            boolean valido = true;
            Map<String,Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idInmueble", idInmueble);
            parametrosConsulta.put("mes", mes);
            parametrosConsulta.put("anio", anio);
            parametrosConsulta.put("pagina", 0);
            parametrosConsulta.put("paginacion", 0);
            ResultSet rs = null;
            parametrosConsulta.put("C_LISTA_SERVICIOS", rs);
            List<ServicioProrrateoInmueble>  listaServiciosProrrateo = consultarServiciosParaProrrateo(parametrosConsulta);
            int cantidadBloques = 0;

            /* VALIDA SERVICIOS */
            for(ServicioProrrateoInmueble servicioProrrateo: listaServiciosProrrateo){
                if(servicioProrrateo.getIdServicioProrrateoInmueble() == null){
                    valido = false;
                    if(servicioProrrateo.getIdServicio() != null){
                        errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.servicios"), servicioProrrateo.getDescripcionServicio()));
                    }else{
                        errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.suministros"), servicioProrrateo.getDescripcionServicio()));
                    }

                    break;
                }
            }

            /* VALIDA BLOQUES */
            if(valido){
                InmuebleBloque inmuebleBloque = new InmuebleBloque();
                inmuebleBloque.setIdInmueble(idInmueble);
                cantidadBloques = ejbInmuebleBloque.obtenerCantidadBloquesVigentes(inmuebleBloque);
                if(cantidadBloques>0){
                    for(ServicioProrrateoInmueble servicioProrrateo: listaServiciosProrrateo){
                        if(servicioProrrateo.getIdServicio() != null){
                            Map<String,Object> parametrosBloque = new HashMap<>();
                            parametrosBloque.put("idServicioProrrateoInmueble", servicioProrrateo.getIdServicioProrrateoInmueble());
                            parametrosBloque.put("idServicio", servicioProrrateo.getIdServicio());
                            parametrosBloque.put("idInmueble", idInmueble);
                            parametrosBloque.put("pagina", 0);
                            parametrosBloque.put("paginacion", 0);
                            List<BloqueServicioProrrateo> listaBloqueProrrateo = ejbBloqueProrrateo.consultarBloquesProrrateo(parametrosBloque);
                            for(BloqueServicioProrrateo bloqueProrrateo:listaBloqueProrrateo){
                                if(bloqueProrrateo.getIdBloqueServicioProrrateo() == null){
                                    valido = false;
                                    errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.bloque.servicio"), bloqueProrrateo.getDescripcionBloque(), servicioProrrateo.getDescripcionServicio()));
                                    break;
                                }
                            }
                        }else if(servicioProrrateo.getIdSuministroInmueble() != null){
                            Map<String,Object> parametrosBloque = new HashMap<>();
                            parametrosBloque.put("idServicioProrrateoInmueble", servicioProrrateo.getIdServicioProrrateoInmueble());
                            parametrosBloque.put("idSuministroInmueble", servicioProrrateo.getIdSuministroInmueble());
                            parametrosBloque.put("idInmueble", idInmueble);
                            parametrosBloque.put("pagina", 0);
                            parametrosBloque.put("paginacion", 0);

                            List<BloqueServicioProrrateo> listaBloqueProrrateo = ejbBloqueProrrateo.consultarBloquesSuministroInmueble(parametrosBloque);
                            for(BloqueServicioProrrateo bloqueProrrateo:listaBloqueProrrateo){
                                if(bloqueProrrateo.getIdBloqueServicioProrrateo() == null){
                                    valido = false;
                                    errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.bloque.suministro"), bloqueProrrateo.getDescripcionBloque(), servicioProrrateo.getDescripcionServicio()));
                                    break;
                                }
                            }

                        }else if(servicioProrrateo.getIdSuministroBloque() != null){
                            Map<String,Object> parametrosBloque = new HashMap<>();
                            parametrosBloque.put("idServicioProrrateoInmueble", servicioProrrateo.getIdServicioProrrateoInmueble());
                            parametrosBloque.put("idSuministroBloque", servicioProrrateo.getIdSuministroBloque());
                            parametrosBloque.put("idInmueble", idInmueble);
                            parametrosBloque.put("pagina", 0);
                            parametrosBloque.put("paginacion", 0);

                            List<BloqueServicioProrrateo> listaBloqueProrrateo = ejbBloqueProrrateo.obtenerBloquesDeSuministroBloque(parametrosBloque);
                            for(BloqueServicioProrrateo bloqueProrrateo:listaBloqueProrrateo){
                                if(bloqueProrrateo.getIdBloqueServicioProrrateo() == null){
                                    valido = false;
                                    errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.bloque.suministro"), bloqueProrrateo.getDescripcionBloque(), servicioProrrateo.getDescripcionServicio()));
                                    break;
                                }
                            }
                        }

                        if(!valido){
                            break;
                        }
                    }
                }


            }
            /* VALIDA PREDIOS */
            if(valido){
                if(cantidadBloques>0){
                    InmuebleBloque busquedaBloque = new InmuebleBloque();
                    busquedaBloque.setIdInmueble(idInmueble);
                    List<InmuebleBloque> listaBloqueVigentes = ejbInmuebleBloque.obtenerListaBloquesVigentes(busquedaBloque);
                    for(InmuebleBloque bloque:listaBloqueVigentes){
                        InmueblePredio busquedaPredio = new InmueblePredio();
                        busquedaPredio.setIdInmueble(bloque.getIdInmueble());
                        busquedaPredio.setIdInmuebleBloque(Long.valueOf(bloque.getIdInmuebleBloque()).intValue());
                        int cantidadPredios = ejbInmueblePredio.obtenerCantidadPrediosBloqueVigentes(busquedaPredio);

                        if(cantidadPredios == 0){
                            valido = false;
                            errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.predios.bloque"), bloque.getDescripcionBloque()));
                        }
                    }
                }else{
                    InmueblePredio busquedaPredio = new InmueblePredio();
                    busquedaPredio.setIdInmueble(idInmueble);
                    int cantidadPredios = ejbInmueblePredio.obtenerCantidadPrediosVigentes(busquedaPredio);
                    if(cantidadPredios == 0){
                        valido = false;
                        errores.agregarError(servicioREST.getMessage("generar.prorrateo.valida.predios.inmueble"));
                    }
                }
            }

            /* VALIDA TIPO DE PRORRATEO */
            if(valido){

                for(ServicioProrrateoInmueble servicioProrrateo: listaServiciosProrrateo){
                    ServicioProrrateoInmueble servicioProrrateoObtenido = ejbProrrateo.obtenerServiciosDeProrrateo(servicioProrrateo.getIdServicioProrrateoInmueble());
                    if((servicioProrrateoObtenido.getTipoProrrateo() != null && !servicioProrrateoObtenido.getTipoProrrateo().isEmpty())|| servicioProrrateoObtenido.getIdSuministroBloque() != null){

                        if(cantidadBloques > 0){
                            List<BloqueServicioProrrateo> listaBloquesProrrateo = ejbBloqueProrrateo.obtenerListaBloqueServicioProrrateo(servicioProrrateoObtenido.getIdServicioProrrateoInmueble());
                            for(BloqueServicioProrrateo bloqueProrrateo: listaBloquesProrrateo){
                                if(bloqueProrrateo.getTipoProrrateo() == null || bloqueProrrateo.getTipoProrrateo().isEmpty()){
                                    valido = false;
                                    if(servicioProrrateoObtenido.getIdServicio() != null){
                                        errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.servicio.bloque.tipo.prorrateo"), bloqueProrrateo.getDescripcionBloque(), servicioProrrateoObtenido.getDescripcionServicio()));
                                    }else{
                                        errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.suministro.bloque.tipo.prorrateo"), bloqueProrrateo.getDescripcionBloque(), servicioProrrateoObtenido.getDescripcionServicio()));
                                    }
                                    break;
                                }

                            }
                        }
                    }else{
                        valido = false;
                        errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.servicio.tipo.prorrateo"), servicioProrrateoObtenido.getDescripcionServicio()));
                        break;

                    }
                }

            }

            /* VALIDA PROCENTAJE DE INCIDENCIA */
            if(valido){
                if(cantidadBloques>0){
                    for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){
                        ServicioProrrateoInmueble servicioProrrateoObtenido = ejbProrrateo.obtenerServiciosDeProrrateo(servicioProrrateo.getIdServicioProrrateoInmueble());
                        if(servicioProrrateoObtenido.getTipoProrrateo() != null && servicioProrrateoObtenido.getTipoProrrateo().equals("03")){
                            BigDecimal sumaPorcentaje = new BigDecimal("0");
                            BigDecimal totalPorcentaje = new BigDecimal("100");
                            List<BloqueServicioProrrateo> listaBloquesProrrateo = ejbBloqueProrrateo.obtenerListaBloqueServicioProrrateo(servicioProrrateoObtenido.getIdServicioProrrateoInmueble());
                            for(BloqueServicioProrrateo bloqueProrrateo: listaBloquesProrrateo){
                                if(bloqueProrrateo.getPorcentajeIncidencia() != null){
                                    sumaPorcentaje = sumaPorcentaje.add(bloqueProrrateo.getPorcentajeIncidencia());
                                }
                            }

                            if(sumaPorcentaje.compareTo(totalPorcentaje) != 0){
                                valido = false;
                                errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.porcentaje.incidencia"), servicioProrrateoObtenido.getDescripcionServicio()));

                            }
                        }

                    }
                }
            }

            /* VALIDA PORCENTAJES DE AREA COMUN */
            if(valido){
                ServicioProrrateoInmueble servicioPorcAreaComun = null;
                for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){
                    if(cantidadBloques>0){
                        ServicioProrrateoInmueble servicioProrrateoObtenido = ejbProrrateo.obtenerServiciosDeProrrateo(servicioProrrateo.getIdServicioProrrateoInmueble());
                        if(servicioProrrateoObtenido.getTipoProrrateo()!=null && servicioProrrateoObtenido.getTipoProrrateo().equals("05")){
                            servicioPorcAreaComun = servicioProrrateoObtenido;
                            break;
                        }else{
                            List<BloqueServicioProrrateo> listaBloquesProrrateo = ejbBloqueProrrateo.obtenerListaBloqueServicioProrrateo(servicioProrrateoObtenido.getIdServicioProrrateoInmueble());
                            for(BloqueServicioProrrateo bloqueProrrateo: listaBloquesProrrateo){
                                if(bloqueProrrateo.getTipoProrrateo().equals("04")){
                                    servicioPorcAreaComun = servicioProrrateoObtenido;
                                    break;
                                }
                            }
                        }
                        if(servicioPorcAreaComun != null){
                            break;
                        }

                    }else{
                        ServicioProrrateoInmueble servicioProrrateoObtenido = ejbProrrateo.obtenerServiciosDeProrrateo(servicioProrrateo.getIdServicioProrrateoInmueble());
                        if(servicioProrrateoObtenido.getTipoProrrateo()!=null && servicioProrrateoObtenido.getTipoProrrateo().equals("04")){
                            servicioPorcAreaComun = servicioProrrateoObtenido;
                            break;
                        }
                    }
                }

                if(servicioPorcAreaComun != null){
                    Map<String,Object> parametros = new HashMap<>();
                    List<PredioProrrateo> listaConfiguracionPredio = null;
                    parametros.put("idServicioProrrateoInmueble", servicioPorcAreaComun.getIdServicioProrrateoInmueble());
                    if(cantidadBloques>0){
                        if(servicioPorcAreaComun.getIdServicio() != null){
                            listaConfiguracionPredio = ejbPredioProrrateo.obtenerConfiguracionServicioPredioConBloques(parametros);

                        }else if(servicioPorcAreaComun.getIdSuministroInmueble() != null){
                            listaConfiguracionPredio = ejbPredioProrrateo.obtenerConfiguracionSuministroPredioConBloques(parametros);
                        }else if(servicioPorcAreaComun.getIdSuministroBloque() != null){
                            listaConfiguracionPredio = ejbPredioProrrateo.obtenerConfiguracionSuministroBloquesPredio(parametros);
                        }
                    }else{
                        if(servicioPorcAreaComun.getIdServicio() != null){
                            listaConfiguracionPredio = ejbPredioProrrateo.obtenerConfiguracionServicioPredioSinBloques(parametros);
                        }else if(servicioPorcAreaComun.getIdSuministroInmueble() != null){
                            listaConfiguracionPredio = ejbPredioProrrateo.obtenerConfiguracionSuministroPredioSinBloques(parametros);
                        }
                    }

                    if(listaConfiguracionPredio != null){
                        BigDecimal sumaPorcentajeAreaComun = new BigDecimal("0");
                        BigDecimal totalPorcentajeAreaComun = new BigDecimal("100");
                        for(PredioProrrateo predioConfiguracion:listaConfiguracionPredio){
                            if(predioConfiguracion.getIndicadorProrrateoPredio().equals("1")){
                                if(predioConfiguracion.getPorcentajeAreaComun() != null){
                                    sumaPorcentajeAreaComun = sumaPorcentajeAreaComun.add(predioConfiguracion.getPorcentajeAreaComun());
                                }else{
                                    valido = false;
                                    errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.porcentaje.area.comun"), servicioPorcAreaComun.getDescripcionServicio()));
                                    break;
                                }
                            }

                        }
                        if(valido){
                            if(sumaPorcentajeAreaComun.compareTo(totalPorcentajeAreaComun) != 0){
                                valido = false;
                                errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.porcentaje.area.comun.suma"), servicioPorcAreaComun.getDescripcionServicio()));
                            }
                        }
                    }


                }
            }


            /* VALIDAR MONTOS */
            if(valido){
                BigDecimal montoCero = new BigDecimal("0");
                for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){

                    if(servicioProrrateo.getIdServicio()!=null){
                        Map<String,Object> parametros = new HashMap<>();
                        parametros.put("anio", anio);
                        parametros.put("mes", mes);
                        parametros.put("idInmueble", idInmueble);
                        parametros.put("idServicio",servicioProrrateo.getIdServicio());
                        List<ServicioProrrateoInmueble> listaMontosServicio = ejbProrrateo.obtenerMontosServicios(parametros);

                        for(ServicioProrrateoInmueble montoServicio:listaMontosServicio){
                            if(montoServicio.getMontoProrrateoServicio().compareTo(montoCero) == 0){
                                valido = false;
                                errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.monto.servicio"), montoServicio.getDescripcionServicio(), montoServicio.getDescripcionProveedor()));
                                break;
                            }
                        }

                        if(!valido){
                            break;
                        }
                    }else{
                        if(servicioProrrateo.getMontoProrrateoServicio().compareTo(montoCero) == 0){
                            valido = false;
                            errores.agregarError(MessageFormat.format(servicioREST.getMessage("generar.prorrateo.valida.monto.suministro"), servicioProrrateo.getDescripcionServicio()));
                            break;
                        }
                    }
                }
            }

            return errores;
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener los datos del periodo del prorrateo
     * @param periodoProrrateo datos del periodo del prorrateo a buscar, tipo PeriodoProrrateoInmueble.
     * @return datos del periodo del prorrateo, tipo PeriodoProrrateoInmueble.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PeriodoProrrateoInmueble obtenerPeriodoProrrateo(PeriodoProrrateoInmueble periodoProrrateo)
            throws Exception {
        try{
            return ejbProrrateo.obtenerPeriodoProrrateo(periodoProrrateo);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene la lista de inmuebles en un determinado periodo para su prorrateo.
     * @param idInmueble identificador del inmueble, tipo String.
     * @param mes mes del prorrateo, tipo String.
     * @param anio año del prorrateo, tipo String.
     * @param idServicio identificador del servicio, tipo String.
     * @return lista de inmueble en un determinado periodo para su prorrateo, tipo List<PeriodoProrrateoInmueble>
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PeriodoProrrateoInmueble> obtenerPeriodoProrrateoInmueble(String idInmueble,String mes,String anio,String idServicio)
            throws Exception {
        List<PeriodoProrrateoInmueble> listaInmuebles;
        try {
            Map<String, Object> periodoProrrateo = new HashMap<>();
            periodoProrrateo.put("idInmueble",idInmueble);
            periodoProrrateo.put("mes",mes);
            periodoProrrateo.put("anio",anio);
            int decodificarIdServicio = (int) GeneradorID.decodificar(idServicio);
            periodoProrrateo.put("idServicio",decodificarIdServicio);
            listaInmuebles = ejbProrrateo.obtenerPeriodoProrrateoInmueble(periodoProrrateo);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaInmuebles;
    }

    /**
     * Método que permite actualizar los servicios para prorrateo
     * @param listaServicios lista de servicios para prorrateo, tipo List<ServicioProrrateoInmueble>.
     * @param periodoInmueble datos del periodo del inmueble, tipo PeriodoProrrateoInmueble
     * @param auditoria datos de auditoria, tipo Auditoria.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void actualizarServiciosProrrateo(List<ServicioProrrateoInmueble> listaServicios,PeriodoProrrateoInmueble periodoInmueble,Auditoria auditoria) throws Exception {
        try{
            ejbProrrateo.actualizarServiciosProrrateo(listaServicios,periodoInmueble,auditoria);
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite generar el prorrateo simulado
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarProrrateoSimulado(PeriodoProrrateoInmueble periodoProrrateo) throws Exception {
        try{
            ejbProrrateo.generarProrrateoSimulado(periodoProrrateo);
            return generarExcelProrrateo(periodoProrrateo);
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite generar el prorrateo definitivo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarProrrateoDefinitivo(PeriodoProrrateoInmueble periodoProrrateo)
            throws Exception {
        try{
            ejbProrrateo.generarProrrateoDefinitivo(periodoProrrateo);
            return generarExcelProrrateo(periodoProrrateo);
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite ver el prorrateo definitivo generado
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream verProrrateoPeriodo(PeriodoProrrateoInmueble periodoProrrateo) throws Exception {
        try{
            return generarExcelProrrateo(periodoProrrateo);
        }catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    /**
     * Método que permite generar el excel para prorrateo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarExcelProrrateo(PeriodoProrrateoInmueble periodoProrrateo) throws Exception {
        try{

            XSSFWorkbook excel = new XSSFWorkbook();

            generarVentanaServiciosInmuebleProrrateo(excel,periodoProrrateo);
            generarExcel(excel,periodoProrrateo);

            ByteArrayOutputStream bytes=new ByteArrayOutputStream();
            excel.write(bytes);
            excel.close();
            return bytes;
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar los servicios de prorrateos definitivos
     * @param parametros parametros de busqueda, tipo Map<String,Object>.
     * @return lista de servicios del inmueble, tipo List<ServicioProrrateoInmueble>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ServicioProrrateoInmueble> consultarServiciosProrrateoDefinitivo(Map<String, Object> parametros)
            throws Exception {
        List<ServicioProrrateoInmueble> listaServicios;
        try {
            listaServicios = ejbProrrateo.consultarServiciosProrrateoDefinitivo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaServicios;
    }

    /**
     * Método que permite consultar la cantidad de servicios de prorrateo definitivos
     * @param parametros parametros de busqueda, tipo Map<String,Object>.
     * @return cantidad de servicios del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int consultarCantidadServiciosProrrateoDefinitivo(Map<String, Object> parametros) throws Exception {
        int cantidad;
        try {
            cantidad = ejbProrrateo.consultarCantidadServiciosProrrateoDefinitivo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite ver el prorrateo de los inmuebles en el periodo
     * @param periodoProrrateo datos del periodo de prorrateo, tipo PeriodoProrrateoInmueble.
     * @return arreglo de bytes con el archivo generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream verProrrateosPeriodo(PeriodoProrrateoInmueble periodoProrrateo) throws Exception {
        try{

            List<PeriodoProrrateoInmueble> listaInmueblesProrrateo = ejbProrrateo.consultarPeriodosProrrateados(periodoProrrateo);

            XSSFWorkbook excel = new XSSFWorkbook();


            for(PeriodoProrrateoInmueble inmuebleProrrateo:listaInmueblesProrrateo){
                generarExcel(excel,inmuebleProrrateo);
            }


            ByteArrayOutputStream bytes=new ByteArrayOutputStream();
            excel.write(bytes);
            excel.close();
            return bytes;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite generar el excel
     * @param excel archivo excel, tipo XSSFWorkbook.
     * @param periodoProrrateo periodo prorrateo del inmueble, tipo PeriodoProrrateoInmueble
     * @throws Exception excepción generada en caso de error.
     */
    private void generarExcel(XSSFWorkbook excel, PeriodoProrrateoInmueble periodoProrrateo)throws Exception{
        try {
            List<ProrrateoPredioGeneral> listaPrediosProrrateo = ejbProrrateo.obtenerCabeceraProrrateo(periodoProrrateo);


            XSSFSheet sheet = excel.createSheet(periodoProrrateo.getIdInmueble());
            XSSFFont font = excel.createFont();
            font.setBold(true);
            CellStyle estiloCelda = excel.createCellStyle();
            estiloCelda.setWrapText(true);
            estiloCelda.setAlignment(HorizontalAlignment.CENTER);
            estiloCelda.setFont(font);

            estiloCelda.setBorderBottom(BorderStyle.MEDIUM);
            estiloCelda.setBorderLeft(BorderStyle.MEDIUM);
            estiloCelda.setBorderRight(BorderStyle.MEDIUM);
            estiloCelda.setBorderTop(BorderStyle.MEDIUM);


            Row cabecera = sheet.createRow(0);

            Cell celdaInmueble = cabecera.createCell(0);
            celdaInmueble.setCellValue("CÓDIGO \nINMUEBLE");
            celdaInmueble.setCellStyle(estiloCelda);


            Cell celdaDescripcionInmueble = cabecera.createCell(1);
            celdaDescripcionInmueble.setCellValue("INMUEBLE");
            celdaDescripcionInmueble.setCellStyle(estiloCelda);

            Cell celdaBloque = cabecera.createCell(2);
            celdaBloque.setCellValue("BLOQUE");
            celdaBloque.setCellStyle(estiloCelda);

            Cell celdaPredio = cabecera.createCell(3);
            celdaPredio.setCellValue("PREDIO");
            celdaPredio.setCellStyle(estiloCelda);

            Cell celdaContrato = cabecera.createCell(4);
            celdaContrato.setCellValue("CONTRATO");
            celdaContrato.setCellStyle(estiloCelda);

            Cell celdaAdenda = cabecera.createCell(5);
            celdaAdenda.setCellValue("ADENDAS");
            celdaAdenda.setCellStyle(estiloCelda);

            Cell celdaFechaInicio = cabecera.createCell(6);
            celdaFechaInicio.setCellValue("F. INICIO \nCONTRATO \nO ADENDA");
            celdaFechaInicio.setCellStyle(estiloCelda);

            Cell celdaFechaFin = cabecera.createCell(7);
            celdaFechaFin.setCellValue("F. FIN \nCONTRATO \nO ADENDA");
            celdaFechaFin.setCellStyle(estiloCelda);

            Cell celdaArrendatarios = cabecera.createCell(8);
            celdaArrendatarios.setCellValue("ARRENDATARIO");
            celdaArrendatarios.setCellStyle(estiloCelda);

            Cell celdaAreaOcupadaPredio = cabecera.createCell(9);
            celdaAreaOcupadaPredio.setCellValue("ÁREA OCUPADA \nPREDIO");
            celdaAreaOcupadaPredio.setCellStyle(estiloCelda);

            Cell celdaDiasOcupadosPredio = cabecera.createCell(10);
            celdaDiasOcupadosPredio.setCellValue("DIAS OCUPADOS \nPREDIO");
            celdaDiasOcupadosPredio.setCellStyle(estiloCelda);

            Cell celdaDiasDesocupadosPredio = cabecera.createCell(11);
            celdaDiasDesocupadosPredio.setCellValue("DIAS DESOCUPADOS \nPREDIO");
            celdaDiasDesocupadosPredio.setCellStyle(estiloCelda);


            Map<Long,Integer> listaFilaPredio = new HashMap<>();

            int fila = 1;
            for(ProrrateoPredioGeneral predioProrrateo:listaPrediosProrrateo){
                Row datosPredio = sheet.createRow(fila);

                listaFilaPredio.put(predioProrrateo.getIdProrrateoInmueble(), fila);

                CellStyle estiloItem = excel.createCellStyle();
                estiloItem.setAlignment(HorizontalAlignment.CENTER);
                Cell celdaReporteInmueble = datosPredio.createCell(0);
                celdaReporteInmueble.setCellValue(predioProrrateo.getIdInmueble()!=null?predioProrrateo.getIdInmueble():"");
                celdaReporteInmueble.setCellStyle(estiloItem);

                Cell celdaReporteDescripcionInmueble = datosPredio.createCell(1);
                celdaReporteDescripcionInmueble.setCellValue(predioProrrateo.getDescripcionInmueble() !=null?predioProrrateo.getDescripcionInmueble():"");
                celdaReporteDescripcionInmueble.setCellStyle(estiloItem);

                Cell celdaReporteBloque = datosPredio.createCell(2);
                celdaReporteBloque.setCellValue(predioProrrateo.getDescripcionBloque() !=null?predioProrrateo.getDescripcionBloque():"");
                celdaReporteBloque.setCellStyle(estiloItem);

                Cell celdaReportePredio = datosPredio.createCell(3);
                celdaReportePredio.setCellValue(predioProrrateo.getDescripcionPredio() !=null?predioProrrateo.getDescripcionPredio():"");
                celdaReportePredio.setCellStyle(estiloItem);

                Cell celdaReporteContrato = datosPredio.createCell(4);
                celdaReporteContrato.setCellValue(predioProrrateo.getIdContrato() !=null?predioProrrateo.getIdContrato():"");
                celdaReporteContrato.setCellStyle(estiloItem);

                Cell celdaReporteAdenda = datosPredio.createCell(5);
                celdaReporteAdenda.setCellValue(predioProrrateo.getCodigoAdenda() !=null?predioProrrateo.getCodigoAdenda():"");
                celdaReporteAdenda.setCellStyle(estiloItem);

                Cell celdaReporteFechaInicio = datosPredio.createCell(6);
                celdaReporteFechaInicio.setCellValue(predioProrrateo.getFechaInicioContrato() !=null? UConvierteFecha.formatearFecha(predioProrrateo.getFechaInicioContrato()):"");
                celdaReporteFechaInicio.setCellStyle(estiloItem);

                Cell celdaReporteFechaFin = datosPredio.createCell(7);
                celdaReporteFechaFin.setCellValue(predioProrrateo.getFechaFinContrato() !=null?UConvierteFecha.formatearFecha(predioProrrateo.getFechaFinContrato()):"");
                celdaReporteFechaFin.setCellStyle(estiloItem);

                Cell celdaReporteArrendatario = datosPredio.createCell(8);
                celdaReporteArrendatario.setCellValue(predioProrrateo.getCodigoArrendatario() !=null?predioProrrateo.getCodigoArrendatario():"");
                celdaReporteArrendatario.setCellStyle(estiloItem);

                Cell celdaReporteAreaOcupadaPredio = datosPredio.createCell(9);
                if(predioProrrateo.getAreaOcupadaPredio() !=null){
                    celdaReporteAreaOcupadaPredio.setCellValue(predioProrrateo.getAreaOcupadaPredio().doubleValue());
                }else{
                    celdaReporteAreaOcupadaPredio.setCellValue("");
                }
                celdaReporteAreaOcupadaPredio.setCellStyle(estiloItem);

                Cell celdaReporteDiasOcupados = datosPredio.createCell(10);
                celdaReporteDiasOcupados.setCellValue(predioProrrateo.getDiasOcupadoPredio()!=null?predioProrrateo.getDiasOcupadoPredio():0);
                celdaReporteDiasOcupados.setCellStyle(estiloItem);

                Cell celdaReporteDiasDesocupados = datosPredio.createCell(11);
                celdaReporteDiasDesocupados.setCellValue(predioProrrateo.getDiasDesocupadoPredio()!=null?predioProrrateo.getDiasDesocupadoPredio():0);
                celdaReporteDiasDesocupados.setCellStyle(estiloItem);

                fila++;
            }

            CellStyle estiloNumero = excel.createCellStyle();
            DataFormat format = excel.createDataFormat();
            estiloNumero.setDataFormat(format.getFormat("#,##0.00"));


            int columnaDinamica = 12;


            InmuebleBloque inmuebleBloque = new InmuebleBloque();
            inmuebleBloque.setIdInmueble(periodoProrrateo.getIdInmueble());
            int cantidadBloques = ejbInmuebleBloque.obtenerCantidadBloquesVigentes(inmuebleBloque);

            List<ServicioProrrateoInmueble> listaServiciosProrrateo = ejbProrrateo.consultarServiciosSuministrosPeriodoProrrateo(periodoProrrateo);

            if(cantidadBloques > 0){
                for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){
                    if(servicioProrrateo.getIdServicio()!=null){
                        int columnaInicialServicio = columnaDinamica;
                        List<ProrrateoPredioDetalle> listaProrrateoServicioPredio = ejbProrrateo.consultarProrrateoGeneradoServiciosConBloque(periodoProrrateo,servicioProrrateo);
                        String descripcionServicio = listaProrrateoServicioPredio.getFirst().getDescripcionServicio();
                        String tipoProrrateoBloque = listaProrrateoServicioPredio.getFirst().getTipoProrrateoInmueble();//Tipo de prorrateo de inmueble para sus bloques


                        Cell celdaCostoServicioPredio = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredio.setCellValue("COSTO SERVICIO \n"+descripcionServicio+" \nPOR PREDIO OCUPADO");
                        celdaCostoServicioPredio.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        Cell celdaCostoServicioPredioDesocupado = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredioDesocupado.setCellValue("COSTO SERVICIO \n"+descripcionServicio+" \nPOR PREDIO DESOCUPADO");
                        celdaCostoServicioPredioDesocupado.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        for(ProrrateoPredioDetalle prorrateoServicioPredio :listaProrrateoServicioPredio){
                            int columnaInicialServicioDinamica = columnaInicialServicio;

                            int filaServicio = listaFilaPredio.get(prorrateoServicioPredio.getIdProrrateoInmueble());
                            Row datosServicioPredio = sheet.getRow(filaServicio);

                            CellStyle estiloServicioItem = excel.createCellStyle();
                            estiloServicioItem.setAlignment(HorizontalAlignment.CENTER);


                            Cell celdaItemCostoServicioPredio = datosServicioPredio.createCell(columnaInicialServicioDinamica);


                            celdaItemCostoServicioPredio.setCellValue(prorrateoServicioPredio.getMontoPredioOcupado()!=null?prorrateoServicioPredio.getMontoPredioOcupado().doubleValue():0);
                            celdaItemCostoServicioPredio.setCellStyle(estiloNumero);

                            columnaInicialServicioDinamica++;

                            Cell celdaItemCostoServicioPredioDesocupado = datosServicioPredio.createCell(columnaInicialServicioDinamica);
                            celdaItemCostoServicioPredioDesocupado.setCellValue(prorrateoServicioPredio.getMontoPredioDesocupado()!=null?prorrateoServicioPredio.getMontoPredioDesocupado().doubleValue():0);
                            celdaItemCostoServicioPredioDesocupado.setCellStyle(estiloNumero);


                        }


                    }else if(servicioProrrateo.getIdSuministroInmueble()!=null){
                        int columnaInicialSuministro = columnaDinamica;

                        List<ProrrateoPredioDetalle> listaProrrateoServicioPredio = ejbProrrateo.consultarProrrateoGeneradoSuministrosInmuebleConBloque(periodoProrrateo,servicioProrrateo);
                        String descripcionSuministro = listaProrrateoServicioPredio.getFirst().getDescripcionServicio();
                        String tipoProrrateoBloque = listaProrrateoServicioPredio.getFirst().getTipoProrrateoInmueble();//Tipo de prorrateo de inmueble para sus bloques

                        Cell celdaCostoServicioPredio = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredio.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nPOR PREDIO OCUPADO");
                        celdaCostoServicioPredio.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        Cell celdaCostoServicioPredioDesocupado = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredioDesocupado.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nPOR PREDIO DESOCUPADO");
                        celdaCostoServicioPredioDesocupado.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        for(ProrrateoPredioDetalle prorrateoServicioPredio :listaProrrateoServicioPredio){
                            int columnaInicialSuministroDinamica = columnaInicialSuministro;

                            int filaServicio = listaFilaPredio.get(prorrateoServicioPredio.getIdProrrateoInmueble());
                            Row datosServicioPredio = sheet.getRow(filaServicio);

                            CellStyle estiloServicioItem = excel.createCellStyle();
                            estiloServicioItem.setAlignment(HorizontalAlignment.CENTER);


                            Cell celdaItemCostoServicioPredio = datosServicioPredio.createCell(columnaInicialSuministroDinamica);


                            celdaItemCostoServicioPredio.setCellValue(prorrateoServicioPredio.getMontoPredioOcupado()!=null?prorrateoServicioPredio.getMontoPredioOcupado().doubleValue():0);
                            celdaItemCostoServicioPredio.setCellStyle(estiloNumero);

                            columnaInicialSuministroDinamica++;

                            Cell celdaItemCostoServicioPredioDesocupado = datosServicioPredio.createCell(columnaInicialSuministroDinamica);
                            celdaItemCostoServicioPredioDesocupado.setCellValue(prorrateoServicioPredio.getMontoPredioDesocupado()!=null?prorrateoServicioPredio.getMontoPredioDesocupado().doubleValue():0);
                            celdaItemCostoServicioPredioDesocupado.setCellStyle(estiloNumero);

                        }
                    }else if(servicioProrrateo.getIdSuministroBloque()!=null){

                        int columnaInicialSuministroBloque = columnaDinamica;

                        List<ProrrateoPredioDetalle> listaProrrateoServicioPredio = ejbProrrateo.consultarProrrateoGeneradoSuministrosBloque(periodoProrrateo,servicioProrrateo);
                        String descripcionSuministro = listaProrrateoServicioPredio.getFirst().getDescripcionServicio();


                        Cell celdaCostoServicioPredio = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredio.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nDEL PREDIO OCUPADO");
                        celdaCostoServicioPredio.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        Cell celdaCostoServicioPredioDesocupado = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredioDesocupado.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nDEL PREDIO DESOCUPADO");
                        celdaCostoServicioPredioDesocupado.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        for(ProrrateoPredioDetalle prorrateoServicioPredio :listaProrrateoServicioPredio){

                            int columnaInicialSuministroBloqueDinamica = columnaInicialSuministroBloque;

                            int filaServicio = listaFilaPredio.get(prorrateoServicioPredio.getIdProrrateoInmueble());
                            Row datosServicioPredio = sheet.getRow(filaServicio);

                            CellStyle estiloServicioItem = excel.createCellStyle();
                            estiloServicioItem.setAlignment(HorizontalAlignment.CENTER);


                            Cell celdaItemCostoServicioPredio = datosServicioPredio.createCell(columnaInicialSuministroBloqueDinamica);

                            celdaItemCostoServicioPredio.setCellValue(prorrateoServicioPredio.getMontoPredioOcupado()!=null?prorrateoServicioPredio.getMontoPredioOcupado().doubleValue():0);
                            celdaItemCostoServicioPredio.setCellStyle(estiloNumero);

                            columnaInicialSuministroBloqueDinamica++;

                            Cell celdaItemCostoServicioPredioOcupado = datosServicioPredio.createCell(columnaInicialSuministroBloqueDinamica);

                            celdaItemCostoServicioPredioOcupado.setCellValue(prorrateoServicioPredio.getMontoPredioDesocupado()!=null?prorrateoServicioPredio.getMontoPredioDesocupado().doubleValue():0);
                            celdaItemCostoServicioPredioOcupado.setCellStyle(estiloNumero);


                        }
                    }
                }
            }else{

                for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){
                    if(servicioProrrateo.getIdServicio()!=null){
                        int columnaInicialServicio = columnaDinamica;
                        List<ProrrateoPredioDetalle> listaProrrateoServicioPredio = ejbProrrateo.consultarProrrateoGeneradoServiciosSinBloque(periodoProrrateo,servicioProrrateo);
                        String descripcionServicio = listaProrrateoServicioPredio.getFirst().getDescripcionServicio();


                        Cell celdaCostoServicioPredio = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredio.setCellValue("COSTO SERVICIO \n"+descripcionServicio+" \nDEL PREDIO OCUPADO");
                        celdaCostoServicioPredio.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        Cell celdaCostoServicioPredioDesocupado = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredioDesocupado.setCellValue("COSTO SERVICIO \n"+descripcionServicio+" \nDEL PREDIO DESOCUPADO");
                        celdaCostoServicioPredioDesocupado.setCellStyle(estiloCelda);

                        columnaDinamica++;


                        for(ProrrateoPredioDetalle prorrateoServicioPredio :listaProrrateoServicioPredio){
                            int columnaInicialServicioDinamica = columnaInicialServicio;


                            int filaServicio = listaFilaPredio.get(prorrateoServicioPredio.getIdProrrateoInmueble());
                            Row datosServicioPredio = sheet.getRow(filaServicio);

                            CellStyle estiloServicioItem = excel.createCellStyle();
                            estiloServicioItem.setAlignment(HorizontalAlignment.CENTER);


                            Cell celdaItemCostoServicioPredio = datosServicioPredio.createCell(columnaInicialServicioDinamica);

                            celdaItemCostoServicioPredio.setCellValue(prorrateoServicioPredio.getMontoPredioOcupado()!=null?prorrateoServicioPredio.getMontoPredioOcupado().doubleValue():0);
                            celdaItemCostoServicioPredio.setCellStyle(estiloNumero);

                            columnaInicialServicioDinamica++;

                            Cell celdaItemCostoServicioPredioDesocupado = datosServicioPredio.createCell(columnaInicialServicioDinamica);

                            celdaItemCostoServicioPredioDesocupado.setCellValue(prorrateoServicioPredio.getMontoPredioDesocupado()!=null?prorrateoServicioPredio.getMontoPredioDesocupado().doubleValue():0);
                            celdaItemCostoServicioPredioDesocupado.setCellStyle(estiloNumero);
                        }
                    }else if(servicioProrrateo.getIdSuministroInmueble()!=null){
                        int columnaInicialSuministro = columnaDinamica;

                        List<ProrrateoPredioDetalle> listaProrrateoServicioPredio = ejbProrrateo.consultarProrrateoGeneradoSuministrosInmuebleSinBloque(periodoProrrateo,servicioProrrateo);
                        String descripcionSuministro = listaProrrateoServicioPredio.getFirst().getDescripcionServicio();


                        Cell celdaCostoServicioPredio = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredio.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nDEL PREDIO OCUPADO");
                        celdaCostoServicioPredio.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        Cell celdaCostoServicioPredioDesocupado = cabecera.createCell(columnaDinamica);
                        celdaCostoServicioPredioDesocupado.setCellValue("COSTO SUMINISTRO \n"+descripcionSuministro+" \nDEL PREDIO DESOCUPADO");
                        celdaCostoServicioPredioDesocupado.setCellStyle(estiloCelda);

                        columnaDinamica++;

                        for(ProrrateoPredioDetalle prorrateoServicioPredio :listaProrrateoServicioPredio){
                            int columnaInicialSuministroDinamica = columnaInicialSuministro;

                            int filaServicio = listaFilaPredio.get(prorrateoServicioPredio.getIdProrrateoInmueble());
                            Row datosServicioPredio = sheet.getRow(filaServicio);

                            CellStyle estiloServicioItem = excel.createCellStyle();
                            estiloServicioItem.setAlignment(HorizontalAlignment.CENTER);


                            Cell celdaItemCostoServicioPredio = datosServicioPredio.createCell(columnaInicialSuministroDinamica);

                            celdaItemCostoServicioPredio.setCellValue(prorrateoServicioPredio.getMontoPredioOcupado()!=null?prorrateoServicioPredio.getMontoPredioOcupado().doubleValue():0);
                            celdaItemCostoServicioPredio.setCellStyle(estiloNumero);

                            columnaInicialSuministroDinamica++;

                            Cell celdaItemCostoServicioPredioDesocupado = datosServicioPredio.createCell(columnaInicialSuministroDinamica);
                            celdaItemCostoServicioPredioDesocupado.setCellValue(prorrateoServicioPredio.getMontoPredioDesocupado()!=null?prorrateoServicioPredio.getMontoPredioDesocupado().doubleValue():0);
                            celdaItemCostoServicioPredioDesocupado.setCellStyle(estiloNumero);
                        }

                    }
                }
            }

            for(int i=0;i<=columnaDinamica;i++){
                sheet.autoSizeColumn(i);
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    /**
     * Método que permite generar la ventana de servicios del prorrateo de inmueble.
     * @param excel archivo excel, tipo XSSFWorkbook.
     * @param periodoProrrateo periodo prorrateo del inmueble, tipo PeriodoProrrateoInmueble
     * @throws Exception excepción generada en caso de error.
     */
    private void generarVentanaServiciosInmuebleProrrateo(XSSFWorkbook excel, PeriodoProrrateoInmueble periodoProrrateo)throws Exception{
        try {
            List<ServicioProrrateoInmueble> listaServiciosProrrateo = ejbProrrateo.consultaMontosServiciosPeriodo(periodoProrrateo);
            XSSFSheet sheet = excel.createSheet("SERVICIOS");
            XSSFFont font = excel.createFont();
            font.setBold(true);
            CellStyle estiloCelda = excel.createCellStyle();
            estiloCelda.setWrapText(true);
            estiloCelda.setAlignment(HorizontalAlignment.CENTER);
            estiloCelda.setFont(font);

            Row cabecera = sheet.createRow(0);

            Cell celdaInmueble = cabecera.createCell(0);
            celdaInmueble.setCellValue("PERIODO");
            celdaInmueble.setCellStyle(estiloCelda);

            Cell celdaDescripcionInmueble = cabecera.createCell(1);
            celdaDescripcionInmueble.setCellValue("CÓDIGO INMUEBLE");
            celdaDescripcionInmueble.setCellStyle(estiloCelda);

            Cell celdaBloque = cabecera.createCell(2);
            celdaBloque.setCellValue("NOMBRE INMUEBLE");
            celdaBloque.setCellStyle(estiloCelda);

            Cell celdaPredio = cabecera.createCell(3);
            celdaPredio.setCellValue("SERVICIO");
            celdaPredio.setCellStyle(estiloCelda);

            Cell celdaContrato = cabecera.createCell(4);
            celdaContrato.setCellValue("MONTO TOTAL DEL MES");
            celdaContrato.setCellStyle(estiloCelda);

            int fila = 1;

            Inmueble datosInmueble = ejbInmueble.obtenerInmueble(periodoProrrateo.getIdInmueble());


            for(ServicioProrrateoInmueble servicioProrrateo:listaServiciosProrrateo){
                Row datosPredio = sheet.createRow(fila);

                CellStyle estiloItem = excel.createCellStyle();
                estiloItem.setAlignment(HorizontalAlignment.CENTER);

                CellStyle estiloNumero = excel.createCellStyle();
                DataFormat format = excel.createDataFormat();
                estiloNumero.setDataFormat(format.getFormat("#,##0.00"));

                Cell celdaReporteInmueble = datosPredio.createCell(0);
                celdaReporteInmueble.setCellValue(UConvierteFecha.obtenerDescripcionMesMayuscula(servicioProrrateo.getPeriodoInmueble().getMesProrrateo())+"-"+servicioProrrateo.getPeriodoInmueble().getAnioProrrateo());

                Cell celdaReporteDescripcionInmueble = datosPredio.createCell(1);
                celdaReporteDescripcionInmueble.setCellValue(servicioProrrateo.getPeriodoInmueble().getIdInmueble() !=null?servicioProrrateo.getPeriodoInmueble().getIdInmueble():"");

                Cell celdaReporteBloque = datosPredio.createCell(2);
                celdaReporteBloque.setCellValue(datosInmueble.getDescripcionInmueble() !=null?datosInmueble.getDescripcionInmueble():"");

                Cell celdaReportePredio = datosPredio.createCell(3);
                celdaReportePredio.setCellValue(servicioProrrateo.getDescripcionServicio()!=null?servicioProrrateo.getDescripcionServicio():"");

                Cell celdaReporteContrato = datosPredio.createCell(4);
                if(servicioProrrateo.getMontoProrrateoServicio()!=null){
                    celdaReporteContrato.setCellValue(servicioProrrateo.getMontoProrrateoServicio().doubleValue());
                    celdaReporteContrato.setCellStyle(estiloNumero);
                }else{
                    celdaReporteContrato.setCellValue("");
                }

                fila++;
            }

            for(int i=0;i<=4;i++){
                sheet.autoSizeColumn(i);
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite consultar la lista de inmuebles para el prorrateo
     * @param pagina página a buscar, tipo int
     * @param nroRegistros número de registros a buscar, tipo int
     * @return resultado datos del inmueble consultado, tipo ResultadoBusquedaInmueble
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaProrrateo consultarInmueblesProrrateoPredio(ProrrateoPredioGeneral prorrateoBusqueda, int pagina,
                                                                        int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("mes", prorrateoBusqueda.getMes());
            parametro.put("anio", prorrateoBusqueda.getAnio());
            parametro.put("idInmueble", prorrateoBusqueda.getIdInmueble());
            parametro.put("tipoEstado", prorrateoBusqueda.getTipoEstado());
            parametro.put("tipoInmueble", prorrateoBusqueda.getTipoInmueble());
            parametro.put("tipoUso", prorrateoBusqueda.getTipoUso());
            parametro.put("tipoBloque", prorrateoBusqueda.getTipoBloque());
            parametro.put("tipoServicio", prorrateoBusqueda.getTipoServicio());
            parametro.put("descripcionPredio", prorrateoBusqueda.getDescripcionPredio());
            parametro.put("codigo", prorrateoBusqueda.getCodigo());
            parametro.put("pagina", pagina);
            parametro.put("paginacion", nroRegistros);
            int cantidadRegistrosBusquedaInmueble = obtenerCantidadInmueblesPredioParaProrrateo(prorrateoBusqueda);
            List<ProrrateoPredioGeneral> listaInmuebles = consultarInmueblesPredioParaProrrateo(parametro);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaInmueble);

            ResultadoBusquedaProrrateo resultado = new ResultadoBusquedaProrrateo();
            resultado.setPaginacion(paginacion);
            resultado.setListaProrrateoPredioGeneral(listaInmuebles);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene la lista de inmuebles según parametros de busqueda
     * @param parametros mapa de parametros de busqueda, tipo Map<String,Object>
     * @return listaInmuebles lista de inmuebles obtenida, tipo List<Inmueble>
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<ProrrateoPredioGeneral> consultarInmueblesPredioParaProrrateo(Map<String, Object> parametros) throws Exception {
        List<ProrrateoPredioGeneral> listaInmuebles;
        try {
            listaInmuebles = ejbProrrateo.consultarInmueblesPredioParaProrrateo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaInmuebles;
    }


    /**
     * Método que permite obtener la cantidad de registros del prorrateo Predio.
     * @param prorrateoPredio bean con los datos de búsqueda del prorrateo predio del inmueble, tipo ProrrateoPredioGeneral.
     * @return cantidad número de registros del predio del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadInmueblesPredioParaProrrateo(ProrrateoPredioGeneral prorrateoPredio) throws Exception {
        int cantidad;
        try{
            cantidad = ejbProrrateo.obtenerCantidadInmueblesPredioParaProrrateo(prorrateoPredio);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }

        return cantidad;
    }


    @Override
    public  Map<String, Object> generarProrrateoInmuebleBloquePredio(ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio) throws Exception {

        Map<String,Object> response;
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("mes",filtroReporteProrrateoInmuebleBloquePredio.getMes());
        parametros.put("anio",filtroReporteProrrateoInmuebleBloquePredio.getAnio());
        parametros.put("codServicio",filtroReporteProrrateoInmuebleBloquePredio.getCodServicio());
        ResultSet rs = null;
        parametros.put("C_LISTA_VALORES", rs);
        Integer codigoRespuesta=null;
        parametros.put("PO_CODIGORESPUESTA", codigoRespuesta);
        String respuesta=null;
        parametros.put("PO_MENSAJERESPUESTA", respuesta);

        try {
            response = ejbProrrateo.generarProrrateoInmuebleBloquePredio(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return response;
    }

    @Override
    public byte[] generarProrrateoInmuebleBloquePredioPdf(List<ReporteProrrateoInmuebleBloquePredio> listaReporteProrrateoInmuebleBloquePredio,
                                                          ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio, String ruta, ServletContext context,
                                                          Auditoria auditoria)  throws Exception{

        try{
            DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
            simbolo.setDecimalSeparator('.');
            simbolo.setGroupingSeparator(',');
            DecimalFormat formatoNumero = new DecimalFormat("#,##0.00",simbolo);

            BigDecimal formatDecimal = new BigDecimal("0.0");

            Map<String,Object> parametros = new HashMap<>();
            parametros.put("contexto", context.getRealPath("/"));
            parametros.put("P_PERIODO",filtroReporteProrrateoInmuebleBloquePredio.getDescMes()+" "+filtroReporteProrrateoInmuebleBloquePredio.getAnio());
            parametros.put("P_SERVICIO", filtroReporteProrrateoInmuebleBloquePredio.getServicio());

            List<Map<String, Object>> listaParaMap = new ArrayList<>();
            int n=1;
            for (ReporteProrrateoInmuebleBloquePredio doc : listaReporteProrrateoInmuebleBloquePredio) {

                Map<String, Object> map = new HashMap<>();
                map.put("ITEM", n);
                map.put("PREDIO", doc.getPredio());
                map.put("PRECARIO", doc.getPrecario());
                map.put("CONTRATO", doc.getContrato());
                map.put("CODARRENDATARIO", doc.getCodArrendatario());
                map.put("ARRENDATARIO", doc.getArrendatario());
                map.put("AREACONS", doc.getAreaConstruida());
                map.put("AREACOMUN", doc.getAreaComun());
                map.put("DIASOCUP", doc.getDiasOcupados());
                map.put("GENEROCOMP", doc.getGeneroComprobante());
                map.put("COSTOOCUPADOS", doc.getCostoOcupado());
                map.put("COSTODESOCUP", doc.getCostoDesocupado());
                map.put("TOTAL", doc.getTotal());
                map.put("TOTALOCUPADOS", doc.getTotalOcupado());
                map.put("TOTALDESOCUP", doc.getTotalDesocupado());
                map.put("TTOTAL", doc.getTotalGeneral());
                map.put("PERIODO", doc.getPeriodo());
                map.put("IDINMUEBLE", doc.getIdInmueble());
                map.put("INMUEBLE", doc.getInmueble());
                map.put("BLOQUE", doc.getBloque());
                map.put("AREACONSTEXT", formatoNumero.format(formatDecimal.add(doc.getAreaConstruida())));
                map.put("AREACOMUNTEXT", formatoNumero.format(formatDecimal.add(doc.getAreaComun())));
                map.put("COSTOOCUPADOSTEXT", formatoNumero.format(formatDecimal.add(doc.getCostoOcupado())));
                map.put("COSTODESOCUPTEXT", formatoNumero.format(formatDecimal.add(doc.getCostoDesocupado())));
                map.put("TOTALTEXT", formatoNumero.format(formatDecimal.add(doc.getTotal())));
                map.put("TOTALOCUPADOSTEXT", formatoNumero.format(formatDecimal.add(doc.getTotalOcupado())));
                map.put("TOTALDESOCUPTEXT", formatoNumero.format(formatDecimal.add(doc.getTotalDesocupado())));
                map.put("TTOTALTEXT", formatoNumero.format(formatDecimal.add(doc.getTotalGeneral())));
                map.put("IDBLOQUE", doc.getIdBloque());
                map.put("MONEDA", doc.getMoneda());
                map.put("SERVICIO", doc.getServicio());
                map.put("COSTOTODOSTEXT", formatoNumero.format(formatDecimal.add(doc.getCostoTodos())));
                map.put("NUMEROPREDIOSTEXT", formatoNumero.format(formatDecimal.add(doc.getNumeroPredios())));
                map.put("COSTOTODOS", doc.getCostoTodos());
                map.put("NUMEROPREDIOS", doc.getNumeroPredios());


                listaParaMap.add(map);
                n++;
            }


            if(listaParaMap.isEmpty()){
                ReporteProrrateoInmuebleBloquePredio doc = new ReporteProrrateoInmuebleBloquePredio();
                Map<String, Object> map = new HashMap<>();
                map.put("ITEM", n);
                map.put("PREDIO", doc.getPredio());
                map.put("PRECARIO", doc.getPrecario());
                map.put("CONTRATO", doc.getContrato());
                map.put("CODARRENDATARIO", doc.getCodArrendatario());
                map.put("ARRENDATARIO", doc.getArrendatario());
                map.put("IDBLOQUE", doc.getIdBloque());
                map.put("MONEDA", doc.getMoneda());
                map.put("SERVICIO", doc.getServicio());
                map.put("PERIODO", doc.getPeriodo());
                map.put("IDINMUEBLE", doc.getIdInmueble());
                map.put("INMUEBLE", doc.getInmueble());

                listaParaMap.add(map);
            }

            JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listaParaMap);
            InputStream reportStream = new FileInputStream(ruta);

            return JasperRunManager.runReportToPdf(reportStream, parametros, jrb);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    @Override
    public  Map<String, Object> verificacionProrrateo(ReporteVerificacionProrrateoPredio filtroReporteVerificacionProrrateo) throws Exception {

        Map<String,Object> response;
        Map<String,Object> parametros = new HashMap<>();
        parametros.put("mes",Integer.parseInt(filtroReporteVerificacionProrrateo.getMes()));
        parametros.put("anio",filtroReporteVerificacionProrrateo.getAnio());
        parametros.put("codServicio",filtroReporteVerificacionProrrateo.getIdServicio());
        ResultSet rsVerificacionInmueble = null;
        parametros.put("C_LISTA_CABECERA", rsVerificacionInmueble);
        ResultSet rsVerificacionBloque = null;
        parametros.put("C_LISTA_DE_TIP_PROR", rsVerificacionBloque);
        ResultSet rsVerificacion = null;
        parametros.put("C_LISTA_DE_AFECTO", rsVerificacion);

        Integer codigoRespuesta=null;
        parametros.put("PO_CODIGORESPUESTA", codigoRespuesta);
        String respuesta=null;
        parametros.put("PO_MENSAJERESPUESTA", respuesta);

        try {
            response = ejbProrrateo.verificacionProrrateo(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return response;
    }

    @Override
    public byte[] verificacionProrrateoPdf(List<ReporteVerificacionProrrateoInmueble> listaVerificacionPorInmueble,
                                           List<ReporteVerificacionProrrateoBloque> listaVerificacionPorBloque,
                                           List<ReporteVerificacionProrrateoPredio> listaVerificacionPorPredio,
                                           ReporteVerificacionProrrateoPredio filtroReporteVerificacionProrrateo, String ruta, ServletContext context,
                                           Auditoria auditoria)  throws Exception{
        try{

            Map<String,Object> parametros = new HashMap<>();
            parametros.put("contexto", context.getRealPath("/"));


            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
            Date fechaDate;

            fechaDate = formato.parse(filtroReporteVerificacionProrrateo.getAnio()+"-"+filtroReporteVerificacionProrrateo.getMes());
            Calendar c = Calendar.getInstance();
            c.setTime(fechaDate);
            c.add(Calendar.MONTH, -1);
            parametros.put("P_PERIODO1","PERIODO "+formato.format(c.getTime()));
            parametros.put("P_PERIODO2","PERIODO "+filtroReporteVerificacionProrrateo.getAnio()+" - "+filtroReporteVerificacionProrrateo.getMes());


            int n=1;
            List<Map<String, Object>> listaParaMapVerifica = new ArrayList<>();
            for (ReporteVerificacionProrrateoInmueble verifica : listaVerificacionPorInmueble) {

                Map<String, Object> mapVerifica = new HashMap<>();
                mapVerifica.put("ITEM", n);
                mapVerifica.put("IDINMUEBLE", verifica.getIdInmueble());
                mapVerifica.put("INMUEBLE", verifica.getInmueble());


                List<Map<String, Object>> listaParaMapVerificaInmueble = new ArrayList<>();
                for (ReporteVerificacionProrrateoInmueble verificaInmueble : listaVerificacionPorInmueble) {
                    Map<String, Object> mapVerificaInmueble = new HashMap<>();
                    mapVerificaInmueble.put("IDINMUEBLE", verificaInmueble.getIdInmueble());
                    mapVerificaInmueble.put("INMUEBLE", verificaInmueble.getInmueble());
                    mapVerificaInmueble.put("IDSERVICIO", verificaInmueble.getIdServicio());
                    mapVerificaInmueble.put("SERVICIO", verificaInmueble.getServicio());
                    mapVerificaInmueble.put("PRORRATEOP1", verificaInmueble.getProrrateoP1());
                    mapVerificaInmueble.put("PRORRATEOP2", verificaInmueble.getProrrateoP2());
                    if(verifica.getIdInmueble().equals(verificaInmueble.getIdInmueble())  &&  !listaParaMapVerificaInmueble.contains(mapVerificaInmueble)  ) {
                        listaParaMapVerificaInmueble.add(mapVerificaInmueble);
                    }

                    List<Map<String, Object>> listaParaMapVerificaBloque = new ArrayList<>();
                    List<Map<String, Object>> listaParaMapVerificaPredio = new ArrayList<>();
                    for (ReporteVerificacionProrrateoBloque verificaBloque : listaVerificacionPorBloque) {
                        Map<String, Object> mapVerificaBloque = new HashMap<>();
                        mapVerificaBloque.put("IDINMUEBLE", verificaBloque.getIdInmueble());
                        mapVerificaBloque.put("INMUEBLE", verificaBloque.getInmueble());
                        mapVerificaBloque.put("IDBLOQUE", verificaBloque.getIdBloque());
                        mapVerificaBloque.put("BLOQUE", verificaBloque.getBloque());
                        mapVerificaBloque.put("IDSERVICIO", verificaBloque.getIdServicio());
                        mapVerificaBloque.put("SERVICIO", verificaBloque.getServicio());
                        mapVerificaBloque.put("PRORRATEOBLOQUEP1", verificaBloque.getProrrateoBloqueP1());
                        mapVerificaBloque.put("PRORRATEOBLOQUEP2", verificaBloque.getProrrateoBloqueP2());
                        mapVerificaBloque.put("PRORRATEOPREDIOP1", verificaBloque.getProrrateoPredioP1());
                        mapVerificaBloque.put("PRORRATEOPREDIOP2", verificaBloque.getProrrateoPredioP2());
                        if(verifica.getIdInmueble().equals(verificaBloque.getIdInmueble())  &&  !listaParaMapVerificaBloque.contains(mapVerificaBloque)  ) {
                            listaParaMapVerificaBloque.add(mapVerificaBloque);

                            for (ReporteVerificacionProrrateoPredio verificaPredio : listaVerificacionPorPredio) {
                                Map<String, Object> mapVerificaPredio = new HashMap<>();
                                mapVerificaPredio.put("IDINMUEBLE", verificaPredio.getIdInmueble());
                                mapVerificaPredio.put("INMUEBLE", verificaPredio.getInmueble());
                                mapVerificaPredio.put("IDBLOQUE", verificaPredio.getIdBloque());
                                mapVerificaPredio.put("BLOQUE", verificaPredio.getBloque());
                                mapVerificaPredio.put("IDSERVICIO", verificaPredio.getIdServicio());
                                mapVerificaPredio.put("SERVICIO", verificaPredio.getServicio());
                                mapVerificaPredio.put("IDPREDIO", verificaPredio.getIdPredio());
                                mapVerificaPredio.put("PREDIO", verificaPredio.getPredio());
                                mapVerificaPredio.put("AFECTOP1", verificaPredio.getAfectoP1());
                                mapVerificaPredio.put("AFECTOP2", verificaPredio.getAfectoP2());

                                if(verifica.getIdInmueble().equals(verificaPredio.getIdInmueble()) && verificaBloque.getIdBloque().equals(verificaPredio.getIdBloque())
                                        &&  !listaParaMapVerificaPredio.contains(mapVerificaPredio)){
                                    listaParaMapVerificaPredio.add(mapVerificaPredio);
                                }
                            }

                            JRBeanCollectionDataSource jrbVerificaPredio = new JRBeanCollectionDataSource(listaParaMapVerificaPredio);
                            mapVerifica.put("DATAPROCESOPREDIO",jrbVerificaPredio);
                        }
                    }

                    JRBeanCollectionDataSource jrbVerificaBloque = new JRBeanCollectionDataSource(listaParaMapVerificaBloque);
                    mapVerifica.put("DATAPROCESOBLOQUE",jrbVerificaBloque);

                }
                JRBeanCollectionDataSource jrbVerificaInmueble = new JRBeanCollectionDataSource(listaParaMapVerificaInmueble);
                mapVerifica.put("DATAPROCESOINMUEBLE",jrbVerificaInmueble);
                if(!listaParaMapVerifica.contains(mapVerifica)) {
                    listaParaMapVerifica.add(mapVerifica);
                    n++;
                }
            }

            if(listaVerificacionPorInmueble.isEmpty()){
                ReporteVerificacionProrrateoInmueble verifica = new ReporteVerificacionProrrateoInmueble();
                Map<String, Object> mapVerifica = new HashMap<>();
                mapVerifica.put("ITEM", n);
                mapVerifica.put("IDINMUEBLE", verifica.getIdInmueble());
                mapVerifica.put("INMUEBLE", verifica.getInmueble());
                listaParaMapVerifica.add(mapVerifica);
            }
            JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(listaParaMapVerifica);
            InputStream reportStream = new FileInputStream(ruta);

            return JasperRunManager.runReportToPdf(reportStream, parametros, jrb);
        }catch(Exception excepcion){
            throw new Exception(excepcion);
        }
    }

    @Override
    public ByteArrayOutputStream generarProrrateoInmuebleBloquePredioExcel(
            List<ReporteProrrateoInmuebleBloquePredio> listaReporteProrrateoInmuebleBloquePredio,
            ReporteProrrateoInmuebleBloquePredio filtroReporteProrrateoInmuebleBloquePredio, String ruta,
            ServletContext context, Auditoria auditoria) throws Exception {



        try{
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("resultado");

            //addLogo(workBook,sheet,2.5,3,context);


            System.out.println("generarProrrateoInmuebleBloquePredioExcel" + filtroReporteProrrateoInmuebleBloquePredio.getCodServicio());

            String[] codigoServicio = filtroReporteProrrateoInmuebleBloquePredio.getCodServicio().split(",");
            //List<CategoriaParametro> listaServicioIni = categoriaParametroServicio.obtenerParametros(Parametro.TIPO_SERVICIO);
            // System.out.println("Ini" + listaServicioIni.size());
            // List<CategoriaParametro> listaServicio = new ArrayList<CategoriaParametro>();
            Servicio busquedaServicio = new Servicio();
            busquedaServicio.setDescripcionServicio("");
            ResultadoBusquedaMantenimiento resultadoBusqueda = servicioServicio.consultarServiciosProrrateo(busquedaServicio, 0, 0);
            List<Servicio> listaServicioIni = resultadoBusqueda.getListaServicios();
            List<Servicio> listaServicio = new ArrayList<>();

            for (String codigo: codigoServicio) {
                for (Servicio servicio: listaServicioIni) {
                    if (servicio.getCodigoServicio().equals(codigo)) {
                        listaServicio.add(servicio);
                    }
                }
            }

            //System.out.println("Fin" + listaServicio.size());

            XSSFFont fontTitles = workBook.createFont();
            fontTitles.setBold(true);
            fontTitles.setFontHeightInPoints((short)14);

            XSSFFont fontHeaders = workBook.createFont();
            fontHeaders.setBold(true);
            fontHeaders.setFontHeightInPoints((short)11);


            XSSFFont fontTotals = workBook.createFont();
            fontTotals.setBold(true);
            fontTotals.setFontHeightInPoints((short)10);

            XSSFFont fontNumber = workBook.createFont();
            fontTotals.setBold(false);
            fontTotals.setFontHeightInPoints((short)9);



            XSSFFont fontFilers = workBook.createFont();
            fontFilers.setFontHeightInPoints((short)12);

            CellStyle styleHeaders = workBook.createCellStyle();
            styleHeaders.setFont(fontHeaders);
            styleHeaders.setBorderBottom(BorderStyle.MEDIUM);
            styleHeaders.setBorderLeft(BorderStyle.MEDIUM);
            styleHeaders.setBorderRight(BorderStyle.MEDIUM);
            styleHeaders.setBorderTop(BorderStyle.MEDIUM);
            styleHeaders.setAlignment(HorizontalAlignment.CENTER);
            styleHeaders.setVerticalAlignment(VerticalAlignment.CENTER);
            styleHeaders.setWrapText(true);

            CellStyle estiloItem = workBook.createCellStyle();
            estiloItem.setAlignment(HorizontalAlignment.CENTER);

            CellStyle styleTitles = workBook.createCellStyle();
            styleTitles.setFont(fontTitles);
            styleTitles.setAlignment(HorizontalAlignment.CENTER_SELECTION);

            CellStyle endStyleReport = workBook.createCellStyle();
            endStyleReport.setFont(fontTitles);
            endStyleReport.setAlignment(HorizontalAlignment.CENTER_SELECTION);


            CellStyle styleNumber = workBook.createCellStyle();
            DataFormat format = workBook.createDataFormat();
            styleNumber.setDataFormat(format.getFormat("#,##0.00"));
            styleNumber.setAlignment(HorizontalAlignment.RIGHT);
            styleNumber.setFont(fontNumber);


            CellStyle styleNumberTotal = workBook.createCellStyle();
            DataFormat formatNumberTotal = workBook.createDataFormat();
            styleNumberTotal.setDataFormat(formatNumberTotal.getFormat("#,##0.00"));
            styleNumberTotal.setAlignment(HorizontalAlignment.RIGHT);
            styleNumberTotal.setFont(fontTotals);


            CellStyle styleSumTotal = workBook.createCellStyle();
            DataFormat formatTotal = workBook.createDataFormat();
            styleSumTotal.setDataFormat(formatTotal.getFormat("#,##0.00"));
            styleSumTotal.setFont(fontTotals);
            styleSumTotal.setBorderTop(BorderStyle.MEDIUM);
            styleSumTotal.setBorderBottom(BorderStyle.MEDIUM);

            CellStyle styleSumTitle = workBook.createCellStyle();
            styleSumTitle.setFont(fontTotals);
            styleSumTitle.setBorderTop(BorderStyle.MEDIUM);

            CellStyle styleTotal = workBook.createCellStyle();
            styleSumTitle.setFont(fontTotals);
            styleSumTitle.setBorderTop(BorderStyle.MEDIUM);
            styleNumber.setAlignment(HorizontalAlignment.LEFT);

            int cantidadColumnas = listaServicio.size() + 11;/*Columnas adicionales*/
            int cantColumnasTmp= cantidadColumnas - 3;
            int cantidadRow1 = 8;
            Row rowHeaders = null;
            Cell cellHeaders;


            int iniColumn = 8;
            int nextColumn = 0;

            for (int i = 0; i < 2; i++) {
                System.out.println("fila creada" + i);
                rowHeaders = sheet.createRow(i);
                //if (i == 0) {
                int contadorInt = 8;
                for (int j=0; j < listaServicio.size() ;j++) {
                    cellHeaders= rowHeaders.createCell(iniColumn);
                    cellHeaders.setCellStyle(styleHeaders);
                    if (i == 0) {
                        if (i == 0) cellHeaders.setCellValue(listaServicio.get(j).getDescripcionServicio().toUpperCase());
                        contadorInt += 1;
                        nextColumn = iniColumn + 2;
                        System.out.println("iniColumn" + iniColumn+ " nextColumn " +nextColumn);
                        sheet.addMergedRegion(new CellRangeAddress(0,0,iniColumn,nextColumn));
                        iniColumn += 3;
                    }

                }
                if (i == 1) {
                    for (int h= 0; h < cantidadColumnas;h++) {
                        cellHeaders= rowHeaders.createCell(h);
                        cellHeaders.setCellStyle(styleHeaders);
                        if (i == 1 && h== 0)cellHeaders.setCellValue("PREDIO");
                        if (i == 1 && h== 1)cellHeaders.setCellValue("PRECARIO");
                        if (i == 1 && h== 2)cellHeaders.setCellValue("CONTRATO");
                        if (i == 1 && h== 3)cellHeaders.setCellValue("ARRENDATARIO");
                        if (i == 1 && h== 4)cellHeaders.setCellValue("AREA CONS.");
                        if (i == 1 && h== 5)cellHeaders.setCellValue("% AREA COMUN");
                        if (i == 1 && h== 6)cellHeaders.setCellValue("DIAS OCUP");
                        if (i == 1 && h== 7)cellHeaders.setCellValue("GENERO COMP.");
                    }
                    int columnaActualRow2Tmp = iniColumn;
                    int columnaAutoIncrem  = 8;
                    int cont = 1;
                    for (int n= 8; n< columnaActualRow2Tmp;n++) {
                        cellHeaders= rowHeaders.createCell(n);
                        cellHeaders.setCellStyle(styleHeaders);
                        if (i == 1 && cont == 1)cellHeaders.setCellValue("COSTO OCUPADOS");
                        if (i == 1 &&  cont == 2)cellHeaders.setCellValue("COSTO DESOCUPADOS");
                        if (i == 1 &&  cont == 3)cellHeaders.setCellValue("TOTAL");
                        if (cont == 3) {
                            cont = 0;
                        }
                        cont = cont +1;
                    }

                    cellHeaders= rowHeaders.createCell(columnaActualRow2Tmp);
                    cellHeaders.setCellStyle(styleHeaders);
                    cellHeaders.setCellValue("TOTAL OCUPADOS");

                    cellHeaders= rowHeaders.createCell(columnaActualRow2Tmp +1);
                    cellHeaders.setCellStyle(styleHeaders);
                    cellHeaders.setCellValue("TOTAL DESOCUPADOS");

                    cellHeaders= rowHeaders.createCell(columnaActualRow2Tmp +2);
                    cellHeaders.setCellStyle(styleHeaders);
                    cellHeaders.setCellValue("TOTAL");

                }
            }

            for (int l = 8; l < cantidadColumnas - 1;l++) {
                sheet.setColumnWidth(l,3000);
            }

            sheet.setColumnWidth(0,3000);
            sheet.setColumnWidth(1,3000);
            sheet.setColumnWidth(2,4000);
            sheet.setColumnWidth(3,20000);
            sheet.setColumnWidth(4,3000);
            sheet.setColumnWidth(5,3000);
            sheet.setColumnWidth(6,3000);
            sheet.setColumnWidth(7,3000);

            int numItem = 1;
            int rowItem = 2 ;

            /*Acomodar los datos para colocarlo en las columnas*/
            List<ReporteProrrateoInmuebleBloquePredio> listaCab= new ArrayList<>();
            for(ReporteProrrateoInmuebleBloquePredio prorrateo:listaReporteProrrateoInmuebleBloquePredio){
                if (prorrateo.getNuflgCab() == 1) {
                    listaCab.add(prorrateo);
                }
            }

            for(ReporteProrrateoInmuebleBloquePredio prorrateoDet:listaCab){
                prorrateoDet.setDetalle(new ArrayList<>());
                for(ReporteProrrateoInmuebleBloquePredio prorrateo:listaReporteProrrateoInmuebleBloquePredio){
                    if (prorrateoDet.getIdBloque().equals(prorrateo.getIdBloque()) && prorrateoDet.getContrato().equals(prorrateo.getContrato()) && prorrateoDet.getIdInmueble().equals(prorrateo.getIdInmueble()) && prorrateoDet.getPredio().equals(prorrateo.getPredio())) {
                        ReporteProrrateoInmuebleBloquePredioDeta deta = new ReporteProrrateoInmuebleBloquePredioDeta();
                        deta.setCostoOcupado(prorrateo.getCostoOcupado());
                        deta.setCostoDesocupado(prorrateo.getCostoDesocupado());
                        deta.setTotal(prorrateo.getTotal());
                        deta.setTipoServicio(prorrateo.getTipoServicio());
                        prorrateoDet.getDetalle().add(deta);
                    }
                }
            }



            String descBloque = "";
            String descInmueble = "";
            for(ReporteProrrateoInmuebleBloquePredio datosFilaProrrateo:listaCab){
                System.out.println(descBloque + " - " + datosFilaProrrateo.getBloque());
                if (!descBloque.equals(datosFilaProrrateo.getBloque()) && !descInmueble.equals(datosFilaProrrateo.getInmueble())) {
                    Row filaBloque = sheet.createRow(rowItem);
                    Cell celdaPredio = filaBloque.createCell(0);
                    sheet.addMergedRegion(new CellRangeAddress(rowItem,rowItem,0,3));
                    celdaPredio.setCellValue("BLOQUE: "+datosFilaProrrateo.getBloque());
                    celdaPredio.setCellStyle(estiloItem);
                    descBloque = datosFilaProrrateo.getBloque();
                    descInmueble = datosFilaProrrateo.getInmueble();
                    numItem++;
                    rowItem++;
                }
                Row filaProrrateo = sheet.createRow(rowItem);

                Cell celdaPredio = filaProrrateo.createCell(0);
                celdaPredio.setCellValue(datosFilaProrrateo.getPredio());
                celdaPredio.setCellStyle(estiloItem);

                Cell celdaPrecario = filaProrrateo.createCell(1);
                celdaPrecario.setCellValue(datosFilaProrrateo.getPrecario());
                celdaPrecario.setCellStyle(estiloItem);

                Cell celdaContrato = filaProrrateo.createCell(2);
                celdaContrato.setCellValue(datosFilaProrrateo.getContrato());
                celdaContrato.setCellStyle(estiloItem);

                Cell celdaArrendatario = filaProrrateo.createCell(3);
                celdaArrendatario.setCellValue(datosFilaProrrateo.getArrendatario());

                Cell celdaAConstruida = filaProrrateo.createCell(4);
                celdaAConstruida.setCellValue(datosFilaProrrateo.getAreaConstruida().doubleValue());
                celdaAConstruida.setCellStyle(styleNumber);


                Cell celdaAreaComun = filaProrrateo.createCell(5);
                celdaAreaComun.setCellValue(datosFilaProrrateo.getAreaComun().doubleValue());
                celdaAreaComun.setCellStyle(styleNumberTotal);

                Cell celdaDiasOcupados = filaProrrateo.createCell(6);
                celdaDiasOcupados.setCellValue(datosFilaProrrateo.getDiasOcupados());
                celdaDiasOcupados.setCellStyle(styleNumberTotal);

                Cell celdaGeneroComp = filaProrrateo.createCell(7);
                celdaGeneroComp.setCellValue(datosFilaProrrateo.getGeneroComprobante());
                celdaGeneroComp.setCellStyle(estiloItem);

                int celdaActual = 8;
                boolean blExiste;
                ReporteProrrateoInmuebleBloquePredioDeta detalleTmp = null;
                System.out.println("1");
                for (int j=0; j < listaServicio.size() ;j++) {
                    blExiste = false;
                    for (ReporteProrrateoInmuebleBloquePredioDeta deta: datosFilaProrrateo.getDetalle()) {
                        if (listaServicio.get(j).getCodigoServicio().equals(deta.getTipoServicio())) {
                            detalleTmp = new ReporteProrrateoInmuebleBloquePredioDeta();
                            detalleTmp.setTipoServicio(deta.getTipoServicio());
                            detalleTmp.setCostoOcupado(deta.getCostoOcupado());
                            detalleTmp.setCostoDesocupado(deta.getCostoDesocupado());
                            detalleTmp.setTotal(deta.getTotal());
                            blExiste = true;
                            break;
                        }
                    }
                    if (blExiste) {
                        Cell c1 = filaProrrateo.createCell(celdaActual++);
                        c1.setCellValue(detalleTmp.getCostoOcupado().doubleValue());
                        c1.setCellStyle(styleNumberTotal);
                        Cell c2 = filaProrrateo.createCell(celdaActual++);
                        c2.setCellValue(detalleTmp.getCostoDesocupado().doubleValue());
                        c2.setCellStyle(styleNumberTotal);
                        Cell c3 = filaProrrateo.createCell(celdaActual++);
                        c3.setCellValue(detalleTmp.getTotal().doubleValue());
                        c3.setCellStyle(styleNumberTotal);
                    } else {
                        Cell c1 = filaProrrateo.createCell(celdaActual++);
                        c1.setCellValue(0.00D);
                        c1.setCellStyle(styleNumberTotal);
                        Cell c2 = filaProrrateo.createCell(celdaActual++);
                        c2.setCellValue(0.00D);
                        c2.setCellStyle(styleNumberTotal);
                        Cell c3 = filaProrrateo.createCell(celdaActual++);
                        c3.setCellValue(0.00D);
                        c3.setCellStyle(styleNumberTotal);
                    }

                }

                System.out.println("2");

                Cell celdaTotalCostoOcupado = filaProrrateo.createCell(celdaActual);
                celdaTotalCostoOcupado.setCellValue(datosFilaProrrateo.getTotalOcupado().doubleValue());
                celdaTotalCostoOcupado.setCellStyle(styleNumberTotal);

                Cell celdaTotalDesocupado = filaProrrateo.createCell(celdaActual +1);
                celdaTotalDesocupado.setCellValue(datosFilaProrrateo.getTotalDesocupado().doubleValue());
                celdaTotalDesocupado.setCellStyle(styleNumberTotal);

                Cell celdaTotal = filaProrrateo.createCell(celdaActual + 2);
                celdaTotal.setCellValue(datosFilaProrrateo.getTotalColumna().doubleValue());
                celdaTotal.setCellStyle(styleNumberTotal);

                numItem++;
                rowItem++;
                //System.out.println("ultBloque" + datosFilaProrrateo.getUltBloque() + " " +  datosFilaProrrateo.getUltInmueble());
                if (datosFilaProrrateo.getUltBloque() == 1) {
                    Row filaTotalBloque = sheet.createRow(rowItem);
                    Cell celdaTotalBloque = filaTotalBloque.createCell(3);
                    celdaTotalBloque.setCellValue("TOTAL BLOQUE : "+datosFilaProrrateo.getBloque());
                    //celdaTotalBloque.setCellStyle(styleTotal);
                    //sheet.addMergedRegion(new CellRangeAddress(rowItem,rowItem,2,3));
                    List<Double> listaTotalesBloque = obtenerTotalBloque(datosFilaProrrateo.getIdInmueble(), datosFilaProrrateo.getIdBloque(), listaServicio, listaReporteProrrateoInmuebleBloquePredio);
                    int inicio = 8;
                    for (int n= 0; n< listaTotalesBloque.size();n++) {
                        celdaTotalBloque= filaTotalBloque.createCell(inicio);
                        celdaTotalBloque.setCellValue(listaTotalesBloque.get(n));
                        celdaTotalBloque.setCellStyle(styleNumberTotal);
                        inicio += 1;
                    }
                    numItem++;
                    rowItem++;
                }

                if (datosFilaProrrateo.getUltInmueble() == 1) {
                    Row filaTotalInmueble = sheet.createRow(rowItem);
                    Cell celdaTotalInmueble = filaTotalInmueble.createCell(3);
                    celdaTotalInmueble.setCellValue("TOTAL INMUEBLE : "+datosFilaProrrateo.getInmueble());
                    //celdaTotalInmueble.setCellStyle(styleTotal);
                    //sheet.addMergedRegion(new CellRangeAddress(rowItem,rowItem,2,3));
                    List<Double> listaTotalesBloque = obtenerTotalInmueble(datosFilaProrrateo.getIdInmueble(),listaServicio, listaReporteProrrateoInmuebleBloquePredio);
                    int inicio = 8;
                    for (int n= 0; n< listaTotalesBloque.size();n++) {
                        celdaTotalInmueble= filaTotalInmueble.createCell(inicio);
                        celdaTotalInmueble.setCellValue(listaTotalesBloque.get(n));
                        celdaTotalInmueble.setCellStyle(styleNumberTotal);
                        inicio += 1;
                    }
                    numItem++;
                    rowItem++;

                    List<Double> listaTotales = obtenerTotalesInmueble(datosFilaProrrateo.getIdInmueble(),datosFilaProrrateo.getIdBloque(),listaServicio, listaReporteProrrateoInmuebleBloquePredio);
                    for (int l= 0; l< listaTotales.size();l++) {
                        Row nuevaFila = sheet.createRow(rowItem);
                        if (l == 0) {
                            Cell celdaTexto= nuevaFila.createCell(3);
                            celdaTexto.setCellValue("TOTAL AREA CONSTRUIDA: ");

                            Cell celdaTotales= nuevaFila.createCell(4);
                            celdaTotales.setCellStyle(styleNumberTotal);
                            celdaTotales.setCellValue(listaTotales.get(l));
                        }
                        if (l == 1) {
                            Cell celdaTexto= nuevaFila.createCell(3);
                            celdaTexto.setCellValue("TOTAL AREA OCUPADA ARRENDATARIO: ");

                            Cell celdaTotales= nuevaFila.createCell(4);
                            celdaTotales.setCellStyle(styleNumberTotal);
                            celdaTotales.setCellValue(listaTotales.get(l));
                        }
                        if (l == 2) {
                            Cell celdaTexto= nuevaFila.createCell(3);
                            celdaTexto.setCellValue("TOTAL AREA OCUPADA PRECARIO: ");

                            Cell celdaTotales= nuevaFila.createCell(4);
                            celdaTotales.setCellStyle(styleNumberTotal);
                            celdaTotales.setCellValue(listaTotales.get(l));
                        }
                        if (l == 3) {
                            Cell celdaTexto= nuevaFila.createCell(3);
                            celdaTexto.setCellValue("TOTAL AREA DESOCUPADA:");

                            Cell celdaTotales= nuevaFila.createCell(4);
                            celdaTotales.setCellStyle(styleNumberTotal);
                            celdaTotales.setCellValue(listaTotales.get(l));
                        }
                        numItem++;
                        rowItem++;
                    }
	        		 
	        			/*lista.add(totalAreaConstruida);
	        			lista.add(totalAreaOcupadaArre);
	        			lista.add(totalAreaOcupadaPrecario);
	        			lista.add(totalAreaDesocupada);*/

                }

            }
	         
	         /*rowItem = rowItem + 1;
	         int tRowItem = rowItem;
	         
	         Row rowTotal= null;
	         Cell cellTotal;
	         for (int i = rowItem; i <= (rowItem+1); ++i) {
	        	 rowTotal = sheet.createRow(i);
	             for(int j=11;j<=16;j++){	          
	            	 cellTotal= rowTotal.createCell(j);
	            	
	            	 if (i == tRowItem && (j== 11)) {
	            		 
	            		 if (i == tRowItem && j== 11)cellTotal.setCellValue("TOTAL GENERAL:");
	            		 cellTotal.setCellStyle(styleSumTitle);
	            	 }
	            	 if (i == tRowItem && j== 13){	            		
	            		 cellTotal.setCellValue("S/ :");
	            		 cellTotal.setCellStyle(styleSumTitle);
	            	 }
	            	  if (i == (tRowItem+1) && j== 13) {	            		
	            		cellTotal.setCellValue("US$ :");
	            		 cellTotal.setCellStyle(styleSumTitle);
	            	 }
	            	 cellTotal.setCellStyle(styleSumTotal);
	            	 
	                 if (i == tRowItem && j== 14)cellTotal.setCellValue(sumRentaSol.doubleValue());
	                 if (i == tRowItem && j== 15)cellTotal.setCellValue(sumRentaIgvSol.doubleValue());              
	                 if (i == tRowItem && j== 16)cellTotal.setCellValue(sumTotalCompSol.doubleValue());
	                 //if (i == tRowItem && j== 17)cellTotal.setCellValue(sumMoraSol.doubleValue());	                 
	              
	                 if (i == (tRowItem+1) && j== 14)cellTotal.setCellValue(sumRentaDol.doubleValue());
	                 if (i ==  (tRowItem+1) && j== 15)cellTotal.setCellValue(sumRentaIgvDol.doubleValue());              
	                 if (i ==  (tRowItem+1) && j== 16)cellTotal.setCellValue(sumTotalCompDol.doubleValue());
	                 //if (i ==  (tRowItem+1) && j== 17)cellTotal.setCellValue(sumMoraDol.doubleValue());	            	 
	             }
	         }
	         sheet.addMergedRegion(new CellRangeAddress(tRowItem,tRowItem,11,12));	        
	         
	         rowItem = rowItem + 3;
	          */
            Row endRowReport = sheet.createRow(rowItem);
            Cell endCellReport = endRowReport.createCell(0);
            endCellReport.setCellValue("*** FIN DE REPORTE ***");
            endCellReport.setCellStyle(endStyleReport);
            sheet.addMergedRegion(new CellRangeAddress(rowItem,rowItem,0,16));


            ByteArrayOutputStream bytes=new ByteArrayOutputStream();
            workBook.write(bytes);
            workBook.close();

            return bytes;
        }catch(Exception excepcion){
            System.out.println("ProrrateoDelegateEJB" + excepcion.getMessage());
            throw new Exception(excepcion);
        }
    }


    public List<Double> obtenerTotalBloque(String idInmueble, String idBloque, List<Servicio> listaServicio,List<ReporteProrrateoInmuebleBloquePredio> listaProrrateo){
        List<Double> lista = new ArrayList<>();
        Double costoOcupado= 0.00D;
        Double costoDesocupado = 0.00D;
        Double total = 0.00D;
        Double totalOcupado =0.00D;
        Double totalDesocupado=0.00D;
        Double totalGeneral =0.00D;

        List<ReporteProrrateoInmuebleBloquePredio> listaInmueblePredio = new ArrayList<>();

        for (ReporteProrrateoInmuebleBloquePredio inmueblePredio :listaProrrateo) {
            if (inmueblePredio.getIdInmueble().equals(idInmueble) && inmueblePredio.getIdBloque().equals(idBloque)) {
                listaInmueblePredio.add(inmueblePredio);
            }
        }


        for (int i=0;i<listaServicio.size();i++) {
            costoOcupado= 0.00D;
            costoDesocupado = 0.00D;
            total = 0.00D;


            for (int j=0;j<listaInmueblePredio.size();j++) {
                if (listaInmueblePredio.get(j).getTipoServicio().equals(listaServicio.get(i).getCodigoServicio())) {
                    costoOcupado += listaInmueblePredio.get(j).getCostoOcupado().doubleValue();
                    costoDesocupado += listaInmueblePredio.get(j).getCostoDesocupado().doubleValue();
                    total += listaInmueblePredio.get(j).getTotal().doubleValue();

                    totalOcupado += listaInmueblePredio.get(j).getTotalOcupado().doubleValue();
                    totalDesocupado += listaInmueblePredio.get(j).getTotalDesocupado().doubleValue();
                    totalGeneral += listaInmueblePredio.get(j).getTotalColumna().doubleValue();
                } else {
                    costoOcupado += 0.00D;
                    costoDesocupado += 0.00D;
                    total += 0.00D;
                }
            }
            lista.add(costoOcupado);
            lista.add(costoDesocupado);
            lista.add(total);
        }

        lista.add(totalOcupado);
        lista.add(totalDesocupado);
        lista.add(totalGeneral);

        System.out.println("tamanio lista retorno " + lista.size());
        return lista;
    }

    public List<Double> obtenerTotalInmueble(String idInmueble, List<Servicio> listaServicio,List<ReporteProrrateoInmuebleBloquePredio> listaProrrateo){
        List<Double> lista = new ArrayList<>();
        Double costoOcupado= 0.00D;
        Double costoDesocupado = 0.00D;
        Double total = 0.00D;
        Double totalOcupado =0.00D;
        Double totalDesocupado=0.00D;
        Double totalGeneral = 0.00D;

        List<ReporteProrrateoInmuebleBloquePredio> listaInmueblePredio = new ArrayList<>();

        for (ReporteProrrateoInmuebleBloquePredio inmueblePredio :listaProrrateo) {
            if (inmueblePredio.getIdInmueble().equals(idInmueble)) {
                listaInmueblePredio.add(inmueblePredio);
            }
        }


        for (int i=0;i<listaServicio.size();i++) {
            costoOcupado= 0.00D;
            costoDesocupado = 0.00D;
            total =0.00D;


            for (int j=0;j<listaInmueblePredio.size();j++) {
                if (listaInmueblePredio.get(j).getTipoServicio().equals(listaServicio.get(i).getCodigoServicio())) {
                    costoOcupado += listaInmueblePredio.get(j).getCostoOcupado().doubleValue();
                    costoDesocupado += listaInmueblePredio.get(j).getCostoDesocupado().doubleValue();
                    total += listaInmueblePredio.get(j).getTotal().doubleValue();

                    totalOcupado += listaInmueblePredio.get(j).getTotalOcupado().doubleValue();
                    totalDesocupado += listaInmueblePredio.get(j).getTotalDesocupado().doubleValue();
                    totalGeneral += listaInmueblePredio.get(j).getTotalColumna().doubleValue();
                } else {
                    costoOcupado +=0.00D;
                    costoDesocupado +=0.00D;
                    total +=0.00D;
                }
            }
            lista.add(costoOcupado);
            lista.add(costoDesocupado);
            lista.add(total);
        }

        lista.add(totalOcupado);
        lista.add(totalDesocupado);
        lista.add(totalGeneral);

        System.out.println("obtenerTotalInmueble " + lista.size());
        return lista;
    }

    public List<Double> obtenerTotalesInmueble(String idInmueble, String idBloque, List<Servicio> listaServicio,List<ReporteProrrateoInmuebleBloquePredio> listaProrrateo){
        List<Double> lista = new ArrayList<Double>();
        Double totalAreaConstruida= 0.00D;
        Double totalAreaOcupadaArre = 0.00D;
        Double totalAreaOcupadaPrecario = 0.00D;
        Double totalAreaDesocupada =0.00D;

        List<ReporteProrrateoInmuebleBloquePredio> listaInmueblePredio = new ArrayList<ReporteProrrateoInmuebleBloquePredio>();

        for (ReporteProrrateoInmuebleBloquePredio inmueblePredio :listaProrrateo) {
            if (inmueblePredio.getIdInmueble().equals(idInmueble) && inmueblePredio.getNuflgCab() == 1 && inmueblePredio.getIdBloque().equals(idBloque)) {
                listaInmueblePredio.add(inmueblePredio);
            }
        }
        System.out.println("cantidad inmueble predio " + listaInmueblePredio.size());
		
		/*totalAreaConstruida= 0.00D;
		totalAreaOcupadaArre = 0.00D;
		totalAreaOcupadaPrecario =0.00D;
		totalAreaDesocupada =0.00D;*/


        for (int i=0;i<listaServicio.size();i++) {


            for (int j=0;j<listaInmueblePredio.size();j++) {
                if (listaInmueblePredio.get(j).getTipoServicio().equals(listaServicio.get(i).getCodigoServicio())) {
                    totalAreaConstruida += listaInmueblePredio.get(j).getAreaConstruida().doubleValue();
                    totalAreaOcupadaArre += listaInmueblePredio.get(j).getCostoOcupado().doubleValue();
                    if (listaInmueblePredio.get(j).getPrecario().equals("SI")) {
                        totalAreaOcupadaPrecario += listaInmueblePredio.get(j).getTotal().doubleValue();
                    }
                    totalAreaDesocupada += 0.00D;
                }
            }
        }
        System.out.println("totalAreaConstruida " + totalAreaConstruida);
        lista.add(totalAreaConstruida);
        lista.add(totalAreaOcupadaArre);
        lista.add(totalAreaOcupadaPrecario);
        lista.add(totalAreaDesocupada);

        return lista;
    }


}