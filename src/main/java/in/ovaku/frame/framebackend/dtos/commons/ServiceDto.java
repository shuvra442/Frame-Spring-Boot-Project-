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

import java.util.Date;

/**
 * This class is a dto class with 5 member variables.
 * It defines the details of different services.
 *
 * @author Sohan
 * @version 1.0
 * @since 07/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Service details")
public class ServiceDto {
    /**
     * It represents the unique id of every records.
     */
    @ApiModelProperty(name = "id", notes = "Service Id", required = true, value = "1")
    private Long id;
    /**
     * It represents service name.
     */
    @ApiModelProperty(name = "name", notes = "Service name", required = true, value = "xyz")
    private String name;
    /**
     * It represents service launch date.
     */
    @ApiModelProperty(name = "flightDate", notes = "Service flight date", required = true, value = "xyz")
    private Date flightDate;
    /**
     * It represents service brief description.
     */
    @ApiModelProperty(name = "description", notes = "Service description", required = true, value = "xyz")
    private String description;
    /**
     * It represents service is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Service is available or not ", required = true, value = "true/false")
    private Boolean isActive;
}
