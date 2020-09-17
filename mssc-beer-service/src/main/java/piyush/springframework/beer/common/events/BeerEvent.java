package piyush.springframework.beer.common.events;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

/**
 * 
 * @author Piyush
 *
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

	private static final long serialVersionUID = -6880566554653248505L;
	private BeerDto beerDto;

}
