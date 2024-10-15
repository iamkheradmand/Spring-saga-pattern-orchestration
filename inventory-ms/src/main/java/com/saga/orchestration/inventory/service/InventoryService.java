package com.saga.orchestration.inventory.service;

import com.saga.orchestration.inventory.service.model.ItemServiceModel;

public interface InventoryService {
	void checkProductInventory(ItemServiceModel model);

	void reverseProductInventory(ItemServiceModel model);

}
