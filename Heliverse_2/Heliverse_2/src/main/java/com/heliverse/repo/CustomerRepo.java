package com.heliverse.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliverse.entities.Customer;



public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByName(String username);

	Boolean existsByEmail(String email);

}
