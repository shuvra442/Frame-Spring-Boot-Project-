package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.AddressDto;

/**
 * This interface provides create, retrieve, update and delete operation for address.
 *
 * @author Sohan
 * @version 1.0
 * @since 25/05/22
 */
public interface AddressService {

    /**
     * This method return {@link AddressDto} identified by business id.
     *
     * @param id - id of business entity to find. Must not be null.
     * @return {@link AddressDto}
     */
    AddressDto getByBusinessId(Long id);
}
