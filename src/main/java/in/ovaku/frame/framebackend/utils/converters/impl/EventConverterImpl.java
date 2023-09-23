package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.EventDto;
import in.ovaku.frame.framebackend.entities.Event;
import in.ovaku.frame.framebackend.utils.converters.EventConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link EventConverter}
 * It contain converter logic for {@link Event} and {@link EventDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 06/07/22
 */
@Component
public class EventConverterImpl implements EventConverter {
    private final ModelMapper modelMapper;

    public EventConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Event} to {@link EventDto}
     *
     * @return eventDto
     */
    @Override
    public EventDto eventToEventDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    /**
     * This method convert {@link EventDto} to {@link Event}
     *
     * @return event
     */
    @Override
    public Event eventDtoToEvent(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }

    /**
     * This method update existing {@link Event} by {@link EventDto}
     *
     * @return event
     */
    @Override
    public Event toUpdateEvent(EventDto eventDto, Event event) {
        modelMapper.map(eventDto, event);
        return event;
    }
}
