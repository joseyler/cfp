package ar.gob.cfp.cfpservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
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
    
    
    @RequestMapping("/cursosfallback")
    public Mono<String> cursos() {
        return Mono.just("No se puede acceder a la api de cursos");
    }
    
    @RequestMapping("/inscripcionesfallback")
    public Mono<String> inscripciones() {
        return Mono.just("No se puede acceder a la api de inscripciones");
    }
    
    
    @RequestMapping("/autorizacionesfallback")
    public ResponseEntity<Mono<String>> autorizaciones() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(Mono.just("No se puede acceder a la api de autorizaciones"));
        
        return new ResponseEntity<Mono<String>>(Mono.just("No se puede acceder a la api de autorizaciones"),HttpStatus.UNAUTHORIZED);
    }
    
    @RequestMapping("/configfallback")
    public Mono<String> configuraciones() {
        return Mono.just("No se puede acceder a la api de configuraciones");
    }
    
    
    
    
    
    
    
    

}
