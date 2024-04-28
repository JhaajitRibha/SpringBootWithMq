package com.ajit.active.mq.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    private List<String> messageBuffer = new ArrayList<>();

    @Scheduled(fixedRate=40000)
    public void consumeScheduledMessage() {
        System.out.println("Consuming messages from the queues at: " + LocalDateTime.now());
        receiveMessagesFromQueues();
        processBufferedMessages();
    }

    private void receiveMessagesFromQueues() {
        // Receive messages from queue1
        String messageFromQueue1 = (String) jmsTemplate.receiveAndConvert("queue");
        if (messageFromQueue1 != null) {
            System.out.println("Received message from queue: " + messageFromQueue1 + " at " + LocalDateTime.now());
            messageBuffer.add(messageFromQueue1);
        }

        // Receive messages from queueTwo
//        String messageFromQueue2 = (String) jmsTemplate.receiveAndConvert("queueTwo");
//        if (messageFromQueue2 != null) {
//            System.out.println("Received message from queueTwo: " + messageFromQueue2 + " at " + LocalDateTime.now());
//            messageBuffer.add(messageFromQueue2);
//        }
    }

    private void processBufferedMessages() {
        if (!messageBuffer.isEmpty()) {
            System.out.println("Processing buffered messages...");
            for (String message : messageBuffer) {
                processMessage(message);
            }
            messageBuffer.clear(); // Clear the buffer after processing
        } else {
            System.out.println("No messages in the buffer.");
        }
    }

    // Process the received message
    private void processMessage(String message) {
        // You can implement your message processing logic here
        System.out.println("Message processed: " + message + " at " + LocalDateTime.now());
    }
}
