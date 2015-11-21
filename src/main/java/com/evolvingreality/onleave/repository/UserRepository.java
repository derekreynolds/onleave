package com.evolvingreality.onleave.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    List<User> findAllByGroupMembers_SecurityGroupGroupNameIn(Collection<String> groupNames);
    
    @Override
    void delete(User t);

}
