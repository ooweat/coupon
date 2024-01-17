package kr.co.ooweat.model;

import lombok.Data;

@Data
public class Coupon {
    int seq, userSeq, prevAmount, tranAmount, nextAmount, couponStatus, sendCount;
    String couponNo, couponType, expireDate, recvType, recvTarget, merchantName;
}
