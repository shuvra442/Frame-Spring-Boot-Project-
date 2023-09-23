package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ImageDto;
import in.ovaku.frame.framebackend.entities.Image;
import in.ovaku.frame.framebackend.utils.converters.ImageConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link ImageConverter}.
 * It contain converter logic for {@link Image}.
 *
 * @author Sohan
 * @version 1.0
 * @since 08/07/22
 */
@Component
public class ImageConverterImpl implements ImageConverter {
    private final ModelMapper modelMapper;

    public ImageConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link ImageDto} to {@link Image}
     *
     * @return image
     */
    @Override
    public Image imageDtoToImage(ImageDto imageDto) {
        return modelMapper.map(imageDto, Image.class);
    }

    /**
     * This method convert {@link Image} to {@link ImageDto}
     *
     * @return imageDto
     */
    @Override
    public ImageDto imageToImageDto(Image image) {
        return modelMapper.map(image, ImageDto.class);
    }
}
