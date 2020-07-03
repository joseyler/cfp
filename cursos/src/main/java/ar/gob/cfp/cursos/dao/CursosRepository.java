package ar.gob.cfp.cursos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.cursos.dao.entities.CursoEntity;


public interface CursosRepository extends CrudRepository<CursoEntity, Integer> {
	
 List<CursoEntity> findByIdInstitucion(Integer IdInstitucion);
 
 Optional<CursoEntity> findById(Integer id);

List<CursoEntity> findByNombre(String nombre);

}
