/**
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

import com.cirp.app.config.security.service.CustomUserDetailsService;


/**
 * @author Jincy P Janardhanan
 *
 */

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
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
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
	
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http                           
	        .authorizeRequests()
	        	.antMatchers("/", "/index", "/home", "/register", "/register-recruiter", 
	        			"/register-college", "/register-alumnus", "/terms-and-conditions",
	        			"/error", "/login").permitAll()
	        	.antMatchers("/admin-panel").hasRole("ADMIN")
	        	.antMatchers("/college-home", "/college-admin-panel").hasRole("COLLEGE")
	        	.antMatchers("/recruiter-home", "/create-job", "manage-job", "job-applications").hasRole("RECRUITER")
	        	.antMatchers("/student-home").hasRole("STUDENT")
	        	.antMatchers("/alumnus-home").hasRole("ALUMNUS")
	            .anyRequest().authenticated()
	            .and()
	            	.formLogin().successHandler(customizeAuthenticationSuccessHandler)
	            	.loginPage("/login")
	            	.failureUrl("/login?error=true")
	            .and()  
	            	.httpBasic()
	            .and()  
	            	.logout() 
	            	.logoutUrl("/")  
	            	.logoutSuccessUrl("/").permitAll()
	            .and()
	                .exceptionHandling();
	}
	
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }
}
