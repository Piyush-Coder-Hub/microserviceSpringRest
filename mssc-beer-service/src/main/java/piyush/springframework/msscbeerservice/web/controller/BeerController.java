package piyush.springframework.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import piyush.springframework.msscbeerservice.services.BeerSerice;
import piyush.springframework.msscbeerservice.web.constants.ApiV1;
import piyush.springframework.msscbeerservice.web.model.BeerDto;

@RequestMapping(ApiV1.BEERSERVICEPATHAPI)
@RestController
public class BeerController {

	@Autowired
	public BeerSerice beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {

		//return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
		return new ResponseEntity<BeerDto>(beerService.getBeerById(beerId), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
		return new ResponseEntity(HttpStatus.CREATED);

	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
