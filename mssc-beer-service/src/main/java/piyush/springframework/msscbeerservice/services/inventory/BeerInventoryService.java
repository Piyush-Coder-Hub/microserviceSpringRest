package piyush.springframework.msscbeerservice.services.inventory;

import java.util.UUID;

public interface BeerInventoryService {
	Integer getonHandInventory(UUID beerId);
}