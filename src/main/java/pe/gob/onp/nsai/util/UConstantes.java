package pe.gob.onp.nsai.util;

import java.util.Locale;

public class UConstantes {

    /** Constante de error de validación del token */
    public static final int ERROR_VALIDACION_TOKEN = 1;

    /** Constante de error de tiempo de expiración del token */
    public static final int ERROR_TIEMPO_EXPIRACION_TOKEN = 2;

    /** Constante de error de perfil no autorizado en el sistema */
    public static final int ERROR_PERFIL_NO_AUTORIZADO = 3;

    /** Constante de error de token no encontrado */
    public static final int ERROR_NO_ENCONTRO_TOKEN = 4;

    /** Constante para definir el método GET del servidor. */
    public static final String METODO_GET_SERVIDOR = "1"; //OBTENER

    /** Constante para definir el método POST del servidor. */
    public static final String METODO_POST_SERVIDOR = "2"; //AGREGAR

    /** Constante para definir el método PUT del servidor. */
    public static final String METODO_PUT_SERVIDOR = "3"; //ACTUALIZAR

    /** Constante para definir el método DELETE del servidor. */
    public static final String METODO_DELETE_SERVIDOR = "4"; //ELIMINAR

    /** Constante para definir el método GET. */
    public static final String METODO_GET = "GET";

    /** Constante para definir el método POST. */
    public static final String METODO_POST = "POST";

    /** Constante para definir el método PUT. */
    public static final String METODO_PUT = "PUT";

    /** Constante para definir el método DELETE. */
    public static final String METODO_DELETE = "DELETE";

    /** Tipo de archivo PDF **/
    public static final String TIPO_ARCHIVO_REPORTE_PDF = "PDF";

    /** Tipo de archivo EXCEL **/
    public static final String TIPO_ARCHIVO_REPORTE_EXCEL = "EXCEL";

    /** Tipo de archivo WORD **/
    public static final String TIPO_ARCHIVO_REPORTE_WORD = "WORD";

    /** Locale Reporte **/
    public static final Locale LOCALE_REPORTE = Locale.of("es", "PE");

    /** Tipo de objeto MANTENIMIENTO **/
    public static final String TIPO_OBJETO_MANTENIMIENTO = "OBJE_MANT";

    /** Tipo de objeto COMERCIAL **/
    public static final String TIPO_OBJETO_COMERCIAL = "OBJE_COME";

    /** Tipo PREDIO **/
    public static final String TIPO_PREDIO = "01";

    /** Tipo COCHERA **/
    public static final String TIPO_COCHERA = "02";

    /** Tipo DEPOSITO **/
    public static final String TIPO_DEPOSITO = "03";

    /**
     * Constructor de la clase
     */
    public class UNotificacion{

        /** Constante para identificar la tabla de contrato. */
        public static final String ENTIDAD_CONTRATO = "mae_cont";

        /** Constante para identificar la tabla de inmueble. */
        public static final String ENTIDAD_INMUEBLE = "mae_inmu";

        /** Cosntante para indentificar la tabla de adenda. */
        public static final String ENTIDAD_ADENDA = "det_aden_cont";

        /** Constante para generar la descripción de la notificación de contrato. */
        public static final String DESCRIPCION_NOTIFICACION_CONTRATO = "El contrato {id} se encuentra Pendiente de Firma.";

        /** Constante para generar la descripción de la notificación de inmueble. */
        public static final String DESCRIPCION_NOTIFICACION_INMUEBLE = "La baja del inmueble {id} se encuentra incompleta, agregar archivos faltantes.";

        /** Cosntante para generar la descripción de la notificación de la adenda.*/
        public static final String DESCRIPCION_NOTIFICACION_ADENDA = "La adenda {numeroAdenda} del contrato {idContrato} se encuentra Pendiente de Firma.";

        /** Constante para identificar el permiso del módulo MARGESI. */
        public static final String PERMISO_MODULO_MARGESI = "MARGESI";

        /** Constante para identificar el permiso del módulo ADMINISTRACION. */
        public static final String PERMISO_MODULO_ADMINISTRACION = "ADMINISTRACION";

        /** Constante para identificar el permiso del módulo COMERCIAL. */
        public static final String PERMISO_MODULO_COMERCIAL = "COMERCIAL";

        /** Constante para identificar el permiso del módulo MANTENIMIENTO. */
        public static final String PERMISO_MODULO_MANTENIMIENTO = "MANTENIMIENTO";

        /** Constante para identificar los días de vencimiento del contrato. */
        public static final int DIAS_VENCIMIENTO_CONTRATO = 30;

        /** Constante para identificar los días de vencimiento del inmueble. */
        public static final int DIAS_VENCIMIENTO_INMUEBLE = 30;

        /** Cosntante para identificar los días de vencimiento de la adenda. */
        public static final int DIAS_VENCIMIENTO_ADENDA = 30;
    }


}
