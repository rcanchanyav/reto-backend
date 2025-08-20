/**
 * Resumen.
 * Objeto               :   PlantillaMantenimiento.java.
 * Descripción          :   DTO utilizado para encapsular los campos de la plantilla del modulo de mantenimiento(documento o correo).
 * Fecha de Creación    :   30/04/2021
 * PE de Creación       :   2021-INI900.
 * Autor                :   Jherson Huayhua
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 *
 */
package pe.gob.onp.nsai.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import pe.gob.onp.nsai.util.GeneradorID;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 *Clase que contiene los campos de la plantilla del documento.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PlantillaMantenimiento implements Serializable{

    /** Número de versión de la clase Serializable. */
    @Serial
    private static final long serialVersionUID = 6350940002858293844L;

    /** Objeto instanciado para generar el id cifrado. */
    private IdHash hashId=new IdHash();

    /** Identificador de la plantilla del documento */
    @Setter(AccessLevel.NONE)
    private Integer idPlantillaMantenimiento;

    /** Identificador del detalle de plantilla de documento */
    private Integer idDetallePlantillaMante;

    /** Código del tipo de plantilla del documento */
    private String codigoTipoPlantillaMante;

    /** Plantilla del documento */
    private String plantilla;

    /** Tipo de plantilla del documento */
    private String tipoPlantilla;

    private String coTipoSeccion;

    /** Tipo sección del documento */
    private String tipoSeccion;

    /** Tipo de clausula del documento */
    private String tipoClausula;

    /** Sección del documento */
    private String nombreSeccion;

    /** Orden de sección del documento */
    private String ordenSeccion;

    /** Nombre de la firma */
    private String nombreFirma;

    /** Cargo de la firma */
    private String cargoFirma;

    /** Parrafo de la sección del documento */
    private String parrafoSeccion;

    /** Indicador de vigencia del documento */
    private String indicadorVigencia;

    /** Estado de Registro */
    private String indicadorEstadoRegistro;

    /** Descripcion del número de la claúsula  */
    private String descOrdenSeccion;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    private String usuarioCreacion;

    private Date fechaCreacion;


    /**
     * Método que permite establecer el identificador de la plantilla del documento.
     * @param idPlantillaMantenimiento identificador de la plantilla del documento, tipo Integer.
     */
    public void setIdPlantillaMantenimiento(Integer idPlantillaMantenimiento) {

        if(this.hashId.getIdSecundario() == null){
            this.hashId.setIdSecundario(GeneradorID.codificarID(idPlantillaMantenimiento));
            this.idPlantillaMantenimiento=null;
        }else{
            this.idPlantillaMantenimiento = idPlantillaMantenimiento;
        }
    }




}
