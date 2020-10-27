package piyush.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.services.inventory.modal.BeerInventoryDto;

@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService {

	private final InventoryServiceFeignClient inventoryServiceFeignClient;

	@Override
	public Integer getonHandInventory(UUID beerId) {
		log.debug("Calling Invrntory Service-- BeerId " + beerId);

		ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);

		Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
				.mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
		log.debug("Beer Id: " + beerId + "On Hold is : " + onHand.toString());
		return onHand;
	}

}
