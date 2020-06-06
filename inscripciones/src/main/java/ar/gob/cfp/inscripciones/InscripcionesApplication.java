package ar.gob.cfp.inscripciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
public class InscripcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InscripcionesApplication.class, args);
	}

}
