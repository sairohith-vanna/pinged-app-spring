package com.pingedapp.controllers.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.security.ApplicationAuthentication;

@RestController
public class CurrentUserController {
	
	@Autowired
	ApplicationAuthentication appAuthentication;
	
	@GetMapping(value = "/logout")
	public String logout() {
		if(appAuthentication.logout()) {
			return "User Logged out.";
		}
		else {
			return "Logout Error.";
		}
	}
}
