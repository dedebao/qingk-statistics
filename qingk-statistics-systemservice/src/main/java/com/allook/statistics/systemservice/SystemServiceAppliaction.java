package com.allook.statistics.systemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.allook.statistics" }) // 默认扫描该类同级下的文件及目录
@EnableDiscoveryClient
public class SystemServiceAppliaction {
	public static void main(String[] args) {
		SpringApplication.run(SystemServiceAppliaction.class, args);
	}
}