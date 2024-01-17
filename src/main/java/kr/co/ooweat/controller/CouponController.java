package kr.co.ooweat.controller;

import kr.co.ooweat.model.Coupon;
import kr.co.ooweat.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;
    
    /*@GetMapping
    public Coupon find(@AuthenticationPrincipal final Long seq, final Coupon request){
        return couponService.find(request, seq);
    }*/
    
}
