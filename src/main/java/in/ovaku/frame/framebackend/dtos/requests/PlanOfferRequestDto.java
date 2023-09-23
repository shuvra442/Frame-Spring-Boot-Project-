package in.ovaku.frame.framebackend.dtos.requests;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Offer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class is a request dto class of PlanOffer with 4 member variables.
 *
 * @author Sohan
 * @version 1.0
 * @since 09/08/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains plan-offer request details")
public class PlanOfferRequestDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Plan service Id", value = "1")
    private Long id;
    /**
     * It represents {@link Offer}id.
     */
    @ApiModelProperty(name = "offerId", notes = "Offer details", required = true, value = "1")
    private Long offerId;
    /**
     * It represents offers starting date of a plan.
     */
    @ApiModelProperty(name = "startDate", notes = "Plan offer start date", required = true, value = "00/00/0000")
    private Date startDate;
    /**
     * It represents offers ending date of a plan.
     */
    @ApiModelProperty(name = "endDate", notes = "Plan offer end date", required = true, value = "00/00/0000")
    private Date endDate;
}
