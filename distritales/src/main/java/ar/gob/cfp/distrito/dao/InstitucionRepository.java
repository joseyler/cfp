package ar.gob.cfp.distrito.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.distrito.dao.entities.DistritoEntity;
import ar.gob.cfp.distrito.dao.entities.InstitucionEntity;

public interface InstitucionRepository extends CrudRepository<InstitucionEntity, Integer> {
	
	List<InstitucionEntity> findAll();
	
	List<InstitucionEntity> findByDistrito(DistritoEntity distrito);
	
	List<InstitucionEntity> findByIdDistrito(Integer idDistrito);

}
