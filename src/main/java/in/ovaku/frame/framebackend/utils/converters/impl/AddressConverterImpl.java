package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.AddressDto;
import in.ovaku.frame.framebackend.entities.Address;
import in.ovaku.frame.framebackend.utils.converters.AddressConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link AddressConverter}
 * It contain converter logic for {@link Address} and {@link AddressDto}
 *
 * @author sohan
 * @version 1.0
 * @since 25/05/22
 */
@Component
public class AddressConverterImpl implements AddressConverter {
    private ModelMapper modelMapper;

    public AddressConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Address} to {@link AddressDto}
     *
     * @return addressDto
     */
    @Override
    public AddressDto addressToAddressDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    /**
     * This method convert {@link AddressDto} to {@link Address}
     *
     * @return address
     */
    @Override
    public Address addressDtoToAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    /**
     * This method update existing {@link Address} by {@link AddressDto}
     *
     * @return address
     */
    @Override
    public Address toUpdateAddress(AddressDto addressDto, Address address) {
        modelMapper.map(addressDto, address);
        return address;
    }
}
