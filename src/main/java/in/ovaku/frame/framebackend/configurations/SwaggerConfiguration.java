package in.ovaku.frame.framebackend.configurations;
/*
 * Copyright (c) 2022 Ovaku.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * This is a configuration class for Swagger
 *
 * @author sohan
 * @version 1.0
 * @since 29/06/22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * This method define {@link Docket} bean.
     * It returns an instance of ApiSelectorBuilder,
     * which control the endpoints exposed by Swagger
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket swaggerConfigurationDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/**"))
                .apis(RequestHandlerSelectors.basePackage("in.ovaku.frame.framebackend"))
                .build()
                .apiInfo(apiDetails());
    }

    /**
     * This method add custom information about the api.
     *
     * @return {@link ApiInfo}
     */
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Frame",
                "Backend Spring Boot Project of Frame",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ovaku", "https://theovaku.com", "ops@ovaku.in"),
                "Api License",
                "https://xyz.com",
                Collections.emptyList());

    }
}
