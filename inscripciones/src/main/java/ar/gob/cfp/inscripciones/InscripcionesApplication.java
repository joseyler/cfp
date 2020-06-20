package ar.gob.cfp.inscripciones;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


import ar.gob.cfp.commons.jsonmap.ObjectMapperProvider;
import ar.gob.cfp.commons.model.Curso;
import ar.gob.cfp.commons.model.Inscripcion;
import ar.gob.cfp.commons.model.Inscripto;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
public class InscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscripcionesApplication.class, args);
		Inscripcion i = new Inscripcion();
		i.setCurso(new Curso());
		i.getCurso().setId(3);
		
		Inscripto ic= new Inscripto();
		ic.setApellido("Diaz");
		ic.setNacionalidad("Argentina");
		ic.setCelular("15689878");
		ic.setDireccion("Dorrego 3000");
		ic.setDni(32545689L);
		ic.setEmail("a@b.net");
		ic.setFecha_nacimiento(new Date(new Date().getTime() - 693792000000L));
		ic.setLocalidad("Olavarria");
		ic.setMayorEdad(true);
		ic.setNivelEducativo("Universitario");
		ic.setNombre("Luciano");
		ic.setPais("Argentina");
		ic.setProvincia("Buenos Aires");
		ic.setTelefono("426589");
		
		i.setInscripto(ic);
		
		System.out.println(ObjectMapperProvider.pasarAJSON(i));
		
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return ObjectMapperProvider.getBuilder();
    }
	
	@Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(10*1024*1024);//maximo 10 megas
        return commonsMultipartResolver;
    }

}	
