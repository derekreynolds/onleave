package com.evolvingreality.onleave.service;

import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.web.rest.dto.UserDTO;

public interface UserService extends EntityService<User> {

	Optional<User> activateRegistration(String key);

	Optional<User> completePasswordReset(String newPassword, String key);

	Optional<User> requestPasswordReset(String mail);

	User create(final UserDTO userDto);

	void updateUserInformation(String firstName, String lastName, String email, String langKey);

	void changePassword(String password);

	User getUserWithAuthorities();

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p/>
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 * </p>
	 */
	void removeNotActivatedUsers();

}