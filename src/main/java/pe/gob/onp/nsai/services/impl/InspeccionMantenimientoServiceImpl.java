package pe.gob.onp.nsai.services.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.InspeccionMantenimientoLocalDao;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.entity.InspeccionMantenimientoProjection;
import pe.gob.onp.nsai.repository.InspeccionMantenimientoRepository;
import pe.gob.onp.nsai.services.InspeccionMantenimientoService;
import pe.gob.onp.nsai.util.DirectorioTaxonomia;
import pe.gob.onp.nsai.util.TaxonomiaDocumentos;
import pe.gob.onp.nsai.util.UConvierteFecha;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class InspeccionMantenimientoServiceImpl implements InspeccionMantenimientoService {

    /**
     * Clase EJB para inspeccion mantenimiento del predio del inmueble.
     */
    private final InspeccionMantenimientoRepository inspeccionMantenimientoRepository;
    private final InspeccionMantenimientoLocalDao ejbInspeccionMantenimiento;

    public InspeccionMantenimientoServiceImpl(InspeccionMantenimientoRepository inspeccionMantenimientoRepository, InspeccionMantenimientoLocalDao ejbInspeccionMantenimiento) {
        this.inspeccionMantenimientoRepository = inspeccionMantenimientoRepository;
        this.ejbInspeccionMantenimiento = ejbInspeccionMantenimiento;
    }

    /**
     * Método que permite guardar gastos de inversión por predio
     *
     * @param inspeccionMantenimiento Datos del Predio a guardar, tipo Predio.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void guardarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.guardarInspeccionMantenimiento(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar inspeccion mantenimiento
     *
     * @param inspeccionMantenimiento mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    public ResultadoBusquedaInspeccionMantenimiento consultarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento, int pagina, int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("pagina", pagina);
            parametrosConsulta.put("paginacion", nroRegistros);
            parametrosConsulta.put("idInmueble", inspeccionMantenimiento.getIdInmueble());
            parametrosConsulta.put("codigoPredio", Objects.equals(inspeccionMantenimiento.getCodigoPredio(), "null") ? null : inspeccionMantenimiento.getCodigoPredio());
            parametrosConsulta.put("idInmuebleBloque", Objects.equals(inspeccionMantenimiento.getIdInmuebleBloque(), "null") ? null : inspeccionMantenimiento.getIdInmuebleBloque());

            int cantidadRegistros = obtenerCantidadInspeccionInmueble(inspeccionMantenimiento);
            List<InspeccionMantenimiento> listaInspeccionMantenimiento = ejbInspeccionMantenimiento.consultaInspeccionMantenimiento(parametrosConsulta);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);

            ResultadoBusquedaInspeccionMantenimiento resultado = new ResultadoBusquedaInspeccionMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListInspeccionMantenimiento(listaInspeccionMantenimiento);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }

    }

    /**
     * Método que permite obtener la cantidad de registros de inspeccion mantenimiento.
     *
     * @param inspeccionMantenimiento bean con los datos de búsqueda del inspeccionMantenimiento, tipo inspeccionMantenimiento.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadInspeccionInmueble(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        int cantidad;
        try {
            cantidad = ejbInspeccionMantenimiento.obtenerCantidadInspeccionInmueble(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    /**
     * Método que permite consultar inspeccion mantenimiento
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
	/*@Override
	public List<InspeccionMantenimiento>obtenerInspeccionMantenimiento(Map<String, Object> parametros) throws Exception {
		List<InspeccionMantenimiento> listInspeccionMantenimiento = new ArrayList<InspeccionMantenimiento>();
		try {
			listInspeccionMantenimiento = ejbInspeccionMantenimiento.consultaInspeccionMantenimiento(parametros);

		}catch(Exception excepcion) {
			throw new Exception(excepcion);
		}
		 return listInspeccionMantenimiento;
	}*/

    /**
     * Método que permite eliminar  segun el id
     *
     * @param inspeccionMantenimiento datos de gasto operacion inspeccionInventario a eliminar, tipo inspeccionInventario.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void eliminarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.eliminarInspeccionMantenimiento(inspeccionMantenimiento);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    /**
     * Método que permite consultar el inspeccionInventario segun el código
     *
     * @param codigo código del inspeccionInventario a consultar, tipo String.
     * @return inspeccionInventarioObtenido datos del inspeccionInventario, tipo inspeccionInventario.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public InspeccionMantenimiento obtenerInspeccionMantenimiento(String codigo) throws Exception {
        try {
            return ejbInspeccionMantenimiento.obtenerInspeccionMantenimiento(codigo);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite modificar inspeccion Mantenimiento segun el id
     *
     * @param inspeccionMantenimiento datos de gasto operacion InspeccionMantenimiento a modificar, tipo InspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void modificarInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.modificarInspeccionMantenimiento(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    public ResultadoBusquedaInspeccionMantenimiento cargarContacto(TipoResponsable tipoResponsable) throws Exception {
        try {
            List<TipoResponsable> listarContacto = obtenerContacto(tipoResponsable);
            ResultadoBusquedaInspeccionMantenimiento resultado = new ResultadoBusquedaInspeccionMantenimiento();
            resultado.setListarContacto(listarContacto);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    @Override
    public List<TipoResponsable> obtenerContacto(TipoResponsable tipoResponsable) throws Exception {
        List<TipoResponsable> listarContacto;
        try {
            listarContacto = ejbInspeccionMantenimiento.obtenerContacto(tipoResponsable);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listarContacto;
    }


    public ResultadoBusquedaInspeccionMantenimiento cargarArrendamiento(String codigo) throws Exception {
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("idInmueble", codigo);
            List<Arrendatario> listarContacto = obtenerArrendatario(parametro);
            ResultadoBusquedaInspeccionMantenimiento resultado = new ResultadoBusquedaInspeccionMantenimiento();
            resultado.setListarArrendamiento(listarContacto);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar inspeccion mantenimiento
     *
     * @param parametro mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<Arrendatario> obtenerArrendatario(Map<String, Object> parametro) throws Exception {
        List<Arrendatario> listarArrendamiento;
        try {
            listarArrendamiento = ejbInspeccionMantenimiento.obtenerArrendatario(parametro);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listarArrendamiento;
    }

    public ResultadoBusquedaInspeccionMantenimiento cargarAmbiente(String idInmueble, String idInmueblePredio, String idInspecccionMantenimiento) throws Exception {
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("idInmueble", idInmueble);
            parametro.put("idpredio", idInmueblePredio);
            parametro.put("idInspecccionMantenimiento", idInspecccionMantenimiento);
            List<InspeccionMantenimiento> listarAmbiente = cargarAmbiente(parametro);
            ResultadoBusquedaInspeccionMantenimiento resultado = new ResultadoBusquedaInspeccionMantenimiento();
            resultado.setListarAmbiente(listarAmbiente);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite consultar inspeccion mantenimiento
     *
     * @param parametro mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<InspeccionMantenimiento> cargarAmbiente(Map<String, Object> parametro) throws Exception {
        List<InspeccionMantenimiento> listarAmbiente;
        try {
            listarAmbiente = ejbInspeccionMantenimiento.obtenerAmbiente(parametro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listarAmbiente;
    }

    /**
     * Método que permite agregar observacion por ambiente.
     *
     * @param listarObservacionesAmbiente datos de la ambiente, tipo InspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionAmbiente(List<InspeccionMantenimiento> listarObservacionesAmbiente, InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            for (InspeccionMantenimiento value : listarObservacionesAmbiente) {
                value.setAuditoria(inspeccionMantenimiento.getAuditoria());
                value.setIdInspecccionMantenimiento(inspeccionMantenimiento.getIdInspecccionMantenimiento());
                System.out.println("---> getObservacion  ==>" + value.getObservacion());


                inspeccionMantenimiento.setObservacion(value.getObservacion());
                inspeccionMantenimiento.setIdInspecccionMantenimiento(inspeccionMantenimiento.getIdInspecccionMantenimiento());
                inspeccionMantenimiento.setCodigoTipoAmbiente(value.getCodigoTipoAmbiente());
                inspeccionMantenimiento.setIdAmbientePredio(value.getIdAmbientePredio());
                if (value.getIdInspecccionMantenimientoAmb() > 0) {
                    inspeccionMantenimiento.setIdInspecccionMantenimientoAmb(value.getIdInspecccionMantenimientoAmb());
                    ejbInspeccionMantenimiento.actualizarInspeccionMantenimientoAmbiente(inspeccionMantenimiento);
                } else {
                    ejbInspeccionMantenimiento.registrarInspeccionMantenimientoAmbiente(inspeccionMantenimiento);
                }
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener los datos de la infraestructura por ambiente.
     *
     * @param idAmbiente identificador del ambiente idAmbiente, tipo long.
     * @return bean con los datos de la infraestructura, tipo List<AmbienteInfraestructura>.
     * @throws Exception excepción generada en caso de error.
     */
    public List<InspeccionMantenimiento> obtenerInfraestruturaPorAmbiente(long idAmbiente, String idInspecccionMantenimiento) throws Exception {
        List<InspeccionMantenimiento> ambienteInfraestructura;
        try {
            Map<String, Object> parametrosConsulta = new HashMap<>();
            parametrosConsulta.put("idAmbiente", idAmbiente);
            parametrosConsulta.put("idInspecccionMantenimiento", idInspecccionMantenimiento);
            ambienteInfraestructura = ejbInspeccionMantenimiento.obtenerInfraestruturaPorAmbiente(parametrosConsulta);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return ambienteInfraestructura;
    }

    /**
     * Método que permite agregar observacion por ambiente.
     *
     * @param inspeccionMantenimiento datos de la ambiente, tipo InspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void agregarObservacionInfraestructura(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.registrarInspeccionMantenimientoInfraestructura(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite agregar observacion por infraestructura.
     *
     * @param inspeccionMantenimiento datos de la ambiente, tipo InspeccionMantenimiento.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public void actualizarObservacionInfraestructura(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.actualizarObservacionInfraestructura(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public void actualizarActaInspeccion(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        try {
            ejbInspeccionMantenimiento.actualizarActaInspeccion(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }


    /**
     * Método que permite generar los datos del reporte de acta inspeccion en excel.
     *
     * @param auditoria  objeto de auditoria, tipo Auditoria.
     * @param inspeccion filtro por código del contrato, tipo String.	 *
     * @return arregloBytes arreglo de bytes del archivo excel generado, tipo ByteArrayOutputStream.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public ByteArrayOutputStream generarReporteActaInspeccionExcel(Auditoria auditoria, InspeccionMantenimiento inspeccion, String id) throws Exception {
        try {


            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Inspeccion");

            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontName("Arial");
            font.setFontHeight((short) (9 * 20));

            XSSFFont font2 = workbook.createFont();
            font2.setFontName("Arial");
            font2.setFontHeight((short) (9 * 20));

            XSSFFont font3 = workbook.createFont();
            font3.setFontName("Arial");
            font3.setFontHeight((short) (14 * 20));

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

            CellStyle estiloCabeFont = workbook.createCellStyle();
            estiloCabeFont.setFont(font3);
            estiloCabeFont.setBorderBottom(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderLeft(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderRight(BorderStyle.DOUBLE);
            estiloCabeFont.setBorderTop(BorderStyle.DOUBLE);
            estiloCabeFont.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCabeFont.setAlignment(HorizontalAlignment.CENTER);

            CellStyle estiloCeldaDe = workbook.createCellStyle();
            estiloCeldaDe.setFont(font2);
            estiloCeldaDe.setBorderBottom(BorderStyle.THIN);
            estiloCeldaDe.setBorderLeft(BorderStyle.THIN);
            estiloCeldaDe.setBorderRight(BorderStyle.THIN);
            estiloCeldaDe.setBorderTop(BorderStyle.THIN);


            CellStyle estiloCeldaRota = workbook.createCellStyle();
            estiloCeldaRota.setFont(font);
            estiloCeldaRota.setBorderBottom(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderLeft(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderRight(BorderStyle.MEDIUM);
            estiloCeldaRota.setBorderTop(BorderStyle.MEDIUM);
            estiloCeldaRota.setVerticalAlignment(VerticalAlignment.CENTER);
            estiloCeldaRota.setAlignment(HorizontalAlignment.CENTER);
            estiloCeldaRota.setRotation((short) 0xff);

            CellStyle estiloCeldaNegrita = workbook.createCellStyle();
            estiloCeldaNegrita.setFont(font);


            Row fila1 = sheet.createRow(0);

            Cell celda1 = fila1.createCell(0);
            celda1.setCellValue("OFICINA DE NORMALIZACIÓN PREVISIONAL - ONP");
            celda1.setCellStyle(estiloCabe);
            Cell celda2 = fila1.createCell(1);
            celda2.setCellStyle(estiloCabe);
            Cell celda3 = fila1.createCell(2);
            celda3.setCellStyle(estiloCabe);
            Cell celda4 = fila1.createCell(3);
            celda4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN: DEL PREDIO: DPTO. N° " + inspeccion.getIdInmuebleBloque() + " - " + inspeccion.getCodigoPredio());
            celda4.setCellStyle(estiloCabe);
            Cell celda5 = fila1.createCell(4);
            celda5.setCellStyle(estiloCabe);
            Cell celda6 = fila1.createCell(5);
            celda6.setCellStyle(estiloCabe);
            Cell celda7 = fila1.createCell(6);
            celda7.setCellStyle(estiloCabe);
            Cell celda8 = fila1.createCell(7);
            celda8.setCellStyle(estiloCabe);
            Cell celda9 = fila1.createCell(8);
            celda9.setCellValue("FECHA:");
            celda9.setCellStyle(estiloCabe);
            Cell celda10 = fila1.createCell(9);
            celda10.setCellValue(UConvierteFecha.formatearFecha(new Date()));
            celda10.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 7));


            Row fila2 = sheet.createRow(1);

            Cell celda11 = fila2.createCell(0);
            celda11.setCellStyle(estiloCabe);
            Cell celda12 = fila2.createCell(1);
            celda12.setCellStyle(estiloCabe);
            Cell celda13 = fila2.createCell(2);
            celda13.setCellStyle(estiloCabe);
            Cell celda14 = fila2.createCell(3);
            celda14.setCellValue("ACTA DE INSPECCIÓN");
            celda14.setCellStyle(estiloCabeFont);
            Cell celda15 = fila2.createCell(4);
            celda15.setCellStyle(estiloCabe);
            Cell celda16 = fila2.createCell(5);
            celda16.setCellStyle(estiloCabe);
            Cell celda17 = fila2.createCell(6);
            celda17.setCellStyle(estiloCabe);
            Cell celda18 = fila2.createCell(7);
            celda18.setCellStyle(estiloCabe);
            Cell celda19 = fila2.createCell(8);
            celda19.setCellValue("PAGINA:");
            celda19.setCellStyle(estiloCabe);
            Cell celda20 = fila2.createCell(9);
            celda20.setCellValue("1");
            celda20.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));


            Row fila3 = sheet.createRow(2);

            Cell celda21 = fila3.createCell(0);
            celda21.setCellStyle(estiloCabe);
            Cell celda22 = fila3.createCell(1);
            celda22.setCellStyle(estiloCabe);
            Cell celda23 = fila3.createCell(2);
            celda23.setCellStyle(estiloCabe);
            Cell celda24 = fila3.createCell(3);
            celda24.setCellValue("Contrato de Arrendamiento N° ");
            celda24.setCellStyle(estiloCabe);
            Cell celda25 = fila3.createCell(4);
            celda25.setCellStyle(estiloCabe);
            Cell celda26 = fila3.createCell(5);
            celda26.setCellValue(inspeccion.getIdcontrato());//entregaPredio.getIdContrato()
            celda26.setCellStyle(estiloCabe);
            Cell celda27 = fila3.createCell(6);
            celda27.setCellStyle(estiloCabe);
            Cell celda28 = fila3.createCell(7);
            celda28.setCellStyle(estiloCabe);
            Cell celda29 = fila3.createCell(8);
            celda29.setCellStyle(estiloCabe);
            Cell celda30 = fila3.createCell(9);
            celda30.setCellStyle(estiloCabe);

            sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 7));

            Row fila4 = sheet.createRow(4);
            fila4.createCell(0).setCellValue("Edificio");
            fila4.createCell(1).setCellValue(":");
            fila4.createCell(2).setCellValue(inspeccion.getDescripcionInmueble());
            sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 9));

            Row fila5 = sheet.createRow(5);
            fila5.createCell(0).setCellValue("Bloque");
            fila5.createCell(1).setCellValue(":");
            fila5.createCell(2).setCellValue(inspeccion.getIdInmuebleBloque());
            sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 9));

            Row fila6 = sheet.createRow(6);
            fila6.createCell(0).setCellValue("Predio");
            fila6.createCell(1).setCellValue(":");
            fila6.createCell(2).setCellValue(inspeccion.getCodigoPredio());
            sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 9));

            Row fila7 = sheet.createRow(7);
            fila7.createCell(0).setCellValue("De");
            fila7.createCell(1).setCellValue(":");
            fila7.createCell(2).setCellValue(inspeccion.getDireccionInmueble());
            sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 9));

            Row fila8 = sheet.createRow(8);
            fila8.createCell(0).setCellValue("A");
            fila8.createCell(1).setCellValue(":");
            fila8.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila9 = sheet.createRow(9);
            fila9.createCell(0).setCellValue("Fecha");
            fila9.createCell(1).setCellValue(":");
            fila9.createCell(2).setCellValue("");//UConvierteFecha.formatearHora(entregaPredio.getFechaInicio())
            sheet.addMergedRegion(new CellRangeAddress(9, 9, 2, 9));

            Row fila11 = sheet.createRow(11);
            fila11.createCell(0).setCellValue("Condiciones Fisicas de las instalaciones");
            sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 9));

            Map<String, Object> parametro = new HashMap<>();
            parametro.put("idInmueble", inspeccion.getIdInmueble());
            parametro.put("idpredio", inspeccion.getIdInmueblePredio());
            parametro.put("idInspecccionMantenimiento", inspeccion.getIdInspecccionMantenimiento());
            int fila = 13;

            List<InspeccionMantenimiento> listarAmbiente = cargarAmbiente(parametro);

            for (InspeccionMantenimiento datosAmbienteContrato : listarAmbiente) {

                Row filaAmbiente = sheet.createRow(fila);

                Cell celdaBloque = filaAmbiente.createCell(2);
                if (datosAmbienteContrato.getDescripcionTipoAmbiente() != null) {
                    celdaBloque.setCellValue(datosAmbienteContrato.getDescripcionTipoAmbiente());
                } else {
                    celdaBloque.setCellValue("");
                }

                fila++;

                Row filaCabeceraTabla = sheet.createRow(fila);

                Cell celdaCabecera0 = filaCabeceraTabla.createCell(0);
                celdaCabecera0.setCellStyle(estiloCelda);

                Cell celdaCabecera1 = filaCabeceraTabla.createCell(1);
                celdaCabecera1.setCellValue("");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 2, 0, 1));
                celdaCabecera1.setCellStyle(estiloCelda);

                Cell celdaCabecera2 = filaCabeceraTabla.createCell(2);
                celdaCabecera2.setCellValue("Descripción");
                celdaCabecera2.getSheet().addMergedRegion(new CellRangeAddress(fila, fila + 2, 2, 3));
                celdaCabecera2.setCellStyle(estiloCelda);


                Cell celdaCabecera3 = filaCabeceraTabla.createCell(3);
                celdaCabecera3.setCellStyle(estiloCelda);

                Cell celdaCabecera4 = filaCabeceraTabla.createCell(4);
                celdaCabecera4.setCellValue("Entrega");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 4, 7));
                celdaCabecera4.setCellStyle(estiloCelda);

                Cell celdaCabecera5 = filaCabeceraTabla.createCell(5);
                celdaCabecera5.setCellStyle(estiloCelda);
                Cell celdaCabecera6 = filaCabeceraTabla.createCell(6);
                celdaCabecera6.setCellStyle(estiloCelda);
                Cell celdaCabecera7 = filaCabeceraTabla.createCell(7);
                celdaCabecera7.setCellStyle(estiloCelda);

                Cell celdaCabecera8 = filaCabeceraTabla.createCell(8);
                celdaCabecera8.setCellValue("Observaciones");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 2, 8, 9));
                celdaCabecera8.setCellStyle(estiloCelda);

                Cell celdaCabecera9 = filaCabeceraTabla.createCell(9);
                celdaCabecera9.setCellStyle(estiloCelda);

                fila++;
                Row filaCabeceraTabla2 = sheet.createRow(fila);

                Cell celdaCabecera01 = filaCabeceraTabla.createCell(0);
                celdaCabecera01.setCellStyle(estiloCelda);

                Cell celdaCabecera02 = filaCabeceraTabla.createCell(1);
                celdaCabecera02.setCellStyle(estiloCelda);

                Cell celdaCabecera03 = filaCabeceraTabla2.createCell(2);
                celdaCabecera03.setCellStyle(estiloCelda);
                Cell celdaCabecera04 = filaCabeceraTabla2.createCell(3);
                celdaCabecera04.setCellStyle(estiloCelda);

                Cell celdaCabecera05 = filaCabeceraTabla2.createCell(4);
                celdaCabecera05.setCellValue("Cantidad");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila + 1, 4, 4));
                celdaCabecera05.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera06 = filaCabeceraTabla2.createCell(5);
                celdaCabecera06.setCellValue("Estado");
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 5, 7));
                celdaCabecera06.setCellStyle(estiloCelda);

                Cell celdaCabecera07 = filaCabeceraTabla2.createCell(6);
                celdaCabecera07.setCellStyle(estiloCelda);
                Cell celdaCabecera08 = filaCabeceraTabla2.createCell(7);
                celdaCabecera08.setCellStyle(estiloCelda);

                Cell celdaCabecera09 = filaCabeceraTabla2.createCell(8);
                celdaCabecera09.setCellStyle(estiloCelda);
                Cell celdaCabecera10 = filaCabeceraTabla2.createCell(9);
                celdaCabecera10.setCellStyle(estiloCelda);

                fila++;
                Row filaCabeceraTabla3 = sheet.createRow(fila);

                Cell celdaCabecera001 = filaCabeceraTabla3.createCell(0);
                celdaCabecera001.setCellStyle(estiloCelda);
                Cell celdaCabecera002 = filaCabeceraTabla3.createCell(1);
                celdaCabecera002.setCellStyle(estiloCelda);

                Cell celdaCabecera003 = filaCabeceraTabla3.createCell(2);
                celdaCabecera003.setCellStyle(estiloCelda);
                Cell celdaCabecera004 = filaCabeceraTabla3.createCell(3);
                celdaCabecera004.setCellStyle(estiloCelda);

                Cell celdaCabecera005 = filaCabeceraTabla3.createCell(4);
                celdaCabecera005.setCellStyle(estiloCelda);

                Cell celdaCabecera006 = filaCabeceraTabla3.createCell(5);
                celdaCabecera006.setCellValue("Bueno");
                celdaCabecera006.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera007 = filaCabeceraTabla3.createCell(6);
                celdaCabecera007.setCellValue("Regular");
                celdaCabecera007.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera008 = filaCabeceraTabla3.createCell(7);
                celdaCabecera008.setCellValue("Malo");
                celdaCabecera008.setCellStyle(estiloCeldaRota);

                Cell celdaCabecera009 = filaCabeceraTabla3.createCell(8);
                celdaCabecera009.setCellStyle(estiloCelda);
                Cell celdaCabecera010 = filaCabeceraTabla3.createCell(9);
                celdaCabecera010.setCellStyle(estiloCelda);


                sheet.setColumnWidth(0, 2800);
                sheet.setColumnWidth(1, 500);
                sheet.setColumnWidth(2, 8000);
                sheet.setColumnWidth(3, 8000);
                sheet.setColumnWidth(4, 900);
                sheet.setColumnWidth(5, 900);
                sheet.setColumnWidth(6, 900);
                sheet.setColumnWidth(7, 900);
                sheet.setColumnWidth(8, 3000);
                sheet.setColumnWidth(9, 3000);

                fila++;

                List<InspeccionMantenimiento> listarDescripcion = obtenerInfraestruturaPorAmbiente(datosAmbienteContrato.getIdAmbientePredio(), String.valueOf(inspeccion.getIdInspecccionMantenimiento()));

                for (InspeccionMantenimiento datosDescripcion : listarDescripcion) {

                    Row filaCabeceraDetalle = sheet.createRow(fila);

                    Cell celdaCabeceraDet0 = filaCabeceraDetalle.createCell(2);
                    celdaCabeceraDet0.setCellValue(datosDescripcion.getInfraestructura());
                    celdaCabeceraDet0.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 2, 3));
                    celdaCabeceraDet0.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet1 = filaCabeceraDetalle.createCell(3);
                    celdaCabeceraDet1.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet2 = filaCabeceraDetalle.createCell(4);
                    celdaCabeceraDet2.setCellValue(datosDescripcion.getCantidad());
                    celdaCabeceraDet2.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet3 = filaCabeceraDetalle.createCell(5);
                    if (datosDescripcion.getEstado().equals("BUENO")) {
                        celdaCabeceraDet3.setCellValue("X");
                    } else {
                        celdaCabeceraDet3.setCellValue("");
                    }
                    celdaCabeceraDet3.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet4 = filaCabeceraDetalle.createCell(6);
                    if (datosDescripcion.getEstado().equals("REGULAR")) {
                        celdaCabeceraDet4.setCellValue("X");
                    } else {
                        celdaCabeceraDet4.setCellValue("");
                    }
                    celdaCabeceraDet4.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet5 = filaCabeceraDetalle.createCell(7);
                    if (datosDescripcion.getEstado().equals("MALO")) {
                        celdaCabeceraDet5.setCellValue("X");
                    } else {
                        celdaCabeceraDet5.setCellValue("");
                    }
                    celdaCabeceraDet5.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet6 = filaCabeceraDetalle.createCell(8);
                    celdaCabeceraDet6.setCellValue(datosDescripcion.getObservacion());
                    celdaCabeceraDet1.getSheet().addMergedRegion(new CellRangeAddress(fila, fila, 8, 9));
                    celdaCabeceraDet6.setCellStyle(estiloCeldaDe);

                    Cell celdaCabeceraDet7 = filaCabeceraDetalle.createCell(9);
                    celdaCabeceraDet7.setCellStyle(estiloCeldaDe);

                    fila++;
                }

                fila++;
                fila++;

                Row filaCabeceraTabla4 = sheet.createRow(fila);
                Cell celdaCabecera110 = filaCabeceraTabla4.createCell(2);
                celdaCabecera110.setCellValue("Observaciones:");
                celdaCabecera110.setCellStyle(estiloCeldaNegrita);

                fila++;
                Row filaCabeceraTabla5 = sheet.createRow(fila);
                Cell celdaCabeceras0012 = filaCabeceraTabla5.createCell(2);
                celdaCabeceras0012.setCellValue(datosAmbienteContrato.getObservacion());
                sheet.addMergedRegion(new CellRangeAddress(fila, fila, 2, 9));
                celdaCabeceras0012.setCellStyle(estiloCeldaDe);

                Cell celdaCabecera11 = filaCabeceraTabla5.createCell(3);
                celdaCabecera11.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera12 = filaCabeceraTabla5.createCell(4);
                celdaCabecera12.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera13 = filaCabeceraTabla5.createCell(5);
                celdaCabecera13.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera14 = filaCabeceraTabla5.createCell(6);
                celdaCabecera14.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera15 = filaCabeceraTabla5.createCell(7);
                celdaCabecera15.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera16 = filaCabeceraTabla5.createCell(8);
                celdaCabecera16.setCellStyle(estiloCeldaDe);
                Cell celdaCabecera17 = filaCabeceraTabla5.createCell(9);
                celdaCabecera17.setCellStyle(estiloCeldaDe);


                fila++;
                fila++;
            }

            //CABECERA
            fila++;


            Row filaFinReporte = sheet.createRow(fila);
            Cell celdaFinReporte = filaFinReporte.createCell(0);
            celdaFinReporte.setCellValue("___________________________________");

            Cell celdaFinReporte1 = filaFinReporte.createCell(6);
            celdaFinReporte1.setCellValue("___________________________________");

            fila++;
            Row filaFinReporte1 = sheet.createRow(fila);
            Cell celdaFinReporte2 = filaFinReporte1.createCell(2);
            celdaFinReporte2.setCellValue("ARRENDADOR");

            Cell celdaFinReporte3 = filaFinReporte1.createCell(8);
            celdaFinReporte3.setCellValue("ARRENDATARIO");

            fila++;
            Row filaFinReporte2 = sheet.createRow(fila);
            Cell celdaFinReporte4 = filaFinReporte2.createCell(0);
            celdaFinReporte4.setCellValue("Oficina de Normalizacion Previsional - ONP");

            Cell celdaFinReporte5 = filaFinReporte2.createCell(5);
            celdaFinReporte5.setCellValue("Por :	Oficina de Normalizacion Previsional ");

            fila++;
            Row filaFinReporte3 = sheet.createRow(fila);
            Cell celdaFinReporte6 = filaFinReporte3.createCell(0);
            celdaFinReporte6.setCellValue("         Secretaría Técnica del FCR");

            Cell celdaFinReporte7 = filaFinReporte3.createCell(5);
            celdaFinReporte7.setCellValue("		  - Secretaría Técnica del FCR");

            fila++;
            Row filaFinReporte4 = sheet.createRow(fila);
            Cell celdaFinReporte9 = filaFinReporte4.createCell(5);
            celdaFinReporte9.setCellValue("Nombre :________________________");

            fila++;
            Row filaFinReporte5 = sheet.createRow(fila);
            Cell celdaFinReporte10 = filaFinReporte5.createCell(5);
            celdaFinReporte10.setCellValue("Dni :_________________________");


            CellStyle estiloFilaFinReporte = workbook.createCellStyle();
            estiloFilaFinReporte.setFont(font);
            estiloFilaFinReporte.setAlignment(HorizontalAlignment.CENTER_SELECTION);


            XSSFSheet sheet2 = workbook.createSheet("Fotos");

            Row fila21 = sheet2.createRow(0);

            Cell celdas1 = fila21.createCell(0);
            celdas1.setCellValue("OFICINA DE NORMALIZACIÓN PREVISIONAL - ONP");
            celdas1.setCellStyle(estiloCabe);
            Cell celdas2 = fila21.createCell(1);
            celdas2.setCellStyle(estiloCabe);
            Cell celdas3 = fila21.createCell(2);
            celdas3.setCellStyle(estiloCabe);
            Cell celdas4 = fila21.createCell(3);
            celdas4.setCellValue("REGISTRO DEL ESTADO DE CONSERVACIÓN:");
            celdas4.setCellStyle(estiloCabe);
            Cell celdas5 = fila21.createCell(4);
            celdas5.setCellStyle(estiloCabe);
            Cell celdas6 = fila21.createCell(5);
            celdas6.setCellStyle(estiloCabe);
            Cell celdas7 = fila21.createCell(6);
            celdas7.setCellStyle(estiloCabe);
            Cell celdas8 = fila21.createCell(7);
            celdas8.setCellStyle(estiloCabe);
            Cell celdas9 = fila21.createCell(8);
            celdas9.setCellValue("FECHA:");
            celdas9.setCellStyle(estiloCabe);
            Cell celdas10 = fila21.createCell(9);
            celdas10.setCellValue(UConvierteFecha.formatearFecha(new Date()));
            celdas10.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(0, 2, 0, 2));
            sheet2.addMergedRegion(new CellRangeAddress(0, 0, 3, 7));


            Row fila22 = sheet2.createRow(1);

            Cell celdas11 = fila22.createCell(0);
            celdas11.setCellStyle(estiloCabe);
            Cell celdas12 = fila22.createCell(1);
            celdas12.setCellStyle(estiloCabe);
            Cell celdas13 = fila22.createCell(2);
            celdas13.setCellStyle(estiloCabe);
            Cell celdas14 = fila22.createCell(3);
            celdas14.setCellValue("ACTA DE INSPECCIÓN");
            celdas14.setCellStyle(estiloCabeFont);
            Cell celdas15 = fila22.createCell(4);
            celdas15.setCellStyle(estiloCabe);
            Cell celdas16 = fila22.createCell(5);
            celdas16.setCellStyle(estiloCabe);
            Cell celdas17 = fila22.createCell(6);
            celdas17.setCellStyle(estiloCabe);
            Cell celdas18 = fila22.createCell(7);
            celdas18.setCellStyle(estiloCabe);
            Cell celdas19 = fila22.createCell(8);
            celdas19.setCellValue("PAGINA:");
            celdas19.setCellStyle(estiloCabe);
            Cell celdas20 = fila22.createCell(9);
            celdas20.setCellValue("1");
            celdas20.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet2.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            sheet2.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));


            Row fila33 = sheet2.createRow(2);

            Cell celdas21 = fila33.createCell(0);
            celdas21.setCellStyle(estiloCabe);
            Cell celdas22 = fila33.createCell(1);
            celdas22.setCellStyle(estiloCabe);
            Cell celdas23 = fila33.createCell(2);
            celdas23.setCellStyle(estiloCabe);
            Cell celdas24 = fila33.createCell(3);
            celdas24.setCellValue("Contrato de Arrendamiento N°");
            celdas24.setCellStyle(estiloCabe);
            Cell celdas25 = fila33.createCell(4);
            celdas25.setCellStyle(estiloCabe);
            Cell celdas26 = fila33.createCell(5);
            celdas26.setCellValue("");// entregaPredio.getIdContrato()
            celdas26.setCellStyle(estiloCabe);
            Cell celdas27 = fila33.createCell(6);
            celdas27.setCellStyle(estiloCabe);
            Cell celdas28 = fila33.createCell(7);
            celdas28.setCellStyle(estiloCabe);
            Cell celdas29 = fila33.createCell(8);
            celdas29.setCellStyle(estiloCabe);
            Cell celdas30 = fila33.createCell(9);
            celdas30.setCellStyle(estiloCabe);

            sheet2.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
            sheet2.addMergedRegion(new CellRangeAddress(2, 2, 5, 7));

            Row fila44 = sheet2.createRow(4);
            fila44.createCell(0).setCellValue("Edificio");
            fila44.createCell(1).setCellValue(":");
            fila44.createCell(2).setCellValue("");//entregaPredio.getDescripcionInmueble()
            sheet2.addMergedRegion(new CellRangeAddress(4, 4, 2, 9));

            Row fila55 = sheet2.createRow(5);
            fila55.createCell(0).setCellValue("Bloque");
            fila55.createCell(1).setCellValue(":");
            fila55.createCell(2).setCellValue("");//entregaPredio.getDescripcionBloque()
            sheet2.addMergedRegion(new CellRangeAddress(5, 5, 2, 9));

            Row fila66 = sheet2.createRow(6);
            fila66.createCell(0).setCellValue("Predio");
            fila66.createCell(1).setCellValue(":");
            fila66.createCell(2).setCellValue("");//entregaPredio.getDescripcionPredio()
            sheet2.addMergedRegion(new CellRangeAddress(6, 6, 2, 9));

            Row fila77 = sheet2.createRow(7);
            fila77.createCell(0).setCellValue("De");
            fila77.createCell(1).setCellValue(":");
            fila77.createCell(2).setCellValue("");//entregaPredio.getDireccionInmueble()
            sheet2.addMergedRegion(new CellRangeAddress(7, 7, 2, 9));

            Row fila88 = sheet2.createRow(8);
            fila88.createCell(0).setCellValue("A");
            fila88.createCell(1).setCellValue(":");
            fila88.createCell(2).setCellValue("Oficina de Normalizacion Previsional - Secretaría Técnica del FCR");
            sheet2.addMergedRegion(new CellRangeAddress(8, 8, 2, 9));

            Row fila99 = sheet2.createRow(9);
            fila99.createCell(0).setCellValue("Fecha");
            fila99.createCell(1).setCellValue(":");
            fila99.createCell(2).setCellValue("");//UConvierteFecha.formatearHora(entregaPredio.getFechaInicio())
            sheet2.addMergedRegion(new CellRangeAddress(9, 9, 2, 9));

            Row fila111 = sheet2.createRow(11);
            fila111.createCell(0).setCellValue("REGISTRO FOTOGRAFICO");
            sheet2.addMergedRegion(new CellRangeAddress(11, 11, 0, 9));


            sheet2.setColumnWidth(0, 2800);
            sheet2.setColumnWidth(1, 500);
            sheet2.setColumnWidth(2, 8000);
            sheet2.setColumnWidth(3, 8000);
            sheet2.setColumnWidth(4, 900);
            sheet2.setColumnWidth(5, 900);
            sheet2.setColumnWidth(6, 900);
            sheet2.setColumnWidth(7, 900);
            sheet2.setColumnWidth(8, 3000);
            sheet2.setColumnWidth(9, 3000);

            ByteArrayOutputStream arregloBytes = new ByteArrayOutputStream();
            workbook.write(arregloBytes);
            workbook.close();

            return arregloBytes;


        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    //obtenerInspeccion
    public InspeccionMantenimiento obtenerInspeccion(String id) throws Exception {
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("idInspecccionMantenimiento", id);
            return ejbInspeccionMantenimiento.obtenerInspeccion(parametro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite validar si existe N° solicitud
     *
     * @param numeroSolicitud bean con los datos de búsqueda del inspeccionMantenimiento, tipo NumeroSolicitud.
     * @return cantidad número de registros del inmueble, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int validarSolicitud(String numeroSolicitud) throws Exception {
        int cantidad;
        try {
            Map<String, Object> parametro = new HashMap<>();
            parametro.put("numeroSolicitud", numeroSolicitud);
            cantidad = ejbInspeccionMantenimiento.validarSolicitud(parametro);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    public ResultadoBusquedaInspeccionMantenimiento consultarReporteInspeccionMantenimiento(InspeccionMantenimiento inspeccionMantenimiento, int pagina, int nroRegistros) throws Exception {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("pagina", pagina);
            parametros.put("paginacion", nroRegistros);
            parametros.put("idInmueble", inspeccionMantenimiento.getIdInmueble());
            parametros.put("codigoPredio", inspeccionMantenimiento.getCodigoPredio());
            parametros.put("idInmuebleBloque", inspeccionMantenimiento.getIdInmuebleBloque());
            parametros.put("anio", inspeccionMantenimiento.getAnio());
            parametros.put("mes", inspeccionMantenimiento.getMes());

            int cantidadRegistros = obtenerReporteCantidadInspeccion(inspeccionMantenimiento);
            System.out.println("Cantidad: ---> " + cantidadRegistros);
            List<InspeccionMantenimiento> listaInspeccionMantenimiento = obtenerReporteInspeccionMantenimiento(parametros);
            Paginacion paginacion = new Paginacion();
            paginacion.setNumeroTotalRegistros(cantidadRegistros);
            System.out.println("listaInspeccionMantenimiento.size(): ---> " + listaInspeccionMantenimiento.size());
            ResultadoBusquedaInspeccionMantenimiento resultado = new ResultadoBusquedaInspeccionMantenimiento();
            resultado.setPaginacion(paginacion);
            resultado.setListInspeccionMantenimiento(listaInspeccionMantenimiento);
            return resultado;
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de reporte de inspeccion mantenimiento estado conforme.
     *
     * @param inspeccionMantenimiento bean con los datos de búsqueda del inspeccionMantenimiento, tipo inspeccionMantenimiento.
     * @return cantidad número de registros del inspeccionMantenimiento, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerReporteCantidadInspeccion(InspeccionMantenimiento inspeccionMantenimiento) throws Exception {
        int cantidad;
        try {
            cantidad = ejbInspeccionMantenimiento.obtenerReporteCantidadInspeccion(inspeccionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }


    /**
     * Método que permite consultar reporte inspeccion mantenimiento estado conforme
     *
     * @param parametros mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaInspeccionMantenimientolista registros con los datos del inspeccionMantenimiento, tipo List<inspeccionMantenimiento>.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public List<InspeccionMantenimiento> obtenerReporteInspeccionMantenimiento(Map<String, Object> parametros) throws Exception {
        List<InspeccionMantenimiento> listInspeccionMantenimiento;
        try {
            listInspeccionMantenimiento = ejbInspeccionMantenimiento.obtenerReporteInspeccionMantenimiento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listInspeccionMantenimiento;
    }

    /**
     * Método que obtiene el número correlativo de acta de inspeccion.
     *
     * @return número correlativo del acta de inspeccion, tipo int.
     * @throws Exception excepción generada en caso de error.
     */
    @Override
    public int obtenerCorrelativoActaInspeccion() throws Exception {
        try {
            return ejbInspeccionMantenimiento.obtenerCorrelativoActaInspeccion();
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    public void guardarActaInspeccion(InspeccionMantenimiento inspeccionMantenimiento, Archivo archivo) throws Exception {

        try {

            //int correlativo = obtenerCorrelativoActaInspeccion();
            //inspeccionMantenimiento.setIdDetalleActaDocumento(correlativo);

            DocumentoFilenet documentoFilenet = new DocumentoFilenet();
            AuditoriaFilenet auditoriaFilenet = new AuditoriaFilenet();
            ArchivoFileNet archivoFilenet = new ArchivoFileNet();
            PropertiesFilenet propiedades_0 = new PropertiesFilenet();
            PropertiesFilenet propiedades_1 = new PropertiesFilenet();
            CarpetaFilenet carpetasFilenet_0 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_1 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_2 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_3 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_4 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_5 = new CarpetaFilenet();

            PropertiesFilenet propiedades_1A = new PropertiesFilenet();
            PropertiesFilenet propiedades_3A = new PropertiesFilenet();
            PropertiesFilenet propiedades_4A = new PropertiesFilenet();


            //Carpeta /NSAI/CONTRATO
            carpetasFilenet_0.setOrden("0");
            carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
            carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_CONTRATO);
            carpetasFilenet_0.setCarpetaDocumental(null);
            carpetasFilenet_0.setCantidadPropiedades("0");
            carpetasFilenet_0.setProperties(null);

            //Carpeta /NSAI/CONTRATO/{ID_CONTRATO}
            carpetasFilenet_1.setOrden("1");
            carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_CONTRATO);
            carpetasFilenet_1.setCarpetaHija(inspeccionMantenimiento.getIdcontrato());
            carpetasFilenet_1.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_CONTRATO);
            carpetasFilenet_1.setCantidadPropiedades("1");

            propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
            propiedades_1A.setValor(inspeccionMantenimiento.getIdcontrato());
            propiedades_1A.setProperties(null);

            carpetasFilenet_1.setProperties(propiedades_1A);

            //Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA
            carpetasFilenet_2.setOrden("2");
            carpetasFilenet_2.setCarpetaPadre(inspeccionMantenimiento.getIdcontrato());
            carpetasFilenet_2.setCarpetaHija(DirectorioTaxonomia.CARPETA_ACTA);
            carpetasFilenet_2.setCarpetaDocumental(null);
            carpetasFilenet_2.setCantidadPropiedades("0");
            carpetasFilenet_2.setProperties(null);

            //Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/INSPECCION
            carpetasFilenet_3.setOrden("3");
            carpetasFilenet_3.setCarpetaPadre(DirectorioTaxonomia.CARPETA_ACTA);
            carpetasFilenet_3.setCarpetaHija(DirectorioTaxonomia.CARPETA_INSPECCION);
            carpetasFilenet_3.setCarpetaDocumental(null);
            carpetasFilenet_3.setCantidadPropiedades("0");
            carpetasFilenet_3.setProperties(null);

            ////Crear Carpeta /NSAI/CONTRATO/{ID_CONTRATO}/ACTA/INSPECCION/{ID}
            carpetasFilenet_4.setOrden("4");
            carpetasFilenet_4.setCarpetaPadre(DirectorioTaxonomia.CARPETA_INSPECCION);
            carpetasFilenet_4.setCarpetaHija(String.valueOf(inspeccionMantenimiento.getIdInspecccionMantenimiento()));
            carpetasFilenet_4.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_INSPECCION);
            carpetasFilenet_4.setCantidadPropiedades("1");

            propiedades_4A.setCodigo(TaxonomiaDocumentos.PROP_ID_ACTA_INSPECCION);
            propiedades_4A.setValor(String.valueOf(inspeccionMantenimiento.getIdInspecccionMantenimiento()));
            propiedades_4A.setProperties(null);

            carpetasFilenet_4.setProperties(propiedades_4A);

            //Crear Carpeta /NSAI/CONTRATO/{ID}/ACTA/INSPECCION/{ID}/DOCUMENTOS
            carpetasFilenet_5.setOrden("5");
            carpetasFilenet_5.setCarpetaPadre(String.valueOf(inspeccionMantenimiento.getIdInspecccionMantenimiento()));
            carpetasFilenet_5.setCarpetaHija(DirectorioTaxonomia.CARPETA_DOCUMENTOS);
            carpetasFilenet_5.setCarpetaDocumental(null);
            carpetasFilenet_5.setCantidadPropiedades("0");
            carpetasFilenet_5.setProperties(null);
            carpetasFilenet_5.setCarpeta(null);

            carpetasFilenet_4.setCarpeta(carpetasFilenet_5);
            carpetasFilenet_3.setCarpeta(carpetasFilenet_4);
            carpetasFilenet_2.setCarpeta(carpetasFilenet_3);
            carpetasFilenet_1.setCarpeta(carpetasFilenet_2);
            carpetasFilenet_0.setCarpeta(carpetasFilenet_1);

            auditoriaFilenet.setIdUsuario(inspeccionMantenimiento.getAuditoria().getUsuarioCreacion());
            auditoriaFilenet.setIpTerminal(inspeccionMantenimiento.getAuditoria().getTerminalCreacion());

            archivoFilenet.setClaseDocumental(TaxonomiaDocumentos.CLASE_DOCUMENTO_ACTA);
            archivoFilenet.setCantidadPropiedades("2");

            propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_CONTRATO);
            propiedades_0.setValor(inspeccionMantenimiento.getIdcontrato());

            propiedades_1.setCodigo(TaxonomiaDocumentos.PROP_ID_ACTA_INSPECCION);
            propiedades_1.setValor(String.valueOf(inspeccionMantenimiento.getIdInspecccionMantenimiento()));
            propiedades_1.setProperties(null);

            propiedades_0.setProperties(propiedades_1);

            archivoFilenet.setProperties(propiedades_0);
            archivoFilenet.setTitle(archivo.getNombre());
            archivoFilenet.setContentType(archivo.getTipoMime());

            archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

            documentoFilenet.setArchivo(archivoFilenet);
            documentoFilenet.setAuditoria(auditoriaFilenet);
            documentoFilenet.setCantidadCarpetas("6");
            documentoFilenet.setCarpeta(carpetasFilenet_0);


            inspeccionMantenimiento.setNombreArchivoDocumento(archivo.getNombre());

            ejbInspeccionMantenimiento.registrarActaInspeccionDocfile(inspeccionMantenimiento, documentoFilenet);

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    @Override
    public int obtenerCantidadInspeccionMantenimiento(Map<String, Object> parametros) throws Exception {
        int cantidad;
        try {
            cantidad = ejbInspeccionMantenimiento.obtenerCantidadInspeccionMantenimiento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    @Override
    public int obtenerCantidadPrediosReparados(Map<String, Object> parametros) throws Exception {
        int cantidad;
        try {
            cantidad = ejbInspeccionMantenimiento.obtenerCantidadPrediosReparados(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    public List<InspeccionMantenimiento> consultarPrediosReparados(
            InspeccionMantenimiento inspeccionMantenimiento) throws Exception{
        List<InspeccionMantenimientoProjection> inspeccionMantenimientoProjection = inspeccionMantenimientoRepository.findInspeccionesMantenimiento(
                inspeccionMantenimiento.getAnio(),
                inspeccionMantenimiento.getMes(),
                inspeccionMantenimiento.getIdInmueble(),
                inspeccionMantenimiento.getCodigoPredio(),
                inspeccionMantenimiento.getIdInmuebleBloque());
        return inspeccionMantenimientoProjection.stream().map(item -> {
            InspeccionMantenimiento inspeccion = new InspeccionMantenimiento();
            inspeccion.setIdInmueble(item.getIdInmueble());
            inspeccion.setDescripcionInmueble(item.getDescripcionInmueble());
            inspeccion.setNumeroSolicitud(item.getNumeroSolicitud());
            inspeccion.setFechaSolicitudForm(item.getFechaSolicitud());
            inspeccion.setCostoManoObra(item.getCostoManoObra());
            inspeccion.setCostoValorMaterial(item.getCostoValorMaterial());
            inspeccion.setCostoTotal(item.getTotal());
            inspeccion.setCodigoEstadoInspeccion(item.getCodigoEstadoInspeccion());
            inspeccion.setIdInmueblePredio(item.getIdInmueblePredio());
            inspeccion.setIdInmuebleBloque(item.getIdInmuebleBloque());
            //inspeccion.setCodigoBloque(item.getCodigoBloque());
            return inspeccion;
        }).collect(Collectors.toList());


    }

}