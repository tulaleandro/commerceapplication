package com.app.commerce.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Value;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;
import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Optional.ofNullable;

/**
 * The Class OpenApiAutoConfigurations
 */
@UtilityClass
public class OpenApiAutoConfigurations {

    /**
     * The Class OpenApiAutoConfiguration
     */
    @ConditionalOnProperty("springdoc.api-docs.enabled")
    @Configuration
    @NonFinal
    @Value
    @Accessors(fluent = true)
    public static class OpenApiAutoConfiguration {

        ServerProperties serverProperties;

        /**
         * Custom open API.
         *
         * @return the open API
         */
        @Bean
        public OpenAPI customOpenAPI() {

            var serverDefinition = new Server();
            final String securitySchemeName = "bearerAuth";


            var contextPath = ofNullable(serverProperties().getServlet()
                    .getContextPath())
                    .orElse("/");

            serverDefinition.setUrl(contextPath);

            return new OpenAPI().addServersItem(serverDefinition)
                    .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                    .components(
                            new Components()
                                    .addSecuritySchemes(securitySchemeName,
                                            new SecurityScheme()
                                                    .name(securitySchemeName)
                                                    .type(SecurityScheme.Type.HTTP)
                                                    .scheme("bearer")
                                                    .bearerFormat("JWT")
                                    )
                    );
        }

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }

    }
}
