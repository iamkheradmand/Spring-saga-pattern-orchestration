package com.order.lisener;

import com.order.service.OrderService;
import lombok.AllArgsConstructor;
import com.saga.orchestration.common.dto.OrderCancelRequest;
import com.saga.orchestration.common.dto.OrderCompleteRequest;
import com.order.mapper.OrderServiceMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.saga.orchestration.common.common.RabbitConstants.orderCancelQueue;
import static com.saga.orchestration.common.common.RabbitConstants.orderCompleteQueue;

@Component
@AllArgsConstructor
public class OrderListener {

	private final OrderService orderService;

	private final OrderServiceMapper mapper;

	@RabbitListener(id = "rabbit-order-cancel-listener", queues = orderCancelQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveMessage(OrderCancelRequest message)
	{
		System.out.println("rabbit-order-cancel-listener: " + message + " in thread: " + Thread.currentThread().getName());
		orderService.cancelOrder(mapper.toOrderServiceModel(message));
	}

	@RabbitListener(id = "rabbit-order-complete-listener", queues = orderCompleteQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveMessage(OrderCompleteRequest message)
	{
		System.out.println("rabbit-order-complete-listener: " + message + " in thread: " + Thread.currentThread().getName());
		orderService.completeOrder(mapper.toOrderServiceModel(message));
	}

}
