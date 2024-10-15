package com.saga.orchestration.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class NotificationRequest implements Serializable {
	private Integer userId;
	private String message;
}
