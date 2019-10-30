package com.pingedapp.pinged;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pingedapp.appmodels.User;
import com.pingedapp.repositories.UserRepository;
import com.pingedapp.security.CustomPasswordEncodeStrategy;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = { "com.pingedapp.repositories", "com.pingedapp.security", "com.pingedapp.appmodels",
		"com.pingedapp.controllers" })
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Slf4j
public class PingedApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	CustomPasswordEncodeStrategy customEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PingedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setFirstname("Ramesh");
		user.setLastname("Thota");
		user.setPassword(customEncoder.getDelegatingPasswordEncoder().encode("password"));
		user.setCity("Hyderabad");
		user.setCountry("India");
		user.setEmail("ramesh.thota@vannainfotech.com");
		user.setUsername("ramesh123");
		userRepository.save(user);
		log.debug("CommandLineRunner run invoked.");
	}
}
