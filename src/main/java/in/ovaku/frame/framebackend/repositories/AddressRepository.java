package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Address;
import in.ovaku.frame.framebackend.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This is a repository interface which provides crud operation for {@link Address}.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Get {@link Address} by using {@link Business} id;
     *
     * @param id - id of business entity to find. Must not be null.
     * @return address
     */
    Address getByBusinessesId(Long id);

    /**
     * Find {@link Address} by id.
     *
     * @param id - id to find entity. Must not be null.
     * @return Optional
     */
    Optional<Address> findById(Long id);
}
