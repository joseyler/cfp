package ar.gob.cfp.distrito.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.model.Distrito;
import ar.gob.cfp.distrito.dao.entities.DistritoEntity;
import ar.gob.cfp.distrito.dao.mappers.DistritoMapper;

@Service
public class DistritoDao  {
	
	@Autowired
	DistritoRepository repo;

//	public List<Distrito> getAll2() {
//		EntityManager em = emf.createEntityManager();
//		try {
//			TypedQuery<DistritoEntity> query = em.createQuery("select d from DistritoEntity d ",DistritoEntity.class);
//			List<DistritoEntity> resultList = query.getResultList();
//			return DistritoMapper.mapModelo(resultList);
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			em.close();
//		}
//		return new ArrayList<Distrito>();
//	}
	
	
	public List<Distrito> getAll() {
		List<DistritoEntity> resultList = repo.findAll();
		return DistritoMapper.mapModelo(resultList);
	}
	
	public List<Distrito> getDistritosDeProvincia(Integer idProvincia) {
		List<DistritoEntity> resultList = repo.findByIdProvincia(idProvincia);
		return DistritoMapper.mapModelo(resultList);
	}
	
	public Distrito guardarDistrito(Distrito dist) {
		DistritoEntity ent = DistritoMapper.mapEntity(dist);
		repo.save(ent);
		dist.setId(ent.getId());
		return dist;
	}

	public Distrito getDistritosById(Integer idDistrito) {
		Optional<DistritoEntity> entOp = this.repo.findById(idDistrito);
		DistritoEntity distritoEntity = entOp.get();
		return DistritoMapper.mapModelo(distritoEntity, true);
	}

	
	
	
	
	
}
