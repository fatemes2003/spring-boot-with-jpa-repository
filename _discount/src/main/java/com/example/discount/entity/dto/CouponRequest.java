package com.example.discount.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequest {
    private Long id;

    private String code;

    private BigDecimal discount;

    private LocalDate expiryDate;
}
