package ar.gob.cfp.cursos.modelo;

import java.util.Date;

public class Curso {

	private Integer id;
	private String nombre;
	private Integer horasCatedra;
	private Date fechaInicio;
	private String periodoAcademico;
	private Institucion institucion;
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
	 * @return the horasCatedra
	 */
	public Integer getHorasCatedra() {
		return horasCatedra;
	}
	/**
	 * @param horasCatedra the horasCatedra to set
	 */
	public void setHorasCatedra(Integer horasCatedra) {
		this.horasCatedra = horasCatedra;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the periodoAcademico
	 */
	public String getPeriodoAcademico() {
		return periodoAcademico;
	}
	/**
	 * @param periodoAcademico the periodoAcademico to set
	 */
	public void setPeriodoAcademico(String periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}
	/**
	 * @return the institucion
	 */
	public Institucion getInstitucion() {
		return institucion;
	}
	/**
	 * @param institucion the institucion to set
	 */
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	
	
	
}
