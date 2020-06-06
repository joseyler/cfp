package ar.gob.cfp.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { })
public class CommonsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CommonsApplication.class, args);
	}

}
