package com.pingedapp.appmodels;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Post {
	
	@Id
	private String postId;
	
	private String username;
	
	private String postContent;
	
	private Date postDate;
	
	private List<Comment> comments;
}
