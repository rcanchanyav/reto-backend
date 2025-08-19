package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Resumen.
 * Objeto               :   ReporteGastoServicioPredio.java.
 * Descripción          :   DTO utilizado para encapsular los atributos del reporte de gastos por servicios de predios desocupados.
 * Fecha de Creación    :   11/06/2021
 * PE de Creación       :   INI-900
 * Autor                :   Mario Salinas
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
public class ReporteGastoServicioPredio implements Serializable{

    /**
     * Codigo autogenerado identificador de la clase
     */
    private static final long serialVersionUID = -7279463678608660441L;

    private String observacion;

    private List<GastoServicioPredio> listaPagoServicioPrediosDesocupados = new ArrayList<>();

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<GastoServicioPredio> getListaPagoServicioPrediosDesocupados() {
        return listaPagoServicioPrediosDesocupados;
    }

    public void setListaPagoServicioPrediosDesocupados(List<GastoServicioPredio> listaPagoServicioPrediosDesocupados) {
        this.listaPagoServicioPrediosDesocupados = listaPagoServicioPrediosDesocupados;
    }

}