package com.noviantoanggoro.kafkastream.order.command.action;

import com.noviantoanggoro.kafkastream.order.api.request.SubscriptionUserRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.SubscriptionUserMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.SubscriptionUserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SubscriptionUserAction {

	@Autowired
	private SubscriptionUserProducer producer;

	public void publishToKafka(SubscriptionUserRequest request) {
		var message = new SubscriptionUserMessage();

		message.setDuration(request.getDuration());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}

}
