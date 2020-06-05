package ar.gob.cfp.inscripciones.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class CursoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7760304720669130769L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "anio")
	private Integer anio;
	
	@Column(name = "id_profesor")
	private Integer idProfesor;

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
	 * @return the anio
	 */
	public Integer getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	/**
	 * @return the idProfesor
	 */
	public Integer getIdProfesor() {
		return idProfesor;
	}

	/**
	 * @param idProfesor the idProfesor to set
	 */
	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}

	
	

}
