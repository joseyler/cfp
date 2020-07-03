package ar.gob.cfp.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.commons.CfpRestController;
import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.cursos.exceptions.InstitucionInexistenteException;
import ar.gob.cfp.cursos.services.CursosService;

@RestController
@RequestMapping("/v1/cursos")
public class CursosController extends CfpRestController {

	@Autowired
	CursosService cursosService;
	// http://localhost:8073/cursos/v1/cursos
	@GetMapping(value= "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscarCurso(@RequestParam(name = "idInstitucion", required = true) Integer idInstitucion ){
		try {
			List<Curso> listaCursos = cursosService.buscarCurso(idInstitucion); 
			
				return new ResponseEntity<Object>(listaCursos, HttpStatus.OK);
			
		}catch(CfpException e) {
			return procesarException(e);
		}	
	}  
	
	@GetMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscarCursoId(@PathVariable(name="id", required = true) Integer id){
	    try {
	        Curso curso = cursosService.buscarCursoId(id); 
	        return new ResponseEntity<Object>(curso, HttpStatus.OK);
	    } catch (CfpException e) {
            return procesarException(e);
        }
		
	}
	
	// http://localhost:8073/cursos/v1/cursos
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveDistrito(@RequestBody Curso curso) {
		try {
			Curso respuesta = cursosService.guardarCurso(curso);
			return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
		} catch (CfpException e) {
			//return new ResponseEntity<Object>("No existe institucion indicada", HttpStatus.BAD_REQUEST);
			return procesarException(e);
		}
		
	}

}
