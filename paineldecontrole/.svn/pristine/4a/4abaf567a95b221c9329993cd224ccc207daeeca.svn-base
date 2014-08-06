package br.com.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import  org.springframework.security.openid.*;


public class DemoAuthenticationSuccessHandler { 
/*
implements AuthenticationUserDetailsService  {
	private InMemoryUserDetailsService userDetailsService;
	
	public void setUserDetailsService(InMemoryUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	
	public void onOpenIdAuthenticationSuccess(HttpServletRequest request,
											  String identityUrl, 
											  List<OpenIDAttribute> attributes) {
		String email = null;
		String firstName = null;
		String lastName = null;
		String fullName = null;
		
		//try to get required params from attributes
		for (OpenIDAttribute attr : attributes) {
			if ("email".equals(attr.getName())) {
				email = (attr.getValues() != null && attr.getValues().size() > 0 ) ? attr.getValues().get(0) : null;
			} else if ("first".equals(attr.getName())) {
				firstName = (attr.getValues() != null && attr.getValues().size() > 0 ) ? attr.getValues().get(0) : null;
			} else if ("last".equals(attr.getName())) {
				lastName = (attr.getValues() != null && attr.getValues().size() > 0 ) ? attr.getValues().get(0) : null;
			} else if ("fullname".equals(attr.getName())) {
				fullName = (attr.getValues() != null && attr.getValues().size() > 0 ) ? attr.getValues().get(0) : null;
			}
		}
		
//		if (StringUtils.isEmpty(fullName) &&
//				(!StringUtils.isEmpty(firstName) || !StringUtils.isEmpty(lastName))) {
//			fullName = firstName + " " + lastName;
//		}
		
		// add user with specified openId - if not exist
		try {
			userDetailsService.loadUserByUsername(identityUrl);
		} catch (UsernameNotFoundException ex) {
			// user not found - create new
			DemoUserDetails user = new DemoUserDetails(identityUrl, email, fullName);
			userDetailsService.addUser(user);
		}
	}
	*/
}