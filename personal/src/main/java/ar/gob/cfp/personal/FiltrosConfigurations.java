package ar.gob.cfp.personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.gob.cfp.personal.services.AutorizacionesService;

@Configuration
public class FiltrosConfigurations {
	
	@Autowired 
	AutorizacionesService authServices;
	
	@Bean
	public FilterRegistrationBean<FiltroMetodosPrivados> filtroMetodosPrivados() {
		FilterRegistrationBean<FiltroMetodosPrivados> filtroReg = new FilterRegistrationBean<FiltroMetodosPrivados>();
		filtroReg.setFilter(new FiltroMetodosPrivados(authServices));
		filtroReg.addUrlPatterns("/v1/profesores/*","/otraUrl");
		filtroReg.addUrlPatterns("/otromas");
		return filtroReg;
	}

}
