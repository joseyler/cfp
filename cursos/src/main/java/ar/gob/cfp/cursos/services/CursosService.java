package ar.gob.cfp.cursos.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.RestClienteCallCfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.cursos.dao.CursosDao;
import ar.gob.cfp.cursos.exceptions.InstitucionInexistenteException;

@Service
public class CursosService {

	private CursosDao cursosDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CursosService.class);

	// inyecto el daco por parametro en constructor
	public CursosService(CursosDao cursosDao) {
		this.cursosDao = cursosDao;
	}

	
	/////HACER EL RESTO: -empezar por dao (getAll). -Corregir el getId. -EL RESTO...  
	
	
	public List<Curso> buscarCurso(Integer IdInstitucion) throws CfpException {
		try {	
			Institucion existe = existeInstitucion(IdInstitucion);
			
				List<Curso> respuesta = cursosDao.buscarCurso(IdInstitucion);
				//respuesta.setProfesor(buscarProfesor(respuesta.getProfesor().getId()));
				
				for(Curso curso : respuesta) {
					Profesor prof=buscarProfesor(curso.getProfesor().getId());
					curso.setProfesor(prof);
				}
				
				return respuesta;
			
		}catch(CfpException e) {
			throw e;
			
		}	
		catch(Exception e) {
			LOGGER.error("ERROR para nosotros...", e);
			throw new CfpException("Ha ocurrido un error obteniendo  cursos del a institucion "+ IdInstitucion +". Mensaje: "+ e.getMessage());
			
		}	
	}		

	
	public Curso buscarCursoId(Integer id) throws CfpException {
		
		return cursosDao.buscarCursoId(id);
	}

	public Curso guardarCurso(Curso curso) throws InstitucionInexistenteException {
		
		Institucion instExistente = existeInstitucion(curso.getInstitucion().getId());
		if (instExistente != null) {
			curso.setInstitucion(instExistente);
			
			return cursosDao.guardarCurso(curso);
		} else {
			throw new InstitucionInexistenteException();
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	private Institucion existeInstitucion(Integer id) throws CfpException {
		try {
			RestTemplate rs = new RestTemplate();
			String url = "http://localhost:8070/distritales/v1/instituciones/" + id;
			HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
			Institucion institucion = rs.exchange(url, HttpMethod.GET, entidadHttp, Institucion.class).getBody();
			
			return institucion;
			
		} catch (HttpStatusCodeException e) {
		   LOGGER.error("Error al conectarse a la API instituciones... ", e);
		    
		    throw new RestClienteCallCfpException(e.getRawStatusCode(), "Error en llamada a curso api: " + e.getResponseBodyAsString());
		} catch (Exception e) {
		    LOGGER.info("No se encontro la institucion solicitada nro: "+ id, e);
			throw new CfpException("Ocurrio error inesperado obteniendo curso " + e.getMessage());
		}	
			
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	private Profesor buscarProfesor(Integer id) {
		RestTemplate rs = new RestTemplate();
		String url = "http://localhost:8072/personal/v1/profesores/" + id;
		HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
		Profesor prof = rs.exchange(url, HttpMethod.GET, entidadHttp, Profesor.class).getBody();//REVISAR...;
		if(prof !=null) {
			return prof;
		}
		return null;
	}

	
}
