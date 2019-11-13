package com.pingedapp.controllers.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.security.ApplicationAuthentication;

@RestController
public class CurrentUserController {
	
	@Autowired
	ApplicationAuthentication appAuthentication;
	
	@GetMapping(value = "/appLogout")
	public String logout() {
		System.out.println("Hello");
		if(appAuthentication.logout()) {
			return "User Logged out.";
		}
		else {
			return "Logout Error.";
		}
	}
	
	@GetMapping(value = "/logoutSuccess")
	public boolean logoutSuccess() {
		boolean loggedOut = appAuthentication.logout();
		return (loggedOut && SecurityContextHolder.getContext().getAuthentication() == null);
	}
}
