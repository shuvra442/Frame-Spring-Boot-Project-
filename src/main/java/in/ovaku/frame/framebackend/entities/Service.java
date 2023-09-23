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
import java.util.Set;

/**
 * This class is an entity class with 7 member variables.
 * It represents the various Services provided by this product.
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
@ApiModel(description = "It contains Service details")
public class Service {
    /**
     * It represents the unique id of every records.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Service Id", required = true, value = "1")
    private Long id;
    /**
     * It represents service name.
     */
    @ApiModelProperty(name = "name", notes = "Service name", required = true, value = "xyz")
    private String name;
    /**
     * It represents service launch date.
     */
    @ApiModelProperty(name = "flightDate", notes = "Service flight date", required = true, value = "xyz")
    private Date flightDate;
    /**
     * It represents service brief description.
     */
    @Lob
    @ApiModelProperty(name = "description", notes = "Service description", required = true, value = "xyz")
    private String description;
    /**
     * It represents service is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Service is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It has one-to-many relation with {@link PlanService} class.
     */
    @OneToMany(mappedBy = "service")
    @ApiModelProperty(name = "plansServices", notes = "Service plans")
    private Set<PlanService> plansServices;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Service created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Service updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
