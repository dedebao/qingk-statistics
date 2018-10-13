package com.allook.statistics.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerAppliaction.class, args);
    }
}