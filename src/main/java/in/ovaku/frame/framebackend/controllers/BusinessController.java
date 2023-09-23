package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.requests.BusinessRegistrationDto;
import in.ovaku.frame.framebackend.dtos.requests.ClientRegistrationDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.services.BusinessServices;
import in.ovaku.frame.framebackend.services.ClientService;
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
 * This class is a controller class of Business.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 25/05/22
 */
@RestController
@RequestMapping("/businesses")
@Api(tags = "Business Controller", value = "BusinessController", description = "Controller for Business")
public class BusinessController {
    private final BusinessServices businessServices;
    private final ClientService clientService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public BusinessController(BusinessServices businessServices, ClientService clientService) {
        this.businessServices = businessServices;
        this.clientService = clientService;
    }

    /**
     * This method is used to get all active or inactive {@link BusinessDto}.
     * It by default get all active {@link BusinessDto}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all Business ",
            notes = "View list of all active or inactive Business information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all business entity list");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, businessServices.getAll(isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get all active or inactive {@link ClientDto} for business.
     * It by default get all active {@link ClientDto}.
     *
     * @param businessId - business id.
     * @return json
     */
    @ApiOperation(value = "Find clients business by id",
            notes = "Provide a specific business id to get clients information ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{businessId}/clients")
    public ResponseEntity<Object> getAllClientsByBusinessId(@PathVariable Long businessId,
                                                            @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all client entity list by businessId => {}", businessId);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, clientService.getAllByBusinessId(businessId, isActive),
                        "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific {@link BusinessDto}
     * It by default get active {@link BusinessDto}.
     *
     * @param id- id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find business by id",
            notes = "Provide a specific business id to get information ", response = BusinessDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBusiness(@PathVariable Long id,
                                              @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting business entity by id => {}", id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, businessServices.getById(id, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific client {@link ClientDto} by businessId and clientID
     * It by default get active {@link ClientDto}.
     *
     * @param businessId- id of business entity.
     * @param id          -id of the client entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find Client by business id and client id",
            notes = "Provide a specific business id and client id to get information ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{businessId}/clients/{id}")
    public ResponseEntity<Object> getClientByBusinessIdAndId(@PathVariable Long businessId, @PathVariable Long id,
                                                             @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting client entity by business id => {} AND client id=> {}", businessId, id);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, clientService.getByBusinessIdAndId(businessId, id, isActive)
                        , "Successfully data retrieved");
    }

    /**
     * This method register new business using phone number.
     *
     * @param businessRegistrationDto- dto to be registered
     * @return json
     */
    @ApiOperation(value = "Add new Business", notes = "Register new Business using mobile number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> businessRegistration(@Valid @RequestBody BusinessRegistrationDto businessRegistrationDto) {
        // TODO: 26/06/22 Validate phone number using OTP
        logger.trace("Creating new business entity with registration data => {}", businessRegistrationDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, businessServices.add(businessRegistrationDto), "Successfully registered");
    }

    /**
     * This method add new clients by businessId
     *
     * @param businessId             -id of business
     * @param clientRegistrationDto- dto to be registered
     * @return json
     */
    @ApiOperation(value = "Add new client", notes = "Create a new client using buisnessId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping("/{businessId}/clients")
    public ResponseEntity<Object> addClientByBusinessId(@PathVariable Long businessId, @RequestBody ClientRegistrationDto clientRegistrationDto) {
        // TODO: 01/07/22 Validate phone number using OTP
        logger.trace("Creating new client entity by business id =>{} with registration data => {}", businessId, clientRegistrationDto);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.CREATED, clientService.add(businessId, clientRegistrationDto)
                        , "Successfully registered");
    }

    /**
     * This method is used to update a specific business
     *
     * @param id           - id of the entity to find. Must not be null.
     * @param businessDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a business by id", notes = "Provide a specific business id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBusiness(@Valid @PathVariable Long id, @RequestBody BusinessDto businessDto) {
        logger.trace("Updating business entity by id => {} id with data => {}", id, businessDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, businessServices.update(id, businessDto), "Successfully updated");
    }

    /**
     * This method is used to update a specific client by businessId
     *
     * @param businessId -id of business entity.
     * @param id         - id of client entity to find. Must not be null.
     * @param clientDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a clients by businessId", notes = "Provide a specific businessId and clientId to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{businessId}/clients/{id}")
    public ResponseEntity<Object> updateClientByBusinessIdAndId(@PathVariable Long businessId, @PathVariable Long id, @RequestBody ClientDto clientDto) {
        logger.trace("Updating client entity by client id => {} and business id => {} with data => {}", businessId, id, clientDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, clientService.updateByBusinessIdAndId(businessId, id, clientDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific business
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a business by id", notes = "Provide a specific business id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBusiness(@PathVariable Long id) {
        logger.trace("Deleting business entity by id => {}", id);
        businessServices.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }

    /**
     * This method is used to delete a specific client using businessId and clientId
     *
     * @param id         - id of the entity to delete. Must not be null.
     * @param businessId -id of business.
     * @return json
     */
    @ApiOperation(value = "Delete a client by id and businessId", notes = "Provide a specific client id and businessId to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{businessId}/clients/{id}")
    public ResponseEntity<Object> deleteClientByBusinessIdAndId(@PathVariable Long businessId, @PathVariable Long id) {
        logger.trace("Deleting client entity by business id => {} and client id =>{}", businessId, id);
        clientService.deleteByBusinessIdAndId(businessId, id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
