package com.noviantoanggoro.kafkastream.order.command.action;

import com.noviantoanggoro.kafkastream.order.api.request.DiscountRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.DiscountMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.DiscountProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DiscountAction {

	@Autowired
	private DiscountProducer producer;

	public void publishToKafka(DiscountRequest request) {
		var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
		producer.publish(message);
	}

}
