package com.orchestrator.mapper;

import com.common.dto.InventoryCheckRequest;
import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseRequest;
import com.common.dto.InventoryReverseResult;
import com.common.dto.OrderCancelRequest;
import com.common.dto.OrderCompleteRequest;
import com.common.dto.OrderCreatedRequest;
import com.common.dto.PaymentRequest;
import com.common.dto.PaymentResult;
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