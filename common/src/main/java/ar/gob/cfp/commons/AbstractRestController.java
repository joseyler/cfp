package ar.gob.cfp.commons;

import org.springframework.http.ResponseEntity;

import ar.gob.cfp.commons.exceptions.CfpException;

public class AbstractRestController {

	
	protected ResponseEntity<Object> procesarException(CfpException ex){
		return null;
	}
	
}
