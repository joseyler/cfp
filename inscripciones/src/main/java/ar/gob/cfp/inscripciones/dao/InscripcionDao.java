package ar.gob.cfp.inscripciones.dao;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.commons.exceptions.RecursoExistenteCfpException;

import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.entities.InscripcionEntity;
import ar.gob.cfp.inscripciones.dao.entities.InscriptoEntity;
import ar.gob.cfp.inscripciones.dao.mappers.InscripcionMapper;
import ar.gob.cfp.inscripciones.dao.mappers.InscriptoMapper;


@Service
public class InscripcionDao {

	@Autowired
	InscripcionRepository repoInscripcion;
	@Autowired
	InscriptoRepository repoInscripto;
	
	public List<Inscripcion>getAll(){
		List<InscripcionEntity>resultList = repoInscripcion.findAll();
		return InscripcionMapper.mapModelo(resultList);
	}
	
	public Inscripcion crearInscripcion(Inscripcion inscripcion) throws CfpException{
		try {
		InscripcionEntity ent = InscripcionMapper.mapEntity(inscripcion);
		ent.setFechaInscripcion(new Date());
		repoInscripcion.save(ent);
		inscripcion.setId(ent.getId());
		inscripcion.setFechaInscripcion(ent.getFechaInscripcion());
		return inscripcion;
		} catch (NoSuchElementException e) {
		throw new RecursoExistenteCfpException("Ya existe una inscripcion para la " + inscripcion.getId());
		}
		
		
	}
	
	
	
	
	public Inscripto crearInscripto(Inscripto inscripto) throws CfpException{
		try {
		InscriptoEntity ent = InscriptoMapper.mapEntity(inscripto);
		repoInscripto.save(ent);
		inscripto.setId(ent.getId());
		return inscripto;
		} catch (NoSuchElementException e) {
		throw new RecursoExistenteCfpException("Ya existe un inscripto para el " + inscripto.getId());
	}
	}
	public Inscripcion getInscripcionById(Integer idInscripto) throws CfpException{
		try {
		Optional<InscripcionEntity> entOp = this.repoInscripcion.findById(idInscripto);
		InscripcionEntity InscripcionEntity = entOp.get();
		return InscripcionMapper.mapInscripcionModelo(InscripcionEntity);
		} catch (NoSuchElementException e) {
			throw new ObjetoNoEncontradoCfpException("No se encuentra la inscripcion para el id " + idInscripto);
			
		}
				
	}

	public Inscripto getInscriptoById(Integer id) throws CfpException{
		try {
		Optional<InscriptoEntity> entOp = this.repoInscripto.findById(id);
		InscriptoEntity InscriptoEntity = entOp.get();
		return InscriptoMapper.mapInscriptoModelo(InscriptoEntity);
		} catch (NoSuchElementException e) {
			throw new ObjetoNoEncontradoCfpException("No se encuentra la inscripto para el id " + id);
		}
	}

	
   

    public boolean existeInscripcion(Inscripcion inscripcion) {
        List<InscripcionEntity> resultado = this.repoInscripcion.findByIdCursoAndInscriptoDni(inscripcion.getCurso().getId(), inscripcion.getInscripto().getDni());
        if (resultado==null || resultado.isEmpty()) {
            return false;
        } else {
            return true;
        }
        
        
    }

}               
