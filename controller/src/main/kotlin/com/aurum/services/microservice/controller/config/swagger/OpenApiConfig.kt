package com.aurum.services.microservice.controller.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    fun apiInfo(): Info {
        return Info().title("Microservice Template API")
                .description("Microservice Template Middleware")
                .version("1.0.0")
                .license(License().name("Apache 2.0")
                        .url("http://springdoc.org"))
    }

    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        val bearerToken = "Bearer Token"
        val basic = "Basic Auth"
        return OpenAPI()
                .addSecurityItem(SecurityRequirement().addList(bearerToken).addList(basic))
                .components(
                        Components()
                                .addSecuritySchemes(bearerToken,
                                        SecurityScheme()
                                                .name(bearerToken)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(apiInfo())
    }

}
