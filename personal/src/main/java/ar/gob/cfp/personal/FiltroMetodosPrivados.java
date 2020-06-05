package ar.gob.cfp.personal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.gob.cfp.personal.services.AutorizacionesService;

//Fuera del contexto de spring, no esta anotada
public class FiltroMetodosPrivados implements Filter {
	
	AutorizacionesService authServices;

	public FiltroMetodosPrivados(AutorizacionesService authServices) {
		this.authServices = authServices;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//request.getmethod;
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getMethod().equals("GET")) {
			chain.doFilter(request, response);
		} else {
			//que exista y sea valido un token
			String autorizacion = req.getHeader("Authorization");
			if (autorizacion!=null  &&  esTokenValido(autorizacion.replace("Bearer ", ""))) {
				chain.doFilter(request, response);
			} else {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
			}
		}
		//hacer cosas despues
		System.out.println("despues del filtro");
	}

	private boolean esTokenValido(String token) {
		return authServices.esTokenValido(token);
	}

}
