package com.orchestrator.lisener;

import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseResult;
import com.orchestrator.service.OrchestrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.common.common.RabbitConstants.inventoryCheckResultQueue;
import static com.common.common.RabbitConstants.inventoryReverseResultQueue;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryListener {

	private final OrchestrationService service;

	@RabbitListener(id = "rabbit-inventory-check-result-listener", queues = inventoryCheckResultQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveInventoryCheckResultMessage(InventoryCheckResult message) {
		log.info("going to inventory-check-result-listener: {}", message);
		service.inventoryCheckEvent(message);
		System.out.println("rabbit-inventory-check-result-listener: " + message + " in thread: " + Thread.currentThread().getName());
	}

	@RabbitListener(id = "rabbit-inventory-reverse-result-listener", queues = inventoryReverseResultQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveInventoryReverseResultMessage(InventoryReverseResult message) {
		log.info("going to inventory-reverse-result-listener: {}", message);
		service.inventoryReverseStatusEvent(message);
		System.out.println("rabbit-inventory-reverse-result-listener: " + message + " in thread: " + Thread.currentThread().getName());
	}

}
