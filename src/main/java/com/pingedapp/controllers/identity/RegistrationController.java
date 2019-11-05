package com.pingedapp.controllers.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoWriteException;
import com.pingedapp.appdto.UserRegistrationRequest;
import com.pingedapp.responses.RegistrationResponse;
import com.pingedapp.security.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@PostMapping(value = "/signup")
	public RegistrationResponse register(@RequestBody UserRegistrationRequest registrationRequest) {
		try {
			boolean userRegistered = registrationService.registerUser(registrationRequest);
			if (userRegistered) {
				return RegistrationResponse.REG_SUCCESSFUL;
			} else {
				return RegistrationResponse.REG_FAILURE_ISERROR;
			}
		} catch (MongoWriteException ex) {
			ex.getCode();
			System.out.println(ex.getCause().getMessage());
			return RegistrationResponse.REG_FAILURE_USEREXISTS;
		}
	}
}
