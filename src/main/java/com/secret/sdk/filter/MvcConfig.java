package com.secret.sdk.filter;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * filter 配置
 *
 * @author zkj
 * @date 2018-08-07
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(ALL)
				.allowedMethods(ALL)
				.allowedHeaders(ALL)
				.allowCredentials(true);
	}
}
