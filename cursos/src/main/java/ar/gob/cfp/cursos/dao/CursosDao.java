package ar.gob.cfp.cursos.dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.metamodel.model.domain.spi.MapPersistentAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.ObjetoNoEncontradoCfpException;
import ar.gob.cfp.commons.exceptions.RecursoExistenteCfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.cursos.dao.entities.CursoEntity;
import ar.gob.cfp.cursos.dao.mappers.CursosMapper;
import ar.gob.cfp.cursos.services.CursosService;

@Service
public class CursosDao  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CursosService.class);
	
	@Autowired
	CursosRepository repo;

	public  List<Curso>  buscarCurso(Integer idInstitucion){
		List<CursoEntity> cursos = repo.findByIdInstitucion(idInstitucion);
		List<Curso> respuesta = CursosMapper.mapModel(cursos);
		
		
		//HACER FILTRO (no aqui...)
		return respuesta; 
	}
	
	public Curso buscarCursoId(Integer id) throws CfpException {
	    try {
		Optional<CursoEntity> respuesta = this.repo.findById(id);
		CursoEntity resp = respuesta.get();
		Curso curso = CursosMapper.mapp(resp);
		return curso;
	    } catch (NoSuchElementException e) {
	       // LOGGER.info("No se encontro el curso solicitado", e);
	        throw new ObjetoNoEncontradoCfpException("No se encuentra curso para el id: " + id);
	    }
	}
	
	public Curso guardarCurso(Curso cursoModel) throws CfpException{
			List<CursoEntity> cursosNombre = this.repo.findByNombre(cursoModel.getNombre());
			if(cursosNombre.isEmpty()) {	
				CursoEntity ent = CursosMapper.mapEntity(cursoModel);
				this.repo.save(ent);
				cursoModel.setId(ent.getId());
				return cursoModel;
			}else {
				//LOGGER.warn("");
				throw new RecursoExistenteCfpException("Ya existe el curso con el nombre "+ cursoModel.getNombre());
			}	
			//throw new RecursoExistenteCfpException("");
		
	}

	public Curso actualizar(Curso curso) throws CfpException{
		try {
			Optional<CursoEntity> enti = this.repo.findById(curso.getId());
			CursoEntity entidad = enti.get();
			CursoEntity cursoActualizar = CursosMapper.mapEntity(curso);
			repo.save(cursoActualizar);
			return curso;
		}catch(NoSuchElementException e) {
			throw new ObjetoNoEncontradoCfpException("No se encuentra el curso "+curso.getId()+", para actualizarlo.");
		}
		
	}

	public void eliminar(Integer id) throws CfpException {
		try {
			Optional<CursoEntity> enti = this.repo.findById(id);
			CursoEntity entidad = enti.get();
			repo.delete(entidad);
		}catch(NoSuchElementException e) {
			throw new ObjetoNoEncontradoCfpException("No se encuentra el curso "+ id +", para eliminarlo.");
		}
	}

	

	
	
	
	
	
	
}
