/**
 * 
 */
package com.cirp.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cirp.app.service.CustomUserDetailsService;

/**
 * @author Jincy P Janardhanan
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new CustomUserDetailsService();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
	    auth
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(bCryptPasswordEncoder);
    }
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .antMatchers("/server_admin_panel").hasAuthority("ADMIN").anyRequest()
	            .authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
	            .loginPage("/login").failureUrl("/login?error=true")
	            .usernameParameter("username")
	            .passwordParameter("password")
	            .and().logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout").and().exceptionHandling();
	    
	    http.csrf().disable();
	}
	
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
