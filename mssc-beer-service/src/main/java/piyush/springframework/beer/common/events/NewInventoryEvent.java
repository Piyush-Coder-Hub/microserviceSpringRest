package piyush.springframework.beer.common.events;

import lombok.NoArgsConstructor;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

/**
 * 
 * @author Piyush
 *
 */
@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
