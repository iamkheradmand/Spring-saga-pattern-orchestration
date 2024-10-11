package com.order.controller;

import com.order.controller.dto.CreateOrderRequest;
import com.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.order.mapper.OrderServiceMapper;
import com.order.service.OrderService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

	private final OrderService orderService;

	private final OrderServiceMapper mapper;

/*
	curl --location --request POST 'http://localhost:8082/api/create-order/' \
			--header 'Content-Type: application/json' \
			--data-raw '{
			"userId":1001,
			"productId":1001001,
			"orderId":"f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"
}'
*/

	@PostMapping("/create-order/")
	public ResponseEntity<ApiResponse> createUser(@RequestBody CreateOrderRequest request) {
		log.info("going to create order: {}", request);
		orderService.createOrder(mapper.toOrderServiceModel(request));
		return ResponseEntity.ok(new ApiResponse("Ok order created"));
	}

}
