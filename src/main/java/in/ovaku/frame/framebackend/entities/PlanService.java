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
import java.math.BigDecimal;
import java.util.Date;

/**
 * This class is an entity class with 6 member variables.
 * It is a jointable of two entity class {@link Plan} and @link {@link Service}.
 * It defines different plans for services.
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
@ApiModel(description = "It contains Plan's services details")
public class PlanService {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Plan service Id", required = true, value = "1")
    private Long id;
    /**
     * It has many-to-one relation with {@link Plan} class.
     */
    @ManyToOne
    @JoinColumn(name = "plan_id")
    @ApiModelProperty(name = "plan", notes = "Plan details")
    private Plan plan;
    /**
     * It has many-to-one relation with {@link Service} class.
     */
    @ManyToOne
    @JoinColumn(name = "service_id")
    @ApiModelProperty(name = "services", notes = "Service details")
    private Service service;
    /**
     * It represents plan limits of a service.
     */
    @Column(precision = 13, scale = 4)
    @ApiModelProperty(name = "limits", notes = "Service plan limit", required = true, value = "123")
    private BigDecimal limits;
    /**
     * It represents plan service is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Plan service is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Service plan created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Service plan updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
