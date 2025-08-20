/**
 * Resumen.
 * Objeto               :   PredioSuministro.java.
 * Descripción          :   DTO utilizado para encapsular los campos de los suministros del predio.
 * Fecha de Creación    :   18/04/2018
 * PE de Creación       :   4181
 * Autor                :   Pedro Aguilar
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0059					 10/05/2018			Christian Wong Carrasco	 Se modifican los mensajes de validación
 * PE2018-0117					 30/07/2018		    Pedro Peña   Se agregan los atributos indicadorOrigenRegistroSumi,idSuministroOrigen,estadoSuministro,descripcionOrigenSuministro,codigoEstadoSuministroPredio,observacionSuministroPredio e indicadorEstadoProrratero.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 *Clase que contiene los atributos de los suministros del predio.
 *
 */
public class PredioSuministro implements Serializable {

    /** Número de versión de la clase Serializable */
    private static final long serialVersionUID = -8379076444592644816L;

    /** identificador del Inmueble. */
    private String idInmueble;

    /** Identificador del suministro de predio. */
    private Integer idSuministroPredio;

    /** Identificador del suministro de bloque. */
    private Integer idSuministroBloque;

    /** Identificador del suministro de inmueble. */
    private Integer idSuministroInmueble;



    /** Identificador del predio de inmueble. */
    private Integer idPredioInmueble;

    /** Identificador del predio de inmueble. */
    private Integer idBloqueInmueble;

    /** Tipo de Servicio. */
    @NotNull(message="{predioSuministro.tipoServicio.notNull}")
    @Size(min = 2, max = 2,message="{predioSuministro.tipoServicio.size}")
    private String tipoServicio;

    /** Empresa de Servicio Público. */
    @NotNull(message="{predioSuministro.empresaServicioPublico.notNull}")
    @Size(min = 2, max = 2,message="{predioSuministro.empresaServicioPublico.size}")
    private String empresaServicioPublico;

    /** Número de suministros del inmueble */
    @NotNull(message="{predioSuministro.numeroSuministroPredio.notNull}")
    @Size(max = 15,message="{predioSuministro.numeroSuministroPredio.size}")
    private String numeroSuministroPredio;

    /** Estado del registro. 1: Activo, 0: Inactivo. */
    private String indicadorEstadoRegistro;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /** Descripción del Inmueble. */
    private String descripcionInmueble;

    /** Descripción del Bloque. */
    private String descripcionBloque;

    /** Descripción del Predio. */
    private String descripcionPredio;

    /** Identificador del Bloque. */
    private String idInmuebleBloque;

    /** Identificador del Predio. */
    private String idInmueblePredio;





    /**
     * Indicador de registro del suministro.
     */
    private String indicadorOrigenRegistroSumi;

    /** Id del suministro de origen */
    private Integer idSuministroOrigen;

    /** Estado del suministro */
    private String estadoSuministro;

    /** Descripción del suministro */
    private String descripcionOrigenSuministro;

    /** Código del estado de suministro  */
    private String codigoEstadoSuministroPredio;

    /** Observación del suministro  */
    private String observacionSuministroPredio;

    /** Indicador del estado de prorraterio. */
    private String indicadorEstadoProrratero;

    /**
     * Método que obtiene el identificador del Inmueble.
     * @return idInmueble Identificador del Inmueble, tipo String.
     */
    public String getIdInmueble() {
        return idInmueble;
    }

    /**
     * Método que estable el identificador del Inmueble.
     * @param idInmueble Identificador del Inmueble, tipo String.
     */
    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    /**
     * Método que obtiene el identificador del suministro de predio.
     * @return idSuministroPredio Identificador del suministro de predio, tipo Integer.
     */
    public Integer getIdSuministroPredio() {
        return idSuministroPredio;
    }

    /**
     * Método que estable el identificador del suministro de predio.
     * @param idSuministroPredio Identificador del suministro de predio, tipo Integer.
     */
    public void setIdSuministroPredio(Integer idSuministroPredio) {
        this.idSuministroPredio = idSuministroPredio;
    }

    /**
     * Método que obtiene el identificador del predio del inmueble.
     * @return idPredioInmueble Identificador del predio del inmueble, tipo Integer.
     */
    public Integer getIdPredioInmueble() {
        return idPredioInmueble;
    }

    /**
     * Método que establece el identificador del predio del inmueble.
     * @param idPredioInmueble Identificador del predio del inmueble, tipo Integer.
     */
    public void setIdPredioInmueble(Integer idPredioInmueble) {
        this.idPredioInmueble = idPredioInmueble;
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
     * Método que obtiene el número de suministros del inmueble.
     * @return numeroSuministroPredio Número de suministro del predio, tipo String.
     */
    public String getNumeroSuministroPredio() {
        return numeroSuministroPredio;
    }

    /**
     * Método que establece el número de suministros del inmueble.
     * @param numeroSuministroPredio Número de suministro del predio, tipo String.
     */
    public void setNumeroSuministroPredio(String numeroSuministroPredio) {
        this.numeroSuministroPredio = numeroSuministroPredio;
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
     * Método que obtiene el indicador de origen del registro del suministro.
     * @return indicadorOrigenRegistroSumi indicador de origen del registro del suministro, tipo String.
     */
    public String getIndicadorOrigenRegistroSumi() {
        return indicadorOrigenRegistroSumi;
    }

    /**
     *  Método que establece el indicador de origen del registro del suministro.
     * @param indicadorOrigenRegistroSumi indicador de origen del registro del suministro, tipo String.
     */
    public void setIndicadorOrigenRegistroSumi(String indicadorOrigenRegistroSumi) {
        this.indicadorOrigenRegistroSumi = indicadorOrigenRegistroSumi;
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
     * @return codigoEstadoSuministroPredio código de estado del suministro, tipo String.
     */
    public String getCodigoEstadoSuministroPredio() {
        return codigoEstadoSuministroPredio;
    }

    /**
     * Método que establece el código de estado del suministro.
     * @param codigoEstadoSuministroPredio código de estado del suministro, tipo String.
     */
    public void setCodigoEstadoSuministroPredio(String codigoEstadoSuministroPredio) {
        this.codigoEstadoSuministroPredio = codigoEstadoSuministroPredio;
    }

    /**
     * Método que obtiene la observación del suministro.
     * @return observacionSuministro observación del suministro, tipo String.
     */
    public String getObservacionSuministroPredio() {
        return observacionSuministroPredio;
    }

    /**
     * Método que establece la observación del suministro.
     * @param observacionSuministroPredio observación del suministro, tipo String.
     */
    public void setObservacionSuministroPredio(String observacionSuministroPredio) {
        this.observacionSuministroPredio = observacionSuministroPredio;
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
     * @return the idInmueblePredio
     */
    public String getIdInmueblePredio() {
        return idInmueblePredio;
    }

    /**
     * @param idInmueblePredio the idInmueblePredio to set
     */
    public void setIdInmueblePredio(String idInmueblePredio) {
        this.idInmueblePredio = idInmueblePredio;
    }

    /**
     * @return the idSuministroBloque
     */
    public Integer getIdSuministroBloque() {
        return idSuministroBloque;
    }

    /**
     * @param idSuministroBloque the idSuministroBloque to set
     */
    public void setIdSuministroBloque(Integer idSuministroBloque) {
        this.idSuministroBloque = idSuministroBloque;
    }

    /**
     * @return the idBloqueInmueble
     */
    public Integer getIdBloqueInmueble() {
        return idBloqueInmueble;
    }

    /**
     * @param idBloqueInmueble the idBloqueInmueble to set
     */
    public void setIdBloqueInmueble(Integer idBloqueInmueble) {
        this.idBloqueInmueble = idBloqueInmueble;
    }

    /**
     * @return the idSuministroInmueble
     */
    public Integer getIdSuministroInmueble() {
        return idSuministroInmueble;
    }

    /**
     * @param idSuministroInmueble the idSuministroInmueble to set
     */
    public void setIdSuministroInmueble(Integer idSuministroInmueble) {
        this.idSuministroInmueble = idSuministroInmueble;
    }

}
