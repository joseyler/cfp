package ar.gob.cfp.inscripciones.dao.mappers;

import ar.gob.cfp.inscripciones.dao.entities.ArchivoTemporalEntity;
import ar.gob.cfp.inscripciones.modelo.ArchivoTemporal;

public class ArchivoTemporalMapper {

    public static ArchivoTemporalEntity mapEntity(ArchivoTemporal atp) {
        ArchivoTemporalEntity ate = new ArchivoTemporalEntity();
        ate.setCategoria(atp.getCategoria());
        ate.setContentType(atp.getContentType());
        ate.setId(atp.getId());
        ate.setUploadId(atp.getUploadId());
        ate.setNombreArchivo(atp.getNombreArchivo());
        return ate;
    }

}
