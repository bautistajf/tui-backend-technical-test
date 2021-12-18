package com.tui.proof.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@OpenAPIDefinition(
        info = @Info(
                title = "Tui order API",
                version = "0.0.1",
                license = @License(name = "Apache 2.0")
        )
)
public class OpenApiConfiguration {

    private static final String BEARERAUTH = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addSecurityItem(
                        new SecurityRequirement().addList(BEARERAUTH))
                .components(
                        new Components().addSecuritySchemes(
                                BEARERAUTH,
                                new SecurityScheme()
                                        .name(BEARERAUTH)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
