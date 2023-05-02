package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.FlashSaleVoteRequest;
import com.noviantoanggoro.kafkastream.order.command.action.FlashSaleVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlashSaleVoteService {

	@Autowired
	private FlashSaleVoteAction action;

	public void createFlashSaleVote(FlashSaleVoteRequest request) {
		action.publishToKafka(request);
	}

}
