package in.ovaku.frame.framebackend.dtos.commons;
/*
 * Copyright (c) 2022 Ovaku.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class is a dto class with 6 member variables.
 * It defines the details of different events.
 *
 * @author Sohan
 * @version 1.0
 * @since 06/07/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "It contains Event details")
public class EventDto {
    /**
     * It represents the unique id of every record.
     */
    @ApiModelProperty(name = "id", notes = "Event Id", required = true, value = "1")
    private Long id;
    /**
     * It represents event name.
     */
    @ApiModelProperty(name = "name", notes = "Event name", required = true, value = "xyz")
    private String name;
    /**
     * It represents brief description of this event.
     */
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
    @ApiModelProperty(name = "sourceDirectoryPath", notes = "Event image save path", required = true, value = "00/00/0000")
    private String sourceDirectoryPath;
    /**
     * It represents event is available or not.
     */
    @ApiModelProperty(name = "isActive", notes = "Event is available or not ", required = true, value = "true/false")
    private Boolean isActive;
}
