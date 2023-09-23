package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import in.ovaku.frame.framebackend.entities.Service;
import in.ovaku.frame.framebackend.utils.converters.ServiceConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link ServiceConverter}
 * It contain converter logic for {@link Service} and {@link ServiceDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 07/07/22
 */
@Component
public class ServiceConverterImpl implements ServiceConverter {
    private final ModelMapper modelMapper;

    public ServiceConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Service} to {@link ServiceDto}
     *
     * @return serviceDto
     */
    @Override
    public ServiceDto serviceToServiceDto(Service service) {
        return modelMapper.map(service, ServiceDto.class);
    }

    /**
     * This method convert {@link ServiceDto} to {@link Service}
     *
     * @return service
     */
    @Override
    public Service serviceDtoToService(ServiceDto serviceDto) {
        return modelMapper.map(serviceDto, Service.class);
    }

    /**
     * This method update existing {@link Service} by {@link ServiceDto}
     *
     * @return service
     */
    @Override
    public Service toUpdateService(ServiceDto serviceDto, Service service) {
        modelMapper.map(serviceDto, service);
        return service;
    }
}
