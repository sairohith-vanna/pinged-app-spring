package com.pingedapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAuthentication {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	CustomPasswordEncodeStrategy customEncoder;

	public Authentication userAuthentication(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return authentication;
		} catch(AuthenticationException | NullPointerException ex) {
			return null;
		}
	}
	
	public boolean logout() {
		Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
		if(currentAuthentication != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
			return true;
		}
		return false;
	}
}
