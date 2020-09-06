package piyush.springframework.msscbeerservice.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class BeerDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Null
	private UUID id;

	@Null
	private Integer version;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	@Null
	private OffsetDateTime lastModifiedDate;

	@NotNull
	private String beerName;

	@NotNull
	private String beerStyle;

	@Positive
	@NotNull
	private String upc;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Positive
	@NotNull(message = "Price cannot be empty!")
	private BigDecimal price;
	private Integer quantityOnHand;

}