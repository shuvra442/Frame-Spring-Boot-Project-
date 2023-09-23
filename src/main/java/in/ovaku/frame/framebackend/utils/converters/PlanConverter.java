package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.entities.Plan;

/**
 * This is a converter interface.
 * It is used to map {@link Plan} entity class with {@link PlanDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanConverter {

    /**
     * This method convert {@link Plan} to {@link PlanDto}
     *
     * @return {@link PlanDto}
     */
    PlanDto planToPlanDto(Plan plan);

    /**
     * This method convert {@link PlanDto} to {@link Plan}
     *
     * @return {@link Plan}
     */
    Plan planDtoToPlan(PlanDto planDto);

    /**
     * This method update existing {@link Plan} by {@link PlanDto}
     *
     * @return {@link Plan}
     */
    Plan toUpdatePlan(PlanDto planDto, Plan plan);
}
