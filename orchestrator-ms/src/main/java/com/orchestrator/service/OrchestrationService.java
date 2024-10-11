package com.orchestrator.service;

import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseResult;
import com.common.dto.OrderCancelResult;
import com.common.dto.OrderCreatedRequest;
import com.common.dto.PaymentResult;

public interface OrchestrationService {

	void orderCreatedEvent(OrderCreatedRequest createdRequest);

	void orderCancelStatusEvent(OrderCancelResult cancelResult);

	void inventoryCheckEvent(InventoryCheckResult checkResult);

	void inventoryReverseStatusEvent(InventoryReverseResult reverseResult);

	void paymentResultEvent(PaymentResult paymentResult);

}
