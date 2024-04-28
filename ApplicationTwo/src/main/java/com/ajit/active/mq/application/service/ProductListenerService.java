package com.ajit.active.mq.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ajit.active.mq.application.productEntities.Product;
import com.ajit.active.mq.application.productRepositories.ProductRepository;

@Service
public class ProductListenerService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	ProductRepository productRepo;

	private List<Map<String,String>> messageBuffer = new ArrayList<>();

	@Scheduled(fixedRate = 60000)
	public void consumeScheduledMessage() {
		System.out.println("Consuming messages from the *****product*****  queues at: " + LocalDateTime.now());
		receiveMessagesFromQueues();
		processBufferedMessages();
	}

	private void receiveMessagesFromQueues() {
		// Receive messages from queue1
		@SuppressWarnings("unchecked")
		Map<String,String> messageFromProductQueue = (Map<String, String>) jmsTemplate.receiveAndConvert("product_messager_queue");
		if (messageFromProductQueue != null) {
			System.out.println("Received message from queue: " + messageFromProductQueue + " at " + LocalDateTime.now());
			messageBuffer.add(messageFromProductQueue);
		}

	}

	private void processBufferedMessages() {
		if (!messageBuffer.isEmpty()) {
			System.out.println("Processing buffered messages...");
			for (Map<String, String> message : messageBuffer) {
				processMessage(message);
			}
			messageBuffer.clear(); // Clear the buffer after processing
		} else {
			System.out.println("No messages in the buffer.");
		}
	}

	// Process the received message
	private void processMessage(Map<String, String> message) {
		// You can implement your message processing logic here
		System.out.println("Message processed: " + message + " at " + LocalDateTime.now());
		long id = Long.valueOf(message.get("id"));
		
		Product product = productRepo.findById(id).orElse(null);
		product.setMessageReceived(true);
		product.setMessageCount(product.getMessageCount()+1);
		productRepo.save(product);
	
		}
}
