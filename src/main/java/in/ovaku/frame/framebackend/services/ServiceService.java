package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import in.ovaku.frame.framebackend.entities.Service;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for service.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface ServiceService {

    /**
     * This method return the list of {@link ServiceDto}.
     *
     * @param isActive - true or false to get active or inactive service.
     * @return list of {@link ServiceDto}
     */
    List<ServiceDto> getAll(Boolean isActive);

    /**
     * This method return a specific {@link ServiceDto} entity identified by the given {@link Service} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive service.
     * @return {@link ServiceDto}
     */
    ServiceDto getById(Long id, Boolean isActive);

    /**
     * This method register new {@link Service}.
     *
     * @param serviceDto -dto to be created.
     * @return {@link ServiceDto}
     */
    ServiceDto add(ServiceDto serviceDto);

    /**
     * This method update {@link Service} identified by serviceID.
     *
     * @param id         - id of the entity to update. Must not be null.
     * @param serviceDto - {@link ServiceDto} to be updated.
     * @return {@link ServiceDto}
     */
    ServiceDto update(Long id, ServiceDto serviceDto);

    /**
     * This method delete the {@link Service} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);
}
