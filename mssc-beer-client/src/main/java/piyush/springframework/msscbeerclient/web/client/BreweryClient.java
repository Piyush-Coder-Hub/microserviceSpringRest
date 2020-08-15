package piyush.springframework.msscbeerclient.web.client;

import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import piyush.springframework.msscbeerclient.web.model.BeerDto;

/**
 * 
 * @author Piyush
 *
 */
@Component
@ConfigurationProperties(prefix = "sfp.brewery", ignoreInvalidFields = false)
public class BreweryClient {

	private final String BEER_PATH_V1 = "/api/v1/beer/";
	@Setter
	private String apiHost;

	private final RestTemplate restTemplate;

	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public BeerDto getbeerById(UUID id) {
		return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
	}

}
