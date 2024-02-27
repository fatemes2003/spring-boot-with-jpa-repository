package com.example.discount.controller;

import com.example.discount.entity.Coupon;
import com.example.discount.entity.dto.CouponRequest;
import com.example.discount.entity.dto.CouponResponse;
import com.example.discount.service.contract.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/coupons")
@Slf4j
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody CouponRequest couponRequest) {
        Coupon coupon = modelMapper.map(couponRequest, Coupon.class);
        coupon = couponService.insertCoupon(coupon);
        CouponResponse couponResponse = modelMapper.map(coupon, CouponResponse.class);
        return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CouponResponse> updateCoupon(@RequestBody CouponRequest couponRequest) {
        Coupon coupon = modelMapper.map(couponRequest, Coupon.class);
        coupon = couponService.updateCoupon(coupon);
        CouponResponse couponResponse = modelMapper.map(coupon, CouponResponse.class);
        return ResponseEntity.ok().body(couponResponse);
    }

    @GetMapping
    public List<CouponResponse> getAllCoupon() {
        return couponService.getAllCoupon().stream().map(coupon -> modelMapper.map(coupon, CouponResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> getCouponById(@PathVariable(name = "id") Long id) {
        Coupon coupon = couponService.getCouponById(id);
        CouponResponse couponResponse = modelMapper.map(coupon, CouponResponse.class);
        return ResponseEntity.ok().body(couponResponse);
    }

    @RequestMapping(value = "get-by-code/{code}",method = RequestMethod.GET)
    public CouponResponse getCouponByCode(@PathVariable(name = "code") String code) {
        log.debug("invoked getCouponByCode");
        //throw new RuntimeException("sasasa");
        Coupon coupon = couponService.findByCouponCode(code);
        CouponResponse couponResponse = modelMapper.map(coupon, CouponResponse.class);
        return couponResponse;
    }
}
