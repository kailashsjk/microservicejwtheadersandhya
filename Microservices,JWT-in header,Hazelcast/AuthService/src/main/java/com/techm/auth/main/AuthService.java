package com.techm.auth.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableEurekaClient
@EnableWebMvc
@SpringBootApplication
/*@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard*/

public class AuthService {

		
	public static void main(String[] args) {

		SpringApplication.run(AuthService.class, args);
		final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);
		String customlogger ="ADMS Loger::::";
		LOGGER.info(customlogger+"Auth Service Started....");

	}
	

	

}