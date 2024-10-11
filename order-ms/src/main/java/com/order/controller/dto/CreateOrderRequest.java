package com.order.controller.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateOrderRequest {
	private Integer userId;
	private Integer productId;
	private String orderId;
}
