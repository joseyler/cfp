package ar.gob.cfp.personal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.personal.services.ProfesoresServices;

@RestController
@RequestMapping("/v1/profesores")
public class ProfesoresController {

	@Autowired
	ProfesoresServices profesoresService;

	// http://localhost:8072/personal/v1/profesores
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllProfesores() {
		List<Profesor> respuesta = profesoresService.getAllProfesores();
		
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8072/personal/v1/profesores/8
	@GetMapping(value = "/{idProfesor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProfesorbyId(@PathVariable("idProfesor") Integer idProfesor) {
		Profesor respuesta = profesoresService.getProfesorById(idProfesor);
		
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8072/personal/v1/profesores
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveProfesor(@RequestBody Profesor profesor) {
		Profesor respuesta = profesoresService.guardarProfesor(profesor);
		return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
	}

	// http://localhost:8072/personal/v1/profesores/8
	@PutMapping(value = "/{idProfesor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProfesor(@PathVariable("idProfesor") Integer idProfesor,
			@RequestBody Profesor profesor) {
		if (!idProfesor.equals(profesor.getId())) {
			return new ResponseEntity<Object>("Error: el id parametrizado no coincide con el del cuerpo",
					HttpStatus.BAD_REQUEST);
		}
		Profesor respuesta = profesoresService.actualizarProfesor(profesor);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8072/personal/v1/profesores/8
	@DeleteMapping(value = "/{idProfesor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteProfesor(@PathVariable("idProfesor") Integer idProfesor) {
		profesoresService.deleteProfesor(idProfesor);
		return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
	}

}
