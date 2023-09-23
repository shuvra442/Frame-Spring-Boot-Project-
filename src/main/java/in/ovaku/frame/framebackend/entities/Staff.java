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
 * This class is an entity class with 13 member variables.
 * It defines the details of staffs and their activity.
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
@ApiModel(description = "It contains Staffs details")
public class Staff {
    /**
     * It represents the unique id of every records.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Staff Id", required = true, value = "1")
    private Long id;
    /**
     * It represents staff email.
     */
    @Column(length = 70)
    @ApiModelProperty(name = "email", notes = "Staff email", required = true, value = "xyz")
    private String email;
    /**
     * It represents staff phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Staff contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents staff password.
     */
    @ApiModelProperty(name = "password", notes = "Staff password", required = true, value = "xyz")
    private String password;
    /**
     * It represents staff is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Staff is avalable or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents staff first name.
     */
    @ApiModelProperty(name = "firstName", notes = "Staff  first name", required = true, value = "xyz")
    private String firstName;
    /**
     * It represents staff last name.
     */
    @ApiModelProperty(name = "lastName", notes = "Staff  last name", required = true, value = "xyz")
    private String lastName;
    /**
     * It has many-to-one relation with {@link Business} class.
     * It represents in which organization staff work
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    @ApiModelProperty(name = "business", notes = "Staff organization details")
    private Business business;
    /**
     * It has many-to-one relation with {@link Role} class.
     * It represents staff role.
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    @ApiModelProperty(name = "role", notes = "Staff role details")
    private Role role;
    /**
     * It has many-to-one relation with {@link Address} class.
     * It represents staff address
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @ApiModelProperty(name = "address", notes = "Staff address details")
    private Address address;
    /**
     * It has one-to-many relation with {@link Event} class.
     */
    @OneToMany(mappedBy = "staff")
    @ApiModelProperty(name = "events", notes = "Staff event details")
    private Set<Event> events;
    /**
     * It represents record created date.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @ApiModelProperty(name = "createdDate", notes = "Staff created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Staff updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
