package com.eemarisademello.eletiva_geotec_api_gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class AuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();
        var path = request.getPath();

        System.out.println("Intercepted request: " + path);

        // Permitir requisições públicas (exemplo)
        if (request.getPath().toString().contains("/auth")) {
            System.out.println("Public route; skipping token validation.");
            return chain.filter(exchange);
        }

        var authHeader = request.getHeaders().getFirst("Authorization");
        System.out.println("Authorization header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Authorization header missing or invalid");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token ausente ou inválido");
        }

        var token = authHeader.substring(7);

        try {
            if (!jwtUtil.isTokenValid(token)) {
                System.out.println("Invalid or expired token: " + token);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
            }

            System.out.println("Valid token. Access granted. Token: " + token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro na validação do token: " + e.getMessage());
        }

        return chain.filter(exchange);
    }
}
