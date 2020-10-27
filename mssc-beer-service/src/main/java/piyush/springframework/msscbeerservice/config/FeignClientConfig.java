package piyush.springframework.msscbeerservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * Config for Intercepting & basic auth secure every request from feign client
 * 
 * @author Piyush
 *
 */
@Configuration
public class FeignClientConfig {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
			@Value("${mssc.brewery.inventory-user}") String inventoryUser,
			@Value("${mssc.brewery.inventory-password}") String inventoryPassword) {
		return new BasicAuthRequestInterceptor(inventoryUser, inventoryPassword);

	}

}
