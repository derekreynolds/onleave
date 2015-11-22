/**
 * 
 */
package com.evolvingreality.onleave.web.rest.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.evolvingreality.onleave.model.User;

/**
 * @author Derek Reynolds
 *
 */
@Mapper
public interface UserMapper {

	UserDTO toDto(User user);
	
	User fromDto(UserDTO user);
	
}
