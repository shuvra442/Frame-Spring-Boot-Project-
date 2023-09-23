package in.ovaku.frame.framebackend.entities;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.enums.SubscriptionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is an entity class with 10 member variables.
 * It defines the detail of subscription of plans and services.
 *
 * @author Sohan
 * @version 1.0
 * @since 17/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel(description = "It contains subscription details")
public class Subscription {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Subscription Id", required = true, value = "1")
    private Long id;
    /**
     * It has many-to-one relation with {@link Offer} class.
     * It represents subscription pack offers.
     */
    @ManyToOne
    @JoinColumn(name = "offer_id")
    @ApiModelProperty(name = "offers", notes = "Subscription pack offers details")
    private Offer offer;
    /**
     * It has many-to-one relation with {@link Plan} class.
     * It represents subscriptions pack plan.
     */
    @ManyToOne
    @JoinColumn(name = "plan_id")
    @ApiModelProperty(name = "plan", notes = "Subscription pack plan details")
    private Plan plan;
    /**
     * It has one-to-many relation with {@link Business} class.
     * It represents the subscriptions take by business organization.
     */
    @ManyToOne
    @JoinColumn(name = "business_id")
    @ApiModelProperty(name = "business", notes = "Subscription business details")
    private Business business;
    /**
     * It has many-to-one relation with {@link Payment} class.
     * it represents payments for subscription.
     */
    @ManyToOne
    @JoinColumn(name = "payment_id")
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
    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    @ApiModelProperty(name = "subscriptionType", notes = "Subscription type")
    private SubscriptionType subscriptionType = SubscriptionType.NEW;
    /**
     * It represents subscription is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Subscription is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Subscription created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record Updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Subscription updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
