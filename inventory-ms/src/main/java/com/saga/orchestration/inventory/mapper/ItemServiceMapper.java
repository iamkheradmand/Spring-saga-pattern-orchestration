package com.saga.orchestration.inventory.mapper;

import com.saga.orchestration.inventory.service.model.ItemServiceModel;
import com.saga.orchestration.common.dto.InventoryCheckRequest;
import com.saga.orchestration.common.dto.InventoryCheckResult;
import com.saga.orchestration.common.dto.InventoryReverseRequest;
import com.saga.orchestration.common.dto.InventoryReverseResult;
import com.saga.orchestration.common.enums.InventoryStatus;
import com.saga.orchestration.common.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ItemServiceMapper {
	ItemServiceModel toItemServiceModel(InventoryCheckRequest request);

	ItemServiceModel toItemServiceModel(InventoryReverseRequest request);

	@Mapping(target = "inventoryStatus", source = "inventoryStatus")
	InventoryCheckResult toInventoryCheckResult(ItemServiceModel request, InventoryStatus inventoryStatus);

	@Mapping(target = "status", source = "status")
	InventoryReverseResult toInventoryReverseResult(ItemServiceModel request, Status status);

}