package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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

                                )
                                .uri("lb://PRODUCT")
                )
                .route(predicateSpec ->
                        predicateSpec
                                .path("/api/v1/coupons/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/api/v1/coupons?(<segment>.*)","/api/v1/coupons/${segment}" )
                                        .addResponseHeader("My_Response_Time", LocalDateTime.now().toString())
                                )
                                .uri("lb://DISCOUNT"))
                .build();
    }
}
