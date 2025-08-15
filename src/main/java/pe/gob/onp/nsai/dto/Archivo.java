package pe.gob.onp.nsai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Archivo {

    /** Nombre del Archivo */
    private String nombre;

    /** Contenido en bytes del documento */
    private byte[] archivo;

    /** Tipo mime */
    private String tipoMime;

    /**
     * Constructor de la clase.
     */
    public Archivo() {
    }

    /**
     * Método que establece el nombre y el archivo en bytes.
     * @param nombre nombre del archivo, tipo String.
     * @param archivo archivo  con los datos, tipo byte[].
     */
    public Archivo(String nombre, byte[] archivo) {
        this.nombre = nombre;
        this.archivo = archivo;
    }

}
