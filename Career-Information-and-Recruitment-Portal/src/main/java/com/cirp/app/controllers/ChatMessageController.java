/**
 * 
 */
package com.cirp.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cirp.app.model.Message;
import com.cirp.app.repository.ChatMessageRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ChatMessageController {
	
	@Autowired
	ChatMessageRepo chatMessageRepo;

	@SuppressWarnings("unchecked")
	@PostMapping("/chat")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Message> postChat(@Valid @RequestBody Message chatMessage) {
		return (Mono<Message>) chatMessageRepo.save(chatMessage).subscribe();
	}

	@GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Message> streamMessages(@RequestParam String channelId) {
		return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
	}
}