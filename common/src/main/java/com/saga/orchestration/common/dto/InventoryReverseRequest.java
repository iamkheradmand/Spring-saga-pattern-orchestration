package com.saga.orchestration.common.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InventoryReverseRequest implements Serializable {
	private Integer userId;
	private Integer productId;
	private String orderId;
}
