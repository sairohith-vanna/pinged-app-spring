package com.pingedapp.appdto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest implements Serializable {
	
	private static final long serialVersionUID = -7734831521266584634L;
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String city;
	private String country;
}
