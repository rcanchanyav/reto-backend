package pe.gob.onp.nsai.controller.mantenimiento;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.Paginacion;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDocumento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.ProgramacionMantenimientoDocumentoService;

/**
 * Controlador REST para gestionar los documentos de la programación de mantenimientos.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/programacionMantenimientoDocumento")
public class ServicioProgramacionMantenimientoDocumentoController {

  private final ProgramacionMantenimientoDocumentoService programacionMantenimientoDocumentoService;

  @GetMapping("/consulta")
  public ResponseEntity<?> consultarDocumentoProgramacionMantenimiento(
      @RequestParam(name = "codigoProgramacion", required = false) Integer codigoProgramacion,
      @RequestParam(name = "pagina", defaultValue = "0") int pagina,
      @RequestParam(name = "nroRegistros", defaultValue = "5") int nroRegistros
  ) throws Exception {
    // El bloque try-catch se mueve a un manejador de excepciones global (@ControllerAdvice).
    log.info("GET /consulta : codigoProgramacion={}, pagina={}, nroRegistros={}", codigoProgramacion, pagina, nroRegistros);

    if (codigoProgramacion == null && pagina == 0 && nroRegistros == 0) {
      ErrorValidacion erroresValidacion = new ErrorValidacion();
      // Suponiendo que tienes una forma de obtener los mensajes.
      erroresValidacion.agregarError("Se debe proporcionar al menos un criterio de búsqueda.");
      // Se devuelve un 400 Bad Request, que es más estándar para errores de validación.
      return ResponseEntity.badRequest().body(erroresValidacion);
    }

    Map<String, Object> parametros = new HashMap<>();
    parametros.put("codigoProgramacionMantenimiento", codigoProgramacion);
    parametros.put("pagina", pagina);
    parametros.put("paginacion", nroRegistros);

    // La lógica de negocio permanece igual.
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

}
