package ar.gob.cfp.inscripciones.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.inscripciones.services.InscripcionesService;
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

	@Autowired
	InscripcionesService inscripcionesService;

	// http://localhost:8071/inscripciones/v1/inscrpciones
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> crearInscripcion(@RequestBody Inscripcion inscripcion) {
		Inscripcion respuesta = inscripcionesService.crearInscripcion(inscripcion);
		return new ResponseEntity<Object>(respuesta, HttpStatus.CREATED);
	}

	// http://localhost:8071/inscripciones/v1/inscripciones
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllInscripciones() {
		List<Inscripcion> respuesta = inscripcionesService.getAllInscripciones();

		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}

	// http://localhost:8071/inscripciones/v1/inscripciones/8
	@GetMapping(value = "/{idInscripcion}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getInscripcionById(@PathVariable("idInscripcion") Integer idInscripto) {
		Inscripcion respuesta = inscripcionesService.getInscripcionById(idInscripto);

		return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
	}
}
