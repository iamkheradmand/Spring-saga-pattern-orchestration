package com.common.dto;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PaymentRequest implements Serializable {
	private Integer userId;
	private Integer productId;
	private String orderId;
}
