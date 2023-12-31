package com.example.product.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String code;
}
