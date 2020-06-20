package ar.gob.cfp.inscripciones.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ar.gob.cfp.inscripciones.dao.ArchivosTmpDao;
import ar.gob.cfp.inscripciones.dao.entities.ArchivoTemporalEntity;
import ar.gob.cfp.inscripciones.modelo.ArchivoTemporal;
import ar.gob.cfp.inscripciones.modelo.Upload;

@Service
public class UploadsService {
    
    @Autowired
    ArchivosTmpDao atpDao;
    
    private static final String PATH_BASE_FILES = "D:/Users/jeyler/Trabajo/CFP/SpringBoot/archivos";

    public Upload crearArchivo(MultipartFile archivo, String categoriaArchivo) {
        try {
            Path pathTmp = Paths.get(PATH_BASE_FILES + "/tmp"); 
            if (!Files.exists(pathTmp)) {
                Files.createDirectories(pathTmp);
            }
            ArchivoTemporal atp = new ArchivoTemporal();
            atp.setUploadId("UPL" + new Date().getTime());
            atp.setNombreArchivo(archivo.getOriginalFilename());
            atp.setContentType(archivo.getContentType());
            atp.setCategoria(categoriaArchivo);
            
            atpDao.guardarArchivoTemporal(atp);
            
            String urlArchivo  = PATH_BASE_FILES + "/tmp/" + atp.getUploadId();
            guardarArchivoDirTemporal(urlArchivo,archivo.getBytes());
            
            Upload resultado = new Upload();
            resultado.setIdUpload(atp.getUploadId());
            return resultado;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    private void guardarArchivoDirTemporal(String urlArchivo, byte[] bytes) throws IOException {
        
        //alterntiva valida
        //OutputStream os = new FileOutputStream(urlArchivo);
        File archivo = new File(urlArchivo);
        OutputStream os = new FileOutputStream(archivo);
        os.write(bytes);
        os.close();
        
    }

     
}
