package piyush.springframework.msscbeerservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

@Slf4j
public abstract class BeerMapperDecorator implements BeerMapper {

	@Autowired
	public BeerInventoryService beerInventoryService;
	@Autowired
	public BeerMapper beerMapper;

	BeerMapperDecorator() {

	}

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		return beerMapper.beerToBeerDto(beer);

	}

	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return beerMapper.beerDtoToBeer(beerDto);
	}

	@Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) {
		BeerDto dto = beerMapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getonHandInventory(beer.getId()));
		log.debug(dto.toString());
		return dto;
	}

}
