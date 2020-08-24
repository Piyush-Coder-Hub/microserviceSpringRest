package piyush.springframework.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class BeerDto {

	@Null
	private UUID id;

	@Null
	private Integer version;

	@Null
	private OffsetDateTime createdDate;
	@Null
	private OffsetDateTime lastModifiedDate;

	@NotNull
	private String beerName;

	@NotNull
	private String beerStyle;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	@NotNull(message = "Price cannot be empty!")
	private BigDecimal price;
	private Integer quantityOnHand;

}