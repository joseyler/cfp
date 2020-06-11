package ar.gob.cfp.distrito.dao.mappers;

import java.util.ArrayList;
import java.util.List;

import ar.gob.cfp.commons.model.Distrito;
import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.distrito.dao.entities.DistritoEntity;
import ar.gob.cfp.distrito.dao.entities.InstitucionEntity;

public class DistritoMapper {

	public static List<Distrito> mapModelo(List<DistritoEntity> resultList) {
		List<Distrito> respuesta = new ArrayList<Distrito>();
		for (DistritoEntity entidad : resultList) {
			respuesta.add(DistritoMapper.mapModelo(entidad,false));
		}
		return respuesta;
	}

	public static Distrito mapModelo(DistritoEntity entidad,boolean obtenerInstituciones) {
		Distrito distrito = new Distrito();
		distrito.setId(entidad.getId());
		distrito.setNombre(entidad.getNombre());
		distrito.setIdProvincia(entidad.getIdProvincia());
		distrito.setIdRegion(entidad.getIdRegion());
		if (obtenerInstituciones) {
			distrito.setInstituciones(new ArrayList<Institucion>());
			for (InstitucionEntity inst: entidad.getInstituciones()) {
				distrito.getInstituciones().add(InstitucionesMapper.getInstitucion(inst));
			}
		}
		return distrito;
	}
	
	public static DistritoEntity mapEntity(Distrito dist) {
		DistritoEntity de = new DistritoEntity();
		de.setId(dist.getId());
		de.setNombre(dist.getNombre());
		de.setIdProvincia(dist.getIdProvincia());
		de.setIdRegion(dist.getIdRegion());
		de.setInstituciones(new ArrayList<InstitucionEntity>());
		if (dist.getInstituciones()!=null) {
			for (Institucion inst : dist.getInstituciones()) {
				de.getInstituciones().add(InstitucionesMapper.mapEntity(inst,de));
			}
		}
		return de;
	}

}
