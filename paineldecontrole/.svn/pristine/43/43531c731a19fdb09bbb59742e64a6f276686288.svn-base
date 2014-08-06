package br.com.painelcontrole.security;

import java.util.List;

/**
 * This software is the property of Portland Webworks.
 * 
 * @Author rnorthup
 */
public class AuthenticationResult {
	private boolean authenticated;
	private String principal;
	private AuthenticationFailedReason failureReason;
	private List<String> roles;

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setFailureReason(AuthenticationFailedReason reason) {
		this.failureReason = reason;
	}

	public AuthenticationFailedReason getFailureReason() {
		return failureReason;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}
}
