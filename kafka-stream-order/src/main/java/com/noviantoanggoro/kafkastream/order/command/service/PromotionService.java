package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.PromotionRequest;
import com.noviantoanggoro.kafkastream.order.command.action.PromotionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PromotionService {

	@Autowired
	private PromotionAction action;

	public void createPromotion(PromotionRequest request) {
		action.publishToKafka(request);
	}

}
