package ar.gob.cfp.commons.model;

import java.util.List;

public class Distrito {
	
	private Integer id;
	private String nombre;
	private List<Institucion> instituciones;
	private Integer idProvincia;
	private Integer idRegion;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the instituciones
	 */
	public List<Institucion> getInstituciones() {
		return instituciones;
	}
	/**
	 * @param instituciones the instituciones to set
	 */
	public void setInstituciones(List<Institucion> instituciones) {
		this.instituciones = instituciones;
	}
    /**
     * @return the idProvincia
     */
    public Integer getIdProvincia() {
        return idProvincia;
    }
    /**
     * @param idProvincia the idProvincia to set
     */
    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }
    /**
     * @return the idRegion
     */
    public Integer getIdRegion() {
        return idRegion;
    }
    /**
     * @param idRegion the idRegion to set
     */
    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
	
	

}
