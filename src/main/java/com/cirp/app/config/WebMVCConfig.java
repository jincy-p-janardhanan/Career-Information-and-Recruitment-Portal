/**
 * This configuration class registers APIs (Views) for all the HTML pages used. 
 */

package com.cirp.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jincy P Janardhanan
 *
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		/*
		 * addviewController creates API name, setViewName sets which HTML page to use.
		 * The default root folder for HTML pages is src/main/resources/templates
		 */
		
	    registry.addViewController("/home").setViewName("index");
	    registry.addViewController("/").setViewName("index");
	    registry.addViewController("/index").setViewName("index");
	    registry.addViewController("/register").setViewName("register");
	    registry.addViewController("/register-college").setViewName("register_college");
	    registry.addViewController("/register-recruiter").setViewName("register_recruiter");
	    registry.addViewController("/register-alumnus").setViewName("register_alumnus");
	    registry.addViewController("/login").setViewName("login");
	    registry.addViewController("/reset-password-request").setViewName("reset_password_request");
	    registry.addViewController("/terms-and-conditions").setViewName("terms_and_conditions");
	    registry.addViewController("/error").setViewName("error");	    
	    registry.addViewController("/pending-approval").setViewName("common/registration_pending");
	    registry.addViewController("/update-password").setViewName("common/reset_password");
	    registry.addViewController("/info/cs-it").setViewName("cs_it_info");
	    registry.addViewController("/info/ec").setViewName("ec");
	    registry.addViewController("/info/eee").setViewName("eee");
	}
}
