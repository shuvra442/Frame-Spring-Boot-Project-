package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.requests.BusinessRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.utils.converters.BusinessConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link BusinessConverter}
 * It contain converter logic for {@link Business} and {@link BusinessDto}
 *
 * @author sohan
 * @version 1.0
 * @since 25/05/22
 */
@Component
public class BusinessConverterImpl implements BusinessConverter {
    private final ModelMapper modelMapper;

    public BusinessConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Business} to {@link BusinessRegistrationDto}
     *
     * @return businessRegistrationDto
     */
    @Override
    public BusinessRegistrationDto businessToBusinessRegDto(Business business) {
        return modelMapper.map(business, BusinessRegistrationDto.class);
    }

    /**
     * This method convert {@link BusinessRegistrationDto} to {@link Business}
     *
     * @return business
     */
    @Override
    public Business businessRegDtoToBusiness(BusinessRegistrationDto businessRegistrationDto) {
        return modelMapper.map(businessRegistrationDto, Business.class);
    }

    /**
     * This method convert {@link Business} to {@link BusinessDto}
     *
     * @return businessDto
     */
    @Override
    public BusinessDto businessToBusinessDto(Business business) {
        return modelMapper.map(business, BusinessDto.class);
    }

    /**
     * This method convert {@link BusinessDto} to {@link Business}
     *
     * @return business
     */
    @Override
    public Business businessDtoToBusiness(BusinessDto businessDto) {
        return modelMapper.map(businessDto, Business.class);
    }

    /**
     * This method update existing {@link Business} by {@link BusinessDto}
     *
     * @return business
     */
    @Override
    public Business toUpdateBusiness(BusinessDto businessDto, Business business) {
        modelMapper.map(businessDto, business);
        return business;
    }
}
