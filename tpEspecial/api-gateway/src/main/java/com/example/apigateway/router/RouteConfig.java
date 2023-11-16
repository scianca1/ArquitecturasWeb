package com.example.apigateway.router;

import com.example.apigateway.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes( RouteLocatorBuilder builder, AuthenticationFilter authFilter ) {
        return builder.routes()
                .route("usuario-service", r -> r.path("/usuario/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8003"))
                .route("cuenta-service", r -> r.path("/cuenta/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8003"))
                .route("monopatin-service", r -> r.path("/monopatin/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8001"))
                .route("parada-service", r -> r.path("/parada/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8001"))
                .route("viaje-service", r -> r.path("/viaje/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8004"))
                .route("admin-service", r -> r.path( "/administrador/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8002"))
                .route("reporte-service", r -> r.path( "/reporteMonopatin/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8002"))
                .build();
    }

}