package ar.gob.cfp.commons.model;

import java.util.Date;

public class Inscripcion {
    
    private Integer id;
    private Inscripto inscripto;
    private Date fechaInscripcion;
    private Curso curso;
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
     * @return the inscripto
     */
    public Inscripto getInscripto() {
        return inscripto;
    }
    /**
     * @param inscripto the inscripto to set
     */
    public void setInscripto(Inscripto inscripto) {
        this.inscripto = inscripto;
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
    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }
    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    

}
