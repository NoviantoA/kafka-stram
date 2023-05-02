package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.CustomerPurchaseMobileRequest;
import com.noviantoanggoro.kafkastream.order.api.request.CustomerPurchaseWebRequest;
import com.noviantoanggoro.kafkastream.order.command.action.CustomerPurchaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerPurchaseService {

	@Autowired
	private CustomerPurchaseAction action;

	public String createPurchaseMobile(CustomerPurchaseMobileRequest request) {
		return action.publishMobileToKafka(request);
	}

	public String createPurchaseWeb(CustomerPurchaseWebRequest request) {
		return action.publishWebToKafka(request);
	}
}
