/**
 * Resumen.
 * Objeto               :   CartaAvisoEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para la consulta de cartas
 * de aviso.
 * Fecha de Creación    :   18/02/2021
 * PE de Creación       :   INI-900
 * Autor                :   Igor A. Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.CartaAvisoLocalDao;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.CartaAvisoService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Clase que implementa métodos para la consulta de las cartas de aviso
 */
@Service
public class CartaAvisoServiceImpl implements CartaAvisoService {

    /** Clase EJB para el mantenimiento de programaciones de mantenimientos */

    private final CartaAvisoLocalDao ejbCartaAviso;

    public CartaAvisoServiceImpl(CartaAvisoLocalDao ejbCartaAviso) {
        this.ejbCartaAviso = ejbCartaAviso;
    }

    /**
     * Método que permite realizar una consulta de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, tipo ResultadoBusquedaMantenimiento.
     * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarServicioInmuebleCartaAviso(Map<String, Object> parametros)
            throws Exception {
        try {
            int cantidad = obtenerCantidadServicioInmuebleCartaAviso(parametros);
            List<CartaAviso> lista = obtenerListaServicioInmuebleCartaAviso(parametros);
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

    /**
     * Método que permite obtener la cantidad de registros de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, Tipo Map<String,Object>.
     * @return número de registros de los inmuebles con cartas de aviso, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadServicioInmuebleCartaAviso(Map<String, Object> parametros) throws Exception {
        try {
            return ejbCartaAviso.obtenerCantidadServicioInmuebleCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite realizar una obtiene la lista de los inmuebles con cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de los inmuebles con cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo List<CartaAviso>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<CartaAviso> obtenerListaServicioInmuebleCartaAviso(Map<String, Object> parametros)
            throws Exception {
        try {
            return ejbCartaAviso.obtenerListaServicioInmuebleCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite realizar una consulta de las cartas de aviso generadas.
     * @param parametros objeto con los datos para las cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
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

    /**
     * Método que permite obtener la lista de las cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de las cartas de aviso, tipo Map<String,Object>.
     * @return resultado de la busqueda, tipo List<CartaAviso>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<CartaAviso> obtenerListaCartaAviso(Map<String, Object> parametros) throws Exception {
        // TODO Auto-generated method stub
        try {
            return ejbCartaAviso.obtenerListaCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de las cartas de aviso generadas.
     * @param parametros objeto con los datos para la búsqueda de las cartas de aviso, Tipo Map<String,Object>.
     * @return número de registros de las cartas de aviso, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadCartaAviso(Map<String, Object> parametros) throws Exception {
        try {
            return ejbCartaAviso.obtenerCantidadCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la plantilla de la carta de aviso de programacion
     * @param parametros objeto con los datos para la búsqueda de la plantilla de carta de aviso, Tipo Map<String,Object>.
     * @return datos de la plantilla de carta de aviso, tipo PlantillaCartaAvisoProgramacion.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PlantillaCartaAvisoProgramacion obtenerPlantillaCartaAviso(Map<String, Object> parametros) throws Exception {
        try {
            return ejbCartaAviso.obtenerPlantillaCartaAviso(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public byte[] getDocumentoCartaAviso(Map<String, Object> parametros) throws Exception {
        PlantillaCartaAvisoProgramacion document = obtenerPlantillaCartaAviso(null);
        List<CartaAviso> cartasSeleccionadas = (List<CartaAviso>) parametros.get("cartasSeleccionadas");
        Map<Long, Map<String, Object>> datos = new HashMap<>();

        //XWPFDocument base = new XWPFDocument(new ByteArrayInputStream(document.getBytes()));
        XWPFDocument doc = new XWPFDocument();
        XWPFDocument page = new XWPFDocument();


        int i = 1;
        int c = cartasSeleccionadas.size();
        //CARTAS SELECCIONADAS
        for (CartaAviso carta : cartasSeleccionadas) {

            List<PlantillaCartaAvisoDetalle> result = listarDatosCartaAvisoGenerada(parametros);

            if (i == 1) {
                doc = new XWPFDocument(new ByteArrayInputStream(document.getBytes()));

                //MARCAS CON VALORES A REEMPLAZAR
                for (PlantillaCartaAvisoDetalle det : result) {
                    for (XWPFParagraph p : doc.getParagraphs()) {
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                String text = r.getText(0);
                                if (text != null && text.contains(det.getNombreMarca())) {
                                    text = text.replace(det.getNombreMarca(), det.getValorMarca());
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
                if (c > 1) {
                    doc.getLastParagraph().createRun().addBreak(BreakType.PAGE);
                }
            } else {
                page = new XWPFDocument(new ByteArrayInputStream(document.getBytes()));
                //MARCAS CON VALORES A REEMPLAZAR
                for (PlantillaCartaAvisoDetalle det : result) {
                    for (XWPFParagraph p : page.getParagraphs()) {
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                String text = r.getText(0);
                                if (text != null && text.contains(det.getNombreMarca())) {
                                    text = text.replace(det.getNombreMarca(), det.getValorMarca());
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }

                if (i != c)
                    page.getLastParagraph().createRun().addBreak(BreakType.PAGE);
            }


            /**
             XWPFDocument document = new XWPFDocument(is);
             ...
             XWPFParagraph paragraph = document.createParagraph();
             XWPFRun run = paragraph.createRun();
             run.addBreak(BreakType.PAGE);
             */

            //JUMP PAGE
            if (i > 1) {
                //List<XWPFParagraph> l = doc.getParagraphs();

                doc.getDocument().addNewBody().set(page.getDocument().getBody());

            }
            i++;
        }

        //doc.getLastParagraph().createRun().addBreak(BreakType.PAGE);
        //doc.getDocument().addNewBody().set(doc.getDocument().getBody()); //<-TEST

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        doc.write(out);
        out.close();
        doc.close();

        // do something with the byte array
        return out.toByteArray();
    }

    /**
     * Método que permite obtener de manera dinámica el resultado de los datos para las cartas de aviso
     * @param parametros objeto con los datos para la búsqueda de la plantilla de carta de aviso, Tipo Map<String,Object>.
     * @return datos de las marcas de cartas de aviso, tipo List<Map<String,Object>>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PlantillaCartaAvisoDetalle> listarDatosCartaAvisoGenerada(Map<String, Object> parametros) throws Exception {
        try {
            return ejbCartaAviso.listarDatosCartaAvisoGenerada(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar el correlativo de la carta de aviso.
     * @param cartaAviso datos carta de aviso a modificar, tipo CartaAviso.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarCorrelativo(CartaAviso cartaAviso) throws Exception {
        // TODO Auto-generated method stub
        try {
            ejbCartaAviso.modificarCorrelativo(cartaAviso);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


}