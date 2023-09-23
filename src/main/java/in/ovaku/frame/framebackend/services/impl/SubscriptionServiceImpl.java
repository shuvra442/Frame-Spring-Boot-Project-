package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.SubscriptionRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.SubscriptionResponseDto;
import in.ovaku.frame.framebackend.entities.*;
import in.ovaku.frame.framebackend.entities.enums.OfferType;
import in.ovaku.frame.framebackend.entities.enums.PlanType;
import in.ovaku.frame.framebackend.entities.enums.SubscriptionType;
import in.ovaku.frame.framebackend.exceptions.OperationFailedException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.SubscriptionRepository;
import in.ovaku.frame.framebackend.services.BusinessServices;
import in.ovaku.frame.framebackend.services.OfferService;
import in.ovaku.frame.framebackend.services.PlanService;
import in.ovaku.frame.framebackend.services.SubscriptionService;
import in.ovaku.frame.framebackend.utils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link SubscriptionService}
 * It contain different service logic for Subscription
 *
 * @author sohan
 * @version 1.0
 * @since 11/07/22
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionConverter subscriptionConverter;
    private final BusinessConverter businessConverter;
    private final BusinessServices businessServices;
    private final PlanConverter planConverter;
    private final PlanService planService;
    private final OfferConverter offerConverter;
    private final OfferService offerService;
    private final PlanOfferConverter planOfferConverter;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   SubscriptionConverter subscriptionConverter, BusinessConverter businessConverter,
                                   BusinessServices businessServices, PlanConverter planConverter,
                                   PlanService planService, OfferConverter offerConverter,
                                   OfferService offerService,
                                   PlanOfferConverter planOfferConverter
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionConverter = subscriptionConverter;
        this.businessConverter = businessConverter;
        this.businessServices = businessServices;
        this.planConverter = planConverter;
        this.planService = planService;
        this.offerConverter = offerConverter;
        this.offerService = offerService;
        this.planOfferConverter = planOfferConverter;

    }

    /**
     * This method return the list of {@link SubscriptionResponseDto}.
     *
     * @param isActive - true or false to get active or inactive SubscriptionDto.
     * @return list of subscriptionResponseDto
     */
    @Override
    public List<SubscriptionResponseDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Subscription> subscriptionList;

        if (isActive) {
            subscriptionList = subscriptionRepository.findAllActive();
        } else {
            subscriptionList = subscriptionRepository.findAll();
        }

        if (subscriptionList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched subscription list => {}", subscriptionList);
        return subscriptionList.stream()
                .map(subscriptionConverter::subscriptionToSubscriptionResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return the list of {@link SubscriptionResponseDto}.
     *
     * @param businessId -id of business. If present it return all business purchased subscription.
     * @param isActive   - true or false to get active or inactive SubscriptionDto.
     * @return list of subscriptionResponseDto
     */
    @Override
    public List<SubscriptionResponseDto> getAllByBusinessId(Long businessId, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Subscription> subscriptionList;

        if (isActive) {
            subscriptionList = subscriptionRepository.findAllByBusinessIdAndIsActive(businessId, true);
        } else {
            subscriptionList = subscriptionRepository.findAllByBusinessId(businessId);
        }

        if (subscriptionList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched subscription list => {}", subscriptionList);
        return subscriptionList.stream()
                .map(subscriptionConverter::subscriptionToSubscriptionResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link SubscriptionResponseDto} entity identified by the given {@link SubscriptionResponseDto} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive SubscriptionDto.
     * @return {@link SubscriptionResponseDto}
     */
    @Override
    public SubscriptionResponseDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Subscription> optionalSubscription;

        if (isActive) {
            optionalSubscription = subscriptionRepository.findActiveById(id);
        } else {
            optionalSubscription = subscriptionRepository.findById(id);
        }

        if (optionalSubscription.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Subscription subscription = optionalSubscription.get();
        logger.debug("Fetched Subscription entity => {}", subscription);
        SubscriptionResponseDto responseDtoDto = subscriptionConverter.subscriptionToSubscriptionResponseDto(subscription);
        logger.debug("Converted DTO => {} from Subscription entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method return a specific {@link SubscriptionResponseDto} entity identified by {@link Subscription} id.
     *
     * @param id         - id of the entity to find. Must not be null.
     * @param businessId -id of business. If present it return specific subscription of a business by subscriptionId.
     * @param isActive   - true or false to get active or inactive SubscriptionDto.
     * @return subscriptionDto
     */
    @Override
    public SubscriptionResponseDto getByBusinessId(Long id, Long businessId, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Subscription> optionalSubscription;

        if (isActive) {
            optionalSubscription = subscriptionRepository.findByIdAndBusinessIdAndIsActive(id, businessId, true);
        } else {
            optionalSubscription = subscriptionRepository.findByIdAndBusinessId(id, businessId);
        }

        if (optionalSubscription.isEmpty()) {

            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Subscription subscription = optionalSubscription.get();
        logger.debug("Fetched Subscription entity => {}", subscription);
        SubscriptionResponseDto responseDtoDto = subscriptionConverter.subscriptionToSubscriptionResponseDto(subscription);
        logger.debug("Converted DTO => {} from Subscription entity", responseDtoDto);
        return responseDtoDto;
    }

    /**
     * This method create new {@link SubscriptionResponseDto}.
     *
     * @param businessId             -id of business.Must not be null;
     * @param subscriptionRequestDto -dto to be created.
     * @return subscriptionResponseDto
     */
    @Override
    public SubscriptionResponseDto add(Long businessId, SubscriptionRequestDto subscriptionRequestDto) {
        logger.debug("Into add entity service method with data =>{}", subscriptionRequestDto);

        //validate business is active or not
        Business business = businessConverter.businessDtoToBusiness(businessServices.getById(businessId, true));
        logger.debug("Fetch active business by businessService=>{}", business);

        //extract plan from subscriptionRequestDto dto and validate
        Plan plan = planConverter.planDtoToPlan(planService.getById(subscriptionRequestDto.getPlanId(), true));
        logger.trace("Extract plan from subscriptionRequestDto");
        logger.debug("Fetch active plan by planService=>{}", plan);

        //Fetching business all active subscription
        Subscription prevSubscription = null;
        List<Subscription> subscriptionList = subscriptionRepository.findAllByBusinessIdAndIsActive(business.getId(), true);
        if (subscriptionList.size() > 0) {
            for (Subscription tempSubscription : subscriptionList) {
                //Validate fixed subscription
                if (tempSubscription.getPlan().getPlanType() == PlanType.FIXED) {
                    prevSubscription = tempSubscription;
                    break;
                }
            }
        }
        //Validate current plan with exiting subscription plan of that business.
        //If previous and current plan type is fixed then delete previous subscription
        if (prevSubscription != null && plan.getPlanType() == PlanType.FIXED) {
            delete(prevSubscription.getId());
            logger.debug("Delete previous active fixed plan=>{}", prevSubscription);
        }
        //throw error if business have no active fixed plan and current plan type is ADD_ON
        else if (prevSubscription == null && plan.getPlanType() == PlanType.ADD_ON) {
            logger.error("No fixed plan applied!");
            throw new OperationFailedException("No fixed plan applied!");
        }

        Subscription subscription = subscriptionConverter.subscriptionRequestDtoToSubscription(subscriptionRequestDto);
        logger.debug("Converted subscription entity => {} from DTO", subscription);
        //Set business and plan
        subscription.setBusiness(business);
        subscription.setPlan(plan);

        //Validate offer applied or not
        if (subscription.getOffer() == null) {
            logger.trace("No offer applied!");
            subscription.setOffer(null);
        } else {
            Offer offer = offerConverter.offerDtoToOffer(
                    offerService.getById(subscriptionRequestDto.getOfferId(), true));
            logger.trace("Extract offer from subscriptionRequestDto");
            logger.debug("Fetch active offer by offerService=>{}", offer);

            //Validate Offer is valid for this plan or not
            List<PlanOfferResponseDto> planOfferResponseDtoSet = planService.getAllOfferByPlanId(plan.getId(), true);
            logger.trace("Fetch all offers of this plan");
            PlanOffer checkOffer = null;
            //Fetch all offers in plan and validate
            for (PlanOfferResponseDto responseDto : planOfferResponseDtoSet) {
                if (responseDto.getOfferDto().getId().equals(offer.getId())) {
                    checkOffer = planOfferConverter.planOfferResponseDtoToPlanOffer(responseDto);
                }
            }
            //If offer found then apply offer in subscription otherwise throw exception
            if (checkOffer != null) {
                //validate offer is available or expired otherwise throw exception
                Date checkDate = new Date(System.currentTimeMillis());
                if (checkDate.before(checkOffer.getEndDate()) || checkDate.after(checkOffer.getStartDate())
                        || checkDate == checkOffer.getStartDate()) {
                    subscription.setOffer(offer);
                } else {
                    logger.debug("Offer is not valid");
                    throw new OperationFailedException("Offer is not valid");
                }
            } else {
                logger.debug("No offer found for selected plan");
                throw new ResourceNotFoundException("No offer found for selected plan");
            }
        }
        // TODO: 15/07/2022 set payment of subscription

        setPayment(subscriptionRequestDto,subscription);



        //Validate planType is FIXED OR ADD_ON and set date
        if (plan.getPlanType() == PlanType.ADD_ON) {
            subscription.setStartDate(new Date());
            logger.trace("Set subscription ADD_ON plan start date");
            subscription.setEndDate(prevSubscription.getEndDate());
            logger.trace("Set subscription ADD_ON plan ending date");
        } else {
            subscription.setStartDate(new Date());
            logger.trace("Set subscription FIXED plan start date");
            subscription.setEndDate(new Date(subscription.getStartDate().getTime()
                    + TimeUnit.DAYS.toMillis(plan.getDurationInDays())));
            logger.trace("Set subscription FIXED ending date");
        }
        subscription = subscriptionRepository.save(subscription);
        logger.debug("Record saved =>{} in DB", subscription);
        SubscriptionResponseDto responseDto = subscriptionConverter.subscriptionToSubscriptionResponseDto(subscription);
        logger.debug("Converted DTO => {} from subscription entity", responseDto);
        return responseDto;
    }

    /**
     * This method update {@link SubscriptionResponseDto} identified by serviceID.
     *
     * @param id                     - id of the entity to update. Must not be null.
     * @param businessId             --id of business to specifically update.Must not be null.
     * @param subscriptionRequestDto - dto to be updated.
     * @return subscriptionResponseDto
     */
    @Override
    public SubscriptionResponseDto update(Long id, Long businessId, SubscriptionRequestDto subscriptionRequestDto) {
        logger.debug("Into update entity service method with id => {} and data => {}", id, subscriptionRequestDto);
        // Validate.
        if (!subscriptionRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        //Validate plan type is FIXED
        if (subscriptionRepository.getById(id).getPlan().getPlanType() != PlanType.FIXED) {
            logger.error("Operation Failed");
            throw new OperationFailedException("Operation Failed");
        }
        Subscription subscription = subscriptionConverter
                .subscriptionRequestDtoToSubscription(subscriptionRequestDto);
        logger.debug("Converted subscription entity => {} from DTO => {}", subscription, subscriptionRequestDto);
        Subscription prevSubscription = subscriptionRepository.getById(id);
        logger.debug("Fetch subscription by id=>{} from DB", id);
        //update subscription end date
        prevSubscription.setEndDate(new Date(prevSubscription.getEndDate().getTime()
                + TimeUnit.DAYS.toMillis(prevSubscription.getPlan().getDurationInDays())));
        logger.trace("Set subscription ending date");
        //update subscription type
        prevSubscription.setSubscriptionType(SubscriptionType.RENEWAL);

        //Update Payment
        setPayment(subscriptionRequestDto,prevSubscription);

        prevSubscription = subscriptionRepository.save(prevSubscription);
        logger.debug("Subscription record updated in DB=>{}", prevSubscription);
        SubscriptionResponseDto responseDto = subscriptionConverter.subscriptionToSubscriptionResponseDto(prevSubscription);
        logger.debug("Converted DTO => {} from subscription entity", responseDto);
        return responseDto;
    }

    /**
     * This method delete the {@link Subscription} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return subscription
     */
    @Override
    public Subscription delete(Long id) {
        logger.debug("Into delete entity service method with id => {}", id);
        // Validate.
        if (subscriptionRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Subscription subscription = subscriptionRepository.getById(id);
        logger.debug("Fetching subscription entity by id => {} from DB using repository", id);
        subscription.setIsActive(false);
        subscriptionRepository.save(subscription);
        logger.debug("Business entity deleted from DB=>{}", subscription);
        return subscription;
    }

    /**
     * This method is used to set payment in subscription {@link Subscription}
     *
     *
     * @param subscriptionRequestDto
     * @param subscription
     */

    private void setPayment(SubscriptionRequestDto subscriptionRequestDto, Subscription subscription){
        Payment payment = subscriptionRequestDto.getPayment();

        BigDecimal amount=payment.getAmount();
        Offer offer = offerConverter.offerDtoToOffer(
                offerService.getById(subscriptionRequestDto.getOfferId(), true));
        logger.trace("Extract offer from subscriptionRequestDto");
        logger.debug("Fetch active offer by offerService=>{}", offer);

        OfferType offerType = offer.getOfferType();
        if(offerType.equals(OfferType.FLAT)){
            amount = amount.subtract(BigDecimal.valueOf(offer.getDiscount())) ;
        } else if(offerType.equals(OfferType.PERCENTAGE)) {
            BigDecimal discount = (amount.multiply(BigDecimal.valueOf(offer.getDiscount()))).divide(BigDecimal.valueOf(100));
            int maxDiscount = offer.getMaxDiscount();
            if(BigDecimal.valueOf(maxDiscount).compareTo(discount) >=0){
                amount = amount.subtract(discount);
            } else {
                amount = amount.subtract(BigDecimal.valueOf(offer.getMaxDiscount())) ;
            }
        }
        payment.setAmount(amount);
        subscription.setPayment(payment);

    }
}
