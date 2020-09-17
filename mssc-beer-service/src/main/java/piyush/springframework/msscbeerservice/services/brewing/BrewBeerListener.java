package piyush.springframework.msscbeerservice.services.brewing;

import static piyush.springframework.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;
import static piyush.springframework.msscbeerservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

import javax.jms.JMSException;
import javax.transaction.Transactional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import piyush.springframework.beer.common.events.BrewBeerEvent;
import piyush.springframework.beer.common.events.NewInventoryEvent;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.repositories.BeerRepository;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

/**
 * 
 * @author Piyush
 *
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

	private final BeerRepository beerRepository;

	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = BREWING_REQUEST_QUEUE)
	public void listenEvents(BrewBeerEvent beerEvent) throws JMSException {

		BeerDto beerDto = beerEvent.getBeerDto();

		Beer beer = beerRepository.getOne(beerDto.getId());

		beerDto.setQuantityOnHand(beer.getQuantityToBrew());

		NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

		log.debug("Brewed Beer " + beer.getMinOnHand() + " QOH: " + beerDto.getQuantityOnHand());
		jmsTemplate.convertAndSend(NEW_INVENTORY_QUEUE, newInventoryEvent);
		// throw new JMSException("manually failed...........");

	}

}
