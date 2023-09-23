package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.EventDto;
import in.ovaku.frame.framebackend.entities.Client;
import in.ovaku.frame.framebackend.entities.Event;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.EventRepository;
import in.ovaku.frame.framebackend.services.ClientService;
import in.ovaku.frame.framebackend.services.EventService;
import in.ovaku.frame.framebackend.utils.converters.EventConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link EventService}
 * It contain different business logic for Event
 *
 * @author sohan
 * @version 1.0
 * @since 06/07/22
 */
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventConverter eventConverter;
    private final ClientService clientService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public EventServiceImpl(EventRepository eventRepository, EventConverter eventConverter, ClientService clientService) {
        this.eventRepository = eventRepository;
        this.eventConverter = eventConverter;
        this.clientService = clientService;
    }

    /**
     * This method return the list of {@link EventDto}.
     *
     * @param isActive - true or false to get active or inactive event.
     * @return list of eventDto
     */
    @Override
    public List<EventDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Event> eventList;
        if (isActive) {
            eventList = eventRepository.findAllActive();
        } else {
            eventList = eventRepository.findAll();
        }
        if (eventList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched event list => {}", eventList);
        return eventList.stream()
                .map(eventConverter::eventToEventDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return the list of {@link EventDto} by clienId.
     *
     * @param clientId -id of client
     * @param isActive - true or false to get active or inactive event.
     * @return list of eventDto
     */
    @Override
    public List<EventDto> getAllEventsByClientId(long clientId, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Event> eventList;
        if (isActive) {
            eventList = eventRepository.findAllActiveByClientId(clientId);
        } else {
            eventList = eventRepository.findAllByClientId(clientId);
        }
        if (eventList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched event list => {}", eventList);
        return eventList.stream()
                .map(eventConverter::eventToEventDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link EventDto} entity identified by
     * the given {@link Client} id and {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param clientId - id of the client.
     * @param isActive - true or false to get active or inactive client.
     * @return {@link EventDto}
     */
    @Override
    public EventDto getByClientIdAndId(Long clientId, Long id, Boolean isActive) {
        logger.debug("Into get entity service method with client id => {} and event id => {}", clientId, id);
        Optional<Event> optionalEvent;

        if (isActive) {
            optionalEvent = eventRepository.findActiveByClientIdAndId(clientId, id);
        } else {
            optionalEvent = eventRepository.findByClientIdAndId(clientId, id);
        }
        if (optionalEvent.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Event event = optionalEvent.get();
        logger.debug("Fetched event entity => {}", event);
        EventDto eventDto = eventConverter.eventToEventDto(event);
        logger.debug("Converted DTO => {} from event entity", eventDto);
        return eventDto;
    }

    @Override
    public Event findById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with  event id => {}", id);
        Optional<Event> optionalEvent = eventRepository.findByIdAndIsActive(id, true);
        if (optionalEvent.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Event event = optionalEvent.get();
        logger.debug("Fetched event entity => {}", event);
        return event;
    }

    /**
     * This method return a specific {@link EventDto} entity identified by
     * the given {@link Client} id and {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive event.
     * @return eventDto
     */
    @Override
    public EventDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with event id => {}", id);
        Optional<Event> optionalEvent;
        if (isActive) {
            optionalEvent = eventRepository.findActiveById(id);
        } else {
            optionalEvent = eventRepository.findById(id);
        }
        if (optionalEvent.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Event event = optionalEvent.get();
        logger.debug("Fetched event entity => {}", event);
        EventDto eventDto = eventConverter.eventToEventDto(event);
        logger.debug("Converted DTO => {} from event entity", eventDto);
        return eventDto;
    }

    /**
     * This method create new {@link Event} for {@link Client}.
     *
     * @param eventDto -eventDto to be created.
     * @return eventDto
     */
    @Override
    public EventDto add(Long clientId, EventDto eventDto) {
        logger.debug("Into add entity service method with client id => {} and data =>{}", clientId, eventDto);
        Event event = eventConverter.eventDtoToEvent(eventDto);
        logger.debug("Converted event entity => {} from DTO", event);
        event.setClient(clientService.getById(clientId));
        event.setStaff(null);
        event = eventRepository.save(event);
        logger.debug("Record saved =>{} in DB", event);
        eventDto = eventConverter.eventToEventDto(event);
        logger.debug("Converted DTO => {} from event entity", eventDto);
        return eventDto;
    }

    /**
     * This method update {@link Event} identified by {@link Event} id.
     *
     * @param id       -id of the event entity to find. Must not be null.
     * @param eventDto - {@link EventDto} to be updated.
     * @return eventDto
     */
    @Override
    public EventDto update(Long id, EventDto eventDto) {
        logger.debug("Into update entity service method with" +
                " staff id => {} and data => {}", id, eventDto);
        //Validate.
        if (!eventRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Event event = eventConverter.eventDtoToEvent(eventDto);
        logger.debug("Converted event entity => {} from DTO => {}", event, eventDto);
        event.setId(id);
        event.setClient(eventRepository.getById(id).getClient());
        event.setStaff(null);
        event = eventRepository.save(event);
        logger.debug("Event record updated in DB=>{}", event);
        eventDto = eventConverter.eventToEventDto(event);
        logger.debug("Converted DTO => {} from event entity", eventDto);
        return eventDto;
    }

    /**
     * This method update {@link Event} identified by {@link Client} id and {@link Event} id.
     *
     * @param clientId - id of the {@link Client} entity to update event.Must not be null.
     * @param id       -id of the event entity to find. Must not be null.
     * @param eventDto - {@link EventDto} to be updated.
     * @return eventDto
     */
    @Override
    public EventDto updateEventByClientIdAndId(Long clientId, Long id, EventDto eventDto) {
        logger.debug("Into update entity service method with client id => {}," +
                " staff id => {} and data => {}", clientId, id, eventDto);
        //Validate.
        if (!eventRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Event event = eventConverter.eventDtoToEvent(eventDto);
        logger.debug("Converted event entity => {} from DTO => {}", event, eventDto);
        event.setId(id);
        event.setClient(clientService.getById(clientId));
        event.setStaff(null);
        event = eventRepository.save(event);
        logger.debug("Event record updated in DB=>{}", event);
        eventDto = eventConverter.eventToEventDto(event);
        logger.debug("Converted DTO => {} from event entity", eventDto);
        return eventDto;
    }

    /**
     * This method delete {@link Event} entity identified by  {@link Event} id.
     *
     * @param id -id of the event entity to delete. Must not be null.
     * @return inactive eventDto entity
     */
    @Override
    public Boolean delete(Long id) {
        logger.debug("Into delete entity service method with event id => {}", id);
        //Validate
        if (eventRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Event event = eventRepository.getById(id);
        logger.debug("Fetching staff entity by id => {} from DB using repository", id);
        event.setIsActive(false);
        event = eventRepository.save(event);
        logger.debug("Event entity deleted from DB=>{}", event);
        return true;
    }

    /**
     * This method delete {@link Event} entity identified by {@link Client} id and {@link Event} id
     *
     * @param clientId -id of client
     * @param id       -id of the event entity to delete. Must not be null.
     * @return true or false
     */
    @Override
    public Boolean deleteEventByClientIdAndId(Long clientId, Long id) {
        logger.debug("Into delete entity service method with event id => {}", id);
        //Validate
        if (eventRepository.findActiveByClientIdAndId(clientId, id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Event event = eventRepository.getById(id);
        logger.debug("Fetching staff entity by id => {} from DB using repository", id);
        event.setIsActive(false);
        event = eventRepository.save(event);
        logger.debug("Event entity deleted from DB=>{}", event);
        return true;
    }
}
