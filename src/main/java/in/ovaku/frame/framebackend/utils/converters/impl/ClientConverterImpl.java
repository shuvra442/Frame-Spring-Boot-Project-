package in.ovaku.frame.framebackend.utils.converters.impl;
/*
 * Copyright (c) 2022 Ovaku.
 */

import in.ovaku.frame.framebackend.dtos.commons.ClientDto;
import in.ovaku.frame.framebackend.dtos.requests.ClientRegistrationDto;
import in.ovaku.frame.framebackend.entities.Client;
import in.ovaku.frame.framebackend.utils.converters.ClientConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * This class implement an interface {@link ClientConverter}
 * It contain converter logic for {@link Client} and {@link ClientDto}
 *
 * @author Sohan
 * @version 1.0
 * @since 01/07/22
 */
@Component
public class ClientConverterImpl implements ClientConverter {
    private final ModelMapper modelMapper;

    public ClientConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * This method convert {@link Client} to {@link ClientRegistrationDto}
     *
     * @return clientRegistrationDto
     */
    @Override
    public ClientRegistrationDto clientToClientRegDto(Client client) {
        return modelMapper.map(client, ClientRegistrationDto.class);
    }

    /**
     * This method convert {@link ClientRegistrationDto} to {@link Client}
     *
     * @return client
     */
    @Override
    public Client clientRegDtoToClient(ClientRegistrationDto clientRegistrationDto) {
        return modelMapper.map(clientRegistrationDto, Client.class);
    }

    /**
     * This method convert {@link Client} to {@link ClientDto}
     *
     * @return clientDto
     */
    @Override
    public ClientDto clientToClientDto(Client client) {
        return modelMapper.map(client, ClientDto.class);
    }

    /**
     * This method convert {@link ClientDto} to {@link Client}
     *
     * @return client
     */
    @Override
    public Client clientDtoToClient(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    /**
     * This method update existing {@link Client} by {@link ClientDto}
     *
     * @return client
     */
    @Override
    public Client toUpdateClient(ClientDto clientDto, Client client) {
        modelMapper.map(clientDto, client);
        return client;
    }
}
