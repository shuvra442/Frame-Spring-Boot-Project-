package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.enums.PlanType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * This class is a dto class with 7 member variables.
 * It defines the details of different plans.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains plan details")
public class PlanDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Plan Id", required = true, value = "1")
    private Long id;
    /**
     * It represents plans name.
     */
    @ApiModelProperty(name = "name", notes = "Plan name", required = true, value = "xyz")
    private String name;
    /**
     * It represents plans brief description.
     */
    @ApiModelProperty(name = "description", notes = "Plan description", required = true, value = "xyz")
    private String description;
    /**
     * It represents plans price.
     */
    @ApiModelProperty(name = "price", notes = "Plan price", required = true, value = "123")
    private BigDecimal price;
    /**
     * It represents plans type.
     */
    @ApiModelProperty(name = "planType", notes = "Plan type")
    private PlanType planType;
    /**
     * It represents plan is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Plan is available or not ", required = true, value = "true/false")
    private Boolean isActive;
    /**
     * It represents plan duration.
     */
    @ApiModelProperty(name = "durationInDays", notes = "Plans duration")
    private Integer durationInDays;
}
