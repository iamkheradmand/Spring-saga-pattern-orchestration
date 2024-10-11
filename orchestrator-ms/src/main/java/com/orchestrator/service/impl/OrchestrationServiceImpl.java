package com.orchestrator.service.impl;

import com.common.common.RabbitConstants;
import com.common.dto.InventoryCheckResult;
import com.common.dto.InventoryReverseResult;
import com.common.dto.NotificationRequest;
import com.common.dto.OrderCancelResult;
import com.common.dto.OrderCreatedRequest;
import com.common.dto.PaymentResult;
import com.common.enums.InventoryStatus;
import com.common.enums.Status;
import com.orchestrator.mapper.OrchestrationServiceMapper;
import com.orchestrator.service.OrchestrationService;
import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchestrationServiceImpl implements OrchestrationService {

	private final RabbitTemplate rabbitTemplate;

	private final OrchestrationServiceMapper mapper;

	@Override
	public void orderCreatedEvent(OrderCreatedRequest createdRequest) {
		rabbitTemplate.convertAndSend(RabbitConstants.inventoryExchange, RabbitConstants.inventoryCheckRouteKey,
				mapper.toInventoryCheckRequest(createdRequest));
	}

	@Override
	public void orderCancelStatusEvent(OrderCancelResult cancelResult) {
		rabbitTemplate.convertAndSend(RabbitConstants.notificationExchange, RabbitConstants.notificationRouteKey,
				new NotificationRequest(cancelResult.getUserId(), "Your order canceled"));
	}

	@Override
	public void inventoryCheckEvent(InventoryCheckResult checkResult) {
		if (InventoryStatus.AVAILABLE.equals(checkResult.getInventoryStatus())) {
			rabbitTemplate.convertAndSend(RabbitConstants.paymentExchange, RabbitConstants.paymentRouteKey,
					mapper.toPaymentRequest(checkResult));
		} else {
			rabbitTemplate.convertAndSend(RabbitConstants.orderExchange, RabbitConstants.orderCancelRouteKey,
					mapper.toOrderCancelRequest(checkResult));
		}
	}

	@Override
	public void inventoryReverseStatusEvent(InventoryReverseResult reverseResult) {
		rabbitTemplate.convertAndSend(RabbitConstants.orderExchange, RabbitConstants.orderCancelRouteKey,
				mapper.toOrderCancelRequest(reverseResult));
	}

	@Override
	public void paymentResultEvent(PaymentResult paymentResult) {
		if (Status.SUCCESSFUL.equals(paymentResult.getStatus())) {
			rabbitTemplate.convertAndSend(RabbitConstants.notificationExchange, RabbitConstants.notificationRouteKey,
					new NotificationRequest(paymentResult.getUserId(), "Your order completed"));

			rabbitTemplate.convertAndSend(RabbitConstants.orderExchange, RabbitConstants.orderCompleteRouteKey,
					mapper.toOrderCompleteRequest(paymentResult));
		} else {
			rabbitTemplate.convertAndSend(RabbitConstants.inventoryExchange, RabbitConstants.inventoryReverseRouteKey,
					mapper.toInventoryReverseRequest(paymentResult));
		}
	}

}
