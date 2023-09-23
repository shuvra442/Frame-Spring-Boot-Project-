package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import in.ovaku.frame.framebackend.entities.Service;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.ServiceRepository;
import in.ovaku.frame.framebackend.services.ServiceService;
import in.ovaku.frame.framebackend.utils.converters.ServiceConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link ServiceService}
 * It contain different service logic for Service
 *
 * @author sohan
 * @version 1.0
 * @since 07/07/22
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceConverter serviceConverter;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceConverter serviceConverter) {
        this.serviceRepository = serviceRepository;
        this.serviceConverter = serviceConverter;
    }

    /**
     * This method return the list of {@link ServiceDto}.
     *
     * @param isActive - true or false to get active or inactive service.
     * @return list of serviceDto
     */
    @Override
    public List<ServiceDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Service> serviceList;

        if (isActive) {
            serviceList = serviceRepository.findAllActive();
        } else {
            serviceList = serviceRepository.findAll();
        }

        if (serviceList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched service list => {}", serviceList);
        return serviceList.stream()
                .map(serviceConverter::serviceToServiceDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link ServiceDto} entity identified by the given {@link Service} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive service.
     * @return serviceDto entity
     */
    @Override
    public ServiceDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Service> optionalService;

        if (isActive) {
            optionalService = serviceRepository.findActiveById(id);
        } else {
            optionalService = serviceRepository.findById(id);
        }

        if (optionalService.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Service service = optionalService.get();
        logger.debug("Fetched service entity => {}", service);
        ServiceDto serviceDto = serviceConverter.serviceToServiceDto(service);
        logger.debug("Converted DTO => {} from service entity", serviceDto);
        return serviceDto;
    }

    /**
     * This method register new {@link Service}.
     *
     * @param serviceDto -dto to be created.
     * @return serviceDto entity
     */
    @Override
    public ServiceDto add(ServiceDto serviceDto) {
        logger.debug("Into add entity service method with data =>{}", serviceDto);
        Service service = serviceConverter.serviceDtoToService(serviceDto);
        logger.debug("Converted service entity => {} from DTO", service);
        service = serviceRepository.save(service);
        logger.debug("Record saved =>{} in DB", service);
        ServiceDto dto = serviceConverter.serviceToServiceDto(service);
        logger.debug("Converted DTO => {} from service entity", dto);
        return dto;
    }

    /**
     * This method update {@link Service} identified by serviceID.
     *
     * @param id         - id of the entity to update. Must not be null.
     * @param serviceDto - {@link ServiceDto} to be updated.
     * @return serviceDto entity
     */
    @Override
    public ServiceDto update(Long id, ServiceDto serviceDto) {
        logger.debug("Into update entity service method with id => {} and data => {}", id, serviceDto);
        // Validate.
        if (!serviceRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Service service = serviceConverter.serviceDtoToService(serviceDto);
        logger.debug("Converted service entity => {} from DTO => {}", service, serviceDto);
        service.setId(id);
        service = serviceRepository.save(service);
        logger.debug("Service record updated in DB=>{}", service);
        ServiceDto dto = serviceConverter.serviceToServiceDto(service);
        logger.debug("Converted DTO => {} from service entity", dto);
        return dto;
    }

    /**
     * This method delete the {@link Service} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return inactive serviceDto entity
     */
    @Override
    public Boolean delete(Long id) {
        logger.debug("Into delete entity service method with id => {}", id);
        // Validate.
        if (serviceRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Service service = serviceRepository.getById(id);
        logger.debug("Fetching service entity by id => {} from DB using repository", id);
        service.setIsActive(false);
        serviceRepository.save(service);
        logger.debug("Service entity deleted from DB=>{}", service);
        return true;
    }
}
