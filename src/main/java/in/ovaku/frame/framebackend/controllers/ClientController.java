package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.commons.EventDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.services.ClientService;
import in.ovaku.frame.framebackend.services.EventService;
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
 * This class is a controller class of Client.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 1/07/22
 */
@RestController
@RequestMapping("/clients")
@Api(tags = "Client Controller", value = "ClientController", description = "Controller for Business's client")
public class ClientController {
    private final ClientService clientService;
    private final EventService eventService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ClientController(ClientService clientService, EventService eventService) {
        this.clientService = clientService;
        this.eventService = eventService;
    }

    /**
     * This method is used to get all active or inactive {@link ClientDto} .
     * It by default get all active {@link ClientDto}.
     *
     * @return json
     */
    @ApiOperation(value = "Get list of all Client",
            notes = "View list of all active or inactive Client information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all client entity list");
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, clientService.getAll(isActive),
                        "Successfully data retrieved");
    }

    /**
     * This method is used to get all active or inactive {@link EventDto} for client.
     * It by default get all active {@link EventDto}.
     *
     * @param id -id of the client entity to get staff.Must not be null.
     * @return json
     */
    @ApiOperation(value = "Get list of all Event for client",
            notes = "View list of all active or inactive Event information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}/events")
    public ResponseEntity<Object> getAllEventsByClientId(@PathVariable Long id,
                                                         @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all event entity list of client");
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, eventService.getAllEventsByClientId(id, isActive),
                        "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific client by clientId {@link ClientDto}
     * It by default get active {@link ClientDto}.
     *
     * @param id -id of the client entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find Client by Client id",
            notes = "Provide a specific client id to get information ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id,
                                          @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting client entity by client id=> {}", id);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, clientService.getById(id, isActive)
                        , "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific event {@link EventDto} by clientId and id
     * It by default get active {@link EventDto}.
     *
     * @param id      -id of client
     * @param eventId -id of the event entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find event by client id and event id",
            notes = "Provide a specific client id and event id to get information ", response = EventDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}/events/{eventId}")
    public ResponseEntity<Object> getEventByClientIdAndId(@PathVariable Long id, @PathVariable Long eventId,
                                                          @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting event entity by client id => {} AND event id=> {}", id, eventId);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, eventService.getByClientIdAndId(id, eventId, isActive)
                        , "Successfully data retrieved");
    }

    /**
     * This method create new event for client by clientId.
     *
     * @param id        - id of the client entity to create event. Must not be null.
     * @param eventDto- dto to be registered
     * @return json
     */
    @ApiOperation(value = "Create new Event for client", notes = "Create new Event for Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping("/{id}/events")
    public ResponseEntity<Object> addEventsByClientId(@PathVariable Long id, @RequestBody EventDto eventDto) {
        logger.trace("Creating new event entity by client id =>{} with creation data => {}", id, eventDto);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.CREATED, eventService.add(id, eventDto)
                        , "Successfully created");
    }

    /**
     * This method is used to update a specific client.
     *
     * @param id        -id of the client entity to find. Must not be null.
     * @param clientDto -dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a client by Client id", notes = "Provide a specific client id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        logger.trace("Updating client entity by client id => {} with data => {}", id, clientDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, clientService.update(id, clientDto), "Successfully updated");
    }

    /**
     * This method is used to update a specific event for client by clientId.
     *
     * @param id       -id of the client entity to update event. Must not be null.
     * @param eventId  -id of the event entity to find. Must not be null.
     * @param eventDto -dto to be updated
     * @return json
     */
    @ApiOperation(value = "Update a event by Client id and Event id", notes = "Provide a specific client id and event id to update information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/{id}/events/{eventId}")
    public ResponseEntity<Object> updateEventsByClientId(@PathVariable Long id, @PathVariable Long eventId, @RequestBody EventDto eventDto) {
        logger.trace("Updating event entity by client id => {} and event id => {} with data => {}", eventId, id, eventDto);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, eventService.updateEventByClientIdAndId(id, eventId, eventDto), "Successfully updated");
    }

    /**
     * This method is used to delete a specific client.
     *
     * @param id -id of the client entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a client by Client id",
            notes = "Provide a specific client id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        logger.trace("Deleting client entity by client id =>{}", id);
        clientService.delete(id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }

    /**
     * This method is used to delete a specific event by clientId  {@link EventDto}
     *
     * @param id      -id of the client entity to delete event. Must not be null.
     * @param eventId -id of the event entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Delete a event by Client id and Event id",
            notes = "Provide a specific client id and event id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}/events/{eventId}")
    public ResponseEntity<Object> deleteEventsByClientId(@PathVariable Long id, @PathVariable Long eventId) {
        logger.trace("Deleting event entity by client id => {} and event id =>{}", id, eventId);
        eventService.deleteEventByClientIdAndId(id, eventId);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}