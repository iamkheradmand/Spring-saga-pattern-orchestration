package com.saga.orchestration.notification.mapper;

import com.saga.orchestration.common.dto.NotificationRequest;
import com.saga.orchestration.notification.service.model.NotificationServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PaymentServiceMapper {

	NotificationServiceModel toNotificationServiceModel(NotificationRequest request);

}