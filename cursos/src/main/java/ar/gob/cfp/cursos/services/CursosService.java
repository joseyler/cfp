package ar.gob.cfp.cursos.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.cursos.dao.CursosDao;
import ar.gob.cfp.cursos.exceptions.InstitucionInexistenteException;

@Service
public class CursosService {

	private CursosDao cursosDao;

	// inyecto el daco por parametro en constructor
	public CursosService(CursosDao cursosDao) {
		this.cursosDao = cursosDao;
	}

	public List<Curso> buscarCurso(Integer IdInstitucion) {
		
		Institucion existe = existeInstitucion(IdInstitucion);
		if(existe != null) {
			List<Curso> respuesta = cursosDao.buscarCurso(IdInstitucion);
			//respuesta.setProfesor(buscarProfesor(respuesta.getProfesor().getId()));
			
			for(Curso curso : respuesta) {
				Profesor prof=buscarProfesor(curso.getProfesor().getId());
				curso.setProfesor(prof);
			}
			
			return respuesta;
		}
		
		return null;
		
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

	private Institucion existeInstitucion(Integer id) {
		RestTemplate rs = new RestTemplate();
		String url = "http://localhost:8070/distritales/v1/instituciones/" + id;
		HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
		Institucion institucion = rs.exchange(url, HttpMethod.GET, entidadHttp, Institucion.class).getBody();
		if (institucion != null) {
			return institucion;
		}
		return null;
	}
	
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
