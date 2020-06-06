package ar.gob.cfp.personal.dao.mappers;

import java.util.ArrayList;
import java.util.List;

import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.personal.dao.entities.ProfesorEntity;

public class ProfesorMapper {
	
	
	public static Profesor mapProfesorModelo(ProfesorEntity pe) {
		Profesor p = new Profesor();
		p.setApellido(pe.getApellido());
		p.setDni(pe.getDni());
		p.setFechaNacimiento(pe.getFechaNacimiento());
		p.setId(pe.getId());
		p.setNombre(pe.getNombre());
		p.setTitulo(pe.getTitulo());
		return p;
	}

	public static List<Profesor> mapModelo(List<ProfesorEntity> resultList) {
		List<Profesor> profesores = new ArrayList<Profesor>();
		for (ProfesorEntity profesorEntity : resultList) {
			profesores.add(mapProfesorModelo(profesorEntity));
		}
		return profesores;
	}

	public static ProfesorEntity mapEntity(Profesor pe) {
		ProfesorEntity p = new ProfesorEntity();
		p.setApellido(pe.getApellido());
		p.setDni(pe.getDni());
		p.setFechaNacimiento(pe.getFechaNacimiento());
		p.setId(pe.getId());
		p.setNombre(pe.getNombre());
		p.setTitulo(pe.getTitulo());
		return p;
	}

}
