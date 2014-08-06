package br.com.painelcontrole.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class securityBase extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws AuthenticationException {

/*		Cookie cookie = new Cookie("j_base", "");
		cookie.setMaxAge(0);
		
		response.addCookie(cookie); 
*/		
		
		Cookie cookie = new Cookie("j_base", request.getParameter("j_base") ); 
		cookie.setMaxAge(60 * 60 * 24); // 10 segundos 
		response.addCookie(cookie); 
		
		return super.attemptAuthentication(request, response);

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {

		
		super.successfulAuthentication(request, response, authResult);
		
		

		//SecurityContextHolder.getContext().setAuthentication(ssp.authenticate("henrique.nunes", "1234");
		

	//	System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
		//RequestAttributes re = RequestAttributes.this();
		//RequestContextHolder.setRequestAttributes(attributes)
		//Object lp = RequestContextHolder.setRequestAttributes(Req)
		
		/*SecurityContextHolder.getContext().getAuthentication().getCredentials().
		request.getParameter("j_base");*/
		
		/*
		 try {
		        // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
		        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("henrique.nunes", "1234");
		        token.setDetails(new WebAuthenticationDetails(request));
		        //token.setDetails("BFF");
		        
		        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        //logger.debug("Logging in with [{}]", authentication.getPrincipal());
		        
		        System.out.println(authentication.getDetails().toString());
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		    } catch (Exception e) {
		        SecurityContextHolder.getContext().setAuthentication(null);
		        logger.error("Failure in autoLogin", e);
		    }*/
	}

/*	@Override
	protected String obtainUsername(
			javax.servlet.http.HttpServletRequest request) {

		logger.warn("ENTREI");
		System.out.println("obtainUsername" + request.getParameter("j_base"));

		return super.obtainUsername(request);

	}
*/
}
