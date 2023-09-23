package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Plan}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanRepository extends JpaRepository<Plan, Long> {
    /**
     * Find {@link Plan} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Plan> findById(Long id);

    /**
     * Find active {@link Plan} entity by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    @Query("SELECT p from Plan p where p.id=:id AND p.isActive=true")
    Optional<Plan> findActiveById(@Param("id") Long id);

    /**
     * Find all active {@link Plan} entity.
     *
     * @return list of Plan.s
     */
    @Query("SELECT p from Plan p where p.isActive=true")
    List<Plan> findAllActive();
}
