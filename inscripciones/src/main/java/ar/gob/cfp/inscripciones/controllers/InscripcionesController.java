package ar.gob.cfp.inscripciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.inscripciones.services.PdfService;

@RestController
@RequestMapping("/v1/inscripciones")
public class InscripcionesController {
    
    @Autowired
    PdfService pdfService;
    
    //http://localhost:8071/inscripciones/v1/inscripciones/8/comprobante
    @GetMapping(value="/{idinscripcion}/comprobante", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Object> descargarComprobante(@PathVariable("idinscripcion") Integer idInscripcion) {
        try {
            byte[] archivoPdf = pdfService.getComprobanteInscripcion(idInscripcion);
            HttpHeaders headersComprobante = new HttpHeaders();
            headersComprobante.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ComprobanteInscripcion" + idInscripcion + ".pdf");
            return new ResponseEntity<Object>(archivoPdf ,headersComprobante, HttpStatus.OK);
        } catch (ObjetoNoEncontradoCfpException e) {
            return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
        } catch (CfpException e) {
            return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
}
