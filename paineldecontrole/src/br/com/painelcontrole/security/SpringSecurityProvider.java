package br.com.painelcontrole.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implementation of the ISecurityProvider interface which utilizes a Spring security context.
 *
 * This software is the property of Portland Webworks.
 *
 * @Author rnorthup
 */
public class SpringSecurityProvider implements ISecurityProvider
{
	@Autowired(required = true)
	@Qualifier("org.springframework.security.authenticationManager")
	private AuthenticationManager authManager;
		 
	public AuthenticationResult authenticate(String login, String password)
	 	{
		 		AuthenticationResult result = new AuthenticationResult();
		 		Authentication auth = null;
		 		try{
		 			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password); 
		 			auth = authManager.authenticate(SecurityContextHolder.getContext().getAuthentication());
		 		}catch (Exception e) {
					System.out.println(e.getMessage());
				}
		 
		 		// Store the authentication result in spring's security context.
		 		SecurityContextHolder.getContext().setAuthentication(auth);
		 
		 		result.setAuthenticated(auth.isAuthenticated());
		// Translate failure to authenticate into a generic enumerated value.
		if (! auth.isAuthenticated()) {
			result.setFailureReason(AuthenticationFailedReason.BadCredentials);
		}
		else {
			// Get the principal from the successful auth
			UserDetails principal = (UserDetails)auth.getPrincipal();
			
			// Check the principal for error conditions, and if one is found translate to generic enumerated value.
			if (! principal.isAccountNonExpired()) {
				result.setFailureReason(AuthenticationFailedReason.AccountExpired);
			}
			else if (! principal.isAccountNonLocked()) {
				result.setFailureReason(AuthenticationFailedReason.AccountLocked);
			}
			else if (! principal.isCredentialsNonExpired()) {
				result.setFailureReason(AuthenticationFailedReason.PasswordExpired);
			}
			else if (! principal.isEnabled()) {
				result.setFailureReason(AuthenticationFailedReason.AccountDisabled);
			}

			// Populate the generic auth result's principal field with the Spring principal's username.
			result.setPrincipal(principal.getUsername());

			// Pull the authorities from the Spring principal into the generic auth result's role list.
			List<String> roles = new ArrayList<String>(principal.getAuthorities().size());
			for (GrantedAuthority authority : principal.getAuthorities()) {
				roles.add(authority.getAuthority());
			}
			result.setRoles(roles);
		}
		return result;
	}

	
	public boolean isAuthenticated()
	{
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	public String getPrincipal()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			throw new SecurityException("Not authenticated");
		}

		Object principal = auth.getPrincipal();
		if (principal == null) {
			throw new SecurityException("No principal found in security context");
		}

		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}

		return principal.toString();
	}

	public List<String> getRoles()
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roles = new ArrayList<String>(authorities.size());
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		return roles;
	}



	public boolean hasRole(String role)
	{
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(role)) {
				return true;
			}
		}
		return false;
	}


}
