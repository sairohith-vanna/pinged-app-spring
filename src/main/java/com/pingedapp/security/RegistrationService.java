package com.pingedapp.security;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.MongoWriteException;
import com.pingedapp.appdto.UserRegistrationRequest;
import com.pingedapp.appmodels.User;
import com.pingedapp.repositories.UserRepository;

@Service
public class RegistrationService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomPasswordEncodeStrategy encodeStrategy;
	
	public boolean registerUser(UserRegistrationRequest request) throws MongoWriteException {
		User applicationUser = new User();
		try {
		BeanUtils.copyProperties(applicationUser, request);
		User savedUser = userRepository.save(applicationUser);
		if(savedUser != null) {
			return true;
			}
		} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
}
