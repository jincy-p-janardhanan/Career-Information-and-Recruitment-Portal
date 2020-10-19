package com.cirp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableReactiveMongoRepositories
public class CareerInformationAndRecruitmentPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerInformationAndRecruitmentPortalApplication.class, args);
	}

	public @Bean ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {
	    return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(), "cirp-chat");
	  }
}
