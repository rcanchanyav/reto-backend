package pe.gob.onp.nsai.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inmueble implements Serializable {

    @Serial
    private static final long serialVersionUID = 1245142052232885526L;

    public Inmueble(String idInmueble, String descripcionInmueble, String indicadorVigenciaInmueble,
                    String indicadorEstadoRegistro, String codigoMotivoBaja,
                    String descripcionDireccionInmueble, String codigoAreaGeografica) {
        this.idInmueble = idInmueble;
        this.descripcionInmueble = descripcionInmueble;
        this.indicadorVigenciaInmueble = indicadorVigenciaInmueble;
        this.indicadorEstadoRegistro = indicadorEstadoRegistro;
        this.codigoMotivoBaja = codigoMotivoBaja;
        this.descripcionDireccionInmueble = descripcionDireccionInmueble;
        this.codigoAreaGeografica = codigoAreaGeografica;
    }

    /** campo check permite seleccionar */
    private boolean checked;

    private Long item;

    /** Identificador del inmueble */
    private String idInmueble;

    /** Objeto de auditoria. */
    private Auditoria auditoria;

    /** Descripción del inmueble  */
    @NotNull(message="{inmueble.descripcionInmueble.notNull}")
    @Size(min = 1, max = 50,message="{inmueble.descripcionInmueble.size}")
    private String descripcionInmueble;

    /** Código del tipo de vía */
    @NotNull(message="{inmueble.codigoTipoVia.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoTipoVia.size}")
    private String codigoTipoVia;

    /** Nombre de la via */
    @NotNull(message="{inmueble.nombreVia.notNull}")
    @Size(min = 1, max = 100,message="{inmueble.nombreVia.size}")
    private String nombreVia;
    /** Código tipo Zona */
    @Size(max = 2,message="{inmueble.codigoTipoZona.size}")
    private String codigoTipoZona;

    /** Nombre Zona */
    @Size(max = 100,message="{inmueble.nombreZona.size}")
    private String nombreZona;

    /** Numero Manzana */
    @Size(max = 18,message="{inmueble.numeroManzanaDireccion.size}")
    private String numeroManzanaDireccion;

    /** Dirección del Inmueble  */
    @Size(max = 500,message="{inmueble.descripcionDireccionInmueble.size}")
    private String descripcionDireccionInmueble;

    /** Direccion de referencia */
    @Size(max = 500,message="{inmueble.descripcionDireccionReferencia.size}")
    private String descripcionDireccionReferencia;

    /** Provincia del Inmueble */
    @NotNull(message="{inmueble.provinciaInmueble.notNull}")
    @Size(min = 6, max = 6,message="{inmueble.provinciaInmueble.size}")
    private String provinciaInmueble;

    /** Departamento del Inmueble  */
    @NotNull(message="{inmueble.departamentoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{inmueble.departamentoInmueble.size}")
    private String departamentoInmueble;

    /** Distrito del Inmueble */
    @NotNull(message="{inmueble.distritoInmueble.notNull}")
    @Size(min = 6, max = 6,message="{inmueble.distritoInmueble.size}")
    private String distritoInmueble;

    /** Codigo de Ubigeo  */
    private String codigoAreaGeografica;

    /** Fecha de Recepción del Inmueble  */
    @NotNull(message="{inmueble.fechaRecepcionInmueble.notNull}")
    private Date fechaRecepcionInmueble;

    /** Fecha de Adqusición del Inmueble */
    @NotNull(message="{inmueble.fechaAdquisicionInmueble.notNull}")
    private Date fechaAdquisicionInmueble;

    /** Fecha de Estado de Conservacion */
    private Date fechaEstadoConservacion;

    /** año de Construcción  */
    @Digits(integer=4, fraction = 0 , message="{inmueble.anioConstrucionInmueble.digits}")
    private Integer anioConstrucionInmueble;

    /** Area del Terreno */
    @NotNull(message="{inmueble.numeroAreaTerreno.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.numeroAreaTerreno.digits}")
    private BigDecimal numeroAreaTerreno;

    /** Area techada */
    @NotNull(message="{inmueble.numeroAreaTechado.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.numeroAreaTechado.digits}")
    private BigDecimal numeroAreaTechado;

    /** Area Ocupada */
    @NotNull(message="{inmueble.numeroAreaOcupado.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.numeroAreaOcupado.digits}")
    private BigDecimal numeroAreaOcupado;

    /** Area Ocupada Comun  */
    private Double numeroAreaOcupadaComun;

    /** Porcentaje de area comun */
    @NotNull(message="{inmueble.numeroPorcentajeAreaComun.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.numeroPorcentajeAreaComun.digits}")
    private BigDecimal numeroPorcentajeAreaComun;

    /** Area Libre */
    @NotNull(message="{inmueble.numeroAreaLibre.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.numeroAreaLibre.digits}")
    private BigDecimal numeroAreaLibre;

    /** Sustento de adquisición del inmueble */
    @Size(max = 100,message="{inmueble.descripcionSustentoAdquisicionInmueble.size}")
    private String descripcionSustentoAdquisicionInmueble;

    /** Nombre del archivo de sustento */
    private String nombreArchivoSustentoAdquisicionInmueble;

    /** Código Imagen */
    private String codigoImagen;

    /** Valor de Adquisición del Inmueble */
    @NotNull(message="{inmueble.montoValorAdquisicionInmueble.notNull}")
    @Digits(integer=9, fraction=2, message="{inmueble.montoValorAdquisicionInmueble.digits}")
    private BigDecimal montoValorAdquisicionInmueble;

    /** Tipo de Cambio  */
    @NotNull(message="{inmueble.montoTipoCambio.notNull}")
    @Digits(integer=9, fraction=3, message="{inmueble.montoTipoCambio.digits}")
    private BigDecimal montoTipoCambio;

    /** Patida de Matriz */
    @NotNull(message="{inmueble.descripcionPartidaMatriz.notNull}")
    @Size(min=1, max = 30,message="{inmueble.descripcionPartidaMatriz.size}")
    private String descripcionPartidaMatriz;

    /** Observación */
    @Size(max = 500,message="{inmueble.textoObservacion.size}")
    private String textoObservacion;

    /** Número de puerta */
    private String descripcionNumeroPuerta;

    /** Número de Via */
    @Size(max=10, message="{inmueble.numeroDireccionVia.size}")
    private String numeroDireccionVia;

    /** Número de Interior */
    @Size(max=10, message="{inmueble.numeroInteriorDireccion.size}")
    private String numeroInteriorDireccion;

    /** Número de Lote  */
    @Size(max=10, message="inmueble.numeroLoteDireccion.size")
    private String numeroLoteDireccion;

    /** Código Estado de Saneamiento */
    @NotNull(message="{inmueble.codigoEstadoSaneamiento.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoEstadoSaneamiento.size}")
    private String codigoEstadoSaneamiento;

    private String estadoSaneamiento;

    /** Código de Titularidad */
    @NotNull(message="{inmueble.codigoTituloInmueble.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoTituloInmueble.size}")
    private String codigoTituloInmueble;

    /** Código de Tipo de Uso */
    @NotNull(message="{inmueble.codigoTipoUsoInmueble.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoTipoUsoInmueble.size}")
    private String codigoTipoUsoInmueble;

    /** Código Modalidad de Adquisición  */
    @NotNull(message="{inmueble.codigoModaAdquisicion.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoModaAdquisicion.size}")
    private String codigoModaAdquisicion;

    /** Código Moneda */
    @NotNull(message="inmueble.codigoMoneda.notNull")
    @Size(min = 2, max = 2,message="{inmueble.codigoMoneda.size}")
    private String codigoMoneda;

    /** Codigo Estado Conservación  */
    @NotNull(message="{inmueble.codigoEstadoConservacion.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoEstadoConservacion.size}")
    private String codigoEstadoConservacion;

    /** Area Techada Comun  */
    private Double numeroAreaTechoComun;

    /** Area Libre Comun */
    private BigDecimal numeroAreaComun;

    /** Codigo Motivo de Baja */
    private String codigoMotivoBaja;

    /** Fecha de Baja  */
    private Date  fechaBaja;

    /** Identificador del codigo de persona o supervisor. */
    private Integer idPersona;

    /** Indicador de Vigencia 1: Vigente, 0: No Vigente */
    private String indicadorVigenciaInmueble;

    /** Estado de Registro */
    private String indicadorEstadoRegistro;

    /**Codigo de tipo de inmueble */
    @NotNull(message="{inmueble.codigoTipoInmueble.notNull}")
    @Size(min = 2, max = 2,message="{inmueble.codigoTipoInmueble.size}")
    private String codigoTipoInmueble;

    /**Antiguedad del inmueble */
    private String antiguedad;

    /** Valor de adquisicion del inmueble calculado */
    private String montoValorAdquisicionInmuebleCalculado;


    /** Cantidad de predios asociados al inmueble */
    private int cantidadPrediosAsociados;

    /** Cantidad de bloques asociados al inmueble */
    private int cantidadBloquesAsociados;

    /** Fecha de venta del Inmueble */
    private Date fechaVenta;

    /** Valor de venta del Inmueble */
    private BigDecimal montoVenta;

    /** Observación de la baja del inmueble */
    private String observacionBaja;

    /** Sustento de la baja del inmueble */
    private String sustentoBaja;

    /** Datos del documento minuta */
    private String minuta;

    /** Datos del acta de entrega */
    private String actaEntrega;

    /** Datos de la baja municipal */
    private String bajaMunicipal;

    /** Datos de la inscripción registral */
    private String inscripcionRegistral;

    /** Datos del acta de devolución */
    private String actaDevolucion;

    /** Identificador de la minuta */
    private String idMinuta;

    /** Identificador del acta de entrega */
    private String idActaEntrega;

    /** Identificador de la  baja municipal */
    private String idBajaMunicipal;

    /** Identificador de la inscripción registral */
    private String idInscripcionRegistral;

    /** Identificador del acta de devolución */
    private String idActaDevolucion;

    /*****************GASTO**************/

    /** Identificador del inmueble */
    private String idInmuebleGasto;

    /** Mes del gasto de inversion */
    private String mesGasto;

    /** Anio del gasto de inversion */
    private String anioGasto;

    /** Fecha de venta del Inmueble */
    private Date fechaFactura;

    /** Valor de venta del Inmueble */
    private BigDecimal montoValor;

    /** Anio del gasto de inversion */
    private String codigoTipoGasto;


    /** Anio del gasto de inversion */
    private String codigoTipoMoneda;

    /** Anio del gasto de inversion */
    private String observacionGasto;

    /** Anio del gasto de inversion */
    private int numeroOrden;

    /** Anio del gasto de inversion */
    private String descripcionGasto;

    /** Anio del gasto de inversion */
    private int numeroFactura;

    /** Anio del gasto de inversion */
    private String descripcionProveedor;

    /** Indicador de Vigencia 1: Vigente, 0: No Vigente */
    private String indicadorVigencia;

    /** Ruc del proveedor  Por validar*/
    private String ruc;

    /** nombre del proveedor  Por validar*/
    private String proveedor;

    /** Tipo de modena*/
    private String tipoMoneda;

    private Double montoFactura;

    private String supervisor;

    private String codigoInmueble;

    private String idInmuServProv;

    private String idDetalleInmuebleGasto;

    private String descripcionTipoInmueble;

    /** Anio del gasto de inversion */
    private String descripcionTipoUso;

    /** Tipo mes*/
    private String mes;

    /** Tipo año*/
    private String anio;

    /** Identificador del Gastos Operativos */
    private Integer idGastoOperativos;

    private String bloque;

    private String ocupabilidadPredio;

    private String estadoInmueble;

    private String tipoBloque;

    private String predio;

    private String fechaBajaFormat;

    private String codigoBloque;

    private String codigoTipoBloque;

}
