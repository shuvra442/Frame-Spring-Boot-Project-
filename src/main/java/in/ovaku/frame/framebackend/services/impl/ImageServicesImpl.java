package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ImageDto;
import in.ovaku.frame.framebackend.entities.Event;
import in.ovaku.frame.framebackend.entities.Image;
import in.ovaku.frame.framebackend.entities.enums.ImageSelectionStatus;
import in.ovaku.frame.framebackend.entities.enums.ImageType;
import in.ovaku.frame.framebackend.exceptions.OperationFailedException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.exceptions.UnsupportedFileFormatException;
import in.ovaku.frame.framebackend.repositories.ImageRepository;
import in.ovaku.frame.framebackend.services.EventService;
import in.ovaku.frame.framebackend.services.ImageServices;
import in.ovaku.frame.framebackend.utils.converters.ImageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link ImageServices}
 * It contain different business logic for image
 *
 * @author sohan
 * @version 1.0
 * @since 18/07/22
 */
@Service
public class ImageServicesImpl implements ImageServices {
    private final ImageRepository imageRepository;
    private final ImageConverter imageConverter;
    private final EventService eventService;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${image.path}")
    private String path;

    public ImageServicesImpl(ImageRepository imageRepository, ImageConverter imageConverter, EventService eventService) {
        this.imageRepository = imageRepository;
        this.imageConverter = imageConverter;
        this.eventService = eventService;
    }

    /**
     * This method return the list of {@link ImageDto}.
     *
     * @param eventId  - id of the event.
     * @param imageSelectionStatus -select the image status.
     * @param isActive - true or false to get active or inactive image.
     * @return list of imageDto
     */
    @Override
    public List<ImageDto> getAllByEvent(Long eventId,ImageSelectionStatus imageSelectionStatus, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Image> imageList;
            if (imageSelectionStatus !=null) {
                if(isActive){
                    imageList = imageRepository.findAllActiveByImageSelection(eventId, imageSelectionStatus);
                } else {
                    imageList = imageRepository.findAllByImageSelection(eventId, imageSelectionStatus);
                }
            }
            else {
                if(isActive){
                    imageList = imageRepository.findAllByEventIdAndIsActive(eventId, true);
                } else {
                    imageList = imageRepository.findAllByEventId(eventId);
                }
            }
        if (imageList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched service list => {}", imageList);
        return imageList.stream()
                .map(imageConverter::imageToImageDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link ImageDto} entity identified by the given {@link Event} id.
     *
     * @param eventId  - id of the event.
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive image.
     * @return imageDto
     */
    @Override
    public ImageDto getByEvent(Long eventId, Long id, Boolean isActive) {
        logger.debug("Into get entity service method with event id => {} and event id => {}", eventId, id);
        Optional<Image> optionalImage;
        if (isActive) {
            optionalImage = imageRepository.findByEventIdAndIdAndIsActive(eventId, id, true);
        } else {
            optionalImage = imageRepository.findByEventIdAndId(eventId, id);
        }
        if (optionalImage.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Image image = optionalImage.get();
        logger.debug("Fetched event entity => {}", image);
        ImageDto imageDto = imageConverter.imageToImageDto(image);
        logger.debug("Converted DTO => {} from image entity", imageDto);
        return imageDto;
    }

    /**
     * This method add new list of {@link Image}.
     *
     * @param eventId - id of the event.
     * @param files   -uploaded file.
     * @return list of imageDto
     */
    @Override
    public List<ImageDto> addByEvent(Long eventId, MultipartFile[] files) throws IOException {
        logger.debug("Into add entity service method with eventId=> {} and  data =>{}", eventId, files);

        Event event = eventService.findById(eventId, true);

        //Check and create path in local directory
        Path paths = Paths.get(path);
        if (!Files.isDirectory(paths)) {
            Files.createDirectory(paths);
        }

        List<ImageDto> responseDtoList = new ArrayList<>();
        List<String> failedFilesList = new ArrayList<>();

        //Check uploading file and save in directory
        for (MultipartFile file : files) {
            // Check file type is image otherwise continue and put in the errors list.
            String fileType = Objects.requireNonNull(file.getContentType());
            String originalFileName = file.getOriginalFilename();
            if (!fileType.contains("image")) {
                failedFilesList.add(originalFileName);
                logger.error("File format {} Not supported for file name {}", fileType, originalFileName);
                continue;
            }
            String extension = fileType.substring(6).toUpperCase();
            paths = Paths.get(path + "/" + originalFileName);
            try {
                file.transferTo(paths);
                responseDtoList.add(saveImage(file, event, ImageType.valueOf(extension)));
            } catch (Exception ex) {
                failedFilesList.add(originalFileName);
                logger.error(" Exception: {}", ex.getMessage());
            }
        }

        if (!CollectionUtils.isEmpty(failedFilesList)) {
            logger.error("File format not supported for {} files", failedFilesList.size());
            throw new UnsupportedFileFormatException("Failed to upload or file format Not supported for files: " +
                    String.join(", ", failedFilesList));
        }

        return responseDtoList;
    }

    /**
     * This method add image specification in DB
     *
     * @param file      -image file
     * @param event     -event entity
     * @param imageType -type of image
     * @return imageDto
     */
    private ImageDto saveImage(MultipartFile file, Event event, ImageType imageType) {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .event(event)
                .imageType(imageType)
                .extension(Objects.requireNonNull(file.getContentType()).substring(6))
                .size((int) (file.getSize() / 1024))
                .build();
        imageRepository.save(image);
        logger.debug("Record saved =>{} in DB", image);
        ImageDto imageDto = imageConverter.imageToImageDto(image);
        logger.debug("Converted DTO => {} from event entity", imageDto);
        return imageDto;
    }

    /**
     * This method update list of {@link Image} identified by eventId.
     *
     * @param eventId      - id of the event.
     * @param imageDtoList -List of dto to be select.
     * @return list of imageDto
     */
    @Override
    public List<ImageDto> updateByEvent(Long eventId, List<ImageDto> imageDtoList) {
        logger.debug("Into select entity service method with eventId => {}, and list of data => {}", eventId, imageDtoList);
        List<ImageDto> responseDtoList = new ArrayList<>();
        List<String> failedFilesList = new ArrayList<>();
        //Iterate image and check active and select
        for (ImageDto dto : imageDtoList) {
            Image image = imageConverter.imageDtoToImage(getByEvent(eventId, dto.getId(), true));

            //Check image selection type is not null otherwise store it in failed list
            if (dto.getImageSelectionStatus() == null) {
                failedFilesList.add(image.getName());
                logger.error("Image {} Selection Type is null", image.getName());
                continue;
            } else {
                image.setImageSelectionStatus(dto.getImageSelectionStatus());
            }
            image = imageRepository.save(image);
            logger.debug("Image record updated in DB=>{}", image);
            ImageDto imageDto = imageConverter.imageToImageDto(image);
            logger.debug("Converted DTO => {} from event entity", imageDto);
            responseDtoList.add(imageDto);
        }

        if (!CollectionUtils.isEmpty(failedFilesList)) {
            logger.error("Image Selection Type is null for {}", failedFilesList.size());
            throw new OperationFailedException("Failed to update selection type for images: " +
                    String.join(", ", failedFilesList));
        }
        return responseDtoList;
    }

     /**
     * This method delete the {@link Image} entity identified by the given id.
     *
     * @param eventId - id of the event.
     * @param id      - id of the entity to delete. Must not be null.
     * @return inactive entity
     */
    @Override
    public Boolean delete(Long eventId, Long id) {
        logger.debug("Into delete entity service method with eventId => {} and id => {}", eventId, id);
        //Validate
        if (imageRepository.findByEventIdAndIdAndIsActive(eventId, id, true).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Image image = imageRepository.getById(id);
        logger.debug("Fetching image entity by id => {} from DB using repository", id);
        image.setIsActive(false);
        image = imageRepository.save(image);
        logger.debug("Image entity deleted from DB=>{}", image);
        return true;
    }
}
