package piyush.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.services.inventory.modal.BeerInventoryDto;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

	private final String INVENTORY_PATH = "api/v1/beer/{beerId}/inventory";
	
	//@Autowired
	private final  RestTemplate restTemplate;

	@Setter
	private String beerInventoryServiceHost;
	
	BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Integer getonHandInventory(UUID beerId) {
		log.debug("Calling Invrntory Service");

		ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
				beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BeerInventoryDto>>() {
				}, beerId);

		Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
				.mapToInt(BeerInventoryDto::getQuantityOnHand).sum();
		log.debug(onHand.toString());
		return onHand;
	}

}
