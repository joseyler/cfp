package ar.gob.cfp.inscripciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.inscripciones.dao.entities.InscripcionEntity;

public interface InscripcionRepository extends CrudRepository<InscripcionEntity, Integer> {

	List<InscripcionEntity> findAll();
	
	List<InscripcionEntity> findByIdCursoAndInscriptoDni(Integer idCurso, Long dniInscripto);
	
}
