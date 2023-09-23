package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.entities.Offer;
import in.ovaku.frame.framebackend.utils.converters.OfferConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link OfferConverter}
 * It contain converter logic for {@link Offer} and {@link OfferDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 10/07/22
 */
@Component
public class OfferConverterImpl implements OfferConverter {
    private final ModelMapper modelMapper;


    public OfferConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Offer} to {@link OfferDto}
     *
     * @return offerDto
     */
    @Override
    public OfferDto offerToOfferDto(Offer offer) {
        return modelMapper.map(offer, OfferDto.class);
    }

    /**
     * This method convert {@link OfferDto} to {@link Offer}
     *
     * @return offer
     */
    @Override
    public Offer offerDtoToOffer(OfferDto offerDto) {
        return modelMapper.map(offerDto, Offer.class);
    }

    /**
     * This method update existing {@link Offer} by {@link OfferDto}
     *
     * @return offer
     */
    @Override
    public Offer toUpdateOffer(OfferDto offerDto, Offer offer) {
        modelMapper.map(offerDto, offer);
        return offer;
    }
}
