package pe.gob.onp.nsai.controller.mantenimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.ProrrateoService;
import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.util.GeneradorID;
import pe.gob.onp.nsai.util.UConstantes;
import pe.gob.onp.nsai.util.UJson;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para el prorrateo de inmuebles
 */
@RestController
@RequestMapping("/prorrateo")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ServicioProrrateoController {

    private final ProrrateoService prorrateoService;
    private final ServicioREST servicioREST;
    private final ServletContext servletContext;

    private final String ruta = "/WEB-INF/reportes/";

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos del inmueble segun parametros de busqueda para el prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigo código de inmueble
     * @param descripcion descripción de inmueble
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaInmuebles")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarInmueblesParaProrrateo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {

        if (codigo == null && descripcion == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        Inmueble inmuebleBusqueda = new Inmueble();
        inmuebleBusqueda.setIdInmueble(codigo);
        inmuebleBusqueda.setDescripcionInmueble(descripcion);

        ResultadoBusquedaInmueble resultado = prorrateoService.consultarInmueblesProrrateo(inmuebleBusqueda, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos del servicios del inmueble para prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mes mes del periodo a buscar
     * @param anio año del periodo a buscar
     * @param idInmueble identificador del inmueble
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaServiciosPeriodoInmueble")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarServiciosPeriodoInmueble(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {

        if (mes == null && anio == null && idInmueble == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        } else if (mes != null && !mes.isEmpty() && anio != null && !anio.isEmpty()) {
            PeriodoProrrateoInmueble periodoInmueble = new PeriodoProrrateoInmueble();
            periodoInmueble.setMesProrrateo(mes);
            periodoInmueble.setAnioProrrateo(anio);
            periodoInmueble.setIdInmueble(idInmueble);

            PeriodoProrrateoInmueble periodoProrrateoObtenido = prorrateoService.obtenerPeriodoProrrateo(periodoInmueble);
            if (periodoProrrateoObtenido != null) {
                periodoInmueble = periodoProrrateoObtenido;
            }

            ResultadoBusquedaProrrateo resultado = prorrateoService.consultarServiciosPeriodoInmueble(periodoInmueble, pagina, nroRegistros);
            return ResponseEntity.ok(resultado);
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Validación de periodo de prorrateo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
    }

    /**
     * Endpoint 3
     * Servicio que permite registrar los servicios para prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mapaProrrateo datos para registro de servicio de prorrateo
     * @return ResponseEntity con el resultado de la operación
     */
    @PostMapping("/registroServicioProrrateo")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> registrarServiciosProrrateo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestBody Map<String, Object> mapaProrrateo) {

        ServicioProrrateoInmueble prorrateoInmueble = null;
        if (mapaProrrateo.get("servicioProrrateo") != null) {
            prorrateoInmueble = UJson.convertirJsonATipo(mapaProrrateo.get("servicioProrrateo").toString(), ServicioProrrateoInmueble.class);
            prorrateoInmueble.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        }

        List<BloqueServicioProrrateo> listaBloqueProrrateo = new ArrayList<>();
        if (mapaProrrateo.get("listaBloqueServicio") != null) {
            listaBloqueProrrateo = UJson.convertirListJsonAListaTipo(mapaProrrateo.get("listaBloqueServicio").toString(), new com.fasterxml.jackson.core.type.TypeReference<List<BloqueServicioProrrateo>>() {});
        }

        List<PredioServicioProrrateo> listaPredioProrrateo = new ArrayList<>();
        if (mapaProrrateo.get("listaPredioServicio") != null) {
            listaPredioProrrateo = UJson.convertirListJsonAListaTipo(mapaProrrateo.get("listaPredioServicio").toString(), new com.fasterxml.jackson.core.type.TypeReference<List<PredioServicioProrrateo>>() {});
        }

        Long idServicioProrrateo = prorrateoService.registrarServiciosProrrateo(prorrateoInmueble, listaBloqueProrrateo, listaPredioProrrateo);

        if (idServicioProrrateo != null) {
            Map<String, String> mapServicioProrrateo = new HashMap<>();
            mapServicioProrrateo.put("idServicioProrrateo", String.valueOf(idServicioProrrateo));
            return ResponseEntity.ok(mapServicioProrrateo);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    /**
     * Endpoint 4
     * Servicio que permite actualizar los servicios para prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mapaProrrateo datos para registro de servicio de prorrateo
     * @return ResponseEntity con el resultado de la operación
     */
    @PutMapping("/actualizaServicioProrrateo")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> actualizarServiciosProrrateo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestBody Map<String, Object> mapaProrrateo) {

        ServicioProrrateoInmueble prorrateoInmueble = null;
        if (mapaProrrateo.get("servicioProrrateo") != null) {
            prorrateoInmueble = UJson.convertirJsonATipo(mapaProrrateo.get("servicioProrrateo").toString(), ServicioProrrateoInmueble.class);
            prorrateoInmueble.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        }

        List<BloqueServicioProrrateo> listaBloqueProrrateo = new ArrayList<>();
        if (mapaProrrateo.get("listaBloqueServicio") != null) {
            listaBloqueProrrateo = UJson.convertirListJsonAListaTipo(mapaProrrateo.get("listaBloqueServicio").toString(), new com.fasterxml.jackson.core.type.TypeReference<List<BloqueServicioProrrateo>>() {});
        }

        List<PredioServicioProrrateo> listaPredioProrrateo = new ArrayList<>();
        if (mapaProrrateo.get("listaPredioServicio") != null) {
            listaPredioProrrateo = UJson.convertirListJsonAListaTipo(mapaProrrateo.get("listaPredioServicio").toString(), new com.fasterxml.jackson.core.type.TypeReference<List<PredioServicioProrrateo>>() {});
        }

        prorrateoService.modificarServiciosProrrateo(prorrateoInmueble, listaBloqueProrrateo, listaPredioProrrateo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 5
     * Servicio que permite consultar datos del servicio para prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idServicioProrrateo identificador del servicio de prorrateo
     * @return ResponseEntity con los datos del servicio
     */
    @GetMapping("/consutaDatosServicioProrrateo/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<ServicioProrrateoInmueble> consultarDatosServicioProrrateo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @PathVariable("id") Long idServicioProrrateo) {

        ServicioProrrateoInmueble servicioProrrateo = prorrateoService.obtenerServiciosDeProrrateo(idServicioProrrateo);
        return ResponseEntity.ok(servicioProrrateo);
    }

    /**
     * Endpoint 6
     * Servicio que permite obtener el periodo de prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idInmueble identificador del inmueble
     * @param mes mes del periodo a buscar
     * @param anio año del periodo a buscar
     * @return ResponseEntity con los datos del periodo
     */
    @GetMapping("/consultaPeriodoProrrateo")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarPeriodoProrrateo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio) {

        if (mes == null && anio == null && idInmueble == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        PeriodoProrrateoInmueble periodoInmueble = new PeriodoProrrateoInmueble();
        periodoInmueble.setMesProrrateo(mes);
        periodoInmueble.setAnioProrrateo(anio);
        periodoInmueble.setIdInmueble(idInmueble);

        PeriodoProrrateoInmueble periodoProrrateoObtenido = prorrateoService.obtenerPeriodoProrrateo(periodoInmueble);
        if (periodoProrrateoObtenido == null) {
            return ResponseEntity.ok(new PeriodoProrrateoInmueble());
        } else {
            return ResponseEntity.ok(periodoProrrateoObtenido);
        }
    }

    /**
     * Endpoint 7
     * Servicio que permite obtener el periodo de prorrateo por inmueble
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idInmueble identificador del inmueble
     * @param mes mes del periodo a buscar
     * @param anio año del periodo a buscar
     * @param idServicio identificador del servicio
     * @return ResponseEntity con la lista de periodos
     */
    @GetMapping("/consultaPeriodoProrrateoInmueble")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarPeriodoProrrateoInmueble(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio,
            @RequestParam(value = "idServicio", required = false) String idServicio) {

        if (mes == null && anio == null && idInmueble == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        List<PeriodoProrrateoInmueble> periodoProrrateoObtenido = prorrateoService.obtenerPeriodoProrrateoInmueble(idInmueble, mes, anio, idServicio);
        if (periodoProrrateoObtenido == null) {
            return ResponseEntity.ok(new PeriodoProrrateoInmueble());
        } else {
            return ResponseEntity.ok(periodoProrrateoObtenido);
        }
    }

    /**
     * Endpoint 8
     * Servicio que permite obtener el prorrateo del periodo por inmueble
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idInmueble identificador del inmueble
     * @param mes mes del periodo a buscar
     * @param anio año del periodo a buscar
     * @return ResponseEntity con el archivo Excel generado
     */
    @GetMapping("/verProrrateoPeriodo")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> verReporteProrrateoPeriodo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "idInmueble") String idInmueble,
            @RequestParam(value = "mes") String mes,
            @RequestParam(value = "anio") String anio) {

        PeriodoProrrateoInmueble periodoInmueble = new PeriodoProrrateoInmueble();
        periodoInmueble.setIdInmueble(idInmueble);
        periodoInmueble.setMesProrrateo(mes);
        periodoInmueble.setAnioProrrateo(anio);

        ByteArrayOutputStream excel = prorrateoService.verProrrateoPeriodo(periodoInmueble);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentDispositionFormData("attachment", "ProrrateoSimulado.xlsx");

        return new ResponseEntity<>(excel.toByteArray(), responseHeaders, HttpStatus.OK);
    }

    /**
     * Endpoint 9
     * Servicio que permite ver los prorrateos generados del inmueble en el periodo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mes mes del periodo a buscar
     * @param anio año del periodo a buscar
     * @return ResponseEntity con el archivo Excel generado
     */
    @GetMapping("/verProrrateosInmueblesPeriodo")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> verProrrateosInmueblesPeriodo(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "mes") String mes,
            @RequestParam(value = "anio") String anio) {

        PeriodoProrrateoInmueble periodoInmueble = new PeriodoProrrateoInmueble();
        periodoInmueble.setMesProrrateo(mes);
        periodoInmueble.setAnioProrrateo(anio);

        ByteArrayOutputStream excel = prorrateoService.verProrrateosPeriodo(periodoInmueble);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String nombre = "ProrrateoPeriodo_" + mes + "_" + anio;
        responseHeaders.setContentDispositionFormData("attachment", nombre + ".xls");

        return new ResponseEntity<>(excel.toByteArray(), responseHeaders, HttpStatus.OK);
    }

    /**
     * Endpoint 10
     * Método que permite obtener el reporte porrateo gasto Mantenimiento formato Pdf
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param notas Notas adicionales
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/porrateoGastoMantenimientoPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> porrateoGastoMantenimientoPdf(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("notas") String notas) {

        String jasper = "reporteConsolidadoPorrateoGasto.jasper";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_MES", mes);
        parametros.put("P_ANIO", anio);
        parametros.put("P_NOTAS", notas);

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteConsolidadoPorrateoGasto.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 11
     * Método que permite obtener el reporte porrateo gasto Mantenimiento formato Excel
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param notas Notas adicionales
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/porrateoGastoMantenimientoExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> porrateoGastoMantenimientoExcel(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("notas") String notas) {

        String jasper = "reporteConsolidadoPorrateoGasto.jasper";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_MES", mes);
        parametros.put("P_ANIO", anio);
        parametros.put("P_NOTAS", notas);

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteConsolidadoPorrateoGasto.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 12
     * Servicio que permite obtener los datos del inmueble segun parametros de busqueda para el prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mes mes de prorrateoPredioGeneral
     * @param anio anio de prorrateoPredioGeneral
     * @param idInmueble identificador del inmueble
     * @param tipoEstado tipo de estado
     * @param tipoInmueble tipo de inmueble
     * @param tipoUso tipo de uso
     * @param tipoBloque tipo de bloque
     * @param tipoServicio tipo de servicio
     * @param descripcionPredio descripción del predio
     * @param codigo código adicional
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaconsultarInmueblesProrrateoPredio")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarInmueblesProrrateoPredio(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "anio", required = false) String anio,
            @RequestParam(value = "idInmueble", required = false) String idInmueble,
            @RequestParam(value = "estado", required = false) String tipoEstado,
            @RequestParam(value = "tipoInmueble", required = false) String tipoInmueble,
            @RequestParam(value = "tipoUso", required = false) String tipoUso,
            @RequestParam(value = "tipoBloque", required = false) String tipoBloque,
            @RequestParam(value = "tipoServicio", required = false) String tipoServicio,
            @RequestParam(value = "predio", required = false) String descripcionPredio,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {

        if (mes == null && anio == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        ProrrateoPredioGeneral prorrateoBusqueda = new ProrrateoPredioGeneral();
        if (tipoServicio != null && !tipoServicio.isEmpty()) {
            try {
                Long codigoDecodificado = GeneradorID.decodificar(tipoServicio);
                prorrateoBusqueda.setTipoServicio(String.valueOf(codigoDecodificado));
            } catch (IllegalArgumentException e) {
                prorrateoBusqueda.setTipoServicio("");
            }
        } else {
            prorrateoBusqueda.setTipoServicio("");
        }

        prorrateoBusqueda.setAnio(anio);
        prorrateoBusqueda.setMes(mes);
        prorrateoBusqueda.setIdInmueble(idInmueble);
        prorrateoBusqueda.setTipoEstado(tipoEstado);
        prorrateoBusqueda.setTipoInmueble(tipoInmueble);
        prorrateoBusqueda.setTipoUso(tipoUso);
        prorrateoBusqueda.setTipoBloque(tipoBloque);
        prorrateoBusqueda.setDescripcionPredio(descripcionPredio);
        prorrateoBusqueda.setCodigo(codigo);

        ResultadoBusquedaProrrateo resultado = prorrateoService.consultarInmueblesProrrateoPredio(prorrateoBusqueda, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 13
     * Método que permite obtener el reporte de prorrateo Predio en formato Pdf
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param tipoServicio Tipo de servicio
     * @param idInmueble Identificador del inmueble
     * @param codigo Código adicional
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/reporteProrrateoPredioPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> reporteProrrateoPredioPdf(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("tipoServicio") String tipoServicio,
            @RequestParam("idInmueble") String idInmueble,
            @RequestParam("codigo") String codigo) {

        String servicio = "";
        if (tipoServicio != null && !tipoServicio.isEmpty()) {
            try {
                Long codigoDecodificado = GeneradorID.decodificar(tipoServicio);
                servicio = String.valueOf(codigoDecodificado);
            } catch (IllegalArgumentException e) {
                servicio = "";
            }
        }

        String jasper = "reporteProrrateoPredio.jasper";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_mes", mes);
        parametros.put("P_anio", anio);
        parametros.put("P_tipo_serv", servicio);
        parametros.put("P_idInmueble", idInmueble);
        parametros.put("P_codigo", codigo);

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteProrrateoPredio.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 14
     * Método que permite obtener el reporte de prorrateo Predio en formato Excel
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param tipoServicio Tipo de servicio
     * @param idInmueble Identificador del inmueble
     * @param codigo Código adicional
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/reporteProrrateoPredioExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> reporteProrrateoPredioExcel(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("tipoServicio") String tipoServicio,
            @RequestParam("idInmueble") String idInmueble,
            @RequestParam("codigo") String codigo) {

        String servicio = "";
        if (tipoServicio != null && !tipoServicio.isEmpty()) {
            try {
                Long codigoDecodificado = GeneradorID.decodificar(tipoServicio);
                servicio = String.valueOf(codigoDecodificado);
            } catch (IllegalArgumentException e) {
                servicio = "";
            }
        }

        String jasper = "reporteProrrateoPredio.jasper";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_mes", mes);
        parametros.put("P_anio", anio);
        parametros.put("P_tipo_serv", servicio);
        parametros.put("P_idInmueble", idInmueble);
        parametros.put("P_codigo", codigo);

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteProrrateoPredio.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 15
     * Método que permite obtener el reporte de prorrateo de gasto de mantenimiento en formato Pdf
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/reporteProrrateoGastoMantenimientoPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> reporteProrrateoGastoMantenimientoPdf(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request) {

        String jasper = "reporteProrrateoGasto.jasper";
        Map<String, Object> parametros = new HashMap<>();

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteProrrateoGasto.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Endpoint 16
     * Método que permite obtener el reporte de prorrateo de gasto de mantenimiento en formato Excel
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/reporteProrrateoGastoMantenimientoExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> reporteProrrateoGastoMantenimientoExcel(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request) {

        String jasper = "reporteProrrateoGasto.jasper";
        Map<String, Object> parametros = new HashMap<>();

        byte[] bytes = servicioREST.exportarJasper(
                jasper,
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteProrrateoGasto.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(resource);
    }

    /**
     * Endpoint 17
     * Servicio que permite generar reporte PDF de prorrateo inmueble bloque predio
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param descServicio Descripción del servicio
     * @param descMes Descripción del mes
     * @param codServicio Código del servicio
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/generarProrrateoInmuebleBloquePredioPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> generarProrrateoInmuebleBloquePredioPdf(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("descServicio") String descServicio,
            @RequestParam("descMes") String descMes,
            @RequestParam("codServicio") String codServicio) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headersRequest, request);
        String jasper = "reporteProrrateoPorPredio.jasper";
        String pathReport = servletContext.getRealPath(this.ruta + jasper);

        ReporteProrrateoInmuebleBloquePredio filtro = new ReporteProrrateoInmuebleBloquePredio();
        filtro.setMes(mes);
        filtro.setAnio(anio);
        filtro.setDescMes(descMes);
        filtro.setCodServicio(codServicio);
        filtro.setServicio(descServicio);

        Map<String, Object> responseBusqueda = prorrateoService.generarProrrateoInmuebleBloquePredio(filtro);

        String mensajeRespuesta = responseBusqueda.get("mensajeRespuesta").toString();
        Integer codigoRespuesta = Integer.parseInt(responseBusqueda.get("codigoRespuesta").toString());
        List<ReporteProrrateoInmuebleBloquePredio> lista = (List<ReporteProrrateoInmuebleBloquePredio>) responseBusqueda.get("lista");

        if (codigoRespuesta == 0) {
            byte[] bytes = prorrateoService.generarProrrateoInmuebleBloquePredioPdf(lista, filtro, pathReport, servletContext, auditoria);

            ByteArrayResource resource = new ByteArrayResource(bytes);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteProrrateoPorPredio.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(mensajeRespuesta);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
    }

    /**
     * Endpoint 18
     * Servicio que permite generar reporte Excel de prorrateo inmueble bloque predio
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param descServicio Descripción del servicio
     * @param descMes Descripción del mes
     * @param codServicio Código del servicio
     * @param fechaEmision Fecha de emisión
     * @param fechaVencimiento Fecha de vencimiento
     * @param fechaCalcInteres Fecha de cálculo de interés
     * @param fechaGeneracion Fecha de generación
     * @param tipoProrrateo Tipo de prorrateo
     * @return Archivo Excel con el reporte generado
     */
    @GetMapping("/generarProrrateoInmuebleBloquePredioExcel")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> generarProrrateoInmuebleBloquePredioExcel(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("descServicio") String descServicio,
            @RequestParam("descMes") String descMes,
            @RequestParam("codServicio") String codServicio,
            @RequestParam(value = "fechaEmision", required = false) Date fechaEmision,
            @RequestParam(value = "fechaVencimiento", required = false) Date fechaVencimiento,
            @RequestParam(value = "fechaCalcInteres", required = false) Date fechaCalcInteres,
            @RequestParam(value = "fechaGeneracion", required = false) Date fechaGeneracion,
            @RequestParam(value = "tipoProrrateo", required = false) String tipoProrrateo) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headersRequest, request);
        String jasper = "reporteProrrateoPorPredio.jasper";
        String pathReport = servletContext.getRealPath(this.ruta + jasper);

        ReporteProrrateoInmuebleBloquePredio filtro = new ReporteProrrateoInmuebleBloquePredio();
        filtro.setMes(mes);
        filtro.setAnio(anio);
        filtro.setDescMes(descMes);
        filtro.setCodServicio(codServicio);
        filtro.setServicio(descServicio);
        filtro.setFechaEmision(fechaEmision);
        filtro.setFechaVencimiento(fechaVencimiento);
        filtro.setFechaCalcInteres(fechaCalcInteres);
        filtro.setFechaGeneracion(fechaGeneracion);
        filtro.setTipoProrrateo(tipoProrrateo);
        filtro.setAuditoria(auditoria);

        Map<String, Object> responseBusqueda = prorrateoService.generarProrrateoInmuebleBloquePredio(filtro);

        String mensajeRespuesta = responseBusqueda.get("mensajeRespuesta").toString();
        Integer codigoRespuesta = Integer.parseInt(responseBusqueda.get("codigoRespuesta").toString());
        List<ReporteProrrateoInmuebleBloquePredio> lista = (List<ReporteProrrateoInmuebleBloquePredio>) responseBusqueda.get("lista");

        if (codigoRespuesta == 0) {
            ByteArrayOutputStream bytes = prorrateoService.generarProrrateoInmuebleBloquePredioExcel(lista, filtro, pathReport, servletContext, auditoria);

            ByteArrayResource resource = new ByteArrayResource(bytes.toByteArray());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteProrrateoPorPredio.xlsx")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(mensajeRespuesta);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
    }

    /**
     * Endpoint 19
     * Servicio que permite generar reporte PDF de verificación de prorrateo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param anio Año del reporte
     * @param mes Mes del reporte
     * @param descServicio Descripción del servicio
     * @param descMes Descripción del mes
     * @param servicio Código del servicio
     * @return Archivo PDF con el reporte generado
     */
    @GetMapping("/verificacionProrrateoPdf")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> verificacionProrrateoPdf(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam("anio") String anio,
            @RequestParam("mes") String mes,
            @RequestParam("descServicio") String descServicio,
            @RequestParam("descMes") String descMes,
            @RequestParam("servicio") String servicio) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headersRequest, request);
        String jasper = "reporteVerificacionProcesoProrrateo.jasper";
        String pathReport = servletContext.getRealPath(this.ruta + jasper);

        ReporteVerificacionProrrateoPredio filtro = new ReporteVerificacionProrrateoPredio();
        filtro.setMes(mes);
        filtro.setAnio(anio);
        filtro.setDescMes(descMes);
        filtro.setIdServicio(servicio);
        filtro.setServicio(descServicio);

        Map<String, Object> responseBusqueda = prorrateoService.verificacionProrrateo(filtro);

        String mensajeRespuesta = responseBusqueda.get("mensajeRespuesta").toString();
        Integer codigoRespuesta = Integer.parseInt(responseBusqueda.get("codigoRespuesta").toString());
        List<ReporteVerificacionProrrateoInmueble> listaInmueble = (List<ReporteVerificacionProrrateoInmueble>) responseBusqueda.get("listaVerificacionPorInmueble");
        List<ReporteVerificacionProrrateoBloque> listaBloque = (List<ReporteVerificacionProrrateoBloque>) responseBusqueda.get("listaVerificacionPorBloque");
        List<ReporteVerificacionProrrateoPredio> listaPredio = (List<ReporteVerificacionProrrateoPredio>) responseBusqueda.get("listaVerificacionPorPredio");

        if (codigoRespuesta == 0) {
            byte[] bytes = prorrateoService.verificacionProrrateoPdf(listaInmueble, listaBloque, listaPredio, filtro, pathReport, servletContext, auditoria);

            ByteArrayResource resource = new ByteArrayResource(bytes);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteVerificacionProrrateo.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(mensajeRespuesta);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
    }
}