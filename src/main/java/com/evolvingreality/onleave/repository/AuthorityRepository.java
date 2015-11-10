package com.evolvingreality.onleave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
