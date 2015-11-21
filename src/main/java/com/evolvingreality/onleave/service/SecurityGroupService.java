/**
 * 
 */
package com.evolvingreality.onleave.service;

import java.util.List;

import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.SecurityGroup;

/**
 * @author Derek Reynolds
 *
 */
public interface SecurityGroupService extends EntityService<SecurityGroup> {

	List<SelectOption> getSecurityGroupSelectOptions();
	
}
