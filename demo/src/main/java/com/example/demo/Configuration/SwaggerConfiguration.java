package com.example.demo.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API BIBLIOTECA & UNIVERSIDAD")
                        .version("1.0")
                        .description("CRUD: Categoria, Libro, Usuario, PerfilUsuario, Estudiante, Curso")
                        .contact(new Contact()
                                .name("DESARROLLADOR")
                                .email("prueba@prueba.com")));
    }
}