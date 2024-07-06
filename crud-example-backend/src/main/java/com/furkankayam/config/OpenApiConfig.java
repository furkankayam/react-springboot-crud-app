package com.furkankayam.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Furkan",
                        email = "furkan.36kaya@gmail.com",
                        url = "https://furkankayam.github.io/furkankaya.github.io/"
                ),
                title = "Basic App",
                description = "Basic React Spring Boot App",
                version = "v1"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
