package ar.gob.cfp.cursos.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.exceptions.RestClienteCallCfpException;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Institucion;
import ar.gob.cfp.commons.model.Profesor;
import ar.gob.cfp.cursos.dao.CursosDao;
import ar.gob.cfp.cursos.exceptions.InstitucionInexistenteException;

@Service
public class CursosService {

	@Autowired
	EurekaClient eureka;
	
	private CursosDao cursosDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CursosService.class);

	// inyecto el daco por parametro en constructor
	public CursosService(CursosDao cursosDao) {
		this.cursosDao = cursosDao;
	}

	
	/////HACER EL RESTO: -empezar por dao (getAll). -Corregir el getId. -EL RESTO...  
	
	
	public List<Curso> buscarCurso(Integer IdInstitucion) throws CfpException {
		try {	
			Institucion existe = existeInstitucion(IdInstitucion);
			
				List<Curso> respuesta = cursosDao.buscarCurso(IdInstitucion);
				//respuesta.setProfesor(buscarProfesor(respuesta.getProfesor().getId()));
				
				for(Curso curso : respuesta) {
					Profesor prof=buscarProfesor(curso.getProfesor().getId());
					curso.setProfesor(prof);
				}
				
				return respuesta;
				
		}catch(Exception e) {
			LOGGER.error("ERROR para Nosotros...", e);
			throw new CfpException("Ha ocurrido un error obteniendo  cursos del a institucion "+ IdInstitucion +". Mensaje: "+ e.getMessage());
			
		}	
	}		

	//////////////////////////////////////////////////////////
	public Curso buscarCursoId(Integer id) throws CfpException {
		try {
			return cursosDao.buscarCursoId(id);
		}catch(CfpException e) {
			throw e;
		}catch(Exception e) {
			LOGGER.error("Error al buscar curso ID ", e);
			throw new CfpException("No se pudo encontrar el curso mencionado en el ID. "+ e.getMessage());
		}	
	}
////////////////////////////////////////////////777
	
	public Curso guardarCurso(Curso curso) throws /*InstitucionInexistenteException,*/ CfpException /**/ {
		try {	
			Institucion instExistente = existeInstitucion(curso.getInstitucion().getId());
			curso.setInstitucion(instExistente);	
			return cursosDao.guardarCurso(curso);
			
		}catch(CfpException e) {
			//throw new InstitucionInexistenteException();
			throw e;
		}catch(Exception e) {
			LOGGER.error("Error Guardando ", e);
			throw new CfpException("Ocurrio un error guardando curso "+ e.getMessage());
		}	
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Curso putCurso(Curso curso) throws CfpException{
		try {
			Curso respuestaDao = cursosDao.actualizar(curso);
			return respuestaDao;
		}catch(CfpException e) {
			throw e;
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void deleteCurso(Integer id) throws CfpException{
		try {
			
			cursosDao.eliminar(id);
		}catch(CfpException e) {
			throw e; 
		}
		
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	private Institucion existeInstitucion(Integer id) throws CfpException {
		try {
			Application application = eureka.getApplication("distritales");
            InstanceInfo instanceInfo = application.getInstances().get(0);
            
			RestTemplate rs = new RestTemplate();
			
			String url = "http://"+ instanceInfo.getIPAddr() +":" + instanceInfo.getPort() + "/distritales/v1/instituciones/" + id;
			
			HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
			Institucion institucion = rs.exchange(url, HttpMethod.GET, entidadHttp, Institucion.class).getBody();
			
			if(institucion==null) {
				 LOGGER.info("No se encontro la institucion solicitada nro: "+ id);
				throw new CfpException("Ocurrio error inesperado obteniendo curso ");
			}
				return institucion;
			
		} catch (HttpStatusCodeException e) {
		    throw new RestClienteCallCfpException(e.getRawStatusCode(), "Error en llamada a curso api: " + e.getResponseBodyAsString());
		} catch(Exception e) {
			LOGGER.error("Error al conectarse a la API instituciones... ", e);
			throw new CfpException("Error Inesperado buscando Institucion " + e.getMessage());
		}
			
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	private Profesor buscarProfesor(Integer id) throws CfpException{
		try {	
			Application application = eureka.getApplication("personal");
            InstanceInfo instanceInfo = application.getInstances().get(0);
			
			RestTemplate rs = new RestTemplate();
			
			String url = "http://"+ instanceInfo.getIPAddr() +":" + instanceInfo.getPort() + "/personal/v1/profesores/" + id;
		
			HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
			Profesor prof = rs.exchange(url, HttpMethod.GET, entidadHttp, Profesor.class).getBody();
			
			if(prof==null) {
				LOGGER.info("No se encontro el profesor solicitado nro: "+ id);
				throw new CfpException("Ocurrio error inesperado obteniendo el profesor ");
			}
				return prof;
			
			
		}catch(HttpStatusCodeException e) {
			LOGGER.error("Error al conectarse a la API PROFESORES... ", e);
			throw new RestClienteCallCfpException(e.getRawStatusCode(), " Error en la llamada a profesores api: "+ e.getResponseBodyAsString());
		}
	}


	

	
}
