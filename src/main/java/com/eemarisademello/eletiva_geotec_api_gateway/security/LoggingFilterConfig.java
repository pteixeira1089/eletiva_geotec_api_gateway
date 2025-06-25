package com.eemarisademello.eletiva_geotec_api_gateway.security;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class LoggingFilterConfig {

    @Bean
    public GlobalFilter loggingFilter() {
        return (ServerWebExchange exchange, GatewayFilterChain chain) -> {
            System.out.println(">>> [DEBUG] Request path: " + exchange.getRequest().getPath());
            return chain.filter(exchange);
        };
    }
}
