package ru.tecius.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open API Конфигурация.
 */
@Configuration
public class OpenApiConfig {

  /**
   * Конфигурация OpenAPI.
   *
   * @return OpenApi
   */
  @Bean
  public OpenAPI openApi() {
    var securityScheme =
        new SecurityScheme().type(Type.HTTP).scheme("bearer").bearerFormat("JWT");

    var securityRequirement = new SecurityRequirement().addList("bearerAuth");
    return new OpenAPI()
        .info(
            new Info()
                .title("Knowledge Base Service API")
                .version("1.0.0")
                .description("API для работы с базой знаний")
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")))
        .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
        .addSecurityItem(securityRequirement)
        .servers(List.of(
            new Server()
                .url("http://localhost:8084")
                .description("Knowledge Base (API Gateway) | localhost")));
  }
}
