package com.example.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class OrdersApplication {

	public static final String RANDOM_DROP_SHIPPING = "randomDropShipping";

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Bean
	Map<String, Boolean> featureMap() {
		Map<String, Boolean> featureMap = new ConcurrentHashMap<>();
		featureMap.put(RANDOM_DROP_SHIPPING, false);
		return featureMap;
	}

}
