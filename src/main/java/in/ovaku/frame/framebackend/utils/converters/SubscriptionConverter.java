package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.SubscriptionRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.SubscriptionResponseDto;
import in.ovaku.frame.framebackend.entities.Subscription;

/**
 * This is a converter interface.
 * It maps {@link Subscription} entity class with {@link SubscriptionRequestDto} and {@link SubscriptionResponseDto} class.
 *
 * @author Sohan
 * @version 1.0
 * @since 11/07/22
 */
public interface SubscriptionConverter {
    /**
     * This method convert {@link SubscriptionRequestDto} to {@link Subscription}
     *
     * @return {@link Subscription}
     */
    Subscription subscriptionRequestDtoToSubscription(SubscriptionRequestDto subscriptionRequestDto);

    /**
     * This method convert {@link Subscription} to {@link SubscriptionResponseDto}
     *
     * @return {@link SubscriptionResponseDto}
     */
    SubscriptionResponseDto subscriptionToSubscriptionResponseDto(Subscription subscription);
}
