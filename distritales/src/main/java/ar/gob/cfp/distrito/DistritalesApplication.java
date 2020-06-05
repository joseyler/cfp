package ar.gob.cfp.distrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
public class DistritalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistritalesApplication.class, args);
	}

}
