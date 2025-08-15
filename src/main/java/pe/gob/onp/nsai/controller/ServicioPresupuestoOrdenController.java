package pe.gob.onp.nsai.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import pe.gob.onp.nsai.dto.Archivo;
import pe.gob.onp.nsai.dto.Auditoria;
import pe.gob.onp.nsai.dto.ErrorSistema;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.PresupuestoOrden;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.onp.nsai.services.PresupuestoOrdenService;
import pe.gob.onp.nsai.util.UConstantes;
import pe.gob.onp.nsai.util.UJson;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/presupuestoOrden")
public class ServicioPresupuestoOrdenController {

  public final PresupuestoOrdenService presupuestoOrdenServicio;

  public final ServicioREST servicioREST;

  private final Validator validador;

  @GetMapping("/consulta")
  public ResponseEntity<?> consultarPresupuestosOrden(
      @RequestParam("pagina") int pagina,
      @RequestParam("nroRegistros") int nroRegistros,
      @RequestParam("idInmueble") String idInmueble,
      @RequestParam("idInmueblePredio") String idInmueblePredio,
      @RequestParam("idInmuebleBloque") String idInmuebleBloque) {
    try {
      if ((pagina == 0) && (nroRegistros == 0)) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {

        var presupuestoOrden = new PresupuestoOrden();
        presupuestoOrden.setIdInmueble(idInmueble);
        presupuestoOrden.setIdInmueblePredio(idInmueblePredio);
        presupuestoOrden.setIdInmuebleBloque(idInmuebleBloque);


        var resultado = presupuestoOrdenServicio.consultarPresupuestoOrden(presupuestoOrden, pagina, nroRegistros);
        return ResponseEntity.ok().body(resultado);

      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error:{}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/generarPdf")
  public ResponseEntity<byte[]> generarPDF(
      @RequestParam("idInmueble") String idInmueble,
      @RequestParam("idInmueblePredio") String idInmueblePredio,
      @RequestParam("idInmuebleBloque") String idInmuebleBloque,
      @RequestHeader HttpHeaders headersRequest,
      HttpServletRequest request) {
    try {
      String jasper = "reporteOrdenesDeTrabajoPdf.jasper";
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("idInmueble", idInmueble);
      parametros.put("idInmueblePredio", idInmueblePredio);
      parametros.put("idInmuebleBloque", idInmuebleBloque);

      var auditoria = servicioREST.obtenerAuditoriaCreacion(headersRequest, request);

      byte[] bytes = servicioREST.exportarJasper(jasper, parametros,
          UConstantes.TIPO_ARCHIVO_REPORTE_PDF, auditoria);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=ReporteOrdenesDeTrabajo.pdf");
      return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    } catch (Exception excepcion) {
      log.error("Error al generar reporte PDF", excepcion);
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/generarExcel")
  public ResponseEntity<byte[]> generarExcel(
      @RequestParam("idInmueble") String idInmueble,
      @RequestParam("idInmueblePredio") String idInmueblePredio,
      @RequestParam("idInmuebleBloque") String idInmuebleBloque,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      String jasper = "reporteOrdenesDeTrabajoExcel.jasper";
      Map<String, Object> parametros = new HashMap<String, Object>();
      parametros.put("idInmueble", idInmueble);
      parametros.put("idInmueblePredio", idInmueblePredio);
      parametros.put("idInmuebleBloque", idInmuebleBloque);

      var auditoria = servicioREST.obtenerAuditoriaCreacion(headersRequest, request);

      byte[] bytes = servicioREST.exportarJasper(jasper, parametros,
          UConstantes.TIPO_ARCHIVO_REPORTE_EXCEL, auditoria);
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION,
          "attachment; filename=ReporteOrdenesDeTrabajo.xlsx");
      return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }catch(Exception excepcion){
      log.error("Error al generar reporte Excel", excepcion);
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/guardarPresupuesto")
  public ResponseEntity<?> guardarPresupuesto(
      @RequestBody @Valid PresupuestoOrden presupuestoOrden,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Verificar si ya existe el número de solicitud
      Integer cantidadNroSol = presupuestoOrdenServicio.obtenerCantidadNroSol(presupuestoOrden);
      if (cantidadNroSol > 0) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("presupuesto.orden.numero.solicitud.duplicado"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }

      // Validar el objeto PresupuestoOrden
      var constraintViolations = validador.validate(presupuestoOrden);
      if (constraintViolations.isEmpty()) {
        presupuestoOrden.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        presupuestoOrdenServicio.guardarPresupuesto(presupuestoOrden);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        // Si hay errores de validación
        var erroresValidacion = new ErrorValidacion();
        constraintViolations.forEach(constraint ->
            erroresValidacion.agregarError(constraint.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }

    } catch (Exception excepcion) {
      // Manejo de excepciones
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error:{}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/consultaReg")
  public ResponseEntity<?> consultaPres(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "5") int nroRegistros,
      @RequestParam("idPresupuestoOrden") int idPresupuestoOrden) {
    try {
      // Validación de parámetros
      if (pagina == 0 && nroRegistros == 0) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        // Crear el objeto PresupuestoOrden y realizar la consulta
        var presupuestoOrden = new PresupuestoOrden();
        presupuestoOrden.setIdPresupuestoOrden(idPresupuestoOrden);

        var resultado =
            presupuestoOrdenServicio.consultarPresReg(presupuestoOrden, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: " + errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> modificarPresupuesto(
      @PathVariable("id") String id,
      @RequestBody @Valid PresupuestoOrden presupuestoOrden,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Verificar si ya existe el número de solicitud
      var cantidadNroSol = presupuestoOrdenServicio.obtenerCantidadNroSol(presupuestoOrden);
      if (cantidadNroSol > 0) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("presupuesto.orden.numero.solicitud.duplicado"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }
      // Añadir auditoría
      presupuestoOrden.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

      // Realizar la modificación
      presupuestoOrdenServicio.modificarPresupuesto(presupuestoOrden);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/consultaRealizar")
  public ResponseEntity<?> consultaRealizaPresupuesto(
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "5") int nroRegistros,
      @RequestParam("nroSolicitud") String nroSolicitud) {
    try {
      // Validación de parámetros
      if (pagina == 0 && nroRegistros == 0) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        // Crear objeto PresupuestoOrden y realizar la consulta
        var presupuestoOrden = new PresupuestoOrden();
        presupuestoOrden.setNroSolicitudSiga(nroSolicitud);

        var resultado =
            presupuestoOrdenServicio.consultaRealizarPre(presupuestoOrden, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
      }
    } catch (Exception excepcion) {
      var errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PostMapping("/guardarGastoPresupuesto")
  public ResponseEntity<?> guardarGastoPresupuesto(
      @RequestBody @Valid PresupuestoOrden presupuestoOrden,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Consulta total presupuesto
      var objResultadoBusqMantTotal = presupuestoOrdenServicio.consultaTotalPresuOrde(presupuestoOrden);

      // Cálculo del monto total
      var montoTotalFinal = (presupuestoOrden.getMontoTotalDet() + (presupuestoOrden.getMontoTotalDet() * 0.18)) +
          objResultadoBusqMantTotal.getListaPresupuestoOrden().stream()
              .findFirst().orElseThrow().getMontoTotalDet();

      // Validación
      var constraintViolations = validador.validate(presupuestoOrden);
      if (montoTotalFinal > objResultadoBusqMantTotal.getListaPresupuestoOrden().get(0).getMontoMaterial()) {
        ErrorValidacion erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("presupuesto.orden.monto.total.superado"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else if (constraintViolations.isEmpty()) {
        // Agregar auditoría y guardar presupuesto
        presupuestoOrden.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        presupuestoOrdenServicio.guardarGastoPresupuesto(presupuestoOrden);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        var erroresValidacion = new ErrorValidacion();
        constraintViolations.forEach(constraint -> erroresValidacion.agregarError(constraint.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PutMapping("/modificarPresupuestoDet/{id}")
  public ResponseEntity<?> modificarPresupuestoDet(
      @PathVariable("id") String id,
      @RequestBody @Valid PresupuestoOrden presupuestoOrden,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Consulta total presupuesto
      var objResultadoBusqMantTotal = presupuestoOrdenServicio.consultaTotalPresuOrde(presupuestoOrden);
      var objResultadoBusqMantTotalDet = presupuestoOrdenServicio.consultaTotalPresuOrdeDet(presupuestoOrden);

      // Cálculo del monto total
      var montoTotalFinal =
          (presupuestoOrden.getMontoTotalDet() + (presupuestoOrden.getMontoTotalDet() * 0.18)) +
              objResultadoBusqMantTotalDet.getListaPresupuestoOrden().stream().findFirst()
                  .orElseThrow().getMontoTotalDet();
      // Validación
      if (montoTotalFinal > objResultadoBusqMantTotalDet.getListaPresupuestoOrden().stream()
          .findFirst().orElseThrow().getMontoMaterial()) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(
            servicioREST.getMessage("presupuesto.orden.monto.total.superado"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        // Agregar auditoría y modificar presupuesto
        presupuestoOrden.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        presupuestoOrdenServicio.modificarPresupuestoDet(presupuestoOrden);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }
    } catch (Exception excepcion) {
      var errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/consultaTotalPresuOrde")
  public ResponseEntity<?> consultaTotalPresuOrde(
      @RequestParam("idPresupuestoOrden") int idPresupuestoOrden) {
    try {
      // Crear objeto PresupuestoOrden y realizar la consulta
      var presupuestoOrden = new PresupuestoOrden();
      presupuestoOrden.setIdPresupuestoOrden(idPresupuestoOrden);
      var resultado = presupuestoOrdenServicio.consultaTotalPresuOrde(presupuestoOrden);
      return ResponseEntity.ok(resultado);
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/consultaPresupuestoOrdenVigente")
  public ResponseEntity<?> consultaPresupuestoOrdenVigente(
      @RequestParam("idInmueble") String idInmueble,
      @RequestParam("idInmuebleBloque") String idInmuebleBloque,
      @RequestParam("idInmueblePredio") String idInmueblePredio) {
    try {
      // Crear objeto PresupuestoOrden y realizar la consulta
      var objPresOrde = new PresupuestoOrden();
      objPresOrde.setIdInmueble(idInmueble);
      objPresOrde.setIdInmuebleBloque(idInmuebleBloque);
      objPresOrde.setIdInmueblePredio(idInmueblePredio);

      var listaPresupuestoOrden = presupuestoOrdenServicio.consultaPresupuestoOrdenVigente(objPresOrde);
      return ResponseEntity.ok(listaPresupuestoOrden);
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PostMapping("/registrarDocumento")
  public ResponseEntity<?> registrarDocumento(
      @RequestParam("documento") MultipartFile documento,
      @RequestParam("archivoAdjunto") MultipartFile archivoAdjunto,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Procesamiento del archivo recibido
      var registroPresupuestoDoc =
          UJson.convertirJsonATipo(documento.getInputStream(), PresupuestoOrden.class);
      var archivoDocumento = new Archivo();
      archivoDocumento.setArchivo(archivoAdjunto.getBytes());
      archivoDocumento.setNombre(archivoAdjunto.getOriginalFilename());
      archivoDocumento.setTipoMime(archivoAdjunto.getContentType());

      // Validación
      var constraintViolations = validador.validate(registroPresupuestoDoc);
      if (constraintViolations.isEmpty()) {
        registroPresupuestoDoc.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        presupuestoOrdenServicio.guardarDocumentoOrden(registroPresupuestoDoc, archivoDocumento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        var erroresValidacion = new ErrorValidacion();
        constraintViolations.forEach(constraint ->
            erroresValidacion.agregarError(constraint.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/visualizarDocumento/{id}")
  public ResponseEntity<?> visualizarDocumento(@PathVariable("id") String idPresupuestoOrden) {
    try {
      if (Objects.isNull(idPresupuestoOrden) || idPresupuestoOrden.isEmpty()) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        // Obtener documento adjunto
        PresupuestoOrden objPresupuestoOrden = new PresupuestoOrden();
        objPresupuestoOrden.setIdPresupuestoOrden(Integer.parseInt(idPresupuestoOrden));
        Archivo documentoAdjunto = presupuestoOrdenServicio.obtenerDocumentoAdjunto(objPresupuestoOrden);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=%s", documentoAdjunto.getNombre()))
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
            .body(documentoAdjunto.getArchivo());
      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }


}
