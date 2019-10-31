package com.pingedapp.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncodeStrategy {

	@Bean
	public PasswordEncoder getDelegatingPasswordEncoder() {
		Map<String, PasswordEncoder> passwordEncoders = new HashMap<String, PasswordEncoder>();
		passwordEncoders.put("bcrypt", new BCryptPasswordEncoder());
		passwordEncoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		PasswordEncoder encoder = new DelegatingPasswordEncoder("bcrypt", passwordEncoders);
		return encoder;
	}
}
