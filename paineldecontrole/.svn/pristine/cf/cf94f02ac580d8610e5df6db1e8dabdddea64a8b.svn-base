package br.com.painelcontrole.security;

import java.util.List;



public interface ISecurityProvider
{
	public boolean isAuthenticated();

	public String getPrincipal() throws SecurityException;

	public List<String> getRoles();

	public boolean hasRole(String role);

	AuthenticationResult authenticate(String login, String password);
}
