package com.noviantoanggoro.kafkastream.order.api.server;

import com.noviantoanggoro.kafkastream.order.api.request.DummyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DummyApi {

	@PostMapping(value = "/test")
	public String test(@RequestBody DummyRequest request) {
		return request.toString();
	}

}
