package com.pingedapp.appmodels;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String firstname;
	private String lastname; 
	private String username;
	private String password;
	private String email;
	private String country;
	private String city;
}
