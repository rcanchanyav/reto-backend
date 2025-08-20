/**
 * Resumen.
 * Objeto               :   Servicio.java.
 * Descripción          :   Clase utilizada para encapsular los campos del Servicio.
 * Fecha de Creación    :   04/05/2018
 * PE de Creación       :   2018-0059
 * Autor                :   Christian Wong Carrasco.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 * PE2018-0060					   06/06/2018		Joseph Mena		 GeneradorID se refactoriza a una libreria general.
 * PE2018-0117					   05/10/2018		James Torress	 Se agregan atributos.
 */
package pe.gob.onp.nsai.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import pe.gob.onp.nsai.util.GeneradorID;

/**
 * Clase que contiene los datos del servicio.
 */
public class Servicio implements Serializable {

    /**
     * Número de serie generado
     */
    private static final long serialVersionUID = -7577176778605839017L;

    /** Datos de ids generados **/
    private IdHash hashId=new IdHash();

    /** Código del servicio */
    private String codigoServicio;

    /** Descripción del servicio */
    @NotNull(message="Nombre del inmueble no puede ser nulo")
    @Size(min = 1, max = 100,message="La descripción del servicio debe ser mayor a 1 y menor de 100")
    private String descripcionServicio;

    /** Indicador de servicio para prorratear */
    private String indicadorServicioParaProrratear;

    /** Indicador de servicio para seguimiento */
    private String indicadorServicioSeguimiento;

    /** Indicador de servicio de luz */
    private String indicadorServicioLuz;

    /** Indicador de servicio de agua  */
    private String indicadorServicioAgua;

    /** Indicador de otros servicios */
    private String indicadorOtroServicio;

    /** Indicador del tipo de servicio */
    private String indicadorTipoServicio;

    /** Datos de auditoria */
    private Auditoria auditoria;

    private Boolean checked;

    private String indicadorSuministro;

    private String indicadorArbitrio;

    private String indicadorMontoFijo;

    private Double factorPagoServicio;

    private String tipoProrrateo;

    private String item;

    /**
     * Método que permite obtener el código del servicio.
     * @return codigoServicio código del servicio, tipo String.
     */
    public String getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Método que permite establecer el código del servicio.
     * @param codigoServicio código del servicio, tipo String.
     */

	/*
	public void setCodigoServicio(String codigoServicio) {
		if(this.hashId.getIdPrincipal() == null && codigoServicio!=null && codigoServicio !=""){
			this.hashId.setIdPrincipal(GeneradorID.codificarID(Long.parseLong(codigoServicio)));
			this.codigoServicio = null;
		}else{
			this.codigoServicio = codigoServicio;
		}
	}
	*/

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }


    /**
     * Método que permite obtener la descripción del servicio.
     * @return descripcionServicio descripción del servicio, tipo String.
     */
    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    /**
     * Método que permite establecer la descripción del servicio.
     * @param descripcionServicio descripción del servicio, tipo String.
     */
    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    /**
     * Método que permite obtener el indicador de servicio para prorratear
     * @return indicadorServicioParaProrratear indicador de servicio para prorratear, tipo String.
     */
    public String getIndicadorServicioParaProrratear() {
        return indicadorServicioParaProrratear;
    }

    /**
     * Método que permite estalbecer el indicador de servicio para prorratear
     * @param indicadorServicioParaProrratear indicador de servicio para prorratear, tipo String.
     */
    public void setIndicadorServicioParaProrratear(String indicadorServicioParaProrratear) {
        this.indicadorServicioParaProrratear = indicadorServicioParaProrratear;
    }

    /**
     * Método que permite obtener los datos de auditoria
     * @return auditoria datos de auditoria, tipo Auditoria.
     */
    public Auditoria getAuditoria() {
        return auditoria;
    }

    /**
     * Método que permite establecer los datos de auditoria
     * @param auditoria datos de auditoria, tipo Auditoria.
     */
    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Método que permite obtener los datos de id
     * @return hashId datos de id, tipo IdHash.
     */
    public IdHash getHashId() {
        return hashId;
    }

    /**
     * Método que permite establecer los datos de id
     * @param hashId datos de id, tipo IdHash.
     */
    public void setHashId(IdHash hashId) {
        this.hashId = hashId;
    }

    /**
     * Método que permite obtener el indicador de servicio de luz.
     * @return indicadorServicioLuz indicador de servicio de luz, tipo String.
     */
    public String getIndicadorServicioLuz() {
        return indicadorServicioLuz;
    }

    /**
     * Método que permite establecer el indicador de servicio de luz.
     * @param indicadorServicioLuz indicador de servicio de luz, tipo String.
     */
    public void setIndicadorServicioLuz(String indicadorServicioLuz) {
        this.indicadorServicioLuz = indicadorServicioLuz;
    }

    /**
     * Método que permite obtener el indicador de servicio de agua.
     * @return indicadorServicioAgua indicador de servicio de agua, tipo String.
     */
    public String getIndicadorServicioAgua() {
        return indicadorServicioAgua;
    }

    /**
     * Método que permite establecer el indicador de servicio de agua.
     * @param indicadorServicioAgua indicador de servicio de agua, tipo String.
     */
    public void setIndicadorServicioAgua(String indicadorServicioAgua) {
        this.indicadorServicioAgua = indicadorServicioAgua;
    }

    /**
     * Método que permite obtener el indicador de otros servicios.
     * @return indicadorOtroServicio indicador de otros servicios, tipo String.
     */
    public String getIndicadorOtroServicio() {
        return indicadorOtroServicio;
    }

    /**
     * Método que permite establecer el indicador de otros servicios.
     * @param indicadorOtroServicio indicador de otros servicios, tipo String.
     */
    public void setIndicadorOtroServicio(String indicadorOtroServicio) {
        this.indicadorOtroServicio = indicadorOtroServicio;
    }

    /**
     * Método que permite obtener el indicador del servicio seleccionado.
     * @return indicadorTipoServicio indicador del servicio seleccionado, tipo String.
     */
    public String getIndicadorTipoServicio() {
        return indicadorTipoServicio;
    }

    /**
     * Método que permite establecer el indicador del servicio seleccionado.
     * @param indicadorTipoServicio indicador del servicio seleccionado, tipo String.
     */
    public void setIndicadorTipoServicio(String indicadorTipoServicio) {
        this.indicadorTipoServicio = indicadorTipoServicio;
    }

    /**
     * @return the checked
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the indicadorServicioSeguimiento
     */
    public String getIndicadorServicioSeguimiento() {
        return indicadorServicioSeguimiento;
    }

    /**
     * @param indicadorServicioSeguimiento the indicadorServicioSeguimiento to set
     */
    public void setIndicadorServicioSeguimiento(String indicadorServicioSeguimiento) {
        this.indicadorServicioSeguimiento = indicadorServicioSeguimiento;
    }

    /**
     * @return the indicadorSuministro
     */
    public String getIndicadorSuministro() {
        return indicadorSuministro;
    }

    /**
     * @param indicadorSuministro the indicadorSuministro to set
     */
    public void setIndicadorSuministro(String indicadorSuministro) {
        this.indicadorSuministro = indicadorSuministro;
    }

    /**
     * @return the indicadorArbitrio
     */
    public String getIndicadorArbitrio() {
        return indicadorArbitrio;
    }

    /**
     * @param indicadorArbitrio the indicadorArbitrio to set
     */
    public void setIndicadorArbitrio(String indicadorArbitrio) {
        this.indicadorArbitrio = indicadorArbitrio;
    }

    /**
     * @return the indicadorMontoFijo
     */
    public String getIndicadorMontoFijo() {
        return indicadorMontoFijo;
    }

    /**
     * @param indicadorMontoFijo the indicadorMontoFijo to set
     */
    public void setIndicadorMontoFijo(String indicadorMontoFijo) {
        this.indicadorMontoFijo = indicadorMontoFijo;
    }

    /**
     * @return the factorPagoServicio
     */
    public Double getFactorPagoServicio() {
        return factorPagoServicio;
    }

    /**
     * @param factorPagoServicio the factorPagoServicio to set
     */
    public void setFactorPagoServicio(Double factorPagoServicio) {
        this.factorPagoServicio = factorPagoServicio;
    }

    /**
     * @return the tipoProrrateo
     */
    public String getTipoProrrateo() {
        return tipoProrrateo;
    }

    /**
     * @param tipoProrrateo the tipoProrrateo to set
     */
    public void setTipoProrrateo(String tipoProrrateo) {
        this.tipoProrrateo = tipoProrrateo;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }


}
