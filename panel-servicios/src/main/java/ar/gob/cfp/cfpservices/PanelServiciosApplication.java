package ar.gob.cfp.cfpservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class PanelServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanelServiciosApplication.class, args);
		
	}
	


}	
