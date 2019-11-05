package com.pingedapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pingedapp.appmodels.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>{
	
}
