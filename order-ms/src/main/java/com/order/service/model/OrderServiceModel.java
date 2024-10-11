package com.order.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
@AllArgsConstructor
public class OrderServiceModel {
	private Integer userId;

	private Integer productId;

	private String orderId;
}
