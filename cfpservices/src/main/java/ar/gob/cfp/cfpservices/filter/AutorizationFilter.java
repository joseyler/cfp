package ar.gob.cfp.cfpservices.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;

import ar.gob.cfp.cfpservices.modelo.TokenContainer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AutorizationFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        if (exchange.getRequest().getMethod().equals(HttpMethod.GET) ||
                exchange.getRequest().getPath().toString().contains("autorizaciones")) {
           return chain.filter(exchange);
        } else {
            //que exista y sea valido un token
            String autorizacion = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (autorizacion!=null) {
                String usuarioJson = esTokenValido(autorizacion.replace("Bearer ", ""));
                if (usuarioJson!=null) {
                    exchange.getRequest().getHeaders().add("usuariojson", usuarioJson);
                    return chain.filter(exchange);
                } else {
                    return retornarNoAutorizado(exchange);
                }
                
            } else {
                return retornarNoAutorizado(exchange);
            }
        }
        
    }

    private Mono<Void> retornarNoAutorizado(ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        byte[] response =  "No autorizado".getBytes(StandardCharsets.UTF_8);;
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(response);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
    
    public String esTokenValido(String token) {
        try {
            RestTemplate rs = new RestTemplate();
            String url = "http://localhost:8075/autorizaciones/v1/validar";
            TokenContainer auth = new TokenContainer();
            auth.setToken(token);
            HttpEntity<TokenContainer> entidadHttp = new HttpEntity<TokenContainer>(auth);
            String respuesta = rs.exchange(url, HttpMethod.POST, entidadHttp, String.class).getBody();
            return respuesta;
        } catch (HttpStatusCodeException e) {
            if (e.getRawStatusCode() == 401) {
                return null;
            }
            System.out.println("ERROR CODIGO: " + e.getRawStatusCode() + "  MENSAJE: " + e.getStatusText());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }

}
