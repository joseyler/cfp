package ar.gob.cfp.inscripciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.cfp.inscripciones.modelo.Upload;
import ar.gob.cfp.inscripciones.services.UploadsService;

@RestController
@RequestMapping("/v1/inscripciones/archivos")
public class UploadsController {

    @Autowired
    UploadsService uploadService;
    
    
	//http://localhost:8071/inscripciones/v1/inscripciones/archivos
    @PostMapping(value="")
    @ResponseBody
    public ResponseEntity<Object> uploadArchivoInscripcion(
            @RequestParam(name="file", required = true) MultipartFile archivo,
            @RequestParam(name="tipo", required = true) String categoriaArchivo) {
        
        if (esArchivoValido(archivo) && (esUnTipoValido(categoriaArchivo))) {
            Upload carga = uploadService.crearArchivo(archivo,categoriaArchivo);
            return new ResponseEntity<Object>(carga,HttpStatus.OK);
        } else {
           return new ResponseEntity<Object>("Error en datos de entrada", HttpStatus.BAD_REQUEST);
        }
       
        
       
    }

    //restringo por la categoria
    private boolean esUnTipoValido(String categoriaArchivo) {
        return categoriaArchivo.equals("dinFrente") 
                || categoriaArchivo.equals("dinContraFrente") 
                || categoriaArchivo.equals("certificadoEstudios") 
                || categoriaArchivo.equals("aptoMedico") 
                || categoriaArchivo.equals("boletaPago"); 
    }

    //registro por tipo de archivo
    private boolean esArchivoValido(MultipartFile archivo) {
        return archivo.getContentType().contains("jpg") 
        || archivo.getContentType().contains("jpeg") 
        || archivo.getContentType().contains("png") 
        || archivo.getContentType().contains("gif") 
        || archivo.getContentType().contains("tiff") 
        || archivo.getContentType().contains("pdf"); 
    }
    
}
