package com.heliverse.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliverse.entities.ERole;
import com.heliverse.entities.Role;



@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(ERole name);

}