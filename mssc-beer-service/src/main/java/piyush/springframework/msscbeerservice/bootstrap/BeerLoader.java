package piyush.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import piyush.springframework.msscbeerservice.domain.Beer;
import piyush.springframework.msscbeerservice.repositories.BeerRepository;

/**
 * 
 * @author Piyush
 *
 */
@Slf4j
@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();

	}

	private void loadBeerObjects() {
			if(beerRepository.count()==0) {
				
				beerRepository.save(Beer.builder()
						.beerName("Mango Bobs")
						.beerStyle("IPA")
						.minOnHand(12)
						.quantityToBrew(200)
						.upc(1261726572383L)
						.price(new BigDecimal("110.19"))
						.build());
				
				beerRepository.save(Beer.builder()
						.beerName("Budwiser")
						.beerStyle("50% alocohol")
						.minOnHand(12)
						.quantityToBrew(200)
						.upc(900023778239283L)
						.price(new BigDecimal("500.78"))
						.build());
			}
			
			log.debug("Loaded Bears durng startup context load " +beerRepository.count());
	}
	

}
