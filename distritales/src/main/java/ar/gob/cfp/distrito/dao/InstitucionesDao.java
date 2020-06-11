package ar.gob.cfp.distrito.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.distrito.dao.entities.DistritoEntity;
import ar.gob.cfp.distrito.dao.entities.InstitucionEntity;
import ar.gob.cfp.distrito.dao.mappers.InstitucionesMapper;

@Service
public class InstitucionesDao  {
	
	@Autowired
	InstitucionRepository repo;
	
	@Autowired
	DistritoRepository repoDistrito;

	public Institucion crear(Institucion institucion) {
		Optional<DistritoEntity> findById = repoDistrito.findById(institucion.getDistrito().getId());
		DistritoEntity disEnt = findById.get();
		
		InstitucionEntity instEntity = InstitucionesMapper.mapEntity(institucion, disEnt);
		repo.save(instEntity);
		institucion.setId(instEntity.getId());
		return institucion;
		
	}

	public List<Institucion> getInstitucionesByDistrito(Integer idDistrito) {
		Optional<DistritoEntity> findById = repoDistrito.findById(idDistrito);
		DistritoEntity disEnt = findById.get();
		
		List<InstitucionEntity> resultadoConsulta = repo.findByDistrito(disEnt);
		
		return InstitucionesMapper.getInstituciones(resultadoConsulta);
	}
	
	
	public List<Institucion> getInstitucionesByDistritoId(Integer idDistrito) {
		List<InstitucionEntity> resultadoConsulta = repo.findByIdDistrito(idDistrito);
		return InstitucionesMapper.getInstituciones(resultadoConsulta);
	}

	public Institucion getInstitucionesById(Integer idInstitucion) {
		Optional<InstitucionEntity> instEntOpt = repo.findById(idInstitucion);
		if (instEntOpt.isPresent()) {
			InstitucionEntity institucionEntity = instEntOpt.get();
			return InstitucionesMapper.getInstitucion(institucionEntity);
		} else {
			return null;
		}
	}

	
	
	
	
	
}
