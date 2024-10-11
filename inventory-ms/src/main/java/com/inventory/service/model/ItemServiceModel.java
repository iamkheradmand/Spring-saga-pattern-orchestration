package com.inventory.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
public class ItemServiceModel {
	private Integer userId;
	private Integer productId;
	private String orderId;
}