package in.ovaku.frame.framebackend.services.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.requests.ClientRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Client;
import in.ovaku.frame.framebackend.exceptions.ResourceAlreadyExistsException;
import in.ovaku.frame.framebackend.exceptions.ResourceNotFoundException;
import in.ovaku.frame.framebackend.repositories.BusinessRepository;
import in.ovaku.frame.framebackend.repositories.ClientRepository;
import in.ovaku.frame.framebackend.services.ClientService;
import in.ovaku.frame.framebackend.utils.converters.ClientConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implement an interface {@link ClientService}
 * It contain different business logic for Client
 *
 * @author sohan
 * @version 1.0
 * @since 01/07/22
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientConverter clientConverter;
    private final BusinessRepository businessRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public ClientServiceImpl(ClientRepository clientRepository, ClientConverter clientConverter,
                             BusinessRepository businessRepository) {
        this.clientRepository = clientRepository;
        this.clientConverter = clientConverter;
        this.businessRepository = businessRepository;
    }

    /**
     * This method return the list of {@link ClientDto}.
     *
     * @param isActive   - true or false to get active or inactive client.
     * @return all clientDto entity.
     */
    @Override
    public List<ClientDto> getAll(Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Client> clientList;
        if (isActive) {
            clientList = clientRepository.findAllActive();
        } else {
            clientList = clientRepository.findAll();
        }
        if (clientList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched client list => {}", clientList);
        return clientList.stream()
                .map(clientConverter::clientToClientDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return the list of {@link ClientDto}.
     *
     * @param businessId - business id of this client.
     * @param isActive   - true or false to get active or inactive client.
     * @return all clientDto entity.
     */
    @Override
    public List<ClientDto> getAllByBusinessId(Long businessId, Boolean isActive) {
        logger.debug("Into getAll service method");
        List<Client> clientList;
        if (isActive) {
            clientList = clientRepository.findAllActiveByBusinessId(businessId);
        } else {
            clientList = clientRepository.findAllByBusinessId(businessId);
        }
        if (clientList.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        logger.debug("Fetched client list => {}", clientList);
        return clientList.stream()
                .map(clientConverter::clientToClientDto)
                .collect(Collectors.toList());
    }

    /**
     * This method return a specific {@link ClientDto} entity identified by
     * the given {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param isActive   - true or false to get active or inactive client.
     * @return a clientDto entity
     */
    @Override
    public ClientDto getById(Long id, Boolean isActive) {
        logger.debug("Into get entity service method with client id => {}", id);
        Optional<Client> optionalClient;

        if (isActive) {
            optionalClient = clientRepository.findActiveById(id);
        } else {
            optionalClient = clientRepository.findById(id);
        }
        if (optionalClient.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Client client = optionalClient.get();
        logger.debug("Fetched client entity => {}", client);
        ClientDto clientDto = clientConverter.clientToClientDto(client);
        logger.debug("Converted DTO => {} from client entity", clientDto);
        return clientDto;
    }

    /**
     * This method return a specific {@link ClientDto} entity identified by
     * the given {@link Business} id and {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param businessId - business id of the client.
     * @param isActive   - true or false to get active or inactive client.
     * @return {@link ClientDto}
     */
    @Override
    public ClientDto getByBusinessIdAndId(Long businessId, Long id, Boolean isActive) {
        logger.debug("Into get entity service method with business id => {} and client id => {}",businessId, id);
        Optional<Client> optionalClient;

        if (isActive) {
            optionalClient = clientRepository.findActiveByBusinessIdAndId(businessId, id);
        } else {
            optionalClient = clientRepository.findByBusinessIdAndId(businessId, id);
        }
        if (optionalClient.isEmpty()) {
            logger.error("No data available!");
            throw new ResourceNotFoundException("No data available!");
        }
        Client client = optionalClient.get();
        logger.debug("Fetched client entity => {}", client);
        ClientDto clientDto = clientConverter.clientToClientDto(client);
        logger.debug("Converted DTO => {} from client entity", clientDto);
        return clientDto;
    }

    /**
     * This method return a specific {@link Client} entity identified by {@link Client} id.
     *
     * @param id -id of the client entity to find. Must not be null.
     * @return client entity
     */
    @Override
    public Client getById(Long id) {
        logger.debug("Into get entity service method with client id => {}", id);
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Client client = optionalClient.get();
        logger.debug("Fetched client entity => {}", client);
        return client;
    }

    /**
     * This method register new {@link Client} for {@link Business}.
     *
     * @param businessId            - id of {@link Business} entity to add {@link Client}.Must not be null.
     * @param clientRegistrationDto -clientDto to be registered.
     * @return clientRegistrationDto entity
     */
    @Override
    public ClientRegistrationDto add(Long businessId, ClientRegistrationDto clientRegistrationDto) {

        logger.debug("Into add entity service method with business id => {} and data =>{}", businessId, clientRegistrationDto);
        //Validate
        if (clientRepository.findByPhoneNo(clientRegistrationDto.getPhoneNo()).isPresent()) {
            logger.error("Client data already exist =>{}", clientRegistrationDto);
            throw new ResourceAlreadyExistsException("Already Exists!");
        }
        Client client = clientConverter.clientRegDtoToClient(clientRegistrationDto);
        logger.debug("Converted client entity => {} from DTO", client);
        client.setBusiness(businessRepository.getById(businessId));
        client = clientRepository.save(client);
        logger.debug("Record saved =>{} in DB", client);
        ClientRegistrationDto registrationDto = clientConverter.clientToClientRegDto(client);
        logger.debug("Converted DTO => {} from client entity", registrationDto);
        return registrationDto;
    }

    /**
     * This method update {@link Client} by {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param clientDto  - {@link ClientDto} to be updated.
     * @return clientDto entity
     */
    @Override
    public ClientDto update(Long id, ClientDto clientDto) {
        logger.debug("Into update entity service method with " +
                " client id => {} and data => {}", id, clientDto);
        //Validate.
        if (!clientRepository.existsById(id)) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Client client = clientConverter.clientDtoToClient(clientDto);
        logger.debug("Converted client entity => {} from DTO => {}", client, clientDto);
        client.setId(id);
        client.setBusiness(businessRepository.getById(clientRepository.findById(id).get().getBusiness().getId()));
        client = clientRepository.save(client);
        logger.debug("Client record updated in DB=>{}", client);
        ClientDto dto = clientConverter.clientToClientDto(client);
        logger.debug("Converted DTO => {} from client entity", dto);
        return dto;
    }

    /**
     * This method update {@link Client} identified by {@link Business} id and {@link Client} id.
     *
     * @param businessId - business id of the client.
     * @param id         -id of the client entity to find. Must not be null.
     * @param clientDto  - {@link ClientDto} to be updated.
     * @return clientDto entity
     */
    @Override
    public ClientDto updateByBusinessIdAndId(Long businessId, Long id, ClientDto clientDto) {
        logger.debug("Into update entity service method with business id => {}, " +
                " client id => {} and data => {}", businessId, id, clientDto);
        //Validate.
        if (clientRepository.findActiveByBusinessIdAndId(businessId, id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }

        Client client = clientConverter.clientDtoToClient(clientDto);
        logger.debug("Converted client entity => {} from DTO => {}", client, clientDto);
        client.setId(id);
        client.setBusiness(businessRepository.getById(businessId));
        client = clientRepository.save(client);
        logger.debug("Client record updated in DB=>{}", client);
        ClientDto dto = clientConverter.clientToClientDto(client);
        logger.debug("Converted DTO => {} from client entity", dto);
        return dto;
    }

    /**
     * This method delete {@link Client} entity identified by {@link Business} id and {@link Client} id.
     *
     * @param id         -id of the client entity to delete. Must not be null.
     * @return inactive clientDto entity
     */
    @Override
    public Boolean delete(Long id) {

        logger.debug("Into delete entity service method with client id => {}", id);
        //Validate
        if (clientRepository.findActiveById(id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Client client = clientRepository.getById(id);
        logger.debug("Fetching client entity by id => {} from DB using repository", id);
        client.setIsActive(false);
        client = clientRepository.save(client);
        logger.debug("Client entity deleted from DB=>{}", client);
        return true;
    }

    /**
     * This method delete {@link Client} entity identified by {@link Business} id and {@link Client} id.
     *
     * @param businessId - business id of the client.
     * @param id         -id of the client entity to delete. Must not be null.
     * @return inactive clientDto entity
     */
    @Override
    public Boolean deleteByBusinessIdAndId(Long businessId, Long id) {

        logger.debug("Into delete entity service method with business id => {} and client id => {}", businessId, id);
        //Validate
        if (clientRepository.findActiveByBusinessIdAndId(businessId, id).isEmpty()) {
            logger.error("Resource doesn't exist!");
            throw new ResourceNotFoundException("Resource doesn't exist!");
        }
        Client client = clientRepository.getById(id);
        logger.debug("Fetching client entity by id => {} from DB using repository", id);
        client.setIsActive(false);
        client = clientRepository.save(client);
        logger.debug("Client entity deleted from DB=>{}", client);
        return true;
    }
}