package com.evolvingreality.onleave.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.Application;
import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Derek Reynolds
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class EntitlementServiceImplIT {

	@Autowired
	private EntitlementServiceImpl entitlementService;

	@Autowired
	private UserService userService;
	
	
	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#save(com.evolvingreality.onleave.model.Entitlement)}.
	 */
	@Test
	public void testSaveSuccess() {

		User user = userService.createUserInformation("johndoe", "johndoe", "John", "Doe", "john.doe@localhost");
		
		userService.save(user);	
		
		Entitlement entitlement = EntitlementTestUtil.create();
		
		entitlement.setUser(user);
		
		entitlementService.save(entitlement);
		
		assertThat(entitlement.getId()).isNotNull();
		assertThat(entitlement.getCreatedDate()).isNotNull();
		assertThat(entitlement.getCreatedBy()).isEqualTo("system");
		assertThat(entitlement.getLastModifiedDate()).isNotNull();
		assertThat(entitlement.getLastModifiedBy()).isEqualTo("system");
	}

	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#hasUserEntitlementForYear(com.evolvingreality.onleave.model.User, java.lang.Integer)}.
	 */
	@Test
	public void testHasUserEntitlementForYearSuccess() {
		
		User user = userService.createUserInformation("johndoe", "johndoe", "John", "Doe", "john.doe@localhost");
		
		userService.save(user);	
		
		Entitlement entitlement = EntitlementTestUtil.create();
		
		entitlement.setUser(user);		
		
		entitlementService.save(entitlement);
		
		assertThat(entitlementService.hasUserEntitlementForYear(user, 2015)).isTrue();
	}
	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#hasUserEntitlementForYear(com.evolvingreality.onleave.model.User, java.lang.Integer)}.
	 */
	@Test
	public void testHasUserEntitlementForYearFailure() {
		
		User user = userService.createUserInformation("johndoe", "johndoe", "John", "Doe", "john.doe@localhost");
		
		userService.save(user);			
		
		assertThat(entitlementService.hasUserEntitlementForYear(user, 2015)).isFalse();
	}

}
