package com.order.service;

import com.order.service.model.OrderServiceModel;

public interface OrderService {
	void createOrder(OrderServiceModel model);
	void cancelOrder(OrderServiceModel model);
	void completeOrder(OrderServiceModel model);
}
