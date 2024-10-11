package com.orchestrator.lisener;

import com.common.dto.PaymentResult;
import com.orchestrator.service.OrchestrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.common.common.RabbitConstants.paymentResultQueue;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentListener {

	private final OrchestrationService service;

	@RabbitListener(id = "rabbit-payment-result-listener", queues = paymentResultQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receivePaymentResultMessage(PaymentResult message) {
		log.info("going to payment-result-listener: {}", message);
		service.paymentResultEvent(message);
		System.out.println("rabbit-payment-result-listener: " + message + " in thread: " + Thread.currentThread().getName());
	}

}
