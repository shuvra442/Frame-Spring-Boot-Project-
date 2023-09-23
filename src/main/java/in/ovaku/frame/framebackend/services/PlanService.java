package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanOfferRequestDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanServiceRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanServiceResponseDto;
import in.ovaku.frame.framebackend.entities.Plan;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for plan.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
public interface PlanService {
    /**
     * This method return the list of {@link PlanDto}.
     *
     * @param isActive - true or false to get active or inactive plan.
     * @return list of {@link PlanDto}
     */
    List<PlanDto> getAll(Boolean isActive);

    /**
     * This method return the list of {@link PlanServiceResponseDto} by {@link Plan} id.
     *
     * @param planId   - id of the plan entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return list of {@link PlanServiceResponseDto}
     */
    List<PlanServiceResponseDto> getAllServicesByPlanId(Long planId, Boolean isActive);

    /**
     * This method return the list of {@link PlanOfferResponseDto} by {@link Plan} id.
     *
     * @param planId   - id of the plan entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return list of {@link PlanOfferResponseDto}
     */
    List<PlanOfferResponseDto> getAllOfferByPlanId(Long planId, Boolean isActive);

    /**
     * This method return a specific {@link PlanDto} entity identified by the given {@link Plan} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return {@link PlanDto}
     */
    PlanDto getById(Long id, Boolean isActive);

    /**
     * This method return a specific {@link PlanServiceResponseDto} entity identified by the given {@link Plan} id.
     *
     * @param planId   -id of the plan entity to find. Must not be null.
     * @param id       - id of the planService entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return {@link PlanServiceResponseDto}
     */
    PlanServiceResponseDto getServicesByPlanId(Long planId, Long id, Boolean isActive);

    /**
     * This method return a specific {@link PlanOfferResponseDto} entity identified by the given {@link Plan} id.
     *
     * @param planId   -id of the plan entity to find. Must not be null.
     * @param id       - id of the PlanOffer entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return {@link PlanOfferResponseDto}
     */
    PlanOfferResponseDto getOfferByPlanId(Long planId, Long id, Boolean isActive);

    /**
     * This method register new {@link Plan}.
     *
     * @param planDto -dto to be created.
     * @return {@link PlanDto}
     */
    PlanDto add(PlanDto planDto);

    /**
     * This method create new {@link PlanServiceResponseDto} by {@link Plan} id.
     *
     * @param planServiceRequestDto -dto to be created.
     * @return {@link PlanServiceResponseDto}
     */
    PlanServiceResponseDto addServiceByPlanId(Long planId, PlanServiceRequestDto planServiceRequestDto);

    /**
     * This method create new {@link PlanOfferResponseDto} by {@link Plan} id.
     *
     * @param planOfferRequestDto -dto to be created.
     * @return {@link PlanOfferResponseDto}
     */
    PlanOfferResponseDto addOfferByPlanId(Long planId, PlanOfferRequestDto planOfferRequestDto);

    /**
     * This method update {@link Plan} identified by planID.
     *
     * @param id      - id of the entity to update. Must not be null.
     * @param planDto - {@link PlanDto} to be updated.
     * @return {@link PlanDto}
     */
    PlanDto update(Long id, PlanDto planDto);

    /**
     * This method update {@link PlanServiceResponseDto} identified by planID and planServiceId.
     *
     * @param planId                -id of the plan entity to find. Must not be null.
     * @param id                    - id of the planService entity to update. Must not be null.
     * @param planServiceRequestDto - {@link PlanServiceRequestDto} to be updated.
     * @return {@link PlanServiceResponseDto}
     */
    PlanServiceResponseDto updateServiceByPlanId(Long planId, Long id, PlanServiceRequestDto planServiceRequestDto);

    /**
     * This method update {@link PlanOfferResponseDto} identified by planID and planOfferId.
     *
     * @param planId              -id of the plan entity to find. Must not be null.
     * @param id                  - id of the planOffer entity to update. Must not be null.
     * @param planOfferRequestDto - {@link PlanOfferRequestDto} to be updated.
     * @return {@link PlanServiceResponseDto}
     */
    PlanOfferResponseDto updateOfferByPlanId(Long planId, Long id, PlanOfferRequestDto planOfferRequestDto);

    /**
     * This method delete the {@link Plan} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);

    /**
     * This method delete the {@link PlanServiceResponseDto} entity identified by the given planId and planServiceId.
     *
     * @param planId -id of the plan entity to find. Must not be null.
     * @param id     - id of the planService entity to delete. Must not be null.
     * @return true or false
     */
    Boolean deleteServiceByPlanId(Long planId, Long id);

    /**
     * This method delete the {@link PlanOfferResponseDto} entity identified by the given planId and planOfferId.
     *
     * @param planId -id of the plan entity to find. Must not be null.
     * @param id     - id of the planOffer entity to delete. Must not be null.
     * @return true or false
     */
    Boolean deleteOfferByPlanId(Long planId, Long id);
}
