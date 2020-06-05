package ar.gob.cfp.autorizaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
public class AutorizacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizacionesApplication.class, args);
	}

}
