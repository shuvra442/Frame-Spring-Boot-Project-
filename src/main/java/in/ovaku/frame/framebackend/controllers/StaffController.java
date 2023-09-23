package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.StaffDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.services.StaffService;
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
 * This class is a controller class of Staff.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 1/07/22
 */
@RestController
@RequestMapping("{businessId}/staffs")
@Api(tags = "Staff Controller", value = "StaffController", description = "Controller for Business's staff")
public class StaffController {
    private final StaffService staffService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    /**
     * This method is used to get all active or inactive {@link StaffDto} for business.
     * It by default get all active {@link StaffDto}.
     *
     * @param businessId -id of the business entity to get staff.Must not be null.
     * @return json
     */
    @ApiOperation(value = "Get list of all Staff for business",
            notes = "View list of all active or inactive Staff information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@PathVariable Long businessId,
                                         @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all staff entity list of business");
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, staffService.getAll(businessId, isActive),
                        "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific business {@link StaffDto}
     * It by default get active {@link StaffDto}.
     *
     * @param businessId- id of the business entity to get staff. Must not be null.
     * @param id          -id of the staff entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find Staff by Business id and Staff id",
            notes = "Provide a specific business id and Staff id to get information ", response = StaffDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getStaff(@PathVariable Long businessId, @PathVariable Long id,
                                           @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting staff entity by business id=>{} and staff id=> {}", businessId, id);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, staffService.getById(businessId, id, isActive)
                        , "Successfully data retrieved");
    }

    /**
     * This method register new staff for business using phone number.
     *
     * @param businessId - id of the business entity to register staff. Must not be null.
     * @param staffDto-  dto to be registered
     * @return json
     */
    @ApiOperation(value = "Add new Staff for business", notes = "Register new Staff for Business using mobile number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> staffRegistration(@PathVariable Long businessId, @RequestBody StaffDto staffDto) {
        // TODO: 01/07/22 Validate phone number using OTP
        logger.trace("Creating new staff entity by business id =>{} with registration data => {}", businessId, staffDto);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.CREATED, staffService.add(businessId, staffDto)
                        , "Successfully registered");
    }

    /**
     * This method is used to update a specific staff for business.
     *
     * @param businessId -id of the business entity to update staff. Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @param staffDto   -dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a staff by Business id and Staff id", notes = "Provide a specific business id and staff id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStaff(@PathVariable Long businessId, @PathVariable Long id, @RequestBody StaffDto staffDto) {
        logger.trace("Updating staff entity by business id => {} and staff id => {} with data => {}", businessId, id, staffDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, staffService.update(businessId, id, staffDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific business {@link StaffDto}
     *
     * @param businessId -id of the business entity to delete staff. Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a staff by Business id and Staff id",
            notes = "Provide a specific business id and staff id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStaff(@PathVariable Long businessId, @PathVariable Long id) {
        logger.trace("Deleting staff entity by business id => {} and staff id =>{}", businessId, id);
        staffService.delete(businessId, id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
