package ar.gob.cfp.distrito.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.gob.cfp.distrito.dao.DistritoDao;
import ar.gob.cfp.distrito.modelo.Distrito;

@Service
public class DistritosService {
	
	private DistritoDao distritosDao;

	//inyecto el daco por parametro en constructor
	public DistritosService(DistritoDao distritosDao) {
		this.distritosDao = distritosDao;
	}

	public List<Distrito> getAllDistritos(Integer idProvincia) {
		return this.distritosDao.getDistritosDeProvincia(idProvincia);
	}

	public Distrito guardarDistrito(Distrito distrito) {
		return this.distritosDao.guardarDistrito(distrito);
	}

	public Distrito actualizarDistrito(Distrito distrito) {
		return this.distritosDao.guardarDistrito(distrito);
	}
	
	public void eliminar(Integer idDistrito) {
		
	}

	public Distrito getDistritoById(Integer idDistrito) {
		return this.distritosDao.getDistritosById(idDistrito);
	}

}
