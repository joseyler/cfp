package ar.gob.cfp.cursos.dao.mappers;

import java.util.ArrayList;
import java.util.List;

import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.cursos.dao.entities.CursoEntity;

public class CursosMapper {

	public static CursoEntity mapEntity(Curso cursoModel) {
		CursoEntity ce = new CursoEntity();
		ce.setFechaInicio(cursoModel.getFechaInicio());
		ce.setHorasCatedra(cursoModel.getHorasCatedra());
		ce.setId(cursoModel.getId());
		ce.setNombre(cursoModel.getNombre());
		ce.setPeriodoAcademico(cursoModel.getPeriodoAcademico());
		ce.setIdInstitucion(cursoModel.getInstitucion().getId());
		ce.setIdProfesor(cursoModel.getProfesor().getId());
		return ce;
	}

	public static List<Curso> mapModel(List<CursoEntity> cursos) {
		List<Curso> respuesta = new ArrayList<Curso>();
		for(CursoEntity indice : cursos) {
			respuesta.add(mapp(indice));
		}
		
		return respuesta;
	}

	public static Curso mapp(CursoEntity indice) {
		 Curso r = new Curso();
		 r.setId(indice.getId());
		 r.setNombre(indice.getNombre());
		 r.setHorasCatedra(indice.getHorasCatedra());
		 r.setFechaInicio(indice.getFechaInicio());
		 r.setPeriodoAcademico(indice.getPeriodoAcademico());
		 r.setProfesor(new Profesor());
		 r.getProfesor().setId(indice.getIdProfesor());
		return r;
	}

}
