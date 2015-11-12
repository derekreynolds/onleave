package com.evolvingreality.onleave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.EntitlementRepository;

/**
 * @author Derek Reynolds
 *
 */
@Service
@Transactional(readOnly = true)
public class EntitlementServiceImpl extends EntityServiceImpl<Entitlement> implements EntitlementService {

	private EntitlementRepository entitlementRepository;
	
	@Autowired
	public EntitlementServiceImpl(final EntitlementRepository entitlementRepository) {
		super(entitlementRepository);
		this.entitlementRepository = entitlementRepository;
	}


	@Override
	public Boolean hasUserEntitlementForYear(final User user, final Integer year) {
		
		log.debug("Check to see if user {} has entitlement for year {}", user.getId(), year);
		
		return entitlementRepository.findByUserAndYear(user, year).isPresent();
	}	
	
}