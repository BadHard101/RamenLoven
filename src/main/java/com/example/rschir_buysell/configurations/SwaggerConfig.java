package com.example.rschir_buysell.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springDocOpenApi() {
        return new OpenAPI()
                .info(springDocInfo());
    }

    public Info springDocInfo() {
        return new Info()
                .title("Ramen Loven")
                .description("Ramen Loven")
                .version("1.0.0");
    }
}