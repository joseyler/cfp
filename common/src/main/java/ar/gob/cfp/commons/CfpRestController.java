package ar.gob.cfp.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.NoAutorizadoCfpException;
import ar.gob.cfp.commons.exceptions.NoPermitidoCfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.commons.exceptions.RecursoExistenteCfpException;
import ar.gob.cfp.commons.exceptions.RequerimientoInvalidoCfpException;
import ar.gob.cfp.commons.exceptions.RestClienteCallCfpException;

public class CfpRestController {
    
    
    protected ResponseEntity<Object> procesarException(CfpException ex) {
       
        if (ex instanceof RequerimientoInvalidoCfpException) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        if (ex instanceof NoAutorizadoCfpException) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        if (ex instanceof NoPermitidoCfpException) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
        if (ex instanceof ObjetoNoEncontradoCfpException) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        if (ex instanceof RestClienteCallCfpException) {
            RestClienteCallCfpException aux = (RestClienteCallCfpException) ex;
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.resolve(aux.getStatusCode()));
        }
        if (ex instanceof RecursoExistenteCfpException) {
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
