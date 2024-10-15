package com.saga.orchestration.common.common;

public class RabbitConstants {

	//order
	public static final String orderExchange = "order-exchange";
	public static final String orderCreatedQueue = "created-order-queue";
	public static final String orderCompleteQueue = "complete-order-queue";
	public static final String orderCancelQueue = "cancel-order-queue";
	public static final String orderCancelResultQueue = "cancel-result-order-queue";
	public static final String orderCreatedRouteKey = "created-order-rk";
	public static final String orderCancelRouteKey = "cancel-order-rk";
	public static final String orderCompleteRouteKey = "complete-order-rk";
	public static final String orderCancelResultRouteKey = "cancel-result-order-rk";

	//inventory
	public static final String inventoryExchange = "inventory-exchange";
	public static final String inventoryCheckQueue = "check-inventory-queue";
	public static final String inventoryCheckResultQueue = "check-inventory-result-queue";
	public static final String inventoryReverseQueue = "reverse-inventory-queue";
	public static final String inventoryReverseResultQueue = "reverse-result-inventory-queue";
	public static final String inventoryCheckRouteKey = "check-inventory-rk";
	public static final String inventoryCheckResultRouteKey = "check-inventory-result-rk";
	public static final String inventoryReverseRouteKey = "reverse-inventory-rk";
	public static final String inventoryReverseResultRouteKey = "reverse-result-inventory-rk";


	//payment
	public static final String paymentExchange = "payment-exchange";
	public static final String paymentQueue = "payment-queue";
	public static final String paymentResultQueue = "payment-result-queue";
	public static final String paymentRouteKey = "payment-rk";
	public static final String paymentResultRouteKey = "payment-result-rk";

	//notification
	public static final String notificationExchange = "notification-exchange";
	public static final String notificationQueue = "notification-queue";
	public static final String notificationRouteKey = "notification-rk";

}
