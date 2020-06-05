package ar.gob.cfp.personal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.personal.dao.entities.ProfesorEntity;

public interface ProfesorRepository extends CrudRepository<ProfesorEntity, Integer> {
	
	List<ProfesorEntity> findAll();


}
