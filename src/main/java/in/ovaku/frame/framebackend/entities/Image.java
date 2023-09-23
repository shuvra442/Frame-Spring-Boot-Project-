package in.ovaku.frame.framebackend.entities;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import in.ovaku.frame.framebackend.entities.enums.ImageType;
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

/**
 * This class is an entity class with 8 member variables.
 * It contains different image specification.
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
@ApiModel(description = "It contains Image details")
public class Image {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(name = "id", notes = "Image Id", required = true, value = "1")
    private Long id;
    /**
     * It has one-to-many relation with {@link Event} class.
     * It represents events images.
     */
    @ManyToOne
    @JoinColumn(name = "event_id")
    @ApiModelProperty(name = "event", notes = "Image event details")
    private Event event;
    /**
     * It represents image name.
     */
    @ApiModelProperty(name = "name", notes = "Image name", required = true, value = "xyz")
    private String name;
    /**
     * It represents image size.
     */
    @Column(length = 11)
    @ApiModelProperty(name = "size", notes = "Image size", required = true, value = "xyz")
    private Integer size;
    /**
     * It represents image extension.
     */
    @ApiModelProperty(name = "extension", notes = "Image extension", required = true, value = "xyz")
    private String extension;
    /**
     * It represents image type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @ApiModelProperty(name = "imageType", notes = "Image type", required = true, value = "xyz")
    private ImageType imageType;
    /**
     * It represents image is available or not.
     */
    @Builder.Default
    @ApiModelProperty(name = "isActive", notes = "Image is available or not ", required = true, value = "true/false")
    private Boolean isActive = true;
    /**
     * It represents Image is selected or not.
     */
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "imageSelectionStatus", notes = "Image is selected or not ", required = true,
            value = "ACCEPT/REJECT/LOVE/PENDING")
    private ImageSelectionStatus imageSelectionStatus = ImageSelectionStatus.PENDING;
    /**
     * It represents record created date.
     */
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "createdDate", notes = "Image created date", required = true, value = "00/00/0000")
    private Date createdDate;
    /**
     * It represents record updated date.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "updatedDate", notes = "Event updated date", required = true, value = "00/00/0000")
    private Date updatedDate;
}
