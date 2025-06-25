package com.eemarisademello.eletiva_geotec_api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        System.out.println(">>> [DEBUG] Configurando segurança do Gateway");
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Desativa CSRF no Gateway
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**").permitAll()
                        .anyExchange().permitAll()
                );
        return http.build();
    }
}
