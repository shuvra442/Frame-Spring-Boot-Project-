package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.AddressDto;
import in.ovaku.frame.framebackend.entities.Address;

/**
 * This is a converter interface.
 * It is used to map {@link Address} entity class with {@link AddressDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface AddressConverter {

    /**
     * This method convert {@link Address} to {@link AddressDto}
     *
     * @return {@link AddressDto}
     */
    AddressDto addressToAddressDto(Address address);

    /**
     * This method convert {@link AddressDto} to {@link Address}
     *
     * @return {@link Address}
     */
    Address addressDtoToAddress(AddressDto addressDto);

    /**
     * This method update existing {@link Address} by {@link AddressDto}
     *
     * @return {@link Address}
     */
    Address toUpdateAddress(AddressDto addressDto, Address address);
}
