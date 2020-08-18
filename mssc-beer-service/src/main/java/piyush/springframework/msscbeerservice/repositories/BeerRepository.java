package piyush.springframework.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import piyush.springframework.msscbeerservice.domain.Beer;

/**
 * 
 * @author Piyush
 *
 */

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}
