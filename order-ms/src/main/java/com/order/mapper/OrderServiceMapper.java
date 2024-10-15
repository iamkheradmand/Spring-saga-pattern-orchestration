package com.order.mapper;

import com.order.controller.dto.CreateOrderRequest;
import com.saga.orchestration.common.dto.OrderCreatedRequest;
import com.order.service.model.OrderServiceModel;
import com.saga.orchestration.common.dto.OrderCancelRequest;
import com.saga.orchestration.common.dto.OrderCancelResult;
import com.saga.orchestration.common.dto.OrderCompleteRequest;
import com.order.repository.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderServiceMapper{

	OrderServiceModel toOrderServiceModel(CreateOrderRequest request);

	OrderServiceModel toOrderServiceModel(OrderCancelRequest request);

	OrderServiceModel toOrderServiceModel(OrderCompleteRequest request);

	@Mapping(target = "userId", source = "userId")
	@Mapping(target = "productId", source = "productId")
	@Mapping(target = "orderId", source = "orderId")
	@Mapping(target = "orderStatus", constant = "ORDER_CREATED")
	OrderItemEntity toOrderItemEntity(OrderServiceModel request);

	OrderCreatedRequest toOrderCreatedRequest(OrderServiceModel orderItem);

	OrderCancelResult toOrderCancelResult(OrderServiceModel model);

}