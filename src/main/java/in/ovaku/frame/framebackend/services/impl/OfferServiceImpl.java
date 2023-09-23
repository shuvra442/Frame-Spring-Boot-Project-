package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.OfferDto;
import in.ovaku.frame.framebackend.entities.Offer;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.OfferRepository;
import in.ovaku.frame.framebackend.services.OfferService;
import in.ovaku.frame.framebackend.utils.converters.OfferConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link OfferService}
 * It contain different offer logic for Service
 *
 * @author sohan
 * @version 1.0
 * @since 10/07/22
 */
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferConverter offerConverter;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public OfferServiceImpl(OfferRepository offerRepository, OfferConverter offerConverter) {
        this.offerRepository = offerRepository;
        this.offerConverter = offerConverter;
    }

    /**
     * This method return the list of {@link OfferDto}.
     *
     * @param isActive - true or false to get active or inactive Offer.
     * @return list of offerDto
     */
    @Override
    public List<OfferDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Offer> offerList;

        if (isActive) {
            offerList = offerRepository.findAllActive();
        } else {
            offerList = offerRepository.findAll();
        }

        if (offerList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched offer list => {}", offerList);
        return offerList.stream()
                .map(offerConverter::offerToOfferDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link OfferDto} entity identified by the given {@link Offer} id.
     *
     * @param id       - id of the entity to find. Must not be null.
     * @param isActive - true or false to get active or inactive Offer.
     * @return offerDto entity
     */
    @Override
    public OfferDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with id => {}", id);
        Optional<Offer> optionalOffer;

        if (isActive) {
            optionalOffer = offerRepository.findActiveById(id);
        } else {
            optionalOffer = offerRepository.findById(id);
        }

        if (optionalOffer.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Offer offer = optionalOffer.get();
        logger.debug("Fetched Offer entity => {}", offer);
        OfferDto offerDto = offerConverter.offerToOfferDto(offer);
        logger.debug("Converted DTO => {} from service entity", offerDto);
        return offerDto;
    }

    /**
     * This method create new {@link Offer}.
     *
     * @param offerDto -dto to be created.
     * @return offerDto entity
     */
    @Override
    public OfferDto add(OfferDto offerDto) {
        logger.debug("Into add entity service method with data =>{}", offerDto);
        Offer offer = offerConverter.offerDtoToOffer(offerDto);
        logger.debug("Converted service entity => {} from DTO", offer);
        offer = offerRepository.save(offer);
        logger.debug("Record saved =>{} in DB", offer);
        OfferDto dto = offerConverter.offerToOfferDto(offer);
        logger.debug("Converted DTO => {} from service entity", dto);
        return dto;
    }

    /**
     * This method update {@link Offer} identified by serviceID.
     *
     * @param id       - id of the entity to update. Must not be null.
     * @param offerDto - {@link OfferDto} to be updated.
     * @return offerDto entity
     */
    @Override
    public OfferDto update(Long id, OfferDto offerDto) {
        logger.debug("Into update entity service method with id => {} and data => {}", id, offerDto);
        // Validate.
        if (!offerRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Offer offer = offerConverter.offerDtoToOffer(offerDto);
        logger.debug("Converted offer entity => {} from DTO => {}", offer, offerDto);
        offer.setId(id);
        offer = offerRepository.save(offer);
        logger.debug("Offer record updated in DB=>{}", offer);
        OfferDto dto = offerConverter.offerToOfferDto(offer);
        logger.debug("Converted DTO => {} from offer entity", dto);
        return dto;
    }

    /**
     * This method delete the {@link Offer} entity identified by the given id.
     *
     * @param id - id of the entity to delete. Must not be null.
     * @return true or false
     */
    @Override
    public Boolean delete(Long id) {
        logger.debug("Into delete entity service method with id => {}", id);
        // Validate.
        if (offerRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Offer offer = offerRepository.getById(id);
        logger.debug("Fetching offer entity by id => {} from DB using repository", id);
        offer.setIsActive(false);
        offerRepository.save(offer);
        logger.debug("Offer entity deleted from DB=>{}", offer);
        return true;
    }
}
