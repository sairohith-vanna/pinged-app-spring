package com.pingedapp.appdto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.pingedapp.appmodels.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPost {

	@Id
	private String postId;
	
	private String username;
	
	private String postContent;
	
	private Date postDate;
	
	private List<Comment> comments;
}
