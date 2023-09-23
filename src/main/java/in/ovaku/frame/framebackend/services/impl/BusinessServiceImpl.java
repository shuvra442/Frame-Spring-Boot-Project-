package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.requests.BusinessRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.exceptions.ResourceAlreadyExistsException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.BusinessRepository;
import in.ovaku.frame.framebackend.services.BusinessServices;
import in.ovaku.frame.framebackend.utils.converters.BusinessConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link BusinessServices}
 * It contain different business logic for Business
 *
 * @author sohan
 * @version 1.0
 * @since 25/05/22
 */
@Service
public class BusinessServiceImpl implements BusinessServices {
    private final BusinessRepository businessRepository;
    private final BusinessConverter businessConverter;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public BusinessServiceImpl(BusinessRepository businessRepository, BusinessConverter businessConverter) {
        this.businessRepository = businessRepository;
        this.businessConverter = businessConverter;
    }

    /**
     * This method return the list of {@link BusinessDto}.
     * It converts {@link Business} entity to {@link BusinessDto} by {@link BusinessConverter}
     *
     * @param isActive - true or false to get active or inactive business.
     * @return all businessDto entity.
     */
    @Override
    public List<BusinessDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Business> businessList;

        if (isActive) {
            businessList = businessRepository.findAllActive();
        } else {
            businessList = businessRepository.findAll();
        }

        if (businessList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched business list => {}", businessList);
        return businessList.stream()
                .map(businessConverter::businessToBusinessDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a reference of specific {@link BusinessDto} entity identified by the given {@link Business} id.
     * It convert {@link Business} to {@link BusinessDto} by {@link BusinessConverter}
     *
     * @param id       -id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive business.
     * @return a businessDto entity
     */
    @Override
    public BusinessDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Business> optionalBusiness;

        if (isActive) {
            optionalBusiness = businessRepository.findActiveById(id);
        } else {
            optionalBusiness = businessRepository.findById(id);
        }

        if (optionalBusiness.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Business business = optionalBusiness.get();
        logger.debug("Fetched business entity => {}", business);
        BusinessDto businessDto = businessConverter.businessToBusinessDto(business);
        logger.debug("Converted DTO => {} from business entity", businessDto);
        return businessDto;
    }

    /**
     * This method register new {@link Business}.
     * It gets phone number from {@link BusinessDto} body,
     * if phone number is not registered then {@link BusinessDto} convert to {@link Business} by {@link BusinessConverter},
     * and save {@link Business}.
     *
     * @param businessRegistrationDto -businessDto to be registered
     * @return businessRegistrationDto entity
     */
    @Override
    public BusinessRegistrationDto add(BusinessRegistrationDto businessRegistrationDto) {
        logger.debug("Into add entity service method with data =>{}", businessRegistrationDto);
        if (businessRepository.findByPhoneNo(businessRegistrationDto.getPhoneNo()).isPresent()) {
            logger.error("Business data already exist =>{}", businessRegistrationDto);
            throw new ResourceAlreadyExistsException("Already Exists!");
        }
        Business business = businessConverter.businessRegDtoToBusiness(businessRegistrationDto);
        logger.debug("Converted business entity => {} from DTO", business);
        business = businessRepository.save(business);
        logger.debug("Record saved =>{} in DB", business);
        BusinessRegistrationDto registrationDto = businessConverter.businessToBusinessRegDto(business);
        logger.debug("Converted DTO => {} from business entity", registrationDto);
        return registrationDto;
    }

    /**
     * This method update {@link Business} identified by businessID.
     * It find {@link Business} by id and update its changes by converting {@link BusinessDto} to {@link Business},
     * using {@link BusinessConverter} and save it.
     *
     * @param id-         id of the entity to find. Must not be null.
     * @param businessDto - businessDto to be updated
     * @return businessDto entity
     */
    @Override
    public BusinessDto update(Long id, BusinessDto businessDto) {

        logger.debug("Into update entity service method with id => {} and data => {}", id, businessDto);
        // Validate.
        if (!businessRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Business business = businessConverter.businessDtoToBusiness(businessDto);
        logger.debug("Converted business entity => {} from DTO => {}", business, businessDto);
        business.setId(id);
        business = businessRepository.save(business);
        logger.debug("Business record updated in DB=>{}", business);
        BusinessDto dto = businessConverter.businessToBusinessDto(business);
        logger.debug("Converted DTO => {} from business entity", dto);
        return dto;


//        AddressDto addressDto = businessDto.getAddressDto();
//        Business business = businessConverter.toUpdateBusiness(businessDto, businessRepository.getById(id));
//        Address address = business.getAddress();
//        if (addressDto != null) {
//            address = addressConverter.toUpdateAddress(addressDto, address);
//            business.setAddress(address);
//        }
//        business = businessRepository.save(business);
//        businessDto = businessConverter.businessToBusinessDto(business);
//        return businessDto;
    }

    /**
     * This method delete the {@link Business}entity identified by the given id
     *
     * @param id - the id of the entity to be deleted. Must not be null.
     * @return inactive businessDto entity
     */
    @Override
    public Boolean delete(Long id) {

        logger.debug("Into delete entity service method with id => {}", id);
        // Validate.
        if (businessRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Business business = businessRepository.getById(id);
        logger.debug("Fetching business entity by id => {} from DB using repository", id);
        business.setIsActive(false);
        businessRepository.save(business);
        logger.debug("Business entity deleted from DB=>{}", business);
        return true;
    }
}