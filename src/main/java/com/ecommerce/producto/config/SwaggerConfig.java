package com.ecommerce.producto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Producto Service")
                        .version("1.0")
                        .description("Microservicio de gestión de productos. Permite crear, consultar, actualizar y eliminar productos,así como filtrarlos por id. Requiere autenticación mediante JWT con roles USUARIO o ADMIN."));
    
        
        }
    }
    
    
