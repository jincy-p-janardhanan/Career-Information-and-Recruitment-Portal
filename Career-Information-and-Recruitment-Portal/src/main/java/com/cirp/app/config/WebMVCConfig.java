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
	    
	    registry.addViewController("/admin-panel").setViewName("server/server_admin_panel");
	    registry.addViewController("/college-admin-panel").setViewName("college/college_admin_panel");
	    
	    registry.addViewController("/college-home").setViewName("college/home_college");
	    registry.addViewController("/recruiter-home").setViewName("recruiter/home_recruiter");
	    registry.addViewController("/student-home").setViewName("student/home_student");
	    registry.addViewController("/alumnus-home").setViewName("alumnus/home_alumnus");
	    
	    registry.addViewController("/create-job").setViewName("recruiter/create_job");
	    registry.addViewController("/job-applications").setViewName("recruiter/job_applications");
	    registry.addViewController("/manage-job").setViewName("recruiter/manage_job");
	    
	    registry.addViewController("/pending-approval").setViewName("common/registration_pending");
	    registry.addViewController("/update-password").setViewName("common/reset_password");
	}
}
