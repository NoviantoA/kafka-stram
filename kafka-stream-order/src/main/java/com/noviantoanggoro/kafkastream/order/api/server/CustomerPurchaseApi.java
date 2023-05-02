package com.noviantoanggoro.kafkastream.order.api.server;

import com.noviantoanggoro.kafkastream.order.api.request.CustomerPurchaseMobileRequest;
import com.noviantoanggoro.kafkastream.order.api.request.CustomerPurchaseWebRequest;
import com.noviantoanggoro.kafkastream.order.api.response.PurchaseResponse;
import com.noviantoanggoro.kafkastream.order.command.service.CustomerPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/purchase")
public class CustomerPurchaseApi {

	@Autowired
	private CustomerPurchaseService service;

	@PostMapping(value = "/mobile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseResponse> createPurchaseMobile(@RequestBody CustomerPurchaseMobileRequest request) {
		var purchaseNumber = service.createPurchaseMobile(request);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new PurchaseResponse(purchaseNumber));
	}

	@PostMapping(value = "/web", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseResponse> createPurchaseWeb(@RequestBody CustomerPurchaseWebRequest request) {
		var purchaseNumber = service.createPurchaseWeb(request);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new PurchaseResponse(purchaseNumber));
	}

}