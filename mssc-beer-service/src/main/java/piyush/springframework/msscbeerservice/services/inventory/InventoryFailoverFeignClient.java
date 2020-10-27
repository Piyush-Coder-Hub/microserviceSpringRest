package piyush.springframework.msscbeerservice.services.inventory;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import piyush.springframework.msscbeerservice.services.inventory.modal.BeerInventoryDto;

@FeignClient(name = "inventory-failover")
public interface InventoryFailoverFeignClient {

	@GetMapping(value = "/inventory-failover")
	ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();

}
