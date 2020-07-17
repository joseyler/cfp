package ar.gob.cfp.cfpservices;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.gob.cfp.cfpservices.filter.AutorizationFilter;

@EnableHystrix
@Configuration
public class GatewayConfiguration {
    
    @Bean
    public RouteLocator misRutas(RouteLocatorBuilder builder) {
        return builder.routes().
            route(p -> p
                    .path("/personal/**")
                    .filters(f -> f.addRequestHeader("Ruteado", "personal")
                            .addResponseHeader("Routeado", "personal")
                            .filter(new AutorizationFilter())
//                            .hystrix(config -> config.setName("personal")
//                                                .setFallbackUri("forward:/personalfallback")))
                    ).uri("lb://personal")
                    )
            .route (p -> p
                    .path("/distritales/**")
                    .filters(f -> f.addRequestHeader("Routeado", "distritales")
                          .addResponseHeader("Routeado", "personal")
                          .filter(new AutorizationFilter())
//                            .hystrix(config -> config.setName("distritales")
//                                                .setFallbackUri("forward:/distritalesfallback"))
                            )
                    .uri("lb://distritales"))
            .route (p -> p
                    .path("/cursos/**")
                    .filters(f -> f
                            .filter(new AutorizationFilter())
                            .hystrix(config -> config.setName("cursos")
                                                .setFallbackUri("forward:/cursosfallback")))
                    .uri("http://localhost:8073/cursos"))
            .route (p -> p
                    .path("/inscripciones/**")
                    .filters(f -> f
                            .filter(new AutorizationFilter())
                            .hystrix(config -> config.setName("inscripciones")
                                                .setFallbackUri("forward:/inscripcionesfallback")))
                    .uri("http://localhost:8071/inscripciones"))
            .route (p -> p
                    .path("/autorizaciones/**")
                    .filters(f -> f
                            .filter(new AutorizationFilter())
//                            .hystrix(config -> config.setName("autorizaciones")
//                                                .setFallbackUri("forward:/autorizacionesfallback"))
                            )
                    .uri("lb://autorizaciones"))
            .build();
    }
    
    

}
