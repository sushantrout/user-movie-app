package com.documentor.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Documentation Aggregator API",
        version = "1.0",
        description = "Aggregates Swagger/OpenAPI definitions from microservices"
    )
)
@Configuration
public class SwaggerConfig {
}
