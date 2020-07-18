package ar.gob.cfp.inscripciones;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.MultipartFilter;

@Configuration
public class FiltrosConfigurations {
	
	
    @Bean
    public FilterRegistrationBean<MultipartFilter> multipartFilterRegistrationBean() {
        final MultipartFilter multipartFilter = new MultipartFilter();
        final FilterRegistrationBean<MultipartFilter> filterRegistrationBean = new FilterRegistrationBean<MultipartFilter>(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
        return filterRegistrationBean;
    }
	
}
