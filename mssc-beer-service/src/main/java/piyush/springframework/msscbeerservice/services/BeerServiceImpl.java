package piyush.springframework.msscbeerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.exception.handlers.NotFoundException;
import piyush.springframework.msscbeerservice.repositories.BeerRepository;
import piyush.springframework.msscbeerservice.web.mappers.BeerMapper;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;

	private final BeerMapper beerMapper;

	@Override
	public BeerDto getBeerById(UUID id) {
		/*
		 * return BeerDto.builder() .beerName("CORONA")
		 * .beerStyle(BeerStyleName.PILSNER.toString()) .build();
		 */

		return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		/*
		 * return BeerDto.builder().id(UUID.randomUUID()).beerName("CORONA").beerStyle(
		 * BeerStyleName.PILSNER.toString()) .build();
		 */

		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID id, BeerDto beerDto) {
		// return BeerDto.builder().id(UUID.randomUUID()).build();

		Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public BeerDto deleteBeerById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
