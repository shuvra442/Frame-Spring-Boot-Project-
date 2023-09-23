package in.ovaku.frame.framebackend.entities;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * It contains address of different users.
 *
 * @author Sohan
 * @version 1.0
 * @since 22/03/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ApiModel(description = "It contains address of different users")
public class Address {
    /**
     * it represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Address Id", required = true, value = "1")
    private Long id;
    /**
     * It represents address line.
     */
    @Lob
    @ApiModelProperty(name = "line1", notes = "Address first line", required = true, value = "xyz")
    private String line1;
    /**
     * It represents address line.
     */
    @Lob
    @ApiModelProperty(name = "line2", notes = "Address second line2", required = true, value = "xyz")
    private String line2;
    /**
     * It represents city name.
     */
    @ApiModelProperty(name = "city", notes = "User city", required = true, value = "xyz")
    private String city;
    /**
     * It represents pincode.
     */
    @ApiModelProperty(name = "pincode", notes = "User pincode", required = true, value = "1234")
    @Column(length = 20)
    private Integer pincode;
    /**
     * It represents district name.
     */
    @ApiModelProperty(name = "district", notes = "User district", required = true, value = "xyz")
    private String district;
    /**
     * It represents state name.
     */
    @ApiModelProperty(name = "state", notes = "User state", required = true, value = "xyz")
    private String state;
    /**
     * It represents country name.
     */
    @ApiModelProperty(name = "country", notes = "User country", required = true, value = "xyz")
    private String country;
    /**
     * It has one-to-many relation with {@link Business} class.
     * It represents address of business organization.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "address")
    @ApiModelProperty(name = "business", notes = "Business details")
    private Set<Business> businesses;
    /**
     * It has one-to-many relation with {@link Staff} class.
     * It represents address of staffs.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "address")
    @ApiModelProperty(name = "business", notes = "Business details")
    private Set<Staff> staffs;
    /**
     * It has one-to-many relation with {@link Client} class.
     * It represents address of clients.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "address")
    @ApiModelProperty(name = "clients", notes = "Client details")
    private Set<Client> clients;
    /**
     * It represents record created date
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @ApiModelProperty(name = "createdDate", notes = "Address created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Address updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
