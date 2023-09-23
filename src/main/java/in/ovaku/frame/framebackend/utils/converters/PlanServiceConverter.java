package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.PlanServiceRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanServiceResponseDto;
import in.ovaku.frame.framebackend.entities.PlanService;

/**
 * This is a converter interface.
 * It maps {@link PlanService} entity class with {@link PlanServiceRequestDto} and {@link PlanServiceResponseDto} class.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanServiceConverter {

    /**
     * This method convert {@link PlanServiceRequestDto} to {@link PlanService}
     *
     * @return {@link PlanService}
     */
    PlanService planServiceRequestDtoToPlanService(PlanServiceRequestDto planServiceRequestDto);

    /**
     * This method convert {@link PlanService} to {@link PlanServiceResponseDto}
     *
     * @return {@link PlanServiceResponseDto}
     */
    PlanServiceResponseDto planServiceToPlanServiceResponseDto(PlanService planService);
}
