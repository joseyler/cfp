package ar.gob.cfp.inscripciones.dao.mappers;

import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.entities.InscriptoEntity;

public class InscriptoMapper {

	public static InscriptoEntity mapEntity(Inscripto ins) {

		InscriptoEntity ie = new InscriptoEntity();
		ie.setId(ins.getId());
		ie.setNombre(ins.getNombre());
		ie.setApellido(ins.getApellido());
		ie.setDni(ins.getDni());
		ie.setFecha_nacimiento(ins.getFecha_nacimiento());
		ie.setNacionalidad(ins.getNacionalidad());
		ie.setPais(ins.getPais());
		ie.setProvincia(ins.getProvincia());
		ie.setLocalidad(ins.getLocalidad());
		ie.setDireccion(ins.getDireccion());
		ie.setEmail(ins.getEmail());
		ie.setCelular(ins.getCelular());
		ie.setMayorEdad(ins.isMayorEdad());
		ie.setNivelEducativo(ins.getNivelEducativo());
		ie.setTelefono(ins.getTelefono());

		return ie;

	}

	public static Inscripto mapInscriptoModelo(InscriptoEntity ins) {
		Inscripto ie = new Inscripto();
		ie.setId(ins.getId());
		ie.setNombre(ins.getNombre());
		ie.setApellido(ins.getApellido());
		ie.setDni(ins.getDni());
		ie.setFecha_nacimiento(ins.getFecha_nacimiento());
		ie.setNacionalidad(ins.getNacionalidad());
		ie.setPais(ins.getPais());
		ie.setProvincia(ins.getProvincia());
		ie.setLocalidad(ins.getLocalidad());
		ie.setDireccion(ins.getDireccion());
		ie.setEmail(ins.getEmail());
		ie.setCelular(ins.getCelular());
		ie.setMayorEdad(ins.isMayorEdad());
		ie.setNivelEducativo(ins.getNivelEducativo());
		ie.setTelefono(ins.getTelefono());

		return ie;
	}
}
