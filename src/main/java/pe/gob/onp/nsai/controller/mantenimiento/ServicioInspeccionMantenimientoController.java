package pe.gob.onp.nsai.controller.mantenimiento;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.InspeccionMantenimientoService;
import pe.gob.onp.nsai.util.UConstantes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Controlador REST para la gestión de Inspecciones de Mantenimiento.
 * Migrado de JAX-RS a Spring Boot con manejo centralizado de errores.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/inspeccionMantenimiento")
public class ServicioInspeccionMantenimientoController {

    private final InspeccionMantenimientoService inspeccionMantenimientoService;
    private final ServicioREST servicioREST;
    private final Validator validator;

    /**
     * Endpoint 1
     * Servicio que permite consultar las inspecciones de mantenimiento con paginación.
     *
     * @param idInmueble identificador del inmueble.
     * @param codigoPredio código del predio.
     * @param idInmuebleBloque identificador del bloque del inmueble.
     * @param pagina página solicitada (>=1).
     * @param nroRegistros cantidad de registros por página.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/consultaInspeccionMantenimiento")
    public ResponseEntity<ResultadoBusquedaInspeccionMantenimiento> consultaInspeccionMantenimiento(
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String codigoPredio,
            @RequestParam(required = false) String idInmuebleBloque,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int nroRegistros) {

        InspeccionMantenimiento criterio = new InspeccionMantenimiento();
        criterio.setIdInmueble(idInmueble);
        criterio.setCodigoPredio(codigoPredio);
        criterio.setIdInmuebleBloque(idInmuebleBloque);

        ResultadoBusquedaInspeccionMantenimiento resultado =
                inspeccionMantenimientoService.consultarInspeccionMantenimiento(criterio, pagina, nroRegistros);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio que permite registrar una nueva inspección de mantenimiento.
     *
     * @param inspeccionMantenimiento datos de la inspección a registrar.
     * @param headers cabeceras HTTP de la petición.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 201 Created en caso de éxito o errores de validación.
     */
    @PostMapping
    public ResponseEntity<?> guardarInspeccionMantenimiento(
            @Valid @RequestBody InspeccionMantenimiento inspeccionMantenimiento,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        if (inspeccionMantenimientoService.validarSolicitud(inspeccionMantenimiento.getNumeroSolicitud()) > 0) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("El número de solicitud ya existe.");
            return ResponseEntity.badRequest().body(error);
        }

        long diffTrabajo = TimeUnit.DAYS.convert(
                inspeccionMantenimiento.getFechaFinTrabajador().getTime() -
                        inspeccionMantenimiento.getFechaInicioTrabajo().getTime(),
                TimeUnit.MILLISECONDS);

        long diffAtencion = TimeUnit.DAYS.convert(
                inspeccionMantenimiento.getFechaInicioTrabajo().getTime() -
                        inspeccionMantenimiento.getFechaSolicitud().getTime(),
                TimeUnit.MILLISECONDS);

        inspeccionMantenimiento.setTiempoDemoraTrabajo((int) diffTrabajo + 1);
        inspeccionMantenimiento.setTiempoDemoraAtencion((int) diffAtencion + 1);
        inspeccionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));

        inspeccionMantenimientoService.guardarInspeccionMantenimiento(inspeccionMantenimiento);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint 3
     * Servicio que permite eliminar (lógicamente) una inspección de mantenimiento.
     *
     * @param idInspecccionMantenimiento identificador de la inspección.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @DeleteMapping("/eliminarInspeccionMantenimiento")
    public ResponseEntity<Void> eliminarInspeccionMantenimiento(
            @RequestParam String idInspecccionMantenimiento,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        InspeccionMantenimiento inspeccion = new InspeccionMantenimiento();
        inspeccion.setIdInspecccionMantenimiento(Integer.parseInt(idInspecccionMantenimiento));
        inspeccion.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));

        inspeccionMantenimientoService.eliminarInspeccionMantenimiento(inspeccion);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 4
     * Servicio que permite obtener los datos de una inspección por su ID.
     *
     * @param id identificador de la inspección.
     * @return Respuesta HTTP con la inspección encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InspeccionMantenimiento> obtenerInspeccionMantenimiento(@PathVariable String id) {
        InspeccionMantenimiento inspeccion = inspeccionMantenimientoService.obtenerInspeccionMantenimiento(id);
        return ResponseEntity.ok(inspeccion);
    }

    /**
     * Endpoint 5
     * Servicio que permite modificar una inspección de mantenimiento existente.
     *
     * @param id identificador de la inspección.
     * @param inspeccionMantenimiento datos a actualizar.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> modificarInspeccionMantenimiento(
            @PathVariable String id,
            @Valid @RequestBody InspeccionMantenimiento inspeccionMantenimiento,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        long diffTrabajo = TimeUnit.DAYS.convert(
                inspeccionMantenimiento.getFechaFinTrabajador().getTime() -
                        inspeccionMantenimiento.getFechaInicioTrabajo().getTime(),
                TimeUnit.MILLISECONDS);

        long diffAtencion = TimeUnit.DAYS.convert(
                inspeccionMantenimiento.getFechaInicioTrabajo().getTime() -
                        inspeccionMantenimiento.getFechaSolicitud().getTime(),
                TimeUnit.MILLISECONDS);

        inspeccionMantenimiento.setTiempoDemoraTrabajo((int) diffTrabajo + 1);
        inspeccionMantenimiento.setTiempoDemoraAtencion((int) diffAtencion + 1);
        inspeccionMantenimiento.setIdInspecccionMantenimiento(Integer.parseInt(id));
        inspeccionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));

        inspeccionMantenimientoService.modificarInspeccionMantenimiento(inspeccionMantenimiento);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 6
     * Servicio que permite cargar los datos de contacto según el tipo de responsable.
     *
     * @param id identificador del tipo de responsable.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/cargarContacto")
    public ResponseEntity<ResultadoBusquedaInspeccionMantenimiento> cargarContacto(@RequestParam String id) {
        TipoResponsable tipoResponsable = new TipoResponsable();
        tipoResponsable.setIdTipoResponsable(Long.valueOf(id));
        ResultadoBusquedaInspeccionMantenimiento resultado = inspeccionMantenimientoService.cargarContacto(tipoResponsable);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 7
     * Servicio que permite cargar los datos de arrendamiento.
     *
     * @param id identificador del arrendamiento.
     * @return Respuesta HTTP con los datos de arrendamiento.
     */
    @GetMapping("/cargarArrendamiento")
    public ResponseEntity<ResultadoBusquedaInspeccionMantenimiento> cargarArrendamiento(@RequestParam String id) {
        ResultadoBusquedaInspeccionMantenimiento resultado = inspeccionMantenimientoService.cargarArrendamiento(id);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 8
     * Servicio que permite cargar los ambientes asociados a un predio e inspección.
     *
     * @param idInmueble identificador del inmueble.
     * @param idInmueblePredio identificador del detalle de ambiente/predio.
     * @param idInspecccionMantenimiento identificador de la inspección de mantenimiento.
     * @return Respuesta HTTP con los ambientes.
     */
    @GetMapping("/cargarAmbiente")
    public ResponseEntity<ResultadoBusquedaInspeccionMantenimiento> cargarAmbiente(
            @RequestParam String idInmueble,
            @RequestParam String idInmueblePredio,
            @RequestParam String idInspecccionMantenimiento) {

        ResultadoBusquedaInspeccionMantenimiento resultado =
                inspeccionMantenimientoService.cargarAmbiente(idInmueble, idInmueblePredio, idInspecccionMantenimiento);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 9
     * Servicio que permite agregar observaciones a los ambientes de una inspección.
     *
     * @param id identificador de la inspección.
     * @param listarObservacionesAmbiente lista de observaciones por ambiente.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/agregarObservacionAmbiente/{id}")
    public ResponseEntity<Void> agregarObservacionAmbiente(
            @PathVariable String id,
            @RequestBody List<InspeccionMantenimiento> listarObservacionesAmbiente,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        InspeccionMantenimiento inspeccionBase = new InspeccionMantenimiento();
        inspeccionBase.setIdInspecccionMantenimiento(Integer.parseInt(id));
        inspeccionBase.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));

        inspeccionMantenimientoService.agregarObservacionAmbiente(listarObservacionesAmbiente, inspeccionBase);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 10
     * Servicio que permite obtener la infraestructura por ambiente.
     *
     * @param id identificador del ambiente.
     * @param idInspecccionMantenimiento identificador de la inspección.
     * @return Respuesta HTTP con la lista de infraestructura.
     */
    @GetMapping("/obtenerInfraestruturaPorAmbiente")
    public ResponseEntity<List<InspeccionMantenimiento>> obtenerInfraestruturaPorAmbiente(
            @RequestParam long id,
            @RequestParam String idInspecccionMantenimiento) {

        List<InspeccionMantenimiento> resultado =
                inspeccionMantenimientoService.obtenerInfraestruturaPorAmbiente(id, idInspecccionMantenimiento);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 11
     * Servicio que permite registrar o actualizar la observación de infraestructura.
     *
     * @param id identificador de la inspección.
     * @param inspeccionMantenimiento datos de la infraestructura/observación.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/registroObservacionInfraestructura/{id}")
    public ResponseEntity<Void> registroObservacionInfraestructura(
            @PathVariable String id,
            @RequestBody InspeccionMantenimiento inspeccionMantenimiento,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        inspeccionMantenimiento.setIdInspecccionMantenimiento(Integer.parseInt(id));

        if (inspeccionMantenimiento.getIdInspecccionMantenimientoInf() > 0) {
            inspeccionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));
            inspeccionMantenimientoService.actualizarObservacionInfraestructura(inspeccionMantenimiento);
        } else {
            inspeccionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
            inspeccionMantenimientoService.agregarObservacionInfraestructura(inspeccionMantenimiento);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 12
     * Servicio que permite actualizar el estado y la observación de un acta de inspección.
     *
     * @param id identificador de la inspección.
     * @param observacion observación del acta.
     * @param codigoEstadoActa código de estado del acta.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/actualizarActaInspeccion")
    public ResponseEntity<Void> actualizarActaInspeccion(
            @RequestParam String id,
            @RequestParam String observacion,
            @RequestParam String codigoEstadoActa) {

        InspeccionMantenimiento inspeccion = new InspeccionMantenimiento();
        inspeccion.setIdInspecccionMantenimiento(Integer.parseInt(id));
        inspeccion.setObservacionPredio(observacion);
        inspeccion.setCodigoEstadoActa(codigoEstadoActa);

        inspeccionMantenimientoService.actualizarActaInspeccion(inspeccion);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 13
     * Servicio que permite generar el reporte de Acta de Inspección en formato Excel.
     *
     * @param id identificador de la inspección.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo Excel generado.
     */
    @GetMapping("/generaExcel")
    public ResponseEntity<ByteArrayResource> generarExcelPorActaInspeccion(
            @RequestParam String id,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        InspeccionMantenimiento inspeccion = inspeccionMantenimientoService.obtenerInspeccion(id);
        inspeccion.setIdInspecccionMantenimiento(Integer.parseInt(id));

        ByteArrayOutputStream stream =
                inspeccionMantenimientoService.generarReporteActaInspeccionExcel(auditoria, inspeccion, id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteActaEntrega.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new ByteArrayResource(stream.toByteArray()));
    }

    /**
     * Endpoint 14
     * Servicio que permite generar el reporte de inspección de mantenimiento en PDF.
     *
     * @param nroSolicitud número de solicitud.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo PDF generado.
     */
    @GetMapping(value = "/reporteInspeccionMantenimientoPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> reporteInspeccionMantenimientoPdf(
            @RequestParam String nroSolicitud,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_Solicitud", nroSolicitud);

        if (inspeccionMantenimientoService.obtenerCantidadInspeccionMantenimiento(parametros) == 0) {
            return ResponseEntity.noContent().build();
        }

        byte[] bytes = servicioREST.exportarJasper(
                "reporteInspeccionMantenimiento.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headers, request)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteInspeccionMantenimiento.pdf")
                .body(new ByteArrayResource(bytes));
    }

    /**
     * Endpoint 15
     * Servicio que permite generar el reporte de inspección de mantenimiento en Excel.
     *
     * @param nroSolicitud número de solicitud.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo Excel generado.
     */
    @GetMapping("/reporteInspeccionMantenimientoExcel")
    public ResponseEntity<ByteArrayResource> reporteInspeccionMantenimientoExcel(
            @RequestParam String nroSolicitud,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_Solicitud", nroSolicitud);

        if (inspeccionMantenimientoService.obtenerCantidadInspeccionMantenimiento(parametros) == 0) {
            return ResponseEntity.noContent().build();
        }

        byte[] bytes = servicioREST.exportarJasper(
                "reporteInspeccionMantenimiento.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headers, request)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteInspeccionMantenimiento.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new ByteArrayResource(bytes));
    }

    /**
     * Endpoint 16
     * Servicio que permite listar el reporte de predios reparados con filtros y paginación.
     *
     * @param mes mes del reporte.
     * @param anio año del reporte.
     * @param idInmueble identificador de inmueble.
     * @param codigoTipoBloque identificador de bloque.
     * @param codigoTipoPredio identificador de predio.
     * @param pagina página solicitada.
     * @param nroRegistros número de registros por página.
     * @return Respuesta HTTP con el resultado.
     */
    @GetMapping("/listarReportePredioReparados")
    public ResponseEntity<?> listarReportePredioReparados(
            @RequestParam String mes,
            @RequestParam String anio,
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String codigoTipoBloque,
            @RequestParam(required = false) String codigoTipoPredio,
            @RequestParam int pagina,
            @RequestParam int nroRegistros) {

        if (pagina == 0 && nroRegistros == 0) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("Se debe especificar paginación.");
            return ResponseEntity.badRequest().body(error);
        }

        InspeccionMantenimiento criterio = new InspeccionMantenimiento();
        criterio.setMes(mes);
        criterio.setAnio(anio);
        criterio.setIdInmueble(idInmueble);
        criterio.setIdInmuebleBloque(codigoTipoBloque);
        criterio.setCodigoPredio(codigoTipoPredio);

        ResultadoBusquedaInspeccionMantenimiento resultado =
                inspeccionMantenimientoService.consultarReporteInspeccionMantenimiento(criterio, pagina, nroRegistros);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 17
     * Servicio que permite generar el reporte de predios reparados en PDF.
     *
     * @param mes mes del reporte.
     * @param anio año del reporte.
     * @param idInmueble identificador de inmueble.
     * @param codigoTipoBloque identificador de bloque.
     * @param codigoTipoPredio identificador de predio.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo PDF generado.
     */
    @GetMapping(value = "/reportePrediosReparadosPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> reportePrediosReparadosPdf(
            @RequestParam String mes,
            @RequestParam String anio,
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String codigoTipoBloque,
            @RequestParam(required = false) String codigoTipoPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_Mes", mes);
        parametros.put("P_Anio", anio);
        parametros.put("P_IdInmueble", idInmueble);
        parametros.put("P_IdBloque", codigoTipoBloque);
        parametros.put("P_IdPredio", codigoTipoPredio);

        if (inspeccionMantenimientoService.obtenerCantidadPrediosReparados(parametros) == 0) {
            return ResponseEntity.noContent().build();
        }

        byte[] bytes = servicioREST.exportarJasper(
                "reportePredioReparados.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_PDF,
                servicioREST.obtenerAuditoriaCreacion(headers, request)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reportePrediosReparados.pdf")
                .body(new ByteArrayResource(bytes));
    }

    /**
     * Endpoint 18
     * Servicio que permite generar el reporte de predios reparados en Excel.
     *
     * @param mes mes del reporte.
     * @param anio año del reporte.
     * @param idInmueble identificador de inmueble.
     * @param codigoTipoBloque identificador de bloque.
     * @param codigoTipoPredio identificador de predio.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo Excel generado.
     */
    @GetMapping("/reportePrediosReparadosExcel")
    public ResponseEntity<ByteArrayResource> reportePrediosReparadosExcel(
            @RequestParam String mes,
            @RequestParam String anio,
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String codigoTipoBloque,
            @RequestParam(required = false) String codigoTipoPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("P_Mes", mes);
        parametros.put("P_Anio", anio);
        parametros.put("P_IdInmueble", idInmueble);
        parametros.put("P_IdBloque", codigoTipoBloque);
        parametros.put("P_IdPredio", codigoTipoPredio);

        if (inspeccionMantenimientoService.obtenerCantidadPrediosReparados(parametros) == 0) {
            return ResponseEntity.noContent().build();
        }

        byte[] bytes = servicioREST.exportarJasper(
                "reportePredioReparados.jasper",
                parametros,
                UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL,
                servicioREST.obtenerAuditoriaCreacion(headers, request)
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reportePrediosReparados.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new ByteArrayResource(bytes));
    }

    /**
     * Endpoint 19
     * Servicio que permite registrar los datos del arrendatario.
     *
     * @param documento datos del formulario en formato JSON.
     * @param archivoAdjunto archivo adjunto (opcional).
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 201 Created en caso de éxito.
     */
    @PostMapping(value = "/registrarActaInspeccion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registrarActaInspeccion(
            @RequestPart("documento") InspeccionMantenimiento documento,
            @RequestPart(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) throws IOException {

        Set<ConstraintViolation<InspeccionMantenimiento>> violations = validator.validate(documento);
        if (!violations.isEmpty()) {
            ErrorValidacion error = new ErrorValidacion();
            violations.forEach(v -> error.agregarError(v.getMessage()));
            return ResponseEntity.badRequest().body(error);
        }

        Archivo archivo = null;
        if (archivoAdjunto != null && !archivoAdjunto.isEmpty()) {
            archivo = new Archivo(
                    archivoAdjunto.getOriginalFilename(),
                    archivoAdjunto.getBytes());
            archivo.setTipoMime(archivoAdjunto.getContentType());
        }

        documento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        inspeccionMantenimientoService.guardarActaInspeccion(documento, archivo);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}