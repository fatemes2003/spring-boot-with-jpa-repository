package com.example.discount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DISCOUNT",fallback = DiscountFallBack.class)
public interface DiscountClient {
    @GetMapping("/api/v1/coupons/get-by-code/{code}")
    public CouponResponse getCouponByCode(@PathVariable("code") String code);
}
//http://DISCOUNT/api/v1/coupons/get-by-code/{code}