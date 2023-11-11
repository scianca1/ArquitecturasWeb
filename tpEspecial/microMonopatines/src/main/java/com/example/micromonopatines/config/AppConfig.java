package com.example.micromonopatines.config;

//import jakarta.persistence.Column;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean ("clienteRest")
    public RestTemplate registrarRestTemplate(){
        return new RestTemplate();
    }
}
