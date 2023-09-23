package in.ovaku.frame.framebackend.dtos.responses;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import in.ovaku.frame.framebackend.dtos.commons.ServiceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * This class is a response dto class of PLanService with 5 member variables.
 * It defines the details of different plan services.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains plan Service response details")
public class PlanServiceResponseDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Plan service Id", value = "1")
    private Long id;
    /**
     * It represents {@link ServiceDto} class.
     */
    @JsonProperty("service")
    @ApiModelProperty(name = "serviceDto", notes = "Service details")
    private ServiceDto serviceDto;
    /**
     * It represents plan limits of a service.
     */
    @ApiModelProperty(name = "limits", notes = "Service plan limit", value = "123")
    private BigDecimal limits;
    /**
     * It represents plan service is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Plan service is available or not ", value = "true/false")
    private Boolean isActive;
}
