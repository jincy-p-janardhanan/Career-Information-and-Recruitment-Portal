package com.cirp.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cirp.app.model.ChatChannel;

public interface ChatChannelRepo extends MongoRepository<ChatChannel, String>{

	List<ChatChannel> findByUser1(String username);
	List<ChatChannel> findByUser2(String username);

}
