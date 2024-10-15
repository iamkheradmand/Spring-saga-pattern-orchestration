package com.saga.orchestration.inventory.service;

import java.util.List;

import com.saga.orchestration.inventory.BaseContainerInitialiseTest;
import com.saga.orchestration.inventory.repository.InventoryRepository;
import com.saga.orchestration.inventory.repository.entity.ItemEntity;
import com.saga.orchestration.inventory.service.model.ItemServiceModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class InventoryServiceIT extends BaseContainerInitialiseTest {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryRepository inventoryRepository;


	@BeforeEach
	void setUp() {
		inventoryRepository.deleteAll();
	}


	@Test
	@DisplayName("check Product Inventory Async")
	void checkProductInventoryAsync() throws InterruptedException {
		initialiseDatabase();

		ItemServiceModel itemServiceModel = createSampleModel();

		int numThreads = 10;
		Thread[] threads = new Thread[numThreads];
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new Thread(() -> {
				inventoryService.checkProductInventory(itemServiceModel);
			});
			threads[i].start();
		}

		for (Thread thread : threads) {
			thread.join();
		}

		ItemEntity itemEntity = inventoryRepository.findByProductId(1001001);;
		Assertions.assertThat(itemEntity.getCount()).isZero();
	}

	private void initialiseDatabase() {
		inventoryRepository.save(createSampleEntity());
	}

	private ItemEntity createSampleEntity() {
		ItemEntity itemEntity = new ItemEntity();
		itemEntity.setProductId(1001001);
		itemEntity.setCount(5);
		return itemEntity;
	}

	private ItemServiceModel createSampleModel() {
		ItemServiceModel itemEntity = new ItemServiceModel();
		itemEntity.setProductId(1001001);
		return itemEntity;
	}

}
