package pe.gob.onp.nsai.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Clase que contiene los atributos del inspeccion mantenimiento.
 */
@Setter
@Getter
public class InspeccionMantenimiento implements Serializable {

    private static final long serialVersionUID = 1245142052232885526L;

    private Integer idInspecccionMantenimiento;

    private String idInmueblePredio;

    @NotNull(message = "{inspeccionMantenimiento.idInmueble.notNull}")
    private String idInmueble;

    private int idDetalleActaDocumento;

    private Integer idPersonas;

    private String codigoArchivoDocumento;

    private String codigoEstadoPredio;

    private Date fechaContacto;

    private String motivoContacto;

    private Date fechaInspeccion;

    private String motivoInspeccion;

    private String descripcion;

    private String tipoTrabajo;

    private Date fechaInicioTrabajo;

    private Date fechaArrendamiento;

    private String numeroOrdenServicio;

    private Date fechaSolicitud;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaSolicitudForm;

    private Date fechaFinTrabajador;

    private String codigoEstadoInspeccion;

    private BigDecimal costoOrdenServicio;

    private String numeroSolicitud;

    private String observacionTrabajador;

    private BigDecimal costoManoObra;

    private String usuarioLogueado;

    private String numeroOrdenTrabajo;

    private Date fechaConformidad;

    private BigDecimal costoValorMaterial;

    private Integer tiempoDemoraTrabajo;

    private Date fechaOrdenTrabajo;

    private String inspeccionSolicitado;

    private Integer tiempoDemoraAtencion;

    private Auditoria auditoria;

    private String codigoPredio;

    private String idInmuebleBloque;

    private String descripcionInmueble;

    private Integer idAmbientePredio;

    private String codigoTipoAmbiente;

    private String descripcionTipoAmbiente;

    private Integer idInspecccionMantenimientoInf;

    private Integer idInspecccionMantenimientoAmb;

    private String codigoTipo;

    private String codigoEstadoConservacion;

    private String observacion;

    private String descripcionBloque;

    private BigDecimal costoTotal;

    // Infraestructura.
    private Integer idInfraestructura;

//	private String codigoTipo;

    private String ambiente;

    private String infraestructura;

    private Integer cantidad;

    private String estado;

    private Date fechEstadoCons;

    private String observacionPredio;

    private String codigoEstadoActa;

    private String direccionInmueble;

    private String codigoTipoInfraestrutura;

    private String idcontrato;

    private String mes;

    private String anio;

    private IdHash hashId = new IdHash();

    private Integer idPresupuestoOrden;

    private String nombreArchivo;

    private String nombreArchivoDocumento;


}