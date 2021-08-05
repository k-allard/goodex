package ru.goodex.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import ru.goodex.gateway.jwt.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    public GatewayConfig(JwtAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("web",
                        route -> route.path("/web/**")
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://web"))
                .route("service",
                        route -> route.path("/api/**")
                                .filters(f -> {
                                    f=f.filter(filter);
                                    f=f.stripPrefix(1);
                                    return f;
                                })
                                .uri("lb://service"))
                .build();
    }
}
