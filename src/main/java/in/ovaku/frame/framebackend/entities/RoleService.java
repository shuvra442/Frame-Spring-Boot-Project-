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
import java.io.Serializable;
import java.util.Date;

/**
 * This class is an entity class with 5 member variables.
 * It is a jointable of two entity class {@link Role} and @link {@link Service}.
 * It defines different role for services.
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
@ApiModel(description = "It contains Role services details")
public class RoleService implements Serializable {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Role service Id", required = true, value = "1")
    private Long id;

    /**
     * It has many-to-one relation with {@link Role} class.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    @ApiModelProperty(name = "role", notes = "Role details")
    private Role role;
    /**
     * It has many-to-one relation with {@link Service} class.
     */
    @ManyToOne
    @JoinColumn(name = "service_id")
    @ApiModelProperty(name = "service", notes = "Service details")
    private Service service;
    /**
     * It represents record created date.
     */
    @ApiModelProperty(name = "createdDate", notes = "Role service created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @ApiModelProperty(name = "updatedDate", notes = "Role service updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
