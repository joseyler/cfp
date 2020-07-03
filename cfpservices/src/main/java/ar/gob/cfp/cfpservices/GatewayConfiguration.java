package ar.gob.cfp.cfpservices;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                            .hystrix(config -> config.setName("personal")
                                                .setFallbackUri("forward:/personalfallback")))
                    .uri("http://localhost:8072/personal")
                    )
            .route (p -> p
                    .path("/distritales/**")
                    .filters(f -> f.addRequestHeader("Routeado", "distritales")
                          .addResponseHeader("Routeado", "personal")
                            .hystrix(config -> config.setName("distritales")
                                                .setFallbackUri("forward:/distritalesfallback")))
                    .uri("http://localhost:8070/distritales"))
            .build();
    }
    
    

}
