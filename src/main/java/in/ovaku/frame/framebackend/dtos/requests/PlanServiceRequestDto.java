package in.ovaku.frame.framebackend.dtos.requests;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Service;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * This class is a request dto class of PLanService with 4 member variables.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/08/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains plan-service request details")
public class PlanServiceRequestDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Plan service Id", value = "1")
    private Long id;
    /**
     * It represents {@link Service}id.
     */
    @ApiModelProperty(name = "serviceId", notes = "Service details", required = true, value = "1")
    private Long serviceId;
    /**
     * It represents plan limits of a service.
     */
    @ApiModelProperty(name = "limits", notes = "Service plan limit", required = true, value = "123")
    private BigDecimal limits;
}
