package com.secret.sdk;

import com.secret.sdk.filter.RepeatedReadRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		RepeatedReadRequestFilter repeatedReadRequestFilter = new RepeatedReadRequestFilter();
		registrationBean.setFilter(repeatedReadRequestFilter);
		List<String> urlPatterns = new ArrayList<>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}


}
