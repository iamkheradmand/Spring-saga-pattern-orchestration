package com.inventory.service;

import com.inventory.service.model.ItemServiceModel;

public interface InventoryService {
	void checkOrder(ItemServiceModel model);

	void reverseOrder(ItemServiceModel model);

}
