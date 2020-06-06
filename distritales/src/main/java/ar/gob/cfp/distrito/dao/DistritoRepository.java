package ar.gob.cfp.distrito.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.distrito.dao.entities.DistritoEntity;

public interface DistritoRepository extends CrudRepository<DistritoEntity, Integer> {
	
	List<DistritoEntity> findAll();
	
	List<DistritoEntity> findByIdProvincia(Integer idProvincia);

}
