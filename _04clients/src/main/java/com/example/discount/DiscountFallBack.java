package com.example.discount;

import org.springframework.stereotype.Component;

@Component
public class DiscountFallBack implements DiscountClient{
    @Override
    public CouponResponse getCouponByCode(String code) {
        //cache
        //sms
        return null;
    }
}
