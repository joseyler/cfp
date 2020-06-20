package ar.gob.cfp.inscripciones.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.InscripcionDao;

@Service
public class InscripcionesService {

	InscripcionDao inscDao;

	public InscripcionesService(InscripcionDao inscDao) {
		this.inscDao = inscDao;
	}

	public Inscripcion crearInscripcion(Inscripcion inscripcion) {
		Inscripto inscriptoCreado = inscDao.crearInscripto(inscripcion.getInscripto());
		inscripcion.setInscripto(inscriptoCreado);
		return inscDao.crearInscripcion(inscripcion);
	}

	public List<Inscripcion> getAllInscripciones() {
		List<Inscripcion> inscripciones = inscDao.getAll();
		for (Inscripcion inscripcion : inscripciones) {
			Inscripto inscripto = inscDao.getInscriptoById(inscripcion.getInscripto().getId());
			inscripcion.setInscripto(inscripto);
			Curso curso = getCurso(inscripcion.getCurso().getId());
			if (curso != null) {
				inscripcion.setCurso(curso);
			}
		}
		return inscripciones;
	}

	public Inscripcion getInscripcionById(Integer idInscripto) {

		Inscripcion inscripcionById = inscDao.getInscripcionById(idInscripto);
		Inscripto inscripto = inscDao.getInscriptoById(inscripcionById.getInscripto().getId());
		inscripcionById.setInscripto(inscripto);
		Curso curso = getCurso(inscripcionById.getCurso().getId());
		if (curso != null) {
			inscripcionById.setCurso(curso);
		}
		return inscripcionById;
	}

	private Curso getCurso(Integer id) {
		try {
			RestTemplate rs = new RestTemplate();
			String url = "http://localhost:8073/cursos/v1/cursos/" + id;
			HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
			Curso curso = rs.exchange(url, HttpMethod.GET, entidadHttp, Curso.class).getBody();
			return curso;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
