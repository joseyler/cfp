package ar.gob.cfp.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.cursos.exceptions.InstitucionInexistenteException;
import ar.gob.cfp.cursos.modelo.Curso;
import ar.gob.cfp.cursos.services.CursosService;

@RestController
@RequestMapping("/v1/cursos")
public class CursosController {

	@Autowired
	CursosService cursosService;

	// http://localhost:8073/cursos/v1/cursos
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveDistrito(@RequestBody Curso curso) {
		try {
			Curso respuesta = cursosService.guardarCurso(curso);
			return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
		} catch (InstitucionInexistenteException e) {
			return new ResponseEntity<Object>("No existe Institucion indicada", HttpStatus.BAD_REQUEST);
		}
		
	}

}
