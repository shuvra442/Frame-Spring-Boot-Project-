package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Subscription}.
 *
 * @author Sohan
 * @version 1.0
 * @since 11/07/22
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Find {@link Subscription} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Subscription> findById(Long id);

    /**
     * Find active {@link Subscription} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT s from Subscription s where s.id=:id AND s.isActive=true")
    Optional<Subscription> findActiveById(@Param("id") Long id);

    /**
     * Find active {@link Subscription} entity by id and businessId.
     *
     * @param id         - id to find entity. Must not be null.
     * @param businessId -id of business entity.
     * @param isActive   -true or false.
     * @return Optional
     */
    Optional<Subscription> findByIdAndBusinessIdAndIsActive(Long id, Long businessId, Boolean isActive);

    /**
     * Find {@link Subscription} entity by id and businessId.
     *
     * @param id         - id to find entity. Must not be null.
     * @param businessId -id of business entity.
     * @return Optional
     */
    Optional<Subscription> findByIdAndBusinessId(Long id, Long businessId);

    /**
     * Find all active {@link Subscription} entity.
     *
     * @return list of Subscription.
     */
    @Query("SELECT s from Subscription s where s.isActive=true")
    List<Subscription> findAllActive();

    /**
     * Find all active {@link Subscription} entity by businessID.
     *
     * @param businessId -id of the business to find subscription.Must not be null.
     * @param isActive   -true or false to identify active or inActive subscription.
     * @return list of all active Subscription.
     */
    List<Subscription> findAllByBusinessIdAndIsActive(Long businessId, Boolean isActive);

    /**
     * Find all {@link Subscription} entity by businessID.
     *
     * @param businessId -id of the business to find subscription.Must not be null.
     * @return list of all Subscription.
     */
    List<Subscription> findAllByBusinessId(Long businessId);
}
