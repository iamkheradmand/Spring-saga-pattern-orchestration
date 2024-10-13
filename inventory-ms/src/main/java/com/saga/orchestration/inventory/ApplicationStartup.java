package com.saga.orchestration.inventory;

import com.saga.orchestration.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final InventoryRepository repository;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
//		ItemEntity itemEntity = new ItemEntity();
//		itemEntity.setProductId(1001001);
//		itemEntity.setCount(10);
		//repository.save(itemEntity);
		System.out.println("ApplicationStartup set itemEntity");

	}
}
