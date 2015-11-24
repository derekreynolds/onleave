/**
 * 
 */
package com.evolvingreality.onleave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.model.SecurityGroupMember;
import com.evolvingreality.onleave.repository.SecurityGroupMemberRepository;

/**
 * @author Derek Reynolds
 *
 */
@Service
@Transactional(readOnly = true)
public class SecurityGroupMemberServiceImpl extends EntityServiceImpl<SecurityGroupMember> implements SecurityGroupMemberService {

	private SecurityGroupMemberRepository securityGroupMemberRepository;
	
	@Autowired
	public SecurityGroupMemberServiceImpl(final SecurityGroupMemberRepository securityGroupMemberRepository) {
		super(securityGroupMemberRepository);
		this.securityGroupMemberRepository = securityGroupMemberRepository;
	}

	
	
}
