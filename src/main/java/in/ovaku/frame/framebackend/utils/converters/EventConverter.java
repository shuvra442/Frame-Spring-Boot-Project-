package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.EventDto;
import in.ovaku.frame.framebackend.entities.Event;

/**
 * This is a converter interface.
 * It is used to map {@link Event} entity class with {@link EventDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 06/07/22
 */
public interface EventConverter {
    /**
     * This method convert {@link Event} to {@link EventDto}
     *
     * @return {@link EventDto}
     */
    EventDto eventToEventDto(Event event);

    /**
     * This method convert {@link EventDto} to {@link Event}
     *
     * @return {@link Event}
     */
    Event eventDtoToEvent(EventDto eventDto);

    /**
     * This method update existing {@link Event} by {@link EventDto}
     *
     * @return {@link Event}
     */
    Event toUpdateEvent(EventDto eventDto, Event event);
}
