package pe.gob.onp.nsai.controller.mantenimiento;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.ProgramacionMantenimiento;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDetalle;
import pe.gob.onp.nsai.dto.ResultadoBusquedaInmueble;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.ProgramacionMantenimientoService;
import pe.gob.onp.nsai.controller.ServicioREST;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import pe.gob.onp.nsai.util.GeneradorID;

/**
 * Controlador para el mantenimiento de programaciones
 *
 * Endpoint 1: GET /programacionMantenimiento/consulta - Consulta programaciones con filtros
 * Endpoint 2: GET /programacionMantenimiento/consultaInmueble - Consulta inmuebles por servicio
 * Endpoint 3: GET /programacionMantenimiento/detalle - Consulta detalle de programación
 * Endpoint 4: POST /programacionMantenimiento - Registra una nueva programación
 * Endpoint 5: DELETE /programacionMantenimiento/{id} - Elimina una programación
 * Endpoint 6: GET /programacionMantenimiento/{id} - Obtiene programación por ID
 * Endpoint 7: PUT /programacionMantenimiento/{id} - Modifica una programación
 * Endpoint 8: PUT /programacionMantenimiento/etapa - Elimina etapa de programación
 * Endpoint 9: GET /programacionMantenimiento/etapa - Obtiene etapa de programación
 * Endpoint 10: POST /programacionMantenimiento/etapa - Modifica etapa de programación
 * Endpoint 11: GET /programacionMantenimiento/consultaDetalle - Consulta detalle de programación
 * Endpoint 12: GET /programacionMantenimiento/consultaImporteServicio - Consulta importe de servicio
 */
@RestController
@RequestMapping("/programacionMantenimiento")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ServicioProgramacionMantenimientoController {

    private final ProgramacionMantenimientoService programacionMantenimientoService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de la programación del mantenimiento según parametros de busqueda
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param mes mes del mantenimiento
     * @param anio año del mantenimiento
     * @param codigoInmueble código del inmueble
     * @param codigoServicio código del servicio
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consulta")
// @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarProgramaciones(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "mes", required = false) Integer mes,
            @RequestParam(value = "anio", required = false) Integer anio,
            @RequestParam(value = "codigoInmueble", required = false) String codigoInmueble,
            @RequestParam(value = "codigoServicio", required = false) String codigoServicio,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {

        log.info("GET : /programacionMantenimiento/consulta : mes={}", mes);
        log.info("GET : /programacionMantenimiento/consulta : anio={}", anio);
        log.info("GET : /programacionMantenimiento/consulta : codigoInmueble={}", codigoInmueble);
        log.info("GET : /programacionMantenimiento/consulta : codigoServicio={}", codigoServicio);
        log.info("GET : /programacionMantenimiento/consulta : pagina={}", pagina);
        log.info("GET : /programacionMantenimiento/consulta : nroRegistros={}", nroRegistros);

        String codigoDecodificado = "";
        if(codigoServicio != null && !codigoServicio.isEmpty()) {
            try {
                long idDecodificado = GeneradorID.decodificar(codigoServicio);
                codigoDecodificado = String.valueOf(idDecodificado);
            } catch (IllegalArgumentException e) {
                ErrorValidacion erroresValidacion = new ErrorValidacion();
                erroresValidacion.agregarError("Error al decodificar el código del servicio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
            }
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("mes", mes);
        parametros.put("anio", anio);
        parametros.put("codigoInmueble", codigoInmueble);
        parametros.put("codigoServicio", codigoDecodificado);
        parametros.put("pagina", pagina);
        parametros.put("paginacion", nroRegistros);

        ResultadoBusquedaMantenimiento resultadoBusqueda = programacionMantenimientoService.consultarProgramacion(parametros);
        return ResponseEntity.ok(resultadoBusqueda);
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos del inmueble registrado en una proyección de costo
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigoServicio código del servicio
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaInmueble")
// @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarInmuebles(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "codigoServicio") String codigoServicio) {

        if(codigoServicio == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        String codigoDecodificado = "";
        if(!codigoServicio.isEmpty()) {
            try {
                long idDecodificado = GeneradorID.decodificar(codigoServicio);
                codigoDecodificado = String.valueOf(idDecodificado);
            } catch (IllegalArgumentException e) {
                ErrorValidacion erroresValidacion = new ErrorValidacion();
                erroresValidacion.agregarError("Error al decodificar el código del servicio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
            }
        }

        ProgramacionMantenimiento busquedaProgramacion = new ProgramacionMantenimiento();
        busquedaProgramacion.setCodigoServicio(codigoDecodificado);

        ResultadoBusquedaInmueble resultadoBusqueda = programacionMantenimientoService.consultarInmueble(busquedaProgramacion);
        return ResponseEntity.ok(resultadoBusqueda);
    }

    /**
     * Endpoint 3
     * Servicio que permite obtener la data para el grid de etapas de una programación
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigoProgramacion código de la programación
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/detalle")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultaDetalleProgramacion(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "codigoProgramacion") Integer codigoProgramacion) {

        if(codigoProgramacion == null) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        ProgramacionMantenimientoDetalle pmd = new ProgramacionMantenimientoDetalle();
        pmd.setCodigoProgramacion(codigoProgramacion);

        ResultadoBusquedaMantenimiento resultadoBusqueda = programacionMantenimientoService.consultarProgramacionMantenimientoDetalle(pmd);
        return ResponseEntity.ok(resultadoBusqueda);
    }

    /**
     * Endpoint 4
     * Servicio que permite registrar los datos de la programación
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param programacionMantenimiento Objeto con datos de la programación de mantenimiento a registrar
     * @return ResponseEntity con el resultado de la operación
     */
    @PostMapping
// @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> registrarProgramacion(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @Valid @RequestBody ProgramacionMantenimiento programacionMantenimiento) {

        // Verificar si el servicio y su hashId existen antes de acceder a ellos
        if(programacionMantenimiento.getServicio() != null) {
            String codigoDecodificado = "";
            // Acceder de forma segura al hashId para evitar NullPointerException
            if(programacionMantenimiento.getServicio().getHashId() != null &&
                    programacionMantenimiento.getServicio().getHashId().getIdPrincipal() != null &&
                    !programacionMantenimiento.getServicio().getHashId().getIdPrincipal().isEmpty()) {

                try {
                    long idDecodificado = GeneradorID.decodificar(programacionMantenimiento.getServicio().getHashId().getIdPrincipal());
                    codigoDecodificado = String.valueOf(idDecodificado);
                } catch (IllegalArgumentException e) {
                    ErrorValidacion erroresValidacion = new ErrorValidacion();
                    erroresValidacion.agregarError("Error al decodificar el ID del servicio: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
                }
            }

            // Establecer el código del servicio decodificado
            programacionMantenimiento.getServicio().setCodigoServicio(codigoDecodificado);
        }

        programacionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));

        programacionMantenimientoService.registrarProgramacion(programacionMantenimiento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 5
     * Servicio que permite eliminar los datos de la programación del mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param id identificador de la programación
     * @return ResponseEntity con el resultado de la operación
     */
    @DeleteMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> eliminarProgramacionMantenimiento(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @PathVariable("id") String id) {

        ProgramacionMantenimiento programacionMantenimiento = new ProgramacionMantenimiento();
        programacionMantenimiento.setCodigoProgramacion(Integer.parseInt(id));
        programacionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));

        programacionMantenimientoService.eliminarProgramacionMantenimiento(programacionMantenimiento);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 6
     * Servicio que permite obtener los datos de la programación de mantenimiento según su id
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param id identificador de la programación de mantenimiento
     * @return ResponseEntity con los datos de la programación
     */
    @GetMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<ProgramacionMantenimiento> obtenerProgramacionMantenimiento(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @PathVariable("id") int id) {

        ProgramacionMantenimiento programacionMantenimiento = programacionMantenimientoService.obtenerProgramacionMantenimiento(id);
        return ResponseEntity.ok(programacionMantenimiento);
    }

    /**
     * Endpoint 7
     * Servicio que permite modificar los datos de la programación de mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param programacionMantenimiento Objeto con datos de la programación de mantenimiento a modificar
     * @param id identificador de la programacion
     * @return ResponseEntity con el resultado de la operación
     */
    @PutMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> modificarProgramacionMantenimiento(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @Valid @RequestBody ProgramacionMantenimiento programacionMantenimiento,
            @PathVariable("id") int id) {

        if(id != programacionMantenimiento.getCodigoProgramacion()) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Restricción en la modificación");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        programacionMantenimiento.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        programacionMantenimientoService.modificarProgramacionMantenimiento(programacionMantenimiento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 8
     * Servicio que permite eliminar los datos de la etapa de programación del mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param programacionMantenimientoDetalle Objeto con datos de la etapa a eliminar
     * @return ResponseEntity con el resultado de la operación
     */
    @PutMapping("/etapa")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> eliminarProgramacionMantenimientoEtapa(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestBody ProgramacionMantenimientoDetalle programacionMantenimientoDetalle) {

        programacionMantenimientoDetalle.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        programacionMantenimientoService.eliminarProgramacionMantenimientoDetalle(programacionMantenimientoDetalle);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 9
     * Servicio que permite obtener los datos de la etapa de programación del mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param tipo tipo de etapa
     * @param codigoProgramacion código de la programación
     * @param etapa número de etapa
     * @return ResponseEntity con los datos de la etapa
     */
    @GetMapping("/etapa")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> obtenerEtapaProgramacionMatenimientoDetalle(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "tipo") String tipo,
            @RequestParam(value = "codigoProgramacion") int codigoProgramacion,
            @RequestParam(value = "etapa") int etapa) {

        if(tipo == null && codigoProgramacion == 0 && etapa == 0) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        ProgramacionMantenimientoDetalle pmd = new ProgramacionMantenimientoDetalle();
        pmd.setTipo(tipo);
        pmd.setCodigoProgramacion(codigoProgramacion);
        pmd.setDetalleProgramacion(etapa);

        ProgramacionMantenimientoDetalle pmdo = programacionMantenimientoService.obtenerEtapaProgramacionMatenimientoDetalle(pmd);
        return ResponseEntity.ok(pmdo);
    }

    /**
     * Endpoint 10
     * Servicio que permite modificar los datos de la etapa de programación de mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param pmd Objeto con datos de la etapa de programación de mantenimiento a modificar
     * @return ResponseEntity con el resultado de la operación
     */
    @PostMapping("/etapa")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> modificarProgramacionMantenimientoDetalle(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @Valid @RequestBody ProgramacionMantenimientoDetalle pmd) {

        pmd.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
        programacionMantenimientoService.modificarProgramacionMantenimientoDetalle(pmd);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Endpoint 11
     * Servicio que permite obtener los datos del detalle de la programación del mantenimiento
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigoProgramacion código de programacion del mantenimiento
     * @return ResponseEntity con el resultado de la búsqueda
     */
    @GetMapping("/consultaDetalle")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarProgramacionesDetalle(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "codigoProgramacion") int codigoProgramacion) {

        if(codigoProgramacion == 0) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Se requiere al menos un criterio de búsqueda");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        ProgramacionMantenimiento programacionMantenimiento = new ProgramacionMantenimiento();
        programacionMantenimiento.setCodigoProgramacion(codigoProgramacion);

        ResultadoBusquedaMantenimiento resultadoBusqueda = programacionMantenimientoService.obtenerProgramacionMantenimientoDetalle(programacionMantenimiento);
        return ResponseEntity.ok(resultadoBusqueda);
    }

    /**
     * Endpoint 12
     * Servicio que permite obtener el monto total del servicio de programación
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param request Objeto HttpServletRequest para auditoría
     * @param codigoServicio código del servicio
     * @return ResponseEntity con el monto total
     */
    @GetMapping("/consultaImporteServicio")
// @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarMontoTotalServicioProgramacion(
            @RequestHeader HttpHeaders headersRequest,
            HttpServletRequest request,
            @RequestParam(value = "codigoServicio") String codigoServicio) {

        log.info("GET : /programacionMantenimiento/consultaImporteServicio : codigoServicio={}", codigoServicio);

        if(codigoServicio == null || codigoServicio.isEmpty()) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError("Consulta de servicio requerida");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
        }

        String codigoDecodificado = "";
        if(!codigoServicio.isEmpty()) {
            try {
                long idDecodificado = GeneradorID.decodificar(codigoServicio);
                codigoDecodificado = String.valueOf(idDecodificado);
            } catch (IllegalArgumentException e) {
                ErrorValidacion erroresValidacion = new ErrorValidacion();
                erroresValidacion.agregarError("Error al decodificar el código del servicio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresValidacion);
            }
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoServicio", codigoDecodificado);

        Map<String, Object> resultado = programacionMantenimientoService.obtenerMontoTotalServicioProgramacion(parametros);
        return ResponseEntity.ok(resultado);
    }
}