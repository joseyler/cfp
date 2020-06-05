package ar.gob.cfp.autorizaciones.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.gob.cfp.autorizaciones.modelo.DatosAcceso;
import ar.gob.cfp.autorizaciones.modelo.InfoSesion;
import ar.gob.cfp.autorizaciones.modelo.Usuario;
import ar.gob.cfp.autorizaciones.modelo.ValidacionToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AutorizacionesService {
	
	//idealmente se toma de un certificado
	private static final String PRIVATE_KEY = "hjfdsahjkdn43__---dfsgasjhcvu&&&22221233445554344dFGFSDCVGFGDSD3223234DCFS";

	public ValidacionToken validarSesion(DatosAcceso datos) {
		
		//validar en base de datos u otro metodos si el nombre y password son correctos
		Usuario usuario = new Usuario(datos.getNombre()); //supongo OK;
		
		Date expiracionToken = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(expiracionToken);
		cal.add(Calendar.HOUR, 8);
		expiracionToken = cal.getTime();  // la fecha actual + 8 horas
		
		Map<String,Object> informacionToken = new HashMap<String, Object>();
		informacionToken.put("usuario","usuario en json");
		
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setClaims(informacionToken);
		jwtBuilder.setExpiration(expiracionToken);
		
		String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, PRIVATE_KEY).compact();
		
		ValidacionToken vt = new ValidacionToken();
		vt.setToken(token);
		return vt;
	}

	public InfoSesion validarToken(ValidacionToken valToken) {
		try {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(PRIVATE_KEY);
			Jws<Claims> parseado = parser.parseClaimsJws(valToken.getToken());
			// a partir de esta linea es valido
			Claims claims = parseado.getBody();
			InfoSesion infoSesion = new InfoSesion();
			infoSesion.setUsuario(new Usuario("")); 
			//lo debemos extraer del token
			claims.get("usuario");//obtenemos el usuario
			return infoSesion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
