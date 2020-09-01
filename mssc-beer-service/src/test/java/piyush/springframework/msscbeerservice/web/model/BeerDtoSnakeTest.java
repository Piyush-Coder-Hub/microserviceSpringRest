package piyush.springframework.msscbeerservice.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("snake")
@JsonTest
public class BeerDtoSnakeTest extends BaseTest {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void testSnake() throws JsonProcessingException {
		BeerDto beerDto = getDto();

		String jsonString = objectMapper.writeValueAsString(beerDto);

		System.out.println(jsonString);
	}

}
