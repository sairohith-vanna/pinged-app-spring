package com.pingedapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoWriteException;
import com.pingedapp.appdto.UserRegistrationRequest;
import com.pingedapp.security.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping(value = "/signup")
	public String register(@RequestBody UserRegistrationRequest registrationRequest) {
		try {
			boolean userRegistered = registrationService.registerUser(registrationRequest);
			if (userRegistered) {
				return "Registration successful.";
			} else {
				return "Error in registration.";
			}
		} catch (MongoWriteException ex) {
			ex.getCode();
			return "Username already exists.";
		}
	}
}
