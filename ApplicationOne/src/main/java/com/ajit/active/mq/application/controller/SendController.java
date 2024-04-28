package com.ajit.active.mq.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ajit.active.mq.application.Service.SendService;

@RestController
public class SendController {

	@Autowired
	private SendService sendService;
	
	 @GetMapping("/queue/{message}")
	    public String sendMessage(@PathVariable String message) {
	        // Sending message to queue1
	        sendService.sendToQueue1(message);
	        // Sending message to queue2
//	        sendService.sendToQueue2(message);
	        return message;
	    }
}
