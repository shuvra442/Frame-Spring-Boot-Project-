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
 * This class is an entity class with 10 member variables.
 * It defines different events information of clients and staffs.
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
public class Event {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Event Id", required = true, value = "1")
    private Long id;
    /**
     * It has many-to-one relation with {@link Client} class.
     * It represents client managed events.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @ApiModelProperty(name = "client", notes = "Event address details")
    private Client client;
    /**
     * It has many-to-one relation with {@link Staff} class.
     * It represents staffs who are assign with this events.
     */
    @ManyToOne
    @JoinColumn(name = "staff_id")
    @ApiModelProperty(name = "staff", notes = "Staff under event")
    private Staff staff;
    /**
     * It represents event name.
     */
    @ApiModelProperty(name = "name", notes = "Event name", required = true, value = "xyz")
    private String name;
    /**
     * It represents brief description of this event.
     */
    @Lob
    @ApiModelProperty(name = "description", notes = "Event description", required = true, value = "xyz")
    private String description;
    /**
     * It represents event date.
     */
    @ApiModelProperty(name = "date", notes = "Event date", required = true, value = "00/00/0000")
    private Date date;
    /**
     * It represents event clicked images stored folder path.
     */
    @Lob
    @ApiModelProperty(name = "sourceDirectoryPath", notes = "Event image save path", required = true, value = "00/00/0000")
    private String sourceDirectoryPath;
    /**
     * It has one-to-many relation with {@link Image} class.
     * It represents events images.
     */
    @OneToMany(mappedBy = "event")
    @ApiModelProperty(name = "images", notes = "Event capture images details")
    private Set<Image> images;
    /**
     * It represents event is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Event is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Event created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Event updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
