package com.payment.lisener;

import com.saga.orchestration.common.common.RabbitConstants;
import com.saga.orchestration.common.dto.PaymentRequest;
import com.payment.mapper.PaymentServiceMapper;
import lombok.AllArgsConstructor;
import com.payment.service.PaymentService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentListener {

	private final PaymentService paymentService;

	private final PaymentServiceMapper mapper;

	@RabbitListener(id = "rabbit-payment-listener", queues = RabbitConstants.paymentQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveCheckMessage(PaymentRequest message) {
		System.out.println("rabbit-payment-listener: " + message + " in thread: " + Thread.currentThread().getName());
		paymentService.doPayment(mapper.toPaymentServiceModel(message));
	}

}
