package com.saga.orchestration.inventory.config;

import java.io.IOException;
import java.io.InputStream;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class RedissonConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Bean(destroyMethod = "shutdown")
	@Profile("!test")
	public RedissonClient redissonClient() throws IOException {
		ClassPathResource resource = new ClassPathResource("redisson/redisson.yaml");
		InputStream inputStream = resource.getInputStream();
		Config config = Config.fromYAML(inputStream);
		return Redisson.create(config);
	}

	@Bean(destroyMethod = "shutdown")
	@Profile("test")
	public RedissonClient redissonTestClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
		return Redisson.create(config);
	}

}
