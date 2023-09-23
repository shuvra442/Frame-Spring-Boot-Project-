package in.ovaku.frame.framebackend.utils.converters;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.requests.ClientRegistrationDto;
import in.ovaku.frame.framebackend.entities.Client;

/**
 * This is a converter interface.
 * It is used to map {@link Client} entity class with {@link ClientDto} dto class.
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
public interface ClientConverter {

    /**
     * This method convert {@link Client} to {@link ClientRegistrationDto}
     *
     * @return {@link ClientRegistrationDto}
     */
    ClientRegistrationDto clientToClientRegDto(Client client);

    /**
     * This method convert {@link ClientRegistrationDto} to {@link Client}
     *
     * @return {@link Client}
     */
    Client clientRegDtoToClient(ClientRegistrationDto clientRegistrationDto);

    /**
     * This method convert {@link Client} to {@link ClientDto}
     *
     * @return {@link ClientDto}
     */
    ClientDto clientToClientDto(Client client);

    /**
     * This method convert {@link ClientDto} to {@link Client}
     *
     * @return {@link Client}
     */
    Client clientDtoToClient(ClientDto clientDto);

    /**
     * This method update existing {@link Client} by {@link ClientDto}
     *
     * @return {@link Client}
     */
    Client toUpdateClient(ClientDto clientDto, Client client);
}
