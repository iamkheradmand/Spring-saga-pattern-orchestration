package com.saga.orchestration.inventory.service.impl;

import com.saga.orchestration.inventory.repository.entity.ItemEntity;
import com.saga.orchestration.inventory.service.DistributedLockService;
import com.saga.orchestration.inventory.service.OptimisticLockService;
import com.saga.orchestration.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import com.saga.orchestration.common.common.RabbitConstants;
import com.saga.orchestration.common.enums.InventoryStatus;
import com.saga.orchestration.common.enums.Status;
import com.saga.orchestration.inventory.mapper.ItemServiceMapper;
import com.saga.orchestration.inventory.repository.InventoryRepository;
import com.saga.orchestration.inventory.service.model.ItemServiceModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	private final OptimisticLockService optimisticLockService;

	private final DistributedLockService distributedLockService;

	private final ItemServiceMapper mapper;

	private final RabbitTemplate rabbitTemplate;


	@Override
	@Transactional
	public void checkProductInventory(ItemServiceModel model) {
		boolean result = distributedLockService.isInventorySufficient(model.getProductId());
		log.info("checkProductInventory result : " + result);
		if (result) {
			sendCheckResultMessage(model, InventoryStatus.AVAILABLE);
		} else {
			sendCheckResultMessage(model, InventoryStatus.UNAVAILABLE);
		}
	}

	@Override
	@Transactional
	public void reverseProductInventory(ItemServiceModel model) {
		ItemEntity item = inventoryRepository.findByProductId(model.getProductId());
		incrementItemCount(item);
		sendReverseResultMessage(model, Status.SUCCESSFUL);
	}

	private void sendCheckResultMessage(ItemServiceModel model, InventoryStatus inventoryStatus) {
		rabbitTemplate.convertAndSend(RabbitConstants.inventoryExchange, RabbitConstants.inventoryCheckResultRouteKey,
				mapper.toInventoryCheckResult(model, inventoryStatus));
	}

	private void sendReverseResultMessage(ItemServiceModel model, Status status) {
		rabbitTemplate.convertAndSend(RabbitConstants.inventoryExchange, RabbitConstants.inventoryCheckResultRouteKey,
				mapper.toInventoryReverseResult(model, status));
	}

	private void incrementItemCount(ItemEntity item){
		item.setCount(item.getCount() + 1);
		inventoryRepository.save(item);
	}

}
