package com.saga.orchestration.common.dto;

import java.io.Serializable;

import com.saga.orchestration.common.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InventoryReverseResult implements Serializable {
	private Integer userId;
	private Integer productId;
	private String orderId;
	private Status status;
}
