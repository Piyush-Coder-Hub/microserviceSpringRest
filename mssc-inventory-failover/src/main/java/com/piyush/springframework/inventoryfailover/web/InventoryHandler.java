package com.piyush.springframework.inventoryfailover.web;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.piyush.springframework.inventoryfailover.model.BeerInventoryDto;

import reactor.core.publisher.Mono;

@Component
public class InventoryHandler {

	public Mono<ServerResponse> listInventory(ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(Mono.just(BeerInventoryDto.builder()
						.id(UUID.randomUUID())
						.beerId(UUID.fromString("00000-0000-0000-0000-0000000000"))
						.quantityOnHand(999)
						.createdDate(OffsetDateTime.now())
						.lastModifiedDate(OffsetDateTime.now())
						.build()),BeerInventoryDto.class);
	}

}
