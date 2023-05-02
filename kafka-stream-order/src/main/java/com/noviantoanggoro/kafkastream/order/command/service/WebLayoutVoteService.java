package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.WebLayoutVoteRequest;
import com.noviantoanggoro.kafkastream.order.command.action.WebLayoutVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebLayoutVoteService {

	@Autowired
	private WebLayoutVoteAction action;

	public void createLayoutVote(WebLayoutVoteRequest request) {
		action.publishToKafka(request);
	}

}
