package ar.gob.cfp.commons.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonParseException extends JsonProcessingException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public JsonParseException(String mensaje ) {
        super(mensaje);
    }

}
