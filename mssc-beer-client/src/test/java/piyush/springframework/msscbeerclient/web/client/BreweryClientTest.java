package piyush.springframework.msscbeerclient.web.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import piyush.springframework.msscbeerclient.web.model.BeerDto;

@SpringBootTest
class BreweryClientTest {

	@Autowired
	BreweryClient client;

	@Test
	void test() {
		BeerDto dto = client.getbeerById(UUID.randomUUID());
		assertNotNull(dto);
	}

}
