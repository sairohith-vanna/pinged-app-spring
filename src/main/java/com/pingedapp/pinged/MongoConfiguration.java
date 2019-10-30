package com.pingedapp.pinged;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration{

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoClient(), "pingedappdb");
	}
}
