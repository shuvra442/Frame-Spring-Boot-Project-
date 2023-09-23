package in.ovaku.frame.framebackend.entities;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity class with 10 member variables.
 * It defines different Plans of provided service.
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
@ApiModel(description = "It contains Plan details")
public class Plan {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Lob
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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    @ApiModelProperty(name = "planType", notes = "Plan type")
    private PlanType planType;
    /**
     * It represents plan is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Plan is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It has one-to-many relation with {@link PlanService} class.
     * It represents plan for services.
     */
    @OneToMany(mappedBy = "plan")
    @ApiModelProperty(name = "plansServices", notes = "Plans for a service")
    private Set<PlanService> plansServices;
    /**
     * It has one-to-many relation with {@link PlanOffer} class.
     * It represents offers for plans.
     */
    @OneToMany(mappedBy = "plan")
    @ApiModelProperty(name = "plansOffers", notes = "Offers for a plan")
    private Set<PlanOffer> plansOffers;
    /**
     * It has one-to-many relation with {@link Subscription} class.
     * It represents plan for subscription packs.
     */
    @OneToMany(mappedBy = "plan")
    @ApiModelProperty(name = "subscriptions", notes = "Plans for  subscriptions")
    private Set<Subscription> subscriptions;
    /**
     * It represents plan duration.
     */
    @Column(length = 4)
    @ApiModelProperty(name = "durationInDays", notes = "Plans duration")
    private Integer durationInDays;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Plan created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Plan updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}