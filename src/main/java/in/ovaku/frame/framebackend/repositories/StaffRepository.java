package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Staff}.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface StaffRepository extends JpaRepository<Staff, Long> {

    /**
     * Find {@link Staff} entity by phone number.
     *
     * @param phoneNo - phone number to find entity. Must not be null.
     * @return Optional
     */
    Optional<Staff> findByPhoneNo(Long phoneNo);

    /**
     * Find {@link Staff} entity by email.
     *
     * @param email - email to find entity. Must not be null.
     * @return Optional
     */
    Optional<Staff> findByEmail(String email);

    /**
     * Find {@link Staff} entity by {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity.Must not be null.
     * @param id         -id of the {@link Staff} entity. Must not be null.
     * @return Optional
     */
    Optional<Staff> findByBusinessIdAndId(Long businessId, Long id);

    /**
     * Find active {@link Staff} entity {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity.Must not be null.
     * @param id         -id of the {@link Staff} entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT s from Staff s where s.business.id=:businessId AND s.id=:id AND s.isActive=true")
    Optional<Staff> findActiveById(@Param("businessId") Long businessId, @Param("id") Long id);

    /**
     * Find all {@link Staff} entity by {@link Business} id.
     *
     * @param id - id of the {@link Business} entity.Must not be null.
     * @return list of staff.
     */
    List<Staff> findAllByBusinessId(Long id);

    /**
     * Find all active {@link Staff} entity by {@link Business} id.
     *
     * @param id - id of the {@link Business} entity.Must not be null.
     * @return list of active staff.
     */
    @Query("SELECT s from Staff s where s.business.id=:id AND s.isActive=true")
    List<Staff> findAllActiveByBusinessId(@Param("id") Long id);
}
