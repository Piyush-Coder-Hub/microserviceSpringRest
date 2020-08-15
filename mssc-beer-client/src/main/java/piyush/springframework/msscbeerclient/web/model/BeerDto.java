package piyush.springframework.msscbeerclient.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Beer Dto Pojo class
 * 
 * @author Piyush
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	private UUID id;

	private String beerName;

	private String beerStyle;
	private Long upc;

}