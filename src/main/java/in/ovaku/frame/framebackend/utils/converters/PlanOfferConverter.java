package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.PlanOfferRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.entities.PlanOffer;
import in.ovaku.frame.framebackend.entities.PlanService;

/**
 * This is a converter interface.
 * It maps {@link PlanOffer} entity class with {@link PlanOfferRequestDto} and {@link PlanOfferResponseDto} class.
 *
 * @author Sohan
 * @version 1.0
 * @since 09/07/22
 */
public interface PlanOfferConverter {
    /**
     * This method convert {@link PlanOfferRequestDto} to {@link PlanOffer}
     *
     * @return {@link PlanOffer}
     */
    PlanOffer planOfferRequestDtoToPlanOffer(PlanOfferRequestDto planOfferRequestDto);

    /**
     * This method convert {@link PlanService} to {@link PlanOfferResponseDto}
     *
     * @return {@link PlanOfferResponseDto}
     */
    PlanOfferResponseDto planOfferToPlanOfferResponseDto(PlanOffer planOffer);

    /**
     * This method convert {@link PlanOfferResponseDto} to {@link PlanOffer}
     *
     * @return {@link PlanOffer}
     */
    PlanOffer planOfferResponseDtoToPlanOffer(PlanOfferResponseDto planOfferResponseDto);

}
