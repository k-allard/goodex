package ru.goodex.gateway.jwt;

import io.jsonwebtoken.Claims;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtTokenProvider jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request =  exchange.getRequest();

        if (!request.getHeaders().containsKey("Authorization")) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);

            return response.setComplete();
        }

        final String token = jwtUtil.getToken(request);

        try {
            jwtUtil.validateToken(token);
        } catch (JwtAuthenticationException e) {
            // e.printStackTrace();
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            addMessage(response);
            return response.setComplete();
        }
        Claims claims = jwtUtil.getClaims(token);
        exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();


        return chain.filter(exchange);
    }

    public void addMessage(ServerHttpResponse response) {
        byte[] bytes = "Some text".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.writeWith(Flux.just(buffer));
    }


}
