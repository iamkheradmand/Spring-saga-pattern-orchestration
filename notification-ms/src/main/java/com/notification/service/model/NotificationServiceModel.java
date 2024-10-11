package com.notification.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotificationServiceModel {
	private Integer userId;
	private String message;
}