package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class with 12 member variables.
 * It defines the details of different staffs.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Staff details")
public class StaffDto {
    /**
     * It represents the unique id of every records.
     */
    @ApiModelProperty(name = "id", notes = "Staff Id", required = true, value = "1")
    private Long id;
    /**
     * It represents staff email.
     */
    @ApiModelProperty(name = "email", notes = "Staff email", required = true, value = "xyz")
    private String email;
    /**
     * It represents staff phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Staff contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents staff password.
     */
    @ApiModelProperty(name = "password", notes = "Staff password", required = true, value = "xyz")
    private String password;
    /**
     * It represents staff is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Staff is avalable or not ", required = true, value = "true/false")
    private Boolean isActive;
    /**
     * It represents staff first name.
     */
    @ApiModelProperty(name = "firstName", notes = "Staff  first name", required = true, value = "xyz")
    private String firstName;
    /**
     * It represents staff last name.
     */
    @ApiModelProperty(name = "lastName", notes = "Staff  last name", required = true, value = "xyz")
    private String lastName;
    /**
     * It represents staff address
     */
    @JsonProperty("address")
    @ApiModelProperty(name = "address", notes = "Staff address details")
    private AddressDto addressDto;

}
