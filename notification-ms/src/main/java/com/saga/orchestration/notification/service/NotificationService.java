package com.saga.orchestration.notification.service;

import com.saga.orchestration.notification.service.model.NotificationServiceModel;

public interface NotificationService {
	void sendNotification(NotificationServiceModel model);

}
