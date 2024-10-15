package com.order.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.saga.orchestration.common.enums.OrderStatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orderitems")
@AllArgsConstructor
@Getter
@Setter
public class OrderItemEntity {
	@Id
	private String id;

	private Integer userId;

	private Integer productId;

	private String orderId;

	private OrderStatus orderStatus;
}