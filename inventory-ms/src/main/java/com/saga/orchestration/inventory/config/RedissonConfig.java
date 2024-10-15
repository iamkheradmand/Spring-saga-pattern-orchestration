package com.saga.orchestration.inventory.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RedissonConfig {
//
//	@Bean(destroyMethod = "shutdown")
//	public RedissonClient redissonClient() throws IOException {
//		Config config = Config.fromYAML(getClass().getClassLoader().getResource("redisson/redisson.yaml"));
//		return Redisson.create(config);
//	}
}
