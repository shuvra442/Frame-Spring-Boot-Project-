package in.ovaku.frame.framebackend.dtos.responses;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import in.ovaku.frame.framebackend.dtos.commons.BusinessDto;
import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.dtos.commons.PlanDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Offer;
import in.ovaku.frame.framebackend.entities.Payment;
import in.ovaku.frame.framebackend.entities.Plan;
import in.ovaku.frame.framebackend.entities.enums.SubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class is a response dto class of Subscription with 5 member variables.
 * It defines the details of different subscription.
 *
 * @author Sohan
 * @version 1.0
 * @since 11/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains subscription response details")
public class SubscriptionResponseDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Subscription Id", required = true, value = "1")
    private Long id;
    /**
     * It has many-to-one relation with {@link Offer} class.
     * It represents subscription pack offers.
     */
    @JsonProperty("offer")
    @ApiModelProperty(name = "offerDto", notes = "Subscription pack offers details")
    private OfferDto offerDto;
    /**
     * It has many-to-one relation with {@link Plan} class.
     * It represents subscriptions pack plan.
     */
    @JsonProperty("plan")
    @ApiModelProperty(name = "planDto", notes = "Subscription pack plan details")
    private PlanDto planDto;
    /**
     * It has one-to-many relation with {@link Business} class.
     * It represents the subscriptions take by business organization.
     */
    @JsonProperty("business")
    @ApiModelProperty(name = "businessDto", notes = "Subscription business details")
    private BusinessDto businessDto;
    /**
     * It has many-to-one relation with {@link Payment} class.
     * it represents payments for subscription.
     */
    @ApiModelProperty(name = "payment", notes = "Subscription payment details")
    private Payment payment;
    /**
     * It represents subscriptions pack start date.
     */
    @ApiModelProperty(name = "startDate", notes = "Subscription start date", required = true, value = "00/00/0000")
    private Date startDate;
    /**
     * It represents subscriptions pack end date.
     */
    @ApiModelProperty(name = "endDate", notes = "Subscription end date", required = true, value = "00/00/0000")
    private Date endDate;
    /**
     * It represents subscriptions type.
     */
    @ApiModelProperty(name = "subscriptionType", notes = "Subscription type")
    private SubscriptionType subscriptionType;
    /**
     * It represents subscription is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Subscription is available or not ", required = true, value = "true/false")
    private Boolean isActive;
}
