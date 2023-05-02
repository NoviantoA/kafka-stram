package com.noviantoanggoro.kafkastream.order.command.action;

import java.time.LocalDateTime;

import com.noviantoanggoro.kafkastream.order.api.request.OnlinePaymentRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.OnlinePaymentMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.OnlinePaymentProducer;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlinePaymentAction {

	@Autowired
	private OnlinePaymentProducer producer;

	public void publishPaymentToKafka(OnlinePaymentRequest request) {
		var message = new OnlinePaymentMessage();

		message.setOnlineOrderNumber(request.getOnlineOrderNumber());
		message.setPaymentNumber("PAY-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase());
		message.setPaymentDateTime(
				request.getPaymentDateTime() == null ? LocalDateTime.now() : request.getPaymentDateTime());
		message.setPaymentMethod(request.getPaymentMethod());

		producer.publish(message);
	}

}
