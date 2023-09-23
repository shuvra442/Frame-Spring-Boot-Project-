package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Offer}.
 *
 * @author Sohan
 * @version 1.0
 * @since 10/07/22
 */
public interface OfferRepository extends JpaRepository<Offer, Long> {
    /**
     * Find {@link Offer} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Offer> findById(Long id);

    /**
     * Find active {@link Offer} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT f from Offer f where f.id=:id AND f.isActive=true")
    Optional<Offer> findActiveById(@Param("id") Long id);

    /**
     * Find all active {@link Offer} entity.
     *
     * @return list of Offer.
     */
    @Query("SELECT f from Offer f where f.isActive=true")
    List<Offer> findAllActive();
}
