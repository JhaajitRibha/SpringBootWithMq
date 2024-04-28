package com.ajit.active.mq.application.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendService {

	private JmsTemplate jmsTemplate;
	
	@Autowired
	public SendService(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendToQueue1(String message) {
		
              Thread t1 = new Thread(() -> {
		        JmsTemplate localJmsTemplate = new JmsTemplate(); // Create a new JmsTemplate instance for this thread
		        localJmsTemplate.setConnectionFactory(jmsTemplate.getConnectionFactory()); // Set the connection factory
		        for (int i = 0; i < 100; i++) {
		            localJmsTemplate.convertAndSend("queue", message + i + " " + Thread.currentThread().getName());
		        }
		    });

		    t1.start();

		    Thread t2 = new Thread(() -> {
		        JmsTemplate localJmsTemplate = new JmsTemplate(); // Create a new JmsTemplate instance for this thread
		        localJmsTemplate.setConnectionFactory(jmsTemplate.getConnectionFactory()); // Set the connection factory
		        for (int i = 100; i < 200; i++) {
		            localJmsTemplate.convertAndSend("queue", message + i + " " + Thread.currentThread().getName());
		        }
		    });

		    t2.start();
		}

    
	
	

    public void sendToQueue2(String message) {
        jmsTemplate.convertAndSend("queueTwo", message);
    }
}
