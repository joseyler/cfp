package ar.gob.cfp.distrito.controllers;

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

import ar.gob.cfp.distrito.modelo.Distrito;
import ar.gob.cfp.distrito.services.DistritosService;

@RestController
@RequestMapping("/v1/distritos")
public class DistritosController {
	
	@Autowired
	DistritosService distritoService;

	//http://localhost:8070/distritales/v1/distritos?idProvincia=34&region=45
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDistritos(
			@RequestParam(name = "idProvincia",required = true) Integer idProvincia,
			@RequestParam(name = "region",required = false) Integer region) {
		List<Distrito> respuesta = distritoService.getAllDistritos(idProvincia);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
	//http://localhost:8070/distritales/v1/distritos/8
	@GetMapping(value = "/{idDistrito}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDistritosOficiales(@PathVariable("idDistrito") Integer idDistrito) {
		Distrito respuesta = distritoService.getDistritoById(idDistrito);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
	//http://localhost:8070/distritales/v1/distritos
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveDistrito(@RequestBody Distrito distrito) {
		Distrito respuesta = distritoService.guardarDistrito(distrito);
		return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
	}
	
	//http://localhost:8070/distritales/v1/distritos/8
	@PutMapping(value = "/{idDistrito}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateDistrito(@PathVariable("idDistrito") Integer idDistrito,@RequestBody Distrito distrito) {
		if (!idDistrito.equals(distrito.getId())) {
			return new ResponseEntity<Object>("Error: el id parametrizado no coincide con el del cuerpo",HttpStatus.BAD_REQUEST);
		}
		Distrito respuesta = distritoService.actualizarDistrito(distrito);
		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
	
	//http://localhost:8070/distritales/v1/distritos/8
	@DeleteMapping(value = "/{idDistrito}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteDistrito(@PathVariable("idDistrito") Integer idDistrito) {
		//Distrito respuesta = distritoService.deleteDistrito(null);
		return new ResponseEntity<Object>(null, HttpStatus.NO_CONTENT);
	}

}
