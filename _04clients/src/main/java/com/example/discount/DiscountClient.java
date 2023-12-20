package com.example.discount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("DISCOUNT")
public interface DiscountClient {
    @RequestMapping(value = "/api/v1/coupons/get-by-code/{code}",method = RequestMethod.GET)
    public ResponseEntity<CouponResponse> getCouponByCode(@PathVariable(name = "code") String code);
}
//http://DISCOUNT/api/v1/coupons/get-by-code/{code}