package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.entities.Offer;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for Offer.
 *
 * @author Sohan
 * @version 1.0
 * @since 10/07/22
 */
public interface OfferService {
    /**
     * This method return the list of {@link OfferDto}.
     *
     * @param isActive - true or false to get active or inactive Offer.
     * @return list of {@link OfferDto}
     */
    List<OfferDto> getAll(Boolean isActive);

    /**
     * This method return a specific {@link OfferDto} entity identified by the given {@link Offer} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive Offer.
     * @return {@link OfferDto}
     */
    OfferDto getById(Long id, Boolean isActive);

    /**
     * This method create new {@link Offer}.
     *
     * @param offerDto -dto to be created.
     * @return {@link OfferDto}
     */
    OfferDto add(OfferDto offerDto);

    /**
     * This method update {@link Offer} identified by serviceID.
     *
     * @param id       - id of the entity to update. Must not be null.
     * @param offerDto - {@link OfferDto} to be updated.
     * @return {@link OfferDto}
     */
    OfferDto update(Long id, OfferDto offerDto);

    /**
     * This method delete the {@link Offer} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);
}
