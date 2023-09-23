package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.SubscriptionRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.SubscriptionResponseDto;
import in.ovaku.frame.framebackend.entities.Subscription;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for subscription.
 *
 * @author Sohan
 * @version 1.0
 * @since 11/07/22
 */
public interface SubscriptionService {
    /**
     * This method return the list of {@link SubscriptionResponseDto}.
     *
     * @param isActive - true or false to get active or inactive SubscriptionDto.
     * @return list of {@link SubscriptionResponseDto}
     */
    List<SubscriptionResponseDto> getAll(Boolean isActive);

    /**
     * This method return the list of {@link SubscriptionResponseDto}.
     *
     * @param businessId - id of the business.
     * @param isActive   - true or false to get active or inactive SubscriptionDto.
     * @return list of {@link SubscriptionResponseDto}
     */
    List<SubscriptionResponseDto> getAllByBusinessId(Long businessId, Boolean isActive);

    /**
     * This method return a specific {@link SubscriptionResponseDto} entity identified by the given {@link SubscriptionResponseDto} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive SubscriptionDto.
     * @return {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto getById(Long id, Boolean isActive);

    /**
     * This method return a specific {@link SubscriptionResponseDto} entity identified by the given {@link SubscriptionResponseDto} id.
     *
     * @param id            - id of the entity to find. Must not be null.
     * @param businessId-id of the business entity.
     * @param isActive      - true or false to get active or inactive SubscriptionDto.
     * @return {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto getByBusinessId(Long id, Long businessId, Boolean isActive);

    /**
     * This method create new {@link SubscriptionResponseDto}.
     *
     * @param businessId             -id of business.Must not be null.
     * @param subscriptionRequestDto -dto to be created.
     * @return {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto add(Long businessId, SubscriptionRequestDto subscriptionRequestDto);

    /**
     * This method update {@link SubscriptionResponseDto} identified by serviceID.
     *
     * @param id                     - id of the entity to update. Must not be null.
     * @param businessId             -  -id of business.Must not be null.
     * @param subscriptionRequestDto - dto to be updated.
     * @return {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto update(Long id, Long businessId, SubscriptionRequestDto subscriptionRequestDto);

    /**
     * This method delete the {@link Subscription} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    Subscription delete(Long id);
}
