package com.mb.springcloud.couponservice.repo;

import com.mb.springcloud.couponservice.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Coupon findByCode(String code);
}
