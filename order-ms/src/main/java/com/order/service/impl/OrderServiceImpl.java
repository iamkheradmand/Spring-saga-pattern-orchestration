package com.order.service.impl;

import com.common.common.RabbitConstants;
import com.common.dto.OrderCreatedRequest;
import com.order.mapper.OrderServiceMapper;
import com.order.service.model.OrderServiceModel;
import com.order.repository.OrderRepository;
import com.order.repository.entity.OrderItemEntity;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.common.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final RabbitTemplate rabbitTemplate;

	private final OrderServiceMapper mapper;

	@Override
	@Transactional
	public void createOrder(OrderServiceModel model) {
		OrderItemEntity orderItem = orderRepository.findByOrderId(model.getOrderId());
		if (orderItem != null)
			return;
		orderRepository.save(mapper.toOrderItemEntity(model));
		rabbitTemplate.convertAndSend(RabbitConstants.orderExchange, RabbitConstants.orderCreatedRouteKey, mapper.toOrderCreatedRequest(model));
		log.info("order created: {}", model);
	}

	@Override
	@Transactional
	public void cancelOrder(OrderServiceModel model) {
		updateOrderStatus(model.getOrderId(), OrderStatus.ORDER_CANCELLED);
		rabbitTemplate.convertAndSend(RabbitConstants.orderExchange, RabbitConstants.orderCancelResultRouteKey, mapper.toOrderCancelResult(model));
		log.info("order CANCELLED: {}", model);
	}

	@Override
	public void completeOrder(OrderServiceModel model) {
		updateOrderStatus(model.getOrderId(), OrderStatus.ORDER_COMPLETED);
		log.info("order COMPLETED: {}", model);
	}

	private void updateOrderStatus(String orderID, OrderStatus orderStatus){
		OrderItemEntity orderItem = orderRepository.findByOrderId(orderID);
		orderItem.setOrderStatus(orderStatus);
		orderRepository.save(orderItem);
		log.info("order updateOrderStatus: {}", orderItem);
	}
}
