package com.example.apigateway.security;

import com.example.apigateway.dto.ValidateTokenDTO;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final String _AuthHeader = "Authorization";
    List<String> excludedUrls = List.of( "usuario/authenticate" , "usuario/register");
    private final WebClient.Builder webClientBuilder;


    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config ) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst( _AuthHeader );

            // Deshabilitar la verificación del token
//            if (true) {
//                System.out.print("hasta aca llego:apli");
//                return chain.filter(exchange);
//            }
//            "http://localhost:8005/api/validate"
            if( this.isSecured.test( request ) ) {
                return webClientBuilder.build().get()
                        .uri("http://localhost:8003/usuario/validate")
                        .header( _AuthHeader, bearerToken )
                        .retrieve().bodyToMono( ValidateTokenDTO.class )
                        .map( response -> {
                            if( ! response.isAuthenticated() )
                                throw new BadCredentialsException( "JWT invalido" );
                            return exchange;
                        })
                        .flatMap(chain::filter);
            }
            return chain.filter(exchange);
        };
    }

    private final Predicate<ServerHttpRequest> isSecured = request -> excludedUrls.stream().noneMatch(uri -> request.getURI().getPath().contains(uri) );


    /**
     * Required by AbstractGatewayFilterFactory
     */
    @NoArgsConstructor
    public static class Config {}

}