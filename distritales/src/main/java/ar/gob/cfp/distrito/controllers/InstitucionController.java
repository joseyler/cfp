package ar.gob.cfp.distrito.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import ar.gob.cfp.distrito.modelo.Distrito;
import ar.gob.cfp.distrito.modelo.Institucion;
import ar.gob.cfp.distrito.services.InstitucionesService;

@RestController
@RequestMapping("/v1/instituciones")
public class InstitucionController {

	@Autowired
	InstitucionesService institucionesService;

	// http://localhost:8070/distritales/v1/instituciones
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllInstituciones(
			@RequestParam(name = "idDistrito", required = true) Integer idDistrito) {
		List<Institucion> respuesta = institucionesService.getInstitucionesPorDistrito(idDistrito);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8070/distritales/v1/instituciones/5
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getInstitucion(@PathVariable("id") Integer idInstitucion) {
		Institucion respuesta = institucionesService.getInstitucionPorId(idInstitucion);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8070/distritales/v1/instituciones/5
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crearInstitucion(@RequestBody Institucion institucion) {
		Institucion respuesta = institucionesService.crearInstitucionPorId(institucion);
		return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
	}

}
