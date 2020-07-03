package ar.gob.cfp.cfpservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GatewayController {
    
    @RequestMapping("/distritalesfallback")
    public Mono<String> distritales() {
        return Mono.just("No se puede acceder a la api de distritales");
    }
    
    @RequestMapping("/personalfallback")
    public Mono<String> personal() {
        return Mono.just("No se puede acceder a la api de personal");
    }
    
    

}
