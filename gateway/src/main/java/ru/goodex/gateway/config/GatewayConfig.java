package ru.goodex.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("web",
                        route -> route.path("/web/**")
                                .filters(filter->filter.stripPrefix(1))
                                .uri("lb://web"))
                .route("service",
                        route ->route.path("/api")
                                .uri("lb://service"))
                .build();
    }
}
