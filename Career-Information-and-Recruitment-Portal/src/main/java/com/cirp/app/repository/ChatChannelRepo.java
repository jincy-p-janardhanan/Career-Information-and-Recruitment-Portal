package com.cirp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.cirp.app.model.ChatChannel;

public interface ChatChannelRepo extends ReactiveMongoRepository<ChatChannel, String>{

}
