package com.example.discount.service;

import com.example.discount.entity.Coupon;
import com.example.discount.repository.CouponRepository;
import com.example.discount.service.contract.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;


    @Override
    public Coupon insertCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getCouponById(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        return coupon.orElse(null);
    }

    @Override
    public void deleteCouponById(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public Coupon updateCoupon(Coupon newInfo) {
        return couponRepository.save(newInfo);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponRepository.findAll();
    }
}
