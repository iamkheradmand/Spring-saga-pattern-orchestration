package com.order.repository;

import com.order.repository.entity.OrderItemEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<OrderItemEntity, String> {

	OrderItemEntity findByOrderId(String orderId);

}