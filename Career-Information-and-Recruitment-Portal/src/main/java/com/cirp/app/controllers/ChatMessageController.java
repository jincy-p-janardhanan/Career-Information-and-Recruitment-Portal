package com.cirp.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.Message;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.ChatMessageRepo;
import com.cirp.app.repository.CirpRepository;

@Controller
public class ChatMessageController {

	@Autowired
	ChatChannelRepo chatChannelRepo;
	@Autowired
	ChatMessageRepo chatMessageRepo;

	@Autowired
	CirpRepository repo;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@PostMapping("/chat/channel")
	public String createChannel(String receiver, Model model, Authentication authentication) {
		ChatChannel channel = new ChatChannel();
		String sender = authentication.getName();
		String channelid = sender + receiver;
		channel.setId(channelid);
		channel.setUser1(sender);
		channel.setUser2(receiver);
		
		NonAdmin user1 = repo.findById(sender);
		NonAdmin user2 = repo.findById(receiver);
		
		channel.setNameUser1(user1.getName());
		channel.setNameUser2(user2.getName());
		
		chatChannelRepo.save(channel);
		return "redirect:/chat/channel/" + channelid;
	}

	@GetMapping("/chat/channel/{channelid}")
	public String getChannel(@PathVariable("channelid") String channelid, Model model, Authentication authentication) {

		ChatChannel channel = chatChannelRepo.findById(channelid).get();
		String sender, receiver;
		if (authentication.getName().equals(channel.getUser1())) {
			sender = channel.getUser1();
			receiver = channel.getUser2();
		} else {
			sender = channel.getUser2();
			receiver = channel.getUser1();
		}
		
		if(!authentication.getName().equals(sender) && !authentication.getName().equals(receiver)) {
			return "redirect:/error";
		}
		
		model.addAttribute("sender", sender);
		model.addAttribute("receiver", receiver);
		model.addAttribute("channel", channel);
		model.addAttribute("all_msgs", chatMessageRepo.findByChannelid(channelid));

		return "common/chat";
	}

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload Message chatMessage) {
		SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
		chatMessage.setTimestamp(date_format.format(new Date()));
		chatMessageRepo.save(chatMessage);
		messagingTemplate.convertAndSendToUser(chatMessage.getChannelid(),"/queue/messages", chatMessage);
	}
}