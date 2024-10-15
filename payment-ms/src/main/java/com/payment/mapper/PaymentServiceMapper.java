package com.payment.mapper;

import com.saga.orchestration.common.dto.PaymentRequest;
import com.saga.orchestration.common.enums.PaymentStatus;
import com.saga.orchestration.common.enums.Status;
import com.payment.service.model.PaymentServiceModel;
import com.saga.orchestration.common.dto.PaymentResult;
import com.payment.repository.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PaymentServiceMapper {

	PaymentServiceModel toPaymentServiceModel(PaymentRequest request);
	@Mapping(target = "paymentStatus", source = "paymentStatus")
	PaymentEntity toPaymentEntity(PaymentServiceModel request, PaymentStatus paymentStatus);

	@Mapping(target = "status", source = "status")
	PaymentResult toPaymentResult(PaymentServiceModel model, Status status);

}