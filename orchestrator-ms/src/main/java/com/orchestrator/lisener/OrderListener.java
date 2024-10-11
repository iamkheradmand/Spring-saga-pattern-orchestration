package com.orchestrator.lisener;

import com.common.dto.OrderCancelResult;
import com.common.dto.OrderCreatedRequest;
import com.orchestrator.service.OrchestrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.common.common.RabbitConstants.orderCancelResultQueue;
import static com.common.common.RabbitConstants.orderCreatedQueue;

@Slf4j
@Component
@AllArgsConstructor
public class OrderListener {

	private final OrchestrationService service;

	@RabbitListener(id = "rabbit-order-created-listener", queues = orderCreatedQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveOrderCreatedMessage(OrderCreatedRequest message) {
		log.info("going to order-created-listener: {}", message);
		service.orderCreatedEvent(message);
		System.out.println("rabbit-order-created-listener: " + message + " in thread: " + Thread.currentThread().getName());
	}

	@RabbitListener(id = "rabbit-order-cancel-result-listener", queues = orderCancelResultQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveOrderCancelResultMessage(OrderCancelResult message) {
		log.info("going to order-cancel-result-listener: {}", message);
		service.orderCancelStatusEvent(message);
		System.out.println("rabbit-order-cancel-result-listener: " + message + " in thread: " + Thread.currentThread().getName());
	}

}
