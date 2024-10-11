package com.inventory.mapper;

import com.inventory.service.model.ItemServiceModel;
import com.common.dto.InventoryCheckRequest;
import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseRequest;
import com.common.dto.InventoryReverseResult;
import com.common.enums.InventoryStatus;
import com.common.enums.Status;
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