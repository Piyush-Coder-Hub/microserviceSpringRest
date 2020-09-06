package piyush.springframework.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

/**
 * 
 * @author Piyush
 *
 */

public interface BeerRepository extends JpaRepository<Beer, UUID> {

	Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

	Page<Beer> findAllByBeerStyle(BeerStyleName beerStyle, Pageable pageable);

	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleName beerStyle, Pageable pageable);

	Beer findByUpc(String upc);

}
