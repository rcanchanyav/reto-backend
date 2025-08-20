package pe.gob.onp.nsai.dto;

import java.io.Serializable;
import java.util.List;

public class ResultadoBusquedaInspeccionMantenimiento implements Serializable {

    /**
     * Número de serie generado
     */
    private static final long serialVersionUID = -8065052212627980347L;

    /** Lista de los inspeccion inmueble */
    private List<InspeccionMantenimiento> listInspeccionMantenimiento;

    /** Lista de los inspeccion inmueble */
    private List<TipoResponsable> listarContacto;

    /** Lista de los arrendamiento */
    private List<Arrendatario> listarArrendamiento;

    /** Lista de los inspeccion inmueble */
    private List<InspeccionMantenimiento> listarAmbiente;

    /** Objeto para la almacenar la paginación */
    private Paginacion paginacion;


    /**
     * Método que permite obtener la lista de los inspección mantenimiento.
     * @return listInspeccionMantenimiento lista de los inspección mantenimiento, tipo List<InspeccionMantenimiento>.
     */
    public List<InspeccionMantenimiento> getListInspeccionMantenimiento() {
        return listInspeccionMantenimiento;
    }

    /**
     * Método que permite establecer  la lista de los inspección mantenimiento.
     * @param listInspeccionMantenimiento lista de los inspección mantenimiento, tipo List<InspeccionMantenimiento>.
     */
    public void setListInspeccionMantenimiento(List<InspeccionMantenimiento> listInspeccionMantenimiento) {
        this.listInspeccionMantenimiento = listInspeccionMantenimiento;
    }

    /**
     * Método que permite obtener la paginación actual.
     * @return paginacion paginación actual, tipo Paginacion.
     */
    public Paginacion getPaginacion() {
        return paginacion;
    }

    /**
     * Método que permite establecer la paginación actual.
     * @param paginacion paginación actual, tipo Paginacion.
     */
    public void setPaginacion(Paginacion paginacion) {
        this.paginacion = paginacion;
    }

    public List<TipoResponsable> getListarContacto() {
        return listarContacto;
    }

    public void setListarContacto(List<TipoResponsable> listarContacto) {
        this.listarContacto = listarContacto;
    }

    public List<Arrendatario> getListarArrendamiento() {
        return listarArrendamiento;
    }

    public void setListarArrendamiento(List<Arrendatario> listarArrendamiento) {
        this.listarArrendamiento = listarArrendamiento;
    }

    public List<InspeccionMantenimiento> getListarAmbiente() {
        return listarAmbiente;
    }

    public void setListarAmbiente(List<InspeccionMantenimiento> listarAmbiente) {
        this.listarAmbiente = listarAmbiente;
    }



}