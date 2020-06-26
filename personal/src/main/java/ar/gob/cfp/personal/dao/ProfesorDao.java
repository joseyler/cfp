package ar.gob.cfp.personal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.commons.exceptions.RecursoExistenteCfpException;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.personal.dao.entities.ProfesorEntity;
import ar.gob.cfp.personal.dao.mappers.ProfesorMapper;

@Service
public class ProfesorDao  {
	
	@Autowired
	ProfesorRepository repo;

//	public List<Profesor> getAll2() {
//		EntityManager em = emf.createEntityManager();
//		try {
//			TypedQuery<ProfesorEntity> query = em.createQuery("select d from ProfesorEntity d ",ProfesorEntity.class);
//			List<ProfesorEntity> resultList = query.getResultList();
//			return ProfesorMapper.mapModelo(resultList);
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			em.close();
//		}
//		return new ArrayList<Profesor>();
//	}
	
	
	public List<Profesor> getAll() {
		List<ProfesorEntity> resultList = repo.findAll();
		return ProfesorMapper.mapModelo(resultList);
	}

	
	public Profesor guardarProfesor(Profesor prof) throws CfpException, RecursoExistenteCfpException {
		List<ProfesorEntity> profesoresDelDni = repo.findByDni(prof.getDni());
		if(profesoresDelDni.isEmpty()) {
		ProfesorEntity ent = ProfesorMapper.mapEntity(prof);
		repo.save(ent);
		prof.setId(ent.getId());
		return prof;
		}else {
			throw new RecursoExistenteCfpException("Ya existe un profesor para el dni: ");
		}
	}

	public Profesor getProfesorById(Integer idProfesor) throws CfpException {
		Optional<ProfesorEntity> entOp = this.repo.findById(idProfesor);
		ProfesorEntity profesorEntity = entOp.get();
		if(profesorEntity ==null) {
			throw new ObjetoNoEncontradoCfpException("No se encuentra el profesor para el id:" + idProfesor);
		}
		return ProfesorMapper.mapProfesorModelo(profesorEntity);
	}

	
	
	
	
	
}
