/**
 * Referece: https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.instantiating
 */
package com.cirp.app;

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
public class AppConfig {

	public @Bean MongoClient mongoClient() {
	      return MongoClients.create("mongodb://localhost:27017");
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "cirp"); //Database for Career Information and Recruitment Portal (CIRP)
	}
}

