package com.orchestrator.mapper;

import com.saga.orchestration.common.dto.InventoryCheckRequest;
import com.saga.orchestration.common.dto.InventoryCheckResult;
import com.saga.orchestration.common.dto.InventoryReverseRequest;
import com.saga.orchestration.common.dto.InventoryReverseResult;
import com.saga.orchestration.common.dto.OrderCancelRequest;
import com.saga.orchestration.common.dto.OrderCompleteRequest;
import com.saga.orchestration.common.dto.OrderCreatedRequest;
import com.saga.orchestration.common.dto.PaymentRequest;
import com.saga.orchestration.common.dto.PaymentResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrchestrationServiceMapper {
	InventoryCheckRequest toInventoryCheckRequest(OrderCreatedRequest request);
	InventoryReverseRequest toInventoryReverseRequest(PaymentResult request);

	PaymentRequest toPaymentRequest(InventoryCheckResult result);

	OrderCompleteRequest toOrderCompleteRequest(PaymentResult result);

	OrderCancelRequest toOrderCancelRequest(InventoryCheckResult inventoryCheckResult);

	OrderCancelRequest toOrderCancelRequest(InventoryReverseResult inventoryReverseResult);

}