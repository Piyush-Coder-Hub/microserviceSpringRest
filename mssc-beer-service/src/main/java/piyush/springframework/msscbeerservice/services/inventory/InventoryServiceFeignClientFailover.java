package piyush.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import piyush.springframework.msscbeerservice.services.inventory.modal.BeerInventoryDto;


@Component
@RequiredArgsConstructor
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient {

	private final InventoryFailoverFeignClient failoverFeignClient;

	@Override
	public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
		return failoverFeignClient.getOnHandInventory();
	}

}
