package br.com.painelcontrole.security;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;


public class ADNCustomUserDetailsMapper extends LdapUserDetailsMapper {

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities )
	{
		//UserDetails originalUser = super.mapUserFromContext(ctx, username, authorities);
		
		ADNUserDetailsService userService;
		userService = new ADNUserDetailsService();
		
		UserICF newUser = (UserICF) userService.loadUserByUsername(username);
		

		return newUser;
	}
}