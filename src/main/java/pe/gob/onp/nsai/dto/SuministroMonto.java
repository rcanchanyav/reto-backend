/**
 * Resumen.
 * Objeto               :   SuministroMonto.java.
 * Descripción          :   DTO utilizado para encapsular los campos de los suministros.
 * Fecha de Creación    :   04/09/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Pedro Aguilar
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * 
 */
package pe.gob.onp.nsai.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import pe.gob.onp.nsai.util.GeneradorID;

/** Clase que contiene los atributos de los suministros. */
public class SuministroMonto implements Serializable{
	
	/** Número de versión de la clase Serializable. */
	private static final long serialVersionUID = 530025405088071476L;
	
	/** Objeto instanciado para generar el id cifrado. */
	private IdHash hashId=new IdHash();
	
	/** Identificador del suministro del inmueble. */
	private Integer idSuministro;
	
	/** Identificador del Inmueble. */
	private String idInmueble;
	
	/** Tipo de Servicio. */
	private String tipoServicio;
	
	/** Código del tipo de Servicio. */
	private String codigoTipoServicio;
	
	/** Código de la empresa de Servicio Público. */
	private String codigoEmpresaServicioPublico;
	
	/** Empresa de Servicio Público. */
	private String empresaServicioPublico;
	
	/** Nombre completo del inmueble. */
	private String nombreCompletoInmueble;
	
	/** Identifiador del suministro */
	private Integer identificadorSuministro;
	
	/** Identificador del suministro del bloque */
	private Integer idBloqueSuministro;
	
	/** Número de suministros del inmueble */
	@NotNull(message="{inmuebleSuministro.numeroSuministroInmueble.notNull}")
	@Size(max = 15,message="{inmuebleSuministro.numeroSuministroInmueble.size}")
	private String numeroSuministro;
	
	/** Estado del registro. 1: Activo, 0: Inactivo. */
	private String indicadorEstadoRegistro;
		
	/** Descripción del Inmueble. */
	private String descripcionInmueble;
	
	/** Origen del suministro. */
	private String origenRegistroSuministro;
	
	/** Tipo Bloque */
	private String tipoBloque;
	
	/** Nombre del Bloque */
	private String nombreBloque;
	
	/** año del suministro */
	private Integer anioSuministro;
	
	/** Mes del suministro */
	private String mesSuministro;
	
	/** Código del suministro */
	private String codigoEstadoSuministro;
	
	/** Tipo de moneda */
	private String tipoMoneda;
	
	/** Código de la moneda */
	private String codigoMoneda;
	
	/** Identificador del suministro de origen */
	private Integer idSuministroOrigen;
		
	/** Monto del suministro. */
	@Digits(integer=9, fraction=2, message="{contrato.montoRenta.digits}")
	private BigDecimal montoValorSuministro;
	
	/** Identificador del bloque */
	private String idBloque;

	/** Código del tipo de bloque */
	private String codigoTipoBloque;  

	/** Identificador del suministro del inmueble. */
	private Integer idInmuebleSuministro;
	
	/** Objeto de auditoria. */
	private Auditoria auditoria;

	/**
     * Método que obtiene el identificador del suministro.
     * @return idSuministro identificador del suministro, tipo Integer.
     */
	public Integer getIdSuministro() {
		return idSuministro;
	}

	/**
	 * Método que establece el identificador del suministro.
	 * @param idSuministro identificador del suministro, tipo Integer.
	 */
	public void setIdSuministro(Integer idSuministro) {
		if(this.hashId.getIdSecundario() == null){
			this.hashId.setIdSecundario(GeneradorID.codificarID(idSuministro));
			this.idSuministro=null;
		}else{
			this.idSuministro = idSuministro;
		}
	}
	
	/**
	 * Método que obtiene el identificador del inmueble.
	 * @return idInmueble identificador del inmueble, tipo String.
	 */
	public String getIdInmueble() {
		return idInmueble;
	}

	/**
	 * Método que establece el identificador del inmueble.
	 * @param idInmueble identificador del inmueble, tipo String.
	 */
	public void setIdInmueble(String idInmueble) {
		this.idInmueble = idInmueble;
	}

	/**
	 * Método que obtiene el tipo de servicio asociado al suministro.
	 * @return tipoServicio tipo de servicio asociado al suministro, tipo String.
	 */
	public String getTipoServicio() {
		return tipoServicio;
	}

	/**
	 * Método que establece el tipo de servicio asociado al suministro.
	 * @param tipoServicio tipo de servicio asociado al suministro, tipo String.
	 */
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	/**
	 * Método que obtiene la empresa de servicio público asociado al suministro.
	 * @return empresaServicioPublico empresa de servicio público asociado al suministro, tipo String.
	 */
	public String getEmpresaServicioPublico() {
		return empresaServicioPublico;
	}

	/**
	 * Método que establece la empresa de servicio público asociado al suministro.
	 * @param empresaServicioPublico empresa de servicio público asociado al suministro, tipo String.
	 */
	public void setEmpresaServicioPublico(String empresaServicioPublico) {
		this.empresaServicioPublico = empresaServicioPublico;
	}

	/**
	 * Método que obtiene el número de suministros.
	 * @return numSuministro Número de suministro, tipo String.
	 */
	public String getNumeroSuministro() {
		return numeroSuministro;
	}

	/**
	 * Método que establece el número de suministro.
	 * @param numeroSuministro Número de suministro, tipo String.
	 */
	public void setNumeroSuministro(String numeroSuministro) {
		this.numeroSuministro = numeroSuministro;
	}

	/**
	 * Método que obtiene el indicador de estado del registro.
	 * @return indicadorEstadoRegistro indicador de estado del registro, tipo String.
	 */
	public String getIndicadorEstadoRegistro() {
		return indicadorEstadoRegistro;
	}

	/**
	 * Método que establece el indicador de estado del registro.
	 * @param indicadorEstadoRegistro indicador de estado del registro, tipo String.
	 */
	public void setIndicadorEstadoRegistro(String indicadorEstadoRegistro) {
		this.indicadorEstadoRegistro = indicadorEstadoRegistro;
	}
	
	/**
	 * Método que obtiene el objeto instanciado para generar el id cifrado.
	 * @return hashId objeto instanciado para generar el id cifrado, tipo IdHash.
	 */
	public IdHash getHashId() {
		return hashId;
	}

	/**
	 * Método que establece el objeto instanciado para generar el id cifrado.
	 * @param hashId objeto instanciado para generar el id cifrado, tipo IdHash.
	 */
	public void setHashId(IdHash hashId) {
		this.hashId = hashId;
	}

	/**
	 * Método que permite obtener la descripción del Inmueble.
	 * @return descripcionInmueble descripción del Inmueble, tipo String.
	 */
	public String getDescripcionInmueble() {
		return descripcionInmueble;
	}

	/**
	 * Método que permite establecer la descripción del Inmueble.
	 * @param descripcionInmueble descripción del Inmueble, tipo String.
	 */
	public void setDescripcionInmueble(String descripcionInmueble) {
		this.descripcionInmueble = descripcionInmueble;
	}
	
	/**
	 * Método que obtiene el objeto de auditoria .
	 * @return auditoria objeto auditoria, tipo Auditoria.
	 */
	public Auditoria getAuditoria() {
		return auditoria;
	}
	
	/**
	 * Método que establece el objeto de auditoria.
	 * @param auditoria objeto auditoria, , tipo Auditoria.
	 */
	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	/**
	 * Método que permite obtener el código del tipo de servicio.
	 * @return codigoTipoServicio código del tipo de servicio, tipo String.
	 */
	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	/**
	 * Método que permite establecer el código del tipo de servicio.
	 * @param codigoTipoServicio código del tipo de servicio, tipo String.
	 */
	public void setCodigoTipoServicio(String codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}

	/**
	 * Método que permite obtener el código de la empresa de servicio público.
	 * @return codigoEmpresaServicioPublico código de la empresa de servicio público, tipo String.
	 */
	public String getCodigoEmpresaServicioPublico() {
		return codigoEmpresaServicioPublico;
	}

	/**
	 * Método que permite establecer el código de la empresa de servicio público.
	 * @param codigoEmpresaServicioPublico código del la empresa de servicio público, tipo String.
	 */
	public void setCodigoEmpresaServicioPublico(String codigoEmpresaServicioPublico) {
		this.codigoEmpresaServicioPublico = codigoEmpresaServicioPublico;
	}
	
	/**
	 *  Método que obtiene el identificador de origen del suministro.
	 * @return idSuministroOrigen identificador de origen del suministro, tipo Integer.
	 */
	public Integer getIdSuministroOrigen() {
		return idSuministroOrigen;
	}

	/**
	 *  Método que establece el identificador de origen del suministro.
	 * @param idSuministroOrigen identificador de origen del suministro, tipo Integer.
	 */
	public void setIdSuministroOrigen(Integer idSuministroOrigen) {
		this.idSuministroOrigen = idSuministroOrigen;
	}
		
	/**
	 * Método que obtiene el monto del servicio.
	 * @return montoValorSuministro monto del servicio, tipo BigDecimal.
	 */
	public BigDecimal getMontoValorSuministro() {
		return montoValorSuministro;
	}

	/**
	 * Método que establece el monto del servicio.
	 * @param montoValorSuministro monto del servicio, tipo BigDecimal.
	 */
	public void setMontoValorSuministro(BigDecimal montoValorSuministro) {
		this.montoValorSuministro = montoValorSuministro;
	}

	/**
	 * Método que obtiene el origen del registro del suministro.
	 * @return origenRegistroSuministro origen del registro del suministro, tipo String
	 */
	public String getOrigenRegistroSuministro() {
		return origenRegistroSuministro;
	}

	/**
	 * Método que establece el origen del registro del suministro.
	 * @param origenRegistroSuministro origen del registro del suministro, tipo String
	 */
	public void setOrigenRegistroSuministro(String origenRegistroSuministro) {
		this.origenRegistroSuministro = origenRegistroSuministro;
	}

	/**
	 * Método que obtiene el tipo de bloque.
	 * @return tipoBloque tipo de bloque, tipo String
	 */
	public String getTipoBloque() {
		return tipoBloque;
	}

	/**
	 * Método que establece el tipo de bloque.
	 * @param tipoBloque tipo de bloque, tipo String
	 */
	public void setTipoBloque(String tipoBloque) {
		this.tipoBloque = tipoBloque;
	}

	/**
	 * Método que obtiene el nombre del bloque.
	 * @return nombreBloque nombre del bloque, tipo String.
	 */
	public String getNombreBloque() {
		return nombreBloque;
	}

	/**
	 * Método que establece el nombre del bloque.
	 * @param nombreBloque nombre del bloque, tipo String.
	 */
	public void setNombreBloque(String nombreBloque) {
		this.nombreBloque = nombreBloque;
	}

	/**
	 * Método que obtiene el año del suministro
	 * @return anioSuministro año del suministro, tipo Integer
	 */
	public Integer getAnioSuministro() {
		return anioSuministro;
	}

	/**
	 * Método que establece el año del suministro
	 * @param anioSuministro año del suministro, tipo Integer
	 */
	public void setAnioSuministro(Integer anioSuministro) {
		this.anioSuministro = anioSuministro;
	}

	/**
	 * Método que obtiene el mes del suministro.
	 * @return mesSuministro mes del suministro, tipo String.
	 */
	public String getMesSuministro() {
		return mesSuministro;
	}

	/**
	 * Método que establece el mes del suministro.
	 * @param mesSuministro mes del suministro, tipo String.
	 */
	public void setMesSuministro(String mesSuministro) {
		this.mesSuministro = mesSuministro;
	}

	/**
	 * Método que obtiene el código del suministro.
	 * @return codigoEstadoSuministro código del suministro, tipo String.
	 */
	public String getCodigoEstadoSuministro() {
		return codigoEstadoSuministro;
	}

	/**
	 * Método que establece el código del suministro.
	 * @param codigoEstadoSuministro código del suministro, tipo String.
	 */
	public void setCodigoEstadoSuministro(String codigoEstadoSuministro) {
		this.codigoEstadoSuministro = codigoEstadoSuministro;
	}

	/** 
	 * Método que obtiene el tipo de moneda.
	 * @return tipoMoneda tipo de moneda, tipo String.
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}

	/**
	 * Método que establece el tipo de moneda.
	 * @param tipoMoneda tipo de moneda, tipo String.
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	/**
	 * Método que obtiene el código de moneda.
	 * @return codigoMoneda código de moneda, tipo String
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Método que establece el código de moneda
	 * @param codigoMoneda código de moneda, tipo String
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}
	
	/**
	 * Método que permite obtener el id del bloque.
	 * @return idBloque id del bloque, tipo String.
	 */
	public String getIdBloque() {
		return idBloque;
	}

	/**
	 * Método que permite establecer el id del bloque.
	 * @param idBloque id del bloque del inmueble, tipo String.
	 */
	public void setIdBloque(String idBloque) {
		this.idBloque = idBloque;
	}

	/**
	 * Método que permite obtener el código del tipo de bloque.
	 * @return codigoTipoBloque código del tipo de bloque, tipo String.
	 */
	public String getCodigoTipoBloque() {
		return codigoTipoBloque;
	}

	/**
	 * Método que permite establecer el código del tipo de bloque.
	 * @param codigoTipoBloque código del tipo de bloque, tipo String.
	 */
	public void setCodigoTipoBloque(String codigoTipoBloque) {
		this.codigoTipoBloque = codigoTipoBloque;
	}

	/**
	 * Método que obtiene el nombre completo del inmueble.
	 * @return nombreCompletoInmueble nombre completo del inmueble, tipo String.
	 */
	public String getNombreCompletoInmueble() {
		return nombreCompletoInmueble;
	}

	/**
	 * Método que establece el nombre completo del inmueble.
	 * @param nombreCompletoInmueble nombre completo del inmueble, tipo String.
	 */
	public void setNombreCompletoInmueble(String nombreCompletoInmueble) {
		this.nombreCompletoInmueble = nombreCompletoInmueble;
	}
	
	/**
	 * Método que obtiene el identificador del suministro.
	 * @return identificadorSuministro identificador del suministro, tipo Integer.
	 */
	public Integer getIdentificadorSuministro() {
		return identificadorSuministro;
	}

	/**
	 * Método que establece el identificador del suministro.
	 * @param identificadorSuministro identificador del suministro, tipo Integer.
	 */
	public void setIdentificadorSuministro(Integer identificadorSuministro) {
		this.identificadorSuministro = identificadorSuministro;
	}

	/**
	 * Método que obtiene el identificador del suministro del bloque.
	 * @return idBloqueSuministro identificador del suministro del bloque, tipo Integer.
	 */
	public Integer getIdBloqueSuministro() {
		return idBloqueSuministro;
	}

	/**
	 * Método que establece el identificador del suministro del bloque.
	 * @param idBloqueSuministro identificador del suministro del bloque, tipo Integer.
	 */
	public void setIdBloqueSuministro(Integer idBloqueSuministro) {
		this.idBloqueSuministro = idBloqueSuministro;
	}
	
	/**
     * Método que obtiene el identificador del suministro del inmueble.
     * @return idInmuebleSuministro identificador del suministro del inmueble, tipo Integer.
     */
	public Integer getIdInmuebleSuministro() {
		return idInmuebleSuministro;
	}

	/**
	 * Método que establece el identificador del suministro del inmueble.
	 * @param idInmuebleSuministro identificador del suministro del inmueble, tipo Integer.
	 */
	public void setIdInmuebleSuministro(Integer idInmuebleSuministro) {
			this.idInmuebleSuministro = idInmuebleSuministro;
		}

}
