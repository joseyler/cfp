package ar.gob.cfp.inscripciones.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.jsonmap.ObjectMapperProvider;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;
import ar.gob.cfp.inscripciones.dao.entities.InscripcionEntity;
import ar.gob.cfp.inscripciones.dao.entities.InscriptoEntity;
import ar.gob.cfp.inscripciones.dao.mappers.InscripcionMapper;
import ar.gob.cfp.inscripciones.dao.mappers.InscriptoMapper;

@Service
public class InscripcionDao {
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(InscripcionDao.class);

	@Autowired
	InscripcionRepository repoInscripcion;
	@Autowired
	InscriptoRepository repoInscripto;
	
	public List<Inscripcion>getAll(){
		List<InscripcionEntity>resultList = repoInscripcion.findAll();
		return InscripcionMapper.mapModelo(resultList);
	}
	
	public Inscripcion crearInscripcion(Inscripcion inscripcion) {
		InscripcionEntity ent = InscripcionMapper.mapEntity(inscripcion);
		ent.setFechaInscripcion(new Date());
		repoInscripcion.save(ent);
		inscripcion.setId(ent.getId());
		inscripcion.setFechaInscripcion(ent.getFechaInscripcion());
		return inscripcion;
	}
	
	public Inscripto crearInscripto(Inscripto inscripto) {
		InscriptoEntity ent = InscriptoMapper.mapEntity(inscripto);
		repoInscripto.save(ent);
		inscripto.setId(ent.getId());
		return inscripto;
	}

	public Inscripcion getInscripcionById(Integer idInscripto) {
		Optional<InscripcionEntity> entOp = this.repoInscripcion.findById(idInscripto);
		InscripcionEntity InscripcionEntity = entOp.get();
		return InscripcionMapper.mapInscripcionModelo(InscripcionEntity);
				
	}

	public Inscripto getInscriptoById(Integer id) {
		Optional<InscriptoEntity> entOp = this.repoInscripto.findById(id);
		InscriptoEntity InscriptoEntity = entOp.get();
		return InscriptoMapper.mapInscriptoModelo(InscriptoEntity);
	}
	
	public void getInscripcionExistente() {
	    LOGGER.info(ObjectMapperProvider.pasarAJSON(repoInscripcion.findByIdCursoAndInscriptoDni(2,32545689L)));
	}
}               
