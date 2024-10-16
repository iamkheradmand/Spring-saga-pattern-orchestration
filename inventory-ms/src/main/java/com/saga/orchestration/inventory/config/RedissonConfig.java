package com.saga.orchestration.inventory.config;

import java.io.IOException;
import java.io.InputStream;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class RedissonConfig {

	@Bean(destroyMethod = "shutdown")
	public RedissonClient redissonClient() throws IOException {
		ClassPathResource resource = new ClassPathResource("redisson/redisson.yaml");
		InputStream inputStream = resource.getInputStream();
		Config config = Config.fromYAML(inputStream);
		return Redisson.create(config);
	}

}
