package in.ovaku.frame.framebackend.dtos.responses;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class is a response dto class of PLanOffer with 5 member variables.
 * It defines the details of different plan offers.
 *
 * @author Sohan
 * @version 1.0
 * @since 09/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains plan offer response details")
public class PlanOfferResponseDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Plan offer Id", value = "1")
    private Long id;
    /**
     * It represents {@link OfferDto} class.
     */
    @JsonProperty("offer")
    @ApiModelProperty(name = "offerDto", notes = "Offer details")
    private OfferDto offerDto;
    /**
     * It represents offers starting date of a plan.
     */
    @ApiModelProperty(name = "startDate", notes = "Plan offer start date", value = "00/00/0000")
    private Date startDate;
    /**
     * It represents offers ending date of a plan.
     */
    @ApiModelProperty(name = "endDate", notes = "Plan offer end date", value = "00/00/0000")
    private Date endDate;
    /**
     * It represents plan offer is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Plan offer is available or not ", value = "true/false")
    private Boolean isActive;
}
