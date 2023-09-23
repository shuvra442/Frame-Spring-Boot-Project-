package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import in.ovaku.frame.framebackend.entities.Service;

/**
 * This is a converter interface.
 * It is used to map {@link Service} entity class with {@link ServiceDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 07/07/22
 */
public interface ServiceConverter {

    /**
     * This method convert {@link Service} to {@link ServiceDto}
     *
     * @return {@link ServiceDto}
     */
    ServiceDto serviceToServiceDto(Service service);

    /**
     * This method convert {@link ServiceDto} to {@link Service}
     *
     * @return {@link Service}
     */
    Service serviceDtoToService(ServiceDto serviceDto);

    /**
     * This method update existing {@link Service} by {@link ServiceDto}
     *
     * @return {@link Service}
     */
    Service toUpdateService(ServiceDto serviceDto, Service service);
}
