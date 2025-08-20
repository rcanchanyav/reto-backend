package pe.gob.onp.nsai.controller.mantenimiento;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.Archivo;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDocumento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.ProgramacionMantenimientoDocumentoService;
import pe.gob.onp.nsai.util.GeneradorID;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para gestionar los documentos de la programacion de mantenimientos.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/programacionMantenimientoDocumento")
public class ServicioProgramacionMantenimientoDocumentoController {

    private final ProgramacionMantenimientoDocumentoService programacionMantenimientoDocumentoService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Servicio que permite consultar los documentos de la programacion de mantenimientos.
     *
     * @param codigoProgramacion identificador del documento de la programacion de mantenimiento.
     * @param pagina pagina actual del documento.
     * @param nroRegistros cantidad de registros por pagina.
     * @return Respuesta HTTP con el resultado de la busqueda o errores de validacion.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @GetMapping("/consulta")
    public ResponseEntity<?> consultarDocumentoProgramacionMantenimiento(
            @RequestParam(name = "codigoProgramacion", required = false) Integer codigoProgramacion,
            @RequestParam(name = "pagina", defaultValue = "0") int pagina,
            @RequestParam(name = "nroRegistros", defaultValue = "5") int nroRegistros
    ) throws Exception {
        log.info("GET /consulta : codigoProgramacion={}, pagina={}, nroRegistros={}", codigoProgramacion, pagina, nroRegistros);

        // Validacion de criterios minimos.
        if (codigoProgramacion == null && pagina == 0 && nroRegistros == 0) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se debe proporcionar al menos un criterio de busqueda.");
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoProgramacionMantenimiento", codigoProgramacion);
        parametros.put("pagina", pagina);
        parametros.put("paginacion", nroRegistros);

        var criterio = new ProgramacionMantenimientoDocumento();
        criterio.setCodigoProgramacion(codigoProgramacion);

        var totalRegistros = programacionMantenimientoDocumentoService.obtenerCantidadRegistrosBusquedaProgramacionMantenimiento(criterio);
        var documentos = programacionMantenimientoDocumentoService.obtenerDocumentosProgramacionMantenimiento(parametros);

        var paginacion = new Paginacion();
        paginacion.setNumeroTotalRegistros(totalRegistros);

        var resultado = new ResultadoBusquedaMantenimiento();
        resultado.setPaginacion(paginacion);
        resultado.setListaProgramacioMantenimientoDocumento(documentos);

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio para registrar el documento de la programación del mantenimiento.
     *
     * @param programacionMantenimientoDocumento objeto con los datos del documento.
     * @param archivoAdjunto archivo adjunto.
     * @param headersRequest cabeceras de la peticion HTTP.
     * @param request objeto HttpServletRequest.
     * @return Respuesta HTTP indicando el exito de la operacion.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @PostMapping
    public ResponseEntity<Void> registrar(
            @ModelAttribute ProgramacionMantenimientoDocumento programacionMantenimientoDocumento,
            @RequestParam("archivoAdjunto") MultipartFile archivoAdjunto,
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request
    ) throws Exception {
        log.info("POST /");

        Archivo archivoDocumento = new Archivo(archivoAdjunto.getOriginalFilename(), archivoAdjunto.getBytes());
        archivoDocumento.setTipoMime(archivoAdjunto.getContentType());
        programacionMantenimientoDocumento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        programacionMantenimientoDocumentoService.guardarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento, archivoDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 3
     * Servicio para obtener los datos del documento de la programación de mantenimiento.
     *
     * @param id identificador del documento.
     * @return Respuesta HTTP con los datos del documento.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @GetMapping("/consultaDocumentoId/{id}")
    public ResponseEntity<?> obtenerDatosDocumentoProgramacionMantenimiento(
            @PathVariable("id") String id
    ) throws Exception {
        log.info("GET /consultaDocumentoId/{} : id={}", id, id);
        long codigoDecodificado = GeneradorID.decodificar(id);
        ProgramacionMantenimientoDocumento programacionMantenimientoDocumento = programacionMantenimientoDocumentoService.obtenerDatosDocumentoProgramacionMantenimiento(codigoDecodificado);
        return ResponseEntity.ok(programacionMantenimientoDocumento);
    }

    /**
     * Endpoint 4
     * Servicio para modificar el documento de la programación de mantenimiento.
     *
     * @param id identificador del documento.
     * @param programacionMantenimientoDocumento objeto con los datos del documento.
     * @param archivoAdjunto archivo adjunto.
     * @param headersRequest cabeceras de la peticion HTTP.
     * @param request objeto HttpServletRequest.
     * @return Respuesta HTTP indicando el exito de la operacion.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(
            @PathVariable("id") String id,
            @ModelAttribute ProgramacionMantenimientoDocumento programacionMantenimientoDocumento,
            @RequestParam(name = "archivoAdjunto", required = false) MultipartFile archivoAdjunto,
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request
    ) throws Exception {
        log.info("PUT /{id} : id={}", id);
        long codigoDecodificado = GeneradorID.decodificar(id);
        programacionMantenimientoDocumento.setCodigoProgramacionDocumento(codigoDecodificado);

        // Configura la auditoría y realiza la modificación.
        programacionMantenimientoDocumento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));

        Archivo archivoDocumento = null;
        if (archivoAdjunto != null) {
            archivoDocumento = new Archivo(archivoAdjunto.getOriginalFilename(), archivoAdjunto.getBytes());
            archivoDocumento.setTipoMime(archivoAdjunto.getContentType());
        }

        // Aquí se utiliza el método proporcionado en la versión anterior.
        programacionMantenimientoDocumentoService.modificarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento, archivoDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 5
     * Servicio para eliminar el documento de la programación de mantenimiento.
     *
     * @param id identificador del documento de la programación de mantenimiento.
     * @return Respuesta HTTP indicando el exito de la operacion.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocumento(
            @PathVariable("id") String id,
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request
    ) throws Exception {
        log.info("DELETE /{id} : id={}", id);
        ProgramacionMantenimientoDocumento programacionMantenimientoDocumento = new ProgramacionMantenimientoDocumento();
        long codigoDecodificado = GeneradorID.decodificar(id);
        programacionMantenimientoDocumento.setCodigoProgramacionDocumento(codigoDecodificado);

        programacionMantenimientoDocumento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        programacionMantenimientoDocumentoService.eliminarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 6
     * Método para visualizar el documento adjunto.
     *
     * @param id Código de documento de la programación de mantenimiento.
     * @return Respuesta HTTP con el archivo para su visualización o descarga.
     * @throws Exception en caso de errores en la capa de servicio.
     */
    @GetMapping("/documentoAdjunto/{id}")
    public ResponseEntity<StreamingResponseBody> visualizarDocumento(
            @PathVariable("id") String id
    ) throws Exception {
        log.info("GET /documentoAdjunto/{} : id={}", id, id);

        if (id == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se debe proporcionar el código del documento.");
            return ResponseEntity.badRequest().build();
        }

        ProgramacionMantenimientoDocumento documento = new ProgramacionMantenimientoDocumento();
        long codigoDecodificado = GeneradorID.decodificar(id);
        documento.setCodigoProgramacionDocumento(codigoDecodificado);
        Archivo documentoAdjunto = programacionMantenimientoDocumentoService.obtenerDocumentoAdjunto(documento);

        // Usamos StreamingResponseBody para manejar grandes archivos de forma eficiente.
        StreamingResponseBody responseBody = outputStream -> {
            outputStream.write(documentoAdjunto.getArchivo());
        };

        // Se configura la respuesta HTTP con el nombre del archivo original
        // y el tipo de contenido para su descarga.
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + documentoAdjunto.getNombre() + "\"")
                .contentType(MediaType.parseMediaType(documentoAdjunto.getTipoMime()))
                .body(responseBody);
    }
}