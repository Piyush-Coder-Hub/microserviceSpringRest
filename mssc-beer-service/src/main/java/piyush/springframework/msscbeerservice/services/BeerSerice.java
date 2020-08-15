package piyush.springframework.msscbeerservice.services;

import java.util.UUID;

import piyush.springframework.msscbeerservice.web.model.BeerDto;

public interface BeerSerice {

	public BeerDto getBeerById(UUID id);

	public BeerDto saveNewBeer(BeerDto beerDto);

	public BeerDto updateBeer(UUID id, BeerDto beerDto);

	public BeerDto deleteBeerById(UUID id);

}
