package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.entities.Plan;
import in.ovaku.frame.framebackend.utils.converters.PlanConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link PlanConverter}
 * It contain converter logic for {@link Plan} and {@link PlanDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Component
public class PlanConverterImpl implements PlanConverter {
    private final ModelMapper modelMapper;

    public PlanConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Plan} to {@link PlanDto}
     *
     * @return planDto
     */
    @Override
    public PlanDto planToPlanDto(Plan plan) {
        return modelMapper.map(plan, PlanDto.class);
    }

    /**
     * This method convert {@link PlanDto} to {@link Plan}
     *
     * @return plan
     */
    @Override
    public Plan planDtoToPlan(PlanDto planDto) {
        return modelMapper.map(planDto, Plan.class);
    }

    /**
     * This method update existing {@link Plan} by {@link PlanDto}
     *
     * @return plan
     */
    @Override
    public Plan toUpdatePlan(PlanDto planDto, Plan plan) {
        modelMapper.map(planDto, plan);
        return plan;
    }
}
