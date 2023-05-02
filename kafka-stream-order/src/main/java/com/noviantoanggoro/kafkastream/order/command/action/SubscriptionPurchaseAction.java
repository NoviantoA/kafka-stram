package com.noviantoanggoro.kafkastream.order.command.action;

import com.noviantoanggoro.kafkastream.order.api.request.SubscriptionPurchaseRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.SubscriptionPurchaseMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.SubscriptionPurchaseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionPurchaseAction {

	@Autowired
	private SubscriptionPurchaseProducer producer;

	public void publishToKafka(SubscriptionPurchaseRequest request) {
		var message = new SubscriptionPurchaseMessage();

		message.setSubscriptionNumber(request.getSubscriptionNumber());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}

}
