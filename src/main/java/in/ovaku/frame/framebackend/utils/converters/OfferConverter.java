package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.entities.Offer;

/**
 * This is a converter interface.
 * It is used to map {@link Offer} entity class with {@link OfferDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 10/07/22
 */
public interface OfferConverter {
    /**
     * This method convert {@link Offer} to {@link OfferDto}
     *
     * @return {@link OfferDto}
     */
    OfferDto offerToOfferDto(Offer offer);

    /**
     * This method convert {@link OfferDto} to {@link Offer}
     *
     * @return {@link Offer}
     */
    Offer offerDtoToOffer(OfferDto offerDto);

    /**
     * This method update existing {@link Offer} by {@link OfferDto}
     *
     * @return {@link Offer}
     */
    Offer toUpdateOffer(OfferDto offerDto, Offer offer);
}
