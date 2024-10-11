package com.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderCompleteRequest implements Serializable {
	private Integer userId;
	private Integer productId;
	private String orderId;
}
