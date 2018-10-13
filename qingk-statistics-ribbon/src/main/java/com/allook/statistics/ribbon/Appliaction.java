package com.allook.statistics.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker
//@SpringBootApplication
//@EnableDiscoveryClient
@SpringCloudApplication  //代替上面三个注解
public class Appliaction {
	
	@Bean
	@LoadBalanced  //开启客户端负载均衡
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Appliaction.class, args);
    }
}