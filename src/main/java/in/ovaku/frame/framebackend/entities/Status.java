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

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * This class is an entity class with 6 member variables.
 * It defines different status.
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
@ApiModel(description = "It contains status details")
public class Status {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Status Id", required = true, value = "1")
    private Long id;
    /**
     * It represents status name.
     */
    @Column(length = 30)
    @ApiModelProperty(name = "name", notes = "Status name", required = true, value = "xyz")
    private String name;
    /**
     * It represents brief description of a status.
     */
    @Lob
    @ApiModelProperty(name = "description", notes = "Status description", required = true, value = "xyz")
    private String description;
    /**
     * It has one-to-many relation with {@link Payment} class.
     */
    @OneToMany(mappedBy = "status")
    @ApiModelProperty(name = "payments", notes = "Status for payment")
    private Set<Payment> payments;
    /**
     * It represents record created date.
     */
    @ApiModelProperty(name = "createdDate", notes = "Status created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @ApiModelProperty(name = "updatedDate", notes = "Status updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
