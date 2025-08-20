package pe.gob.onp.nsai.controller.mantenimiento;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
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
import pe.gob.onp.nsai.services.DevolucionPredioService;
import pe.gob.onp.nsai.util.UConstantes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Controlador REST para la gestión de Devolución de Predios.
 * Migrado de JAX-RS a Spring Boot con manejo centralizado de errores.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/devolucionPredio")
public class ServicioDevolucionPredioController {

    private final DevolucionPredioService devolucionPredioService;
    private final ServicioREST servicioREST;
    private final Validator validator;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de devolución de predios según parámetros de búsqueda
     *
     * @param idInmueble identificador del inmueble.
     * @param idInmuebleBloque identificador del bloque del inmueble.
     * @param idInmueblePredio identificador del predio del inmueble.
     * @param pagina página solicitada (>=1).
     * @param nroRegistros número de registros por página.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/consulta")
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultarDevolucionPredios(
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String idInmuebleBloque,
            @RequestParam(required = false) String idInmueblePredio,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int nroRegistros) {

        ResultadoBusquedaMantenimiento resultado = devolucionPredioService.consultarDevolucionPredio(
                idInmueble, idInmuebleBloque, idInmueblePredio, pagina, nroRegistros);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos de acta de devolución según parámetros de búsqueda
     *
     * @param idInmueble identificador del inmueble.
     * @param idInmueblePredio identificador del predio del inmueble.
     * @param idInmuebleBloque identificador del bloque del inmueble.
     * @param pagina página solicitada (>=1).
     * @param nroRegistros número de registros por página.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/consultaActaDevolucion")
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultaActaDevolucion(
            @RequestParam(required = false) String idInmueble,
            @RequestParam(required = false) String idInmueblePredio,
            @RequestParam(required = false) String idInmuebleBloque,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int nroRegistros) {

        DevolucionPredio devolucionPredio = new DevolucionPredio();
        devolucionPredio.setIdInmueble(idInmueble);
        devolucionPredio.setIdInmueblePredio(idInmueblePredio);
        devolucionPredio.setIdInmuebleBloque(idInmuebleBloque);

        ResultadoBusquedaMantenimiento resultado = devolucionPredioService.consultarActaDevolucion(
                devolucionPredio, pagina, nroRegistros);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 3
     * Servicio que permite listar actas base
     *
     * @param idContrato identificador del contrato.
     * @param idInmueblePredio identificador del predio del inmueble.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/listarActaBase")
    public ResponseEntity<ResultadoBusquedaMantenimiento> listarActaBase(
            @RequestParam String idContrato,
            @RequestParam int idInmueblePredio) {

        DevolucionPredio devolucionPredio = new DevolucionPredio();
        devolucionPredio.setIdContrato(idContrato);
        devolucionPredio.setIdDetalleInmuPredio(idInmueblePredio);

        ResultadoBusquedaMantenimiento resultado = devolucionPredioService.consultarActaBase(devolucionPredio);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 4
     * Servicio para asignar responsable de entrega
     *
     * @param codigoDevolucionPredio identificador de la devolución del predio.
     * @param devolucionPredio datos del predio a devolver.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/{codigoDevolucionPredio}")
    public ResponseEntity<?> asignarResponsable(
            @PathVariable String codigoDevolucionPredio,
            @Valid @RequestBody DevolucionPredio devolucionPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        if (devolucionPredio.getIdContrato().equals("0")) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("Restricción de modificación de parámetros");
            return ResponseEntity.badRequest().body(error);
        }

        devolucionPredio.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        devolucionPredio.setIdPersona(Integer.parseInt(devolucionPredio.getNombreArrendatario()));
        devolucionPredioService.asignarResponsable(devolucionPredio);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 5
     * Servicio para agregar observación del predio
     *
     * @param codigoDevolucionPredio identificador de la devolución del predio.
     * @param devolucionPredio datos del predio a devolver.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/agregarObservacionPredio/{codigoDevolucionPredio}")
    public ResponseEntity<Void> agregarObservacionPredio(
            @PathVariable String codigoDevolucionPredio,
            @RequestBody DevolucionPredio devolucionPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        devolucionPredio.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));
        devolucionPredioService.agregarObservacionPredio(devolucionPredio);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 6
     * Servicio para agregar observación de infraestructura
     *
     * @param idDetalleAmbiente identificador del detalle del ambiente.
     * @param ambienteInfraestructura datos de la infraestructura.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/agregarObservacionInfraestructura/{idDetalleAmbiente}")
    public ResponseEntity<Void> agregarObservacionInfraestructura(
            @PathVariable int idDetalleAmbiente,
            @RequestBody AmbienteInfraestructura ambienteInfraestructura,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        if (idDetalleAmbiente == 0) {
            ambienteInfraestructura.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        } else {
            ambienteInfraestructura.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));
        }

        devolucionPredioService.agregarObservacionInfraestructura(ambienteInfraestructura, idDetalleAmbiente);
        devolucionPredioService.actualizarInfraestructura(ambienteInfraestructura, idDetalleAmbiente);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 7
     * Servicio para agregar observación de ambiente
     *
     * @param idDevolucionPredio identificador de la devolución del predio.
     * @param listAmbienteContrato lista de ambientes del contrato.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/agregarObservacionAmbiente/{idDevolucionPredio}")
    public ResponseEntity<Void> agregarObservacionAmbiente(
            @PathVariable String idDevolucionPredio,
            @RequestBody List<AmbienteContrato> listAmbienteContrato,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        AmbienteContrato ambienteContrato = new AmbienteContrato();
        ambienteContrato.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        ambienteContrato.setIdDevolucionPredio(Long.parseLong(idDevolucionPredio));

        devolucionPredioService.agregarObservacionAmbiente(listAmbienteContrato, ambienteContrato);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 8
     * Servicio para obtener responsables
     *
     * @return Respuesta HTTP con la lista de tipos de responsables.
     */
    @GetMapping("/obtenerResponsables")
    public ResponseEntity<List<TipoResponsable>> obtenerResponsables() {
        List<TipoResponsable> listaTipoResponsable = devolucionPredioService.obtenerResponsables();
        return ResponseEntity.ok(listaTipoResponsable);
    }

    /**
     * Endpoint 9
     * Servicio que permite generar reporte de acta de entrega en Excel
     *
     * @param filtroCodigoContrato código del contrato.
     * @param filtroCodigoPredio código del predio.
     * @param devolucionPredio datos de la devolución del predio.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo Excel generado.
     */
    @PutMapping("/generaExcel")
    public ResponseEntity<ByteArrayResource> generarExcelPorActaEntrega(
            @RequestParam String filtroCodigoContrato,
            @RequestParam Integer filtroCodigoPredio,
            @RequestBody DevolucionPredio devolucionPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        DevolucionPredio devolucionPredioData = devolucionPredioService.obtenerDevolucionPredio(filtroCodigoContrato);
        devolucionPredioData.setIdActaBase(devolucionPredio.getIdActaBase());

        List<ReporteActaEntrega> listaReporteActaEntrega = devolucionPredioService.obtenerDatosReporteActaEntrega();
        ByteArrayOutputStream archivoExcel = devolucionPredioService.generarReporteActaEntregaExcel(
                listaReporteActaEntrega, devolucionPredioData, auditoria);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteActaEntrega.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new ByteArrayResource(archivoExcel.toByteArray()));
    }

    /**
     * Endpoint 10
     * Servicio que permite generar reporte de acta de entrega en PDF
     *
     * @param filtroCodigoContrato código del contrato.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Archivo PDF generado.
     */
    @GetMapping(value = "/generaPdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> generarPdfPorActaEntrega(
            @RequestParam String filtroCodigoContrato,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        List<ReporteActaEntrega> listaReporteActaEntrega = devolucionPredioService.obtenerDatosReporteActaEntrega();

        byte[] bytes = devolucionPredioService.generarReporteActaEntregaPdf(
                listaReporteActaEntrega, filtroCodigoContrato, request.getServletContext(), auditoria);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteAmbientePredios.pdf")
                .body(new ByteArrayResource(bytes));
    }

    /**
     * Endpoint 11
     * Servicio que permite obtener los datos de una devolución de predio por su ID
     *
     * @param id identificador de la devolución del predio.
     * @return Respuesta HTTP con los datos de la devolución.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DevolucionPredio> obtenerDevolucionPredio(@PathVariable String id) {
        DevolucionPredio devolucionPredio = devolucionPredioService.obtenerDevolucionPredio(id);
        return ResponseEntity.ok(devolucionPredio);
    }

    /**
     * Endpoint 12
     * Servicio que permite consultar ambientes de contrato
     *
     * @param codigo código de búsqueda.
     * @param codigoPredio código del predio.
     * @param pagina página solicitada (>=1).
     * @param nroRegistros número de registros por página.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/consultaAmbiente")
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultaAmbiente(
            @RequestParam String codigo,
            @RequestParam int codigoPredio,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int nroRegistros) {

        DevolucionPredio devolucionPredio = new DevolucionPredio();
        devolucionPredio.setIdDevolucionPredio(Long.parseLong(codigo));
        devolucionPredio.setIdDetalleInmuPredio(codigoPredio);

        ResultadoBusquedaMantenimiento resultado = devolucionPredioService.obtenerAmbienteContrato(
                devolucionPredio, pagina, nroRegistros);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 13
     * Servicio para obtener la infraestructura por ambiente
     *
     * @param id identificador del ambiente.
     * @param idDevolucionPredio identificador de la devolución del predio.
     * @return Respuesta HTTP con la lista de infraestructuras.
     */
    @GetMapping("/obtenerInfraestruturaPorAmbiente")
    public ResponseEntity<List<AmbienteInfraestructura>> obtenerInfraestruturaPorAmbiente(
            @RequestParam long id,
            @RequestParam String idDevolucionPredio) {

        List<AmbienteInfraestructura> ambienteInfraestructura = devolucionPredioService.obtenerInfraestruturaPorAmbiente(
                id, idDevolucionPredio);

        return ResponseEntity.ok(ambienteInfraestructura);
    }

    /**
     * Endpoint 14
     * Servicio para obtener el responsable asignado
     *
     * @param id identificador del contrato.
     * @return Respuesta HTTP con los datos del responsable asignado.
     */
    @GetMapping("/obtenerResponsableAsignado/{id}")
    public ResponseEntity<DevolucionPredio> obtenerResponsableAsignado(@PathVariable Long id) {
        DevolucionPredio devolucionPredio = devolucionPredioService.obtenerResponsableAsignado(String.valueOf(id));
        return ResponseEntity.ok(devolucionPredio);
    }

    /**
     * Endpoint 15
     * Servicio para actualizar responsable
     *
     * @param codigoDevolucionPredio identificador de la devolución del predio.
     * @param devolucionPredio datos del predio a devolver.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @PutMapping("/actualizarResponsable/{codigoDevolucionPredio}")
    public ResponseEntity<Void> actualizarResponsable(
            @PathVariable String codigoDevolucionPredio,
            @RequestBody DevolucionPredio devolucionPredio,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        devolucionPredio.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));
        devolucionPredio.setIdPersona(Integer.parseInt(devolucionPredio.getNombreArrendatario()));
        devolucionPredio.setIdDevolucionPredio(Long.parseLong(codigoDevolucionPredio));

        devolucionPredioService.actualizarResponsable(devolucionPredio);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 16
     * Servicio para registrar documento de acta
     *
     * @param documento datos del formulario.
     * @param archivoAdjunto archivo adjunto.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 201 Created.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registrar(
            @RequestPart("documento") DevolucionPredio documento,
            @RequestPart(value = "archivoAdjunto", required = false) MultipartFile archivoAdjunto,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) throws IOException {

        Set<ConstraintViolation<DevolucionPredio>> violations = validator.validate(documento);
        if (!violations.isEmpty()) {
            ErrorValidacion error = new ErrorValidacion();
            violations.forEach(v -> error.agregarError(v.getMessage()));
            return ResponseEntity.badRequest().body(error);
        }

        Archivo archivoDocumento = null;
        if (archivoAdjunto != null && !archivoAdjunto.isEmpty()) {
            archivoDocumento = new Archivo(
                    archivoAdjunto.getOriginalFilename(),
                    archivoAdjunto.getBytes());
            archivoDocumento.setTipoMime(archivoAdjunto.getContentType());
        }

        documento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        devolucionPredioService.guardarDocumentoActa(documento, archivoDocumento);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint 17
     * Servicio para visualizar archivo adjunto
     *
     * @param id identificador del documento.
     * @return Archivo adjunto.
     */
    @GetMapping("/archivoAdjunto/{id}")
    public ResponseEntity<?> visualizarArchivo(@PathVariable String id) {
        if (id == null) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("Se debe especificar criterio de búsqueda");
            return ResponseEntity.badRequest().body(error);
        }

        DevolucionPredio devolucionPred = new DevolucionPredio();
        devolucionPred.setIdDetalleActaDocumento(Integer.parseInt(id));

        Archivo archivoAdjunto = devolucionPredioService.obtenerArchivoAdjunto(devolucionPred);
        if (archivoAdjunto == null) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("Archivo no encontrado");
            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + archivoAdjunto.getNombre())
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .body(archivoAdjunto.getArchivo());
    }

    /**
     * Endpoint 18
     * Servicio para consultar documentos de acta
     *
     * @param codigo código de búsqueda.
     * @param pagina página solicitada (>=1).
     * @param nroRegistros número de registros por página.
     * @return Respuesta HTTP con el resultado de la búsqueda.
     */
    @GetMapping("/consultaDocumentoActa")
    public ResponseEntity<?> consultarDocumentoActa(
            @RequestParam String codigo,
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "10") int nroRegistros) {

        if (codigo == null && pagina == 0 && nroRegistros == 0) {
            ErrorValidacion error = new ErrorValidacion();
            error.agregarError("Se debe especificar criterio mínimo de búsqueda");
            return ResponseEntity.badRequest().body(error);
        }

        EntregaPredioDocumento actaDocumento = new EntregaPredioDocumento();
        actaDocumento.setIdContrato(codigo);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idContrato", actaDocumento.getIdContrato());
        parametros.put("pagina", pagina);
        parametros.put("paginacion", nroRegistros);

        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        List<EntregaPredioDocumento> listaDocumentosActa = devolucionPredioService.obtenerDocumentosActa(parametros);
        int cantidadRegistrosBusquedaActa = listaDocumentosActa.size();

        Paginacion paginas = new Paginacion();
        paginas.setNumeroTotalRegistros(cantidadRegistrosBusquedaActa);
        resultado.setPaginacion(paginas);
        resultado.setListaActaDocumento(listaDocumentosActa);

        return ResponseEntity.ok(resultado);
    }
    /**
     * Endpoint 19
     * Servicio para eliminar documento
     *
     * @param idDocumento identificador del documento.
     * @param headers cabeceras HTTP.
     * @param request contexto de la solicitud.
     * @return Respuesta HTTP 204 No Content.
     */
    @DeleteMapping("/{idDocumento}")
    public ResponseEntity<?> eliminarDocumento(
            @PathVariable String idDocumento,
            @RequestHeader HttpHeaders headers,
            HttpServletRequest request) {

        EntregaPredioDocumento eliminarDocumento = new EntregaPredioDocumento();
        eliminarDocumento.setIdActaDocumento(Long.parseLong(idDocumento));

        Set<ConstraintViolation<EntregaPredioDocumento>> violations = validator.validate(eliminarDocumento);
        if (!violations.isEmpty()) {
            ErrorValidacion error = new ErrorValidacion();
            violations.forEach(v -> error.agregarError(v.getMessage()));
            return ResponseEntity.badRequest().body(error);
        }

        eliminarDocumento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));
        devolucionPredioService.eliminarDocumento(eliminarDocumento);

        return ResponseEntity.noContent().build();
    }
}