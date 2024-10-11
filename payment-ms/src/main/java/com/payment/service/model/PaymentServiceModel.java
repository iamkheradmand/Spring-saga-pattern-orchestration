package com.payment.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaymentServiceModel {
	private Integer userId;
	private Integer productId;
	private String orderId;
}