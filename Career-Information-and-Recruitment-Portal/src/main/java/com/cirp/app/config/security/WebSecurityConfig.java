/**
 * This class allows to authenticate and authorize all users.
 * 
 */
package com.cirp.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.cirp.app.service.security.CustomUserDetailsService;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		/*
		 * Hashing is an irreversible procedure. It can help to store passwords securely in our database.
		 * BCrypt is a powerful hashing algorithm used especially for encrypting passwords.
		 * It also adds some random text (called salt) to plain text before encrypting.
		 * Hence every time we encrypt a password with BCrypt, we get a different hashed string.
		 * 
		 * Quoting from this web site: https://spycloud.com/how-long-would-it-take-to-crack-your-password/
		 * Cracking a bcrypt encrypted password of medium complexity by brute force attack could take 22 years.  
		 */
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomUserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//This method helps to authenticate all users in our database.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
	
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Authorize requests for all kind of users
		http                           
	       .authorizeRequests()
	       //permitAll() allows anyone get access to the specified apis
	       
	       	.antMatchers("/", "/index", "/home", "/register", "/register-recruiter", 
	       			"/register-college", "/register-alumnus", "/terms-and-conditions",
	       			"/error", "/login" ,"/reset-password-request", "/update-password").permitAll()
	       	
	       	//hasRole() allows to authorize users to access specific apis only
	       	//Note: ROLE_ is not required while specifying the associated roles, when we use hasRole()
	       	.antMatchers("/admin/**").hasRole("ADMIN")
	       	.antMatchers("/college/**").hasRole("COLLEGE")
	       	.antMatchers("/recruiter/**").hasRole("RECRUITER")
	       	.antMatchers("/student/**").hasRole("STUDENT")
	       	.antMatchers("/alumnus/**").hasRole("ALUMNUS")
	       	.antMatchers("/pending-approval").hasRole("PENDING")
	       	.antMatchers("/common/**").hasAnyRole("ADMIN", "COLLEGE", "STUDENT", "RECRUITER", "ALUMNUS")
	       	
	       	//Specifies any request to the apis (other than those specified with permitAll() ) should be authenticated
	        .anyRequest().authenticated()
	        .and()
	        	//Specifies Spring to use a custom login form and use a custom authentication success handler
	        	.formLogin().successHandler(customizeAuthenticationSuccessHandler)
	            .loginPage("/login")
	            
	            //Redirects to login page for unsuccessful login attempts
	            //?error = true allows to display alert for invalid username or password on the html page
	            .failureUrl("/login?error=true")
	            
	         .and() 
	         	//Specifies to use http basic authorization header
	          	.httpBasic();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		//Specifies spring security to ignore security anything in - src/main/resources/static/css
        web.ignoring().antMatchers("/css/**").antMatchers("/js/**");
    }
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
}
