package ar.gob.cfp.cursos.dao.mappers;

import ar.gob.cfp.commons.model.Curso;
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
		return ce;
	}

}
