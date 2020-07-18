package ar.gob.cfp.inscripciones.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.RecursoExistenteCfpException;
import ar.gob.cfp.commons.exceptions.RestClienteCallCfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.InscripcionDao;

@Service
public class InscripcionesService {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InscripcionesService.class);

    InscripcionDao inscDao;

    CursosComponent cursosComponent;

    public InscripcionesService(InscripcionDao inscDao, CursosComponent cursosComponent) {
        this.inscDao = inscDao;
        this.cursosComponent = cursosComponent;
    }

    public Inscripcion crearInscripcion(Inscripcion inscripcion) throws CfpException {
        try {
            if (!inscDao.existeInscripcion(inscripcion)) {
                Inscripto inscriptoCreado = inscDao.crearInscripto(inscripcion.getInscripto());
                inscripcion.setInscripto(inscriptoCreado);
                return inscDao.crearInscripcion(inscripcion);
            } else {
                throw new RecursoExistenteCfpException("Ya existe una inscripcion para el dni " + inscripcion.getInscripto().getDni() + " en el curso id: " + inscripcion.getCurso().getId());
            }
        } catch (CfpException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new CfpException(" Ocurrio un error creando inscripcion " + e.getMessage());
        }
    }

    public List<Inscripcion> getAllInscripciones() throws CfpException {

        try {
            return inscDao.getAll();
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error obteniendo todos las inscripciones." + e.getMessage());
        }
    }

    public Inscripcion getInscripcionById(Integer idInscripto) throws CfpException {
        try {
            return inscDao.getInscripcionById(idInscripto);
            // } catch (CfpException e) {
            // throw e;
        } catch (Exception e) {
            // logear el error
            throw new CfpException("Ha ocurrido un error obteniendo la inscripcion de id." + idInscripto + ".  Mensaje: " + e.getMessage());
        }
    }

    public Curso getCurso(Integer id) throws CfpException {
        try {
            RestTemplate rs = new RestTemplate();
            String url = "http://localhost:8073/cursos/v1/cursos/" + id;
            HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
            Curso curso = rs.exchange(url, HttpMethod.GET, entidadHttp, Curso.class).getBody();
            return curso;
        } catch (HttpStatusCodeException e) {
            // logear excepcion

            throw new RestClienteCallCfpException(e.getRawStatusCode(), "Error en llamada a curso api: " + e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CfpException("Ocurrio error inesperado obteniendo curso " + e.getMessage());
        }
    }
}
