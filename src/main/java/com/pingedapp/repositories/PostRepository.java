package com.pingedapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pingedapp.appmodels.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
