package org.alvin.cishan.common.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class UrlCache {
	@Bean
	public Cache<String, Integer> getCache() {
		return CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.SECONDS).build();
	}
}
