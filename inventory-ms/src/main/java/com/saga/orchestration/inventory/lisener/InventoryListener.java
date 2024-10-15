package com.saga.orchestration.inventory.lisener;

import com.saga.orchestration.common.dto.InventoryCheckRequest;
import com.saga.orchestration.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import com.saga.orchestration.common.dto.InventoryReverseRequest;
import com.saga.orchestration.inventory.mapper.ItemServiceMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.saga.orchestration.common.common.RabbitConstants.inventoryCheckQueue;
import static com.saga.orchestration.common.common.RabbitConstants.inventoryReverseQueue;

@Component
@AllArgsConstructor
@Profile("!test")
public class InventoryListener {

	private final InventoryService inventoryService;

	private final ItemServiceMapper mapper;

	@RabbitListener(id = "rabbit-check-inventory-listener", queues = inventoryCheckQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveCheckMessage(InventoryCheckRequest message) {
		System.out.println("rabbit-inventory-update-listener: " + message + " in thread: " + Thread.currentThread().getName());
		inventoryService.checkProductInventory(mapper.toItemServiceModel(message));
	}

	@RabbitListener(id = "rabbit-inventory-reverse-listener", queues = inventoryReverseQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveReverseMessage(InventoryReverseRequest message) {
		System.out.println("rabbit-inventory-reverse-listener: " + message + " in thread: " + Thread.currentThread().getName());
		inventoryService.reverseProductInventory(mapper.toItemServiceModel(message));
	}

}
