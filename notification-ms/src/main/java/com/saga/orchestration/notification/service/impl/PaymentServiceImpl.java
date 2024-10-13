package com.saga.orchestration.notification.service.impl;

import com.saga.orchestration.notification.mapper.PaymentServiceMapper;
import com.saga.orchestration.notification.service.model.NotificationServiceModel;
import lombok.RequiredArgsConstructor;
import com.saga.orchestration.notification.service.NotificationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements NotificationService {

	private final PaymentServiceMapper mapper;

	@Override
	@Transactional
	public void sendNotification(NotificationServiceModel model) {

	}

}
