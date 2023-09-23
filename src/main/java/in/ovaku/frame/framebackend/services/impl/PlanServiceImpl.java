package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanOfferRequestDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanServiceRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanServiceResponseDto;
import in.ovaku.frame.framebackend.entities.Offer;
import in.ovaku.frame.framebackend.entities.Plan;
import in.ovaku.frame.framebackend.entities.PlanOffer;
import in.ovaku.frame.framebackend.exceptions.ResourceAlreadyExistsException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.PlanOfferRepository;
import in.ovaku.frame.framebackend.repositories.PlanRepository;
import in.ovaku.frame.framebackend.repositories.PlanServiceRepository;
import in.ovaku.frame.framebackend.services.OfferService;
import in.ovaku.frame.framebackend.services.PlanService;
import in.ovaku.frame.framebackend.services.ServiceService;
import in.ovaku.frame.framebackend.utils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link PlanService}
 * It contain different service logic.
 *
 * @author sohan
 * @version 1.0
 * @since 07/07/22
 */
@Service
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final PlanConverter planConverter;
    private final PlanServiceRepository planServiceRepository;
    private final PlanServiceConverter planServiceConverter;
    private final ServiceService serviceService;
    private final ServiceConverter serviceConverter;
    private final PlanOfferRepository planOfferRepository;
    private final PlanOfferConverter planOfferConverter;
    private final OfferService offerService;
    private final OfferConverter offerConverter;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public PlanServiceImpl(PlanRepository planRepository, PlanConverter planConverter, PlanServiceRepository planServiceRepository, PlanServiceConverter planServiceConverter, ServiceService serviceService, ServiceConverter serviceConverter, PlanOfferRepository planOfferRepository, PlanOfferConverter planOfferConverter, OfferService offerService, OfferConverter offerConverter) {
        this.planRepository = planRepository;
        this.planConverter = planConverter;
        this.planServiceRepository = planServiceRepository;
        this.planServiceConverter = planServiceConverter;
        this.serviceService = serviceService;
        this.serviceConverter = serviceConverter;
        this.planOfferRepository = planOfferRepository;
        this.planOfferConverter = planOfferConverter;
        this.offerService = offerService;
        this.offerConverter = offerConverter;
    }

    /**
     * This method return the list of {@link PlanDto}.
     *
     * @param isActive - true or false to get active or inactive plan.
     * @return list of planDto
     */
    @Override
    public List<PlanDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Plan> planList;

        if (isActive) {
            planList = planRepository.findAllActive();
        } else {
            planList = planRepository.findAll();
        }

        if (planList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched plan list => {}", planList);
        return planList.stream()
                .map(planConverter::planToPlanDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return the list of {@link PlanServiceResponseDto}.
     *
     * @param planId   -id of the {@link Plan} entity to get planService.Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return list of planServiceResponseDto
     */
    @Override
    public List<PlanServiceResponseDto> getAllServicesByPlanId(Long planId, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<in.ovaku.frame.framebackend.entities.PlanService> planServiceList;
        if (isActive) {
            planServiceList = planServiceRepository.findAllActiveByPlanId(planId);
        } else {
            planServiceList = planServiceRepository.findAllByPlanId(planId);
        }
        if (planServiceList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched plan list => {}", planServiceList);
        return planServiceList.stream()
                .map(planServiceConverter::planServiceToPlanServiceResponseDto)
                .collect(Collectors.toList());
    }


    /**
     * This method return the list of {@link PlanOfferResponseDto}.
     *
     * @param planId   -id of the {@link Plan} entity to get planOffer.Must not be null.
     * @param isActive - true or false to get active or inactive planOfferDto.
     * @return list of planOfferResponseDto
     */
    @Override
    public List<PlanOfferResponseDto> getAllOfferByPlanId(Long planId, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<PlanOffer> planOfferList;
        if (isActive) {
            planOfferList = planOfferRepository.findAllActiveByPlanId(planId);
        } else {
            planOfferList = planOfferRepository.findAllByPlanId(planId);
        }
        if (planOfferList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched plan list => {}", planOfferList);
        return planOfferList.stream()
                .map(planOfferConverter::planOfferToPlanOfferResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link PlanDto} entity identified by the given {@link Plan} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive plan.
     * @return planDto entity
     */
    @Override
    public PlanDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Plan> optionalPlan;

        if (isActive) {
            optionalPlan = planRepository.findActiveById(id);
        } else {
            optionalPlan = planRepository.findById(id);
        }

        if (optionalPlan.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Plan plan = optionalPlan.get();
        logger.debug("Fetched plan entity => {}", plan);
        PlanDto planDto = planConverter.planToPlanDto(plan);
        logger.debug("Converted DTO => {} from plan entity", planDto);
        return planDto;
    }

    /**
     * This method return a specific PlanServiceResponseDto entity identified by {@link Plan} id.
     *
     * @param planId   -id of the {@link Plan} entity to get planService.Must not be null.
     * @param id       -id of the Plan entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive planService.
     * @return planServiceResponseDto
     */
    @Override
    public PlanServiceResponseDto getServicesByPlanId(Long planId, Long id, Boolean isActive) {
        logger.debug("Into get entity service method with planId=> {} and id => {}", planId, id);
        Optional<in.ovaku.frame.framebackend.entities.PlanService> optionalPlanService;
        if (isActive) {
            optionalPlanService = planServiceRepository.findActiveById(planId, id);
        } else {
            optionalPlanService = planServiceRepository.findByPlanIdAndId(planId, id);
        }
        if (optionalPlanService.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        in.ovaku.frame.framebackend.entities.PlanService planService = optionalPlanService.get();
        logger.debug("Fetched plan entity => {}", planService);
        PlanServiceResponseDto responseDtoDto = planServiceConverter
                .planServiceToPlanServiceResponseDto(planService);
        logger.debug("Converted DTO => {} from plan entity", responseDtoDto);
        return responseDtoDto;
    }


    /**
     * This method return a specific PlanOffer entity identified by {@link Plan} id.
     *
     * @param planId   -id of the {@link Plan} entity to get planOffer.Must not be null.
     * @param id       -id of the Plan entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive planOffer.
     * @return planOfferResponseDto entity
     */
    @Override
    public PlanOfferResponseDto getOfferByPlanId(Long planId, Long id, Boolean isActive) {
        logger.debug("Into get entity service method with planId=> {} and id => {}", planId, id);
        Optional<PlanOffer> optionalPlanOffer;
        if (isActive) {
            optionalPlanOffer = planOfferRepository.findActiveById(planId, id);
        } else {
            optionalPlanOffer = planOfferRepository.findByPlanIdAndId(planId, id);
        }
        if (optionalPlanOffer.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        PlanOffer planOffer = optionalPlanOffer.get();
        logger.debug("Fetched plan entity => {}", planOffer);
        PlanOfferResponseDto responseDtoDto = planOfferConverter
                .planOfferToPlanOfferResponseDto(planOffer);
        logger.debug("Converted DTO => {} from plan entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method register new {@link Plan}.
     *
     * @param planDto -dto to be created.
     * @return planDto entity
     */
    @Override
    public PlanDto add(PlanDto planDto) {
        logger.debug("Into add entity service method with data =>{}", planDto);
        Plan plan = planConverter.planDtoToPlan(planDto);
        logger.debug("Converted plan entity => {} from DTO", plan);
        plan = planRepository.save(plan);
        logger.debug("Record saved =>{} in DB", plan);
        PlanDto dto = planConverter.planToPlanDto(plan);
        logger.debug("Converted DTO => {} from plan entity", dto);
        return dto;
    }

    /**
     * This method create new {@link in.ovaku.frame.framebackend.entities.PlanService}.
     *
     * @param planId                -id of {@link Plan} entity to add planService.Must not be null.
     * @param planServiceRequestDto -dto to be created.
     * @return planServiceResponseDto entity
     */
    @Override
    public PlanServiceResponseDto addServiceByPlanId(Long planId, PlanServiceRequestDto planServiceRequestDto) {
        logger.debug("Into add entity service method with planId=> {} and  data =>{}", planId, planServiceRequestDto);

        //extract plan and validate
        Plan plan = planConverter.planDtoToPlan(getById(planId, true));
        logger.trace("Fetch active plan by planService");

        //extract Service from planService dto and validate
        in.ovaku.frame.framebackend.entities.Service service = serviceConverter.
                serviceDtoToService(serviceService.getById(planServiceRequestDto.getServiceId(), true));
        logger.trace("Fetch active service from planServiceRequestDto");

        Optional<in.ovaku.frame.framebackend.entities.PlanService> optionalPlanService = planServiceRepository
                .findByPlanIdAndServiceId(plan.getId(), service.getId());
        //validate
        if (optionalPlanService.isPresent()) {
            logger.error("Plan service data already exist =>{}", planServiceRequestDto);
            throw new ResourceAlreadyExistsException("Already Exists!");
        }
        in.ovaku.frame.framebackend.entities.PlanService planService = planServiceConverter
                .planServiceRequestDtoToPlanService(planServiceRequestDto);
        logger.debug("Converted planService entity => {} from DTO", planServiceRequestDto);
        planService.setPlan(plan);
        planService.setService(service);
        planService = planServiceRepository.save(planService);
        logger.trace("Record saved =>{} in DB", planService);

        PlanServiceResponseDto responseDtoDto = planServiceConverter.planServiceToPlanServiceResponseDto(planService);
        logger.debug("Converted DTO => {} from planService entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method register new {@link PlanOffer}.
     *
     * @param planId              -id of {@link Plan} entity to add planOffer.Must not be null.
     * @param planOfferRequestDto -dto to be created.
     * @return planOfferResponseDto entity
     */
    @Override
    public PlanOfferResponseDto addOfferByPlanId(Long planId, PlanOfferRequestDto planOfferRequestDto) {
        logger.debug("Into add entity service method with planId=> {} and  data =>{}", planId, planOfferRequestDto);

        //extract plan and validate
        Plan plan = planConverter.planDtoToPlan(getById(planId, true));
        logger.trace("Fetch active plan by planOffer");

        //extract Offer from planService dto and validate
        Offer offer = offerConverter.
                offerDtoToOffer(offerService.getById(planOfferRequestDto.getOfferId(), true));
        logger.trace("Fetch active offer from planServiceRequestDto");

        Optional<PlanOffer> optionalPlanOffer = planOfferRepository
                .findByPlanIdAndOfferId(plan.getId(), offer.getId());
        //validate
        if (optionalPlanOffer.isPresent()) {
            logger.error("Plan offer data already exist =>{}", planOfferRequestDto);
            throw new ResourceAlreadyExistsException("Already Exists!");
        }
        PlanOffer planOffer = planOfferConverter
                .planOfferRequestDtoToPlanOffer(planOfferRequestDto);
        logger.debug("Converted planOffer entity => {} from DTO", planOfferRequestDto);
        planOffer.setPlan(plan);
        planOffer.setOffer(offer);
        planOffer = planOfferRepository.save(planOffer);
        logger.trace("Record saved =>{} in DB", planOffer);

        PlanOfferResponseDto responseDtoDto = planOfferConverter.planOfferToPlanOfferResponseDto(planOffer);
        logger.debug("Converted DTO => {} from planOffer entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method update {@link Plan} identified by planID.
     *
     * @param id      - id of the entity to update. Must not be null.
     * @param planDto - {@link PlanDto} to be updated.
     * @return planDto entity
     */
    @Override
    public PlanDto update(Long id, PlanDto planDto) {
        logger.debug("Into update entity service method with id => {} and data => {}", id, planDto);
        // Validate.
        if (!planRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Plan plan = planConverter.planDtoToPlan(planDto);
        logger.debug("Converted plan entity => {} from DTO => {}", plan, planDto);
        plan.setId(id);
        plan = planRepository.save(plan);
        logger.debug("Plan record updated in DB=>{}", plan);
        PlanDto dto = planConverter.planToPlanDto(plan);
        logger.debug("Converted DTO => {} from plan entity", dto);
        return dto;
    }

    /**
     * This method update {@link in.ovaku.frame.framebackend.entities.PlanService} identified by planID.
     *
     * @param planId                -id of the {@link Plan} entity to update planService.Must not be null.
     * @param id                    - id of the entity to update. Must not be null.
     * @param planServiceRequestDto - {@link PlanServiceRequestDto} to be updated.
     * @return planServiceResponseDto entity
     */
    @Override
    public PlanServiceResponseDto updateServiceByPlanId(Long planId, Long id
            , PlanServiceRequestDto planServiceRequestDto) {
        logger.debug("Into update entity service method with planId=> {}, id => {} and data => {}", planId
                , id, planServiceRequestDto);
        //validate
        if (!planServiceRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        //extract plan and validate
        Plan plan = planConverter.planDtoToPlan(getById(planId, true));
        logger.trace("Fetch active plan by planService");
        in.ovaku.frame.framebackend.entities.PlanService planService = planServiceConverter
                .planServiceRequestDtoToPlanService(planServiceRequestDto);
        logger.debug("Converted plan service entity => {} from DTO => {}", planService, planServiceRequestDto);

        //extract service and validate
        in.ovaku.frame.framebackend.entities.Service service = serviceConverter.
                serviceDtoToService(serviceService.getById(planServiceRequestDto.getServiceId(), true));
        logger.trace("Fetch active service from planServiceRequestDto");
        planService.setId(id);
        planService.setPlan(plan);
        planService.setService(service);
        planService = planServiceRepository.save(planService);
        logger.debug("Plan service record updated in DB=>{}", planService);
        PlanServiceResponseDto responseDtoDto = planServiceConverter.planServiceToPlanServiceResponseDto(planService);
        logger.debug("Converted DTO => {} from plan service entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method update {@link PlanOffer} identified by planID.
     *
     * @param planId              -id of the {@link Plan} entity to update planOffer.Must not be null.
     * @param id                  - id of the entity to update. Must not be null.
     * @param planOfferRequestDto - {@link PlanOfferRequestDto} to be updated.
     * @return planOfferResponseDto entity
     */
    @Override
    public PlanOfferResponseDto updateOfferByPlanId(Long planId, Long id, PlanOfferRequestDto planOfferRequestDto) {
        logger.debug("Into update entity service method with planId=> {}, id => {} and data => {}", planId
                , id, planOfferRequestDto);
        //validate
        if (!planOfferRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        //extract plan and validate
        Plan plan = planConverter.planDtoToPlan(getById(planId, true));
        logger.trace("Fetch active plan by planService");
        PlanOffer planOffer = planOfferConverter
                .planOfferRequestDtoToPlanOffer(planOfferRequestDto);
        logger.debug("Converted plan offer entity => {} from DTO => {}", planOffer, planOfferRequestDto);

        //extract offer and validate
        Offer offer = offerConverter.
                offerDtoToOffer(offerService.getById(planOfferRequestDto.getOfferId(), true));
        logger.trace("Fetch active offer from planOfferRequestDto");
        planOffer.setId(id);
        planOffer.setPlan(plan);
        planOffer.setOffer(offer);
        planOffer = planOfferRepository.save(planOffer);
        logger.debug("Plan offer record updated in DB=>{}", planOffer);
        PlanOfferResponseDto responseDtoDto = planOfferConverter.planOfferToPlanOfferResponseDto(planOffer);
        logger.debug("Converted DTO => {} from plan offer entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method delete the {@link Plan} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return inactive planDto entity
     */
    @Override
    public Boolean delete(Long id) {
        logger.debug("Into delete entity service method with id => {}", id);
        // Validate.
        if (planRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Plan plan = planRepository.getById(id);
        logger.debug("Fetching plan entity by id => {} from DB using repository", id);
        plan.setIsActive(false);
        planRepository.save(plan);
        logger.debug("Plan entity deleted from DB=>{}", plan);
        return true;
    }

    /**
     * This method delete {@link in.ovaku.frame.framebackend.entities.PlanService} entity identified by the given id.
     *
     * @param planId -id of the {@link Plan} entity to delete plan service.Must not be null.
     * @param id     - id of the entity to delete. Must not be null.
     * @return inactive planServiceResponseDto entity
     */
    @Override
    public Boolean deleteServiceByPlanId(Long planId, Long id) {
        logger.debug("Into delete entity service method with planId=> {} and id => {}", planId, id);
        //validate
        if (planServiceRepository.findActiveById(planId, id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        in.ovaku.frame.framebackend.entities.PlanService planService = planServiceRepository.getById(id);
        logger.debug("Fetching plan service entity by id => {} from DB using repository", id);
        planService.setIsActive(false);
        planServiceRepository.save(planService);
        logger.debug("Plan entity deleted from DB=>{}", planService);
        return true;
    }

    /**
     * This method delete {@link PlanOffer} entity identified by the given id.
     *
     * @param planId -id of the {@link Plan} entity to delete planOffer.Must not be null.
     * @param id     - id of the entity to delete. Must not be null.
     * @return inactive PlanOfferResponseDto entity
     */
    @Override
    public Boolean deleteOfferByPlanId(Long planId, Long id) {
        logger.debug("Into delete entity service method with planId=> {} and id => {}", planId, id);
        //validate
        if (planOfferRepository.findActiveById(planId, id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        PlanOffer planOffer = planOfferRepository.getById(id);
        logger.debug("Fetching planOffer entity by id => {} from DB using repository", id);
        planOffer.setIsActive(false);
        planOfferRepository.save(planOffer);
        logger.debug("Plan entity deleted from DB=>{}", planOffer);
        return true;
    }
}
