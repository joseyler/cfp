package ar.gob.cfp.inscripciones.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.inscripciones.dao.entities.ArchivoTemporalEntity;
import ar.gob.cfp.inscripciones.dao.mappers.ArchivoTemporalMapper;
import ar.gob.cfp.inscripciones.modelo.ArchivoTemporal;

@Service
public class ArchivosTmpDao  {
	
	@Autowired
	ArchivosTmpRepository repo;

    public ArchivoTemporal guardarArchivoTemporal(ArchivoTemporal atp) {
        ArchivoTemporalEntity atpEntity = ArchivoTemporalMapper.mapEntity(atp);
        atpEntity.setFechaCreacion(new Date());
        repo.save(atpEntity);
        atp.setId(atpEntity.getId());
        return atp;
        
    }


	
	
	
	
}
