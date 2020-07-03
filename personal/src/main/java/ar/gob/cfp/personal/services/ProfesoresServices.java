package ar.gob.cfp.personal.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.personal.dao.ProfesorDao;

@Service
public class ProfesoresServices {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfesoresServices.class);

    ProfesorDao profDao;

    public ProfesoresServices(ProfesorDao profDao) {
        this.profDao = profDao;
    }

    public List<Profesor> getAllProfesores() throws CfpException {
        try {
            return profDao.getAll();
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error obteniendo todos los profesores." + e.getMessage());
        }
    }

    public Profesor getProfesorById(Integer idProfesor) throws CfpException {
        try {
            return profDao.getProfesorById(idProfesor);
        } catch (CfpException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new CfpException("Ha ocurrido un error obteniendo el profesor de id." + idProfesor + ".  Mensaje: " + e.getMessage());
        }
    }

    public Profesor guardarProfesor(Profesor profesor) throws CfpException {
        try {
            return profDao.guardarProfesor(profesor);
        } catch (CfpException e) {
            throw e;
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error guardando profesor");
        }
    }

    public Profesor actualizarProfesor(Profesor profesor) throws CfpException {
        try {
            return profDao.actualizarProfesor(profesor);
        } catch (CfpException e) {
            throw e;
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error actualizando el profesor de id." + profesor.getId() + ".  Mensaje: " + e.getMessage());
        }
    }

    public void deleteProfesor(Integer idProfesor) throws CfpException {
        try {
            profDao.eliminarProfesor(idProfesor);
        } catch (CfpException e) {
            throw e;
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error obteniendo el profesor de id." + idProfesor + ".  Mensaje: " + e.getMessage());
        }

    }

}
