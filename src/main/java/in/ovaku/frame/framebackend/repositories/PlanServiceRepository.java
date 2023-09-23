package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Plan;
import in.ovaku.frame.framebackend.entities.PlanService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link PlanService}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanServiceRepository extends JpaRepository<PlanService, Long> {
    /**
     * Find {@link PlanService} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<PlanService> findByPlanIdAndId(Long planId, Long id);

    /**
     * Find active {@link PlanService} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT p from PlanService p where p.plan.id=:planId AND p.id=:id AND p.isActive=true")
    Optional<PlanService> findActiveById(@Param("planId") Long planId, @Param("id") Long id);

    /**
     * Find active {@link PlanService} entity by id.
     *
     * @param planId    -id of plan to find entity. Must not be null.
     * @param serviceId - id of service to find entity. Must not be null.
     * @return Optional
     */
    Optional<PlanService> findByPlanIdAndServiceId(Long planId, Long serviceId);

    /**
     * Find all {@link PlanService} entity by {@link Plan} id.
     *
     * @return list of PlanService
     */
    List<PlanService> findAllByPlanId(Long planId);

    /**
     * Find all active {@link PlanService} entity.
     *
     * @return list of PlanService
     */
    @Query("SELECT p from PlanService p where p.plan.id=:id AND p.isActive=true")
    List<PlanService> findAllActiveByPlanId(@Param("id") Long planId);
}
