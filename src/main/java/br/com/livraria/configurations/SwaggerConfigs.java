package br.com.livraria.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfigs {
    @Bean
    OpenAPI customOpenApi() {
        OpenAPI openAPI = new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Livraria API")
                        .description("Parte de um projeto de uma livraria com Spring Boot")
                        .version("v1")
                        .contact(new Contact()
                                .name("Suporte Paulo")
                                .email("suporte@paulo.com.br")
                                .url("https://www.paulo.com.br"))
                        .termsOfService("https://www.paulo.com.br")
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
        return openAPI;
    }
}
