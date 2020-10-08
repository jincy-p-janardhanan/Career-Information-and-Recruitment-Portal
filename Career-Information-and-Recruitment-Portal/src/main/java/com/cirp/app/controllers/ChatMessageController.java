package com.cirp.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.Message;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.ChatMessageRepo;
import com.cirp.app.repository.CirpRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/chat")
public class ChatMessageController {
	
	@Autowired
	ChatChannelRepo chatChannelRepo;
	@Autowired
	ChatMessageRepo chatMessageRepo;
	
	@Autowired
	CirpRepository repo;
	
	@PostMapping("/message")
	@ResponseStatus(HttpStatus.CREATED)
	public void postChat(@Valid @RequestBody Message chatMessage){	
		 chatMessageRepo.insert(chatMessage).subscribe();
	}
	
	@PostMapping("/channel")
	public String createChannel(String receiver, Model model, Authentication authentication){
		ChatChannel channel = new ChatChannel();
		channel.setFirstUser(authentication.getName());
		channel.setSecondUser(receiver);
		Mono<ChatChannel> ch = chatChannelRepo.save(channel);
		NonAdmin user = repo.findById(receiver);
		model.addAttribute("channel", ch);
		model.addAttribute("profile_pic", user.getProfile_pic());
		model.addAttribute("name", user.getName());
		return "common/chat";
	}
	@GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Message> streamMessages(@RequestParam String channelId){
		return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
	}
}