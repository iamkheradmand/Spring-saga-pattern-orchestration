package com.orchestrator.config;

import com.common.common.RabbitConstants;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Configuration
	public static class OrderConfig {

		@Bean
		public DirectExchange orderExchange() {
			return new DirectExchange(RabbitConstants.orderExchange);
		}

		@Bean
		public Queue OrderCreatedQueue() {
			return new Queue(RabbitConstants.orderCreatedQueue, false);
		}

		@Bean
		public Queue cancelOrderQueue() {
			return new Queue(RabbitConstants.orderCancelQueue, false);
		}

		@Bean
		public Queue cancelOrderResultQueue() {
			return new Queue(RabbitConstants.orderCancelResultQueue, false);
		}


		@Bean
		public Queue completeOrderQueue() {
			return new Queue(RabbitConstants.orderCompleteQueue, false);
		}

		@Bean
		public Binding bindingOrderCreatedQueue() {
			return BindingBuilder.bind(OrderCreatedQueue())
					.to(orderExchange())
					.with(RabbitConstants.orderCreatedRouteKey);
		}

		@Bean
		public Binding bindingCancelOrderQueue() {
			return BindingBuilder.bind(cancelOrderQueue())
					.to(orderExchange())
					.with(RabbitConstants.orderCancelRouteKey);
		}

		@Bean
		public Binding bindingCancelOrderResultQueue() {
			return BindingBuilder.bind(cancelOrderResultQueue())
					.to(orderExchange())
					.with(RabbitConstants.orderCancelResultRouteKey);
		}

		@Bean
		public Binding bindingCompleteOrderQueue() {
			return BindingBuilder.bind(completeOrderQueue())
					.to(orderExchange())
					.with(RabbitConstants.orderCompleteRouteKey);
		}
	}

	@Configuration
	public static class InventoryConfig {
		//inventory
		@Bean
		public DirectExchange inventoryExchange() {
			return new DirectExchange(RabbitConstants.inventoryExchange);
		}

		@Bean
		public Queue checkInventoryQueue() {
			return new Queue(RabbitConstants.inventoryCheckQueue, false);
		}

		@Bean
		public Queue checkInventoryResultQueue() {
			return new Queue(RabbitConstants.inventoryCheckResultQueue, false);
		}

		@Bean
		public Queue reverseInventoryQueue() {
			return new Queue(RabbitConstants.inventoryReverseQueue, false);
		}

		@Bean
		public Queue reverseInventoryResultQueue() {
			return new Queue(RabbitConstants.inventoryReverseResultQueue, false);
		}

		@Bean
		public Binding bindingCheckInventoryQueue() {
			return BindingBuilder.bind(checkInventoryQueue())
					.to(inventoryExchange())
					.with(RabbitConstants.inventoryCheckRouteKey);
		}

		@Bean
		public Binding bindingCheckInventoryResultQueue() {
			return BindingBuilder.bind(checkInventoryResultQueue())
					.to(inventoryExchange())
					.with(RabbitConstants.inventoryCheckResultRouteKey);
		}

		@Bean
		public Binding bindingReverseInventoryQueue() {
			return BindingBuilder.bind(reverseInventoryQueue())
					.to(inventoryExchange())
					.with(RabbitConstants.inventoryReverseRouteKey);
		}

		@Bean
		public Binding bindingReverseInventoryResultQueue() {
			return BindingBuilder.bind(reverseInventoryResultQueue())
					.to(inventoryExchange())
					.with(RabbitConstants.inventoryReverseResultRouteKey);
		}
	}

	@Configuration
	public static class PaymentConfig {
		//payment
		@Bean
		public DirectExchange paymentExchange() {
			return new DirectExchange(RabbitConstants.paymentExchange);
		}

		@Bean
		public Queue paymentQueue() {
			return new Queue(RabbitConstants.paymentQueue, false);
		}

		@Bean
		public Queue paymentResultQueue() {
			return new Queue(RabbitConstants.paymentResultQueue, false);
		}

		@Bean
		public Binding bindingPaymentQueue() {
			return BindingBuilder.bind(paymentQueue())
					.to(paymentExchange())
					.with(RabbitConstants.paymentRouteKey);
		}

		@Bean
		public Binding bindingPaymentResultQueue() {
			return BindingBuilder.bind(paymentResultQueue())
					.to(paymentExchange())
					.with(RabbitConstants.paymentResultRouteKey);
		}
	}

	@Configuration
	public static class NotificationConfig {
		//notification
		@Bean
		public DirectExchange notificationExchange() {
			return new DirectExchange(RabbitConstants.notificationExchange);
		}

		@Bean
		public Queue notificationQueue() {
			return new Queue(RabbitConstants.notificationQueue, false);
		}

		@Bean
		public Binding bindingNotificationQueue() {
			return BindingBuilder.bind(notificationQueue())
					.to(notificationExchange())
					.with(RabbitConstants.notificationRouteKey);
		}
	}

}
