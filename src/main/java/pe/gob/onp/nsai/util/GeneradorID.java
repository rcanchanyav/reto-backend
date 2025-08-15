package pe.gob.onp.nsai.util;

import org.hashids.Hashids;

/**
 * Clase utilitaria para la codificación y decodificación de identificadores usando Hashids.
 */
public final class GeneradorID {

    private static final String SALT = "generador";
    private static final Hashids HASHIDS = new Hashids(SALT);

    // Constructor privado para evitar instanciación
    private GeneradorID() {
        throw new UnsupportedOperationException("Clase utilitaria no instanciable");
    }

    /**
     * Codifica un identificador de tipo Long.
     *
     * @param id valor del identificador.
     * @return identificador codificado como String.
     */
    public static String codificarID(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return HASHIDS.encode(id);
    }

    /**
     * Codifica un identificador de tipo Integer.
     *
     * @param id valor del identificador.
     * @return identificador codificado como String.
     */
    public static String codificarID(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        return HASHIDS.encode(id.longValue());
    }

    /**
     * Decodifica un identificador codificado.
     *
     * @param valor identificador codificado como String.
     * @return identificador decodificado como Long.
     */
    public static long decodificar(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El valor no puede ser nulo o vacío");
        }
        long[] decoded = HASHIDS.decode(valor);
        if (decoded.length == 0) {
            throw new IllegalArgumentException("El valor no se pudo decodificar");
        }
        return decoded[0];
    }
}
