package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.StaffDto;
import in.ovaku.frame.framebackend.entities.Staff;
import in.ovaku.frame.framebackend.utils.converters.StaffConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link StaffConverter}
 * It contain converter logic for {@link Staff} and {@link StaffDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
@Component
public class StaffConverterImpl implements StaffConverter {
    private final ModelMapper modelMapper;

    public StaffConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    /**
     * This method convert {@link Staff} to {@link StaffDto}
     *
     * @return staffDto
     */
    @Override
    public StaffDto staffToStaffDto(Staff staff) {
        return modelMapper.map(staff, StaffDto.class);
    }

    /**
     * This method convert {@link StaffDto} to {@link Staff}
     *
     * @return staff
     */
    @Override
    public Staff staffDtoToStaff(StaffDto staffDto) {
        return modelMapper.map(staffDto, Staff.class);
    }

    /**
     * This method update existing {@link Staff} by {@link StaffDto}
     *
     * @return staff
     */
    @Override
    public Staff toUpdateStaff(StaffDto staffDto, Staff staff) {
        modelMapper.map(staffDto, staff);
        return staff;
    }
}
