package in.ovaku.frame.framebackend.configurations;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a configuration class for Model Mapper
 * It creates a @Bean factory method to create ModelMapper instance
 *
 * @author sohan
 * @version 1.0
 * @since 25/05/22
 */
@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper nullSkipModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);
        return modelMapper;
    }
}
