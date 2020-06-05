package ar.gob.cfp.personal.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ar.gob.cfp.personal.modelo.AutorizacionModelo;

@Service
public class AutorizacionesService {
	

	
	public boolean esTokenValido(String token) {
		try {
			RestTemplate rs = new RestTemplate();
			String url = "http://localhost:8075/autorizaciones/v1/validar";
			AutorizacionModelo auth = new AutorizacionModelo();
			auth.setToken(token);
			HttpEntity<AutorizacionModelo> entidadHttp = new HttpEntity<AutorizacionModelo>(auth);
			String respuesta = rs.exchange(url, HttpMethod.POST, entidadHttp, String.class).getBody();
			return respuesta !=null;
		} catch (HttpStatusCodeException e) {
			if (e.getRawStatusCode() == 401) {
				return false;
			}
			System.out.println("ERROR CODIGO: " + e.getRawStatusCode() + "  MENSAJE: " + e.getStatusText());
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return false;
	}

}
