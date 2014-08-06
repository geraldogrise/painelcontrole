package br.com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.openid4java.util.HttpClientFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
public class LoginLogoutController {

	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {
		
	
		logger.debug("proxySet --" +System.getProperties().getProperty("http.proxySet"));
		logger.debug("proxyHost --" +System.getProperties().getProperty("http.proxyHost"));
		logger.debug("proxyPort --" +System.getProperties().getProperty("http.proxyPort"));
		logger.debug("proxyType --" +System.getProperties().getProperty("http.proxyType"));

		
		logger.debug("sproxySet --" +System.getProperties().getProperty("https.proxySet"));
		logger.debug("sproxyHost --" +System.getProperties().getProperty("https.proxyHost"));
		logger.debug("sproxyPort --" +System.getProperties().getProperty("https.proxyPort"));
		logger.debug("sproxyType --" +System.getProperties().getProperty("https.proxyType"));
		
		if ( HttpClientFactory.getProxyProperties() != null )
		   logger.debug("teste papopapo --" +HttpClientFactory.getProxyProperties().getProxyHostName());

		
		
		
  		logger.debug("*************************************************");
 		logger.debug("proxySet --" +System.getProperties().getProperty("http.proxySet"));
 		logger.debug("proxyHost --" +System.getProperties().getProperty("http.proxyHost"));
 		logger.debug("proxyPort --" +System.getProperties().getProperty("http.proxyPort"));
 		logger.debug("proxyType --" +System.getProperties().getProperty("http.proxyType"));

 		logger.debug("sproxySet --" +System.getProperties().getProperty("https.proxySet"));
 		logger.debug("sproxyHost --" +System.getProperties().getProperty("https.proxyHost"));
 		logger.debug("sproxyPort --" +System.getProperties().getProperty("https.proxyPort"));
 		logger.debug("sproxyType --" +System.getProperties().getProperty("https.proxyType"));
 		

 		logger.debug("Inicio de tud 22" );
 		
 		
		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		ModelAndView modelAndView = new ModelAndView("/jsp/LoginLogout/login");

		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView getLogtouPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("Received request to show login page");

		Cookie cookie = new Cookie("JSESSIONID", "");
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	
		SecurityContextHolder.getContext().getAuthentication()
				.setAuthenticated(false);
		SecurityContextHolder.setContext(SecurityContextHolder
				.createEmptyContext());
		SecurityContextHolder.clearContext();
		request.getSession().invalidate();

		ModelAndView modelAndView = new ModelAndView("/jsp/LoginLogout/login");
		return modelAndView;
	}


	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView getDeniedPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.debug("Received request to show denied page");

		SecurityContextHolder.getContext().getAuthentication()
				.setAuthenticated(false);
		SecurityContextHolder.setContext(SecurityContextHolder
				.createEmptyContext());
		SecurityContextHolder.clearContext();
		request.getSession().invalidate();

		
		ModelAndView modelAndView = new ModelAndView("/jsp/LoginLogout/denied");
		// modelAndView.addObject("message", "Hello World");
		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return modelAndView;
	}
}