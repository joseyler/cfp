package ar.gob.cfp.inscripciones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Curso;

@Component
public class CursosComponent {

	@Autowired
	EurekaClient eurekacliente;
	
    @HystrixCommand(fallbackMethod = "getCursoPorDefecto")
    public Curso getCurso(Integer id) throws CfpException {
    	 Application application = eurekacliente.getApplication("cursos");
         InstanceInfo instanceInfo = application.getInstances().get(0);
         RestTemplate rs = new RestTemplate();
         String url = "http://"+ instanceInfo.getIPAddr() +":" + instanceInfo.getPort() + "/cursos/v1/cursos/" + id;
        HttpEntity<Object> entidadHttp = new HttpEntity<Object>(null);
        return rs.getForObject(url, Curso.class);
    }

    public Curso getCursoPorDefecto(Integer id) {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNombre("Api Curso no disponible");
        return curso;
    }

}
