package com.piyush.springframework.inventoryfailover.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.piyush.springframework.inventoryfailover.web.InventoryHandler;

@Configuration
public class RouteConfig {

	@Bean
	public RouterFunction inventoryRoute(InventoryHandler inventoryHandler) {
		return route(
				RequestPredicates.GET("/inventory-failover").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				inventoryHandler::listInventory);
	}

}
