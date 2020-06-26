package ar.gob.cfp.autorizaciones.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.gob.cfp.autorizaciones.dao.entities.UsuarioEntity;

@Service
public class UsuariosDao  {
	
	@Autowired
	UsuariosRepository repo;

	public  boolean  validarUsuario(String username,String password){
		List<UsuarioEntity> usuarios = repo.findByNombreUsuarioAndPassword(username,password);
		if (usuarios.isEmpty()) {
		    return false;
		} else {
		    return true;
		}
		
		
	}
	
	
	
}
