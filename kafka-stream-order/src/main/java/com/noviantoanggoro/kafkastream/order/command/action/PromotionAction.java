package com.noviantoanggoro.kafkastream.order.command.action;

import com.noviantoanggoro.kafkastream.order.api.request.PromotionRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.PromotionMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.PromotionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PromotionAction {

	@Autowired
	private PromotionProducer producer;

	public void publishToKafka(PromotionRequest request) {
		var message = new PromotionMessage(request.getPromotionCode());

		producer.publish(message);
	}

}
