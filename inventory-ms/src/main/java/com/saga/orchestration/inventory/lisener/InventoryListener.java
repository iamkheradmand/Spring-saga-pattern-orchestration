package com.saga.orchestration.inventory.lisener;

import com.common.dto.InventoryCheckRequest;
import com.saga.orchestration.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import com.common.dto.InventoryReverseRequest;
import com.saga.orchestration.inventory.mapper.ItemServiceMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.common.common.RabbitConstants.inventoryCheckQueue;
import static com.common.common.RabbitConstants.inventoryReverseQueue;
import static com.common.common.RabbitConstants.inventoryCheckQueue;

@Component
@AllArgsConstructor
public class InventoryListener {

	private final InventoryService inventoryService;

	private final ItemServiceMapper mapper;

	@RabbitListener(id = "rabbit-check-inventory-listener", queues = inventoryCheckQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveCheckMessage(InventoryCheckRequest message) {
		System.out.println("rabbit-inventory-update-listener: " + message + " in thread: " + Thread.currentThread().getName());
		inventoryService.checkOrder(mapper.toItemServiceModel(message));
	}

	@RabbitListener(id = "rabbit-inventory-reverse-listener", queues = inventoryReverseQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveReverseMessage(InventoryReverseRequest message) {
		System.out.println("rabbit-inventory-reverse-listener: " + message + " in thread: " + Thread.currentThread().getName());
		inventoryService.reverseOrder(mapper.toItemServiceModel(message));
	}

}
