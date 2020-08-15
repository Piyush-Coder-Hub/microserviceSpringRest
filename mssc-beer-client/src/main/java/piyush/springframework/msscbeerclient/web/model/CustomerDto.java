package piyush.springframework.msscbeerclient.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer Dto Pojo file
 * 
 * @author Piyush
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

	private UUID id;
	private String name;
}
