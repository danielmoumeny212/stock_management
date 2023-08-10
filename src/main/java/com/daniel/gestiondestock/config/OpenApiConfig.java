package com.daniel.gestiondestock.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Daniel", email = "danidkm242@gmail.com"), description = "OpenApi documentation for Spring Security", title = "OpenApi specification -Daniel", version = "1.0"

), servers = {
    @Server(description = "local ENV", url = "http://localhost:8181")

})
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT auth description",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}