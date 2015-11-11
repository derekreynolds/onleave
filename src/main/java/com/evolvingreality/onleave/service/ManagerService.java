/**
 * 
 */
package com.evolvingreality.onleave.service;

import java.util.List;

import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.User;

import org.springframework.data.domain.Page;

/**
 * @author Derek Reynolds
 *
 */
public interface ManagerService {

	/**
	 * Get all the managers in the system.
	 * 
	 * @return a {@link List} of {@link SelectOption} containing the id
	 * and name of a manager.
	 */
	List<SelectOption> getManagerSelectOptions();
	
	Page<User> getManagers();
	
	Page<User> getDirectReports(final User user);
	
}
