package com.evolvingreality.onleave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.SecurityGroupMember;



/**
 * Spring Data JPA repository for the {@link SecurityGroupMember} entity.
 */
public interface SecurityGroupMemberRepository extends JpaRepository<SecurityGroupMember, Long> {
	
	
}