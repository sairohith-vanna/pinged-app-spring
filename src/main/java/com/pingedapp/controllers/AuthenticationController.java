package com.pingedapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.appdto.UserLoginRequest;
import com.pingedapp.appmodels.User;
import com.pingedapp.repositories.UserRepository;
import com.pingedapp.security.ApplicationAuthentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthenticationController {

	@Autowired
	ApplicationAuthentication appAuthentication;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/login")
	public User authenticate(@RequestBody UserLoginRequest userLogin) {
		log.debug("Username: "+userLogin.getUsername());
		Authentication authentication = appAuthentication.userAuthentication(userLogin.getUsername(), userLogin.getPassword());
		if(authentication != null) {
			return userRepository.findByUsername(userLogin.getUsername());
		}
		else
			return null;
	}
	
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
