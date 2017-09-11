package com.tech.cadt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableEurekaClient
@EnableWebMvc
@SpringBootApplication
/*@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard*/
public class ShippingService {
	public static void main(String[] args) {
		SpringApplication.run(ShippingService.class, args);
	}
}