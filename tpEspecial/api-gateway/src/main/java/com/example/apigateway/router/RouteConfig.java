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
        System.out.println("router");
        return builder.routes()
                .route("lll", r -> r.path("/usuario/authenticate")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8003/usuario/authenticate"))
                .route("auth-service-login", r -> r.path("/usuario/register")
                               .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                               )
                              .uri("http://localhost:8003/usuario/register"))

                .route("auth-service", r -> r.path("/usuario/**")
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8003"))
                .route("micro-a-product", r -> r.path( "admin/**" )
                        .filters( f ->
                                f.filter( authFilter.apply( new AuthenticationFilter.Config() ) )
                        )
                        .uri("http://localhost:8002"))
                .build();
    }

}