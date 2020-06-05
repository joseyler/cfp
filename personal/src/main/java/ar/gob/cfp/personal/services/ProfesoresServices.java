package ar.gob.cfp.personal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.gob.cfp.personal.dao.ProfesorDao;
import ar.gob.cfp.personal.modelo.Profesor;

@Service
public class ProfesoresServices {
	
	ProfesorDao profDao;
	
	public ProfesoresServices(ProfesorDao profDao) {
		this.profDao = profDao;
	}

	public List<Profesor> getAllProfesores() {
		return profDao.getAll();
	}

	public Profesor getProfesorById(Integer idProfesor) {
		return profDao.getProfesorById(idProfesor);
	}

	public Profesor guardarProfesor(Profesor profesor) {
		return profDao.guardarProfesor(profesor);
	}

	public Profesor actualizarProfesor(Profesor profesor) {
		//TODO Falta hacer
		return profesor;
	}

	public void deleteProfesor(Integer idProfesor) {
		//TODO Falta hacer
		
	}

}
