package com.payment.repository.entity;

import com.saga.orchestration.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
@AllArgsConstructor
@Getter
@Setter
public class PaymentEntity {

	@Id
	private String id;

	private Integer userId;

	private Integer productId;

	private String orderId;

	private PaymentStatus paymentStatus;

}