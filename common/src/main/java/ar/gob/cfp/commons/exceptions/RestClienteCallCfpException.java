package ar.gob.cfp.commons.exceptions;

public class RestClienteCallCfpException extends CfpException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    int statusCode;

    public RestClienteCallCfpException(int statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }
    
    
    

}
