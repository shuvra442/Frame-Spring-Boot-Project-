package in.ovaku.frame.framebackend.entities;
/*
 * Copyright (c) 2022 Ovaku.
 */

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
 * This class is an entity class with 7 member variables.
 * It is a jointable of two entity class {@link Plan} and @link {@link Offer}.
 * It define offers of different plans
 *
 * @author Sohan
 * @version 1.1
 * @since 23/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel(description = "It contains Plan's offer details")
public class PlanOffer {
    /**
     * It represents the unique id of every records.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Plan offer Id", required = true, value = "1")
    private Long id;
    /**
     * It has many-to-one relation with {@link Plan} class.
     */
    @ManyToOne
    @JoinColumn(name = "plan_id")
    @ApiModelProperty(name = "plan", notes = "Plan details")
    private Plan plan;
    /**
     * It has many-to-one relation with {@link Offer} class.
     */
    @ManyToOne
    @JoinColumn(name = "offer_id")
    @ApiModelProperty(name = "offer", notes = "Offer details")
    private Offer offer;
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
    /**
     * It represents plan offer is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Plan offer is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Plan's offer created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Plan's offer updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
