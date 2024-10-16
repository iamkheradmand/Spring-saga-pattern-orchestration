package com.saga.orchestration.inventory.repository;

import com.saga.orchestration.inventory.repository.entity.ItemEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<ItemEntity, String> {

	ItemEntity findByProductId(Integer productId);
}