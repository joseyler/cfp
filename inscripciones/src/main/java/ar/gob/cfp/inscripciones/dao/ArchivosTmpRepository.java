package ar.gob.cfp.inscripciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.inscripciones.dao.entities.ArchivoTemporalEntity;

public interface ArchivosTmpRepository extends CrudRepository<ArchivoTemporalEntity, Integer> {
	
	List<ArchivoTemporalEntity> findAll();
	

}
