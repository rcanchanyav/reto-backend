/**
 * Resumen.
 * Objeto               :   CartaAviso.java.
 * Descripción          :   Clase utilizada para encapsular los campos de la carta de aviso.
 * Fecha de Creación    :   17/02/2021
 * PE de Creación       :  	INI-900
 * Autor                :   Igor A. Quispe Vasquez.
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase que almacena los datos de la programación
 */
@Getter
@Setter
public class CartaAviso implements Serializable {

    /**
     * Numero de serie autogenerado
     */
    @Serial
    private static final long serialVersionUID = -2932855568428105928L;

    /** campo check del registro para la impresión */
    private boolean checked;

    /** item del registro */
    private Integer item;

    /** Código de la carta */
    private Long codigoCarta;

    /** Correlativo de la carta circular */
    private Long correlativo;

    /** Correlativo de hoja Ruta */
    private Long correlativoHoja;

    /** Inmueble a donde se envia la carta  */
    private Inmueble inmueble;

    /** Nombre del inmueble a donde se envia la carta  */
    private String nombreInmueble;

    /** Nombre del bloque a donde se envia la carta  */
    private String nombreBloque;

    /** Nombre del predio a donde se envia la carta  */
    private String nombrePredio;

    /** Contrato del inmueble con el arrendatario  */
    private Contrato contrato;

    /** Arrendatario   */
    private Arrendatario arrendatario;

    /** Servicio de la carta de aviso */
    private Servicio servicio;

    /** Fecha */
    private Date fecha;

    /** Auditoria de la tabla */
    private Auditoria auditoria;


    @Override
    public String toString() {

        return "CartaAviso [checked=" + checked + ", item=" + item + ", codigoCarta=" + codigoCarta + ", correlativo="
                + correlativo + ", nombreInmueble=" + nombreInmueble + ", nombreBloque="
                + nombreBloque + ", nombrePredio=" + nombrePredio + ", contrato=" + contrato + ", arrendatario="
                + arrendatario + ", servicio=" + servicio + ", fecha=" + fecha + ", auditoria=" + auditoria + "]";


    }
}
