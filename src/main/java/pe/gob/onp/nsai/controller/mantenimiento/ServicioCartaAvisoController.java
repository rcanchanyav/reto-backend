package pe.gob.onp.nsai.controller.mantenimiento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.gob.onp.nsai.controller.ServicioREST;
import pe.gob.onp.nsai.dto.Auditoria;
import pe.gob.onp.nsai.dto.CartaAviso;
import pe.gob.onp.nsai.dto.ResultadoBusquedaMantenimiento;
import pe.gob.onp.nsai.services.CartaAvisoService;

/**
 * Controlador para la consulta de cartas de aviso
 */
@RestController
@RequestMapping("/api/v1/cartaAviso")
@Validated
@Slf4j
@RequiredArgsConstructor
public class ServicioCartaAvisoController {

    private static final String NOMBRE_ARCHIVO = "CartaAvisoProgramacion.docx";
    private final CartaAvisoService cartaAvisoService;
    private final ServicioREST servicioREST;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de los servicios de inmuebles con cartas de aviso generadas
     *
     * @param headers cabeceras HTTP
     * @param codigoInmueble código del inmueble
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros por página
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consultaServicioInmueble")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultarServicioInmuebleCartaAviso(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "codigoInmueble", required = false) String codigoInmueble,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros,
            HttpServletRequest request) {

        log.debug("Consulta servicio inmueble - codigoInmueble: {}, pagina: {}, nroRegistros: {}",
                codigoInmueble, pagina, nroRegistros);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoInmueble", codigoInmueble);
        parametros.put("pagina", pagina);
        parametros.put("nroRegistros", nroRegistros);

        ResultadoBusquedaMantenimiento resultado = cartaAvisoService.consultarServicioInmuebleCartaAviso(parametros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos de la programación del mantenimiento
     *
     * @param headers cabeceras HTTP
     * @param codigoCarta código de la carta de aviso
     * @param pagina página de búsqueda
     * @param nroRegistros número de registros por página
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consultaCartaAviso")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<ResultadoBusquedaMantenimiento> consultarCartaAviso(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "codigoCarta", required = false) String codigoCarta,
            @RequestParam("pagina") int pagina,
            @RequestParam("nroRegistros") int nroRegistros,
            HttpServletRequest request) {

        log.debug("Consulta carta aviso - codigoCarta: {}, pagina: {}, nroRegistros: {}",
                codigoCarta, pagina, nroRegistros);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoCarta", codigoCarta);
        parametros.put("pagina", pagina);
        parametros.put("nroRegistros", nroRegistros);

        ResultadoBusquedaMantenimiento resultado = cartaAvisoService.consultarCartaAviso(parametros);
        return ResponseEntity.ok(resultado);
    }

    /**
     * Endpoint 3
     * Servicio que permite generar un archivo de word con la vista previa de la carta
     *
     * @param headers cabeceras HTTP
     * @param codigoCarta código de la carta de aviso
     * @return Archivo DOCX para descarga
     */
    @GetMapping(value = "/generarVistaPreviaCarta", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<byte[]> generarVistaPreviaCartaAviso(
            @RequestHeader HttpHeaders headers,
            @RequestParam("codigoCarta") Long codigoCarta,
            HttpServletRequest request) {

        log.debug("Generar vista previa carta - codigoCarta: {}", codigoCarta);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoCarta", codigoCarta);
        parametros.put("codigoPlantilla", 0);

        byte[] documento = cartaAvisoService.getDocumentoCartaAviso(parametros);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + NOMBRE_ARCHIVO)
                .body(documento);
    }

    /**
     * Endpoint 4
     * Servicio que permite la impresión masiva de cartas
     *
     * @param headers cabeceras HTTP
     * @param cartasSeleccionadas lista de cartas seleccionadas
     * @return Archivo DOCX combinado para descarga
     */
    @PutMapping(value = "/impresionMasiva", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<byte[]> impresionCartaMasiva(
            @RequestHeader HttpHeaders headers,
            @Valid @RequestBody List<CartaAviso> cartasSeleccionadas,
            HttpServletRequest request) {

        log.debug("Impresión masiva - cantidad de cartas: {}", cartasSeleccionadas.size());

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("codigoCarta", 0);
        parametros.put("codigoPlantilla", 0);
        parametros.put("cartasSeleccionadas", cartasSeleccionadas);

        byte[] documento = cartaAvisoService.getDocumentoCartaAviso(parametros);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + NOMBRE_ARCHIVO)
                .body(documento);
    }

    /**
     * Endpoint 5
     * Servicio que permite modificar el correlativo de la carta circular
     *
     * @param headers cabeceras HTTP
     * @param cartaAviso datos de la carta de aviso
     * @return Respuesta vacía con código 204
     */
    @PostMapping("/actualizar")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> modificarCorrelativo(
            @RequestHeader HttpHeaders headers,
            @Valid @RequestBody CartaAviso cartaAviso,
            HttpServletRequest request) {

        log.debug("Modificar correlativo - codigoCarta: {}", cartaAviso.getCodigoCarta());

        Auditoria auditoria = servicioREST.obtenerAuditoriaModificacion(headers, request);
        cartaAviso.setAuditoria(auditoria);
        cartaAvisoService.modificarCorrelativo(cartaAviso);

        return ResponseEntity.noContent().build();
    }
}