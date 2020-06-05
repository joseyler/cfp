package ar.gob.cfp.personal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.personal.dao.entities.ProfesorEntity;
import ar.gob.cfp.personal.dao.mappers.ProfesorMapper;
import ar.gob.cfp.personal.modelo.Profesor;

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

	
	public Profesor guardarProfesor(Profesor prof) {
		ProfesorEntity ent = ProfesorMapper.mapEntity(prof);
		repo.save(ent);
		prof.setId(ent.getId());
		return prof;
	}

	public Profesor getProfesorById(Integer idProfesor) {
		Optional<ProfesorEntity> entOp = this.repo.findById(idProfesor);
		ProfesorEntity ProfesorEntity = entOp.get();
		return ProfesorMapper.mapProfesorModelo(ProfesorEntity);
	}

	
	
	
	
	
}
