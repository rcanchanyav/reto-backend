
/**
 * Resumen.
 * Objeto               :   ResultadoBusquedaMantenimiento.java.
 * Descripción          :   Objeto que encapsula los datos del resultado de busquedas del modulo de mantenimiento.
 * Fecha de Creación    :   04/05/2018
 * PE de Creación       :   2018-0059
 * Autor                :   Christian Wong Carrasco.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0117				   08/08/2018			Pedro Peña 		Se agregan atributos.
 * INI-900				       12/12/2020			Omar Meza        		Se agregan atributos.
* INI-900					   17/02/2021			Igor A. Quispe Vasquez  Se agregan atributos.
 * INI-900					   08/06/2021			Arnold Coba			    Se agregan atributos.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene los datos de resultado de busqueda del módulo de mantenimiento.
 */
public class ResultadoBusquedaMantenimiento implements Serializable {
	
	/**
	 * Número de serie generado
	 */
	private static final long serialVersionUID = 2380540388858290490L;

	/** Lista de Servicios */
	private List<Servicio> listaServicios;
	
	/** Lista de Proveedores */
	private List<Proveedor> listaProveedor;
	
	/** Lista de Programaciones */
	private List<ProgramacionMantenimiento> listaProgramaciones;
	
	/** Lista de Programaciones Detalle*/
	private List<ProgramacionMantenimientoDetalle> listaProgramacionesDetalle;
	
	/** Lista de ProgramacionMantenimientoDetalle */
	private List<ProgramacionMantenimientoDetalle> listaProgramacionesMantenimientoDetalle;
	
	/** Lista de Contacto de Proveedor */
	private List<ContactoProveedor> listaContacto;
	
	/** Lista de Servicios de Proveedor **/
	private List<ProveedorServicio> listaServicioProveedor;
	
	/** Lista de servicios de costos del proveedor */
	private List<ProyeccionCostoServicioProveedor> listaProyeccionCostoServicioProveedor;
	
	/** Lista de años del servicio del proveedor */
	private List<PeriodoServicioProveedor> listaAniosServicioProveedor;
	
	/** Lista de meses del servicio del proveedor */
	private List<PeriodoServicioProveedor> listaMesesServicioProveedor;
	
	/** Lista de variaciones del servicio por proveedor */
	private List<VariacionServicioProveedor> listaVariacionesServicioProveedor;
	
	/** Lista de los inmuebles del servicio por proveedor */
	private List<InmuebleServicioProveedor> listaInmueblesServicioProveedor;
	
	/** Datos de la proyección del costo del proveedor. */
	public ProyeccionCostoServicioProveedor proyeccionCostoServicio; 
	
	/** Datos de los periodos del proveedor. */
	public PeriodoServicioProveedor periodoServicioProveedor; 
	
	/** Datos de las variaciones del servicio por proveedor. */
	public VariacionServicioProveedor variacionServicioProveedor; 
	
	/** Datos de los servicios del inmueble por proveedor. */
	public InmuebleServicioProveedor inmuebleServicioProveedor; 
	
	/** Lista del tipo de tasa de interes */
	public List<TipoTasaInteres> listaTipoTasaInteres;
	
	/** Lista de los Suministros  */
	private List<SuministroMonto> listaSuministros;
	
	/** Lista de los Suministros con sus montos */
	private List<SuministroMonto> listaSuministrosMontos;
	
	/** Lista de entrega predio */
	private List<EntregaPredio> listaEntregaPredio;
	/** Objeto para almacenar los documentos de la programación de mantenimientos */
	private List<ProgramacionMantenimientoDocumento> listaProgramacioMantenimientoDocumento;
	
	/** Lista de los ambientes por contrato */
	private List<AmbienteContrato> listaAmbienteContrato;
	
	/** Objeto para la almacenar la paginación */
	private Paginacion paginacion;

	/** Objeto para almacenar los datos de los inmuebles con una carta de aviso generada */
	private List<CartaAviso> listaCartaAviso;
	/** Lista de devolucion predio */
	private List<DevolucionPredio> listaDevolucionPredio;
	
	/** Lista de acta devolucion predio */
	private List<DevolucionPredio> listaActaDevolucion;
		
	/** Lista de los pagos servicios */
	private List<PagoServicio> listaPagoServicio;	
	
	/** Lista de los presupuestos de ordenes de servicios */
	private List<PresupuestoOrden> listaPresupuestoOrden;
	
	/** Lista de los pagos servicios de predios */
	private List<GastoServicioPredio> listaPagoServicioPredios;	
	
	/** Lista de los acta base */
	private List<ActaBase> listaActaBAse;	
	
	/** Lista de los acta base */
	private List<EntregaPredioDocumento> listaActaDocumento;	
	
	/**
	 * Método que obtiene la lista de servicios
	 * @return listaServicios lista de servicios, tipo List<Servicio>
	 */
	public List<Servicio> getListaServicios() {
		return listaServicios;
	}
	
	/**
	 * Método que establece la lista de servicios
	 * @param listaServicios lista de servicios, tipo List<Servicio>
	 */
	public void setListaServicios(List<Servicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	/**
	 * Método que obtiene la lista de proveedores
	 * @return listaProveedor lista de proveedores, tipo List<Servicio>
	 */
	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}
	
	/**
	 * Método que establece la lista de proveedores
	 * @param listaProveedor lista de proveedores, tipo List<Servicio>
	 */
	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}
	
	/**
	 * Método que obtiene la paginación
	 * @return paginacion datos de la paginación, tipo Paginacion
	 */
	public Paginacion getPaginacion() {
		return paginacion;
	}
	
	/**
	 * Método que establece la paginación
	 * @param paginacion datos de la paginación, tipo Paginacion
	 */
	public void setPaginacion(Paginacion paginacion) {
		this.paginacion = paginacion;
	}
	
	/**
	 * Método que obtiene la lista contactos de proveedores
	 * @return listaContacto lista de contactos de proveedores, tipo List<ContactoProveedor>
	 */
	public List<ContactoProveedor> getListaContacto() {
		return listaContacto;
	}
	
	/**
	 * Método que establece la lista contactos de proveedores
	 * @param listaContacto lista de contactos de proveedores, tipo List<ContactoProveedor>
	 */
	public void setListaContacto(List<ContactoProveedor> listaContacto) {
		this.listaContacto = listaContacto;
	}
	
	/**
	 * Método que obtiene la lista de servicios de proveedores
	 * @return listaServicioProveedor lista de servicios de proveedores, tipo List<ProveedorServicio>
	 */
	public List<ProveedorServicio> getListaServicioProveedor() {
		return listaServicioProveedor;
	}
	
	/**
	 * Método que establece la lista de servicios de proveedores
	 * @param listaServicioProveedor lista de servicios de proveedores, tipo List<ProveedorServicio>
	 */
	public void setListaServicioProveedor(List<ProveedorServicio> listaServicioProveedor) {
		this.listaServicioProveedor = listaServicioProveedor;
	}

	/**
	 * Método que obtiene la lista de costos de servicios del proveedor.
	 * @return listaProyeccionCostoServicioProveedor costos de servicios del proveedor, tipo List<ProyeccionCostoServicioProveedor>.
	 */
	public List<ProyeccionCostoServicioProveedor> getListaProyeccionCostoServicioProveedor() {
		return listaProyeccionCostoServicioProveedor;
	}

	/**
	 * Método que establece la lista de costos de servicios del proveedor.
	 * @param listaProyeccionCostoServicioProveedor costos de servicios del proveedor, tipo List<ProyeccionCostoServicioProveedor>.
	 */
	public void setListaProyeccionCostoServicioProveedor(List<ProyeccionCostoServicioProveedor> listaProyeccionCostoServicioProveedor) {
		this.listaProyeccionCostoServicioProveedor = listaProyeccionCostoServicioProveedor;
	}

	/**
	 * Método que obtiene la lista de años de servicios del proveedor.
	 * @return listaAniosServicioProveedor lista de años de servicios del proveedor, tipo List<PeriodoServicioProveedor>.
	 */
	public List<PeriodoServicioProveedor> getListaAniosServicioProveedor() {
		return listaAniosServicioProveedor;
	}

	/**
	 * Método que establece la lista de años de servicios del proveedor.
	 * @param listaAniosServicioProveedor lista de años de servicios del proveedor, tipo List<PeriodoServicioProveedor>.
	 */
	public void setListaAniosServicioProveedor(List<PeriodoServicioProveedor> listaAniosServicioProveedor) {
		this.listaAniosServicioProveedor = listaAniosServicioProveedor;
	}

	/**
	 * Método que obtiene la lista de meses de servicios del proveedor.
	 * @return listaMesesServicioProveedor lista de meses de servicios del proveedor, tipo List<PeriodoServicioProveedor>.
	 */
	public List<PeriodoServicioProveedor> getListaMesesServicioProveedor() {
		return listaMesesServicioProveedor;
	}

	/**
	 * Método que establece la lista de meses de servicios del proveedor.
	 * @param listaMesesServicioProveedor lista de meses de servicios del proveedor, tipo List<PeriodoServicioProveedor>.
	 */
	public void setListaMesesServicioProveedor(List<PeriodoServicioProveedor> listaMesesServicioProveedor) {
		this.listaMesesServicioProveedor = listaMesesServicioProveedor;
	}
	 
	/**
	 * Método que obtiene los datos de las proyecciones de costo del proveedor.
	 * @return  proyeccionCostoServicio proyecciones de costo del proveedor, tipo ProyeccionCostoServicioProveedor.
	 */
	public ProyeccionCostoServicioProveedor getProyeccionCostoServicio() {
		return proyeccionCostoServicio;
	}

	/**
	 * Método que establece los datos de las proyecciones de costo del proveedor.
	 * @param proyeccionCostoServicio proyecciones de costo del proveedor, tipo ProyeccionCostoServicioProveedor.
	 */
	public void setProyeccionCostoServicio(ProyeccionCostoServicioProveedor proyeccionCostoServicio) {
		this.proyeccionCostoServicio = proyeccionCostoServicio;
	}

	/**
	 * Método que obtiene los datos de los periodos del servicio del proveedor.
	 * @return periodoServicioProveedor datos de los periodos del servicio del proveedor, tipo PeriodoServicioProveedor.
	 */
	public PeriodoServicioProveedor getPeriodoServicioProveedor() {
		return periodoServicioProveedor;
	}

	/**
	 * Método que establece los datos de los periodos del servicio del proveedor.
	 * @param periodoServicioProveedor datos de los periodos del servicio del proveedor, tipo PeriodoServicioProveedor.
	 */
	public void setPeriodoServicioProveedor(PeriodoServicioProveedor periodoServicioProveedor) {
		this.periodoServicioProveedor = periodoServicioProveedor;
	}

	/**
	 * Método que obtiene la lista de las variaciones de servicios por proveedor.
	 * @return listaVariacionesServicioProveedor lista de las variaciones de servicios por proveedor, tipo List<VariacionServicioProveedor>.
	 */
	public List<VariacionServicioProveedor> getListaVariacionesServicioProveedor() {
		return listaVariacionesServicioProveedor;
	}

	/**
	 * Método que establece la lista de las variaciones de servicios por proveedor.
	 * @param listaVariacionesServicioProveedor lista de las variaciones de servicios por proveedor, tipo List<VariacionServicioProveedor>.
	 */
	public void setListaVariacionesServicioProveedor(List<VariacionServicioProveedor> listaVariacionesServicioProveedor) {
		this.listaVariacionesServicioProveedor = listaVariacionesServicioProveedor;
	}

	/**
	 * Método que obtiene la lista de inmuebles de servicios por proveedor.
	 * @return listaInmueblesServicioProveedor lista de inmuebles de servicios por proveedor, tipo List<InmuebleServicioProveedor>.
	 */
	public List<InmuebleServicioProveedor> getListaInmueblesServicioProveedor() {
		return listaInmueblesServicioProveedor;
	}

	/**
	 * Método que establece la lista de inmuebles de servicios por proveedor.
	 * @param listaInmueblesServicioProveedor lista de inmuebles de servicios por proveedor, tipo List<InmuebleServicioProveedor>.
	 */
	public void setListaInmueblesServicioProveedor(List<InmuebleServicioProveedor> listaInmueblesServicioProveedor) {
		this.listaInmueblesServicioProveedor = listaInmueblesServicioProveedor;
	}

	/**
	 * Método que obtiene los datos del objeto de la variación del servicio por proveedor.
	 * @return variacionServicioProveedor datos del objeto de la variación del servicio por proveedor, tipo VariacionServicioProveedor.
	 */
	public VariacionServicioProveedor getVariacionServicioProveedor() {
		return variacionServicioProveedor;
	}

	/**
	 * Método que establece los datos del objeto de la variación del servicio por proveedor.
	 * @param variacionServicioProveedor datos del objeto de la variación del servicio por proveedor, tipo VariacionServicioProveedor.
	 */
	public void setVariacionServicioProveedor(VariacionServicioProveedor variacionServicioProveedor) {
		this.variacionServicioProveedor = variacionServicioProveedor;
	}

	/**
	 * Método que obtiene los datos del inmueble del servicio por proveedor.
	 * @return inmuebleServicioProveedor inmueble del servicio por proveedor, tipo InmuebleServicioProveedor.
	 */
	public InmuebleServicioProveedor getInmuebleServicioProveedor() {
		return inmuebleServicioProveedor;
	}

	/**
	 * Método que establece los datos del inmueble del servicio por proveedor.
	 * @param inmuebleServicioProveedor inmueble del servicio por proveedor, tipo InmuebleServicioProveedor.
	 */
	public void setInmuebleServicioProveedor(InmuebleServicioProveedor inmuebleServicioProveedor) {
		this.inmuebleServicioProveedor = inmuebleServicioProveedor;
	}

	/**
	 * Método que obtiene la lista del tipo de Tasa de interes.
	 * @return listaTipoTasaInteres lista del tipo de Tasa de interes, tipo List<TipoTasaInteres>.
	 */
	public List<TipoTasaInteres> getListaTipoTasaInteres() {
		return listaTipoTasaInteres;
	}

	/**
	 * Método que establece la lista del tipo de Tasa de interes.
	 * @param listaTipoTasaInteres lista del tipo de Tasa de interes., tipo List<TipoTasaInteres>.
	 */
	public void setListaTipoTasaInteres(List<TipoTasaInteres> listaTipoTasaInteres) {
		this.listaTipoTasaInteres = listaTipoTasaInteres;
	}
	
	/**
	 * Método que permite obtener la lista de suministros.
	 * @return listaSuministros lista de suministros, tipo List<SuministroMonto>.
	 */
	public List<SuministroMonto> getListaSuministros() {
		return listaSuministros;
	}

	/**
	 * Método que permite establecer la lista de suministros.
	 * @param listaSuministros lista de suministros, tipo List<SuministroMonto>.
	 */
	public void setListaSuministros(List<SuministroMonto> listaSuministros) {
		this.listaSuministros = listaSuministros;
	}
	
	/**
	 * Método que permite obtener la lista de suministros con montos.
	 * @return listaSuministrosMontos lista de suministros con montos, tipo List<SuministroMonto>.
	 */
	public List<SuministroMonto> getListaSuministrosMontos() {
		return listaSuministrosMontos;
	}

	/**
	 * Método que permite establecer la lista de suministros con montos.
	 * @param listaSuministrosMontos lista de suministros con montos, tipo List<SuministroMonto>.
	 */
	public void setListaSuministrosMontos(List<SuministroMonto> listaSuministrosMontos) {
		this.listaSuministrosMontos = listaSuministrosMontos;
	}

	/**
	 * @return the listaProgramacion
	 */
	public List<ProgramacionMantenimiento> getListaProgramaciones() {
		return listaProgramaciones;
	}

	/**
	 * @param listaProgramacion the listaProgramacion to set
	 */
	public void setListaProgramaciones(List<ProgramacionMantenimiento> listaProgramaciones) {
		this.listaProgramaciones = listaProgramaciones;
	}

	/**
	 * @return the listaProgramacionesMantenimientoDetalle
	 */
	public List<ProgramacionMantenimientoDetalle> getListaProgramacionesMantenimientoDetalle() {
		return listaProgramacionesMantenimientoDetalle;
	}

	/**
	 * @param listaProgramacionesMantenimientoDetalle the listaProgramacionesMantenimientoDetalle to set
	 */
	public void setListaProgramacionesMantenimientoDetalle(List<ProgramacionMantenimientoDetalle> listaProgramacionesMantenimientoDetalle) {
		this.listaProgramacionesMantenimientoDetalle = listaProgramacionesMantenimientoDetalle;
	}

	/**
	 * @return the listaProgramacionesDetalle
	 */
	public List<ProgramacionMantenimientoDetalle> getListaProgramacionesDetalle() {
		return listaProgramacionesDetalle;
	}

	/**
	 * @param listaProgramacionesDetalle the listaProgramacionesDetalle to set
	 */
	public void setListaProgramacionesDetalle(List<ProgramacionMantenimientoDetalle> listaProgramacionesDetalle) {
		this.listaProgramacionesDetalle = listaProgramacionesDetalle;
	}
	
	/**
	 * Método que permite obtener la lista de predios a entregar.
	 * @return listaEntregaPredio  lista de entrega predios, tipo List<listaEntregaPredio>.
	 */
	public List<EntregaPredio> getListaEntregaPredio() {
		return listaEntregaPredio;
	}

	/**
	 * Método que permite establecer la lista de predios a entregar.
	 * @param listaEntregaPredio lista de entrega predios, tipo List<listaEntregaPredio>.
	 */
	public void setListaEntregaPredio(List<EntregaPredio> listaEntregaPredio) {
		this.listaEntregaPredio = listaEntregaPredio;
	}

	/**
	 * @return the listaAmbienteContrato
	 */
	public List<AmbienteContrato> getListaAmbienteContrato() {
		return listaAmbienteContrato;
	}

	/**
	 * @param listaAmbienteContrato the listaAmbienteContrato to set
	 */
	public void setListaAmbienteContrato(List<AmbienteContrato> listaAmbienteContrato) {
		this.listaAmbienteContrato = listaAmbienteContrato;
	}

	/**
	 * Método que permite obtener la lista de los documentos de la programación de mantenimiento.
	 * @return listaEntregaPredio  lista de entrega predios, tipo List<listaEntregaPredio>.
	 */
	public List<ProgramacionMantenimientoDocumento> getListaProgramacioMantenimientoDocumento() {
		return listaProgramacioMantenimientoDocumento;
	}

	/**
	 * Método que permite establecer la lista de documentos de la programación de mantenimientos.
	 * @param listaProgramacioMantenimientoDocumento lista de documentos de la programación de mantenimientos, tipo List<ProgramacionMantenimientoDocumento>.
	 */
	public void setListaProgramacioMantenimientoDocumento(List<ProgramacionMantenimientoDocumento> listaProgramacioMantenimientoDocumento) {
		this.listaProgramacioMantenimientoDocumento = listaProgramacioMantenimientoDocumento;
	}
	
	/**
	 * Método que permite obtener la lista de los inmuebles con cartas de aviso generadas.
	 * @return listaInmuebleServicioCarta  lista de inmuebles con cartas de aviso generadas, tipo List<CartaAviso>.
	 */
	public List<CartaAviso> getListaCartaAviso() {
		return listaCartaAviso;
	}

	/**
	 * Método que permite establecer la lista de los inmuebles con cartas de aviso generadas.
	 * @param listaInmuebleServicioCarta la lista de los inmuebles con cartas de aviso generadas, tipo List<CartaAviso>.
	 */
	public void setListaCartaAviso(List<CartaAviso> listaCartaAviso) {
		this.listaCartaAviso = listaCartaAviso;
	}

	/**
	 * Método que permite obtener la lista de pagos servicios generados.
	 * @return listaPagoServicio  lista de PagoServicio generados, tipo List<PagoServicio>.
	 */	
	public List<PagoServicio> getListaPagoServicio() {
		return listaPagoServicio;
	}

	/**
	 * Método que permite establecer la lista de los pagos servicios generadas.
	 * @param listaPagoServicio la lista de los PagoServicio generados, tipo List<CartaAviso>.
	 */	
	public void setListaPagoServicio(List<PagoServicio> listaPagoServicio) {
		this.listaPagoServicio = listaPagoServicio;
	}
	
	/**
	 * Método que permite obtener la lista de predios a entregar.
	 * @return listaEntregaPredio  lista de entrega predios, tipo List<DevolucionPredio>.
	 */
	public List<DevolucionPredio> getListaDevolucionPredio() {
		return listaDevolucionPredio;
	}

	/**
	 * Método que permite establecer la lista de predios a entregar.
	 * @param listaEntregaPredio lista de entrega predios, tipo List<DevolucionPredio>.
	 */
	public void setListaDevolucionPredio(List<DevolucionPredio> listaDevolucionPredio) {
		this.listaDevolucionPredio = listaDevolucionPredio;
	}
	
	
	/**
	 * Método que permite obtener la lista de acta de devolucion.
	 * @return listaActaDevolucion  lista de devolucion, tipo List<DevolucionPredio>.
	 */
	public List<DevolucionPredio> getListaActaDevolucion() {
		return listaActaDevolucion;
	}

	/**
	 * Método que permite establecer la lista de predios a entregar.
	 * @param listaActaDevolucion lista de entrega predios, tipo List<DevolucionPredio>.
	 */
	public void setListaActaDevolucion(List<DevolucionPredio> listaActaDevolucion) {
		this.listaActaDevolucion = listaActaDevolucion;
	}

	/**
	 * Método que permite obtener la lista de los ordenes de presupuesto.
	 * @return the listaPresupuestoOrden
	 */
	public List<PresupuestoOrden> getListaPresupuestoOrden() {
		return listaPresupuestoOrden;
	}

	/**
	 * Método que permite establecer la lista de los ordenes de presupuesto.
	 * @param listaPresupuestoOrden the listaPresupuestoOrden to set
	 */
	public void setListaPresupuestoOrden(List<PresupuestoOrden> listaPresupuestoOrden) {
		this.listaPresupuestoOrden = listaPresupuestoOrden;
	}

	public List<GastoServicioPredio> getListaPagoServicioPredios() {
		return listaPagoServicioPredios;
	}

	public void setListaPagoServicioPredios(List<GastoServicioPredio> listaPagoServicioPredios) {
		this.listaPagoServicioPredios = listaPagoServicioPredios;
	}

	/**
	 * @return the listaActaBAse
	 */
	public List<ActaBase> getListaActaBAse() {
		return listaActaBAse;
	}

	/**
	 * @param listaActaBAse the listaActaBAse to set
	 */
	public void setListaActaBAse(List<ActaBase> listaActaBAse) {
		this.listaActaBAse = listaActaBAse;
	}

	/**
	 * @return the listaActaDocumento
	 */
	public List<EntregaPredioDocumento> getListaActaDocumento() {
		return listaActaDocumento;
	}

	/**
	 * @param listaActaDocumento the listaActaDocumento to set
	 */
	public void setListaActaDocumento(List<EntregaPredioDocumento> listaActaDocumento) {
		this.listaActaDocumento = listaActaDocumento;
	}	
	
}
