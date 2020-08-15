package piyush.springframework.msscbeerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import piyush.springframework.msscbeerservice.web.model.BeerDto;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

@Service
public class BeerSericeImpl implements BeerSerice {

	@Override
	public BeerDto getBeerById(UUID id) {
		return BeerDto.builder()
				.beerName("CORONA")
				.beerStyle(BeerStyleName.PILSNER)
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("CORONA")
				.beerStyle(BeerStyleName.PILSNER)
				.build();
	}

	@Override
	public BeerDto updateBeer(UUID id, BeerDto beerDto) {
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public BeerDto deleteBeerById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
