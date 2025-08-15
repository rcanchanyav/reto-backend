package pe.gob.onp.nsai.exception;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pe.gob.onp.nsai.dto.ErrorSistema;

/**
 * Centraliza el manejo de excepciones para todos los controladores.
 */
@ControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {


  /**
   * Captura todas las excepciones no controladas de tipo Exception.
   * @param ex La excepción lanzada.
   * @param request La petición web actual.
   * @return Un ResponseEntity con un cuerpo de error estandarizado y estado 500.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorSistema> handleGlobalException(Exception ex, WebRequest request) {
    String id = UUID.randomUUID().toString();
    log.error("Error inesperado [ID: {}] en la petición: {}", id, request.getDescription(false), ex);

    ErrorSistema errorSistema = new ErrorSistema(id, "Ocurrió un error inesperado en el servidor.");
    return new ResponseEntity<>(errorSistema, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
