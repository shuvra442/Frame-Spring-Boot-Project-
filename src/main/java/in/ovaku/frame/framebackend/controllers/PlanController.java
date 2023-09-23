package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanOfferRequestDto;
import in.ovaku.frame.framebackend.dtos.requests.PlanServiceRequestDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanOfferResponseDto;
import in.ovaku.frame.framebackend.dtos.responses.PlanServiceResponseDto;
import in.ovaku.frame.framebackend.services.PlanService;
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
 * This class is a controller class of Plan.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 08/07/22
 */
@RestController
@RequestMapping("/plans")
@Api(tags = "Plan Controller", value = "PlanController", description = "Controller for Plan")
public class PlanController {
    private final PlanService planService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    /**
     * This method is used to get all active or inactive {@link PlanDto}.
     * It by default get all active {@link PlanDto}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all Plan ",
            notes = "View list of all active or inactive Plan information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all plan entity list");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getAll(isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get all active or inactive PlanService.
     * It by default get all active PlanService.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all plan-service ",
            notes = "View list of all active or inactive plan-service information by plan id", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{planId}/services")
    public ResponseEntity<Object> getAllServicesByPlanId(@PathVariable Long planId, @RequestParam(required = false,
            defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all services of a plan");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getAllServicesByPlanId(planId, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get all active or inactive PlanOffer.
     * It by default get all active PlanOffer.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all plan-offer ",
            notes = "View list of all active or inactive plan-offer information by plan id", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{planId}/offers")
    public ResponseEntity<Object> getAllOfferByPlanId(@PathVariable Long planId, @RequestParam(required = false,
            defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all offers of a plan");
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getAllOfferByPlanId(planId, isActive), "Successfully data retrieved");
    }


    /**
     * This method is used to get a specific {@link PlanDto}
     * It by default get active {@link PlanDto}.
     *
     * @param id- id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find plan by id",
            notes = "Provide a specific plan id to get information ", response = PlanDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlan(@PathVariable Long id,
                                          @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting plan entity by id => {}", id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getById(id, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific PlanService.
     * It by default get active PlanService.
     *
     * @param planId --id of plan.Must not be null.
     * @param id-    id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find plan-service by planId and planService id",
            notes = "Provide a specific planId and planService id to get information ", response = PlanServiceResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{planId}/services/{id}")
    public ResponseEntity<Object> getServicesByPlanId(@PathVariable Long planId, @PathVariable Long id,
                                                      @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting service of a plan by planId=> {} and id => {}", planId, id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getServicesByPlanId(planId, id, isActive), "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific PlanOffer.
     * It by default get active planOffer.
     *
     * @param planId --id of plan.Must not be null.
     * @param id-    id of the entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find plan-offer by planId and PlanOffer id",
            notes = "Provide a specific planId and planOffer id to get information ", response = PlanOfferResponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{planId}/offers/{id}")
    public ResponseEntity<Object> getOfferByPlanId(@PathVariable Long planId, @PathVariable Long id,
                                                   @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting offer of a plan by planId=> {} and id => {}", planId, id);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.getOfferByPlanId(planId, id, isActive), "Successfully data retrieved");
    }

    /**
     * This method create new plan.
     *
     * @param planDto- dto to be created
     * @return json
     */
    @ApiOperation(value = "Add new Plan", notes = "Create new Plan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> createPlan(@RequestBody PlanDto planDto) {
        logger.trace("Creating new plan entity with data => {}", planDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, planService.add(planDto), "Successfully created");
    }

    /**
     * This method create new planService.
     *
     * @param planId                 -id of plan.Must not be null
     * @param planServiceRequestDto- dto to be created
     * @return json
     */
    @ApiOperation(value = "Add new plan-service ", notes = "Create new plan-service by plan id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping("/{planId}/services")
    public ResponseEntity<Object> createServiceByPlanId(@PathVariable Long planId
            , @RequestBody PlanServiceRequestDto planServiceRequestDto) {
        logger.trace("Creating new plan service entity with data => {}", planServiceRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, planService.addServiceByPlanId(planId, planServiceRequestDto), "Successfully created");
    }

    /**
     * This method create new planOffer.
     *
     * @param planId               -id of plan.Must not be null
     * @param planOfferRequestDto- dto to be created
     * @return json
     */
    @ApiOperation(value = "Add new plan-offer ", notes = "Create new plan-offer by plan id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping("/{planId}/offers")
    public ResponseEntity<Object> createOfferByPlanId(@PathVariable Long planId
            , @RequestBody PlanOfferRequestDto planOfferRequestDto) {
        logger.trace("Creating new plan offer entity with data => {}", planOfferRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.CREATED, planService.addOfferByPlanId(planId, planOfferRequestDto), "Successfully created");
    }

    /**
     * This method is used to update a specific plan
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param planDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a plan by id", notes = "Provide a specific plan id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable Long id, @RequestBody PlanDto planDto) {
        logger.trace("Updating plan entity by id => {} id with data => {}", id, planDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.update(id, planDto), "Successfully updated");
    }

    /**
     * This method is used to update a specific planService
     *
     * @param planId                 -id of plan.Must not be null.
     * @param id                     - id of the entity to find. Must not be null.
     * @param planServiceRequestDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a plan-service by plan id and planService id",
            notes = "Provide a specific plan id and planService id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{planId}/services/{id}")
    public ResponseEntity<Object> updateServiceByPlanId(@PathVariable Long planId, @PathVariable Long id,
                                                        @RequestBody PlanServiceRequestDto planServiceRequestDto) {
        logger.trace("Updating planService by planId => {}, id=> {} with data => {}", planId, id, planServiceRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.updateServiceByPlanId(planId, id, planServiceRequestDto), "Successfully updated");
    }

    /**
     * This method is used to update a specific planOffer
     *
     * @param planId               -id of plan.Must not be null.
     * @param id                   - id of the entity to find. Must not be null.
     * @param planOfferRequestDto- dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a plan-offer by plan id and planOffer id",
            notes = "Provide a specific plan id and planOffer id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{planId}/offers/{id}")
    public ResponseEntity<Object> updateOfferByPlanId(@PathVariable Long planId, @PathVariable Long id,
                                                      @RequestBody PlanOfferRequestDto planOfferRequestDto) {
        logger.trace("Updating planOffer by planId => {}, id=> {} with data => {}", planId, id, planOfferRequestDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, planService.updateOfferByPlanId(planId, id, planOfferRequestDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific plan
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a plan by id", notes = "Provide a specific plan id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlan(@PathVariable Long id) {
        logger.trace("Deleting plan entity by id => {}", id);
        planService.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }

    /**
     * This method is used to delete a specific planService
     *
     * @param planId -id of plan.Must not be null.
     * @param id     - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a plan-service by planId and id",
            notes = "Provide a specific plan id and  planService id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{planId}/services/{id}")
    public ResponseEntity<Object> deleteServiceByPlanId(@PathVariable Long planId, @PathVariable Long id) {
        logger.trace("Deleting planService entity by planId=> {} and id => {}", planId, id);
        planService.deleteServiceByPlanId(planId, id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }

    /**
     * This method is used to delete a specific planOffer
     *
     * @param planId -id of plan.Must not be null.
     * @param id     - id of the entity to delete. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a plan-offer by planId and id",
            notes = "Provide a specific plan id and  planOffer id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{planId}/offers/{id}")
    public ResponseEntity<Object> deleteOfferByPlanId(@PathVariable Long planId, @PathVariable Long id) {
        logger.trace("Deleting planOffer entity by planId=> {} and id => {}", planId, id);
        planService.deleteOfferByPlanId(planId, id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
