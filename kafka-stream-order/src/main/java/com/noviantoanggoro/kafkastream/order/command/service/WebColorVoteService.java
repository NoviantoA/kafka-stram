package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.WebColorVoteRequest;
import com.noviantoanggoro.kafkastream.order.command.action.WebColorVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WebColorVoteService {

	@Autowired
	private WebColorVoteAction action;

	public void createColorVote(WebColorVoteRequest request) {
		action.publishToKafka(request);
	}

}
