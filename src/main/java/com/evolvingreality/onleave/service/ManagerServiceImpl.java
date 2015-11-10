/**
 * 
 */
package com.evolvingreality.onleave.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.repository.UserRepository;

/**
 * @author Derek Reynolds
 *
 */
@Service
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	private UserRepository userRespository;
	
	@Autowired
	public ManagerServiceImpl(final UserRepository userRespository) {
		this.userRespository = userRespository;
	}
	
	@Override
	public List<SelectOption> getManagerSelectOptions() {
		
		log.debug("Entering");
		
		return userRespository.findAllByGroupMembers_SecurityGroupGroupNameIn(Collections.singleton("ROLE_MANAGER"))
				.stream().map(user -> new SelectOption(String.valueOf(user.getId()), user.getFirstName() + " " + user.getLastName()))
				.collect(Collectors.toList());
	}
	
	
}
