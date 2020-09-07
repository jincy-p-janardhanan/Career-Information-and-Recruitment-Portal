package com.cirp.app;

/*import java.util.List;

import org.bson.types.ObjectId;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
*/
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CareerInformationAndRecruitmentPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerInformationAndRecruitmentPortalApplication.class, args);
	}

/*	
	@Configuration
	public static class WebAppConfig extends WebMvcConfigurationSupport {

		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
			builder.serializerByType(ObjectId.class, new ToStringSerializer());
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
			converters.add(converter);
		}

	}
	
	@Bean
	public ViewResolver ResolveViews() {
		
		ClassLoaderTemplateResolver template_resolver = new ClassLoaderTemplateResolver();
		template_resolver.setTemplateMode("HTML");
		template_resolver.setPrefix("templates/");
		template_resolver.setSuffix(".html");
		
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(template_resolver);
		
		ThymeleafViewResolver view_resolver = new ThymeleafViewResolver();
		view_resolver.setTemplateEngine(engine);
		
		return view_resolver;
	}
	
	@Bean
	public FilterRegistrationBean JwtFiltering() {
		
		final FilterRegistrationBean registration_bean = new FilterRegistrationBean();
		registration_bean.setFilter(new JwtFilter());
		
		registration_bean.addUrlPatterns("/*");
		
		
		return registration_bean;
		
	}
	
*/
}
