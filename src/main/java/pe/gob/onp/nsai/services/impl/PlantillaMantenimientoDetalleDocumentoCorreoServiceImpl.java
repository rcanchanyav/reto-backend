package pe.gob.onp.nsai.services.impl;

import jakarta.servlet.ServletContext;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.PlantillaMantenimientoDetalleDocumentoCorreoLocalDao;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.PlantillaMantenimiento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;
import pe.gob.onp.nsai.services.PlantillaMantenimientoDocumentoCorreoService;
import pe.gob.onp.nsai.util.UWord;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Clase delegate para el mantenimiento de la plantilla.
 */
@Service
public class PlantillaMantenimientoDetalleDocumentoCorreoServiceImpl implements PlantillaMantenimientoDocumentoCorreoService {

    /**
     * Clase EJB para el mantenimiento de la plantilla.
     */

    private final PlantillaMantenimientoDetalleDocumentoCorreoLocalDao ejbPlantilla;

    /**
     * Objeto que permite utilizar el utilitario de creación de métodos word
     */
    private final UWord uword;

    public PlantillaMantenimientoDetalleDocumentoCorreoServiceImpl(PlantillaMantenimientoDetalleDocumentoCorreoLocalDao ejbPlantilla, UWord uword) {
        this.ejbPlantilla = ejbPlantilla;
        this.uword = uword;
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    /**
     * Método que permite realizar una consulta de las plantillas.
     *
     * @param plantillaBusqueda datos de la plantilla a buscar, tipo PlantillaMantenimiento.
     * @param pagina            pagina de busqueda, tipo int.
     * @param nroRegistros      numero de registros, tipo int
     * @return resultado resultado de la busqueda, tipo ResultadoBusquedaPlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaPlantillaMantenimiento consultarPlantillasDocumento(PlantillaMantenimiento plantillaBusqueda, int pagina, int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idPlantillaMantenimiento", plantillaBusqueda.getIdPlantillaMantenimiento());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            int cantidadRegistrosBusquedaInmueble = obtenerCantidadRegistrosBusquedaPlantilla(plantillaBusqueda);
            List<PlantillaMantenimiento> listaPlantillas = obtenerPlantillas(parametrosConsulta);

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistrosBusquedaInmueble);

            ResultadoBusquedaPlantillaMantenimiento resultado = new ResultadoBusquedaPlantillaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPlantilla(listaPlantillas);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar las plantillas del documento.
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaPlantillas lista registros con los datos de las plantillas, tipo List<PlantillaMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<PlantillaMantenimiento> obtenerPlantillas(Map<String, Object> parametros) throws Exception {
        List<PlantillaMantenimiento> listaPlantillas;
        try {
            listaPlantillas = ejbPlantilla.consultarPlantillasDocumento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaPlantillas;
    }

    /**
     * Método que permite obtener la cantidad de registros de la plantilla.
     *
     * @param busquedaPlantilla bean con los datos de búsqueda de la plantilla, tipo PlantillaMantenimiento.
     * @return cantidad número de registros de la plantilla, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadRegistrosBusquedaPlantilla(PlantillaMantenimiento busquedaPlantilla) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPlantilla.obtenerCantidadRegistrosBusquedaPlantilla(busquedaPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite obtener los datos de la plantilla.
     *
     * @param obtenerPlantilla datos de la plantilla, tipo PlantillaMantenimiento.
     * @return obtenerPlantillaDocu bean con los datos de la plantilla, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PlantillaMantenimiento obtenerDatosPlantilla(PlantillaMantenimiento obtenerPlantilla) throws Exception {
        PlantillaMantenimiento obtenerPlantillaDocu;
        try {
            obtenerPlantillaDocu = ejbPlantilla.obtenerDatosPlantilla(obtenerPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return obtenerPlantillaDocu;
    }

    /**
     * Método que permite guardar la plantilla.
     *
     * @param registrarPlantilla datos de la plantilla a guardar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void registrarPlantilla(PlantillaMantenimiento registrarPlantilla) throws Exception {
        try {
            ejbPlantilla.registrarPlantilla(registrarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar la plantilla.
     *
     * @param modificarPlantilla bean con los datos de la plantilla a modificar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarPlantilla(PlantillaMantenimiento modificarPlantilla) throws Exception {
        try {
            ejbPlantilla.modificarPlantilla(modificarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite eliminar la plantilla.
     *
     * @param eliminarPlantilla datos de la plantilla a eliminar, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarPlantilla(PlantillaMantenimiento eliminarPlantilla) throws Exception {
        try {
            ejbPlantilla.eliminarPlantilla(eliminarPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de secciones inicio
     *
     * @param codigoPlantilla código de la plantilla, tipo Integer.
     * @param codigoSeccion   código de la seccion de la plantilla, tipo String.
     * @return cantidad de secciones según los filtros de busqueda, tipo int
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadPlantillaSeccion(Integer codigoPlantilla, String codigoSeccion) throws Exception {
        try {
            return ejbPlantilla.obtenerCantidadPlantillaSeccion(codigoPlantilla, codigoSeccion);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite generar una plantilla de convivencia.
     *
     * @param idPlantillaMantenimiento identificador de la plantilla a descargar, tipo Integer.
     * @return documento word generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarPlantillaCorreoNotificacion(Integer idPlantillaMantenimiento) throws Exception {
        try {
            PlantillaMantenimiento plantillaInicioContrato = ejbPlantilla.obtenerSeccionInicioPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaTextoContrato = ejbPlantilla.obtenerSeccionTextoPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaCierreContrato = ejbPlantilla.obtenerSeccionCierrePlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaFirmaContrato = ejbPlantilla.obtenerSeccionFirmaPlantilla(idPlantillaMantenimiento);
            String textoInicioContrato = null;
            String textoTextoCorreo = null;
            String textoCierreContrato = null;
            String textoNombreFirma = null;
            String textoCargoFirma = null;
            if (plantillaInicioContrato != null) {
                textoInicioContrato = plantillaInicioContrato.getParrafoSeccion();
            }

            if (plantillaTextoContrato != null) {
                textoTextoCorreo = plantillaTextoContrato.getParrafoSeccion();
            }

            if (plantillaCierreContrato != null) {
                textoCierreContrato = plantillaCierreContrato.getParrafoSeccion();
            }

            if (plantillaFirmaContrato != null) {
                textoNombreFirma = plantillaFirmaContrato.getNombreFirma();
                textoCargoFirma = plantillaFirmaContrato.getCargoFirma();
            }

            List<PlantillaMantenimiento> listaClausulas = ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);


            return generarDocumentoCorreoNotificacion(textoInicioContrato, textoTextoCorreo, textoCierreContrato, listaClausulas, textoNombreFirma, textoCargoFirma);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que crea la firma de la plantilla de las normas de convivencia.
     * @param documento objeto del documento word, tipo XWPFDocument.
     */
	/*private void crearFirmaPlantillaConvivencia(XWPFDocument documento)throws Exception{
		try {
			XWPFTable tablaFirma = documento.createTable(4,2);

		      XWPFTableRow tablaFilaUno = tablaFirma.getRow(0);

		      XWPFTableCell celdaUnoFilaUno = tablaFilaUno.getCell(0);
		      XWPFParagraph parrafoCeldaUnoFilaUno = celdaUnoFilaUno.getParagraphs().getFirst();
		      parrafoCeldaUnoFilaUno.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaUnoFilaUno = uword.formatoParrafo(parrafoCeldaUnoFilaUno);
		      parrafoCeldaUnoFilaUno.createRun().setText("--------------------------");

		      XWPFTableCell celdaDosFilaUno = tablaFilaUno.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaUno = celdaDosFilaUno.getParagraphs().getFirst();
		      parrafoCeldaDosFilaUno.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaUno = uword.formatoParrafo(parrafoCeldaDosFilaUno);
		      parrafoCeldaDosFilaUno.createRun().setText(" ");

		      XWPFTableRow tablaFilaDos = tablaFirma.getRow(1);
		      XWPFTableCell celdaUnoFilaDos = tablaFilaDos.getCell(0);
		      XWPFParagraph parrafoCeldaUnoFilaDos = celdaUnoFilaDos.getParagraphs().getFirst();
		      parrafoCeldaUnoFilaDos.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaUnoFilaDos = uword.formatoParrafo(parrafoCeldaUnoFilaDos);
		      XWPFRun runParrafoCeldaUnoFilaDos = parrafoCeldaUnoFilaDos.createRun();
		      runParrafoCeldaUnoFilaDos.setBold(true);
		      runParrafoCeldaUnoFilaDos.setText("EL ARRENDATARIO");

		      XWPFTableCell celdaDosFilaDos = tablaFilaDos.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaDos = celdaDosFilaDos.getParagraphs().getFirst();
		      parrafoCeldaDosFilaDos.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaDos = uword.formatoParrafo(parrafoCeldaUnoFilaDos);
		      XWPFRun runParrafoCeldaDosFilaDos = parrafoCeldaDosFilaDos.createRun();
		      runParrafoCeldaDosFilaDos.setBold(true);
		      runParrafoCeldaDosFilaDos.setText(" ");

		      XWPFTableRow tablaFilaTres = tablaFirma.getRow(2);
		      XWPFTableCell celdaUnoFilaTres = tablaFilaTres.getCell(0);
		      XWPFParagraph parrafoCeldaUnoFilaTres = celdaUnoFilaTres.getParagraphs().getFirst();
		      parrafoCeldaUnoFilaTres.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaUnoFilaTres = uword.formatoParrafo(parrafoCeldaUnoFilaTres);
		      parrafoCeldaUnoFilaTres.createRun().setText("LUIS ENRIQUE MÁRQUEZ ZEPILLI");

		      XWPFTableCell celdaDosFilaTres = tablaFilaTres.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaTres = celdaDosFilaTres.getParagraphs().getFirst();
		      parrafoCeldaDosFilaTres.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaTres = uword.formatoParrafo(parrafoCeldaDosFilaTres);
		      parrafoCeldaDosFilaTres.createRun().setText(" ");

		      XWPFTableRow tablaFilaCuatro = tablaFirma.getRow(3);

		      XWPFTableCell celdaUnoFilaCuatro = tablaFilaCuatro.getCell(0);
		      XWPFParagraph parrafoCeldaUnoFilaCuatro = celdaUnoFilaCuatro.getParagraphs().getFirst();
		      parrafoCeldaUnoFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaUnoFilaCuatro = uword.formatoParrafo(parrafoCeldaUnoFilaCuatro);
		      parrafoCeldaUnoFilaCuatro.createRun().setText("D.N.I N° 00000000");

		      XWPFTableCell celdaDosFilaCuatro = tablaFilaCuatro.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaCuatro = celdaDosFilaCuatro.getParagraphs().getFirst();
		      parrafoCeldaDosFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaCuatro = uword.formatoParrafo(parrafoCeldaDosFilaCuatro);
		      parrafoCeldaDosFilaCuatro.createRun().setText(" ");

		      CTTbl tablaBl = tablaFirma.getCTTbl();
		      CTTblPr tablaPr = tablaBl.getTblPr();
		      tablaPr.unsetTblBorders();
		      CTTblWidth  tablaAncho = tablaPr.getTblW();
		      tablaAncho.setW(BigInteger.valueOf(5000));
		      tablaAncho.setType(STTblWidth.PCT);
		      tablaPr.setTblW(tablaAncho);

		      CTJc tablaJc = tablaPr.addNewJc();
		      tablaJc.setVal(STJc.CENTER);
		      tablaPr.setJc(tablaJc);

		      tablaBl.setTblPr(tablaPr);
		} catch (Exception excepcion) {
			throw new Exception(excepcion);
		}

	}*/

    /**
     * Método que genera el documento de normas de convivencia en formato word.
     *
     * @param textoInicioContrato texto html de inicio del contrato, tipo String.
     * @param textoCierreContrato texto html de cierre del contrato tipo String.
     * @param listaClausulas      lista de objetos de las clausulas a llenar, tipo List<PlantillaMantenimiento>.
     * @return bytes word con el contrato generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    private ByteArrayOutputStream generarDocumentoCorreoNotificacion(String textoInicioContrato, String textoTextoContrato, String textoCierreContrato, List<PlantillaMantenimiento> listaClausulas, String textoNombreFirma, String textoCargoFirma) throws Exception {
        try {
            XWPFDocument documento = new XWPFDocument();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

			/*XWPFParagraph tituloContrato = documento.createParagraph();
			tituloContrato.setAlignment(ParagraphAlignment.CENTER);
			uword.formatoParrafo(tituloContrato);
			XWPFRun runTituloContrato = uword.crearRun(tituloContrato);
			runTituloContrato.setBold(true);
			runTituloContrato.setText("NORMAS DE CONVIVENCIA");*/

            if (textoInicioContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docInicioContrato = Jsoup.parse(textoInicioContrato, "", Parser.htmlParser());
                Elements elementosInicioContrato = docInicioContrato.getElementsByTag("body");
                escribirDocumentos(elementosInicioContrato, documento);
            }
            if (textoTextoContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docTextoContrato = Jsoup.parse(textoTextoContrato, "", Parser.htmlParser());
                Elements elementosTextoContrato = docTextoContrato.getElementsByTag("body");
                escribirDocumentos(elementosTextoContrato, documento);
            }
            if (!listaClausulas.isEmpty()) {

                for (PlantillaMantenimiento clausula : listaClausulas) {
                    uword.parrafoEnBlanco(documento);
                    XWPFParagraph parrafoTitulo = documento.createParagraph();
                    uword.formatoParrafo(parrafoTitulo);
                    XWPFRun runOrdenTitulo = uword.crearRun(parrafoTitulo);
                    runOrdenTitulo.setBold(true);

                    String ordenClausula = clausula.getDescOrdenSeccion();
                    String tituloClausula = clausula.getNombreSeccion();
                    runOrdenTitulo.setUnderline(UnderlinePatterns.SINGLE);
                    runOrdenTitulo.setText(ordenClausula);

                    XWPFRun runTitulo = uword.crearRun(parrafoTitulo);
                    runTitulo.setBold(true);
                    runTitulo.setUnderline(UnderlinePatterns.NONE);
                    runTitulo.setText(":");
                    runTitulo.addTab();
                    runTitulo.setText(tituloClausula);


                    uword.parrafoEnBlanco(documento);
                    String textoClausula = clausula.getParrafoSeccion();
                    Document docClausulaContrato = Jsoup.parse(textoClausula, "", Parser.htmlParser());
                    Elements elementosClausulaContrato = docClausulaContrato.getElementsByTag("body");
                    escribirDocumentos(elementosClausulaContrato, documento);
                }
            }

            if (textoCierreContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docCierreContrato = Jsoup.parse(textoCierreContrato, "", Parser.htmlParser());
                Elements elementosCierreContrato = docCierreContrato.getElementsByTag("body");
                escribirDocumentos(elementosCierreContrato, documento);
            }


            uword.parrafoEnBlanco(documento);
            uword.parrafoEnBlanco(documento);

            crearFirmaCorreoNotificacion(documento, textoNombreFirma, textoCargoFirma);

            documento.write(bytes);
            documento.close();

            return bytes;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite generar una plantilla de contrato
     *
     * @param idPlantillaMantenimiento identificador de la plantilla a descargar, tipo Integer.
     * @return documento word generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarPlantillaCorreo(Integer idPlantillaMantenimiento) throws Exception {
        try {
            PlantillaMantenimiento plantillaInicioContrato = ejbPlantilla.obtenerSeccionInicioPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaTextoContrato = ejbPlantilla.obtenerSeccionTextoPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaCierreContrato = ejbPlantilla.obtenerSeccionCierrePlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaFirmaContrato = ejbPlantilla.obtenerSeccionFirmaPlantilla(idPlantillaMantenimiento);
            String textoInicioContrato = null;
            String textoCierreContrato = null;
            String textoTextoContrato = null;
            String textoNombreFirma = null;
            String textoCargoFirma = null;
            if (plantillaInicioContrato != null) {
                textoInicioContrato = plantillaInicioContrato.getParrafoSeccion();
            }

            if (plantillaTextoContrato != null) {
                textoTextoContrato = plantillaTextoContrato.getParrafoSeccion();
            }

            if (plantillaCierreContrato != null) {
                textoCierreContrato = plantillaCierreContrato.getParrafoSeccion();
            }

            if (plantillaFirmaContrato != null) {
                textoNombreFirma = plantillaFirmaContrato.getNombreFirma();
                textoCargoFirma = plantillaFirmaContrato.getCargoFirma();
            }

            List<PlantillaMantenimiento> listaClausulas = ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);

            return generarWordCorreo(textoInicioContrato, textoTextoContrato, textoCierreContrato, listaClausulas, textoNombreFirma, textoCargoFirma);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que genera el contrato en formato word.
     *
     * @param textoInicioContrato texto html de inicio del contrato, tipo String.
     * @param textoTextoContrato  texto html de Texto del contrato tipo String.
     * @param textoCierreContrato texto html de cierre del contrato tipo String.
     * @param listaClausulas      lista de objetos de las clausulas a llenar, tipo List<PlantillaMantenimiento>.
     * @return bytes word con el contrato generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    private ByteArrayOutputStream generarWordCorreo(String textoInicioContrato, String textoTextoContrato, String textoCierreContrato, List<PlantillaMantenimiento> listaClausulas, String textoNombreFirma, String textoCargoFirma) throws Exception {
        try {
            XWPFDocument documento
                    = new XWPFDocument();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

			/*XWPFParagraph tituloContrato = documento.createParagraph();
			tituloContrato.setAlignment(ParagraphAlignment.CENTER);
			uword.formatoParrafo(tituloContrato);
			XWPFRun runTituloContrato = uword.crearRun(tituloContrato);
			runTituloContrato.setBold(true);
			runTituloContrato.setText("CONTRATO DE ARRENDAMIENTO");

			XWPFParagraph subtituloContrato = documento.createParagraph();
			subtituloContrato.setAlignment(ParagraphAlignment.CENTER);
			uword.formatoParrafo(subtituloContrato);
			XWPFRun runSubtituloContrato = uword.crearRun(subtituloContrato);
			runSubtituloContrato.setText("N° 000000X");*/

            if (textoInicioContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docInicioContrato = Jsoup.parse(textoInicioContrato, "", Parser.htmlParser());
                Elements elementosInicioContrato = docInicioContrato.getElementsByTag("body");
                escribirDocumentos(elementosInicioContrato, documento);
            }

            if (textoTextoContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docTextoContrato = Jsoup.parse(textoTextoContrato, "", Parser.htmlParser());
                Elements elementosTextoContrato = docTextoContrato.getElementsByTag("body");
                escribirDocumentos(elementosTextoContrato, documento);
            }

            if (!listaClausulas.isEmpty()) {

                for (PlantillaMantenimiento clausula : listaClausulas) {
                    uword.parrafoEnBlanco(documento);
                    XWPFParagraph parrafoTitulo = documento.createParagraph();
                    uword.formatoParrafo(parrafoTitulo);
                    XWPFRun runOrdenTitulo = uword.crearRun(parrafoTitulo);
                    runOrdenTitulo.setBold(true);

                    String ordenClausula = clausula.getDescOrdenSeccion();
                    String tituloClausula = clausula.getNombreSeccion();
                    runOrdenTitulo.setUnderline(UnderlinePatterns.SINGLE);
                    runOrdenTitulo.setText(ordenClausula);

                    XWPFRun runTitulo = uword.crearRun(parrafoTitulo);
                    runTitulo.setBold(true);
                    runTitulo.setUnderline(UnderlinePatterns.NONE);
                    runTitulo.setText(":");
                    runTitulo.addTab();
                    runTitulo.setText(tituloClausula + ".-");


                    uword.parrafoEnBlanco(documento);
                    String textoClausula = clausula.getParrafoSeccion();
                    Document docClausulaContrato = Jsoup.parse(textoClausula, "", Parser.htmlParser());
                    Elements elementosClausulaContrato = docClausulaContrato.getElementsByTag("body");
                    escribirDocumentos(elementosClausulaContrato, documento);
                }
            }

            if (textoCierreContrato != null) {
                uword.parrafoEnBlanco(documento);
                Document docCierreContrato = Jsoup.parse(textoCierreContrato, "", Parser.htmlParser());
                Elements elementosCierreContrato = docCierreContrato.getElementsByTag("body");
                escribirDocumentos(elementosCierreContrato, documento);
            }


            uword.parrafoEnBlanco(documento);

            crearFirmaCorreo(documento, textoNombreFirma, textoCargoFirma);

            documento.write(bytes);
            documento.close();

            return bytes;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite escribir el contenido del parrafo en word.
     *
     * @param cuerpo    objeto con el texto del html, tipo Elements.
     * @param documento objeto que instancia al documento word, tipo XWPFDocument.
     * @throws Exception excepción generada en caso de error.
     */
    private void escribirDocumentos(Elements cuerpo, XWPFDocument documento) throws Exception {
        try {
            for (Element elemento : cuerpo) {
                uword.escribirContenido((org.w3c.dom.Element) elemento, documento);
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }


    }

    /**
     * Método que crea la firma de la plantilla.
     *
     * @param documento objeto del documento word, tipo XWPFDocument.
     */
    private void crearFirmaPlantilla(XWPFDocument documento, String textoNombreFirma, String textoCargoFirma) throws Exception {
        try {
            XWPFTable tablaFirma = documento.createTable(5, 2);

            XWPFTableRow tablaFilaUno = tablaFirma.getRow(0);

            XWPFTableCell celdaUnoFilaUno = tablaFilaUno.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaUno = celdaUnoFilaUno.getParagraphs().getFirst();
            parrafoCeldaUnoFilaUno.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaUno = uword.formatoParrafo(parrafoCeldaUnoFilaUno);
            parrafoCeldaUnoFilaUno.createRun().setText("--------------------------");


		      /*XWPFTableCell celdaDosFilaUno = tablaFilaUno.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaUno = celdaDosFilaUno.getParagraphs().getFirst();
		      parrafoCeldaDosFilaUno.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaUno = uword.formatoParrafo(parrafoCeldaDosFilaUno);
		      parrafoCeldaDosFilaUno.createRun().setText("---------------------------");*/

            XWPFTableRow tablaFilaDos = tablaFirma.getRow(1);

            XWPFTableCell celdaUnoFilaDos = tablaFilaDos.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaDos = celdaUnoFilaDos.getParagraphs().getFirst();
            parrafoCeldaUnoFilaDos.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaDos = uword.formatoParrafo(parrafoCeldaUnoFilaDos);
            XWPFRun runParrafoCeldaUnoFilaDos = parrafoCeldaUnoFilaDos.createRun();
            runParrafoCeldaUnoFilaDos.setBold(true);
            runParrafoCeldaUnoFilaDos.setText(textoNombreFirma);

		      /*XWPFTableCell celdaDosFilaDos = tablaFilaDos.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaDos = celdaDosFilaDos.getParagraphs().getFirst();
		      parrafoCeldaDosFilaDos.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaDos = uword.formatoParrafo(parrafoCeldaDosFilaDos);
		      XWPFRun runParrafoCeldaDosFilaDos = parrafoCeldaDosFilaDos.createRun();
		      runParrafoCeldaDosFilaDos.setBold(true);
		      runParrafoCeldaDosFilaDos.setText("LA/EL ARRENDATARIA/O");*/

            XWPFTableRow tablaFilaTres = tablaFirma.getRow(2);

            XWPFTableCell celdaUnoFilaTres = tablaFilaTres.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaTres = celdaUnoFilaTres.getParagraphs().getFirst();
            parrafoCeldaUnoFilaTres.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaTres = uword.formatoParrafo(parrafoCeldaUnoFilaTres);
            parrafoCeldaUnoFilaTres.createRun().setText(textoCargoFirma);

		      /*WPFTableCell celdaDosFilaTres = tablaFilaTres.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaTres = celdaDosFilaTres.getParagraphs().getFirst();
		      parrafoCeldaDosFilaTres.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaTres = uword.formatoParrafo(parrafoCeldaDosFilaTres);
		      parrafoCeldaDosFilaTres.createRun().setText("NOMBRE ARRENDATARIO");*/

            XWPFTableRow tablaFilaCuatro = tablaFirma.getRow(3);

            XWPFTableCell celdaUnoFilaCuatro = tablaFilaCuatro.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaCuatro = celdaUnoFilaCuatro.getParagraphs().getFirst();
            parrafoCeldaUnoFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaCuatro = uword.formatoParrafo(parrafoCeldaUnoFilaCuatro);
            parrafoCeldaUnoFilaCuatro.createRun().setText("Oficina de Normalizacion Previsional");

		      /*XWPFTableCell celdaDosFilaCuatro = tablaFilaCuatro.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaCuatro = celdaDosFilaCuatro.getParagraphs().getFirst();
		      parrafoCeldaDosFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaCuatro = uword.formatoParrafo(parrafoCeldaDosFilaCuatro);
		      parrafoCeldaDosFilaCuatro.createRun().setText("");*/

            XWPFTableRow tablaFilaCinco = tablaFirma.getRow(4);

            XWPFTableCell celdaUnoFilaCinco = tablaFilaCinco.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaCinco = celdaUnoFilaCinco.getParagraphs().getFirst();
            parrafoCeldaUnoFilaCinco.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaCinco = uword.formatoParrafo(parrafoCeldaUnoFilaCinco);
            parrafoCeldaUnoFilaCinco.createRun().setText("Secretaría Técnica del FCR");

		      /*XWPFTableCell celdaDosFilaCinco = tablaFilaCinco.getCell(1);
		      XWPFParagraph parrafoCeldaDosFilaCinco = celdaDosFilaCinco.getParagraphs().getFirst();
		      parrafoCeldaDosFilaCinco.setAlignment(ParagraphAlignment.CENTER);
		      parrafoCeldaDosFilaCinco = uword.formatoParrafo(parrafoCeldaDosFilaCinco);
		      parrafoCeldaDosFilaCinco.createRun().setText("");*/


            CTTbl tablaBl = tablaFirma.getCTTbl();
            CTTblPr tablaPr = tablaBl.getTblPr();
            tablaPr.unsetTblBorders();
            CTTblWidth tablaAncho = tablaPr.getTblW();
            tablaAncho.setW(BigInteger.valueOf(5000));
            tablaAncho.setType(STTblWidth.PCT);
            tablaPr.setTblW(tablaAncho);

            CTJc tablaJc = (CTJc) tablaPr.addNewJc();
            tablaJc.setVal(STJc.CENTER);
            tablaPr.setJc((CTJcTable) tablaJc);

            tablaBl.setTblPr(tablaPr);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que crea la firma de la plantilla.
     *
     * @param documento objeto del documento word, tipo XWPFDocument.
     */
    private void crearFirmaCorreo(XWPFDocument documento, String textoNombreFirma, String textoCargoFirma) throws Exception {
        try {
            XWPFTable tablaFirma = documento.createTable(5, 2);

            XWPFTableRow tablaFilaUno = tablaFirma.getRow(0);

            XWPFTableCell celdaUnoFilaUno = tablaFilaUno.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaUno = celdaUnoFilaUno.getParagraphs().getFirst();
            parrafoCeldaUnoFilaUno.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaUno = uword.formatoParrafo(parrafoCeldaUnoFilaUno);
            parrafoCeldaUnoFilaUno.createRun().setText("--------------------------");

            XWPFTableRow tablaFilaDos = tablaFirma.getRow(1);

            XWPFTableCell celdaUnoFilaDos = tablaFilaDos.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaDos = celdaUnoFilaDos.getParagraphs().getFirst();
            parrafoCeldaUnoFilaDos.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaDos = uword.formatoParrafo(parrafoCeldaUnoFilaDos);
            XWPFRun runParrafoCeldaUnoFilaDos = parrafoCeldaUnoFilaDos.createRun();
            runParrafoCeldaUnoFilaDos.setBold(true);
            runParrafoCeldaUnoFilaDos.setText(textoNombreFirma);

            XWPFTableRow tablaFilaTres = tablaFirma.getRow(2);

            XWPFTableCell celdaUnoFilaTres = tablaFilaTres.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaTres = celdaUnoFilaTres.getParagraphs().getFirst();
            parrafoCeldaUnoFilaTres.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaTres = uword.formatoParrafo(parrafoCeldaUnoFilaTres);
            parrafoCeldaUnoFilaTres.createRun().setText(textoCargoFirma);

            XWPFTableRow tablaFilaCuatro = tablaFirma.getRow(3);

            XWPFTableCell celdaUnoFilaCuatro = tablaFilaCuatro.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaCuatro = celdaUnoFilaCuatro.getParagraphs().getFirst();
            parrafoCeldaUnoFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaCuatro = uword.formatoParrafo(parrafoCeldaUnoFilaCuatro);
            parrafoCeldaUnoFilaCuatro.createRun().setText("Jr. Bolivia 109 - piso 24 - Tlf.634 2222 Anexo 2593");

            CTTbl tablaBl = tablaFirma.getCTTbl();
            CTTblPr tablaPr = tablaBl.getTblPr();
            tablaPr.unsetTblBorders();
            CTTblWidth tablaAncho = tablaPr.getTblW();
            tablaAncho.setW(BigInteger.valueOf(5000));
            tablaAncho.setType(STTblWidth.PCT);
            tablaPr.setTblW(tablaAncho);

            CTJc tablaJc = (CTJc) tablaPr.addNewJc();
            tablaJc.setVal(STJc.CENTER);
            tablaPr.setJc((CTJcTable) tablaJc);

            tablaBl.setTblPr(tablaPr);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que crea la firma de la plantilla.
     *
     * @param documento objeto del documento word, tipo XWPFDocument.
     */
    private void crearFirmaCorreoNotificacion(XWPFDocument documento, String textoNombreFirma, String textoCargoFirma) throws Exception {
        try {
            XWPFTable tablaFirma = documento.createTable(5, 2);

            XWPFTableRow tablaFilaUno = tablaFirma.getRow(0);

            XWPFTableCell celdaUnoFilaUno = tablaFilaUno.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaUno = celdaUnoFilaUno.getParagraphs().getFirst();
            parrafoCeldaUnoFilaUno.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaUno = uword.formatoParrafo(parrafoCeldaUnoFilaUno);
            parrafoCeldaUnoFilaUno.createRun().setText("--------------------------");

            XWPFTableRow tablaFilaDos = tablaFirma.getRow(1);

            XWPFTableCell celdaUnoFilaDos = tablaFilaDos.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaDos = celdaUnoFilaDos.getParagraphs().getFirst();
            parrafoCeldaUnoFilaDos.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaDos = uword.formatoParrafo(parrafoCeldaUnoFilaDos);
            XWPFRun runParrafoCeldaUnoFilaDos = parrafoCeldaUnoFilaDos.createRun();
            runParrafoCeldaUnoFilaDos.setBold(true);
            runParrafoCeldaUnoFilaDos.setText(textoNombreFirma);

            XWPFTableRow tablaFilaTres = tablaFirma.getRow(2);

            XWPFTableCell celdaUnoFilaTres = tablaFilaTres.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaTres = celdaUnoFilaTres.getParagraphs().getFirst();
            parrafoCeldaUnoFilaTres.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaTres = uword.formatoParrafo(parrafoCeldaUnoFilaTres);
            parrafoCeldaUnoFilaTres.createRun().setText(textoCargoFirma);

            XWPFTableRow tablaFilaCuatro = tablaFirma.getRow(3);

            XWPFTableCell celdaUnoFilaCuatro = tablaFilaCuatro.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaCuatro = celdaUnoFilaCuatro.getParagraphs().getFirst();
            parrafoCeldaUnoFilaCuatro.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaCuatro = uword.formatoParrafo(parrafoCeldaUnoFilaCuatro);
            parrafoCeldaUnoFilaCuatro.createRun().setText("Oficina de Normalizacion Previsional");

            XWPFTableRow tablaFilaCinco = tablaFirma.getRow(4);

            XWPFTableCell celdaUnoFilaCinco = tablaFilaCinco.getCell(0);
            XWPFParagraph parrafoCeldaUnoFilaCinco = celdaUnoFilaCinco.getParagraphs().getFirst();
            parrafoCeldaUnoFilaCinco.setAlignment(ParagraphAlignment.CENTER);
            parrafoCeldaUnoFilaCinco = uword.formatoParrafo(parrafoCeldaUnoFilaCinco);
            parrafoCeldaUnoFilaCinco.createRun().setText("Jr. Bolivia 109 - piso 24 - Tlf.634 2222 Anexo 2556,2593,2477");

            CTTbl tablaBl = tablaFirma.getCTTbl();
            CTTblPr tablaPr = tablaBl.getTblPr();
            tablaPr.unsetTblBorders();
            CTTblWidth tablaAncho = tablaPr.getTblW();
            tablaAncho.setW(BigInteger.valueOf(5000));
            tablaAncho.setType(STTblWidth.PCT);
            tablaPr.setTblW(tablaAncho);

            CTJc tablaJc = (CTJc) tablaPr.addNewJc();
            tablaJc.setVal(STJc.CENTER);
            tablaPr.setJc((CTJcTable) tablaJc);

            tablaBl.setTblPr(tablaPr);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que obtiene las clausulas según el tipo de plantilla indicada
     *
     * @param idPlantillaMantenimiento identificador del tipo de plantilla, tipo Integer.
     * @return lista de plantillas de clausulas, tipo List<PlantillaMantenimiento>.
     * @throws Exception excepcion generada en caso de error.
     */
    @Override
    public List<PlantillaMantenimiento> obtenerListaClausulasPlantilla(Integer idPlantillaMantenimiento) throws Exception {
        try {
            return ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que obtiene los número de orden de las clausulas según el tipo de plantilla indicada
     *
     * @param idPlantillaMantenimiento identificador del tipo de plantilla, tipo Integer.
     * @return listaOrden lista de numeros de orden de las clausulas, tipo List<String>.
     * @throws Exception excepcion generada en caso de error.
     */
    @Override
    public List<String> obtenerOrdenClausulasPlantilla(Integer idPlantillaMantenimiento) throws Exception {
        try {
            List<PlantillaMantenimiento> listaPlantillaContrato = ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);
            List<String> listaOrden = new ArrayList<>();
            for (PlantillaMantenimiento plantilla : listaPlantillaContrato) {
                String orden = plantilla.getOrdenSeccion();
                listaOrden.add(orden);
            }
            return listaOrden;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene los números de orden de las clausulas según el tipo de plantilla indicada para modificacion
     *
     * @param idPlantillaMantenimiento identificador del tipo de plantilla, tipo Integer.
     * @param identificador            id de la plantilla a modificar, tipo Integer.
     * @return listaOrden lista de numeros de orden de las clausulas, tipo List<String>
     * @throws Exception excepcion generada en caso de error.
     */
    @Override
    public List<String> obtenerOrdenClausulasPlantillaModificacion(Integer idPlantillaMantenimiento, Integer identificador) throws Exception {
        try {
            List<PlantillaMantenimiento> listaPlantillaContrato = ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);
            List<String> listaOrden = new ArrayList<>();
            for (PlantillaMantenimiento plantilla : listaPlantillaContrato) {
                if (!identificador.equals(plantilla.getIdDetallePlantillaMante())) {
                    String orden = plantilla.getOrdenSeccion();
                    listaOrden.add(orden);
                }
            }
            return listaOrden;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene los datos del tipo de plantilla según el identificador
     *
     * @param idPlantillaMantenimiento identificador del tipo de plantilla, tipo Integer.
     * @return datosObtenidos datos del tipo de plantilla del documento, tipo PlantillaMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public PlantillaMantenimiento obtenerTipoPlantilla(Integer idPlantillaMantenimiento) throws Exception {
        try {
            return ejbPlantilla.obtenerTipoPlantilla(idPlantillaMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene el id de la plantilla según el identificador.
     *
     * @param datosPlantilla bean con los datos de la plantilla, tipo PlantillaMantenimiento.
     * @return idPlantilla identificador de la plantilla, tipo Integer.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public Integer obtenerIdPlantilla(PlantillaMantenimiento datosPlantilla) throws Exception {

        try {
            return ejbPlantilla.obtenerIdPlantilla(datosPlantilla);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite generar una plantilla de adendas
     *
     * @param idPlantillaMantenimiento identificador de la plantilla a descargar, tipo Integer.
     * @return documento word generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarPlantillaDocumento(Integer idPlantillaMantenimiento, ServletContext context) throws Exception {
        try {
            PlantillaMantenimiento plantillaAnioCabeAdenda = ejbPlantilla.obtenerSeccionAnioCabeceraPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaInicioAdenda = ejbPlantilla.obtenerSeccionInicioPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaTextoDocumento = ejbPlantilla.obtenerSeccionTextoPlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaCierreAdenda = ejbPlantilla.obtenerSeccionCierrePlantilla(idPlantillaMantenimiento);
            PlantillaMantenimiento plantillaFirmaAdenda = ejbPlantilla.obtenerSeccionFirmaPlantilla(idPlantillaMantenimiento);

            String textoAnioCabeAdenda = null;
            String textoInicioAdenda = null;
            String textoTextoDocumento = null;
            String textoCierreAdenda = null;
            String textoNombreFirma = null;
            String cargoNombreFirma = null;
            if (plantillaAnioCabeAdenda != null) {
                textoAnioCabeAdenda = plantillaAnioCabeAdenda.getParrafoSeccion();
            }

            if (plantillaInicioAdenda != null) {
                textoInicioAdenda = plantillaInicioAdenda.getParrafoSeccion();
            }

            if (plantillaTextoDocumento != null) {
                textoTextoDocumento = plantillaTextoDocumento.getParrafoSeccion();
            }

            if (plantillaCierreAdenda != null) {
                textoCierreAdenda = plantillaCierreAdenda.getParrafoSeccion();
            }

            if (plantillaFirmaAdenda != null) {
                textoNombreFirma = plantillaFirmaAdenda.getNombreFirma();
                cargoNombreFirma = plantillaFirmaAdenda.getCargoFirma();
            }

            List<PlantillaMantenimiento> listaClausulas = ejbPlantilla.obtenerListaClausulasPlantilla(idPlantillaMantenimiento);


            return generarWordDocumento(textoAnioCabeAdenda, textoInicioAdenda, textoTextoDocumento, textoCierreAdenda, listaClausulas, textoNombreFirma, cargoNombreFirma, context);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que genera el contrato de adendas en formato word.
     *
     * @param textoInicioAdenda texto html de inicio del contrato de adenda, tipo String.
     * @param textoCierreAdenda texto html de cierre del contrato de adenda, tipo String.
     * @param listaClausulas    lista de objetos de las clausulas a llenar, tipo List<PlantillaMantenimiento>.
     * @return bytes word con el contrato generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    private ByteArrayOutputStream generarWordDocumento(String textoAnioAdenda, String textoInicioAdenda, String textoTextoDocumento, String textoCierreAdenda, List<PlantillaMantenimiento> listaClausulas, String textoNombreFirma, String textoCargoFirma, ServletContext context) throws Exception {
        try {
            XWPFDocument documento
                    = new XWPFDocument();
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

			/*XWPFParagraph tituloContrato = documento.createParagraph();
			tituloContrato.setAlignment(ParagraphAlignment.CENTER);
			uword.formatoParrafo(tituloContrato);
			XWPFRun runTituloContrato = uword.crearRun(tituloContrato);
			runTituloContrato.setBold(true);
			runTituloContrato.setText("ADDENDUM N° 00000X");*/

            //XWPFParagraph title = documento.createParagraph();
            //XWPFRun run = title.createRun();


            // create header start
            XWPFHeaderFooterPolicy headerFooterPolicy = documento.getHeaderFooterPolicy();
            if (headerFooterPolicy == null) headerFooterPolicy = documento.createHeaderFooterPolicy();
            XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

            XWPFParagraph paragraph = header.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            //String imgFile = "logo.jpg";
            String imgFile = context.getRealPath("/WEB-INF/reportes/logo.jpg");
            FileInputStream is = new FileInputStream(imgFile);
            run.addBreak();
            //run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(200), Units.toEMU(200)); // 200x200 pixels
            run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(500), Units.toEMU(50)); // 200x200 pixels
            is.close();
            //run.setText("Decenio de las Personas con Discapacidad en el Perú");
            //run.addBreak();
            run.setText("\"" + html2text(textoAnioAdenda) + "\"");

            // create footer start
            XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

            paragraph = footer.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            run = paragraph.createRun();
            run.setText("Jr. Bolivia N° 109  Torre de Oficinas del Centro Cívico y Comercial de Lima - Piso 24 -Telef. 634-2222  www.onp.gob.pe\r\n" +
                    "");

            if (textoInicioAdenda != null) {
                uword.parrafoEnBlanco(documento);
                Document docInicioContrato = Jsoup.parse(textoInicioAdenda, "", Parser.htmlParser());
                Elements elementosInicioContrato = docInicioContrato.getElementsByTag("body");
                escribirDocumentos(elementosInicioContrato, documento);
            }
            if (textoTextoDocumento != null) {
                uword.parrafoEnBlanco(documento);
                Document docTextoContrato = Jsoup.parse(textoTextoDocumento, "", Parser.htmlParser());
                Elements elementosTextoContrato = docTextoContrato.getElementsByTag("body");
                escribirDocumentos(elementosTextoContrato, documento);
            }
            if (!listaClausulas.isEmpty()) {

                for (PlantillaMantenimiento clausula : listaClausulas) {
                    uword.parrafoEnBlanco(documento);
                    XWPFParagraph parrafoTitulo = documento.createParagraph();
                    uword.formatoParrafo(parrafoTitulo);
                    XWPFRun runOrdenTitulo = uword.crearRun(parrafoTitulo);
                    runOrdenTitulo.setBold(true);

                    String ordenClausula = clausula.getDescOrdenSeccion();
                    String tituloClausula = clausula.getNombreSeccion();
                    runOrdenTitulo.setUnderline(UnderlinePatterns.SINGLE);
                    runOrdenTitulo.setText(ordenClausula);

                    XWPFRun runTitulo = uword.crearRun(parrafoTitulo);
                    runTitulo.setBold(true);
                    runTitulo.setUnderline(UnderlinePatterns.NONE);
                    runTitulo.setText(":");
                    runTitulo.addTab();
                    runTitulo.setText(tituloClausula + ".-");

                    uword.parrafoEnBlanco(documento);
                    String textoClausula = clausula.getParrafoSeccion();
                    Document docClausulaContrato = Jsoup.parse(textoClausula, "", Parser.htmlParser());
                    Elements elementosClausulaContrato = docClausulaContrato.getElementsByTag("body");
                    escribirDocumentos(elementosClausulaContrato, documento);
                }
            }

            if (textoCierreAdenda != null) {
                uword.parrafoEnBlanco(documento);
                Document docCierreContrato = Jsoup.parse(textoCierreAdenda, "", Parser.htmlParser());
                Elements elementosCierreContrato = docCierreContrato.getElementsByTag("body");
                escribirDocumentos(elementosCierreContrato, documento);
            }


            uword.parrafoEnBlanco(documento);


            crearFirmaPlantilla(documento, textoNombreFirma, textoCargoFirma);

            documento.write(bytes);
            documento.close();

            return bytes;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }
}
