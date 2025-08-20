package pe.gob.onp.nsai.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UJson {

    /**
     * Método que convierte un json a mapa.
     * @param cadenajson cadena json a ser formateada, tipo String.
     * @return mapa mapa generado al leer el valor del json, tipo Map<String,Object>.
     */
    public static Map<String,Object> convertirJsonAMapa(String cadenajson) throws Exception{
        Map<String,Object> mapa = null;
        try {
            InputStream flujoEntrada=new ByteArrayInputStream(cadenajson.getBytes());
            ObjectMapper objectMapper = new ObjectMapper();
            mapa = objectMapper.readValue(flujoEntrada, HashMap.class);
        } catch (IOException excepcion) {
            throw excepcion;
        }
        return mapa;
    }


    /**
     * Método que convierte un json a objecto.
     * @param cadenajson cadena json a ser formateada, tipo String.
     * @param tipo tipo de clase a ser formateado, tipo Class<T>.
     * @return retorno objeto generado al leer el valor del arreglo de bytes, tipo <T> T.
     */
    public static <T> T convertirJsonATipo(String cadenajson,Class<T> tipo){
        T retorno=null;
        try {
            InputStream flujoEntrada=new ByteArrayInputStream(cadenajson.getBytes());
            ObjectMapper objectMapper = new ObjectMapper();
            retorno=objectMapper.readValue(flujoEntrada, tipo);
        } catch (Exception excepcion) {
            throw excepcion;
        }
        return retorno;
    }

    public static <T> List<T> convertirListJsonAListaTipo(String cadenaJson, TypeReference<List<T>> typeReference){
        if (cadenaJson == null || cadenaJson.trim().isEmpty()) {
            return Collections.emptyList();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(cadenaJson, typeReference);
        } catch (Exception excepcion) {
            throw new Exception("Error al convertir JSON a lista: " + excepcion.getMessage(), excepcion);
        }
    }

    /**
     * Método que convierte un json a objeto.
     * @param flujoEntrada flujo json de entrada, tipo InputStream.
     * @param tipo tipo de clase a ser formateado, tipo Class<T>.
     * @return retorno objeto generado al leer el valor del arreglo de bytes, tipo <T> T
     */
    public static <T> T convertirJsonATipo(InputStream flujoEntrada,Class<T> tipo) throws Exception{
        T retorno=null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            retorno=objectMapper.readValue(flujoEntrada, tipo);
        } catch (Exception excepcion) {
            throw excepcion;
        }
        return retorno;
    }

    /**
     * Método que convierte un file json a objeto.
     * @param archivoJson archivo json de entrada, tipo File.
     * @param tipo tipo de clase a ser formateado, tipo Class<T>.
     * @return retorno objeto generado al leer el valor del arreglo de bytes, tipo <T> T
     */
    public static <T> T convertirFileJsonATipo(File archivoJson, Class<T> tipo) throws Exception{
        T retorno=null;
        try {
            byte[] archivoBytes= Files.readAllBytes(archivoJson.toPath());
            InputStream flujoEntrada=new ByteArrayInputStream(archivoBytes);
            ObjectMapper objectMapper = new ObjectMapper();
            retorno=objectMapper.readValue(flujoEntrada, tipo);
        } catch (Exception excepcion) {
            throw excepcion;
        }
        return retorno;
    }

}
