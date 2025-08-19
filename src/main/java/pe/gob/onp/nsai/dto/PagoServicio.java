/**
 * Resumen.
 * Objeto               :   Pago Servicio.java.
 * Descripción          :   DTO utilizado para encapsular los campos del Pago Servicio.
 * Fecha de Creación    :   05/06/2020
 * PE de Creación       :   INI-900
 * Autor                :   Karina Matew
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 *Clase que contiene los atributos del pago servicio.
 */
public class PagoServicio implements Serializable {

    private static final long serialVersionUID = -1250454716375509318L;

    private Auditoria auditoria;

    private Integer idPagoServicio;

    private String idInmueblePredio;

    private String numeroGasto;

    private String numeroServicio;

    private Date fechaHora;

    private Date fechaVencimiento;

    private BigDecimal montoValor;

    private String codigoTipoMoneda;

//    private Date fechaPeriodo;

    private String codigoTipoServPubli;

    private String codigoTipoServ;

    private Date fechaFactura;

    private String codigoProveedor;

    private String numeroSuministro;

    private BigDecimal montoCargoFijo;

    private BigDecimal conexionReparacion;

    private  BigDecimal numeroIgv;

    private BigDecimal consumoKwHoPunta;

    private BigDecimal consumoKwFuPunta;

    private BigDecimal consumoEnergiaReact;

    private BigDecimal potenciaGeneracion;

    private BigDecimal potenciaDistribucion;

    private BigDecimal alumbradoPublico;

    private BigDecimal interesCompensatorio;

    private BigDecimal electrificacionRural;

    private BigDecimal interesMoratorio;

    private BigDecimal redondeoMesAnt;

    private BigDecimal redondeoMesAct;

    private BigDecimal consumoEnergia;

    private BigDecimal totalDomestico;

    private BigDecimal totalComercial;

    private BigDecimal moras;

    private BigDecimal devoluciones;

    private String idInmueble;

    private String decretoLegal;

    private BigDecimal total;

    private Double montoTotal;

    private String idProveedor;

    private String tipoServicio;

    private String proveedor;

//	private String inmueble;

    private String tipoVia;

    private String predio;

    private String bloque;

    private String tipoBloque;

    private String direccion;

    /** Descripción del inmueble  */
    private String descripcionInmueble;
    /** Descripción del Bloque   */
    private String descripcionBloque;
    /** Descripción del Predio  */
    private String descripcionPredio;

    /**mes de factura del pago Servicio */
    private String mes;

    /**anio de factura del pago Servicio*/
    private String anio;

    private Integer item;

    /** Identificador del bloque del inmueble. */
    private String idInmuebleBloque;

    /** Código de predio. */
    private String codigoPredio;

    /** Código del inmueble. */
    private String codigo;


    /** Disponibilidad del área techada */
    private String estadoVigenciaPredio;

    /** Código del tipo de Inmueble. */
    private String codigoTipoInmueble;


    /** Provincia del Predio */
    @NotNull(message="{inmueblePredio.codigoProvincia.notNull}")
    @Size(min = 6, max = 6,message="{inmueblePredio.codigoProvincia.size}")
    private String codigoProvincia;

    /** Departamento del Predio  */
    @NotNull(message="{inmueblePredio.codigoDepartamento.notNull}")
    @Size(min = 6, max = 6,message="{inmueblePredio.codigoDepartamento.size}")
    private String codigoDepartamento;

    /** Distrito del Predio */
    @NotNull(message="{inmueblePredio.codigoDistrito.notNull}")
    @Size(min = 6, max = 6,message="{inmueblePredio.codigoDistrito.size}")
    private String codigoDistrito;

    /** Codigo de Ubigeo  */
    private String codigoAreaGeografica;


    /** Código del tipo de uso del predio. */
    @NotNull(message="{inmueblePredio.codigoTipoUsoPredio.notNull}")
    @Size(min = 2, max = 2,message="{inmueblePredio.codigoTipoUsoPredio.size}")
    private String codigoTipoUsoPredio;


    private BigDecimal montoServAlca;

    private BigDecimal moCostoCierre;

    private BigDecimal moDeuda;


    /** Tipo formato margesi. */
    private Integer formato;

    public String getDecretoLegal() {
        return decretoLegal;
    }

    public void setDecretoLegal(String decretoLegal) {
        this.decretoLegal = decretoLegal;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public String getNumeroGasto() {
        return numeroGasto;
    }

    public void setNumeroGasto(String numeroGasto) {
        this.numeroGasto = numeroGasto;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getMontoValor() {
        return montoValor;
    }

    public void setMontoValor(BigDecimal montoValor) {
        this.montoValor = montoValor;
    }

    public String getCodigoTipoMoneda() {
        return codigoTipoMoneda;
    }

    public void setCodigoTipoMoneda(String codigoTipoMoneda) {
        this.codigoTipoMoneda = codigoTipoMoneda;
    }

/*	public Date getFechaPeriodo() {
		return fechaPeriodo;
	}

	public void setFechaPeriodo(Date fechaPeriodo) {
		this.fechaPeriodo = fechaPeriodo;
	}
*/

    public String getCodigoTipoServ() {
        return codigoTipoServ;
    }

    public void setCodigoTipoServ(String codigoTipoServ) {
        this.codigoTipoServ = codigoTipoServ;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public BigDecimal getMontoCargoFijo() {
        return montoCargoFijo;
    }

    public void setMontoCargoFijo(BigDecimal montoCargoFijo) {
        this.montoCargoFijo = montoCargoFijo;
    }

    public BigDecimal getNumeroIgv() {
        return numeroIgv;
    }

    public void setNumeroIgv(BigDecimal numeroIgv) {
        this.numeroIgv = numeroIgv;
    }

    public BigDecimal getInteresCompensatorio() {
        return interesCompensatorio;
    }

    public void setInteresCompensatorio(BigDecimal interesCompensatorio) {
        this.interesCompensatorio = interesCompensatorio;
    }

    public BigDecimal getElectrificacionRural() {
        return electrificacionRural;
    }

    public void setElectrificacionRural(BigDecimal electrificacionRural) {
        this.electrificacionRural = electrificacionRural;
    }

    public BigDecimal getInteresMoratorio() {
        return interesMoratorio;
    }

    public void setInteresMoratorio(BigDecimal interesMoratorio) {
        this.interesMoratorio = interesMoratorio;
    }

    public BigDecimal getRedondeoMesAnt() {
        return redondeoMesAnt;
    }

    public void setRedondeoMesAnt(BigDecimal redondeoMesAnt) {
        this.redondeoMesAnt = redondeoMesAnt;
    }

    public BigDecimal getRedondeoMesAct() {
        return redondeoMesAct;
    }

    public void setRedondeoMesAct(BigDecimal redondeoMesAct) {
        this.redondeoMesAct = redondeoMesAct;
    }

    public BigDecimal getTotalDomestico() {
        return totalDomestico;
    }

    public void setTotalDomestico(BigDecimal totalDomestico) {
        this.totalDomestico = totalDomestico;
    }

    public BigDecimal getTotalComercial() {
        return totalComercial;
    }

    public void setTotalComercial(BigDecimal totalComercial) {
        this.totalComercial = totalComercial;
    }

    public BigDecimal getMoras() {
        return moras;
    }

    public void setMoras(BigDecimal moras) {
        this.moras = moras;
    }

    public BigDecimal getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(BigDecimal devoluciones) {
        this.devoluciones = devoluciones;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getIdPagoServicio() {
        return idPagoServicio;
    }

    public void setIdPagoServicio(Integer idPagoServicio) {
        this.idPagoServicio = idPagoServicio;
    }

    public String getIdInmueblePredio() {
        return idInmueblePredio;
    }

    public void setIdInmueblePredio(String idInmueblePredio) {
        this.idInmueblePredio = idInmueblePredio;
    }

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getNumeroSuministro() {
        return numeroSuministro;
    }

    public void setNumeroSuministro(String numeroSuministro) {
        this.numeroSuministro = numeroSuministro;
    }

    public BigDecimal getConexionReparacion() {
        return conexionReparacion;
    }

    public void setConexionReparacion(BigDecimal conexionReparacion) {
        this.conexionReparacion = conexionReparacion;
    }

    public BigDecimal getConsumoKwHoPunta() {
        return consumoKwHoPunta;
    }

    public void setConsumoKwHoPunta(BigDecimal consumoKwHoPunta) {
        this.consumoKwHoPunta = consumoKwHoPunta;
    }

    public BigDecimal getConsumoKwFuPunta() {
        return consumoKwFuPunta;
    }

    public void setConsumoKwFuPunta(BigDecimal consumoKwFuPunta) {
        this.consumoKwFuPunta = consumoKwFuPunta;
    }

    public BigDecimal getConsumoEnergiaReact() {
        return consumoEnergiaReact;
    }

    public void setConsumoEnergiaReact(BigDecimal consumoEnergiaReact) {
        this.consumoEnergiaReact = consumoEnergiaReact;
    }

    public BigDecimal getPotenciaGeneracion() {
        return potenciaGeneracion;
    }

    public void setPotenciaGeneracion(BigDecimal potenciaGeneracion) {
        this.potenciaGeneracion = potenciaGeneracion;
    }

    public BigDecimal getPotenciaDistribucion() {
        return potenciaDistribucion;
    }

    public void setPotenciaDistribucion(BigDecimal potenciaDistribucion) {
        this.potenciaDistribucion = potenciaDistribucion;
    }

    public BigDecimal getAlumbradoPublico() {
        return alumbradoPublico;
    }

    public void setAlumbradoPublico(BigDecimal alumbradoPublico) {
        this.alumbradoPublico = alumbradoPublico;
    }

    public BigDecimal getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(BigDecimal consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public String getCodigoTipoServPubli() {
        return codigoTipoServPubli;
    }

    public void setCodigoTipoServPubli(String codigoTipoServPubli) {
        this.codigoTipoServPubli = codigoTipoServPubli;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

/*	public String getInmueble() {
		return inmueble;
	}

	public void setInmueble(String inmueble) {
		this.inmueble = inmueble;
	}*/

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }



    public String getTipoBloque() {
        return tipoBloque;
    }

    public void setTipoBloque(String tipoBloque) {
        this.tipoBloque = tipoBloque;
    }


    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getDescripcionInmueble() {
        return descripcionInmueble;
    }

    public void setDescripcionInmueble(String descripcionInmueble) {
        this.descripcionInmueble = descripcionInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    /**
     * @return the idInmuebleBloque
     */
    public String getIdInmuebleBloque() {
        return idInmuebleBloque;
    }

    /**
     * @param idInmuebleBloque the idInmuebleBloque to set
     */
    public void setIdInmuebleBloque(String idInmuebleBloque) {
        this.idInmuebleBloque = idInmuebleBloque;
    }

    /**
     * @return the codigoPredio
     */
    public String getCodigoPredio() {
        return codigoPredio;
    }

    /**
     * @param codigoPredio the codigoPredio to set
     */
    public void setCodigoPredio(String codigoPredio) {
        this.codigoPredio = codigoPredio;
    }


    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the estadoVigenciaPredio
     */
    public String getEstadoVigenciaPredio() {
        return estadoVigenciaPredio;
    }

    /**
     * @param estadoVigenciaPredio the estadoVigenciaPredio to set
     */
    public void setEstadoVigenciaPredio(String estadoVigenciaPredio) {
        this.estadoVigenciaPredio = estadoVigenciaPredio;
    }

    /**
     * @return the codigoTipoInmueble
     */
    public String getCodigoTipoInmueble() {
        return codigoTipoInmueble;
    }

    /**
     * @param codigoTipoInmueble the codigoTipoInmueble to set
     */
    public void setCodigoTipoInmueble(String codigoTipoInmueble) {
        this.codigoTipoInmueble = codigoTipoInmueble;
    }

    /**
     * @return the codigoProvincia
     */
    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    /**
     * @param codigoProvincia the codigoProvincia to set
     */
    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    /**
     * @return the codigoDepartamento
     */
    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    /**
     * @param codigoDepartamento the codigoDepartamento to set
     */
    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    /**
     * @return the codigoDistrito
     */
    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    /**
     * @param codigoDistrito the codigoDistrito to set
     */
    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    /**
     * @return the codigoAreaGeografica
     */
    public String getCodigoAreaGeografica() {
        return codigoAreaGeografica;
    }

    /**
     * @param codigoAreaGeografica the codigoAreaGeografica to set
     */
    public void setCodigoAreaGeografica(String codigoAreaGeografica) {
        this.codigoAreaGeografica = codigoAreaGeografica;
    }

    /**
     * @return the codigoTipoUsoPredio
     */
    public String getCodigoTipoUsoPredio() {
        return codigoTipoUsoPredio;
    }

    /**
     * @param codigoTipoUsoPredio the codigoTipoUsoPredio to set
     */
    public void setCodigoTipoUsoPredio(String codigoTipoUsoPredio) {
        this.codigoTipoUsoPredio = codigoTipoUsoPredio;
    }

    /**
     * @return the descripcionBloque
     */
    public String getDescripcionBloque() {
        return descripcionBloque;
    }

    /**
     * @param descripcionBloque the descripcionBloque to set
     */
    public void setDescripcionBloque(String descripcionBloque) {
        this.descripcionBloque = descripcionBloque;
    }

    /**
     * @return the descripcionPredio
     */
    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    /**
     * @param descripcionPredio the descripcionPredio to set
     */
    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }

    /**
     * @return the montoServAlca
     */
    public BigDecimal getMontoServAlca() {
        return montoServAlca;
    }

    /**
     * @param montoServAlca the montoServAlca to set
     */
    public void setMontoServAlca(BigDecimal montoServAlca) {
        this.montoServAlca = montoServAlca;
    }

    /**
     * @return the moCostoCierre
     */
    public BigDecimal getMoCostoCierre() {
        return moCostoCierre;
    }

    /**
     * @param moCostoCierre the moCostoCierre to set
     */
    public void setMoCostoCierre(BigDecimal moCostoCierre) {
        this.moCostoCierre = moCostoCierre;
    }

    /**
     * @return the moDeuda
     */
    public BigDecimal getMoDeuda() {
        return moDeuda;
    }

    /**
     * @param moDeuda the moDeuda to set
     */
    public void setMoDeuda(BigDecimal moDeuda) {
        this.moDeuda = moDeuda;
    }

    /**
     * @return the formato
     */
    public Integer getFormato() {
        return formato;
    }

    /**
     * @param formato the formato to set
     */
    public void setFormato(Integer formato) {
        this.formato = formato;
    }



}