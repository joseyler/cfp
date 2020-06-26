package ar.gob.cfp.autorizaciones.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.gob.cfp.autorizaciones.dao.entities.UsuarioEntity;


public interface UsuariosRepository extends CrudRepository<UsuarioEntity, Integer> {
	
 List<UsuarioEntity> findByNombreUsuarioAndPassword(String username,String password);


}
