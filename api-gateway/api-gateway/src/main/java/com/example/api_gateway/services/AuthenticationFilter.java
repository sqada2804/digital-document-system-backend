package com.example.api_gateway.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final RouterValidator validator;
    private final JwtUtils jwtUtils;

    public AuthenticationFilter(RouterValidator validator, JwtUtils jwtUtils) {
        super(Config.class);
        this.validator = validator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpRequest serverHttpRequest = request;

            if(validator.isSecured.test(request)){
                if(authMissing(request)){
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7);
                } else {
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                String token = authHeader.substring(7);

                if (jwtUtils.isExpired(token)){
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                Integer userId = jwtUtils.extractUserId(authHeader);
                if(userId == null){
                    return onError(exchange, HttpStatus.UNAUTHORIZED);
                }

                serverHttpRequest = request.mutate()
                        .header("X-User-Id", userId.toString())
                        .build();
            }
            logger.warn("Processing request on through gateway: "+ exchange.getRequest().getURI());
            return chain.filter(exchange.mutate().request(serverHttpRequest).build());
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    public static class Config{
        public Config(){}
    }
}
