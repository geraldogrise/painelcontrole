package br.com.painelcontrole.security;

public enum AuthenticationFailedReason
{
	BadCredentials,
	PasswordExpiring,
	PasswordExpired,
	AccountExpired,
	AccountDisabled,
	AccountLocked
}