package com.pingedapp.appdto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewComment {
	
	private String username;
	private String comment;
	private Date commentDate;
}
