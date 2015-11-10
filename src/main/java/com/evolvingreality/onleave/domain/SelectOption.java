/**
 * 
 */
package com.evolvingreality.onleave.domain;

/**
 * This class is used to hold the key values pair used by Html Select Input Option.
 * 
 * @author Derek Reynolds
 *
 */
public class SelectOption {

	private String key;
	
	private String value;
	
	public SelectOption(final String key, final String value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
