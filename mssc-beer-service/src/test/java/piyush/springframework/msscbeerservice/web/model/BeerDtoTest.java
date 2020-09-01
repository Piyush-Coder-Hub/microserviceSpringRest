package piyush.springframework.msscbeerservice.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTest
class BeerDtoTest extends BaseTest {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testSerializeDto() throws JsonProcessingException {
		BeerDto beerDto = getDto();

		String jsonString = objectMapper.writeValueAsString(beerDto);

		System.out.println(jsonString);
	}

	@Test
	void testDeSerializeDto() throws JsonProcessingException {

		String json = "{\"id\":\"8cc776d0-2217-42c4-8408-276c5c50ccd1\",\"version\":null,\"createdDate\":\"2020-09-01T23:15:58+0530\",\"lastModifiedDate\":\"2020-09-01T23:15:58+0530\",\"beerName\":\"BeerName\",\"beerStyle\":\"Corona\",\"upc\":12134455,\"price\":12.99,\"quantityOnHand\":null}\r\n"
				+ "";
		BeerDto beerDto = getDto();
		//beerDto.setLastModifiedDate(OffsetDateTime.parse("2020-08-27T23:52:22.4452904+05:30"));
		//beerDto.setCreatedDate(OffsetDateTime.parse("2020-08-27T23:52:22.4452904+05:30"));
		BeerDto dto = objectMapper.readValue(json, BeerDto.class);
		System.out.println(dto);
		//assertEquals(beerDto, dto);

	}
}
