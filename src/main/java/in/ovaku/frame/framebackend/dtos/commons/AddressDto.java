package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class with 7 member variables.
 * It defines address details.
 *
 * @author Sohan
 * @version 1.0
 * @since 24/05/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains address of different users")
public class AddressDto {
    /**
     * it represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Address Id", required = true, value = "1")
    private Long id;
    /**
     * It represents address line.
     */
    @ApiModelProperty(name = "line1", notes = "Address first line", required = true, value = "xyz")
    private String line1;
    /**
     * It represents address line.
     */
    @ApiModelProperty(name = "line2", notes = "Address second line2", required = true, value = "xyz")
    private String line2;
    /**
     * It represents city name.
     */
    @ApiModelProperty(name = "city", notes = "User city", required = true, value = "xyz")
    private String city;
    /**
     * It represents pincode.
     */
    @ApiModelProperty(name = "pincode", notes = "User pincode", required = true, value = "1234")
    private Integer pincode;
    /**
     * It represents district name.
     */
    @ApiModelProperty(name = "district", notes = "User district", required = true, value = "xyz")
    private String district;
    /**
     * It represents state name.
     */
    @ApiModelProperty(name = "state", notes = "User state", required = true, value = "xyz")
    private String state;
    /**
     * It represents country name.
     */
    @ApiModelProperty(name = "country", notes = "User country", required = true, value = "xyz")
    private String country;
}
