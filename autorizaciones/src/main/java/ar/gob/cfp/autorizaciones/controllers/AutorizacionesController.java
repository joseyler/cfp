package ar.gob.cfp.autorizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.autorizaciones.services.AutorizacionesService;
import ar.gob.cfp.commons.model.autorizacion.DatosAcceso;
import ar.gob.cfp.commons.model.autorizacion.InfoSesion;
import ar.gob.cfp.commons.model.autorizacion.ValidacionToken;

@RestController
@RequestMapping("/v1")
public class AutorizacionesController {

	@Autowired
	AutorizacionesService authService;

	
	@PostMapping(value = "/acceso", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> acceder(@RequestBody DatosAcceso datos) {
		try {
			ValidacionToken respuesta = authService.validarSesion(datos);
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Validacion error", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> validar(@RequestBody ValidacionToken valToken) {
		try {
			InfoSesion infoSesion = authService.validarToken(valToken);
			if (infoSesion!=null) {
				return new ResponseEntity<Object>(infoSesion, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("Token no valido para acceso", HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Object>("Error en validacion", HttpStatus.UNAUTHORIZED);
		}
		
	}

}
