package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import in.ovaku.frame.framebackend.entities.enums.ImageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is a dto class of Image with 8 member variables.
 * It defines the details of different image.
 *
 * @author Sohan
 * @version 1.0
 * @since 18/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains image details")
public class ImageDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Image Id", required = true, value = "1")
    private Long id;
    /**
     * It represents events images.
     */
    @JsonProperty("event")
    @ApiModelProperty(name = "eventDto", notes = "Image event details")
    private EventDto eventDto;
    /**
     * It represents image name.
     */
    @ApiModelProperty(name = "name", notes = "Image name", required = true, value = "xyz")
    private String name;
    /**
     * It represents image size.
     */
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
    @ApiModelProperty(name = "imageType", notes = "Image type", required = true, value = "xyz")
    private ImageType imageType;
    /**
     * It represents Image is selected status.
     */
//    @NotNull
    @ApiModelProperty(name = "imageSelectionStatus", notes = "Image is selected or not ", required = true, value = "ACCEPT/REJECT/LOVE/PENDING")
    private ImageSelectionStatus imageSelectionStatus;
    /**
     * It represents image is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Image is Active or not ", required = true, value = "true/false")
    private Boolean isActive = true;
}
