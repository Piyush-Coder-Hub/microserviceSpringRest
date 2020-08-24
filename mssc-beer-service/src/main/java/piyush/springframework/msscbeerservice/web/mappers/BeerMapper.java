package piyush.springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

/**
 * MapStruct for entity bean & pojo dto file mapping
 * 
 * @author Piyush
 *
 */

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);

}