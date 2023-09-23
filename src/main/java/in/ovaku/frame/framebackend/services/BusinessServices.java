package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.requests.BusinessRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for business.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface BusinessServices {

    /**
     * This method return the list of {@link BusinessDto}.
     *
     * @param isActive - true or false to get active or inactive business.
     * @return list of {@link BusinessDto}
     */
    List<BusinessDto> getAll(Boolean isActive);

    /**
     * This method return a specific {@link BusinessDto} entity identified by the given {@link Business} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive business.
     * @return {@link BusinessDto}
     */
    BusinessDto getById(Long id, Boolean isActive);

    /**
     * This method register new {@link Business}.
     *
     * @param businessRegDto -businessDto to be registered.
     * @return {@link BusinessRegistrationDto}
     */
    BusinessRegistrationDto add(BusinessRegistrationDto businessRegDto);

    /**
     * This method update {@link Business} identified by businessID.
     *
     * @param id          - id of the entity to update. Must not be null.
     * @param businessDto - {@link BusinessDto} to be updated.
     * @return {@link BusinessDto}
     */
    BusinessDto update(Long id, BusinessDto businessDto);

    /**
     * This method delete the {@link Business} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);
}