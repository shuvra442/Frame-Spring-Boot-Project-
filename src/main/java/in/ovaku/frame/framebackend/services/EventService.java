package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.EventDto;
import in.ovaku.frame.framebackend.entities.Client;
import in.ovaku.frame.framebackend.entities.Event;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for event.
 *
 * @author Sohan
 * @version 1.0
 * @since 06/07/22
 */
public interface EventService {

    /**
     * This method return the list of {@link EventDto}.
     *
     * @param isActive - true or false to get active or inactive event.
     * @return list of {@link EventDto}
     */
    List<EventDto> getAll(Boolean isActive);

    /**
     * This method return the list of {@link EventDto} by clienId.
     *
     * @param clientId -id of client
     * @param isActive - true or false to get active or inactive event.
     * @return list of eventDto
     */
    List<EventDto> getAllEventsByClientId(long clientId, Boolean isActive);

    /**
     * This method return a specific {@link EventDto} entity identified by
     * the given {@link Client} id and {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param clientId - id of the client.
     * @param isActive - true or false to get active or inactive client.
     * @return {@link EventDto}
     */
    EventDto getByClientIdAndId(Long clientId, Long id, Boolean isActive);

    /**
     * This method return a specific {@link EventDto} entity identified by
     * the given {@link Client} id and {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive event.
     * @return {@link EventDto}
     */
    EventDto getById(Long id, Boolean isActive);

    /**
     * This method return a specific {@link EventDto} entity identified by {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive event.
     * @return {@link Event}
     */
    Event findById(Long id, Boolean isActive);

    /**
     * This method create new {@link Event} for {@link Client}.
     *
     * @param clientId - id of {@link Client} entity to add {@link Event}.Must not be null.
     * @param eventDto -eventDto to be created.
     * @return eventDto
     */
    EventDto add(Long clientId, EventDto eventDto);

    /**
     * This method update {@link Event} identified by  {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param eventDto - {@link EventDto} to be updated.
     * @return {@link EventDto}
     */
    EventDto update(Long id, EventDto eventDto);

    /**
     * This method update {@link Event} identified by {@link Client} id and {@link Event} id.
     *
     * @param clientId - id of the {@link Client} entity to update event.Must not be null.
     * @param id       -id of the event entity to find. Must not be null.
     * @param eventDto - {@link EventDto} to be updated.
     * @return eventDto
     */
    EventDto updateEventByClientIdAndId(Long clientId, Long id, EventDto eventDto);

    /**
     * This method delete {@link Event} entity identified by {@link Event} id
     *
     * @param id -id of the event entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);

    /**
     * This method delete {@link Event} entity identified by {@link Client} id and {@link Event} id
     *
     * @param clientId -id of client
     * @param id       -id of the event entity to delete. Must not be null.
     * @return true or false
     */
    Boolean deleteEventByClientIdAndId(Long clientId, Long id);
}