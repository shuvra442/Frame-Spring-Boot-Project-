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
 * It defines the details of different business.
 *
 * @author Sohan
 * @version 1.0
 * @since 23/03/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel(description = "It contains Business details")
public class Business {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Business Id", required = true, value = "1")
    private Long id;
    /**
     * It represents business organization name.
     */
    @ApiModelProperty(name = "name", notes = "Business name", required = true, value = "xyz")
    private String name;
    /**
     * It represents business organization email.
     */
    @Column(length = 70)
    @ApiModelProperty(name = "email", notes = "Business email", required = true, value = "xyz")
    private String email;
    /**
     * It represents business organization phone number.
     */
    @Column(length = 15, unique = true)
    @ApiModelProperty(name = "phoneNo", notes = "Business contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents business organization password.
     */
    @ApiModelProperty(name = "password", notes = "Business password", required = true, value = "xyz")
    private String password;
    /**
     * It represents business organization owner first name.
     */
    @ApiModelProperty(name = "ownerFirstName", notes = "Business owner first name", required = true, value = "xyz")
    private String ownerFirstName;
    /**
     * It represents business organization owner last name.
     */
    @ApiModelProperty(name = "ownerLastName", notes = "Business owner last name", required = true, value = "xyz")
    private String ownerLastName;
    /**
     * It has one-to-many relation with {@link Subscription} class.
     * It represents business organization provided subscriptions pack.
     */
    @OneToMany(mappedBy = "business")
    @ApiModelProperty(name = "subscriptions", notes = "Business organization provided subscriptions pack", required = true, value = "xyz")
    private Set<Subscription> subscriptions;
    /**
     * It has many-to-one relation with {@link Address} class.
     * It represents business address.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ApiModelProperty(name = "addresses", notes = "Business addresses", required = true, value = "xyz")
    private Address address;
    /**
     * It represents business is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Business is avalable or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It has one-to-many relation with {@link Staff} class.
     * It represents working staffs under a business organization
     */
    @OneToMany(mappedBy = "business")
    @ApiModelProperty(name = "staffs", notes = "Business organization working staff")
    private Set<Staff> staffs;
    /**
     * It has one-to-many relation with {@link Client} class.
     * It represents clients a business organization
     */
    @OneToMany(mappedBy = "business")
    @ApiModelProperty(name = "staffs", notes = "Business organization clients")
    private Set<Client> clients;
    /**
     * It represents business organization registration number.
     */
    @ApiModelProperty(name = "registrationNumber", notes = "Business registration number", required = true, value = "xyz")
    private String registrationNumber;
    /**
     * It represents record created date.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @ApiModelProperty(name = "createdDate", notes = "Business created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Business updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
