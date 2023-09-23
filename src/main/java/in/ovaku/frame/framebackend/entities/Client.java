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
 * This class is an entity class with 11 member variables.
 * It defines different clients information.
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
@ApiModel(description = "It contains Client details")
public class Client {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Client Id", required = true, value = "1")
    private Long id;
    /**
     * It represents client name.
     */
    @ApiModelProperty(name = "name", notes = "Client name", required = true, value = "xyz")
    private String name;
    /**
     * It represents client email.
     */
    @Column(length = 70)
    @ApiModelProperty(name = "email", notes = "Client email", required = true, value = "xyz")
    private String email;
    /**
     * It represents client phone number.
     */
    @ApiModelProperty(name = "phoneNo", notes = "Client contact number", required = true, value = "91-XXXXXXXXXX")
    private Long phoneNo;
    /**
     * It represents client password.
     */
    @ApiModelProperty(name = "password", notes = "Client password", required = true, value = "xyz")
    private String password;
    /**
     * It represents client first name.
     */
    @ApiModelProperty(name = "firstName", notes = "Client first name", required = true, value = "xyz")
    private String firstName;
    /**
     * It represents client last name.
     */
    @ApiModelProperty(name = "lastName", notes = "Client last name", required = true, value = "xyz")
    private String lastName;
    /**
     * It has many-to-one relation with {@link Address} class.
     * It represents client address.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @ApiModelProperty(name = "address", notes = "Client address details")
    private Address address;
    /**
     * It has many-to-one relation with {@link Business} class.
     * It represents in which organization client
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    @ApiModelProperty(name = "business", notes = "Client organization details")
    private Business business;
    /**
     * It has one-to-many relation with {@link Event} class.
     * It represents client managed events.
     */
    @OneToMany(mappedBy = "client")
    @ApiModelProperty(name = "events", notes = "Client event details")
    private Set<Event> events;
    /**
     * It represents client is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "CLient is avalable or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Client created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Client updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
