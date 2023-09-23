package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Business}.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface BusinessRepository extends JpaRepository<Business, Long> {

    /**
     * Find {@link Business} entity by phone number.
     *
     * @param phoneNo - phone number to find entity. Must not be null.
     * @return Optional
     */
    Optional<Business> findByPhoneNo(Long phoneNo);

    /**
     * Find {@link Business} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Business> findById(Long id);

    /**
     * Find active {@link Business} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT b from Business b where b.id=:id AND b.isActive=true")
    Optional<Business> findActiveById(@Param("id") Long id);

    /**
     * Find all active {@link Business} entity.
     *
     * @return list of business.
     */
    @Query("SELECT b from Business b where b.isActive=true")
    List<Business> findAllActive();
}