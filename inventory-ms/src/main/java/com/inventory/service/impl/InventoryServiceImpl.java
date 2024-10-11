package com.inventory.service.impl;

import com.inventory.repository.entity.ItemEntity;
import com.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import com.common.common.RabbitConstants;
import com.common.enums.InventoryStatus;
import com.common.enums.Status;
import com.inventory.mapper.ItemServiceMapper;
import com.inventory.repository.InventoryRepository;
import com.inventory.service.model.ItemServiceModel;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	private final ItemServiceMapper mapper;

	private final RabbitTemplate rabbitTemplate;

	@Override
	@Transactional
	public void checkOrder(ItemServiceModel model) {
		ItemEntity item = inventoryRepository.findByProductId(model.getProductId());
		if (item.getCount() > 0) {
			decrementItemCount(item);
			sendCheckResultMessage(model, InventoryStatus.AVAILABLE);
		} else {
			sendCheckResultMessage(model, InventoryStatus.UNAVAILABLE);
		}
	}
	
	@Override
	@Transactional
	public void reverseOrder(ItemServiceModel model) {
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

	private void decrementItemCount(ItemEntity item){
		item.setCount(item.getCount() - 1);
		inventoryRepository.save(item);
	}

}
