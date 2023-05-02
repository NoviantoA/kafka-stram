package com.noviantoanggoro.kafkastream.order.command.action;

import java.time.LocalDateTime;

import com.noviantoanggoro.kafkastream.order.api.request.CustomerPreferenceShoppingCartRequest;
import com.noviantoanggoro.kafkastream.order.api.request.CustomerPreferenceWishlistRequest;
import com.noviantoanggoro.kafkastream.order.broker.message.CustomerPreferenceShoppingCartMessage;
import com.noviantoanggoro.kafkastream.order.broker.message.CustomerPreferenceWishlistMessage;
import com.noviantoanggoro.kafkastream.order.broker.producer.CustomerPreferenceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerPreferenceAction {

	@Autowired
	private CustomerPreferenceProducer producer;

	public void publishShoppingCart(CustomerPreferenceShoppingCartRequest request) {
		var message = new CustomerPreferenceShoppingCartMessage(request.getCustomerId(), request.getItemName(),
				request.getCartAmount(), LocalDateTime.now());

		producer.publishShoppingCart(message);
	}

	public void publishWishlist(CustomerPreferenceWishlistRequest request) {
		var message = new CustomerPreferenceWishlistMessage(request.getCustomerId(), request.getItemName(),
				LocalDateTime.now());

		producer.publishWishlist(message);
	}

}
