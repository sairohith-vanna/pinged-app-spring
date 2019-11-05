package com.pingedapp.appmodels;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Comment {
	
	@Id
	private String id;
	
	private String username;
	
	private String comment;
	
	private Date commentDate;
}
