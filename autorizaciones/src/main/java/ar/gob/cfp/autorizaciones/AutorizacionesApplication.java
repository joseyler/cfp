package ar.gob.cfp.autorizaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import ar.gob.cfp.commons.jsonmap.ObjectMapperProvider;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableAutoConfiguration(exclude = { })
public class AutorizacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizacionesApplication.class, args);
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return ObjectMapperProvider.getBuilder();
    }

}
