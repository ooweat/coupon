package kr.co.ooweat.service;

import kr.co.ooweat.model.Coupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class CouponService {

}
