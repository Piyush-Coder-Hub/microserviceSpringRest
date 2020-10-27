package piyush.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import piyush.springframework.msscbeerservice.services.inventory.modal.BeerInventoryDto;

@FeignClient(name = "beer-inventory-service", fallback = InventoryServiceFeignClientFailover.class)
public interface InventoryServiceFeignClient {

	@GetMapping(value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
	ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);

}
