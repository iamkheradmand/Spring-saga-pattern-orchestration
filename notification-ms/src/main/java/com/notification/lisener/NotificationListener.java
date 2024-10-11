package com.notification.lisener;

import com.common.common.RabbitConstants;
import com.common.dto.NotificationRequest;
import com.notification.mapper.PaymentServiceMapper;
import lombok.AllArgsConstructor;
import com.notification.service.NotificationService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationListener {

	private final NotificationService notificationService;

	private final PaymentServiceMapper mapper;

	@RabbitListener(id = "rabbit-notification-listener", queues = RabbitConstants.notificationQueue, containerFactory = "rabbitListenerContainerFactory")
	public void receiveCheckMessage(NotificationRequest message) {
		System.out.println("rabbit-notification-listener: " + message + " in thread: " + Thread.currentThread().getName());
		notificationService.sendNotification(mapper.toNotificationServiceModel(message));
	}

}
