package com.eemarisademello.eletiva_geotec_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class EletivaGeotecApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EletivaGeotecApiGatewayApplication.class, args);
	}

	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user_route", r -> r.path("/user/**")
                        .uri("http://localhost:8080"))
                .route("record_route", r -> r.path("/record/**")
                        .uri("http://localhost:8081"))
                .build();

	}

}
