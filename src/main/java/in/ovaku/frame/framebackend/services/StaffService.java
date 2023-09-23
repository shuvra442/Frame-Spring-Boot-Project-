package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.StaffDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Staff;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for staff.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface StaffService {
    /**
     * This method return the list of {@link StaffDto}.
     *
     * @param businessId -id of the {@link Business} entity to get staff.Must not be null.
     * @param isActive   - true or false to get active or inactive staff.
     * @return list of {@link StaffDto}
     */
    List<StaffDto> getAll(Long businessId, Boolean isActive);

    /**
     * This method return a specific {@link StaffDto} entity identified by
     * the given {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to get staff.Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @param isActive   - true or false to get active or inactive staff.
     * @return {@link StaffDto}
     */
    StaffDto getById(Long businessId, Long id, Boolean isActive);

    /**
     * This method register new {@link Staff} for {@link Business}.
     *
     * @param businessId - id of {@link Business} entity to add {@link Staff}.Must not be null.
     * @param staffDto   -staffDto to be registered.
     * @return {@link StaffDto}
     */
    StaffDto add(Long businessId, StaffDto staffDto);

    /**
     * This method update {@link Staff} identified by {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to update staff.Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @param staffDto   - {@link StaffDto} to be updated.
     * @return {@link StaffDto}
     */
    StaffDto update(Long businessId, Long id, StaffDto staffDto);

    /**
     * This method delete {@link Staff} entity identified by {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to delete staff.Must not be null.
     * @param staffId    -id of the staff entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long businessId, Long staffId);
}
