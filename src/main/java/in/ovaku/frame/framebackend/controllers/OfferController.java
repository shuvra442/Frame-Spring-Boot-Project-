package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.services.OfferService;
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
 * This class is a controller class of Offer.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 10/07/22
 */
@RestController
@RequestMapping("/offers")
@Api(tags = "Offer Controller", value = "OfferController", description = "Controller for Offer")

public class OfferController {
    private final OfferService offerService;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    /**
     * This method is used to get all active or inactive {@link OfferDto}.
     * It by default get all active {@link OfferDto}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all Offer ",
            notes = "View list of all active or inactive Offer information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all offer entity list");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, offerService.getAll(isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific {@link OfferDto}
     * It by default get active {@link OfferDto}.
     *
     * @param id- id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find offer by id",
            notes = "Provide a specific offer id to get information ", response = OfferDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOffer(@PathVariable Long id,
                                           @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting offer entity by id => {}", id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, offerService.getById(id, isActive), "Successfully data retrieved");
    }

    /**
     * This method create new offer.
     *
     * @param offerDto- dto to be created
     * @return json
     */
    @ApiOperation(value = "Add new Offer", notes = "Create new Offer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> createOffer(@Valid @RequestBody OfferDto offerDto) {
        logger.trace("Creating new offer entity with data => {}", offerDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, offerService.add(offerDto), "Successfully created");
    }

    /**
     * This method is used to update a specific offer
     *
     * @param id        - id of the entity to find. Must not be null.
     * @param offerDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a offer by id", notes = "Provide a specific offer id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOffer(@Valid @PathVariable Long id, @RequestBody OfferDto offerDto) {
        logger.trace("Updating offer entity by id => {} id with data => {}", id, offerDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, offerService.update(id, offerDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific offer
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a offer by id", notes = "Provide a specific offer id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOffer(@PathVariable Long id) {
        logger.trace("Deleting offer entity by id => {}", id);
        offerService.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
