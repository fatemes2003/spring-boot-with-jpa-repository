package com.example.discount;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponResponse {
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private BigDecimal discount;
    @NonNull
    private LocalDate expiryDate;
}
