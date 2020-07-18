package ar.gob.cfp.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import ar.gob.cfp.commons.jsonmap.ObjectMapperProvider;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableAutoConfiguration(exclude = { })
@ComponentScan( {"ar.gob.cfp.cursos"})
public class CursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
	}

	@Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return ObjectMapperProvider.getBuilder();
    }
}
