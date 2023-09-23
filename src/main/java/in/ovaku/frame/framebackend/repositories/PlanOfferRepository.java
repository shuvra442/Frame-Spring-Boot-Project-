package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Plan;
import in.ovaku.frame.framebackend.entities.PlanOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link PlanOffer}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanOfferRepository extends JpaRepository<PlanOffer, Long> {
    /**
     * Find {@link PlanOffer} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<PlanOffer> findByPlanIdAndId(Long planId, Long id);

    /**
     * Find active {@link PlanOffer} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT p from PlanOffer p where p.plan.id=:planId AND p.id=:id AND p.isActive=true")
    Optional<PlanOffer> findActiveById(@Param("planId") Long planId, @Param("id") Long id);

    /**
     * Find active {@link PlanOffer} entity by id.
     *
     * @param planId  -id of plan to find entity. Must not be null.
     * @param offerId - id of service to find entity. Must not be null.
     * @return Optional
     */
    Optional<PlanOffer> findByPlanIdAndOfferId(Long planId, Long offerId);

    /**
     * Find all {@link PlanOffer} entity by {@link Plan} id.
     *
     * @return list of PlanOffer
     */
    List<PlanOffer> findAllByPlanId(Long planId);

    /**
     * Find all active {@link PlanOffer} entity.
     *
     * @return list of PlanOffer
     */
    @Query("SELECT p from PlanOffer p where p.plan.id=:id AND p.isActive=true")
    List<PlanOffer> findAllActiveByPlanId(@Param("id") Long planId);

}
