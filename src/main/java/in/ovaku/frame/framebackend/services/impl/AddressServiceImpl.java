package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.AddressDto;
import in.ovaku.frame.framebackend.entities.Address;
import in.ovaku.frame.framebackend.repositories.AddressRepository;
import in.ovaku.frame.framebackend.services.AddressService;
import in.ovaku.frame.framebackend.utils.converters.AddressConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class implement an interface {@link AddressService}
 * It contain different business logic for Address
 *
 * @author sohan
 * @version 1.0
 * @since 25/05/22
 */
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AddressServiceImpl(AddressRepository addressRepository, AddressConverter addressConverter) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    /**
     * This method return {@link AddressDto} identified by business id.
     *
     * @param id - id of business entity to find. Must not be null.
     * @return addressDto
     */
    @Override
    public AddressDto getByBusinessId(Long id) {
        logger.debug("Into getAllAddressByBusiness service method with id => {}", id);
        Address address = addressRepository.getByBusinessesId(id);
        logger.debug("Fetched Entity => {}", address);
        if (address == null) {
            return null;
        }
        return addressConverter.addressToAddressDto(address);
    }
}
