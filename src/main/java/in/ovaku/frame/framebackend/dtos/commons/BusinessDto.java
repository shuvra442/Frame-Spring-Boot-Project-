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
 * This class is a dto class with 10 member variables.
 * It defines the details of different business.
 *
 * @author Sohan
 * @version 1.0
 * @since 24/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Business details")
public class BusinessDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Business Id", required = true, value = "1")
    private Long id;
    /**
     * It represents business organization name.
     */
    @ApiModelProperty(name = "name", notes = "Business name", required = true, value = "xyz")
    private String name;
    /**
     * It represents business organization email.
     */
    @ApiModelProperty(name = "email", notes = "Business email", required = true, value = "xyz")
    private String email;
    /**
     * It represents business organization phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Business contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents business organization password.
     */
    @ApiModelProperty(name = "password", notes = "Business password", required = true, value = "xyz")
    private String password;
    /**
     * It represents business organization owner first name.
     */
    @ApiModelProperty(name = "ownerFirstName", notes = "Business owner first name", required = true, value = "xyz")
    private String ownerFirstName;
    /**
     * It represents business organization owner last name.
     */
    @ApiModelProperty(name = "ownerLastName", notes = "Business owner last name", required = true, value = "xyz")
    private String ownerLastName;

    /**
     * It represents business address.
     */
    @JsonProperty("address")
    @ApiModelProperty(name = "addressDto", notes = "Business address", required = true, value = "xyz")
    private AddressDto addressDto;
    /**
     * It represents business is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Business is available or not ", required = true, value = "true/false")
    private Boolean isActive;
    /**
     * It represents business organization registration number.
     */
    @ApiModelProperty(name = "registrationNumber", notes = "Business registration number", required = true, value = "xyz")
    private String registrationNumber;
}
