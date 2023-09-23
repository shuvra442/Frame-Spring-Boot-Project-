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
 * This class is an entity class with 7 member variables.
 * It defines different roles.
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
@ApiModel(description = "It contains Role details")
public class Role {
    /**
     * It represents the unique id of every records.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Role Id", required = true, value = "1")
    private Long id;
    /**
     * It represents role name.
     */
    @ApiModelProperty(name = "name", notes = "Role name", required = true, value = "xyz")
    private String name;
    /**
     * It represents a brief description of a role.
     */
    @Lob
    @ApiModelProperty(name = "description", notes = "Role description", required = true, value = "xyz")
    private String description;
    /**
     * It has one-to-many relation with {@link Staff} class.
     * It represents staffs for a role.
     */
    @OneToMany(mappedBy = "role")
    @ApiModelProperty(name = "staffs", notes = "Role for staff")
    private Set<Staff> staffs;
    /**
     * It has one-to-many relation with {@link Role} class.
     */
    @OneToMany(mappedBy = "role")
    @ApiModelProperty(name = "rolesServices", notes = "Role service details")
    private Set<RoleService> rolesServices;
    /**
     * It represents record created date.
     */
    @ApiModelProperty(name = "createdDate", notes = "Role created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * it represents record updated date.
     */
    @ApiModelProperty(name = "updatedDate", notes = "Role updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
