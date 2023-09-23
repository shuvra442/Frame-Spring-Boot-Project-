package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Client}.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * Find {@link Client} entity by phone number.
     *
     * @param phoneNo - phone number to find entity. Must not be null.
     * @return Optional
     */
    Optional<Client> findByPhoneNo(Long phoneNo);

    /**
     * Find {@link Client} entity by {@link Business} id and {@link Client} id.
     *
     * @param businessId - id of the {@link Business} entity.Must not be null.
     * @param id         -id of the {@link Client} entity. Must not be null.
     * @return Optional
     */
    Optional<Client> findByBusinessIdAndId(Long businessId, Long id);

    /**
     * Find {@link Client} entity by {@link Business} id and {@link Client} id.
     *
     * @param businessId - id of the {@link Business} entity.Must not be null.
     * @param id         -id of the {@link Client} entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT c from Client c where c.business.id=:businessId AND c.id=:id AND c.isActive=true")
    Optional<Client> findActiveByBusinessIdAndId(@Param("businessId") Long businessId, @Param("id") Long id);

    /**
     * Find active {@link Client} entity {@link Business} id and {@link Client} id.
     *
     * @param id         -id of the {@link Client} entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT c from Client c where c.id=:id AND c.isActive=true")
    Optional<Client> findActiveById(@Param("id") Long id);

    /**
     * Find all {@link Client} entity by {@link Business} id.
     *
     * @param id - id of the {@link Business} entity.Must not be null.
     * @return list of client.
     */
    List<Client> findAllByBusinessId(Long id);

    /**
     * Find all active {@link Client} entity by {@link Business} id.
     *
     * @return list of active client.
     */
    @Query("SELECT c from Client c where c.business.id=:businessId AND c.isActive=true")
    List<Client> findAllActiveByBusinessId(@Param("businessId") Long businessId);

    /**
     * Find all active {@link Client} entity by {@link Business} id.
     *
     * @return list of active client.
     */
    @Query("SELECT c from Client c where c.isActive=true")
    List<Client> findAllActive();
}