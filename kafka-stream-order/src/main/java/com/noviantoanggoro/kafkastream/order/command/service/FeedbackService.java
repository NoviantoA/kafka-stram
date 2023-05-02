package com.noviantoanggoro.kafkastream.order.command.service;

import com.noviantoanggoro.kafkastream.order.api.request.FeedbackRequest;
import com.noviantoanggoro.kafkastream.order.command.action.FeedbackAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackAction action;

	public void createFeedback(FeedbackRequest request) {
		action.publishToKafka(request);
	}

}
