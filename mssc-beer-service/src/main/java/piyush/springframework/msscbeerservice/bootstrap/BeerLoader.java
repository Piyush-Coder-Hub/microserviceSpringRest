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

	public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    
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
						.upc(BEER_1_UPC)
						.price(new BigDecimal("110.19"))
						.build());
				
				beerRepository.save(Beer.builder()
						.beerName("Budwiser")
						.beerStyle("50% alocohol")
						.minOnHand(12)
						.quantityToBrew(200)
						.upc(BEER_2_UPC)
						.price(new BigDecimal("500.78"))
						.build());
				
				beerRepository.save(Beer.builder()
						.beerName("Corona")
						.beerStyle("80% alocohol")
						.minOnHand(9)
						.quantityToBrew(500)
						.upc(BEER_3_UPC)
						.price(new BigDecimal("666"))
						.build());
			}
			
			log.debug("Loaded Bears durng startup context load " +beerRepository.count());
	}
	

}
