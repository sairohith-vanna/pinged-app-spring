package com.pingedapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pingedapp.appmodels.User;
import com.pingedapp.repositories.UserRepository;
import com.pingedapp.security.ApplicationAuthentication;

import AppDTO.UserLoginDTO;

@RestController(value = "/")
public class AuthenticationController {

	@Autowired
	ApplicationAuthentication appAuthentication;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/login")
	public String authenticate(@RequestBody UserLoginDTO userLogin) {
		Authentication authentication = appAuthentication.userAuthentication(userLogin.getUsername(), userLogin.getPassword());
		if(authentication != null) {
			//return userRepository.findByUsername(userLogin.getUsername());
			return "Authenticated";
		}
		else
			return "Error in Authentication";
	}
}
