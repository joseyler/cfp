package ar.gob.cfp.cursos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.cursos.dao.entities.CursoEntity;
import ar.gob.cfp.cursos.dao.mappers.CursosMapper;

@Service
public class CursosDao  {
	
	@Autowired
	CursosRepository repo;

	public Curso guardarCurso(Curso cursoModel) {
		CursoEntity ent = CursosMapper.mapEntity(cursoModel);
		repo.save(ent);
		cursoModel.setId(ent.getId());
		return cursoModel;
	}

	
	
	
	
	
	
}
