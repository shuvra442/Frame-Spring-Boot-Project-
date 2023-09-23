package in.ovaku.frame.framebackend.services;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.requests.ClientRegistrationDto;
import in.ovaku.frame.framebackend.entities.Business;
import in.ovaku.frame.framebackend.entities.Client;

import java.util.List;

/**
 * This interface provides create, retrieve, update and delete operation for client.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface ClientService {
    /**
     * This method return the list of {@link ClientDto}.
     *
     * @param isActive   - true or false to get active or inactive client.
     * @return list of {@link ClientDto}
     */
    List<ClientDto> getAll(Boolean isActive);

    /**
     * This method return the list of {@link ClientDto}.
     *
     * @param businessId - business id of this client.
     * @param isActive   - true or false to get active or inactive client.
     * @return list of {@link ClientDto}
     */
    List<ClientDto> getAllByBusinessId(Long businessId, Boolean isActive);

    /**
     * This method return a specific {@link ClientDto} entity identified by
     * the given {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param isActive   - true or false to get active or inactive client.
     * @return {@link ClientDto}
     */
    ClientDto getById(Long id, Boolean isActive);

    /**
     * This method return a specific {@link ClientDto} entity identified by
     * the given {@link Business} id and {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param businessId - business id of the client.
     * @param isActive   - true or false to get active or inactive client.
     * @return {@link ClientDto}
     */
    ClientDto getByBusinessIdAndId(Long businessId, Long id, Boolean isActive);

    /**
     * This method return a specific {@link Client} entity identified by {@link Client} id.
     *
     * @param id -id of the client entity to find. Must not be null.
     * @return {@link Client}
     */
    Client getById(Long id);

    /**
     * This method register new {@link Client} for {@link Business}.
     *
     * @param businessId            - id of {@link Business} entity to add {@link Client}.Must not be null.
     * @param clientRegistrationDto -clientDto to be registered.
     * @return {@link ClientRegistrationDto}
     */
    ClientRegistrationDto add(Long businessId, ClientRegistrationDto clientRegistrationDto);

    /**
     * This method update {@link Client} identified by {@link Client} id.
     *
     * @param id         -id of the client entity to find. Must not be null.
     * @param clientDto  - {@link ClientDto} to be updated.
     * @return {@link ClientDto}
     */
    ClientDto update(Long id, ClientDto clientDto);

    /**
     * This method update {@link Client} identified by {@link Business} id and {@link Client} id.
     *
     * @param businessId - business id of the client.
     * @param id         -id of the client entity to find. Must not be null.
     * @param clientDto  - {@link ClientDto} to be updated.
     * @return {@link ClientDto}
     */
    ClientDto updateByBusinessIdAndId(Long businessId, Long id, ClientDto clientDto);

    /**
     * This method delete {@link Client} entity identified by {@link Business} id and {@link Client} id.
     *
     * @param id         -id of the client entity to delete. Must not be null.
     * @return true or false
     */
    Boolean delete(Long id);

    /**
     * This method delete {@link Client} entity identified by {@link Business} id and {@link Client} id.
     *
     * @param businessId - business id of the client.
     * @param id         -id of the client entity to delete. Must not be null.
     * @return true or false
     */
    Boolean deleteByBusinessIdAndId(Long businessId, Long id);
}