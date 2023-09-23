package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.PlanServiceRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanServiceResponseDto;
import in.ovaku.frame.framebackend.entities.PlanService;
import in.ovaku.frame.framebackend.utils.converters.PlanServiceConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link PlanServiceConverter}.
 * It contain converter logic for {@link PlanService}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Component
public class PlanServiceConverterImpl implements PlanServiceConverter {
    private final ModelMapper modelMapper;

    public PlanServiceConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link PlanServiceRequestDto} to {@link PlanService}
     *
     * @return planService entity
     */
    @Override
    public PlanService planServiceRequestDtoToPlanService(PlanServiceRequestDto planServiceRequestDto) {
        return modelMapper.map(planServiceRequestDto, PlanService.class);
    }

    /**
     * This method convert {@link PlanService} to {@link PlanServiceResponseDto}
     *
     * @return planServiceResponseDto entity
     */
    @Override
    public PlanServiceResponseDto planServiceToPlanServiceResponseDto(PlanService planService) {
        return modelMapper.map(planService, PlanServiceResponseDto.class);
    }
}
