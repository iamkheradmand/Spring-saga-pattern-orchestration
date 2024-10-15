package com.saga.orchestration.common.enums;

import com.saga.orchestration.common.common.RabbitConstants;

public enum RabbitMqRouting {
    INVENTORY_CHECK(RabbitConstants.inventoryExchange, RabbitConstants.inventoryCheckRouteKey),
    NOTIFICATION(RabbitConstants.notificationExchange, RabbitConstants.notificationRouteKey),
    PAYMENT(RabbitConstants.paymentExchange, RabbitConstants.paymentRouteKey),
    ORDER_CANCEL(RabbitConstants.orderExchange, RabbitConstants.orderCancelRouteKey),
    ORDER_COMPLETE(RabbitConstants.orderExchange, RabbitConstants.orderCompleteRouteKey),
    INVENTORY_REVERSE(RabbitConstants.inventoryExchange, RabbitConstants.inventoryReverseRouteKey);

    private final String exchange;
    private final String routingKey;

    RabbitMqRouting(String exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}