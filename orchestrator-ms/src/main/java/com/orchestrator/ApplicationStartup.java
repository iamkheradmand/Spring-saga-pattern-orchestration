package com.orchestrator;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final RabbitAdmin rabbitAdmin;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		rabbitAdmin.initialize();
		rabbitAdmin.purgeQueue("created-order-queue", true);
//		System.out.println("rabbitAdmin.purgeQueue");

	}
}
