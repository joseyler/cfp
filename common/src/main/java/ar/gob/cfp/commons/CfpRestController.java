package ar.gob.cfp.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CfpRestController.class);

    
    protected ResponseEntity<Object> procesarException(CfpException ex) {
       
        if (ex instanceof RequerimientoInvalidoCfpException) {
            LOGGER.warn(ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
        if (ex instanceof NoAutorizadoCfpException) {
            LOGGER.warn(ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        if (ex instanceof NoPermitidoCfpException) {
            LOGGER.info(ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
        if (ex instanceof ObjetoNoEncontradoCfpException) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        if (ex instanceof RestClienteCallCfpException) {
            LOGGER.error(ex.getMessage(),ex);
            RestClienteCallCfpException aux = (RestClienteCallCfpException) ex;
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.resolve(aux.getStatusCode()));
        }
        if (ex instanceof RecursoExistenteCfpException) {
            LOGGER.error(ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        LOGGER.error(ex.getMessage(),ex);
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
