package com.example.discount.service.contract;

import com.example.discount.entity.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    public Coupon insertCoupon(Coupon coupon);
    public Coupon getCouponById(Long id);
    public void deleteCouponById(Long id);
    public Coupon updateCoupon(Coupon newInfo);
    public List<Coupon> getAllCoupon();
    public Coupon findByCouponCode(String code);
}
