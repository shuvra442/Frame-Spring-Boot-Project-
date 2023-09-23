package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.services.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * This class is a controller class of Service.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 07/07/22
 */
@RestController
@RequestMapping("/services")
@Api(tags = "Service Controller", value = "ServiceController", description = "Controller for Service")
public class ServiceController {
    private final ServiceService serviceService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    /**
     * This method is used to get all active or inactive {@link ServiceDto}.
     * It by default get all active {@link ServiceDto}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all Service ",
            notes = "View list of all active or inactive Service information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all service entity list");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, serviceService.getAll(isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific {@link ServiceDto}
     * It by default get active {@link ServiceDto}.
     *
     * @param id- id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find service by id",
            notes = "Provide a specific service id to get information ", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getService(@PathVariable Long id,
                                             @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting service entity by id => {}", id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, serviceService.getById(id, isActive), "Successfully data retrieved");
    }

    /**
     * This method create new service.
     *
     * @param serviceDto- dto to be created
     * @return json
     */
    @ApiOperation(value = "Add new Service", notes = "Create new Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> createService(@Valid @RequestBody ServiceDto serviceDto) {
        logger.trace("Creating new service entity with data => {}", serviceDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, serviceService.add(serviceDto), "Successfully created");
    }

    /**
     * This method is used to update a specific service
     *
     * @param id          - id of the entity to find. Must not be null.
     * @param serviceDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a service by id", notes = "Provide a specific service id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateService(@Valid @PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        logger.trace("Updating service entity by id => {} id with data => {}", id, serviceDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, serviceService.update(id, serviceDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific service
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a service by id", notes = "Provide a specific service id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        logger.trace("Deleting service entity by id => {}", id);
        serviceService.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
