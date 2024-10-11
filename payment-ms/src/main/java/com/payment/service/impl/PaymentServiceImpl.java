package com.payment.service.impl;

import com.common.common.RabbitConstants;
import com.common.enums.PaymentStatus;
import com.common.enums.Status;
import com.payment.mapper.PaymentServiceMapper;
import com.payment.service.model.PaymentServiceModel;
import com.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import com.payment.repository.PaymentRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	private final PaymentServiceMapper mapper;

	private final RabbitTemplate rabbitTemplate;

	@Override
	@Transactional
	public void doPayment(PaymentServiceModel model) {
		paymentRepository.save(mapper.toPaymentEntity(model, PaymentStatus.PAYMENT_REJECTED));
		sendPaymentResultMessage(model, Status.FAILED);
	}

	private void sendPaymentResultMessage(PaymentServiceModel model, Status status) {
		rabbitTemplate.convertAndSend(RabbitConstants.paymentExchange, RabbitConstants.paymentResultRouteKey,
				mapper.toPaymentResult(model, status));
	}


}