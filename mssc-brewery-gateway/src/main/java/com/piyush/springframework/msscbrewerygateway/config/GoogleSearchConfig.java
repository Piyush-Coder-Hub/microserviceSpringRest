package com.piyush.springframework.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 
 * @author Piyush
 *
 */
//@Profile("google")
//@Configuration
public class GoogleSearchConfig {

	@Bean
	public RouteLocator googleConfig(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/googleSearch2")
				.filters(f-> f.rewritePath("/googleSearch2(?<segment>/?.*)", "/${segment}"))
				.uri("https://google.com")
				.id("google"))
				.build();

	}

}
