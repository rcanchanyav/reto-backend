package pe.gob.onp.nsai.controller.mantenimiento;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.*;
import pe.gob.onp.nsai.services.EntregaPredioService;

/**
 * Controlador para la gestión de entrega de predios
 */
@RestController
@RequestMapping("/api/v1/entregaPredio")
@Validated
@Slf4j
@RequiredArgsConstructor
public class ServicioEntregaPredioController {

    private final EntregaPredioService entregaPredioService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Consulta de predios para entrega
     */
    @GetMapping("/consulta")
    public ResponseEntity<?> consultarPredios(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros) {

        if(estado == null && pagina == 0 && nroRegistros == 0) {
            ErrorValidacion errores = new ErrorValidacion();
            errores.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.badRequest().body(errores);
        }

        EntregaPredio entregaPredio = new EntregaPredio();
        entregaPredio.setEstadoBusqueda(estado);

        ResultadoBusquedaMantenimiento resultado = entregaPredioService.consultarEntregaPredio(entregaPredio, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Asignar responsable de entrega
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> asignarResponsable(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id,
            @Valid @RequestBody EntregaPredio entregaPredio,
            HttpServletRequest request) {

        if(!"0".equals(entregaPredio.getIdContrato())) {
            Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
            entregaPredio.setAuditoria(auditoria);
            entregaPredio.setIdPersona(Integer.parseInt(entregaPredio.getNombreArrendatario()));
            entregaPredio.setIdEntregaPredio(Long.parseLong(id));

            entregaPredioService.asignarResponsable(entregaPredio);
            return ResponseEntity.noContent().build();
        } else {
            ErrorValidacion errores = new ErrorValidacion();
            errores.agregarError("Restricción en modificación de parámetros");
            return ResponseEntity.badRequest().body(errores);
        }
    }

    /**
     * Endpoint 3
     * Agregar observación al predio
     */
    @PutMapping("/agregarObservacionPredio/{id}")
    public ResponseEntity<Void> agregarObservacionPredio(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id,
            @Valid @RequestBody EntregaPredio entregaPredio,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaModificacion(headers, request);
        entregaPredio.setAuditoria(auditoria);
        entregaPredioService.agregarObservacionPredio(entregaPredio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 4
     * Agregar observación a infraestructura
     */
    @PutMapping("/agregarObservacionInfraestructura/{id}")
    public ResponseEntity<Void> agregarObservacionInfraestructura(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id,
            @Valid @RequestBody List<AmbienteInfraestructura> listAmbienteInfraestructura,
            HttpServletRequest request) {

        AmbienteInfraestructura ambiente = new AmbienteInfraestructura();
        ambiente.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        ambiente.setIdEntregaPredio(Long.parseLong(id));

        entregaPredioService.agregarObservacionInfraestructura(listAmbienteInfraestructura, ambiente);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 5
     * Agregar observación a ambiente
     */
    @PutMapping("/agregarObservacionAmbiente/{id}")
    public ResponseEntity<Void> agregarObservacionAmbiente(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id,
            @Valid @RequestBody List<AmbienteContrato> listAmbienteContrato,
            HttpServletRequest request) {

        AmbienteContrato ambiente = new AmbienteContrato();
        ambiente.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headers, request));
        ambiente.setIdEntregaPredio(Long.parseLong(id));

        entregaPredioService.agregarObservacionAmbiente(listAmbienteContrato, ambiente);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 6
     * Obtener lista de responsables
     */
    @GetMapping("/obtenerResponsables")
    public ResponseEntity<List<TipoResponsable>> obtenerResponsables(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(entregaPredioService.obtenerResponsables());
    }

    /**
     * Endpoint 7
     * Generar reporte Excel de acta de entrega
     */
    @GetMapping(value = "/generaExcel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> generarExcelPorActaEntrega(
            @RequestHeader HttpHeaders headers,
            @RequestParam("filtroCodigoContrato") String filtroCodigoContrato,
            @RequestParam("filtroCodigoPredio") Integer filtroCodigoPredio,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        EntregaPredio entregaPredio = entregaPredioService.obtenerEntregaPredio(filtroCodigoContrato, filtroCodigoPredio);
        List<ReporteActaEntrega> reporte = entregaPredioService.obtenerDatosReporteActaEntrega();

        byte[] excel = entregaPredioService.generarReporteActaEntregaExcel(reporte, entregaPredio, auditoria);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=ReporteActaEntrega.xlsx")
                .body(excel);
    }

    /**
     * Endpoint 8
     * Generar reporte PDF de acta de entrega
     */
    @GetMapping(value = "/generaPdf", produces = "application/pdf")
    public ResponseEntity<byte[]> generarPdfPorActaEntrega(
            @RequestHeader HttpHeaders headers,
            @RequestParam("filtroCodigoContrato") String filtroCodigoContrato,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        List<ReporteActaEntrega> reporte = entregaPredioService.obtenerDatosReporteActaEntrega();

        byte[] pdf = entregaPredioService.generarReporteActaEntregaPdf(reporte, filtroCodigoContrato, auditoria);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=ReporteAmbientePredios.pdf")
                .body(pdf);
    }

    /**
     * Endpoint 9
     * Obtener datos de entrega de predio
     */
    @GetMapping("/obtenerEntregaPredio")
    public ResponseEntity<EntregaPredio> obtenerEntregaPredio(
            @RequestHeader HttpHeaders headers,
            @RequestParam("codigo") String codigo,
            @RequestParam("codigoPredio") Integer codigoPredio) {

        return ResponseEntity.ok(entregaPredioService.obtenerEntregaPredio(codigo, codigoPredio));
    }

    /**
     * Endpoint 10
     * Consulta de ambientes
     */
    @GetMapping("/consultaAmbiente")
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultaAmbiente(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "codigoPredio", required = false) Integer codigoPredio,
            @RequestParam(value = "codigoEntregaPredio", required = false) Integer codigoEntregaPredio,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros) {

        EntregaPredio entregaPredio = new EntregaPredio();
        entregaPredio.setIdContrato(codigo);
        entregaPredio.setIdDetalleInmuPredio(codigoPredio);
        entregaPredio.setIdEntregaPredio(codigoEntregaPredio);

        return ResponseEntity.ok(entregaPredioService.obtenerAmbienteContrato(entregaPredio, pagina, nroRegistros));
    }

    /**
     * Endpoint 11
     * Obtener infraestructura por ambiente
     */
    @GetMapping("/obtenerInfraestruturaPorAmbiente/{id}")
    public ResponseEntity<List<AmbienteInfraestructura>> obtenerInfraestruturaPorAmbiente(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") long id) {

        return ResponseEntity.ok(entregaPredioService.obtenerInfraestruturaPorAmbiente(id));
    }

    /**
     * Endpoint 12
     * Obtener responsable asignado
     */
    @GetMapping("/obtenerResponsableAsignado/{id}")
    public ResponseEntity<EntregaPredio> obtenerResponsableAsignado(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id) {

        return ResponseEntity.ok(entregaPredioService.obtenerResponsableAsignado(id));
    }

    /**
     * Endpoint 13
     * Actualizar responsable
     */
    @PutMapping("/actualizarResponsable/{id}")
    public ResponseEntity<Void> actualizarResponsable(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String id,
            @Valid @RequestBody EntregaPredio entregaPredio,
            HttpServletRequest request) {

        Auditoria auditoria = servicioREST.obtenerAuditoriaModificacion(headers, request);
        entregaPredio.setAuditoria(auditoria);
        entregaPredio.setIdPersona(Integer.parseInt(entregaPredio.getNombreArrendatario()));

        entregaPredioService.actualizarResponsable(entregaPredio);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 14
     * Registrar documento de acta
     */
    @PostMapping
    public ResponseEntity<?> registrarDocumento(
            @RequestHeader HttpHeaders headers,
            @Valid EntregaPredio entregaPredio,
            @RequestParam("archivoAdjunto") MultipartFile archivoAdjunto,
            HttpServletRequest request) {

        try {
            Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
            entregaPredio.setAuditoria(auditoria);

            Archivo archivo = new Archivo(
                    archivoAdjunto.getOriginalFilename(),
                    archivoAdjunto.getBytes()
            );
            archivo.setTipoMime(archivoAdjunto.getContentType());

            entregaPredioService.guardarDocumentoActa(entregaPredio, archivo);
            return ResponseEntity.noContent().build();

        } catch (IOException ex) {
            log.error("Error al procesar archivo adjunto", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorSistema(servicioREST.idError()));
        }
    }

    /**
     * Endpoint 15
     * Visualizar documento adjunto
     */
    @GetMapping("/documentoAdjunto/{id}")
    public ResponseEntity<byte[]> visualizarDocumento(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String idContratoDocumento) {

        EntregaPredio documento = new EntregaPredio();
        documento.setIdContrato(idContratoDocumento);

        Archivo archivo = entregaPredioService.obtenerDocumentoAdjunto(documento);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + archivo.getNombre())
                .header("Access-Control-Expose-Headers", "Content-Disposition")
                .body(archivo.getArchivo());
    }

    /**
     * Endpoint 16
     * Generar reporte PDF de seguimiento de predio
     */
    @GetMapping(value = "/reporteSegPredioPdf", produces = "application/pdf")
    public ResponseEntity<byte[]> reporteSegPredioPdf(
            @RequestHeader HttpHeaders headers,
            @RequestParam("estado") String estado) {

        byte[] pdf = entregaPredioService.generarReporteSeguimientoPredioPdf(estado);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporteSeguimientoPredio.pdf")
                .body(pdf);
    }

    /**
     * Endpoint 17
     * Generar reporte Excel de seguimiento de predio
     */
    @GetMapping(value = "/reporteSegPredioExcel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> reporteSegPredioExcel(
            @RequestHeader HttpHeaders headers,
            @RequestParam("estado") String estado) {

        byte[] excel = entregaPredioService.generarReporteSeguimientoPredioExcel(estado);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporteSeguimientoPredio.xlsx")
                .body(excel);
    }

    /**
     * Endpoint 18
     * Consultar documentos de acta
     */
    @GetMapping("/consultaDocumentoActa")
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultarDocumentoActa(
            @RequestHeader HttpHeaders headers,
            @RequestParam("codigo") String codigo,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros) {

        EntregaPredioDocumento actaDocumento = new EntregaPredioDocumento();
        actaDocumento.setIdContrato(codigo);

        Map<String,Object> parametros = new HashMap<>();
        parametros.put("idContrato", actaDocumento.getIdContrato());
        parametros.put("pagina", pagina);
        parametros.put("paginacion", nroRegistros);

        ResultadoBusquedaMantenimiento resultado = new ResultadoBusquedaMantenimiento();
        resultado.setListaActaDocumento(entregaPredioService.obtenerDocumentosActa(parametros));
        resultado.getPaginacion().setNumeroTotalRegistros(resultado.getListaActaDocumento().size());

        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 19
     * Eliminar documento
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocumento(
            @RequestHeader HttpHeaders headers,
            @PathVariable("id") String idDocumento,
            HttpServletRequest request) {

        EntregaPredioDocumento documento = new EntregaPredioDocumento();
        documento.setIdActaDocumento(Long.parseLong(idDocumento));
        documento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headers, request));

        entregaPredioService.eliminarDocumento(documento);
        return ResponseEntity.noContent().build();
    }
}