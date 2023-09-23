package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.PlanOfferRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.entities.PlanOffer;
import in.ovaku.frame.framebackend.utils.converters.PlanOfferConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link PlanOfferConverter}.
 * It contains converter logic for {@link PlanOffer}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Component
public class PlanOfferConverterImpl implements PlanOfferConverter {
    private final ModelMapper modelMapper;

    public PlanOfferConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link PlanOfferRequestDto} to {@link PlanOffer}
     *
     * @return planOffer
     */
    @Override
    public PlanOffer planOfferRequestDtoToPlanOffer(PlanOfferRequestDto planOfferRequestDto) {
        return modelMapper.map(planOfferRequestDto, PlanOffer.class);
    }

    /**
     * This method convert {@link PlanOffer} to {@link PlanOfferResponseDto}
     *
     * @return planOfferResponseDto
     */
    @Override
    public PlanOfferResponseDto planOfferToPlanOfferResponseDto(PlanOffer planOffer) {
        return modelMapper.map(planOffer, PlanOfferResponseDto.class);
    }

    /**
     * This method convert {@link PlanOfferResponseDto} to {@link PlanOffer}
     *
     * @return planOffer
     */
    @Override
    public PlanOffer planOfferResponseDtoToPlanOffer(PlanOfferResponseDto planOfferResponseDto) {
        return modelMapper.map(planOfferResponseDto, PlanOffer.class);
    }
}
