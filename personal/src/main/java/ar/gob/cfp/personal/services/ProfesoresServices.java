package ar.gob.cfp.personal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.personal.dao.ProfesorDao;

@Service
public class ProfesoresServices {
	
	ProfesorDao profDao;
	
	public ProfesoresServices(ProfesorDao profDao) {
		
		this.profDao = profDao;
	}

	public List<Profesor> getAllProfesores() throws CfpException{
		try{
			return profDao.getAll();
		}catch(Exception e) {
			//logear el error
			throw new CfpException("Ha ocurrido un eror obteniendo todos los profesores." + e.getMessage());
		}
	}

	public Profesor getProfesorById(Integer idProfesor) throws CfpException {
		try{
			return profDao.getProfesorById(idProfesor);
		}catch (CfpException e) {
			throw e;
		}catch (Exception e) {
			//logear el error
			throw new CfpException("Ha ocurrido un eror obteniendo todos los profesores." + e.getMessage());
		}
	}

	public Profesor guardarProfesor(Profesor profesor) throws CfpException {
		try{
			return profDao.guardarProfesor(profesor);
		}catch (CfpException e) {
			throw e;
		}catch (Exception e) {
			//logear el error
			throw new CfpException("Ha ocurrido un eror obteniendo todos los profesores." + e.getMessage());
		}
	}

	public Profesor actualizarProfesor(Profesor profesor) throws CfpException{
		//TODO Falta hacer
		return profesor;
	}

	public void deleteProfesor(Integer idProfesor) throws CfpException{
		//TODO Falta hacer
		
	}

}
