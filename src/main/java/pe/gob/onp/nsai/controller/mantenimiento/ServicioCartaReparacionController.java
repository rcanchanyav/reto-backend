package pe.gob.onp.nsai.controller.mantenimiento;

import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.Auditoria;
import pe.gob.onp.nsai.dto.CartaReparacion;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.CartaReparacionService;

/**
 * Controlador para la gestión de cartas de reparación
 */
@RestController
@RequestMapping("/api/v1/cartaReparacion")
@Validated
@Slf4j
@RequiredArgsConstructor
public class ServicioCartaReparacionController {

    private final CartaReparacionService cartaReparacionService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Servicio que permite registrar una carta de reparación
     *
     * @param headers cabeceras HTTP
     * @param cartaReparacion datos de la carta de reparación
     * @return Respuesta vacía con código 204
     */
    @PostMapping("/guardarCartaReparacion")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> guardarCartaReparacion(
            @RequestHeader HttpHeaders headers,
            @Valid @RequestBody CartaReparacion cartaReparacion,
            HttpServletRequest request) {

        log.debug("Registrando carta de reparación");

        Auditoria auditoria = servicioREST.obtenerAuditoriaCreacion(headers, request);
        cartaReparacion.setAuditoria(auditoria);

        Integer correlativoCarta = cartaReparacionService.obtenerCorrelativoCartaReparacion();
        cartaReparacion.setNroCorrelativo(correlativoCarta);

        cartaReparacionService.guardarCartaReparacion(cartaReparacion);

        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint 2
     * Servicio que permite consultar cartas de reparación
     *
     * @param headers cabeceras HTTP
     * @param codigoInmueble código del inmueble
     * @param codigoBloque código del bloque
     * @param codigoPredio código del predio
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros por página
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consultaCarta")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultarCartaRep(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "codigoInmueble", required = false) String codigoInmueble,
            @RequestParam(value = "codigoBloque", required = false) String codigoBloque,
            @RequestParam(value = "codigoPredio", required = false) String codigoPredio,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros,
            HttpServletRequest request) {

        log.debug("Consulta carta reparación - Inmueble: {}, Bloque: {}, Predio: {}, Pagina: {}, Registros: {}",
                codigoInmueble, codigoBloque, codigoPredio, pagina, nroRegistros);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoInmueble", codigoInmueble);
        parametros.put("codigoBloque", codigoBloque);
        parametros.put("codigoPredio", codigoPredio);
        parametros.put("pagina", pagina);
        parametros.put("nroRegistros", nroRegistros);

        ResultadoBusquedaMantenimiento resultado = cartaReparacionService.consultarCartaAviso(parametros);
        return ResponseEntity.ok(resultado);
    }
}