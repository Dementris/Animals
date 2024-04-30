package com.dementris.testtask.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Dmytro Shlapak",
                        email = "bdlap.shl@gmail.com",
                        url = "https://github.com/Dementris/TestTask"
                ),
                description = "OpenApi Documentation for Test task",
                title = "Animal Api - Dmytro Shlapak",
                version = "1.0"
        ),
        servers = @Server(
                description = "Local environment",
                url = "http://localhost:8080/"
        )
)
public class OpenApiConfig {

}
