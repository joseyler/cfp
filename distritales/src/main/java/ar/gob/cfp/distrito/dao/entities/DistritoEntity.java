package ar.gob.cfp.distrito.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "distrito")
public class DistritoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 150, unique = true)
	private String nombre;

	@OneToMany(mappedBy = "distrito", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	private List<InstitucionEntity> instituciones;

	@Column(name = "id_provincia")
	private Integer idProvincia;
	
	@Column(name = "id_region")
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
	public List<InstitucionEntity> getInstituciones() {
		return instituciones;
	}

	/**
	 * @param instituciones the instituciones to set
	 */
	public void setInstituciones(List<InstitucionEntity> instituciones) {
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
