package com.pingedapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pingedapp.appmodels.User;
import com.pingedapp.repositories.UserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) {
		User applicationUser = userRepository.findByUsername(username);
		if(applicationUser == null)
			throw new UsernameNotFoundException(username);
		return new ApplicationUserDetails(applicationUser);
	}
}
