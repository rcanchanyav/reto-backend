/**
 * Resumen.
 * Objeto               :   ProrrateoPredioGeneral.java.
 * Descripción          :   Datos con los datos generales de los predios del inmueble para prorrateo.
 * Fecha de Creación    :   17/10/2018
 * PE de Creación       :   2018-0117
 * Autor                :   Christian Wong
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
 * Clase con los datos generales del predio del inmueble a prorratear
 *
 */
public class ProrrateoPredioGeneral implements Serializable{

    /**
     * Clave serial autogenerada
     */
    private static final long serialVersionUID = 9071731949607255072L;

    /** Identificador de prorrateo del predio del inmueble */
    private Long idProrrateoInmueble;

    /** Identificador del periodo de prorrateo del inmueble */
    private Long idPeriodoProrrateo;

    /** Identificador del inmueble */
    private String idInmueble;

    /** Descripción del inmueble */
    private String descripcionInmueble;

    /** Dias ocupados del inmueble */
    private Integer diasOcupadoInmueble;

    /** Identificador del bloque */
    private Integer idBloque;

    /** Descripción del bloque */
    private String descripcionBloque;

    /** Dias ocupados del bloque */
    private Integer diasOcupadoBloque;

    /** Identificador del predio */
    private Integer idPredio;

    /** Descripción del predio */
    private String descripcionPredio;

    /** Indicador de ocupabilidad del predio */
    private String indicadorOcupabilidadPredio;

    /** Area ocupada del predio */
    private BigDecimal areaOcupadaPredio;

    /** Identificador del contrato */
    private String idContrato;

    /** Codigo de la adenda */
    public String codigoAdenda;

    /** Codigo del arrendatario */
    private String codigoArrendatario;

    /** Fecha de inicio del contrato */
    private Date fechaInicioContrato;

    /** Fecha de fin del contrato */
    private Date fechaFinContrato;

    /** Dias ocupados del predio */
    private Integer diasOcupadoPredio;

    /** Dias desocupados del predio */
    private Integer diasDesocupadoPredio;

    /** Tipo mes*/
    private String mes;

    /** Tipo año*/
    private String anio;

    /** Descripción del servicio */
    private String descripcionServicio;

    /** Codito tipo Bloque */
    private String tipoBloque;

    /** Descripción del mes */
    private String descripcionMes;

    /** Codigo tipo Uso*/
    private String tipoUso;

    /** Codigo tipo Inmueble */
    private String tipoInmueble;

    /** Codigo tipo Estado */
    private String tipoEstado;

    /** Codigo tipo Servicio */
    private String tipoServicio;

    /** Codigo */
    private String codigo;

    /**
     * Método que obtiene el identificador de prorrateo del predio del inmueble.
     * @return idProrrateoInmueble identificador de prorrateo del predio del inmueble, tipo Long.
     */
    public Long getIdProrrateoInmueble() {
        return idProrrateoInmueble;
    }

    /**
     * Método que establece el identificador de prorrateo del predio del inmueble.
     * @param idProrrateoInmueble identificador de prorrateo del predio del inmueble, tipo Long.
     */
    public void setIdProrrateoInmueble(Long idProrrateoInmueble) {
        this.idProrrateoInmueble = idProrrateoInmueble;
    }

    /**
     * Método que obtiene el identificador del periodo de prorrateo del inmueble.
     * @return idPeriodoProrrateo identificador del periodo de prorrateo del inmueble, tipo Long.
     */
    public Long getIdPeriodoProrrateo() {
        return idPeriodoProrrateo;
    }

    /**
     * Método que establece el identificador del periodo de prorrateo del inmueble.
     * @param idPeriodoProrrateo identificador del periodo de prorrateo del inmueble, tipo Long.
     */
    public void setIdPeriodoProrrateo(Long idPeriodoProrrateo) {
        this.idPeriodoProrrateo = idPeriodoProrrateo;
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
     * Método que obtiene la descripción del inmueble.
     * @return descripcionInmueble descripción del inmueble, tipo String.
     */
    public String getDescripcionInmueble() {
        return descripcionInmueble;
    }

    /**
     * Método que establece la descripción del inmueble.
     * @param descripcionInmueble descripción del inmueble, tipo String.
     */
    public void setDescripcionInmueble(String descripcionInmueble) {
        this.descripcionInmueble = descripcionInmueble;
    }

    /**
     * Método que obtiene los dias ocupados del inmueble.
     * @return diasOcupadoInmueble dias ocupados del inmueble, tipo Integer.
     */
    public Integer getDiasOcupadoInmueble() {
        return diasOcupadoInmueble;
    }

    /**
     * Método que establece los dias ocupados del inmueble.
     * @param diasOcupadoInmueble dias ocupados del inmueble, tipo Integer.
     */
    public void setDiasOcupadoInmueble(Integer diasOcupadoInmueble) {
        this.diasOcupadoInmueble = diasOcupadoInmueble;
    }

    /**
     * Método que obtiene el identificador del bloque.
     * @return idBloque el identificador del bloque ,tipo Integer.
     */
    public Integer getIdBloque() {
        return idBloque;
    }

    /**
     * Método que establece el identificador del bloque.
     * @param idBloque el identificador del bloque ,tipo Integer.
     */
    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    /**
     * Método que obtiene la descripción del bloque.
     * @return descripcionBloque descripción del bloque,tipo String.
     */
    public String getDescripcionBloque() {
        return descripcionBloque;
    }

    /**
     * Método que establece la descripción del bloque.
     * @param descripcionBloque descripción del bloque,tipo String.
     */
    public void setDescripcionBloque(String descripcionBloque) {
        this.descripcionBloque = descripcionBloque;
    }

    /**
     * Método que obtiene los dias ocupados del bloque.
     * @return diasOcupadoBloque dias ocupados del bloque, tipo Integer.
     */
    public Integer getDiasOcupadoBloque() {
        return diasOcupadoBloque;
    }

    /**
     * Método que establece los dias ocupados del bloque.
     * @param diasOcupadoBloque dias ocupados del bloque, tipo Integer.
     */
    public void setDiasOcupadoBloque(Integer diasOcupadoBloque) {
        this.diasOcupadoBloque = diasOcupadoBloque;
    }

    /**
     * Método que obtiene el identificador del predio.
     * @return idPredio identificador del predio, tipo Integer.
     */
    public Integer getIdPredio() {
        return idPredio;
    }

    /**
     * Método que establece el identificador del predio.
     * @param idPredio identificador del predio, tipo Integer.
     */
    public void setIdPredio(Integer idPredio) {
        this.idPredio = idPredio;
    }

    /**
     * Método que obtiene la descripción del predio.
     * @return descripcionPredio descripción del predio, tipo String.
     */
    public String getDescripcionPredio() {
        return descripcionPredio;
    }

    /**
     * Método que establece la descripción del predio.
     * @param descripcionPredio descripción del predio, tipo String.
     */
    public void setDescripcionPredio(String descripcionPredio) {
        this.descripcionPredio = descripcionPredio;
    }

    /**
     * Método que obtiene el indicador de ocupabilidad del predio.
     * @return indicadorOcupabilidadPredio indicador de ocupabilidad del predio, tipo String.
     */
    public String getIndicadorOcupabilidadPredio() {
        return indicadorOcupabilidadPredio;
    }

    /**
     * Método que establece el indicador de ocupabilidad del predio..
     * @param indicadorOcupabilidadPredio indicador de ocupabilidad del predio, tipo String.
     */
    public void setIndicadorOcupabilidadPredio(String indicadorOcupabilidadPredio) {
        this.indicadorOcupabilidadPredio = indicadorOcupabilidadPredio;
    }

    /**
     * Método que obtiene el área ocupada del predio.
     * @return areaOcupadaPredio área ocupada del predio, tipo BigDecimal.
     */
    public BigDecimal getAreaOcupadaPredio() {
        return areaOcupadaPredio;
    }

    /**
     * Método que establece el área ocupada del predio.
     * @param areaOcupadaPredio área ocupada del predio, tipo BigDecimal.
     */
    public void setAreaOcupadaPredio(BigDecimal areaOcupadaPredio) {
        this.areaOcupadaPredio = areaOcupadaPredio;
    }

    /**
     * Método que obtiene el identificador del contrato.
     * @return idContrato identificador del contrato, tipo String.
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Método que establece el identificador del contrato.
     * @param idContrato identificador del contrato, tipo String.
     */
    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * Método que obtiene el código de la adenda.
     * @return codigoAdenda código de la adenda,tipo String.
     */
    public String getCodigoAdenda() {
        return codigoAdenda;
    }

    /**
     * Método que establece el código de la adenda.
     * @param codigoAdenda código de la adenda,tipo String.
     */
    public void setCodigoAdenda(String codigoAdenda) {
        this.codigoAdenda = codigoAdenda;
    }

    /**
     * Método que obtiene el código del arrendatario.
     * @return codigoArrendatario código del arrendatario, tipo String.
     */
    public String getCodigoArrendatario() {
        return codigoArrendatario;
    }

    /**
     * Método que establece el código del arrendatario.
     * @param codigoArrendatario código del arrendatario, tipo String.
     */
    public void setCodigoArrendatario(String codigoArrendatario) {
        this.codigoArrendatario = codigoArrendatario;
    }

    /**
     * Método que obtiene la fecha de incio del contrato.
     * @return fechaInicioContrato fecha de incio del contrato, tipo Date.
     */
    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    /**
     * Método que establece la fecha de incio del contrato.
     * @param fechaInicioContrato fecha de incio del contrato, tipo Date.
     */
    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    /**
     * Método que obtiene la fecha de fin del contrato.
     * @return fechaFinContrato fecha de fin del contrato, tipo Date.
     */
    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    /**
     * Método que establece la fecha de fin del contrato.
     * @param fechaFinContrato fecha de fin del contrato, tipo Date.
     */
    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    /**
     * Método que obtiene los dias ocupados del predio.
     * @return diasOcupadoPredio dias ocupados del predio,tipo Integer.
     */
    public Integer getDiasOcupadoPredio() {
        return diasOcupadoPredio;
    }

    /**
     * Método que establece los dias ocupados del predio.
     * @param diasOcupadoPredio dias ocupados del predio, tipo Integer.
     */
    public void setDiasOcupadoPredio(Integer diasOcupadoPredio) {
        this.diasOcupadoPredio = diasOcupadoPredio;
    }

    /**
     * Método que obtiene los dias desocupados del predio.
     * @return diasDesocupadoPredio dias desocupados del predio, tipo Integer.
     */
    public Integer getDiasDesocupadoPredio() {
        return diasDesocupadoPredio;
    }

    /**
     * Método que establece los dias desocupados del predio.
     * @param diasDesocupadoPredio dias desocupados del predio, tipo Integer.
     */
    public void setDiasDesocupadoPredio(Integer diasDesocupadoPredio) {
        this.diasDesocupadoPredio = diasDesocupadoPredio;
    }

    /**
     * Método que obtienes el mes prorrateo.
     * @param mes del prorrateo, tipo String.
     */
    public String getMes() {
        return mes;
    }

    /**
     * Método que obtienes el mes prorrateo.
     * @param mes del prorrateo, tipo String.
     */
    public void setMes(String mes) {
        this.mes = mes;
    }
    /**
     * Método que obtienes el anio prorrateo.
     * @param anio del prorrateo, tipo String.
     */
    public String getAnio() {
        return anio;
    }

    /**
     * Método que obtienes el Anio prorrateo.
     * @param Anio del prorrateo, tipo String.
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * Método que obtiene la descripción del servicio.
     * @return descripcionServicio descripción del servicio,tipo String.
     */
    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    /**
     * Método que establece la descripción del servicio.
     * @param descripcionServicio descripción del servicio,tipo String.
     */
    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    /**
     * Método que obtiene el tipoCodigo prorrateo.
     * @return tipoBloque código, tipo String.
     */
    public String getTipoBloque() {
        return tipoBloque;
    }

    /**
     * Método que establece el tipoCodigo del prorrateo.
     * @param tipoBloque código del arrendatario, tipo String.
     */
    public void setTipoBloque(String tipoBloque) {
        this.tipoBloque = tipoBloque;
    }

    /**
     * Método que obtiene la descripción del mes.
     * @return descripcionMes descripción del mes,tipo String.
     */
    public String getDescripcionMes() {
        return descripcionMes;
    }

    /**
     * Método que establece la descripción del mes.
     * @param descripcionMes descripción del mes,tipo String.
     */
    public void setDescripcionMes(String descripcionMes) {
        this.descripcionMes = descripcionMes;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public String getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }



}
