package com.inventory.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory-items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemEntity {

	@Id
	private String id;

	private Integer productId;

	private Integer count;

}