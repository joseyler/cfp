package ar.gob.cfp.inscripciones.services;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ar.gob.cfp.commons.exceptions.CfpException;
import ar.gob.cfp.commons.model.Curso;

@Component
public class CursosComponent {

    @HystrixCommand(fallbackMethod = "getCursoPorDefecto")
    public Curso getCurso(Integer id) throws CfpException {
        RestTemplate rs = new RestTemplate();
        String url = "http://localhost:8073/cursos/v1/cursos/" + id;
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
