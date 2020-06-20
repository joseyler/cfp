package ar.gob.cfp.inscripciones.dao.mappers;


import java.util.ArrayList;
import java.util.List;

import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.entities.InscripcionEntity;

public class InscripcionMapper {
	
	public static List<Inscripcion> mapModelo(List<InscripcionEntity> resultList){
		List<Inscripcion> inscripcion = new ArrayList<Inscripcion>();
		for(InscripcionEntity inscripcionEntity : resultList) {
			inscripcion.add(mapInscripcionModelo(inscripcionEntity));
		}
		return inscripcion;
	}
	
	public static Inscripcion mapInscripcionModelo(InscripcionEntity ie) {
		Inscripcion i = new Inscripcion();
		i.setId(ie.getId());
		i.setFechaInscripcion(ie.getFechaInscripcion());
		i.setInscripto(new Inscripto());
		i.getInscripto().setId(ie.getIdInscripto());
		i.setCurso(new Curso());
		i.getCurso().setId(ie.getIdCurso());
		return i;
	}
	
	public static InscripcionEntity mapEntity(Inscripcion inscripcion) {
		InscripcionEntity ie = new InscripcionEntity();
		ie.setId(inscripcion.getId());
		ie.setFechaInscripcion(inscripcion.getFechaInscripcion());
		ie.setIdCurso(inscripcion.getCurso().getId());
		ie.setIdInscripto(inscripcion.getInscripto().getId());
		return ie;
	}

	
}
