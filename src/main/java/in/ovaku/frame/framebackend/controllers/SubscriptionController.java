package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.requests.SubscriptionRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.SubscriptionResponseDto;
import in.ovaku.frame.framebackend.entities.Subscription;
import in.ovaku.frame.framebackend.services.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * This class is a controller class of Subscription.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 11/07/22
 */
@RestController
@RequestMapping("/subscriptions")
@Api(tags = "Subscription Controller", value = "SubscriptionController", description = "Controller for Subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * This method is used to get all active or inactive Subscription.
     * It by default get all active Subscription.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all subscription ",
            notes = "View list of all active or inactive subscription information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAllSubscription(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all subscription entity list");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, subscriptionService.getAll(isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get all active or inactive {@link Subscription} of a business.
     * It by default get all active {@link Subscription}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all subscription of a business",
            notes = "View list of all active or inactive subscription information of a business",
            response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("businesses/{businessId}")
    public ResponseEntity<Object> getAllSubscriptionByBusinessId(@PathVariable Long businessId,
                                                                 @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all subscription of a business by businessId=>{}", businessId);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, subscriptionService.getAllByBusinessId(businessId, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific {@link Subscription}
     * It by default get active {@link Subscription}.
     *
     * @param id- id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find subscription by id",
            notes = "Provide a specific subscription id to get information ", response = SubscriptionResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubscriptionById(@PathVariable Long id,
                                                      @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting subscription entity by id => {}", id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, subscriptionService.getById(id, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific {@link Subscription} of a business.
     * It by default get active {@link Subscription}.
     *
     * @param id-        id of the entity to find. Must not be null.
     * @param businessId - id of a business.
     * @return json
     */
    @ApiOperation(value = "Find subscription by businessId and subscriptionId",
            notes = "Provide a specific businessId and subscriptionId to get information ",
            response = SubscriptionResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}/businesses/{businessId}")
    public ResponseEntity<Object> getSubscriptionByBusinessId(@PathVariable Long id,
                                                              @PathVariable Long businessId,
                                                              @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting subscription entity by businessId=> {} and subscriptionId => {}", businessId, id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, subscriptionService.getByBusinessId(id, businessId, isActive),
                "Successfully data retrieved");
    }

    /**
     * This method create subscription for a business.
     *
     * @param businessId              -id of business.
     * @param subscriptionRequestDto- dto to be registered
     * @return json
     */
    @ApiOperation(value = "Add new subscription", notes = "Create new subscription using mobile number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping("businesses/{businessId}")
    public ResponseEntity<Object> createSubscription(@PathVariable Long businessId,
                                                     @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        logger.trace("Creating new subscription entity with registration data => {}", subscriptionRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, subscriptionService.add(businessId, subscriptionRequestDto),
                "Successfully registered");
    }

    /**
     * This method is used to update a specific subscription
     *
     * @param id                      - id of the entity to find. Must not be null.
     * @param businessId              -id of business to specify subscription.
     * @param subscriptionRequestDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update subscription by id",
            notes = "Provide a specific subscription id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}/businesses/{businessId}")
    public ResponseEntity<Object> updateSubscription(@PathVariable Long id,
                                                     @PathVariable Long businessId,
                                                     @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        logger.trace("Updating subscription entity by id => {} id with data => {}", id, subscriptionRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, subscriptionService.update(id, businessId, subscriptionRequestDto),
                "Successfully updated");
    }

    /**
     * This method is used to delete a specific subscription
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a subscription by id", notes = "Provide a specific subscription id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long id) {
        logger.trace("Deleting subscription entity by id => {}", id);
        subscriptionService.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
