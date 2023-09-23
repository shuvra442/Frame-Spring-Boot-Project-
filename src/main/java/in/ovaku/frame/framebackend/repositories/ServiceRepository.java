package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Service}.
 *
 * @author Sohan
 * @version 1.0
 * @since 07/07/22
 */
public interface ServiceRepository extends JpaRepository<Service, Long> {

    /**
     * Find {@link Service} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Service> findById(Long id);

    /**
     * Find active {@link Service} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT s from Service s where s.id=:id AND s.isActive=true")
    Optional<Service> findActiveById(@Param("id") Long id);

    /**
     * Find all active {@link Service} entity.
     *
     * @return list of Service.
     */
    @Query("SELECT s from Service s where s.isActive=true")
    List<Service> findAllActive();
}
