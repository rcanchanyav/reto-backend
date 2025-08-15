/**
 * Resumen.
 * Objeto               :   PresupuestoOrdenDelegateEJB.java.
 * Descripción          :   Clase delegate utilizada para la creación de los métodos para los presupuesto de ordenes de servicio.
 * Fecha de Creación    :   15/05/2022
 * PE de Creación       :   INI-900
 * Autor                :   Juan Carlos Verde
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.PresupuestoOrdenLocalDao;
import pe.gob.onp.nsai.dto.PresupuestoOrden;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.PresupuestoOrdenService;
import pe.gob.onp.nsai.util.DirectorioTaxonomia;
import pe.gob.onp.nsai.util.TaxonomiaDocumentos;
import pe.gob.onp.nsai.util.UServicios;

/**
 * Clase delegate para el mantenimiento de los presupuestos de de ordenes de
 * servicio
 */
@Service
public class PresupuestoOrdenServiceImpl implements PresupuestoOrdenService {

    /**
     * EJB con los metodos del mantenimiento de los presupuestos de orden de
     * servicio
     */

    private final PresupuestoOrdenLocalDao ejbPresupuesto;

    public PresupuestoOrdenServiceImpl(PresupuestoOrdenLocalDao ejbPresupuesto) {
        this.ejbPresupuesto = ejbPresupuesto;
    }

    /**
     * Método que permite realizar consulta a los Presupuestos Orden
     *
     * @param presupuestoOrden
     *            datos del presupuesto de orden a buscar.
     * @param pagina
     *            pagina de busqueda, tipo int.
     * @param nroRegistros
     *            numero de registros, tipo int
     * @return resultado de la busqueda, tipo ResultadoBusquedaMantenimiento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ResultadoBusquedaMantenimiento consultarPresupuestoOrden(PresupuestoOrden presupuestoOrden, int pagina,
                                                                    int nroRegistros) throws Exception {
        try {

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idInmueble", presupuestoOrden.getIdInmueble());
            parametrosConsulta.put("idInmueblePredio", presupuestoOrden.getIdInmueblePredio());
            parametrosConsulta.put("idInmuebleBloque", presupuestoOrden.getIdInmuebleBloque());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            //int cantidadRegistros = obtenerCantidadPresupuestoOrden(presupuestoOrden);
            List<PresupuestoOrden> listaPresupuestoOrden = obtenerListaPresupuestoOrden(parametrosConsulta);

            int cantidadRegistros = listaPresupuestoOrden.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que obtiene la lista de presupuesto orden.
     *
     * @param parametros
     *            parametros de busqueda, tipo Map<String,Object>.
     * @return listado de subastas, tipo List<PresupuestoOrden>
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<PresupuestoOrden> obtenerListaPresupuestoOrden(Map<String, Object> parametros) throws Exception {
        try {
            return ejbPresupuesto.obtenerListaPresupuestoOrden(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de la consulta del presupuesto orden
     * por los filtros ingresados.
     *
     * @param presupuestoOrden
     *            filtros para realizar la búsqueda del PresupuestoOrden, tipo
     *            Optional<PresupuestoOrden>.
     * @return cantidad de la consulta de inmuebles, tipo int.
     * @throws Exception
     *             excepción generada en caso de error.
     */

    @Override
    public int obtenerCantidadPresupuestoOrden(PresupuestoOrden presupuestoOrden) throws Exception {
        int cantidad;
        try {
            cantidad = ejbPresupuesto.obtenerCantidadPresupuestoOrden(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    @Override
    public ByteArrayOutputStream generarReportePresupuestoOrdenExcel(PresupuestoOrden presupuestoOrden, int pagina,
                                                                     int nroRegistros) throws Exception {
        try {

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Presupuestos");

            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight((short) (9 * 20));

            CellStyle estiloCelda = workbook.createCellStyle();
            estiloCelda.setFont(font);
            estiloCelda.setBorderBottom(BorderStyle.MEDIUM);
            estiloCelda.setBorderLeft(BorderStyle.MEDIUM);
            estiloCelda.setBorderRight(BorderStyle.MEDIUM);
            estiloCelda.setBorderTop(BorderStyle.MEDIUM);
            estiloCelda.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCelda.setAlignment(HorizontalAlignment.CENTER);

            CellStyle estiloCabe = workbook.createCellStyle();
            estiloCabe.setFont(font);
            estiloCabe.setBorderBottom(BorderStyle.DOUBLE);
            estiloCabe.setBorderLeft(BorderStyle.DOUBLE);
            estiloCabe.setBorderRight(BorderStyle.DOUBLE);
            estiloCabe.setBorderTop(BorderStyle.DOUBLE);
            estiloCabe.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCabe.setAlignment(HorizontalAlignment.CENTER);

            /*
             * Row fila1 = sheet.createRow(0); Cell celda1 = fila1.createCell(0);
             * celda1.setCellValue("OFICINA DE NORMALIZACIÓN PREVISIONAL - ONP");
             * celda1.setCellStyle(estiloCabe); Cell celda2 = fila1.createCell(1);
             * celda2.setCellStyle(estiloCabe); Cell celda3 = fila1.createCell(2);
             * celda3.setCellStyle(estiloCabe); Cell celda4 = fila1.createCell(3);
             * celda4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN:");
             * celda4.setCellStyle(estiloCabe); Cell celda5 = fila1.createCell(4);
             * celda5.setCellStyle(estiloCabe); Cell celda6 = fila1.createCell(5);
             * celda6.setCellStyle(estiloCabe); Cell celda7 = fila1.createCell(6);
             * celda7.setCellStyle(estiloCabe); Cell celda8 = fila1.createCell(7);
             * celda8.setCellStyle(estiloCabe); Cell celda9 = fila1.createCell(8);
             * celda9.setCellValue("FECHA:"); celda9.setCellStyle(estiloCabe); Cell celda10
             * = fila1.createCell(9); celda10.setCellValue(UConvierteFecha.formatearHora(new
             * Date())); celda10.setCellStyle(estiloCabe);
             *
             * sheet.addMergedRegion(new CellRangeAddress(0,2,0,2));
             * sheet.addMergedRegion(new CellRangeAddress(0,0,3,7));
             */

            Row filaCabeceraTabla = sheet.createRow(2);
            Cell celdaCabSolicitud = filaCabeceraTabla.createCell(0);
            celdaCabSolicitud.setCellValue("SOLICITUD");
            celdaCabSolicitud.setCellStyle(estiloCabe);
            Cell celdaCabInmueble = filaCabeceraTabla.createCell(1);
            celdaCabInmueble.setCellValue("INMUEBLE");
            celdaCabInmueble.setCellStyle(estiloCabe);
            Cell celdaCabBloque = filaCabeceraTabla.createCell(2);
            celdaCabBloque.setCellValue("BLOQUE");
            celdaCabBloque.setCellStyle(estiloCabe);
            Cell celdaCabPredio = filaCabeceraTabla.createCell(3);
            celdaCabPredio.setCellValue("PREDIO");
            celdaCabPredio.setCellStyle(estiloCabe);
            celdaCabPredio.setCellStyle(estiloCabe);
            Cell celdaCabNumOrden = filaCabeceraTabla.createCell(4);
            celdaCabNumOrden.setCellValue("NUMORDEN");
            celdaCabNumOrden.setCellStyle(estiloCabe);


            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idInmueble", presupuestoOrden.getIdInmueble());
            parametros.put("idInmueblePredio", presupuestoOrden.getIdInmueblePredio());
            parametros.put("idInmuebleBloque", presupuestoOrden.getIdInmuebleBloque());
            parametros.put("pagina", pagina);
            parametros.put("paginacion", nroRegistros);

            List<PresupuestoOrden> listaPresupuestoOrden = obtenerListaPresupuestoOrden(parametros);

            int fila = 3;

            for (PresupuestoOrden datosPesupuestoOrden : listaPresupuestoOrden) {
                Row filaPresupuestoOrden = sheet.createRow(fila);
                Cell celdaItemReporte = filaPresupuestoOrden.createCell(0);
                celdaItemReporte.setCellValue(datosPesupuestoOrden.getNroSolicitudSiga());
                celdaItemReporte.setCellStyle(estiloCelda);

                Cell celdaInmueble = filaPresupuestoOrden.createCell(1);
                celdaInmueble.setCellValue(datosPesupuestoOrden.getInmueble());
                celdaInmueble.setCellStyle(estiloCelda);

                Cell celdaBloque = filaPresupuestoOrden.createCell(2);
                celdaBloque.setCellValue(datosPesupuestoOrden.getBloque());
                celdaBloque.setCellStyle(estiloCelda);

                Cell celdaPredio = filaPresupuestoOrden.createCell(3);
                celdaPredio.setCellValue(datosPesupuestoOrden.getPredio());
                celdaPredio.setCellStyle(estiloCelda);

                Cell celdaNumOrden = filaPresupuestoOrden.createCell(4);
                celdaNumOrden.setCellValue(datosPesupuestoOrden.getNumNumOrde());
                celdaNumOrden.setCellStyle(estiloCelda);
            }

            ByteArrayOutputStream arregloBytes = new ByteArrayOutputStream();
            workbook.write(arregloBytes);
            workbook.close();

            return arregloBytes;

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public void guardarPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
            //ejbPresupuesto.guardarPresupuesto(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public ResultadoBusquedaMantenimiento consultarPresReg(PresupuestoOrden presupuestoOrden, int pagina,
                                                           int nroRegistros) throws Exception {
        try {

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("nroSolicitud", presupuestoOrden.getNroSolicitudSiga());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            //int cantidadRegistros = obtenerCantidadPresupuestoOrden(presupuestoOrden);
            List<PresupuestoOrden> listaPresupuestoOrden = null; //obtenerListaPresupuestoReg(parametrosConsulta);

            int cantidadRegistros = listaPresupuestoOrden.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public void modificarPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
         //   ejbPresupuesto.modificarPresupuesto(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public List<PresupuestoOrden> obtenerListaRealizarPre(Map<String, Object> parametros) throws Exception {
        try {
            return ejbPresupuesto.obtenerListaRealizarPre(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public ResultadoBusquedaMantenimiento consultaRealizarPre(PresupuestoOrden presupuestoOrden, int pagina,
                                                              int nroRegistros) throws Exception {
        try {

            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("nroSolicitud", presupuestoOrden.getNroSolicitudSiga());
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);

            //int cantidadRegistros = obtenerCantidadPresupuestoOrden(presupuestoOrden);
            List<PresupuestoOrden> listaPresupuestoOrden = obtenerListaRealizarPre(parametrosConsulta);

            int cantidadRegistros = listaPresupuestoOrden.size();

            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public void guardarGastoPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
            //ejbPresupuesto.guardarGastoPresupuesto(presupuestoOrden);

            //ejbPresupuesto.actualizarTotalPresupuesto(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public void modificarPresupuestoDet(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
            //ejbPresupuesto.modificarPresupuestoDet(presupuestoOrden);

            //ejbPresupuesto.actualizarTotalPresupuesto(presupuestoOrden);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public void actualizarTotalPresupuesto(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
            ejbPresupuesto.actualizarTotalPresupuesto(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public ResultadoBusquedaMantenimiento obtenerListaPresupuestoDet(PresupuestoOrden presupuestoOrden, int pagina,
                                                                     int nroRegistros) throws Exception {
        Map<String, Object> parametrosConsulta = new HashMap<>();
        parametrosConsulta.put("idPresupuestoOrden", presupuestoOrden.getIdPresupuestoOrden());
        parametrosConsulta.put("pagina", pagina);
        parametrosConsulta.put("paginacion", nroRegistros);
        parametrosConsulta.put("nuItem", presupuestoOrden.getNuItem());
        parametrosConsulta.put("feRegistro", presupuestoOrden.getFeRegistro());

        //int cantidadRegistros = obtenerCantidadPresupuestoOrden(presupuestoOrden);
        List<PresupuestoOrden> listaPresupuestoOrden = ejbPresupuesto.obtenerListaPresupuestoDet(parametrosConsulta);

        int cantidadRegistros = listaPresupuestoOrden.size();

        Paginacion paginacion = new Paginacion();
        paginacion.setNumeroTotalRegistros(cantidadRegistros);

        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        resultado.setPaginacion(paginacion);
        resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
        return resultado;
    }

    @Override
    public Integer obtenerCantidadNroSol(PresupuestoOrden presupuestoOrden) throws Exception {
        Integer cantidadNroSol;
        try {
            cantidadNroSol = ejbPresupuesto.obtenerCantidadNroSol(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidadNroSol;
    }

    @Override
    public ResultadoBusquedaMantenimiento consultaTotalPresuOrde(PresupuestoOrden presupuestoOrden) throws Exception {
        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        try {
            //List<PresupuestoOrden> listaPresupuestoOrden =  ejbPresupuesto.obtenerListaPresupuestoDet(parametrosConsulta);
            List<PresupuestoOrden> listaPresupuestoOrden = ejbPresupuesto.obtenerTotalPresOrde(presupuestoOrden);
            resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

        return resultado;
    }

    @Override
    public List<PresupuestoOrden> consultaPresupuestoOrdenVigente(PresupuestoOrden presupuestoOrden) throws Exception {
        List<PresupuestoOrden> listaPresupuestoOrden;
        try {
            //listaPresupuestoOrden = ejbPresupuesto.consultaPresupuestoOrdenVigente(presupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

        return listaPresupuestoOrden;
    }

    @Override
    public void guardarDocumentoOrden(PresupuestoOrden registroPresOrdenDoc, Archivo archivo) throws Exception {
        try {
            System.out.println("guardarDocumentoOrden ");
            /**
             {//Crear Carpeta /NSAI/ACTA/{ID_CONTRATO}
             CarpetaFilenet carpetaInmuebleFileNet=new CarpetaFilenet();
             carpetaInmuebleFileNet.setClaseCarpeta(DirectorioTaxonomia.CLASE_CARPETA_ACTA);
             carpetaInmuebleFileNet.setNombreNuevaCarpeta(registroDocumentoActa.getIdContrato());
             carpetaInmuebleFileNet.setRutaCarpetaPadre(DirectorioTaxonomia.RUTA_CONTRATO);
             Map<String,Object> metadata=new HashMap<>();
             metadata.put(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO, registroDocumentoActa.getIdContrato());
             carpetaInmuebleFileNet.setMetadata(metadata);
             carpetasFilenet_0=carpetaInmuebleFileNet;
             }

             {//Crear Carpeta /NSAI/ACTA/{ID_CONTRATO}/CONTRATO
             CarpetaFilenet carpetaDocumentosGeneralesFileNet=new CarpetaFilenet();
             carpetaDocumentosGeneralesFileNet.setClaseCarpeta(null);
             carpetaDocumentosGeneralesFileNet.setNombreNuevaCarpeta(DirectorioTaxonomia.CLASE_CARPETA_DOCUMENTO);
             carpetaDocumentosGeneralesFileNet.setRutaCarpetaPadre(DirectorioTaxonomia.transformarRuta(DirectorioTaxonomia.RUTA_ID_CONTRATO, DirectorioTaxonomia.PATRON_ID_CONTRATO, registroDocumentoActa.getIdContrato()));
             carpetasFilenet_1=carpetaDocumentosGeneralesFileNet;

             }*/

            // /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}/DOCUMENTOS_GENERALES/

            DocumentoFilenet documentoFilenet = new DocumentoFilenet();
            AuditoriaFilenet auditoriaFilenet = new AuditoriaFilenet();
            ArchivoFileNet archivoFilenet = new ArchivoFileNet();
            PropertiesFilenet propiedades_0 = new PropertiesFilenet();
            //PropertiesFilenet propiedades_1 = new PropertiesFilenet();
            CarpetaFilenet carpetasFilenet_0 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_1 = new CarpetaFilenet();
            PropertiesFilenet propiedades_1A = new PropertiesFilenet();


            //Carpeta /NSAI/PRESUPUESTO
            carpetasFilenet_0.setOrden("0");
            carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
            carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_PRESUPUESTO);
            carpetasFilenet_0.setCarpetaDocumental(null);
            carpetasFilenet_0.setCantidadPropiedades("0");
            carpetasFilenet_0.setProperties(null);

            //Carpeta /NSAI/PRESUPUESTO/{ID_PRESUPUESTO_ORDEN}
            carpetasFilenet_1.setOrden("1");
            carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_PRESUPUESTO);
            carpetasFilenet_1.setCarpetaHija(String.valueOf(registroPresOrdenDoc.getIdPresupuestoOrden()));
            carpetasFilenet_1.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_PRESUPUESTO);
            carpetasFilenet_1.setCantidadPropiedades("1");

            propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PRESUPUESTO);
            propiedades_1A.setValor(String.valueOf(registroPresOrdenDoc.getIdPresupuestoOrden()));
            propiedades_1A.setProperties(null);

            carpetasFilenet_1.setProperties(propiedades_1A);

//			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA
//			carpetasFilenet_2.setOrden("2");
//			carpetasFilenet_2.setCarpetaPadre(registroDocumentoActa.getIdContrato());
//			carpetasFilenet_2.setCarpetaHija(DirectorioTaxonomia.CARPETA_ACTA);
//			carpetasFilenet_2.setCarpetaDocumental(null);
//			carpetasFilenet_2.setCantidadPropiedades("0");
//			carpetasFilenet_2.setProperties(null);
//			
//			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA
//			carpetasFilenet_3.setOrden("3");
//			carpetasFilenet_3.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ACTA);
//			carpetasFilenet_3.setCarpetaHija(DirectorioTaxonomia.CARPETA_ENTREGA);
//			carpetasFilenet_3.setCarpetaDocumental(null);
//			carpetasFilenet_3.setCantidadPropiedades("0");
//			carpetasFilenet_3.setProperties(null);
//			
//			//Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}
//			carpetasFilenet_4.setOrden("4");
//			carpetasFilenet_4.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ENTREGA);
//			carpetasFilenet_4.setCarpetaHija(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));//?
//			carpetasFilenet_4.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_ACTA_ENTREGA);
//			carpetasFilenet_4.setCantidadPropiedades("1");
//			
//			propiedades_4A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_ACTA_ENTREGA);
//			propiedades_4A.setValor(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));
//			propiedades_4A.setProperties(null);
//			
//			carpetasFilenet_4.setProperties(propiedades_4A);
//			
//			// /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/ENTREGA/{ID_ACTA_ENTREGA}/DOCUMENTOS_GENERALES/		
//			carpetasFilenet_5.setOrden("5");
//			carpetasFilenet_5.setCarpetaPadre(String.valueOf(registroDocumentoActa.getIdEntregaPredio()));
//			carpetasFilenet_5.setCarpetaHija(DirectorioTaxonomia.CARPETA_DOCUMENTOS_ACTA_GENERALES);
//			carpetasFilenet_5.setCarpetaDocumental(null);
//			carpetasFilenet_5.setCantidadPropiedades("0");
//			carpetasFilenet_5.setProperties(null);
//			carpetasFilenet_5.setCarpeta(null);

            //carpetasFilenet_4.setCarpeta(carpetasFilenet_5);
            //carpetasFilenet_3.setCarpeta(carpetasFilenet_4);
            //carpetasFilenet_2.setCarpeta(carpetasFilenet_3);
            //carpetasFilenet_1.setCarpeta(carpetasFilenet_2);
            carpetasFilenet_0.setCarpeta(carpetasFilenet_1);

            auditoriaFilenet.setIdUsuario(registroPresOrdenDoc.getAuditoria().getUsuarioCreacion());
            auditoriaFilenet.setIpTerminal(registroPresOrdenDoc.getAuditoria().getTerminalCreacion());

            //archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_CONTRATO_ACTA);
            archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_DOCUMENTO_PRESUPUESTO);
            archivoFilenet.setCantidadPropiedades("1");

            propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PRESUPUESTO);
            propiedades_0.setValor(String.valueOf(registroPresOrdenDoc.getIdPresupuestoOrden()));
            propiedades_0.setProperties(null);

            //propiedades_1.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_ACTA_ENTREGA);
            //propiedades_1.setValor(String.valueOf(registroPresOrdenDoc.getIdPresupuestoOrden()));
            //propiedades_1.setProperties(null);

            //propiedades_0.setProperties(propiedades_1);

            archivoFilenet.setProperties(propiedades_0);
            archivoFilenet.setTitle(archivo.getNombre());
            archivoFilenet.setContentType(archivo.getTipoMime());

            archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

            System.out.println(archivo.getNombre());
            System.out.println(registroPresOrdenDoc.getNombreArchivoDocumento());
            documentoFilenet.setArchivo(archivoFilenet);
            documentoFilenet.setAuditoria(auditoriaFilenet);
            documentoFilenet.setCantidadCarpetas("2");
            documentoFilenet.setCarpeta(carpetasFilenet_0);


            registroPresOrdenDoc.setNombreArchivoDocumento(archivo.getNombre());
            ejbPresupuesto.registrarDocumentoOrdenFilenet(registroPresOrdenDoc, documentoFilenet);


        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public ResultadoBusquedaMantenimiento consultaTotalPresuOrdeDet(PresupuestoOrden presupuestoOrden)
            throws Exception {
        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        try {
            List<PresupuestoOrden> listaPresupuestoOrden = ejbPresupuesto.consultaTotalPresuOrdeDet(presupuestoOrden);
            resultado.setListaPresupuestoOrden(listaPresupuestoOrden);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

        return resultado;
    }

    @Override
    public Archivo obtenerDocumentoAdjunto(PresupuestoOrden presupuestoOrden) throws Exception {
        try {
            String uuid = ejbPresupuesto.consultarIdDocumentoAdjunto(presupuestoOrden);
            System.out.println("uuid " + uuid);
            if (uuid == null) {
                return null;
            } else {
                ResultadoBusquedaDocumento resultadoBusquedaDocumento = UServicios.obtenerDocumento(uuid, TaxonomiaDocumentos.CLASE_DOCUMENTO_PRESUPUESTO);

                if (resultadoBusquedaDocumento != null) {
                    if (resultadoBusquedaDocumento.getResultado() != null) {
                        if (resultadoBusquedaDocumento.getResultado().getCodigo().equals("0000")) {
                            return new Archivo(resultadoBusquedaDocumento.getArchivo().getTitle(), Base64.decode(resultadoBusquedaDocumento.getArchivo().getBinaryBase64()));
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }
}