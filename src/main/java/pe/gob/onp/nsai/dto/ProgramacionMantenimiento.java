/**
 * Resumen.
 * Objeto               :   Programacion.java.
 * Descripción          :   Clase utilizada para encapsular los campos de la programación.
 * Fecha de Creación    :   16/12/2020
 * PE de Creación       :  	INI-900
 * Autor                :   Igor A. Quispe Vasquez.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.dto;


import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que almacena los datos de la programación
 */
@Getter
@Setter
public class ProgramacionMantenimiento implements Serializable {
	/**
	 * Numero de serie autogenerado
	 */
	@Serial
	private static final long serialVersionUID = -2172321446484009868L;

	/** Código del programacion */
	private Integer codigoProgramacion;
	
	/** Servicio */
	private Servicio servicio;
	
	/** Código del Servicio */
	private String codigoServicio;
	
	/** Descripción del Servicio */
	private String descripcionServicio;
	
	/** Código del Inmueble */
	private String codigoInmueble;
	
	/** Descripción del Inmueble */
	private String descripcionInmueble;
	
	/** Número del contrato de la programación */
	private String numeroContrato;
	
	/** Número de la orden del servicio */ 
	
	private String numeroOrdenServicio;
	
	/** Fecha de inicio de la programación */
	private Date fechaInicio;
	
	/** Fecha de termino de la programación */
	private Date fechaTermino;
	
	/** Duración de la programación en años, meses y días */
	private String duracion;
	
	/** Monto total de la programación */
	private BigDecimal montoTotal;
	
	/** Indicador de activo e inactivo de la programación */
	private String indicadorActivo;	
	
	/** Indicador de activo e inactivo de la programación */
	private List<ProgramacionMantenimientoDetalle> etapas;
	
	/** Datos de auditoria */
	private Auditoria auditoria;


}
