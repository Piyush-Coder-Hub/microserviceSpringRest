package piyush.springframework.msscbeerservice.services;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.exception.handlers.NotFoundException;
import piyush.springframework.msscbeerservice.repositories.BeerRepository;
import piyush.springframework.msscbeerservice.web.mappers.BeerMapper;
import piyush.springframework.msscbeerservice.web.model.BeerDto;
import piyush.springframework.msscbeerservice.web.model.BeerPageList;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;

	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerCache", key = "#id", condition = "#showInventoryOptions==false")
	@Override
	public BeerDto getBeerById(UUID id, Boolean showInventoryOptions) {
		/*
		 * return BeerDto.builder() .beerName("CORONA")
		 * .beerStyle(BeerStyleName.PILSNER.toString()) .build();
		 */
		return showInventoryOptions
				? beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(id).orElseThrow(NotFoundException::new))
				: beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));
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

	
	@Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOptions==false")
	@Override
	public BeerPageList listBeers(String beerName, BeerStyleName beerStyle, PageRequest pageRequest,Boolean showInventoryOptions) {
		
		BeerPageList beerPageList;
		Page<Beer> beerPage;
		
		log.debug("Method Called from non cache");
        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventoryOptions){
        	beerPageList = new BeerPageList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDtoWithInventory)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        } else{
            beerPageList = new BeerPageList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }

        return beerPageList;
    }

	@Cacheable(cacheNames = "beerUpcCache")
	@Override
	public BeerDto getBeerByUpc(String upc) {
		return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
	}
		
}