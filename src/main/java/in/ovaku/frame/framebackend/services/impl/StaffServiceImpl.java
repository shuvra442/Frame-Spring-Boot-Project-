package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.StaffDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Staff;
import in.ovaku.frame.framebackend.exceptions.ResourceAlreadyExistsException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.BusinessRepository;
import in.ovaku.frame.framebackend.repositories.StaffRepository;
import in.ovaku.frame.framebackend.services.StaffService;
import in.ovaku.frame.framebackend.utils.converters.StaffConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link StaffService}
 * It contain different business logic for Staff
 *
 * @author sohan
 * @version 1.0
 * @since 01/07/22
 */
@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffConverter staffConverter;
    private final BusinessRepository businessRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public StaffServiceImpl(StaffRepository staffRepository, StaffConverter staffConverter, BusinessRepository businessRepository) {
        this.staffRepository = staffRepository;
        this.staffConverter = staffConverter;
        this.businessRepository = businessRepository;
    }

    /**
     * This method return the list of {@link StaffDto}.
     *
     * @param businessId -id of the {@link Business} entity to get staff.Must not be null.
     * @param isActive   - true or false to get active or inactive staff.
     * @return all staffDto entity.
     */
    @Override
    public List<StaffDto> getAll(Long businessId, Boolean isActive) {

        logger.debug("Into getAll service method with business id=> {}", businessId);
        List<Staff> staffList;
        if (isActive) {
            staffList = staffRepository.findAllActiveByBusinessId(businessId);
        } else {
            staffList = staffRepository.findAllByBusinessId(businessId);
        }
        if (staffList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched student list => {}", staffList);
        return staffList.stream()
                .map(staffConverter::staffToStaffDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link StaffDto} entity identified by
     * the given {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to get staff.Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @param isActive   - true or false to get active or inactive staff.
     * @return a staffDto entity
     */
    @Override
    public StaffDto getById(Long businessId, Long id, Boolean isActive) {

        logger.debug("Into get entity service method with business id => {} and staff id => {}", businessId, id);
        Optional<Staff> optionalStaff;

        if (isActive) {
            optionalStaff = staffRepository.findActiveById(businessId, id);
        } else {
            optionalStaff = staffRepository.findByBusinessIdAndId(businessId, id);
        }
        if (optionalStaff.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Staff staff = optionalStaff.get();
        logger.debug("Fetched staff entity => {}", staff);
        StaffDto staffDto = staffConverter.staffToStaffDto(staff);
        logger.debug("Converted DTO => {} from staff entity", staffDto);
        return staffDto;
    }

    /**
     * This method register new {@link Staff} for {@link Business}.
     *
     * @param businessId - id of {@link Business} entity to add {@link Staff}.Must not be null.
     * @param staffDto   -staffDto to be registered.
     * @return staffDto entity
     */
    @Override
    public StaffDto add(Long businessId, StaffDto staffDto) {

        logger.debug("Into add entity service method with business id => {} and data =>{}", businessId, staffDto);
        //Validate
        if (staffRepository.findByPhoneNo(staffDto.getPhoneNo()).isPresent()) {
            logger.error("Staff data already exist =>{}", staffDto);
            throw new ResourceAlreadyExistsException("Mobile number already Exists!");
        } else if (staffRepository.findByEmail(staffDto.getEmail()).isPresent()) {
            logger.error("Staff data already exist =>{}", staffDto);
            throw new ResourceAlreadyExistsException("Email already Exists!");
        }
        Staff staff = staffConverter.staffDtoToStaff(staffDto);
        logger.debug("Converted staff entity => {} from DTO", staff);
        staff.setBusiness(businessRepository.getById(businessId));
        staff = staffRepository.save(staff);
        logger.debug("Record saved =>{} in DB", staff);
        StaffDto registrationDto = staffConverter.staffToStaffDto(staff);
        logger.debug("Converted DTO => {} from staff entity", registrationDto);
        return registrationDto;
    }

    /**
     * This method update {@link Staff} identified by {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to update staff.Must not be null.
     * @param id         -id of the staff entity to find. Must not be null.
     * @param staffDto   - {@link StaffDto} to be updated.
     * @return staffDto entity
     */
    @Override
    public StaffDto update(Long businessId, Long id, StaffDto staffDto) {

        logger.debug("Into update entity service method with business id => {}," +
                " staff id => {} and data => {}", businessId, id, staffDto);
        //Validate.
        if (!staffRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Staff staff = staffConverter.staffDtoToStaff(staffDto);
        logger.debug("Converted staff entity => {} from DTO => {}", staff, staffDto);
        staff.setId(id);
        staff.setBusiness(businessRepository.getById(businessId));
        staff = staffRepository.save(staff);
        logger.debug("Staff record updated in DB=>{}", staff);
        StaffDto dto = staffConverter.staffToStaffDto(staff);
        logger.debug("Converted DTO => {} from staff entity", dto);
        return dto;
    }

    /**
     * This method delete {@link Staff} entity identified by {@link Business} id and {@link Staff} id.
     *
     * @param businessId - id of the {@link Business} entity to delete staff.Must not be null.
     * @param staffId    -id of the staff entity to delete. Must not be null.
     * @return inactive staffDto entity
     */
    @Override
    public Boolean delete(Long businessId, Long staffId) {

        logger.debug("Into delete entity service method with business id => {} and staff id => {}", businessId, staffId);
        //Validate
        if (staffRepository.findActiveById(businessId, staffId).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Staff staff = staffRepository.getById(staffId);
        logger.debug("Fetching staff entity by id => {} from DB using repository", businessId);
        staff.setIsActive(false);
        staff = staffRepository.save(staff);
        logger.debug("Staff entity deleted from DB=>{}", staff);
        return true;
    }
}
