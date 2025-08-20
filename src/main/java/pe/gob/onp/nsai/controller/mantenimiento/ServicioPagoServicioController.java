package pe.gob.onp.nsai.controller.mantenimiento;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.PagoServicioService;
import pe.gob.onp.nsai.util.UConstantes;
import pe.gob.onp.nsai.controller.ServicioREST;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller para el mantenimiento de Pagos de Servicios
 */
@RestController
@RequestMapping("/pagoServicio")
@Validated
@RequiredArgsConstructor
public class ServicioPagoServicioController {

    private final PagoServicioService pagoService;
    private final ServicioREST servicioREST;
    private final ServletContext servletContext;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de pago servicio según parámetros de búsqueda
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param pagina Número de página para la paginación
     * @param nroRegistros Cantidad de registros por página
     * @param codigo Código de inmueblePredio
     * @param idInmueble Identificador del inmueble
     * @param idInmuebleBloque Identificador del bloque del inmueble
     * @param codigoPredio Código del predio
     * @param mes Mes del pago
     * @param anio Año del pago
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consultaPagoServicio")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResultadoBusquedaMantenimiento consultaPagoServicio(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "idInmuebleBloque", required = false) String idInmuebleBloque,
            @RequestParam(value = "codigoPredio", required = false) String codigoPredio,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setCodigo(codigo);
        pagoServicio.setIdInmueble(idInmueble);
        pagoServicio.setIdInmuebleBloque(idInmuebleBloque);
        pagoServicio.setIdInmueblePredio(codigoPredio);
        pagoServicio.setAnio(anio);
        pagoServicio.setMes(mes);

        return pagoService.consultaPagoServicio(pagoServicio, pagina, nroRegistros);
    }

    /**
     * Endpoint 2
     * Servicio que permite registrar los gastos operativos del inmueble
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param pagoServicio Objeto con los datos del pago de servicio a registrar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se registra correctamente
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> registrarGastosOperativos(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestBody @Valid PagoServicio pagoServicio) {

        pagoServicio.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        pagoService.guardarPagoServicio(pagoServicio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 3
     * Servicio que permite modificar los datos del pago de servicio
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param pagoServicio Objeto con los datos actualizados del pago de servicio
     * @param id Identificador del pago de servicio a modificar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se modifica correctamente
     */
    @PutMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> modificarPagoServicio(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestBody @Valid PagoServicio pagoServicio,
            @PathVariable String id) {

        if(id == null) {
            throw new IllegalArgumentException(servicioREST.getMessage("general.consulta.critero.minimo"));
        }

        pagoServicio.setIdPagoServicio(Integer.parseInt(id));
        pagoServicio.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        pagoService.modificarPagoServicio(pagoServicio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 4
     * Servicio que permite eliminar un pago de servicio
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param id Identificador del pago de servicio a eliminar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se elimina correctamente
     */
    @DeleteMapping("/eliminarPagoServicio/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> eliminarPagoServicio(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @PathVariable("id") int id) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setIdPagoServicio(id);
        pagoServicio.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        pagoService.eliminarPagoServicio(pagoServicio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 5
     * Servicio que permite obtener los datos de un pago de servicio específico
     *
     * @param id Identificador del pago de servicio
     * @return Objeto PagoServicio con los datos solicitados
     */
    @GetMapping("/obtenerPagoServicio/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public PagoServicio obtenerPagoServicio(@PathVariable("id") String id) {
        return pagoService.obtenerPagoServicio(Integer.parseInt(id));
    }

    /**
     * Endpoint 6
     * Servicio que permite obtener información de suministro de predio
     *
     * @param suministro Número de suministro
     * @param tipo Tipo de suministro
     * @return Objeto PredioSuministro con la información solicitada
     */
    @GetMapping("/obtenerSuministroPredio")
    // @SeguridadServicio - Reemplazar con Spring Security
    public PredioSuministro obtenerSuministroPredio(
            @RequestParam("suministro") String suministro,
            @RequestParam("tipo") Integer tipo) {
        return pagoService.obtenerSuministroPredio(suministro, tipo);
    }

    /**
     * Endpoint 7
     * Servicio que permite obtener la lista de servicios públicos
     *
     * @return Lista de categorías de parámetros para servicios públicos
     */
    @GetMapping("/obtenerServicioPublico")
    // @SeguridadServicio - Reemplazar con Spring Security
    public List<CategoriaParametro> obtenerServicioPublico() {
        return pagoService.listarServicioPublico();
    }

    /**
     * Endpoint 8
     * Servicio que permite obtener la lista de empresas de servicio
     *
     * @param id Identificador del tipo de servicio
     * @return Lista de categorías de parámetros para empresas de servicio
     */
    @GetMapping("/obtenerServicioEmpresa")
    // @SeguridadServicio - Reemplazar con Spring Security
    public List<CategoriaParametro> obtenerServicioEmpresa(@RequestParam("id") String id) {
        return pagoService.listarServicioEmpresa(id);
    }

    /**
     * Endpoint 9
     * Servicio que permite consultar pagos de servicio público
     *
     * @param pagina Número de página para la paginación
     * @param nroRegistros Cantidad de registros por página
     * @param servicio Tipo de servicio
     * @param empresa Empresa proveedora
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consultaPagoServicioPublico")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResultadoBusquedaMantenimiento consultaPagoServicioPublico(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros,
            @RequestParam(value = "servicio", required = false) String servicio,
            @RequestParam(value = "empresa", required = false) String empresa) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setTipoServicio(servicio);
        pagoServicio.setProveedor(empresa);

        return pagoService.consultaPagoServicioPublico(pagoServicio, pagina, nroRegistros);
    }

    /**
     * Endpoint 10
     * Servicio que permite obtener información por barra
     *
     * @param valor Valor de búsqueda
     * @return Información del pago de servicio
     */
    @GetMapping("/obtenerInformaionBarra")
    // @SeguridadServicio - Reemplazar con Spring Security
    public PagoServicio obtenerInformaionBarra(@RequestParam("valor") String valor) {
        if(valor == null) {
            throw new IllegalArgumentException(servicioREST.getMessage("general.consulta.critero.minimo"));
        }

        int cantBarr = pagoService.validarBusqueda(valor);
        if (cantBarr <= 0) {
            throw new IllegalArgumentException(servicioREST.getMessage("mantenimiento.proceso.validar.pago.masivo.restriccion"));
        }

        return pagoService.obtenerInformaionBarra(valor);
    }

    /**
     * Endpoint 11
     * Servicio que permite guardar temporalmente un pago de servicio
     *
     * @param pagoServicio Objeto con los datos del pago temporal
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se guarda correctamente
     */
    @PostMapping("/tmpPagoServicio")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> tmpPagoServicio(@RequestBody @Valid PagoServicio pagoServicio) {
        if(pagoServicio.getIdInmueblePredio() == null || pagoServicio.getIdInmueble() == null ||
                pagoServicio.getMontoValor() == null || pagoServicio.getCodigoTipoServ() == null ||
                pagoServicio.getFechaVencimiento() == null || pagoServicio.getCodigoProveedor() == null ||
                pagoServicio.getFechaFactura() == null || pagoServicio.getMes() == null ||
                pagoServicio.getAnio() == null) {
            throw new IllegalArgumentException(servicioREST.getMessage("general.consulta.critero.minimo"));
        }

        int cantSumi = pagoService.validarProcesoRepetidos(pagoServicio);
        if (cantSumi > 0) {
            throw new IllegalArgumentException(servicioREST.getMessage("mantenimiento.proceso.registrar.temporal.pago.masivo.restriccion"));
        }

        pagoService.tmpPagoServicio(pagoServicio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 12
     * Servicio que permite consultar pagos de servicio temporales
     *
     * @param pagina Número de página para la paginación
     * @param nroRegistros Cantidad de registros por página
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/tmpConsultaPagoServicio")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResultadoBusquedaMantenimiento tmpConsultaPagoServicio(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {
        return pagoService.tmpConsultaPagoServicio(pagina, nroRegistros);
    }

    /**
     * Endpoint 13
     * Servicio que permite retirar un pago de servicio temporal
     *
     * @param id Identificador del pago temporal a retirar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se retira correctamente
     */
    @DeleteMapping("/retirarPagoServicio/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> retirarPagoServicio(@PathVariable("id") int id) {
        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setIdPagoServicio(id);
        pagoService.retirarPagoServicio(pagoServicio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 14
     * Servicio que permite guardar la lista de pagos temporales
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param listarTemporal Lista de pagos temporales a guardar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se guarda correctamente
     */
    @PutMapping("/tmpGuardarLista")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> tmpGuardarLista(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestBody List<PagoServicio> listarTemporal) {

        if(listarTemporal == null || listarTemporal.isEmpty()) {
            throw new IllegalArgumentException(servicioREST.getMessage("general.consulta.critero.minimo"));
        }

        for(PagoServicio value : listarTemporal) {
            PagoServicio tmpPagoServicio = new PagoServicio();
            tmpPagoServicio.setCodigoTipoServ(value.getCodigoTipoServ());
            tmpPagoServicio.setCodigoProveedor(value.getCodigoProveedor());
            tmpPagoServicio.setMes(value.getMes());
            tmpPagoServicio.setAnio(value.getAnio());
            tmpPagoServicio.setFechaFactura(value.getFechaFactura());
            tmpPagoServicio.setFechaVencimiento(value.getFechaVencimiento());
            tmpPagoServicio.setNumeroSuministro(value.getNumeroSuministro());
            tmpPagoServicio.setMontoValor(value.getMontoValor());
            tmpPagoServicio.setIdInmueble(value.getIdInmueble());
            tmpPagoServicio.setIdInmueblePredio(value.getIdInmueblePredio());
            tmpPagoServicio.setCodigoTipoMoneda(value.getCodigoTipoMoneda());
            tmpPagoServicio.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
            pagoService.guardarPagoServicio(tmpPagoServicio);
            tmpPagoServicio.setIdPagoServicio(value.getIdPagoServicio());
            pagoService.retirarPagoServicio(tmpPagoServicio);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 15
     * Servicio que permite consultar pagos de servicio para predios desocupados
     *
     * @param idServicio Identificador del servicio
     * @param idInmueble Identificador del inmueble
     * @param mes Mes del pago
     * @param anio Año del pago
     * @return Resultado de la búsqueda
     */
    @GetMapping("/consultaPagoServicioPredioDesocupado")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResultadoBusquedaMantenimiento consultaPagoServicioPredioDesocupado(
            @RequestParam("idServicio") String idServicio,
            @RequestParam("idInmueble") String idInmueble,
            @RequestParam("mes") String mes,
            @RequestParam("anio") String anio) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setIdInmueble(idInmueble);
        pagoServicio.setCodigoTipoServ(idServicio);
        pagoServicio.setMes(mes);
        pagoServicio.setAnio(anio);

        return pagoService.consultaPagoServicioPredioDesocupado(pagoServicio, 0, 0);
    }

    /**
     * Endpoint 16
     * Servicio que permite generar reporte Excel de gastos de servicios para predios desocupados
     *
     * @param reporteGastos Datos para el reporte
     * @return Archivo Excel con el reporte generado
     */
    @PutMapping("/generarExcelDesocupados")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarReporteExcelGastosServiciosDesocupados(
            @RequestBody ReporteGastoServicioPredio reporteGastos) {

        byte[] bytes = pagoService.generarReporteExcelGastosServicios(reporteGastos, servletContext);

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReportesGastosServiciosPdf.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 17
     * Servicio que permite generar reporte PDF de gastos de servicios para predios desocupados
     *
     * @param reporteGastos Datos para el reporte
     * @return Archivo PDF con el reporte generado
     */
    @PutMapping("/generarPdfDesocupados")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarReportePdfGastosServiciosDesocupados(
            @RequestBody ReporteGastoServicioPredio reporteGastos) {

        byte[] bytes = pagoService.generarReportePdfGastosServicios(reporteGastos, servletContext);

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReportesGastosServiciosPdf.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 18
     * Servicio que permite consultar pagos de servicio para predios ocupados
     *
     * @param idServicio Identificador del servicio
     * @param idInmueble Identificador del inmueble
     * @param mes Mes del pago
     * @param anio Año del pago
     * @return Resultado de la búsqueda
     */
    @GetMapping("/consultaPagoServicioPredioOcupado")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResultadoBusquedaMantenimiento consultaPagoServicioPredioOcupado(
            @RequestParam("idServicio") String idServicio,
            @RequestParam("idInmueble") String idInmueble,
            @RequestParam("mes") String mes,
            @RequestParam("anio") String anio) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setIdInmueble(idInmueble);
        pagoServicio.setCodigoTipoServ(idServicio);
        pagoServicio.setMes(mes);
        pagoServicio.setAnio(anio);

        return pagoService.consultaPagoServicioPredioOcupado(pagoServicio, 0, 0);
    }

    /**
     * Endpoint 19
     * Servicio que permite generar reporte Excel of gastos de servicios para predios ocupados
     *
     * @param reporteGastos Datos para el reporte
     * @return Archivo Excel con el reporte generado
     */
    @PutMapping("/generarExcelOcupados")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarReporteExcelGastosServiciosOcupados(
            @RequestBody ReporteGastoServicioPredio reporteGastos) {

        byte[] bytes = pagoService.generarReporteExcelGastosServiciosOcupados(reporteGastos, servletContext);

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReportesGastosServiciosOcupados.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 20
     * Servicio que permite generar reporte PDF de gastos de servicios para predios ocupados
     *
     * @param reporteGastos Datos para the reporte
     * @return Archivo PDF con el reporte generado
     */
    @PutMapping("/generarPdfOcupados")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarReportePdfGastosServiciosOcupados(
            @RequestBody ReporteGastoServicioPredio reporteGastos) {

        byte[] bytes = pagoService.generarReportePdfGastosServiciosOcupados(reporteGastos, servletContext);

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReportesGastosServiciosOcupados.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 21
     * Servicio que permite generar reporte Excel de prorrateo de servicio público
     *
     * @param mes Mes del reporte
     * @param anio Año del reporte
     * @param descripcionMes Descripción del mes
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/generaReporteExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generaReporteExcel(
            @RequestParam("mes") String mes,
            @RequestParam("anio") String anio,
            @RequestParam("descripcionMes") String descripcionMes) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setMes(mes);
        pagoServicio.setAnio(anio);

        List<PagoServicio> listarPagoServicio = pagoService.listarPagoServicioReporte(pagoServicio);

        if(listarPagoServicio.isEmpty()) {
            throw new IllegalArgumentException(servicioREST.getMessage("generar.reporte.valida.servicio.publico"));
        }

        ByteArrayOutputStream archivoExcel = pagoService.reportePorrateoServicioPublicoExcel(mes, anio, listarPagoServicio, descripcionMes);

        ByteArrayResource resource = new ByteArrayResource(archivoExcel.toByteArray());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteProrrateoServicioPublico.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 22
     * Servicio que permite generar reporte PDF de prorrateo de servicio público
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param descripcionMes Descripción del mes
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/porrateoServicioPublicoPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> porrateoServicioPublicoPdf(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("descripcionMes") String descripcionMes) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_Anio", anio);
        parametros.put("p_Mes", mes);
        parametros.put("p_DescripcionMes", descripcionMes);

        byte[] bytes = servicioREST.exportarJasper(
                "porrateoServicioPublico.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReportesGastosServiciosPublicoPdf.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 23
     * Servicio que permite generar reporte Excel de prorrateo de servicio público
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param descripcionMes Descripción del mes
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/porrateoServicioPublicoExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> porrateoServicioPublicoExcel(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("descripcionMes") String descripcionMes) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_Anio", anio);
        parametros.put("p_Mes", mes);
        parametros.put("p_DescripcionMes", descripcionMes);

        byte[] bytes = servicioREST.exportarJasper(
                "porrateoServicioPublico.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteProrrateoServicioPublico.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 24
     * Servicio que permite generar reporte PDF detallado de prorrateo de servicio público
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param tipoServicio Tipo de servicio
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/porrateoDetalleServicioPublicoPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> porrateoDetalleServicioPublicoPdf(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("tipoServicio") String tipoServicio) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_anio", anio);
        parametros.put("p_mes", mes);
        parametros.put("p_tipo_Servicio", tipoServicio);

        byte[] bytes = servicioREST.exportarJasper(
                "reporteDetalladoServicioPublicoPorrateo.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=porrateoDetalleServicioPublico.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 25
     * Servicio que permite generar reporte Excel detallado de prorrateo de servicio público
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param tipoServicio Tipo de servicio
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/porrateoDetalleServicioPublicoExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> porrateoDetalleServicioPublicoExcel(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("tipoServicio") String tipoServicio) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("p_anio", anio);
        parametros.put("p_mes", mes);
        parametros.put("p_tipo_Servicio", tipoServicio);

        byte[] bytes = servicioREST.exportarJasper(
                "reporteDetalladoServicioPublicoPorrateo.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteProrrateoServicioPublico.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 26
     * Servicio que permite generar reporte Excel de pagos de servicio
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param codigo Código de referencia
     * @param idInmueble Identificador de inmueble
     * @param idInmuebleBloque Identificador de bloque
     * @param codigoPredio Código de predio
     * @param mes Mes de referencia
     * @param anio Año de referencia
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/generarPagoServicioExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarPagoServicioExcel(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "idInmuebleBloque", required = false) String idInmuebleBloque,
            @RequestParam(value = "codigoPredio", required = false) String codigoPredio,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio) {

        if (idInmueble == null) {
            throw new IllegalArgumentException(servicioREST.getMessage("pago.servicio.inmueble"));
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_ID_INMU", idInmueble);
        parametros.put("P_ID_INMU_BLOQUE", idInmuebleBloque);
        parametros.put("P_ID_PREDIO", codigoPredio);
        parametros.put("P_ANIO", anio);
        parametros.put("P_MES", mes);

        byte[] bytes = servicioREST.exportarJasper(
                "reportePagoServicio.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reportePagoServicio.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }
}