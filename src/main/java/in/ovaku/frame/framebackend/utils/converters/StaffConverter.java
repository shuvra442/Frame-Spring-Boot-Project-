package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.StaffDto;
import in.ovaku.frame.framebackend.entities.Staff;

/**
 * This is a converter interface.
 * It is used to map {@link Staff} entity class with {@link StaffDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface StaffConverter {

    /**
     * This method convert {@link Staff} to {@link StaffDto}
     *
     * @return {@link StaffDto}
     */
    StaffDto staffToStaffDto(Staff staff);

    /**
     * This method convert {@link StaffDto} to {@link Staff}
     *
     * @return {@link Staff}
     */
    Staff staffDtoToStaff(StaffDto staffDto);

    /**
     * This method update existing {@link Staff} by {@link StaffDto}
     *
     * @return {@link Staff}
     */
    Staff toUpdateStaff(StaffDto staffDto, Staff staff);
}
