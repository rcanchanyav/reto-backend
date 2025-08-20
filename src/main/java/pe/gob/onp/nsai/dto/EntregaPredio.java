package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EntregaPredio implements Serializable{

    /** Número de versión de la clase Serializable. */
    private static final long serialVersionUID = 6684413035379492171L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId = new IdHash();

    /** Identificador del detalle del Contrato Persona. */
    private long idEntregaPredio;


    /** Identificador del detalle Acta Documento. */
    private long idDetalleActaDocumento;


    /** Identificador del detalle Predio Contrato. */
    private long idDetallePredioContrato;

    /** Identificador del detalle Inmueble Predio. */
    private long idDetalleInmuPredio;


    /** Identificador del predio del contrato. */
    private Integer idContratoPredio;

    /** Identificador del contrato. */
    private String idContrato;

    /** Identificador del inmueble. */
    private String idInmueble;

    /** Identificador del bloque del inmueble. */
    private Integer idInmuebleBloque;

    /** Identificador del predio del inmueble. */
    private Integer idInmueblePredio;

    /** Fecha de Inicio */
    private Date fechaInicio;

    /** Fecha de Inicio */
    private String nombreArrendatario;

    /** Objeto de Auditoria. */
    private Auditoria auditoria;

    /** Estado del registro. */
    private String estadoBusqueda;

    /** Estado de asignacion. */
    private String estado;

    /** Descripción del inmueble. */
    private String descripcionInmueble;

    /** Descripción del bloque. */
    private String descripcionBloque;

    /** Descripción del predio. */
    private String descripcionPredio;

    /** Identificador del persona. */
    private Integer idPersona;

    /** Fecha de Asignacion. */
    private Date feAsignacion;

    /** Descripción de Responsable. */
    private String responsable;

    /** Correo Arrendatario. */
    private String correo;

    /** Telefono Arrendatario. */
    private String telefono;

    /** Celular Arrendatario. */
    private String celular;

    /** Codigo de Ubigeo  */
    private String codigoAreaGeografica;

    /** Direccion Inmueblre */
    private String direccionInmueble;

    /** Nombre Archivo Documento */
    private String nombreArchivoDocumento;

    /** Codigo Archivo Documento */
    private String codigoArchivoDocumento;

    /** Codigo de Ubigeo  */
    private String coImagDocuAsoc;

    /** Provincia del Inmueble */
    @NotNull(message="{entregaPredio.provinciaInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.provinciaInmueble.size}")
    private String provinciaInmueble;

    /** Departamento del Inmueble  */
    @NotNull(message="{entregaPredio.departamentoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.departamentoInmueble.size}")
    private String departamentoInmueble;

    /** Distrito del Inmueble */
    @NotNull(message="{entregaPredio.distritoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{entregaPredio.distritoInmueble.size}")
    private String distritoInmueble;

    /** Responsable */
    private String nombreResponsable;

    /** Observacion Predio */
    private String observacionPredio;


    /** Numero de Documento */
    private String numeroDocumento;

    private String tipo;


}
