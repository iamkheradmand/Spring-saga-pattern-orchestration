package com.saga.orchestration.inventory.service.impl;

import com.saga.orchestration.inventory.repository.InventoryRepository;
import com.saga.orchestration.inventory.repository.entity.ItemEntity;
import com.saga.orchestration.inventory.service.OptimisticLockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OptimisticLockServiceImpl implements OptimisticLockService {

	private final InventoryRepository inventoryRepository;

	@Override
	@Retryable(
			value = { OptimisticLockingFailureException.class},
			maxAttempts = 3,
			backoff = @Backoff(delay = 200))
	public boolean isInventorySufficient(Integer productId){
		log.info("going to check inventory : " + Thread.currentThread().getName());
		ItemEntity item = inventoryRepository.findByProductId(productId);
		if (item.getCount() > 0) {
			decrementItemCount(item);
			return true;
		}
		return false;
	}


	@Recover
	public boolean recover(OptimisticLockingFailureException e) {
		System.out.println("Retries exhausted. Returning false." + e);
		return false;
	}

	private void decrementItemCount(ItemEntity item){
		item.setCount(item.getCount() - 1);
		inventoryRepository.save(item);
	}

}
