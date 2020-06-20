package ar.gob.cfp.inscripciones.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inscripcion")
public class InscripcionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7760304720669130769L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "id_curso")
	private Integer idCurso;
	
	@Column(name = "id_inscripto")
	private Integer idInscripto;
	
	@Column(name = "fecha_inscripcion")
	private Date fechaInscripcion;

	@ManyToOne(fetch = FetchType.LAZY)
	private InscripcionEntity inscripcion;
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
     * @return the idCurso
     */
    public Integer getIdCurso() {
        return idCurso;
    }

    /**
     * @param idCurso the idCurso to set
     */
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    /**
     * @return the idInscripto
     */
    public Integer getIdInscripto() {
        return idInscripto;
    }

    /**
     * @param idInscripto the idInscripto to set
     */
    public void setIdInscripto(Integer idInscripto) {
        this.idInscripto = idInscripto;
    } 
    

    /**
     * @return the fechaInscripcion
     */
    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * @param fechaInscripcion the fechaInscripcion to set
     */
    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
	

	
	

}
