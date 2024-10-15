package com.orchestrator.service;

import com.saga.orchestration.common.dto.InventoryCheckResult;
import com.saga.orchestration.common.dto.InventoryReverseResult;
import com.saga.orchestration.common.dto.OrderCancelResult;
import com.saga.orchestration.common.dto.OrderCreatedRequest;
import com.saga.orchestration.common.dto.PaymentResult;

public interface OrchestrationService {

	void orderCreatedEvent(OrderCreatedRequest createdRequest);

	void orderCancelStatusEvent(OrderCancelResult cancelResult);

	void inventoryCheckEvent(InventoryCheckResult checkResult);

	void inventoryReverseStatusEvent(InventoryReverseResult reverseResult);

	void paymentResultEvent(PaymentResult paymentResult);

}
