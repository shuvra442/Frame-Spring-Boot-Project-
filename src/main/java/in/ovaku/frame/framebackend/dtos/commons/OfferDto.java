package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.enums.OfferType;
import in.ovaku.frame.framebackend.entities.enums.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class with 10 member variables.
 * It defines the details of different offers.
 *
 * @author Sohan
 * @version 1.0
 * @since 10/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Offer details")
public class OfferDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Offer Id", required = true, value = "1")
    private Long id;
    /**
     * It represents offer name.
     */
    @ApiModelProperty(name = "name", notes = "Offer name", required = true, value = "xyz")
    private String name;
    /**
     * It represents offer brief description.
     */
    @ApiModelProperty(name = "description", notes = "Offer description", required = true, value = "xyz")
    private String description;
    /**
     * It represents offer discount.
     */
    @ApiModelProperty(name = "discount", notes = "Offer discount", required = true, value = "xyz")
    private Integer discount;
    /**
     * It represents offer type.
     */
    @ApiModelProperty(name = "offerType", notes = "Offer type", required = true, value = "xyz")
    private OfferType offerType;
    /**
     * It represents discount coupon.
     */
    @ApiModelProperty(name = "couponCode", notes = "Offer couponCode", required = true, value = "xyz")
    private String couponCode;
    /**
     * It represents user type.
     */
    @ApiModelProperty(name = "userType", notes = "Offer for user", required = true, value = "xyz")
    private UserType userType;
    /**
     * It represents how many times offers applicable.
     */
    @ApiModelProperty(name = "maxNoOfUse", notes = "Offer applicable time", required = true, value = "1")
    private Integer maxNoOfUse;
    /**
     * It represents plan offer maximum discount.
     */
    @ApiModelProperty(name = "maxDiscount", notes = "Offer maximum discount")
    private Integer maxDiscount;
    /**
     * It represents offer is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Offer is available or not ", required = true, value = "true/false")
    private Boolean isActive;
}
