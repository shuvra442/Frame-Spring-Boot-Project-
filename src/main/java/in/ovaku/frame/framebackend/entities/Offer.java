package in.ovaku.frame.framebackend.entities;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity class with 12 member variables.
 * It defines different offers of.
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
@ApiModel(description = "It contains Offers details")
public class Offer {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Lob
    @ApiModelProperty(name = "description", notes = "Offer description", required = true, value = "xyz")
    private String description;
    /**
     * It represents offer discount.
     */
    @Column(precision = 20, scale = 4)
    @ApiModelProperty(name = "discount", notes = "Offer discount", required = true, value = "xyz")
    private Integer discount;
    /**
     * It represents offer type.
     */
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    @ApiModelProperty(name = "offerType", notes = "Offer type", required = true, value = "xyz")
    private OfferType offerType;
    /**
     * It represents discount coupon.
     */
    @Column(length = 15)
    @ApiModelProperty(name = "couponCode", notes = "Offer couponCode", required = true, value = "xyz")
    private String couponCode;
    /**
     * It represents user type.
     */
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(name = "userType", notes = "Offer for user", required = true, value = "xyz")
    private UserType userType;
    /**
     * It represents how many times offers applicable.
     */
    @Column(length = 2)
    @ApiModelProperty(name = "maxNoOfUse", notes = "Offer applicable time", required = true, value = "1")
    private Integer maxNoOfUse;
    /**
     * It represents plan offer maximum discount.
     */
    @Column(precision = 10, scale = 4)
    @ApiModelProperty(name = "maxDiscount", notes = "Offer maximum discount")
    private Integer maxDiscount;
    /**
     * It represents offer is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Offer is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It has one-to-many relation with {@link PlanOffer} class.
     */
    @OneToMany(mappedBy = "offer")
    @ApiModelProperty(name = "plansOffers", notes = "Offer for plans")
    private Set<PlanOffer> plansOffers;
    /**
     * It has one-to-many relation with {@link Subscription} class.
     */
    @OneToMany(mappedBy = "offer")
    @ApiModelProperty(name = "subscriptions", notes = "Offer for subscriptions")
    private Set<Subscription> subscriptions;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Offer created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record Updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Offer updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
