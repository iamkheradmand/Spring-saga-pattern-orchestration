package com.saga.orchestration.inventory.service;

import com.saga.orchestration.inventory.service.model.ItemServiceModel;

public interface InventoryService {
	void checkOrder(ItemServiceModel model);

	void reverseOrder(ItemServiceModel model);

}
