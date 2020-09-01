package piyush.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

class BaseTest {

	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("BeerName")
				.beerStyle("Corona")
				.id(UUID.fromString("8cc776d0-2217-42c4-8408-276c5c50ccd1"))
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.price(new BigDecimal("12.99"))
				.upc(12134455L)
				.build();
	}

}
