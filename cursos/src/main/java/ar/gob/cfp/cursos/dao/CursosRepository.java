package ar.gob.cfp.cursos.dao;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.cursos.dao.entities.CursoEntity;

public interface CursosRepository extends CrudRepository<CursoEntity, Integer> {
	
	

}
