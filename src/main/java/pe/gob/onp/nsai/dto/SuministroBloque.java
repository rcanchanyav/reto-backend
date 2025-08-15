/**
 * Resumen.
 * Objeto               :   SuministroBloque.java.
 * Descripción          :   DTO utilizado para encapsular los campos del suministro del bloque.
 * Fecha de Creación    :   19/04/2018
 * PE de Creación       :   4181
 * Autor                :   Pedro Peña
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0059                    10/05/2018		Christian Wong Carrasco	 Se modifican los mensajes de validación
 * PE2018-0117					  30/07/2018		Pedro Peña   Se agregan atributos. 
 */
package pe.gob.onp.nsai.dto;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
public class SuministroBloque  implements Serializable {

	/**
	 * Número de serie de la clase.
	 */
	private static final long serialVersionUID = -7402618469056443475L;

	/** Id inmueble bloque */
	private int idBloqueSuministro;
	
	/** Empresa de Servicio Público. */
	private String empresaServicioPublico;
	
	/** Identificador del predio de inmueble. */
	private int idPredioInmueble;
	
	/** Código tipo del bloque */
	private String codigoTipoBloque;   
	
	/** Id inmueble bloque */
	private int idInmuebleBloque;
	
	/** Tipo de Servicio. */
	private String  tipoServicio;
	
	/** Identificador del inmueble */
	private String idInmueble;
	
	/** codigo para almacenar los datos del tipo de servicio. */
	private String codigoTipoServicio;
	
	/** codigo para almacenar los datos de la empresa de servicio público. */
	private String codigoEmpresaServicioPublico;
	
	/** Número de suministros del inmueble */
	@Size(max = 15,message="{suministroBloque.numeroSuministroBloque.size}")
	private String numeroSuministroBloque;
	
	/** Número de suministros del inmueble */
	private String numeroSuministroInmueble;
	
		/** Estado del registro. 1: Activo, 0: Inactivo. */
	private String indicadorEstadoRegistro;
	
	/** Descripción del Inmueble. */
	private String descripcionInmueble;
	
	/** Descripción del bloque. */
	private String descripcionBloque;
	
	/**
	 * Indicador de registro del suministro.
	 */
	private String indicadorOrigenRegistroSumi;
	
	/**
	 * Indicador de origen del registro del suministro.
	 */
	private String indicadorOrigenRegistro;
	
	/** Id del suministro de origen */
	private Integer idSuministroOrigen;
	
	/** Estado del suministro */
	private String estadoSuministro;
	
	/** Descripción del origen del suministro */
	private String descripcionOrigenSuministro;
	
	/** Código del estado del suministro  */
	private String codigoEstadoSuministroBloque;
	
	/** Observación del suministro */
	private String observacionSuministroBloque;
	
	/**
	 * Indicador del estado del prorrateo.
	 */
	private String indicadorEstadoProrratero;
	
	/** Objeto de auditoria. */
    private Auditoria auditoria;
	
	/**
	 * Método que obtiene el identificador del suministro del bloque.
	 * @return idBloqueSuministro identificador del suministro del bloque, tipo int.
	 */
	public int getIdBloqueSuministro() {
		return idBloqueSuministro;
	}

	/**
	 * Método que establece el identificador del suministro del bloque.
	 * @param dBloqueSuministro identificador del suministro del bloque, tipo int.
	 */
	public void setIdBloqueSuministro(int idBloqueSuministro) {
		this.idBloqueSuministro = idBloqueSuministro;
	}

	/**
	 * Método que obtiene el código del tipo del bloque.
	 * @return codigoTipoBloque código del tipo del bloque, tipo String.
	 */
	public String getCodigoTipoBloque() {
		return codigoTipoBloque;
	}

	/** Código del bloque */
	private String codigoBloque;
	
	/**
	 * Método que establece el código del tipo del bloque.
	 * @param codigoTipoBloque código del tipo del bloque, tipo String.
	 */
	public void setCodigoTipoBloque(String codigoTipoBloque) {
		this.codigoTipoBloque = codigoTipoBloque;
	}

	/**
	 * Método que obtiene el identificador de bloque del inmueble.
	 * @return idInmuebleBloque identificador de bloque del inmueble, tipo long.
	 */
	public int getIdInmuebleBloque() {
		return idInmuebleBloque;
	}

	/**
	 * Método que establece el identificador de bloque del inmueble.
	 * @param idInmuebleBloque identificador de bloque del inmueble, tipo int.
	 */
	public void setIdInmuebleBloque(int idInmuebleBloque) {
		this.idInmuebleBloque = idInmuebleBloque;
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
	 * Método que obtiene el número de suministros del bloque.
	 * @return numeroSuministroBloque Número de suministro del inmueble, tipo String.
	 */
	public String getNumeroSuministroBloque() {
		return numeroSuministroBloque;
	}

	/**
	 * Método que establece el número de suministros del bloque.
	 * @param numeroSuministroBloque Número de suministro del bloque, tipo String.
	 */
	public void setNumeroSuministroBloque(String numeroSuministroBloque) {
		this.numeroSuministroBloque = numeroSuministroBloque;
	}

	/**
	 * Método que obtiene el indicador de estado del registro.
	 * @return indicadorEstadoRegistro indicador de estado del registro, tipo String.
	 */
	public String getIndicadorEstadoRegistro() {
		return indicadorEstadoRegistro;
	}

	/**
	 * Método que establece el  indicador de estado del registro.
	 * @param indicadorEstadoRegistro indicador de estado del registro, tipo String.
	 */
	public void setIndicadorEstadoRegistro(String indicadorEstadoRegistro) {
		this.indicadorEstadoRegistro = indicadorEstadoRegistro;
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
	 * Método que permite obtener el código del tipo de servicio.
	 * @return codigoTipoServicio Código del tipo de servicio, tipo String.
	 */
	public String getCodigoTipoServicio() {
		return codigoTipoServicio;
	}

	/**
	 * Método que permite establecer el código del tipo de servicio.
	 * @param codigoTipoServicio Código del tipo de servicio, tipo String.
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
	 * @param codigoEmpresaServicioPublico código de la empresa de servicio público, tipo String.
	 */
	public void setCodigoEmpresaServicioPublico(String codigoEmpresaServicioPublico) {
		this.codigoEmpresaServicioPublico = codigoEmpresaServicioPublico;
	}
	/**
	 * Método que obtiene el número de suministros del inmueble.
	 * @return numSuministroInmueble número de suministro del inmueble, tipo String.
	 */
	public String getNumeroSuministroInmueble() {
		return numeroSuministroInmueble;
	}

	/**
	 * Método que establece el número de suministros del inmueble.
	 * @param numSuministroInmueble número de suministro del inmueble, tipo String.
	 */
	public void setNumeroSuministroInmueble(String numeroSuministroInmueble) {
		this.numeroSuministroInmueble = numeroSuministroInmueble;
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
	 * Método que obtiene el identificador del predio del inmueble.
	 * @return idPredioInmueble Identificador del predio del inmueble, tipo int.
	 */
	public int getIdPredioInmueble() {
		return idPredioInmueble;
	}

	/**
	 * Método que establece el identificador del predio del inmueble.
	 * @param idPredioInmueble Identificador del predio del inmueble, tipo int.
	 */
	public void setIdPredioInmueble(int idPredioInmueble) {
		this.idPredioInmueble = idPredioInmueble;
	}

	/**
	 * Método que permite establecer el código del bloque.
	 * @return codigoBloque  código del bloque, tipo String.
	 */
	public String getCodigoBloque() {
		return codigoBloque;
	}

	/**
	 * Método que permite establecer el código del bloque.
	 * @param codigoBloque  código del bloque, tipo String.
	 */
	public void setCodigoBloque(String codigoBloque) {
		this.codigoBloque = codigoBloque;
	}


	/**
	 * Método que permite establecer la descripción del bloque.
	 * @return descripcionBloque  descripción del bloque, tipo String.
	 */
	public String getDescripcionBloque() {
		return descripcionBloque;
	}

	/**
	 * Método que permite establecer la descripción del bloque.
	 * @param descripcionBloque  descripción del bloque, tipo String.
	 */
	public void setDescripcionBloque(String descripcionBloque) {
		this.descripcionBloque = descripcionBloque;
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
	 * Método que obtiene el indicador de origen del registro del suministro.
	 * @return indicadorOrigenRegistroSumi indicador de origen del registro del suministro, tipo String.
	 */
	public String getIndicadorOrigenRegistroSumi() {
		return indicadorOrigenRegistroSumi;
	}

	/**
	 * Método que establece el indicador de origen del registro del suministro.
	 * @param  indicadorOrigenRegistroSumi indicador de origen del registro del suministro, tipo String.
	 */
	public void setIndicadorOrigenRegistroSumi(String indicadorOrigenRegistroSumi) {
		this.indicadorOrigenRegistroSumi = indicadorOrigenRegistroSumi;
	}

	/**
	 * Método que obtiene el identificador de origen del suministro.
	 * @return idSuministroOrigen identificador de origen del suministro, tipo Integer.
	 */
	public Integer getIdSuministroOrigen() {
		return idSuministroOrigen;
	}

	/**
	 * Método que establece el identificador de origen del suministro.
	 * @param idSuministroOrigen identificador de origen del suministro, tipo Integer.
	 */
	public void setIdSuministroOrigen(Integer idSuministroOrigen) {
		this.idSuministroOrigen = idSuministroOrigen;
	}

	/**
	 * Método que obtiene el indicador de registro del suministro.
	 * @return indicadorOrigenRegistro indicador de registro del suministro , tipo String.
	 */
	public String getIndicadorOrigenRegistro() {
		return indicadorOrigenRegistro;
	}

	/**
	 * Método que establece el indicador de registro del suministro.
	 * @param indicadorOrigenRegistro indicador de registro del suministro , tipo String.
	 */
	public void setIndicadorOrigenRegistro(String indicadorOrigenRegistro) {
		this.indicadorOrigenRegistro = indicadorOrigenRegistro;
	}

	/**
	 * Método que obtiene el estado del suministro
	 * @return estadoSuministro estado del suministro, tipo String.
	 */
	public String getEstadoSuministro() {
		return estadoSuministro;
	}

	/**
	 * Método que establece el estado del suministro.
	 * @param estadoSuministro estado del suministro, tipo String.
	 */
	public void setEstadoSuministro(String estadoSuministro) {
		this.estadoSuministro =estadoSuministro;
	}

	/**
	 * Método que obtiene la descripción de origen del suministro
	 * @return descripcionOrigenSuministro descripción de origen del suministro, tipo String.
	 */
	public String getDescripcionOrigenSuministro() {
		return descripcionOrigenSuministro;
	}

	/**
	 * Método que establece la descripción de origen del suministro
	 * @param descripcionOrigenSuministro descripción de origen del suministro, tipo String.
	 */
	public void setDescripcionOrigenSuministro(String descripcionOrigenSuministro) {
		this.descripcionOrigenSuministro = descripcionOrigenSuministro;
	}

	/**
	 * Método que obtiene el código de estado del suministro.
	 * @return codigoEstadoSuministroBloque código de estado del suministro, tipo String.
	 */
	public String getCodigoEstadoSuministroBloque() {
		return codigoEstadoSuministroBloque;
	}

	/**
	 * Método que establece el código de estado del suministro.
	 * @param codigoEstadoSuministroBloque código de estado del suministro, tipo String.
	 */
	public void setCodigoEstadoSuministroBloque(String codigoEstadoSuministroBloque) {
		this.codigoEstadoSuministroBloque = codigoEstadoSuministroBloque;
	}

	/**
	 * Método que obtiene la observación del suministro.
	 * @return observacionSuministroBloque observación del suministro, tipo String.
	 */
	public String getObservacionSuministroBloque() {
		return observacionSuministroBloque;
	}

	/**
	 * Método que establece la observación del suministro.
	 * @param observacionSuministroBloque observación del suministro, tipo String.
	 */
	public void setObservacionSuministroBloque(String observacionSuministroBloque) {
		this.observacionSuministroBloque = observacionSuministroBloque;
	}

	/**
	 * Método que obtiene el indicador del estado de prorrateo.
	 * @return indicadorEstadoProrratero indicador del estado de prorrateo, tipo String.
	 */
	public String getIndicadorEstadoProrratero() {
		return indicadorEstadoProrratero;
	}

	/**
	 * Método que establece el indicador del estado de prorrateo.
	 * @param indicadorEstadoProrratero indicador del estado de prorrateo, tipo String.
	 */
	public void setIndicadorEstadoProrratero(String indicadorEstadoProrratero) {
		this.indicadorEstadoProrratero = indicadorEstadoProrratero;
	}
	
	
	
}
