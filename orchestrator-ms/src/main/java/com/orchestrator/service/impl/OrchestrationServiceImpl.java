package com.orchestrator.service.impl;

import com.common.common.RabbitMqSender;
import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseResult;
import com.common.dto.NotificationRequest;
import com.common.dto.OrderCancelResult;
import com.common.dto.OrderCreatedRequest;
import com.common.dto.PaymentResult;
import com.common.enums.InventoryStatus;
import com.common.enums.RabbitMqRouting;
import com.common.enums.Status;
import com.orchestrator.mapper.OrchestrationServiceMapper;
import com.orchestrator.service.OrchestrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestrationServiceImpl implements OrchestrationService {

	private final RabbitMqSender rabbitMqSender;

	private final OrchestrationServiceMapper mapper;

	@Override
	public void orderCreatedEvent(OrderCreatedRequest createdRequest) {
		sendMessage(RabbitMqRouting.INVENTORY_CHECK, mapper.toInventoryCheckRequest(createdRequest));
	}

	@Override
	public void orderCancelStatusEvent(OrderCancelResult cancelResult) {
		sendMessage(RabbitMqRouting.NOTIFICATION, new NotificationRequest(cancelResult.getUserId(), "Your order canceled"));
	}

	@Override
	public void inventoryCheckEvent(InventoryCheckResult checkResult) {
		if (InventoryStatus.AVAILABLE.equals(checkResult.getInventoryStatus())) {
			sendMessage(RabbitMqRouting.PAYMENT, mapper.toPaymentRequest(checkResult));
		} else {
			sendMessage(RabbitMqRouting.ORDER_CANCEL, mapper.toOrderCancelRequest(checkResult));
		}
	}

	@Override
	public void inventoryReverseStatusEvent(InventoryReverseResult reverseResult) {
		sendMessage(RabbitMqRouting.ORDER_CANCEL, mapper.toOrderCancelRequest(reverseResult));
	}

	@Override
	public void paymentResultEvent(PaymentResult paymentResult) {
		if (Status.SUCCESSFUL.equals(paymentResult.getStatus())) {
			sendMessage(RabbitMqRouting.NOTIFICATION, new NotificationRequest(paymentResult.getUserId(), "Your order completed"));
			sendMessage(RabbitMqRouting.ORDER_COMPLETE, mapper.toOrderCompleteRequest(paymentResult));
		} else {
			sendMessage(RabbitMqRouting.INVENTORY_REVERSE, mapper.toInventoryReverseRequest(paymentResult));
		}
	}

	private void sendMessage(RabbitMqRouting routing, Object message) {
		rabbitMqSender.send(routing, message);
	}

}
