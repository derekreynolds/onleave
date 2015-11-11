package com.evolvingreality.onleave.service;

import com.evolvingreality.onleave.model.Entitlement;

public class EntitlementTestUtil {

	/**
	 * Creates a default {@link Entitlement}
	 * @return
	 */
	public static Entitlement create() {
		
		Entitlement entitlement = new Entitlement();
		
		entitlement.setAnnualLeave(25.0);
		entitlement.setAdditionalAnnualLeave(2.0);
		entitlement.setYear(2015);
		
		return entitlement;
	}
	
}
