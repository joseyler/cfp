package ar.gob.cfp.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import ar.gob.cfp.commons.jsonmap.ObjectMapperProvider;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
@ComponentScan({ "ar.gob.cfp.personal", "ar.gob.cfp.commons" })
public class PersonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return ObjectMapperProvider.getBuilder();
    }

}
