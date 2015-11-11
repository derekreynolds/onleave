package com.evolvingreality.onleave.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.evolvingreality.onleave.model.AbstractAuditingEntity;


/**
 * This interface contains common operations for an Entity. It should any service
 * that interacts with an Entity should implement this.
 * @author Derek Reynolds
 *
 */
public interface EntityService<T extends AbstractAuditingEntity> {

	/**
	 * Gets the entity identified by the id passed in.
	 * @param id
	 * @return an entity.
	 */
	Optional<T> get(final Long id);
	
	/**
	 * Persists an object derived from {@link AbstractAuditingEntity}.
	 * @param entity - the {@link AbstractAuditingEntity} to persist.
	 */
	void save(final T entity);
	
	/**
	 * Get a {@link Page} of {@link AbstractAuditingEntity}.
	 * @param pageable - {@link Pageable}
	 * @return a page of {@link AbstractAuditingEntity}
	 */
	Page<T> getPage(final Pageable pageable);
	
	/**
	 * Get a {@link Page} of {@link AbstractAuditingEntity}.
	 * @param pageable - {@link Pageable}
	 * @return a slice of {@link AbstractAuditingEntity}
	 */
	Slice<T> getSlice(final Pageable pageable);
	
	/**
	 * Delete a {@link AbstractAuditingEntity}
	 * @param entity - the {@link AbstractAuditingEntity} to delete.
	 */
	void delete(final T entity);
	
	/**
	 * Delete a {@link AbstractAuditingEntity}
	 * @param id - the {@link Long} id of the object to delete.
	 */
	void delete(final Long id);
	
}

