package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.CustomerPreferenceShoppingCartRequest;
import com.noviantoanggoro.kafkastream.order.api.request.CustomerPreferenceWishlistRequest;
import com.noviantoanggoro.kafkastream.order.command.action.CustomerPreferenceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerPreferenceService {

	@Autowired
	private CustomerPreferenceAction action;

	public void createShoppingCart(CustomerPreferenceShoppingCartRequest request) {
		action.publishShoppingCart(request);
	}

	public void createWishlist(CustomerPreferenceWishlistRequest request) {
		action.publishWishlist(request);
	}
}
