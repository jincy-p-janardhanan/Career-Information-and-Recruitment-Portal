package com.cirp.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CareerInformationAndRecruitmentPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerInformationAndRecruitmentPortalApplication.class, args);
	}
	
	/*
	@Bean
	public FilterRegistrationBean JwtFiltering() {
		
		final FilterRegistrationBean registration_bean = new FilterRegistrationBean();
		registration_bean.setFilter(new JwtFilter());
		
		registration_bean.addUrlPatterns("/index");
		
		
		return registration_bean;
		
	}
	*/
	
	
}
