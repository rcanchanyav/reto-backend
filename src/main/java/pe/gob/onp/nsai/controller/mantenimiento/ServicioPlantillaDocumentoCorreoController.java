package pe.gob.onp.nsai.controller.mantenimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.PlantillaMantenimientoDocumentoCorreo;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;
import pe.gob.onp.nsai.services.PlantillaMantenimientoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * Controlador para el mantenimiento de plantilla de los documentos y correo.
 *
 * Endpoint 1: GET /plantillaDocumentoCorreo/consultaPlantilla - Consulta plantillas con filtros
 * Endpoint 2: GET /plantillaDocumentoCorreo/{id} - Obtiene una plantilla por ID
 * Endpoint 3: POST /plantillaDocumentoCorreo - Registra una nueva plantilla
 * Endpoint 4: PUT /plantillaDocumentoCorreo - Modifica una plantilla existente
 * Endpoint 5: DELETE /plantillaDocumentoCorreo/{id} - Elimina una plantilla
 */
@RestController
@RequestMapping("/plantillaDocumentoCorreo")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ServicioPlantillaDocumentoCorreoController {

    private final PlantillaMantenimientoService plantillaMantenimientoService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de la plantilla
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigo identificador del tipo de plantilla
     * @param descripcion descripción de la plantilla
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaPlantilla")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarPlantillasDocumentoCorreo(
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

        PlantillaMantenimientoDocumentoCorreo plantillaBusqueda = new PlantillaMantenimientoDocumentoCorreo();
        plantillaBusqueda.setIdPlantillaDocumentoPrincipal(0);
        plantillaBusqueda.setTipoPlantilla(codigo);
        plantillaBusqueda.setCodigoTipoPlantillaDocumento(descripcion);

        ResultadoBusquedaPlantillaMantenimiento resultado = plantillaMantenimientoService
                .consultarPlantillasDocumentoCorreo(plantillaBusqueda, pagina, nroRegistros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos de una plantilla específica
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idPlantilla identificador de la plantilla
     * @return ResponseEntity con los datos de la plantilla
     */
    @GetMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<PlantillaMantenimientoDocumentoCorreo> obtenerPlantilla(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @PathVariable("id") String idPlantilla) {

        PlantillaMantenimientoDocumentoCorreo plantillaObtenida = new PlantillaMantenimientoDocumentoCorreo();
        plantillaObtenida.setIdPlantillaDocumentoPrincipal(Integer.valueOf(idPlantilla));

        plantillaObtenida = plantillaMantenimientoService.obtenerDatosPlantilla(plantillaObtenida);
        return ResponseEntity.ok(plantillaObtenida);
    }

    /**
     * Endpoint 3
     * Servicio para registrar una nueva plantilla
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param registrarPlantilla datos de la plantilla a registrar
     * @return ResponseEntity con el resultado de la operación
     */
    @PostMapping
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> registrarPlantilla(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @Valid @RequestBody PlantillaMantenimientoDocumentoCorreo registrarPlantilla) {

        int cantidadPlantillas = plantillaMantenimientoService
                .obtenerCantidadRegistrosDocumentoCorreo(registrarPlantilla);

        if (cantidadPlantillas > 0) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Ya existe una plantilla con los mismos datos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        if (registrarPlantilla.getIdPlantillaDocumentoPrincipal() != null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("No se permite ID en registro nuevo");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        registrarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        plantillaMantenimientoService.registrarPlantilla(registrarPlantilla);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 4
     * Servicio que permite modificar los datos de una plantilla existente
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param modificarPlantilla datos de la plantilla a modificar
     * @return ResponseEntity con el resultado de la operación
     */
    @PutMapping
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> modificarPlantilla(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @Valid @RequestBody PlantillaMantenimientoDocumentoCorreo modificarPlantilla) {

        int cantidadPlantillas = plantillaMantenimientoService
                .obtenerCantidadRegistrosDocumentoCorreo(modificarPlantilla);

        if (cantidadPlantillas > 0) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Ya existe una plantilla con los mismos datos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        if (modificarPlantilla.getTipoPlantilla() == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Tipo de plantilla es requerido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        modificarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        plantillaMantenimientoService.modificarPlantilla(modificarPlantilla);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 5
     * Servicio que permite eliminar una plantilla
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param idPlantilla identificador de la plantilla a eliminar
     * @return ResponseEntity con el resultado de la operación
     */
    @DeleteMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> eliminarPlantilla(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @PathVariable("id") String idPlantilla) {

        if (idPlantilla != null) {
            PlantillaMantenimientoDocumentoCorreo eliminarPlantilla = new PlantillaMantenimientoDocumentoCorreo();
            eliminarPlantilla.setIdPlantillaDocumentoPrincipal(Integer.valueOf(idPlantilla));

            int cantidadDetallePlantilla = plantillaMantenimientoService
                    .obtenerCantidadRegistrosDetallePlantilla(eliminarPlantilla);

            if (cantidadDetallePlantilla > 0) {
                eliminarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
                plantillaMantenimientoService.eliminarDetallePlantilla(eliminarPlantilla);
            }

            eliminarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
            plantillaMantenimientoService.eliminarPlantilla(eliminarPlantilla);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}