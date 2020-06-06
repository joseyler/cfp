package ar.gob.cfp.distrito.dao.mappers;

import java.util.ArrayList;
import java.util.List;

import ar.gob.cfp.distrito.dao.entities.DistritoEntity;
import ar.gob.cfp.distrito.dao.entities.InstitucionEntity;
import ar.gob.cfp.distrito.modelo.Institucion;

public class InstitucionesMapper {

	public static InstitucionEntity mapEntity(Institucion inst,DistritoEntity distE) {
		InstitucionEntity ie=new InstitucionEntity();
		ie.setId(inst.getId());
		ie.setDireccion(inst.getDireccion());
		ie.setLocalidad(inst.getLocalidad());
		ie.setNombre(inst.getNombre());
		ie.setNumero(inst.getNumero());
		if (distE!=null) {
			ie.setDistrito(distE);
		}
		return ie;
	}

	public static Institucion getInstitucion(InstitucionEntity entity) {
		Institucion institucionModelo=new Institucion();
		institucionModelo.setId(entity.getId());
		institucionModelo.setDireccion(entity.getDireccion());
		institucionModelo.setLocalidad(entity.getLocalidad());
		institucionModelo.setNombre(entity.getNombre());
		institucionModelo.setNumero(entity.getNumero());
		return institucionModelo;
	}

	public static List<Institucion> getInstituciones(List<InstitucionEntity> resultadoConsulta) {
		List<Institucion> ret = new ArrayList<Institucion>();
		for ( InstitucionEntity ent:resultadoConsulta) {
			ret.add(getInstitucion(ent));
		}
		return ret;
	}

}
