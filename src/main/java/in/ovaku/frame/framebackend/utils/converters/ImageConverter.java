package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ImageDto;
import in.ovaku.frame.framebackend.entities.Image;

/**
 * This is a converter interface.
 * It maps {@link Image} entity class with {@link ImageDto}class.
 *
 * @author Sohan
 * @version 1.0
 * @since 18/07/22
 */
public interface ImageConverter {
    /**
     * This method convert {@link ImageDto} to {@link Image}
     *
     * @return {@link Image}
     */
    Image imageDtoToImage(ImageDto imageDto);

    /**
     * This method convert {@link Image} to {@link ImageDto}
     *
     * @return {@link ImageDto}
     */
    ImageDto imageToImageDto(Image image);
}
