package piyush.springframework.msscbeerservice.services;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import piyush.springframework.msscbeerservice.web.model.BeerDto;
import piyush.springframework.msscbeerservice.web.model.BeerPageList;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

public interface BeerService {

	public BeerDto getBeerById(UUID id, Boolean showInventoryOptions);

	public BeerDto saveNewBeer(BeerDto beerDto);

	public BeerDto updateBeer(UUID id, BeerDto beerDto);

	public BeerDto deleteBeerById(UUID id);

	BeerPageList listBeers(String beerName, BeerStyleName beerStyle, PageRequest pageRequest,
			Boolean showInventoryOptions);

	public BeerDto getBeerByUpc(String upc);
}
