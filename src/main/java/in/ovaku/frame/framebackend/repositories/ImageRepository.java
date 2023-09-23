package in.ovaku.frame.framebackend.repositories;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Event;
import in.ovaku.frame.framebackend.entities.Image;
import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    /**
     * Find {@link Image} entity by {@link Event} id and {@link Image} id.
     *
     * @param eventId - id of the {@link Event} entity.Must not be null.
     * @param id      -id of the {@link Image} entity. Must not be null.
     * @return Optional
     */
    Optional<Image> findByEventIdAndId(Long eventId, Long id);

    /**
     * Find active {@link Image} entity {@link Event} id and {@link Image} id.
     *
     * @param eventId  - id of the {@link Event} entity.Must not be null.
     * @param id       -id of the {@link Image} entity. Must not be null.
     * @param isActive -true or false.
     * @return Optional
     */
    Optional<Image> findByEventIdAndIdAndIsActive(Long eventId, Long id, Boolean isActive);
    /**
     * Find all {@link Image} entity by {@link Event} id.
     *
     * @param eventId - id of the {@link Event} entity.Must not be null.
     * @return list of images.
     */
    List<Image> findAllByEventId(Long eventId);

    /**
     * Find all active {@link Image} entity by {@link Event} id.
     *
     * @param eventId  - id of the {@link Event} entity.Must not be null.
     * @param isActive -true or false.
     * @return list of active image.
     */
    List<Image> findAllByEventIdAndIsActive(Long eventId, Boolean isActive);

    /**
     *  Find all {@link Image} entity by {@link Event} id.
     *
     * @param eventId  -id of the {@link Event} entity.Must not be null.
     * @param imageSelectionStatus -select imageSelectionType
     * @return  - list of active image
     */
    @Query("SELECT e from Image e where e.event.id=:eventId AND e.imageSelectionStatus=:imageSelectionStatus")
    List<Image> findAllByImageSelection(Long eventId, @Param("imageSelectionStatus") ImageSelectionStatus imageSelectionStatus);

    /**
     *Find all active {@link Image} entity by {@link Event} id.
     *
     * @param eventId -id of the {@link Event} entity.Must not be null.
     * @param imageSelectionStatus -select imageSelectionType
     * @return -list of active image
     */
    @Query("SELECT e from Image e where e.event.id=:eventId AND e.imageSelectionStatus=:imageSelectionStatus AND e.isActive=true")
    List<Image> findAllActiveByImageSelection(Long eventId, @Param("imageSelectionStatus") ImageSelectionStatus imageSelectionStatus);
}
