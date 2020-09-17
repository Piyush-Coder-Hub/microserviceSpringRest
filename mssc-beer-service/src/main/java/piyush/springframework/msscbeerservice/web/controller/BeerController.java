package piyush.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import piyush.springframework.msscbeerservice.services.BeerService;
import piyush.springframework.msscbeerservice.web.constants.ApiV1;
import piyush.springframework.msscbeerservice.web.model.BeerDto;
import piyush.springframework.msscbeerservice.web.model.BeerPageList;
import piyush.springframework.msscbeerservice.web.model.BeerStyleName;

@RequestMapping(ApiV1.BASEPATHAPI)
@RestController
public class BeerController {

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	@Autowired
	public BeerService beerService;

	@GetMapping(produces = { "application/json" },path = "/beer")
	public ResponseEntity<BeerPageList> listBeers(
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "beerName", required = false) String beerName,
			@RequestParam(value = "beerStyle", required = false) BeerStyleName beerStyle,
			@RequestParam(value = "showInventoryOptions", required = false, defaultValue = "false") Boolean showInventoryOptions) {
		if (pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		BeerPageList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),showInventoryOptions);
		return new ResponseEntity<>(beerList, HttpStatus.OK);

	}

	@GetMapping("beer/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,@RequestParam(value = "showInventoryOptions", required = false, defaultValue = "false") Boolean showInventoryOptions) {

		// return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
		return new ResponseEntity<>(beerService.getBeerById(beerId,showInventoryOptions), HttpStatus.OK);

	}
	
	@GetMapping("beerUpc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc) {

		// return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
		return new ResponseEntity<>(beerService.getBeerByUpc(upc), HttpStatus.OK);

	}

	@PostMapping(path = "/beer")
	public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
		/*
		 * BeerDto dto = beerService.saveNewBeer(beerDto); HttpHeaders headers = new
		 * HttpHeaders(); headers.add("Location", "api/v1/beer/" +
		 * dto.getId().toString());
		 */
		return new ResponseEntity(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);

	}

	@PutMapping("beer/{beerId}")
	public ResponseEntity updateBeerById(@Valid @PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		return new ResponseEntity(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("beer/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeerById(@PathVariable("beerId") UUID beerId) {
		beerService.deleteBeerById(beerId);
	}
}
