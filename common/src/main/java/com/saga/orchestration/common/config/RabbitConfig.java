package com.saga.orchestration.common.config;

import com.saga.orchestration.common.common.RabbitMqSender;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class RabbitConfig {

	public RabbitConfig(){
		System.out.println("rabbit-RabbitConfig-starts");

	}

	@Bean
	@ConditionalOnMissingBean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	@ConditionalOnMissingBean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean
	public RetryOperationsInterceptor retryInterceptor(){
		return RetryInterceptorBuilder.stateless().maxAttempts(3)
				.backOffOptions(2000, 2.0, 100000)
				.recoverer(new RejectAndDontRequeueRecoverer())
				.build();
	}

	@Bean
	@ConditionalOnMissingBean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter converter) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(converter);
		factory.setAdviceChain(retryInterceptor());
		BackOff backOff = new FixedBackOff(3000, 3);
		factory.setRecoveryBackOff(backOff);
		factory.setDefaultRequeueRejected(false);
		//listenerContainerFactory.setConcurrentConsumers(3);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}

	@Bean
	@ConditionalOnMissingBean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	@ConditionalOnMissingBean
	public RabbitMqSender rabbitMqSender(RabbitTemplate rabbitTemplate) {
		return new RabbitMqSender(rabbitTemplate);
	}

}
