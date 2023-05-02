package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.DiscountRequest;
import com.noviantoanggoro.kafkastream.order.command.action.DiscountAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

	@Autowired
	private DiscountAction action;

	public void createDiscount(DiscountRequest request) {
		action.publishToKafka(request);
	}

}
