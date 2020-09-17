package piyush.springframework.beer.common.events;

import lombok.NoArgsConstructor;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

/**
 * 
 * @author Piyush
 *
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
