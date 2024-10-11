package com.inventory;

import com.inventory.repository.InventoryRepository;
import com.inventory.repository.entity.ItemEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
