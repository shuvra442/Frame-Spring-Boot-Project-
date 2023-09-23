package in.ovaku.frame.framebackend.configurations;
/*
 * Copyright (c) 2023 Ovaku.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Temporary CORS configuration class.
 *
 * @Todo: Replace with spring security CORS config.
 *
 * @author Sunny Batabyal
 * @version 1.0
 * @since 25/02/23
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}