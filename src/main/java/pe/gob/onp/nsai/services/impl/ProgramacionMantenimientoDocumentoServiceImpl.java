/**
 * Resumen.
 * Objeto               :   ProgramacionMantenimientoDocumentoDelegateEJB.java.
 * Descripción          :   Clase utilizada para la creación de los métodos para los documentos de la
 * programacion de mantenimiento
 * Fecha de Creación    :   08/02/2021
 * PE de Creación       :   INI-900
 * Autor                :   Igor Alexander Quispe Vásquez
 * --------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                          Fecha             Nombre                  Descripción
 * --------------------------------------------------------------------------------------------------------------
 */
package pe.gob.onp.nsai.services.impl;

import java.util.List;
import java.util.Map;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;
import pe.gob.onp.nsai.dao.ProgramacionMantenimientoDocumentoLocalDao;
import pe.gob.onp.nsai.dto.Archivo;
import pe.gob.onp.nsai.dto.IdHash;
import pe.gob.onp.nsai.dto.ProgramacionMantenimientoDocumento;
import pe.gob.onp.nsai.infrastructure.client.ServicioSparmag;
import pe.gob.onp.nsai.services.ProgramacionMantenimientoDocumentoService;
import pe.gob.onp.nsai.util.DirectorioTaxonomia;
import pe.gob.onp.nsai.util.TaxonomiaDocumentos;
import pe.gob.onp.nsai.util.UServicios;


/**
 * Clase utilizada para la creación de los métodos para los documentos de la
 * programacion de mantenimientos.
 */
@Service
public class ProgramacionMantenimientoDocumentoServiceImpl implements ProgramacionMantenimientoDocumentoService {

    /**
     * Instancia a la clase EJB ESProgramacionMantenimientoDocumentoLocal.
     */

    private final ProgramacionMantenimientoDocumentoLocalDao ejbProgramacionDocumento;
    private final ServicioSparmag servicioSparmag;

    public ProgramacionMantenimientoDocumentoServiceImpl(ProgramacionMantenimientoDocumentoLocalDao ejbProgramacionDocumento, ServicioSparmag servicioSparmag) {
        this.ejbProgramacionDocumento = ejbProgramacionDocumento;
        this.servicioSparmag = servicioSparmag;
    }


    /**
     * Método que permite consultar los documentos de la programacion de
     * mantenimiento
     *
     * @param parametros
     *            mapa de parámetros para la búsqueda, tipo Map<String,Object>.
     * @return listaDocumentosProgramacionMantenimiento registros con los documentos
     *         de la programacion de mantenimiento, tipo
     *         List<ProgramacionMantenimientoDocumento>.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public List<ProgramacionMantenimientoDocumento> obtenerDocumentosProgramacionMantenimiento(
            Map<String, Object> parametros) throws Exception {
        List<ProgramacionMantenimientoDocumento> listaDocumentosProgramacionMantenimiento;
        try {
            listaDocumentosProgramacionMantenimiento = ejbProgramacionDocumento
                    .consultarDocumentosProgramacionMantenimiento(parametros);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return listaDocumentosProgramacionMantenimiento;
    }

    /**
     * Metodo que permite guardar los documentos de la programacion de
     * mantenimiento.
     *
     * @param programacionMantenimientoDocumento
     *            documentos de la programacion de mantenimiento, tipo
     *            ProgramacionMantenimientoDocumento.
     * @param archivo
     *            documento adjunto, tipo Archivo.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void guardarDocumentoProgramacionMantenimiento(
            ProgramacionMantenimientoDocumento programacionMantenimientoDocumento, Archivo archivo) throws Exception {
        try {

            Long codigoProgramacionMantenimientoDocumento = ejbProgramacionDocumento
                    .obtenerCorrelativoDocumentoProgramacionMantenimiento();
            programacionMantenimientoDocumento.getHashId().setIdPrincipal(IdHash.HASH_AUXILIAR);
            programacionMantenimientoDocumento.setCodigoProgramacionDocumento(codigoProgramacionMantenimientoDocumento);

            CarpetaFilenet[] carpetasFilenet = new CarpetaFilenet[2];

            DocumentoFilenet documentoFilenet = new DocumentoFilenet();
            AuditoriaFilenet auditoriaFilenet = new AuditoriaFilenet();
            ArchivoFileNet archivoFilenet = new ArchivoFileNet();
            PropertiesFilenet propiedades_0 = new PropertiesFilenet();
            PropertiesFilenet propiedades_1 = new PropertiesFilenet();
            CarpetaFilenet carpetasFilenet_0 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_1 = new CarpetaFilenet();
            CarpetaFilenet carpetasFilenet_2 = new CarpetaFilenet();
            PropertiesFilenet propiedades_1A = new PropertiesFilenet();

            {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO
                carpetasFilenet_0.setOrden("0");
                carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
                carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_PROGRAMACION_MANTENIMIENTO);
                carpetasFilenet_0.setCarpetaDocumental(null);
                carpetasFilenet_0.setCantidadPropiedades("0");
                carpetasFilenet_0.setProperties(null);
            }

            {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO/{ID}
                carpetasFilenet_1.setOrden("1");
                carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_PROGRAMACION_MANTENIMIENTO);
                carpetasFilenet_1
                        .setCarpetaHija(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                carpetasFilenet_1.setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_PROGRAMACION_MANTENIMIENTO);
                carpetasFilenet_1.setCantidadPropiedades("1");

                propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PROGRAMACION_MANTENIMIENTO);
                propiedades_1A.setValor(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                propiedades_1A.setProperties(null);

                carpetasFilenet_1.setProperties(propiedades_1A);
            }

            {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO/{ID}/DOCUMENTOS_GENERALES

                carpetasFilenet_2.setOrden("2");
                carpetasFilenet_2
                        .setCarpetaPadre(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                carpetasFilenet_2
                        .setCarpetaHija(DirectorioTaxonomia.CARPETA_DOCUMENTOS_PROGRAMACION_MANTENIMIENTO_GENERALES);
                carpetasFilenet_2.setCarpetaDocumental(null);
                carpetasFilenet_2.setCantidadPropiedades("0");
                carpetasFilenet_2.setProperties(null);

                carpetasFilenet_1.setCarpeta(carpetasFilenet_2);
                carpetasFilenet_0.setCarpeta(carpetasFilenet_1);

                auditoriaFilenet.setIdUsuario(programacionMantenimientoDocumento.getAuditoria().getUsuarioCreacion());
                auditoriaFilenet.setIpTerminal(programacionMantenimientoDocumento.getAuditoria().getTerminalCreacion());

            }

            archivoFilenet.setClaseDocumental(DirectorioTaxonomia.CLASE_DOCUMENTO_PROGRAMACION_MANTENIMIENTO);
            archivoFilenet.setCantidadPropiedades("1");

            propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PROGRAMACION_MANTENIMIENTO);
            propiedades_0.setValor(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
            propiedades_0.setProperties(null);

            archivoFilenet.setProperties(propiedades_0);
            archivoFilenet.setTitle(archivo.getNombre());
            archivoFilenet.setContentType(archivo.getTipoMime());

            archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

            documentoFilenet.setArchivo(archivoFilenet);
            documentoFilenet.setAuditoria(auditoriaFilenet);
            documentoFilenet.setCantidadCarpetas("3");
            documentoFilenet.setCarpeta(carpetasFilenet_0);

            ejbProgramacionDocumento.registrarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento,
                    documentoFilenet);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Metodo que permite modificar los documentos de la programacion de
     * mantenimiento.
     *
     * @param programacionMantenimientoDocumento
     *            documentos de la programacion de mantenimientos, tipo
     *            ProgramacionMantenimientoDocumento.
     * @param archivo
     *            documento adjunto, tipo Archivo
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override

    public void modificarDocumentoProgramacionMantenimiento(
            ProgramacionMantenimientoDocumento programacionMantenimientoDocumento, Archivo archivo) throws Exception {
        try {

            // CarpetaFilenet[] carpetasFilenet = new CarpetaFilenet[2];

            if (archivo.getNombre() != null) {
                DocumentoFilenet documentoFilenet = new DocumentoFilenet();
                AuditoriaFilenet auditoriaFilenet = new AuditoriaFilenet();
                ArchivoFileNet archivoFilenet = new ArchivoFileNet();
                PropertiesFilenet propiedades_0 = new PropertiesFilenet();
                PropertiesFilenet propiedades_1 = new PropertiesFilenet();
                CarpetaFilenet carpetasFilenet_0 = new CarpetaFilenet();
                CarpetaFilenet carpetasFilenet_1 = new CarpetaFilenet();
                CarpetaFilenet carpetasFilenet_2 = new CarpetaFilenet();
                PropertiesFilenet propiedades_1A = new PropertiesFilenet();

                {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO
                    carpetasFilenet_0.setOrden("0");
                    carpetasFilenet_0.setCarpetaPadre(DirectorioTaxonomia.CARPETA_NSAI);
                    carpetasFilenet_0.setCarpetaHija(DirectorioTaxonomia.CARPETA_PROGRAMACION_MANTENIMIENTO);
                    carpetasFilenet_0.setCarpetaDocumental(null);
                    carpetasFilenet_0.setCantidadPropiedades("0");
                    carpetasFilenet_0.setProperties(null);
                }

                {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO/{ID}
                    carpetasFilenet_1.setOrden("1");
                    carpetasFilenet_1.setCarpetaPadre(DirectorioTaxonomia.CARPETA_PROGRAMACION_MANTENIMIENTO);
                    carpetasFilenet_1
                            .setCarpetaHija(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                    carpetasFilenet_1
                            .setCarpetaDocumental(DirectorioTaxonomia.CLASE_CARPETA_PROGRAMACION_MANTENIMIENTO);
                    carpetasFilenet_1.setCantidadPropiedades("1");

                    propiedades_1A.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PROGRAMACION_MANTENIMIENTO);
                    propiedades_1A.setValor(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                    propiedades_1A.setProperties(null);

                    carpetasFilenet_1.setProperties(propiedades_1A);
                }

                {// Crear Carpeta /NSAI/PROGRAMACION_MANTENIMIENTO/{ID}/DOCUMENTOS_GENERALES

                    carpetasFilenet_2.setOrden("2");
                    carpetasFilenet_2.setCarpetaPadre(
                            String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                    carpetasFilenet_2.setCarpetaHija(
                            DirectorioTaxonomia.CARPETA_DOCUMENTOS_PROGRAMACION_MANTENIMIENTO_GENERALES);
                    carpetasFilenet_2.setCarpetaDocumental(null);
                    carpetasFilenet_2.setCantidadPropiedades("0");
                    carpetasFilenet_2.setProperties(null);

                    carpetasFilenet_1.setCarpeta(carpetasFilenet_2);
                    carpetasFilenet_0.setCarpeta(carpetasFilenet_1);

                    auditoriaFilenet
                            .setIdUsuario(programacionMantenimientoDocumento.getAuditoria().getUsuarioModificacion());
                    auditoriaFilenet
                            .setIpTerminal(programacionMantenimientoDocumento.getAuditoria().getTerminalModificacion());

                }
                archivoFilenet.setClaseDocumental(DirectorioTaxonomia.CLASE_DOCUMENTO_PROGRAMACION_MANTENIMIENTO);
                archivoFilenet.setCantidadPropiedades("1");

                propiedades_0.setCodigo(TaxonomiaDocumentos.PROP_CODIGO_PROGRAMACION_MANTENIMIENTO);
                propiedades_0.setValor(String.valueOf(programacionMantenimientoDocumento.getCodigoProgramacion()));
                propiedades_0.setProperties(null);

                archivoFilenet.setProperties(propiedades_0);
                archivoFilenet.setTitle(archivo.getNombre());
                archivoFilenet.setContentType(archivo.getTipoMime());

                archivoFilenet.setBinaryBase64(Base64.encode(archivo.getArchivo()));

                documentoFilenet.setArchivo(archivoFilenet);
                documentoFilenet.setAuditoria(auditoriaFilenet);
                documentoFilenet.setCantidadCarpetas("3");
                documentoFilenet.setCarpeta(carpetasFilenet_0);
                ejbProgramacionDocumento.modificarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento,
                        documentoFilenet);

            }

        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Metodo que permite eliminar los documentos de la programacion de
     * mantenimiento.
     *
     * @param programacionMantenimientoDocumento
     *            documentos de la programacion de mantenimiento, tipo
     *            ProgramacionMantenimientoDocumento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public void eliminarDocumentoProgramacionMantenimiento(
            ProgramacionMantenimientoDocumento programacionMantenimientoDocumento) throws Exception {
        try {
            if (programacionMantenimientoDocumento.getCodigoProgramacionDocumento() != 0) {
                ejbProgramacionDocumento.eliminarDocumentoProgramacionMantenimiento(programacionMantenimientoDocumento);
            }
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener el documento adjunto.
     *
     * @param programacionMantenimientoDocumento
     *            bean con los datos de búsqueda del documento, tipo
     *            ProgramacionMantenimientoDocumento.
     * @return objeto con los datos del documento, tipo Archivo.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public Archivo obtenerDocumentoAdjunto(ProgramacionMantenimientoDocumento programacionMantenimientoDocumento)
            throws Exception {
        try {
            // CLASE_DOCUMENTO_PROGRAMACION_MANTENIMIENTO
            String uuid = ejbProgramacionDocumento.consultarIdDocumentoAdjunto(programacionMantenimientoDocumento);
            if (uuid == null) {
                return null;
            } else {
                ResultadoBusquedaDocumento resultadoBusquedaDocumento = UServicios.obtenerDocumento(uuid,
                        TaxonomiaDocumentos.CLASE_DOCUMENTO_PROGRAMACION_MANTENIMIENTO);

                if (resultadoBusquedaDocumento != null) {
                    if (resultadoBusquedaDocumento.getResultado() != null) {
                        if (resultadoBusquedaDocumento.getResultado().getCodigo().equals("0000")) {
                            return new Archivo(resultadoBusquedaDocumento.getArchivo().getTitle(),
                                    Base64.decode(resultadoBusquedaDocumento.getArchivo().getBinaryBase64()));
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            // ESFilenet filenet =
            // (ESFilenet)localizadorServicio.obtenerServicioFilenet(ServicioRemotoFilenet.FILENET);
            // filenet.conectarFilenet();
            // ArchivoFileNet archivoFnet = filenet.obtenerArchivo(uuid);
            // return new Archivo(archivoFnet.getNombreArchivo(),archivoFnet.getArchivo());
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

    /**
     * Método que permite obtener la cantidad de documentos de la programacion de
     * mantenimiento.
     *
     * @param programacionMantenimientoDocumento
     *            bean con los datos de búsqueda del documento de la programacion de
     *            mantenimiento, tipo ProgramacionMantenimientoDocumento.
     * @return cantidad número de registros de los documentos de la programacion de
     *         mantenimientos, tipo int.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public int obtenerCantidadRegistrosBusquedaProgramacionMantenimiento(
            ProgramacionMantenimientoDocumento programacionMantenimientoDocumento) throws Exception {
        int cantidad;
        try {
            cantidad = ejbProgramacionDocumento.obtenerCantidadRegistrosBusquedaProgramacionMantenimientoDocumento(
                    programacionMantenimientoDocumento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return cantidad;
    }

    /**
     * Método que permite obtener los datos del documento de la programacion de
     * mantenimiento.
     *
     * @param codigoProgramacionMantenimiento
     *            identificador del documento, tipo long.
     * @return programacionMantenimientoDocumento bean con los datos del documento
     *         de la programacion de mantenimiento, tipo
     *         ProgramacionMantenimientoDocumento.
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override
    public ProgramacionMantenimientoDocumento obtenerDatosDocumentoProgramacionMantenimiento(
            Long codigoProgramacionMantenimiento) throws Exception {
        ProgramacionMantenimientoDocumento programacionMantenimientoDocumento;
        try {
            programacionMantenimientoDocumento = ejbProgramacionDocumento
                    .obtenerDatosDocumentoProgramacionMantenimiento(codigoProgramacionMantenimiento);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
        return programacionMantenimientoDocumento;
    }

    /**
     * Método que permite obtener los documentos para el reporte de ficha de la
     * programacion de mantenimiento.
     *
     * @param codigoProgramacion
     *            identificador de la programacion de mantenimiento, tipo int.
     * @return listaDocumentos lista de documentos de la programacion de
     *         mantenimiento, tipo List<ProgramacionMantenimientoDocumento>
     * @throws Exception
     *             excepción generada en caso de error.
     */
    @Override

    public List<ProgramacionMantenimientoDocumento> obtenerDocumentosFichaProgramacionMantenimientoDocumento(
            int codigoProgramacion) throws Exception {
        try {
            return ejbProgramacionDocumento
                    .obtenerListaDocumentosProgramacionMantenimiento(codigoProgramacion);
        } catch (Exception excepcion) {
            throw new Exception(excepcion);
        }
    }

}
