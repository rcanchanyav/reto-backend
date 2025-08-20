package pe.gob.onp.nsai.dto;

import java.io.Serializable;

/**
 * Clase utilizado para encapsular los datos de los parametros del sistema
 */
public class CategoriaParametro implements Serializable{

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = 2736166562899771081L;

    /** Valor del parametro **/
    private String valor;
    /** Descripcion del parametro **/
    private String descripcion;
    /** Codigo indicador de tipo de parametro **/
    private String codigoPadre;

    /** Codigo de tipo de administrador **/
    private String coTipoAdministrador;

    /**valor 1 detalle */
    private String valor1;
    /**valor 2 detalle */
    private String valor2;
    /**valor 3 detalle */
    private String valor3;
    /**valor 4 detalle */
    private String valor4;
    /**
     * Permite obtener el valor del parametro
     * @return valor Valor del parametro, tipo String.
     */
    public String getValor() {
        return valor;
    }
    /**
     * Permite actualizar el valor del parametro
     * @param valor Valor a almacenar, tipo String.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    /**
     * Permite obtener la descripcion del parametro
     * @return descripcion Descripcion del parametro, tipo String.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Permite actualizar la descripcion del parametro
     * @param descripcion Descripcion a almacenar, tipo String.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Permite obtener el codigo del tipo de parametro
     * @return codigoPadre Codigo del tipo de parametro, tipo String.
     */
    public String getCodigoPadre() {
        return codigoPadre;
    }
    /**
     * Permite actualizar el código del tipo del parametro
     * @param codigoPadre Codigo del parametro a almacenar, tipo String.
     */
    public void setCodigoPadre(String codigoPadre) {
        this.codigoPadre = codigoPadre;
    }
    public String getCoTipoAdministrador() {
        return coTipoAdministrador;
    }
    public void setCoTipoAdministrador(String coTipoAdministrador) {
        this.coTipoAdministrador = coTipoAdministrador;
    }
    /**
     * @return the valor1
     */
    public String getValor1() {
        return valor1;
    }
    /**
     * @param valor1 the valor1 to set
     */
    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }
    /**
     * @return the valor2
     */
    public String getValor2() {
        return valor2;
    }
    /**
     * @param valor2 the valor2 to set
     */
    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }
    /**
     * @return the valor3
     */
    public String getValor3() {
        return valor3;
    }
    /**
     * @param valor3 the valor3 to set
     */
    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }
    /**
     * @return the valor4
     */
    public String getValor4() {
        return valor4;
    }
    /**
     * @param valor4 the valor4 to set
     */
    public void setValor4(String valor4) {
        this.valor4 = valor4;
    }




}
