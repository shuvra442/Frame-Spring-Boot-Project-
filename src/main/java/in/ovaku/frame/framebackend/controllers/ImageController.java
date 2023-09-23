package in.ovaku.frame.framebackend.controllers;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ImageDto;
import in.ovaku.frame.framebackend.dtos.responses.ApiResponseDto;
import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import in.ovaku.frame.framebackend.services.ImageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a controller class of Image.
 * It processes the request and return the view as response.
 *
 * @author SOHAN
 * @version 1.0
 * @since 18/07/22
 */
@RestController
@RequestMapping("/events/{eventId}/images")
@Api(tags = "Image Controller", value = "ImageController", description = "Controller for Event's image")
public class ImageController {
    private final ImageServices imageServices;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ImageController(ImageServices imageServices) {
        this.imageServices = imageServices;
    }

    /**
     * This method is used to get all active or inactive {@link ImageDto} for event
     * It by default get all active {@link ImageDto}.
     *
     * @param eventId -id of the event entity to get image.Must not be null.
     * @param imageSelectionStatus -select the status of the image.
     * @return json
     */
    @ApiOperation(value = "Get list of all Image for an event",
            notes = "View list of all image information ", response = ArrayList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping
    public ResponseEntity<Object> getAll(@PathVariable Long eventId,
                                         @RequestParam(required = false) ImageSelectionStatus imageSelectionStatus,
                                         @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting all image entity list ");
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, imageServices.getAllByEvent(eventId,imageSelectionStatus,isActive),
                        "Successfully data retrieved");
    }

    /**
     * This method is used to get a specific image {@link ImageDto}
     * It by default get active {@link ImageDto}.
     *
     * @param eventId- id of the event entity to get image. Must not be null.
     * @param id       -id of the image entity to find. Must not be null.
     * @return json
     */
    @ApiOperation(value = "Find Image by image id and eventId",
            notes = "Provide a specific image id and eventId to get information ", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully data retrieved"),
            @ApiResponse(code = 404, message = "No data available!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClient(@PathVariable Long eventId, @PathVariable Long id,
                                            @RequestParam(required = false, defaultValue = "true") Boolean isActive) {
        logger.trace("Getting image entity by eventId=>{} and image id=> {}", eventId, id);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.OK, imageServices.getByEvent(eventId, id, isActive)
                        , "Successfully data retrieved");
    }

    /**
     * This method add new image for event.
     *
     * @param eventId - id of the event entity to add image. Must not be null.
     * @param files-  files to add.
     * @return json
     */
    @ApiOperation(value = "Add new image for event", notes = "Add new Image for image using event id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered"),
            @ApiResponse(code = 409, message = "Already Exists!")
    })
    @PostMapping
    public ResponseEntity<Object> addImage(@PathVariable Long eventId, @RequestParam("file") MultipartFile[] files) throws IOException {
        logger.trace("Add new image by event id =>{} with image data => {}", eventId, files);
        return new ApiResponseDto()
                .generateResponse(HttpStatus.CREATED, imageServices.addByEvent(eventId, files)
                        , "Successfully uploaded");
    }

    /**
     * This method is used to select list of image.
     *
     * @param eventId      -id of the business entity to update client. Must not be null.
     * @param imageDtoList - list of dto to be updated
     * @return json
     */
    @ApiOperation(value = "Select list of image by eventId and dto list",
            notes = "Provide a specific event id and dto list to select list of images ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!"),
            @ApiResponse(code = 500, message = "Operation failed!")
    })
    @PutMapping("/bulk-selection")
    public ResponseEntity<Object> updateClient(@PathVariable Long eventId, @RequestBody List<ImageDto> imageDtoList) {
        logger.trace("Selecting images  by event id => {} and with images data => {}", eventId, imageDtoList);
        return new ApiResponseDto().generateResponse(
                HttpStatus.OK, imageServices.updateByEvent(eventId, imageDtoList), "Successfully updated");
    }


        /**
         * This method is used to delete a specific image {@link ImageDto}
         *
         * @param eventId -id of the event entity to delete image. Must not be null.
         * @param id      -id of the image entity to find. Must not be null.
         * @return json
         */
    @ApiOperation(value = "Delete a image by event id and image id",
            notes = "Provide a specific event id and image id to delete information ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 404, message = "Resource doesn't exist!")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImage(@PathVariable Long eventId, @PathVariable Long id) {
        logger.trace("Deleting image entity by event id => {} and id =>{}", eventId, id);
        imageServices.delete(eventId, id);
        return new ApiResponseDto().generateResponse(HttpStatus.OK, null, "Successfully deleted");
    }
}
