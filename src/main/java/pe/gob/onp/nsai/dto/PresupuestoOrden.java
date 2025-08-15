/**
* Resumen.
* Objeto               :   PresupuestoOrden.java.
* Descripción          :   Clase utilizada para encapsular los campos del presupuesto de una orden de servicio.
* Fecha de Creación    :   04/06/2021
* PE de Creación       :   INI-900
* Autor                :   Juan Carlos Verde
* --------------------------------------------------------------------------------------------------------------
* Modificaciones
* Motivo                          Fecha             Nombre                  Descripción
* --------------------------------------------------------------------------------------------------------------
*
*/
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;

@Getter
public class PresupuestoOrden implements Serializable{
	
	private static final long serialVersionUID = -4271818863164154376L;

	/** Identificador del predio de la subasta
   * -- GETTER --
   *
   * @return the idPresupuestoOrden
   */
	private int idPresupuestoOrden;
	
	/** Identificador del inmueble
   * -- GETTER --
   *
   * @return the idInmueble
   */
	private String idInmueble;
	
	/** Nombre del inmueble.
   * -- GETTER --
   *
   * @return the inmueble
   */
	private String inmueble;
	
	/** Identificador del predio del inmueble
   * -- GETTER --
   *
   * @return the idInmueblePredio
   */
	private String idInmueblePredio;
	
	/** Nombre del predio.
   * -- GETTER --
   *
   * @return the predio
   */
	private String predio;
	
	/** Identificador del bloque del inmueble
   * -- GETTER --
   *
   * @return the idInmuebleBloque
   */
	private String idInmuebleBloque;
	
	/** Nombre del bloque.
   * -- GETTER --
   *
   * @return the bloque
   */
	private String bloque;

	/**Numero de orden
   * -- GETTER --
   *
   * @return the nroSolicitudSiga
   **/
	private String nroSolicitudSiga;

	/**Numero de orden
   * -- GETTER --
   *
   * @return the numNumOrde
   **/
	private String numNumOrde;
	
	/**Fecha de Inicio de Orden
   * -- GETTER --
   *
   * @return the fecIniOrden
   **/
	private Date fecIniOrden;
	
	/**Fecha de Fin de Orden
   * -- GETTER --
   *
   * @return the fecFinOrden
   **/
	private Date fecFinOrden;
  /**
   * -- GETTER --
   *
   * @return the auditoria
   */
  private Auditoria auditoria;

	
	/** Indicador del estado del registro.
   * -- GETTER --
   *
   * @return the estado
   */
	private String estado;
  /**
   * -- GETTER --
   *
   * @return the idDetPresupuestoOrden
   */
  private int idDetPresupuestoOrden;
  /**
   * -- GETTER --
   *
   * @return the partida
   */
  private String partida;
  /**
   * -- GETTER --
   *
   * @return the unidad
   */
  private String unidad;
  /**
   * -- GETTER --
   *
   * @return the item
   */
  private String item;
  /**
   * -- GETTER --
   *
   * @return the feRegistro
   */
  private String feRegistro;
  /**
   * -- GETTER --
   *
   * @return the unidadDesc
   */
  private String unidadDesc;
  /**
   * -- GETTER --
   *
   * @return the unidadDet
   */
  private String unidadDet;
  /**
   * -- GETTER --
   *
   * @return the nuItem
   */
  private String nuItem;
  /**
   * -- GETTER --
   *
   * @return the cantidad
   */
  private int cantidad;
  /**
   * -- GETTER --
   *
   * @return the dimension
   */
  private BigDecimal dimension;
  /**
   * -- GETTER --
   *
   * @return the metrado
   */
  private Double metrado;
  /**
   * -- GETTER --
   *
   * @return the largo
   */
  private Double largo;
  /**
   * -- GETTER --
   *
   * @return the alto
   */
  private Double alto;
  /**
   * -- GETTER --
   *
   * @return the ancho
   */
  private Double ancho;
  /**
   * -- GETTER --
   *
   * @return the subTotal
   */
  private Double subTotal;
  /**
   * -- GETTER --
   *
   * @return the precioUnidad
   */
  private BigDecimal precioUnidad;
  /**
   * -- GETTER --
   *
   * @return the costoDirecto
   */
  private Double costoDirecto;
  /**
   * -- GETTER --
   *
   * @return the montoIgv
   */
  private Double montoIgv;
  /**
   * -- GETTER --
   *
   * @return the montoManoObra
   */
  private Double montoManoObra;
  /**
   * -- GETTER --
   *
   * @return the montoMaterial
   */
  private Double montoMaterial;
  /**
   * -- GETTER --
   *
   * @return the montoTraslado
   */
  private Double montoTraslado;
  /**
   * -- GETTER --
   *
   * @return the montoTotal
   */
  private Double montoTotal;
  /**
   * -- GETTER --
   *
   * @return the montoTotalDet
   */
  private Double montoTotalDet;
  /**
   * -- GETTER --
   *
   * @return the igv
   */
  private Double igv;
  /**
   * -- GETTER --
   *
   * @return the nombreArchivoDocumento
   */
  private String nombreArchivoDocumento;
  /**
   * -- GETTER --
   *
   * @return the partidaDet
   */
  private String partidaDet;
  /**
   * -- GETTER --
   *
   * @return the coImagDocuAsoc
   */
  private String coImagDocuAsoc;
  /**
   * -- GETTER --
   *
   * @return the idDetPresOrdeDocu
   */
  private int idDetPresOrdeDocu;

  /**
	 * @param nombreArchivoDocumento the nombreArchivoDocumento to set
	 */
	public void setNombreArchivoDocumento(String nombreArchivoDocumento) {
		this.nombreArchivoDocumento = nombreArchivoDocumento;
	}

  /**
	 * @param partidaDet the partidaDet to set
	 */
	public void setPartidaDet(String partidaDet) {
		this.partidaDet = partidaDet;
	}
	//private String coDocumento

  /**
	 * @param idPresupuestoOrden the idPresupuestoOrden to set
	 */
	public void setIdPresupuestoOrden(int idPresupuestoOrden) {
		this.idPresupuestoOrden = idPresupuestoOrden;
	}

  /**
	 * @param idInmueble the idInmueble to set
	 */
	public void setIdInmueble(String idInmueble) {
		this.idInmueble = idInmueble;
	}

  /**
	 * @param inmueble the inmueble to set
	 */
	public void setInmueble(String inmueble) {
		this.inmueble = inmueble;
	}

  /**
	 * @param idInmueblePredio the idInmueblePredio to set
	 */
	public void setIdInmueblePredio(String idInmueblePredio) {
		this.idInmueblePredio = idInmueblePredio;
	}

  /**
	 * @param predio the predio to set
	 */
	public void setPredio(String predio) {
		this.predio = predio;
	}

  /**
	 * @param idInmuebleBloque the idInmuebleBloque to set
	 */
	public void setIdInmuebleBloque(String idInmuebleBloque) {
		this.idInmuebleBloque = idInmuebleBloque;
	}

  /**
	 * @param bloque the bloque to set
	 */
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

  /**
	 * @param nroSolicitudSiga the nroSolicitudSiga to set
	 */
	public void setNroSolicitudSiga(String nroSolicitudSiga) {
		this.nroSolicitudSiga = nroSolicitudSiga;
	}

  /**
	 * @param numNumOrde the numNumOrde to set
	 */
	public void setNumNumOrde(String numNumOrde) {
		this.numNumOrde = numNumOrde;
	}

  /**
	 * @param fecIniOrden the fecIniOrden to set
	 */
	public void setFecIniOrden(Date fecIniOrden) {
		this.fecIniOrden = fecIniOrden;
	}

  /**
	 * @param fecFinOrden the fecFinOrden to set
	 */
	public void setFecFinOrden(Date fecFinOrden) {
		this.fecFinOrden = fecFinOrden;
	}

  /**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

  /**
	 * @param idDetPresupuestoOrden the idDetPresupuestoOrden to set
	 */
	public void setIdDetPresupuestoOrden(int idDetPresupuestoOrden) {
		this.idDetPresupuestoOrden = idDetPresupuestoOrden;
	}

  /**
	 * @param partida the partida to set
	 */
	public void setPartida(String partida) {
		this.partida = partida;
	}

  /**
	 * @param unidad the unidad to set
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

  /**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

  /**
	 * @param feRegistro the feRegistro to set
	 */
	public void setFeRegistro(String feRegistro) {
		this.feRegistro = feRegistro;
	}

  /**
	 * @param unidadDesc the unidadDesc to set
	 */
	public void setUnidadDesc(String unidadDesc) {
		this.unidadDesc = unidadDesc;
	}

  /**
	 * @param unidadDet the unidadDet to set
	 */
	public void setUnidadDet(String unidadDet) {
		this.unidadDet = unidadDet;
	}

  /**
	 * @param nuItem the nuItem to set
	 */
	public void setNuItem(String nuItem) {
		this.nuItem = nuItem;
	}

  /**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

  /**
	 * @param dimension the dimension to set
	 */
	public void setDimension(BigDecimal dimension) {
		this.dimension = dimension;
	}

  /**
	 * @param metrado the metrado to set
	 */
	public void setMetrado(Double metrado) {
		this.metrado = metrado;
	}

  /**
	 * @param largo the largo to set
	 */
	public void setLargo(Double largo) {
		this.largo = largo;
	}

  /**
	 * @param alto the alto to set
	 */
	public void setAlto(Double alto) {
		this.alto = alto;
	}

  /**
	 * @param ancho the ancho to set
	 */
	public void setAncho(Double ancho) {
		this.ancho = ancho;
	}

  /**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

  /**
	 * @param precioUnidad the precioUnidad to set
	 */
	public void setPrecioUnidad(BigDecimal precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

  /**
	 * @param costoDirecto the costoDirecto to set
	 */
	public void setCostoDirecto(Double costoDirecto) {
		this.costoDirecto = costoDirecto;
	}

  /**
	 * @param montoIgv the montoIgv to set
	 */
	public void setMontoIgv(Double montoIgv) {
		this.montoIgv = montoIgv;
	}

  /**
	 * @param montoManoObra the montoManoObra to set
	 */
	public void setMontoManoObra(Double montoManoObra) {
		this.montoManoObra = montoManoObra;
	}

  /**
	 * @param montoMaterial the montoMaterial to set
	 */
	public void setMontoMaterial(Double montoMaterial) {
		this.montoMaterial = montoMaterial;
	}

  /**
	 * @param montoTraslado the montoTraslado to set
	 */
	public void setMontoTraslado(Double montoTraslado) {
		this.montoTraslado = montoTraslado;
	}

  /**
	 * @param montoTotal the montoTotal to set
	 */
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

  /**
	 * @param montoTotalDet the montoTotalDet to set
	 */
	public void setMontoTotalDet(Double montoTotalDet) {
		this.montoTotalDet = montoTotalDet;
	}

  /**
	 * @param auditoria the auditoria to set
	 */
	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

  /**
	 * @param igv the igv to set
	 */
	public void setIgv(Double igv) {
		this.igv = igv;
	}

  /**
	 * @param coImagDocuAsoc the coImagDocuAsoc to set
	 */
	public void setCoImagDocuAsoc(String coImagDocuAsoc) {
		this.coImagDocuAsoc = coImagDocuAsoc;
	}

  /**
	 * @param idDetPresOrdeDocu the idDetPresOrdeDocu to set
	 */
	public void setIdDetPresOrdeDocu(int idDetPresOrdeDocu) {
		this.idDetPresOrdeDocu = idDetPresOrdeDocu;
	}

}