package com.example.microusuarios.config;

import com.example.microusuarios.security.Jwt.JwtConfigurer;
import com.example.microusuarios.security.Jwt.TokenProvider;
import com.example.microusuarios.servicios.constant.AuthorityConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;


    /**
     * Password encoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http
                .apply(securityConfigurerAdapter());
        http
                .csrf(AbstractHttpConfigurer::disable)
                // MANEJAMOS LOS PERMISOS A LOS ENDPOINTS.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/authenticate").permitAll()
                        .requestMatchers("/usuario/register").permitAll()

                )
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Nuestra configuracion de JWT.
     */
    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }

}