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


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que almacena los datos de la programación
 */
public class ProgramacionMantenimientoDetalle implements Serializable {

	/** Numero de serie autogenerado */
	private static final long serialVersionUID = 1424667348201637162L;
	/** Número de orden del registro */
	private Integer item;
	
	/** Tipo de etapa I:Inmueble, P:Predio */
	private String tipo;
	
	/** Código de la programación */
	private Integer codigoProgramacion;
	
	/** Código del detalle de la programación(etapa) */
	private Integer detalleProgramacion;
	
	/** Código del inmueble */
	private String codigoInmueble;
	
	/** Nombre del Inmueble */
	private String nombreInmueble;
	
	/** Código del Bloque  */
	private String codigoBloque;
	
	/** Nombre del Bloque */
	private String nombreBloque;
	
	/** Código del Predio */
	private String codigoPredio;
	
	/** Nombre del Predio */
	private String nombrePredio;
	
	/** Motivo de la etapa */	
	private String motivo;
	
	/** Observación de la etapa */
	private String observacion;
	
	/** Moneda de la Etapa */
	private String moneda;
	
	/** Fecha de Inicio de la Etapa */
	private Date fechaInicioEtapa;
	
	/** Fecha de Fin de la Etapa */
	private Date fechaFinEtapa;
	
	/** Duración */
	private String duracionEtapa;
	
	/** Monto de la Etapa */
	private BigDecimal monto;
	
	/** Indicador si esta registrado :0 No, :1 Si */
	private String isSaved;
	
	/** Atributo para la auditoria */
	private Auditoria auditoria;
		
	/**
	 * Método que permite obtener el tipo de la etapa
	 * @return tipo, tipo String.
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Método que permite establecer el tipo de la etapa
	 * @param tipo, tipo String.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Método que permite obtener el código de la programación
	 * @return codigoProgramacion, tipo Integer.
	 */
	public Integer getCodigoProgramacion() {
		return codigoProgramacion;
	}
	
	/**
	 * Método que permite establecer el código de la programación
	 * @param codigoProgramacion, tipo Integer.
	 */
	public void setCodigoProgramacion(Integer codigoProgramacion) {
		this.codigoProgramacion = codigoProgramacion;
	}
	
	/**
	 * Método que permite obtener el detalle de la programación
	 * @return detalleProgramacion, tipo Integer.
	 */
	public Integer getDetalleProgramacion() {
		return detalleProgramacion;
	}
	
	/**
	 * Método que permite establecer el detalle de la programación
	 * @param detalleProgramacion, tipo Integer.
	 */
	public void setDetalleProgramacion(Integer detalleProgramacion) {
		this.detalleProgramacion = detalleProgramacion;
	}
	
	/**
	 * Método que permite obtener el nombre del inmueble
	 * @return nombreInmueble, tipo String.
	 */
	public String getNombreInmueble() {
		return nombreInmueble;
	}
	
	/**
	 * Método que permite establecer el nombre del inmueble
	 * @param nombreInmueble, tipo String.
	 */
	public void setNombreInmueble(String nombreInmueble) {
		this.nombreInmueble = nombreInmueble;
	}
	
	/**
	 * Método que permite obtener el nombre del bloque
	 * @return nombreBloque, tipo String.
	 */
	public String getNombreBloque() {
		return nombreBloque;
	}
	
	/**
	 * Método que permite establecer el nombre del bloque
	 * @param nombreBloque, tipo String.
	 */
	public void setNombreBloque(String nombreBloque) {
		this.nombreBloque = nombreBloque;
	}
	
	/**
	 * Método que permite obtener el nombre del predio
	 * @return nombrePredio, tipo String.
	 */
	public String getNombrePredio() {
		return nombrePredio;
	}
	
	/**
	 * Método que permite establecer el nombre del predio
	 * @param nombrePredio, tipo String.
	 */
	public void setNombrePredio(String nombrePredio) {
		this.nombrePredio = nombrePredio;
	}
	
	/**
	 * Método que permite obtener la fecha de Inicio de la Etapa
	 * @return fechaInicioEtapa, tipo Date.
	 */
	public Date getFechaInicioEtapa() {
		return fechaInicioEtapa;
	}
	
	/**
	 * Método que permite establecer la fecha de Inicio de la Etapa
	 * @param fechaInicioEtapa, tipo Date.
	 */
	public void setFechaInicioEtapa(Date fechaInicioEtapa) {
		this.fechaInicioEtapa = fechaInicioEtapa;
	}
	
	/**
	 * Método que permite obtener la fecha de Fin de la Etapa
	 * @return servicio, tipo String.
	 */
	public Date getFechaFinEtapa() {
		return fechaFinEtapa;
	}
	
	/**
	 * Método que permite establecer la fecha de Fin de Etapa
	 * @param fechaFinEtapa, tipo Date.
	 */
	public void setFechaFinEtapa(Date fechaFinEtapa) {
		this.fechaFinEtapa = fechaFinEtapa;
	}
	
	/**
	 * Método que permite obtener el monto
	 * @return monto, tipo BigDecimal.
	 */
	public BigDecimal getMonto() {
		return monto;
	}
	
	/**
	 * Método que permite establecer el monto
	 * @param monto, tipo BigDecimal.
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	/**
	 * Método que permite obtener la auditoria
	 * @return auditoria, tipo Auditoria.
	 */
	public Auditoria getAuditoria() {
		return auditoria;
	}
	
	/**
	 * Método que permite establecer la auditoria
	 * @param auditoria, tipo Auditoria.
	 */
	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
	
	/**
	 * Método que permite obtener el código del inmueble
	 * @return codigoInmueble, tipo String.
	 */
	public String getCodigoInmueble() {
		return codigoInmueble;
	}
	
	/**
	 * Método que permite establecer el código del inmueble
	 * @param codigoInmueble, tipo String.
	 */
	public void setCodigoInmueble(String codigoInmueble) {
		this.codigoInmueble = codigoInmueble;
	}
	
	/**
	 * Método que permite obtener el código del bloque
	 * @return codigoBloque, tipo String.
	 */
	public String getCodigoBloque() {
		return codigoBloque;
	}
	
	/**
	 * Método que permite establecer el código del bloque
	 * @param codigoBloque, tipo String.
	 */
	public void setCodigoBloque(String codigoBloque) {
		this.codigoBloque = codigoBloque;
	}
	
	/**
	 * Método que permite obtener el código del bloque
	 * @return codigoPredio, tipo String.
	 */
	public String getCodigoPredio() {
		return codigoPredio;
	}
	
	/**
	 * Método que permite establecer el código del predio
	 * @param codigoPredio, tipo String.
	 */
	public void setCodigoPredio(String codigoPredio) {
		this.codigoPredio = codigoPredio;
	}
	
	/**
	 * Método que permite obtener el motivo
	 * @return motivo, tipo String.
	 */
	public String getMotivo() {
		return motivo;
	}
	
	/**
	 * Método que permite establecer el motivo
	 * @param motivo, tipo String.
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	 * Método que permite obtener la observación
	 * @return observacion, tipo String.
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * Método que permite establecer l observación
	 * @param observacion, tipo String.
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	/**
	 * Método que permite obtener la moneda
	 * @return moneda, tipo String.
	 */
	public String getMoneda() {
		return moneda;
	}
	
	/**
	 * Método que permite establecer la moneda
	 * @param moneda, tipo String.
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	/**
	 * Método que permite obtener el item
	 * @return item, tipo Integer.
	 */
	public Integer getItem() {
		return item;
	}
	
	/**
	 * Método que permite establecer el item
	 * @param item, tipo Integer.
	 */
	public void setItem(Integer item) {
		this.item = item;
	}
	
	/**
	 * Método que permite obtener la duracción de la etapa
	 * @return duracionEtapa, tipo String.
	 */
	public String getDuracionEtapa() {
		return duracionEtapa;
	}
	
	/**
	 * Método que permite establecer la duración de la etapa
	 * @param duracionEtapa, tipo String.
	 */
	public void setDuracionEtapa(String duracionEtapa) {
		this.duracionEtapa = duracionEtapa;
	}
	
	/**
	 * Método que permite obtener el valor de isSaved
	 * @return isSaved, tipo String.
	 */
	public String getIsSaved() {
		return isSaved;
	}
	
	/**
	 * Método que permite establecer el valor de isSaved
	 * @param duracionEtapa, tipo String.
	 */
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	
}
