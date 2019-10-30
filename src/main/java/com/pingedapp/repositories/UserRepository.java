package com.pingedapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pingedapp.appmodels.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
}
