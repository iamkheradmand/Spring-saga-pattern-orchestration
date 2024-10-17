package com.saga.orchestration.inventory.base;

import com.redis.testcontainers.RedisContainer;
import com.saga.orchestration.inventory.base.BaseContainerInitialiseTest.TestRabbitConfig;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestRabbitConfig.class)
@ExtendWith({ SpringExtension.class })
@TestPropertySource({"classpath:application-test.properties"})
@Testcontainers
public class BaseContainerInitialiseTest {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:3.6.3"));

	@Container
	static RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8.2-management-alpine");

	@Container
	static RedisContainer redisContainer = new RedisContainer("redis:alpine");


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
		registry.add("spring.data.mongodb.database", () -> "testdb");
		registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
		registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
		registry.add("spring.rabbitmq.username", rabbitMQContainer::getAdminUsername);
		registry.add("spring.rabbitmq.password", rabbitMQContainer::getAdminPassword);
		registry.add("spring.rabbitmq.password", rabbitMQContainer::getAdminPassword);
		registry.add("spring.redis.host", redisContainer::getHost);
		registry.add("spring.redis.port", redisContainer::getRedisPort);
	}

	@RequiredArgsConstructor
	public static class TestRabbitConfig {

		@Bean
		public CacheManager inMemoryCacheManager() {
			return new NoOpCacheManager();
		}

		@Bean
		public DirectExchange directExchange() {
			return new DirectExchange("test_exchange");
		}

		@Bean
		public Queue testQueue() {
			return new Queue("test_queue", false);
		}

		@Bean
		public Binding bindingTestQueue() {
			return BindingBuilder.bind(testQueue())
					.to(directExchange())
					.with("test_queue");
		}
	}

}
