package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.SubscriptionPurchaseRequest;
import com.noviantoanggoro.kafkastream.order.command.action.SubscriptionPurchaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionPurchaseService {

	@Autowired
	private SubscriptionPurchaseAction action;

	public void createPurchase(SubscriptionPurchaseRequest request) {
		action.publishToKafka(request);
	}

}
