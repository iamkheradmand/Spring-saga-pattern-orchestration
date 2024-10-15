package com.saga.orchestration.inventory.service.impl;

import com.saga.orchestration.inventory.repository.InventoryRepository;
import com.saga.orchestration.inventory.repository.entity.ItemEntity;
import com.saga.orchestration.inventory.service.DistributedLockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DistributedLockServiceImpl implements DistributedLockService {

	private final InventoryRepository inventoryRepository;

	private final RedissonClient redissonClient;

	public boolean isInventorySufficient(Integer productId) {
		return checkWithLock(productId);
	}
	public boolean checkWithLock(Integer productId) {
		RLock lock = redissonClient.getLock("inventoryLock");
		try {
			lock.lock();
			log.info("Lock acquired, performing protected operation : " + Thread.currentThread().getName());
			ItemEntity item = inventoryRepository.findByProductId(productId);
			if (item.getCount() > 0) {
				decrementItemCount(item);
				return true;
			}
		} catch (Exception e) {
			log.info("inventoryLock Exception : " + e);
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return false;
	}

	private void decrementItemCount(ItemEntity item) {
		item.setCount(item.getCount() - 1);
		inventoryRepository.save(item);
	}

}
