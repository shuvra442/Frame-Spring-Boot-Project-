package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.requests.BusinessRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;

/**
 * This is a converter interface.
 * It is used to map {@link Business} entity class with {@link BusinessDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface BusinessConverter {

    /**
     * This method convert {@link Business} to {@link BusinessRegistrationDto}
     *
     * @return {@link BusinessRegistrationDto}
     */
    BusinessRegistrationDto businessToBusinessRegDto(Business business);

    /**
     * This method convert {@link BusinessRegistrationDto} to {@link Business}
     *
     * @return {@link Business}
     */
    Business businessRegDtoToBusiness(BusinessRegistrationDto businessRegistrationDto);

    /**
     * This method convert {@link Business} to {@link BusinessDto}
     *
     * @return {@link BusinessDto}
     */
    BusinessDto businessToBusinessDto(Business business);

    /**
     * This method convert {@link BusinessDto} to {@link Business}
     *
     * @return {@link Business}
     */
    Business businessDtoToBusiness(BusinessDto businessDto);

    /**
     * This method update existing {@link Business} by {@link BusinessDto}
     *
     * @return {@link Business}
     */
    Business toUpdateBusiness(BusinessDto businessDto, Business business);
}
