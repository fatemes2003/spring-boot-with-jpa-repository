package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder)
    {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec
                                .path("/api/v1/products/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/api/v1/products/?(<segment>.*)","/api/v1/products/${segment}" )
                                        .addResponseHeader("My_Response_Time", LocalDateTime.now().toString())
                                        /*.circuitBreaker(config -> config.setName("productCircuitBreaker")
                                                .setFallbackUri("forward:/ajab"))*/
                                )
                                .uri("lb://PRODUCT")
                )
                .route(predicateSpec ->
                        predicateSpec
                                .path("/api/v1/coupons/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/api/v1/coupons/?(<segment>.*)","/api/v1/coupons/${segment}" )
                                        .addResponseHeader("My_Response_Time", LocalDateTime.now().toString())
                                        .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(userKeyResolver()))
                                        .retry(retryConfig -> retryConfig.setRetries(3)
                                                .setMethods(HttpMethod.GET)
                                                .setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),2, true))
                                )
                                .uri("lb://DISCOUNT"))
                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 1, 1);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }
}
