package br.com.painelcontrole.security;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;


public class ADNAuthoritiesPopulator  implements LdapAuthoritiesPopulator {

	private ADNUserDetailsService userService;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		
		//username = username.substring(0, username.length() - 3);
		
		userService = new ADNUserDetailsService();
		UserICF usernovo = (UserICF) userService.loadUserByUsername(username);
		
		return  usernovo.getAuthorities();
	}
}