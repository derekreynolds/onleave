package com.evolvingreality.onleave.service;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.EntitlementRepository;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

/**
 * @author Derek Reynolds
 *
 */
public class EntitlementServiceImplTest {

	private EntitlementServiceImpl entitlementService;
		
	@Mock
	private EntitlementRepository entitlementRepository;
	
	public EntitlementServiceImplTest() {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {		
		
		entitlementService = new EntitlementServiceImpl(entitlementRepository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reset(entitlementRepository);
	}

	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#save(com.evolvingreality.onleave.model.Entitlement)}.
	 */
	@Test
	public void testSave() {
		
		Entitlement entitlement = EntitlementTestUtil.create();
		
		entitlementService.save(entitlement);
		
		verify(entitlementRepository, times(1)).save(entitlement);
	}

	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#hasUserEntitlementForYear(com.evolvingreality.onleave.model.User, java.lang.Integer)}.
	 */
	@Test
	public void testHasUserEntitlementForYearSuccess() {
		
		Integer year = new Integer(2015);
		
		User user = new User();
		user.setId(1L);
		
		when(entitlementRepository.findByUserAndYear(user, year)).thenReturn(Optional.<Entitlement>of(EntitlementTestUtil.create()));
		
		assertThat(entitlementService.hasUserEntitlementForYear(user, year)).isTrue();
		
		verify(entitlementRepository, times(1)).findByUserAndYear(user, year);		
		
	}
	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.EntitlementServiceImpl#hasUserEntitlementForYear(com.evolvingreality.onleave.model.User, java.lang.Integer)}.
	 */
	@Test
	public void testHasUserEntitlementForYearFailure() {
		
		Integer year = new Integer(2015);
		
		User user = new User();
		user.setId(1L);
		
		when(entitlementRepository.findByUserAndYear(user, year)).thenReturn(Optional.<Entitlement>empty());
		
		assertThat(entitlementService.hasUserEntitlementForYear(user, year)).isFalse();
		
		verify(entitlementRepository, times(1)).findByUserAndYear(user, year);		
		
	}

}
