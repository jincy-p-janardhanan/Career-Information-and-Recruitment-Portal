/**
 * 
 */
package com.cirp.app.repository;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import com.cirp.app.model.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 *
 */

@Repository
public interface ChatMessageRepo extends ReactiveMongoRepository<Message, String> {
	@Tailable
	public Flux<Message> findWithTailableCursorByChannelId(String channelId);

	public Mono<Message> save(@Valid Message chatMessage);
}
