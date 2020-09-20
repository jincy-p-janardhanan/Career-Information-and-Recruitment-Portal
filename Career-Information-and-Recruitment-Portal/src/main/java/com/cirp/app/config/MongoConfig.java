/**
 * This class configures MongoDB port and database to be used.
 * 
 * Reference: https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.instantiating
 */
package com.cirp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author Jincy P Janardhanan
 *
 */
@Configuration
public class MongoConfig {
	
	@Bean
	public MongoClient mongoClient() {
		//Create a MongoDB client on localhost port 27017
	    return MongoClients.create("mongodb://localhost:27017");
	}
	
	@Bean
	public MongoTemplate mongoTemplate() {
		//This bean allows MongoTemplate to automatically connect to the database for Career Information and Recruitment Portal (cirp)
		return new MongoTemplate(mongoClient(), "cirp");
	}
}

