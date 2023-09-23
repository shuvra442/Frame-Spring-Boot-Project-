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

/**
 * This class is an entity class with 6 member variables
 * It contains details about Super Admin
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
@ApiModel(description = "It contains Super admin details")
public class SuperAdmin {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Super admin Id", required = true, value = "1")
    private Long id;
    /**
     * It represents super admin name.
     */
    @ApiModelProperty(name = "name", notes = "Super admin name", required = true, value = "xyz")
    private String name;
    /**
     * It represents super admin email.
     */
    @ApiModelProperty(name = "email", notes = "Super admin email", required = true, value = "xyz")
    private String email;
    /**
     * It represents super admin password.
     */
    @Column(length = 8)
    @ApiModelProperty(name = "password", notes = "Super admin password", required = true, value = "xyz")
    private String password;
    /**
     * It represents record created date.
     */
    @ApiModelProperty(name = "createdDate", notes = "Super admin created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * it represents record updated date.
     */
    @ApiModelProperty(name = "updatedDate", notes = "Super admin updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
