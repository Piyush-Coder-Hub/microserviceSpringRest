package piyush.springframework.msscbeerservice.web.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import piyush.springframework.msscbeerservice.services.BeerService;
import piyush.springframework.msscbeerservice.web.model.BeerDto;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BeerService beerService;

	BeerDto validBeer;

	private Validator validator;

	@BeforeEach
	public void setUp() {
		validBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("Beer1").beerStyle(BeerStyleName.PORTER.toString())
				.upc("123456789012").build();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void getBeer() throws Exception {
		given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

		mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
				.andExpect(jsonPath("$.beerName", is("Beer1")));
	}

	@Test
	public void handlePost() throws Exception {
		// given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		beerDto.setPrice(new BigDecimal("12.90"));
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer")
				.beerStyle(BeerStyleName.PORTER.toString()).build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		given(beerService.saveNewBeer(any())).willReturn(savedDto);

		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
				.andExpect(status().isCreated());

	}

	@Test
	public void handleUpdate() throws Exception {
		// given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		// when
		mockMvc.perform(
				put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
				.andExpect(status().isNoContent());

		then(beerService).should().updateBeer(any(), any());

	}

	@Test
	public void handleDtoPriceErrorValidation() throws Exception { 
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer")
				.beerStyle(BeerStyleName.PORTER.toString()).build();
		given(beerService.saveNewBeer(any())).willReturn(savedDto);
		Set<ConstraintViolation<BeerDto>> violations = validator.validate(beerDto);
		assertTrue(violations.size() > 0);
		assertEquals("Price cannot be empty!", violations.stream()
				.filter(error -> error.getMessage().equals("Price cannot be empty!")).findFirst().get().getMessage());

	}

}
