package pe.gob.onp.nsai.controller.mantenimiento;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.gob.onp.nsai.dto.ErrorValidacion;
import pe.gob.onp.nsai.dto.PlantillaMantenimiento;
import pe.gob.onp.nsai.dto.ResultadoBusquedaPlantillaMantenimiento;
import pe.gob.onp.nsai.services.PlantillaMantenimientoDocumentoCorreoService;
import pe.gob.onp.nsai.controller.ServicioREST;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller para el mantenimiento de plantillas de documentos y correos
 */
@RestController
@RequestMapping("/plantillaDetalleDocumentoCorreo")
@Validated
@RequiredArgsConstructor
public class ServicioPlantillaDetalleDocumentoCorreoController {

    private final PlantillaMantenimientoDocumentoCorreoService plantillaServicio;
    private final ServicioREST servicioREST;
    private final ServletContext servletContext;

    /**
     * Endpoint 1
     * Servicio que permite obtener los datos de la plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param codigo identificador del tipo de plantilla
     * @param pagina pagina de busqueda
     * @param nroRegistros numero de registros
     * @return Resultado de la búsqueda con paginación
     */
    @GetMapping("/consulta")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> consultarPlantillas(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestParam(value = "codigo", required = false) String codigo,
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "nroRegistros", defaultValue = "10") int nroRegistros) {

        if ((codigo == null) && (pagina == 0) && (nroRegistros == 0)) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("general.consulta.critero.minimo"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        } else {
            PlantillaMantenimiento plantillaBusqueda = new PlantillaMantenimiento();
            plantillaBusqueda.setIdPlantillaMantenimiento(Integer.valueOf(codigo));
            ResultadoBusquedaPlantillaMantenimiento resultado = plantillaServicio.consultarPlantillasDocumento(plantillaBusqueda, pagina, nroRegistros);
            return ResponseEntity.ok(resultado);
        }
    }

    /**
     * Endpoint 2
     * Servicio que permite obtener los datos de la plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param idPlantilla identificador de la plantilla
     * @return Datos de la plantilla solicitada
     */
    @GetMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> obtenerPlantilla(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @PathVariable("id") String idPlantilla) {

        PlantillaMantenimiento plantillaObtenida = new PlantillaMantenimiento();
        if (idPlantilla != null) {
            plantillaObtenida.setIdDetallePlantillaMante(Integer.valueOf(idPlantilla));
            plantillaObtenida = plantillaServicio.obtenerDatosPlantilla(plantillaObtenida);
            return ResponseEntity.ok(plantillaObtenida);
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("general.identificador.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }
    }

    /**
     * Endpoint 3
     * Servicio para registrar una plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param registrarPlantilla datos de la plantilla
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se registra correctamente
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> registrarPlantilla(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestBody @Valid PlantillaMantenimiento registrarPlantilla) {

        boolean validaSeccionDocumentoAnio = true;
        boolean validaSeccionDocumentoInicio = true;
        boolean validaSeccionDocumentoFinal = true;
        boolean validaSeccionDocumentoTexto = true;
        boolean validaSeccionDocumentoFirma = true;
        boolean validaOrden = true;

        if (registrarPlantilla.getTipoSeccion().equals("01")) {
            int cantidadInicio = plantillaServicio.obtenerCantidadPlantillaSeccion(registrarPlantilla.getIdPlantillaMantenimiento(), "01");
            if (cantidadInicio >= 1) {
                validaSeccionDocumentoAnio = false;
            }
        } else if (registrarPlantilla.getTipoSeccion().equals("02")) {
            int cantidadInicio = plantillaServicio.obtenerCantidadPlantillaSeccion(registrarPlantilla.getIdPlantillaMantenimiento(), "02");
            if (cantidadInicio >= 1) {
                validaSeccionDocumentoInicio = false;
            }
        } else if (registrarPlantilla.getTipoSeccion().equals("05")) {
            int cantidadFinal = plantillaServicio.obtenerCantidadPlantillaSeccion(registrarPlantilla.getIdPlantillaMantenimiento(), "05");
            if (cantidadFinal >= 1) {
                validaSeccionDocumentoFinal = false;
            }
        } else if (registrarPlantilla.getTipoSeccion().equals("03")) {
            int cantidadFinal = plantillaServicio.obtenerCantidadPlantillaSeccion(registrarPlantilla.getIdPlantillaMantenimiento(), "03");
            if (cantidadFinal >= 1) {
                validaSeccionDocumentoTexto = false;
            }
        } else if (registrarPlantilla.getTipoSeccion().equals("06")) {
            int cantidadFinal = plantillaServicio.obtenerCantidadPlantillaSeccion(registrarPlantilla.getIdPlantillaMantenimiento(), "06");
            if (cantidadFinal >= 1) {
                validaSeccionDocumentoFirma = false;
            }
        }

        if (!validaSeccionDocumentoAnio) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.tipo.plantilla.anio.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        if (!validaSeccionDocumentoInicio) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.tipo.plantilla.inicio.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        if (!validaSeccionDocumentoFinal) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.tipo.plantilla.final.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        if (!validaSeccionDocumentoTexto) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.tipo.plantilla.texto.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        if (!validaSeccionDocumentoFirma) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.tipo.plantilla.firma.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        List<String> listaOrden = new ArrayList<>();
        listaOrden = plantillaServicio.obtenerOrdenClausulasPlantilla(registrarPlantilla.getIdPlantillaMantenimiento());
        for (String orden : listaOrden) {
            if (orden.equals(registrarPlantilla.getOrdenSeccion())) {
                validaOrden = false;
                break;
            }
        }

        if (!validaOrden) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.orden.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        registrarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaCreacion(headersRequest, request));
        if (registrarPlantilla.getIdDetallePlantillaMante() == null) {
            plantillaServicio.registrarPlantilla(registrarPlantilla);
            return ResponseEntity.noContent().build();
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }
    }

    /**
     * Endpoint 4
     * Servicio que permite modificar los datos de la plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param modificarPlantilla Objeto con datos de la plantilla a modificar
     * @param idPlantilla identificador de la plantilla
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se modifica correctamente
     */
    @PutMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<?> modificar(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @RequestBody @Valid PlantillaMantenimiento modificarPlantilla,
            @PathVariable("id") String idPlantilla) {

        modificarPlantilla.setIdDetallePlantillaMante(Integer.valueOf(idPlantilla));

        boolean validaOrden = true;

        List<String> listaOrden = new ArrayList<>();
        listaOrden = plantillaServicio.obtenerOrdenClausulasPlantillaModificacion(modificarPlantilla.getIdPlantillaMantenimiento(), modificarPlantilla.getIdDetallePlantillaMante());

        for (String orden : listaOrden) {
            if (orden.equals(modificarPlantilla.getOrdenSeccion())) {
                validaOrden = false;
                break;
            }
        }

        if (!validaOrden) {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.registro.valida.orden.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }

        if (Integer.valueOf(idPlantilla).equals(modificarPlantilla.getIdDetallePlantillaMante())) {
            modificarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
            plantillaServicio.modificarPlantilla(modificarPlantilla);
            return ResponseEntity.noContent().build();
        } else {
            ErrorValidacion erroresValidacion = new ErrorValidacion();
            erroresValidacion.agregarError(servicioREST.getMessage("plantilla.modificacion.restriccion"));
            return ResponseEntity.badRequest().body(erroresValidacion);
        }
    }

    /**
     * Endpoint 5
     * Servicio que permite eliminar los datos de la plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param idPlantilla identificador de la plantilla a eliminar
     * @return Respuesta vacía con código 204 (NO_CONTENT) si se elimina correctamente
     */
    @DeleteMapping("/{id}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Void> eliminarPlantilla(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @PathVariable("id") String idPlantilla) {

        if (idPlantilla != null) {
            PlantillaMantenimiento eliminarPlantilla = new PlantillaMantenimiento();
            eliminarPlantilla.setIdDetallePlantillaMante(Integer.valueOf(idPlantilla));
            eliminarPlantilla.setAuditoria(servicioREST.obtenerAuditoriaModificacion(headersRequest, request));
            plantillaServicio.eliminarPlantilla(eliminarPlantilla);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint 6
     * Servicio que permite generar una plantilla
     *
     * @param request Objeto HttpServletRequest para auditoría
     * @param headersRequest Cabeceras HTTP para auditoría
     * @param idPlantilla identificador de la plantilla
     * @return Archivo de la plantilla generada
     */
    @GetMapping("/generaPlantilla/{idPlantilla}")
    // @SeguridadServicio - Reemplazar con Spring Security
    public ResponseEntity<Resource> generarPlantilla(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headersRequest,
            @PathVariable("idPlantilla") String idPlantilla) {

        PlantillaMantenimiento datosPlantilla = new PlantillaMantenimiento();
        datosPlantilla.setIdPlantillaMantenimiento(Integer.valueOf(idPlantilla));
        int cantidadSeccionesPlantilla = plantillaServicio.obtenerCantidadRegistrosBusquedaPlantilla(datosPlantilla);

        if (cantidadSeccionesPlantilla > 0) {
            PlantillaMantenimiento tipoPlantilla = plantillaServicio.obtenerTipoPlantilla(Integer.valueOf(idPlantilla));
            ByteArrayOutputStream word = null;

            if (tipoPlantilla.getTipoPlantilla().equals("01")) {
                if (tipoPlantilla.getCodigoTipoPlantillaMante().equals("03")) {
                    word = plantillaServicio.generarPlantillaCorreoNotificacion(Integer.valueOf(idPlantilla));
                } else {
                    word = plantillaServicio.generarPlantillaCorreo(Integer.valueOf(idPlantilla));
                }
            } else if (tipoPlantilla.getTipoPlantilla().equals("02")) {
                word = plantillaServicio.generarPlantillaDocumento(Integer.valueOf(idPlantilla), servletContext);
            }

            ByteArrayResource resource = new ByteArrayResource(word.toByteArray());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PlantillaMantenimiento.docx")
                    .header("Access-Control-Expose-Headers", "Content-Disposition")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                    .body(resource);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}