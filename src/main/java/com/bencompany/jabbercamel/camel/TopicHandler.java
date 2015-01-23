package com.bencompany.jabbercamel.camel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Sends new JabberMessage's to the WebSockets Topic as JSON for the front-end
 */
@Component
public class TopicHandler {

	@Autowired
	private MessageSendingOperations<String> messagingTemplate;
	
	public void pushToTopic(JabberMessage msg) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String msgJson = om.writeValueAsString(msg);
		this.messagingTemplate.convertAndSend("/topic/jabbermessages",msgJson);
	}
}
