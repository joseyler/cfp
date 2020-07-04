package ar.gob.cfp.cursos.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.commons.CfpRestController;
import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.RequerimientoInvalidoCfpException;
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
	public ResponseEntity<Object> saveCurso(@RequestBody Curso curso) {
		try {
			Curso respuesta = cursosService.guardarCurso(curso);
			return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);

		} catch (CfpException e) {
			//return new ResponseEntity<Object>("No existe institucion indicada", HttpStatus.BAD_REQUEST);
			return procesarException(e);

		}
		
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizarCurso(@RequestBody Curso curso, @PathVariable(name="id", required=true) Integer id){
		try {
			if(!curso.getId().equals(id)) {
				throw new RequerimientoInvalidoCfpException("No coincide el ID del cuerpo, con el del Path"); 
			}
			Curso respuesta = cursosService.putCurso(curso);
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		}catch(CfpException e) {
			return procesarException(e); 
		}
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminarCurso(@PathVariable(name="id", required=true)Integer id){
		try {
			
			cursosService.deleteCurso(id);
			return new ResponseEntity<Object>("El curso fue eliminado", HttpStatus.OK); 
		}catch(CfpException e) {
			return procesarException(e);
		}
	}

}
