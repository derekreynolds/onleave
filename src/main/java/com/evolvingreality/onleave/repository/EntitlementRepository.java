package com.evolvingreality.onleave.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;


/**
 * Spring Data JPA repository for the {@link Entitlement} entity.
 */
public interface EntitlementRepository extends JpaRepository<Entitlement, Long> {
	
	Optional<Entitlement> findByUserAndYear(User user, Integer year);
	
}