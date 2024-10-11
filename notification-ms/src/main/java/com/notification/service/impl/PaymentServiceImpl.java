package com.notification.service.impl;

import com.notification.mapper.PaymentServiceMapper;
import com.notification.service.model.NotificationServiceModel;
import lombok.RequiredArgsConstructor;
import com.notification.service.NotificationService;

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
