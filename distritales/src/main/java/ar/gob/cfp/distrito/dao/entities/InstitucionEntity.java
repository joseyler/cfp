package ar.gob.cfp.distrito.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="institucion")
public class InstitucionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -825164653535904036L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre", length = 150,unique = true,nullable = false)
	private String nombre;
	
	@Column(name = "numero", length = 20 ,unique = true)
	private String numero;
	
	@Column(name = "direccion", length = 150)
	private String direccion;
	
	@Column(name = "localidad", length = 120)
	private String localidad;
	
	@Column(name = "distrito_id", insertable = false,updatable = false)
	private Integer idDistrito;
	
	//FetchType.EAGER  --> Se carga automaticamente cuando traigo la entidad
	//FetchType.LAZY --> Se carga si lo necesito (si accedo al getter)
	@ManyToOne(fetch = FetchType.LAZY)
	private DistritoEntity distrito;

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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return the distrito
	 */
	public DistritoEntity getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(DistritoEntity distrito) {
		this.distrito = distrito;
	}

	

}
