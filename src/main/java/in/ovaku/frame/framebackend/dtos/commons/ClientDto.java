package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import in.ovaku.frame.framebackend.entities.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class with 9 member variables.
 * It defines the details of different clients.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Client details")
public class ClientDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Client Id", required = true, value = "1")
    private Long id;
    /**
     * It represents client name.
     */
    @ApiModelProperty(name = "name", notes = "Client name", required = true, value = "xyz")
    private String name;
    /**
     * It represents client email.
     */
    @ApiModelProperty(name = "email", notes = "Client email", required = true, value = "xyz")
    private String email;
    /**
     * It represents client phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Client contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents client password.
     */
    @ApiModelProperty(name = "password", notes = "Client password", required = true, value = "xyz")
    private String password;
    /**
     * It represents client first name.
     */
    @ApiModelProperty(name = "firstName", notes = "Client first name", required = true, value = "xyz")
    private String firstName;
    /**
     * It represents client last name.
     */
    @ApiModelProperty(name = "lastName", notes = "Client last name", required = true, value = "xyz")
    private String lastName;
    /**
     * It has many-to-one relation with {@link Address} class.
     * It represents client address.
     */
    @JsonProperty("address")
    @ApiModelProperty(name = "address", notes = "Client address details")
    private AddressDto addressDto;
    /**
     * It represents client is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Client is avalable or not ", required = true, value = "true/false")
    private Boolean isActive;
}
