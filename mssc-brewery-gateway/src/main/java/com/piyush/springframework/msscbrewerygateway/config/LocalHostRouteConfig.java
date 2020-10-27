package com.piyush.springframework.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalHostRouteConfig {

	@Bean
	public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r-> r.path("/api/v1/beer*","/api/v1/beer/*","/api/v1/beerUpc/*")
						.uri("http://localhost:8080")
						.id("beer-service"))
				.build();

	}

}
