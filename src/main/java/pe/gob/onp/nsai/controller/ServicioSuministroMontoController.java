package pe.gob.onp.nsai.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import pe.gob.onp.nsai.dto.ErrorSistema;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.IdHash;
import pe.gob.onp.nsai.dto.SuministroMonto;
import pe.gob.onp.nsai.services.SuministroServicioMontoService;
import pe.gob.onp.nsai.util.GeneradorID;
import pe.gob.onp.nsai.util.SistemaNSAIProperties;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/montoSuministro")
public class ServicioSuministroMontoController {

  private final SuministroServicioMontoService suministroServicio;

  private final Validator validador;

  private final SistemaNSAIProperties sistemaNSAIProperties;

  private final ServicioREST servicioREST;


  @GetMapping("/consultaSuministro")
  public ResponseEntity<?> consultarSuministro(
      @RequestParam(required = false) String codigo,
      @RequestParam(required = false) String estado,
      @RequestParam(required = false) String descripcionInmueble,
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "5") int nroRegistros) {
    try {
      if ((codigo == null) && (estado == null) && (descripcionInmueble == null) &&
          (pagina == 0) && (nroRegistros == 0)) {
        var erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        var suministroBusqueda = new SuministroMonto();
        suministroBusqueda.setIdInmueble(codigo);
        suministroBusqueda.setDescripcionInmueble(descripcionInmueble);
        suministroBusqueda.setCodigoTipoServicio(estado);

        var resultado = suministroServicio.obtenerSuministros(suministroBusqueda, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
      }
    } catch (Exception excepcion) {
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/consultaSuministroMonto")
  public ResponseEntity<?> consultarSuministrosMontos(@RequestParam(required = false) String idInmueble,
      @RequestParam(required = false) String codigoTipoServicio,
      @RequestParam(required = false) String numeroSuministro,
      @RequestParam(required = false) String codigoEmpresaServicioPublico,
      @RequestParam(defaultValue = "0") int pagina,
      @RequestParam(defaultValue = "5") int nroRegistros) {
    try {
      // Verificación de los parámetros, si todos son null o vacíos, retorna un error de validación
      if ((idInmueble == null) && (codigoEmpresaServicioPublico == null) && (codigoTipoServicio == null) &&
          (numeroSuministro == null) && (pagina == 0) && (nroRegistros == 0)) {
        ErrorValidacion erroresValidacion = new ErrorValidacion();
        erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      } else {
        // Crear objeto de búsqueda para suministros
        SuministroMonto suministroBusqueda = new SuministroMonto();
        suministroBusqueda.setIdInmueble(idInmueble);
        suministroBusqueda.setCodigoTipoServicio(codigoTipoServicio);
        suministroBusqueda.setNumeroSuministro(numeroSuministro);
        suministroBusqueda.setCodigoEmpresaServicioPublico(codigoEmpresaServicioPublico);

        // Realizar consulta
        var resultado = suministroServicio.consultarSuministrosMontos(sistemaNSAIProperties.getIdSistemaFrontend(), suministroBusqueda, pagina, nroRegistros);

        // Retornar el resultado de la consulta
        return ResponseEntity.ok(resultado);
      }

    } catch (Exception excepcion) {
      // Manejo de errores, devolviendo un error con el código adecuado
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PostMapping("/registrarSuministro")
  public ResponseEntity<?> registrarSuministro(
      @RequestBody @Valid SuministroMonto registroSuministro,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Validación de la entidad SuministroMonto
      var constraintViolations = validador.validate(registroSuministro);

      if (constraintViolations.isEmpty()) {
        // Auditoría y verificación de existencia
        registroSuministro.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        var cantidadSuministro = suministroServicio.verificarSuministroExistente(registroSuministro);

        if (cantidadSuministro == 0) {
          // Si el suministro no existe, guardar el suministro
          if (registroSuministro.getIdSuministro() == null) {
            suministroServicio.guardarSuministro(registroSuministro);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
          } else {
            // Validación de restricción en caso de que ya tenga un ID
            var erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("registro.suministro.monto.restriccion"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
          }
        } else {
          // Si el suministro ya existe, retornamos un error
          var erroresValidacion = new ErrorValidacion();
          erroresValidacion.agregarError(servicioREST.getMessage("suministro.registro.existente"));
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
      } else {
        // Si no pasa la validación, retornar los errores
        var erroresValidacion = new ErrorValidacion();
        constraintViolations.forEach(constraint -> erroresValidacion.agregarError(constraint.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }
    } catch (Exception excepcion) {
      // Manejo de excepciones
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> modificar(
      @RequestBody @Valid SuministroMonto modificarSuministro,
      @PathVariable("id") String id,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      // Validar la entidad SuministroMonto
      Set<ConstraintViolation<SuministroMonto>> constraintViolations = validador.validate(modificarSuministro);

      // Decodificar el ID
      Integer decodificado = (int) GeneradorID.decodificar(id);
      int idInmuebleSuministro = decodificado;

      if (constraintViolations.isEmpty()) {
        // Verificar si el suministro ya existe
        int cantidadSuministro = suministroServicio.verificarSuministroExistente(modificarSuministro);
        if (cantidadSuministro == 1) {
          modificarSuministro.getHashId().setIdSecundario(IdHash.HASH_AUXILIAR);
          modificarSuministro.setIdSuministro(decodificado);

          // Verificar si el ID coincide
          if (idInmuebleSuministro == modificarSuministro.getIdSuministro()) {
            // Establecer auditoría y modificar el suministro
            modificarSuministro.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
            suministroServicio.modificarSuministro(modificarSuministro);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
          } else {
            // Restricción si el inmueble no coincide con el suministro
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("inmueble.suministro.modificacion.restriccion"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
          }
        } else {
          // Si el suministro no existe
          ErrorValidacion erroresValidacion = new ErrorValidacion();
          erroresValidacion.agregarError(servicioREST.getMessage("suministro.registro.no.existente"));
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }
      } else {
        // Si hay errores de validación
        ErrorValidacion erroresValidacion = new ErrorValidacion();
        constraintViolations.forEach(constraint -> erroresValidacion.agregarError(constraint.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }

    } catch (Exception excepcion) {
      // Manejo de excepciones
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminarSuministro(
      @PathVariable("id") String idSuministro,
      @RequestHeader HttpHeaders headersRequest,
      @RequestHeader HttpServletRequest request) {
    try {
      if (idSuministro != null) {
        // Decodificar el ID
        Integer decodificado = (int) GeneradorID.decodificar(idSuministro);
        int idSuministroDecodificado = decodificado;

        // Crear objeto SuministroMonto para eliminar
        SuministroMonto eliminarSuministro = new SuministroMonto();
        eliminarSuministro.getHashId().setIdSecundario(IdHash.HASH_AUXILIAR);
        eliminarSuministro.setIdSuministro(idSuministroDecodificado);
        eliminarSuministro.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        // Llamar al servicio para eliminar el suministro
        suministroServicio.eliminarSuministro(eliminarSuministro);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } else {
        // Si el ID es null, retornamos un error
        ErrorValidacion erroresValidacion = new ErrorValidacion();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
      }
    } catch (Exception excepcion) {
      // Manejo de excepciones
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> consultaSuministroId(@PathVariable("id") String id) {
    try {
      // Consultar suministro por ID
      SuministroMonto suministroMonto = suministroServicio.consultarSuministroPorId(id);
      // Retornar el suministro encontrado
      return ResponseEntity.ok(suministroMonto);
    } catch (Exception excepcion) {
      // Manejo de excepciones
      ErrorSistema errorSistema = new ErrorSistema(servicioREST.idError());
      log.error("ID Error: {}", errorSistema.getIdError(), excepcion);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorSistema);
    }
  }
}
