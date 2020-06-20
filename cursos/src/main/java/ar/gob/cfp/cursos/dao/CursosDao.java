package ar.gob.cfp.cursos.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.metamodel.model.domain.spi.MapPersistentAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.cursos.dao.entities.CursoEntity;
import ar.gob.cfp.cursos.dao.mappers.CursosMapper;

@Service
public class CursosDao  {
	
	@Autowired
	CursosRepository repo;

	public  List<Curso>  buscarCurso(Integer idInstitucion){
		List<CursoEntity> cursos = repo.findByIdInstitucion(idInstitucion);
		List<Curso> respuesta = CursosMapper.mapModel(cursos);
		
		
		//HACER FILTRO (no aqui...)
		return respuesta; 
	}
	
	public Curso buscarCursoId(Integer id) {
		Optional<CursoEntity> respuesta = this.repo.findById(id);
		CursoEntity resp = respuesta.get();
		Curso curso = CursosMapper.mapp(resp);
		return curso;
	}
	
	public Curso guardarCurso(Curso cursoModel) {
		CursoEntity ent = CursosMapper.mapEntity(cursoModel);
		this.repo.save(ent);
		cursoModel.setId(ent.getId());
		return cursoModel;
	}

	

	
	
	
	
	
	
}
