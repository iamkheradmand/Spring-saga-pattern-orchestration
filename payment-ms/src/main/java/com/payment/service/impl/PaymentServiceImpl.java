package com.payment.service.impl;

import com.saga.orchestration.common.common.RabbitConstants;
import com.saga.orchestration.common.enums.PaymentStatus;
import com.saga.orchestration.common.enums.Status;
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
		paymentRepository.save(mapper.toPaymentEntity(model, PaymentStatus.PAYMENT_APPROVED));
		sendPaymentResultMessage(model, Status.SUCCESSFUL);
	}

	private void sendPaymentResultMessage(PaymentServiceModel model, Status status) {
		rabbitTemplate.convertAndSend(RabbitConstants.paymentExchange, RabbitConstants.paymentResultRouteKey,
				mapper.toPaymentResult(model, status));
	}


}
