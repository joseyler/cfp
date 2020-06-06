package ar.gob.cfp.commons.model;

import java.util.List;

public class Distrito {
	
	private Integer id;
	private String nombre;
	private List<Institucion> instituciones;
	
	
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
	
	

}
