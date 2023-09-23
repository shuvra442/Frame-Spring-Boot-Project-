package in.ovaku.frame.framebackend.dtos.requests;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.Payment;
import in.ovaku.frame.framebackend.entities.enums.SubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a request dto class of Subscription with 5 member variables.
 *
 * @author Sohan
 * @version 1.0
 * @since 11/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains subscription request details")
public class SubscriptionRequestDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Subscription Id", value = "1")
    private Long id;
    /**
     * It represents offer id.
     */
    @ApiModelProperty(name = "offerId", notes = "Subscription offers id")
    private Long offerId;
    /**
     * It represents plan id.
     */
    @ApiModelProperty(name = "planId", notes = "Subscription plan id")
    private Long planId;
    /**
     * It has many-to-one relation with {@link Payment} class.
     * it represents payments for subscription.
     */
    @ApiModelProperty(name = "payment", notes = "Subscription payment details")
    private Payment payment;
    /**
     * It represents subscriptions type.
     */
    @ApiModelProperty(name = "subscriptionType", notes = "Subscription type")
    private SubscriptionType subscriptionType;
}
