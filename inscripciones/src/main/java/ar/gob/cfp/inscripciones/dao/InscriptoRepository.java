package ar.gob.cfp.inscripciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.inscripciones.dao.entities.InscripcionEntity;
import ar.gob.cfp.inscripciones.dao.entities.InscriptoEntity;

public interface InscriptoRepository extends CrudRepository<InscriptoEntity, Integer> {

	List<InscriptoEntity> findAll();
	
}
