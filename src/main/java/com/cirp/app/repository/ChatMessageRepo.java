package com.cirp.app.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.Message;

@Repository
public interface ChatMessageRepo extends MongoRepository<Message, String> {
	public List<Message> findByChannelid(String channelId);

	@SuppressWarnings("unchecked")
	public Message save(@Valid Message chatMessage);
}
