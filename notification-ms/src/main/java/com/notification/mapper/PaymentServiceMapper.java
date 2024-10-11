package com.notification.mapper;

import com.common.dto.NotificationRequest;
import com.notification.service.model.NotificationServiceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PaymentServiceMapper {

	NotificationServiceModel toNotificationServiceModel(NotificationRequest request);

}