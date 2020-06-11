package ar.gob.cfp.distrito.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.distrito.dao.InstitucionesDao;

@Service
public class InstitucionesService {
	
	private InstitucionesDao institucionesDao;

	//inyecto el daco por parametro en constructor
	public InstitucionesService(InstitucionesDao institucionesDao) {
		this.institucionesDao = institucionesDao;
	}

	public List<Institucion> getInstitucionesPorDistrito(Integer idDistrito) {
		return this.institucionesDao.getInstitucionesByDistritoId(idDistrito);
	}

	public Institucion crearInstitucionPorId(Institucion institucion) {
		return institucionesDao.crear(institucion);
	}

	public Institucion getInstitucionPorId(Integer idInstitucion) {
		return this.institucionesDao.getInstitucionesById(idInstitucion);
	}

}
