/**
 * 
 */
package com.evolvingreality.onleave.web.rest.util;

import java.net.URI;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * @author Derek Reynolds
 *
 */
public class UriUtil {

	public static <T> URI buildGetUri(final T controller, final Long id) {
        
       UriComponents uriComponents = MvcUriComponentsBuilder
                  .fromMethodName(controller.getClass(), "get", id).build();
      
       return uriComponents.encode().toUri();
	}
	
}
