package com.allook.statistics.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.allook.statistics.gateway.processor.MyFilterProcessor;
import com.netflix.zuul.FilterProcessor;


@SpringBootApplication
@EnableZuulProxy
public class GatewayAppliaction implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(GatewayAppliaction.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		FilterProcessor.setProcessor(new MyFilterProcessor());
	}
}