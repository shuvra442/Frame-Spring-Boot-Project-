package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.SubscriptionRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.SubscriptionResponseDto;
import in.ovaku.frame.framebackend.entities.Subscription;
import in.ovaku.frame.framebackend.utils.converters.SubscriptionConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link SubscriptionConverter}.
 * It contain converter logic for {@link Subscription}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Component
public class SubscriptionConverterImpl implements SubscriptionConverter {
    private final ModelMapper modelMapper;

    public SubscriptionConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link SubscriptionRequestDto} to {@link Subscription}
     *
     * @return subscription
     */
    @Override
    public Subscription subscriptionRequestDtoToSubscription(SubscriptionRequestDto subscriptionRequestDto) {
        return modelMapper.map(subscriptionRequestDto, Subscription.class);
    }

    /**
     * This method convert {@link Subscription} to {@link SubscriptionResponseDto}
     *
     * @return subscriptionResponseDto
     */
    @Override
    public SubscriptionResponseDto subscriptionToSubscriptionResponseDto(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionResponseDto.class);
    }
}
