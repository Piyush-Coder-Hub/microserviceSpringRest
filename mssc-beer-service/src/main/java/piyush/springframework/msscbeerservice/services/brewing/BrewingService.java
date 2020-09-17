package piyush.springframework.msscbeerservice.services.brewing;

import static piyush.springframework.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.repositories.BeerRepository;
import piyush.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import piyush.springframework.msscbeerservice.web.mappers.BeerMapper;

/**
 * 
 * @author Piyush
 *
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

	private final BeerRepository beerRepository;

	private final BeerInventoryService beerInventoryService;

	private final JmsTemplate jmsTemplate;

	private final BeerMapper beerMapper;

	@Scheduled(fixedRate = 120000)
	public void checkForLowInventory() {

		List<Beer> beerList = beerRepository.findAll();

		beerList.forEach(beer -> {
			Integer invQuatityInHand = beerInventoryService.getonHandInventory(beer.getId());
			log.debug("Min beer In hand :" + beer.getMinOnHand());
			log.debug("Inventory is : " + invQuatityInHand);

			if (beer.getMinOnHand() >= invQuatityInHand) {
				jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, beerMapper.beerToBeerDto(beer));
			}
		});
	}

}
