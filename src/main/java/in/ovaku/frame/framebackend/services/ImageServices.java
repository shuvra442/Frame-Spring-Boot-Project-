package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ImageDto;
import in.ovaku.frame.framebackend.entities.Event;
import in.ovaku.frame.framebackend.entities.Image;
import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for image.
 *
 * @author Sohan
 * @version 1.0
 * @since 18/07/22
 */
public interface ImageServices {

    /**
     * This method return the list of {@link ImageDto}.
     *
     * @param eventId  - id of the event.
     * @param isActive - true or false to get active or inactive image.
     * @return list of {@link ImageDto}
     */
    List<ImageDto> getAllByEvent(Long eventId,ImageSelectionStatus imageSelectionStatus, Boolean isActive);

    /**
     * This method return a specific {@link ImageDto} entity identified by the given {@link Event} id.
     *
     * @param eventId  - id of the event.
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive image.
     * @return {@link ImageDto}
     */
    ImageDto getByEvent(Long eventId, Long id, Boolean isActive);

    /**
     * This method add new list of {@link Image}.
     *
     * @param eventId - id of the event.
     * @param files   -uploaded file.
     * @return list of {@link ImageDto}
     */
    List<ImageDto> addByEvent(Long eventId, MultipartFile[] files) throws IOException;

    /**
     * This method update list of {@link Image} identified by eventId.
     *
     * @param eventId      - id of the event.
     * @param imageDtoList -List of dto to be select.
     * @return list of {@link ImageDto}
     */
    List<ImageDto> updateByEvent(Long eventId, List<ImageDto> imageDtoList);

   /**
     * This method delete the {@link Image} entity identified by the given id.
     *
     * @param eventId - id of the event.
     * @param id      - id of the entity to delete. Must not be null.
     * @return true or false
     */

    Boolean delete(Long eventId, Long id);
}
