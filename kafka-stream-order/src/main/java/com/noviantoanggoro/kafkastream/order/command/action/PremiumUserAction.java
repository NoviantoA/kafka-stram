package com.noviantoanggoro.kafkastream.order.command.action;

import com.noviantoanggoro.kafkastream.order.api.request.PremiumUserRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.PremiumUserMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.PremiumUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PremiumUserAction {

	@Autowired
	private PremiumUserProducer producer;

	public void publishToKafka(PremiumUserRequest request) {
		var message = new PremiumUserMessage();

		message.setUsername(request.getUsername());
		message.setLevel(request.getLevel());

		producer.publish(message);
	}

}
