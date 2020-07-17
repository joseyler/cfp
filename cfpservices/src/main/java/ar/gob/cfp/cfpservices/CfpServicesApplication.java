package ar.gob.cfp.cfpservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@EnableAutoConfiguration
public class CfpServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfpServicesApplication.class, args);
		
	}
	
}	
